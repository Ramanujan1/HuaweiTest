package com.huawei;

import org.neo4j.driver.v1.*;

import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;

public class Neo4JConnection implements AutoCloseable
    {
        private final Driver driver;


        public Neo4JConnection(String uri, String user, String password )
        {
            driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
        }

        @Override
        public void close() throws Exception
        {
            driver.close();
        }


        public void loadTriple(List<String> triple){

            try ( Session session = driver.session() )
            {
                String tripleResult = session.writeTransaction( new TransactionWork<String>()
                {
                    @Override
                    public String execute( Transaction tx )
                    {
                        StatementResult result = tx.run( "MERGE (t:Token {message: $triple}) " +
                                        "MERGE (t1:Token {message: $triple1}) " +
                                        "MERGE (t2:Token {message: $triple2}) " +
                                        "MERGE (t)-[:Verb]->(t1) " +
                                        "MERGE (t2)-[:Verb]->(t1) " +
                                        "RETURN t.message + ' ' + t1.message + ' ' + t2.message ",
                                parameters("triple", triple.get(0),
                                        "triple1", triple.get(1),
                                        "triple2", triple.get(2))  );
                        return result.single().get( 0 ).asString();
                    }
                } );
                System.out.println( tripleResult );
            }
        }
    }

