package com.huawei;

import com.huawei.util.InputDataUtil;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/** A demo illustrating how to call the OpenIE system programmatically.
 */
public class NLPProcessor {

    private static Logger logger = Logger.getLogger(RegexWordSearch.class.getName());

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

        // Properties setup list of annotators to run and settings for neural algorithm.
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        properties.setProperty("coref.algorithm", "neural");

        // build pipelineCoreNLP object
        StanfordCoreNLP pipelineCoreNLP = new StanfordCoreNLP(properties);

        // create and annotate coreDocument object
        CoreDocument coreDocument = new CoreDocument(content);
        pipelineCoreNLP.annotate(coreDocument);

        List<List> allTriples = constructTriples(coreDocument);

        //Load Triple to Neo4j
        if(loadTriplesToNeo4j){
            hostTripleOnNeo4j(allTriples);
        }

        logger.info("All the triples : " + allTriples);

        return allTriples;
    }

    private static List<List> constructTriples(CoreDocument coreDocument){
        int tripleCounter = 0;
        List<List> allTriples = new ArrayList<>();
        StringBuilder tripleElement = new StringBuilder();

        for (CoreLabel coreLabel : coreDocument.tokens()) {
            if (coreLabel.tag().startsWith("VB")) {
                List<String> currentTriple = new ArrayList<>();
                String tripleElementStr =  tripleElement.toString().trim();
                currentTriple.add(tripleElementStr);
                currentTriple.add(coreLabel.originalText());
                if (tripleCounter > 0) {
                    List<String> previousTriple = allTriples.get(tripleCounter - 1);
                    previousTriple.add(tripleElementStr);
                }
                allTriples.add(currentTriple);
                tripleElement = new StringBuilder();
                tripleCounter++;
            } else {
                tripleElement.append(coreLabel.before()).append(coreLabel.originalText());
            }
        }

        if(!allTriples.isEmpty()) {
            //Adding after words to the triple of last verb
            List<String> previousTriple = allTriples.get(tripleCounter - 1);
            previousTriple.add(tripleElement.toString().trim());
        }
        return allTriples;
    }

    private static void hostTripleOnNeo4j(List<List> allTriples) throws Exception{
        //Change the details below if you are connecting to a different instance of Neo4J
        try ( Neo4JConnection connection = new Neo4JConnection( "bolt://localhost:7687",
                "neo4j", "test" ) )
        {
            for(List<String> triple : allTriples) {
                connection.loadTriple(triple);
            }

        }
    }
}