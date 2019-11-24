package com.huawei;

import com.huawei.util.InputDataUtil;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class NLPProcessor {

    private static Logger logger = Logger.getLogger(NLPProcessor.class.getName());

    public static void main(String[] args) throws Exception {
        NLPProcessor.generateVerbTriples("textInput.txt", true) ;
    }

    public static List<List> generateVerbTriples(String filename, boolean loadTriplesToNeo4j)  throws Exception {
        String content = "";

        try {
            content = InputDataUtil.loadInputFile();
        } catch(Exception ex) {
            logger.info("The file was not loaded successfully");
        }

        List<List> finalTriplesList = constructTriples(createCoreDocument(constructProperties(), content));

        logger.info("All the triples : " + finalTriplesList);

        //Load Triple to Neo4j
        if(loadTriplesToNeo4j){
            hostTripleOnNeo4j(finalTriplesList);
        }

        return finalTriplesList;
    }

    private static Properties constructProperties() {
        // Properties setup list of annotators to run and settings for neural algorithm.
        Properties propsCoreNLP = new Properties();
        propsCoreNLP.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        propsCoreNLP.setProperty("coref.algorithm", "neural");

        return propsCoreNLP;
    }

    private static CoreDocument createCoreDocument(Properties coreNLProperties, String content) {
        // build stanfordCoreNLP object
        StanfordCoreNLP stanfordCoreNLP = new StanfordCoreNLP(coreNLProperties);

        // create and annotate coreDocument object
        CoreDocument coreDocument = new CoreDocument(content);
        stanfordCoreNLP.annotate(coreDocument);

        return coreDocument;
    }
    private static List<List> constructTriples(CoreDocument coreDocument){
        int tripleCounter = 0;
        List<List> finalTriples = new ArrayList<>();
        StringBuilder tripleElement = new StringBuilder();

        for (CoreLabel coreLabel : coreDocument.tokens()) {
            if (coreLabel.tag().startsWith("VB")) {
                List<String> newTriple = new ArrayList<>();
                String tripleElementStr =  tripleElement.toString().trim();
                newTriple.add(tripleElementStr);
                newTriple.add(coreLabel.originalText());
                if (tripleCounter > 0) {
                    List<String> previousTriple = finalTriples.get(tripleCounter - 1);
                    previousTriple.add(tripleElementStr);
                }
                finalTriples.add(newTriple);
                tripleElement = new StringBuilder();
                tripleCounter++;
            } else {
                tripleElement.append(coreLabel.before()).append(coreLabel.originalText());
            }
        }

        if(!finalTriples.isEmpty()) {
            //Adding after words to the triple of last verb
            List<String> previousTriple = finalTriples.get(tripleCounter - 1);
            previousTriple.add(tripleElement.toString().trim());
        }
        return finalTriples;
    }

    private static void hostTripleOnNeo4j(List<List> finalTriples) {
        // connect to the neo4j database with the correct connection credentials
        DBConnNeo4j neo4jConnection = new DBConnNeo4j("bolt://localhost:7687", "neo4j", "password");
        neo4jConnection.insertTripleToDB(finalTriples);
    }
}