1. RegexWordSearch : This class contains the logic to search for a particular inout string in the sentence and return a comma seperated string with a list of words
                     preceding the input string along with the search string

Problem Statement:
"Given some string of words and a word w, count the number of words that preceed the given word w in the text and also print them. All text is in lowercase.

Use Case:

Input String: "all featured content undergoes a thorough review process to ensure that it meets the highest standards in to serve as the best example of our end goals"

Search String: "to"

Output:（"process", "in", 2)

2.DayCounter : This class contains the logic to calculate the number of days in the given year for the given date and month. This
               also has the logic to consider leapyear number of days. The function countNumberOfDaysInaDate(Integer month, Integer date, Integer year)
               is used to perform these calculations.

Problem Statement:
"Create a function getDatNum(int day, int month, int year) that gets the day, month and year and determines which day of the year it is. (don't use the Java SDK date class)"

Examples:

Input: countNumberOfDaysInaDate（12,11,2008）
Output:317

3. NLPProcessor : This class contains the logic to look for Verbs in a string using Stanford's CoreNLP java api and then create tuples
               constituting before verb string , the veb and the after string, The tuples are the fed as an input to the Neo4j DB
               to generate a graph.

Problem Statement: Write a program that reads a text in txt and does the following:

 parses it into sentences
- Identifies the verbs in the sentence.
(For Parsing text and recognising verbs you can use any existing NLP library like SpaCy, NLTK, CoreNLP, etc., that are available in Python and/or Java, or any other API you are aware of.)


- Having verbs as a center creates triples of the form <Text1 Verb Text2> where
  Text1 is the text preceding the verb until the previous verb
  Text2 is the text succeeding the verb until the next verb
  Note, different triples may have overlapping text (end text of one triple is the start text of the next)
  
  Examples:

  "all featured content undergoes a thorough review process to ensure that it meets the highest standards in to serve as the best example of our end goals"

  we obtain triples like the following ones.

  <"all" "featured" "content">,
  <"content" "undergoes" "a thorough review process to">,
  <"a thorough review process to" "ensure" "that it">
  <"that it" "meets" "the highest standards in to">
  ...and more

- Load the extracted triples into the Neo4J graph DataBase, creating a graph where nodes are annotated with strings. For example, from a triple like <l1 l2 l3> you should create
  * two vertices v1 and v2, where v1 has label l1 and v2 has label l3
  * an edge <v1,v3> between these two vertices.
  * Annotate the edge between them with label l2.