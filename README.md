1. WordFinder : This class implements the below requirement. All the tests are in "WordFinderTest"

"Given some string of words and a word w, count the number of words that preceed the given word w in the text and also print them. All text is in lowercase.

For example:

Input String: "all featured content undergoes a thorough review process to ensure that it meets the highest standards in to serve as the best example of our end goals"

Input Word: "to"

Output:（"process", "in", 2)

DateUtils : This class implements the below requirement. All the tests are in "DateUtilsTest"

Create a function getDatNum(int day, int month, int year) that gets the day, month and year and determines which day of the year it is. (don't use the Java SDK date class)

Examples:

2. getDatNumByData（12,11,2008）= 317 getDatNumByData（12,11,2007）= 316 getDatNumByData（1,1,2000）= 1 getDatNumByData（29,2,1999）= -1

VerbTriplesGenerator : This class implements below requirements. Al the unit tests are in "VerbTriplesGeneratorTest"

Write a program that reads a text in txt and does the following:

parses it into sentences

3. Identifies the verbs in the sentence. (For Parsing text and recognising verbs you can use any existing NLP library like SpaCy, NLTK, CoreNLP, etc., that are available in Python and/or Java, or any other API you are aware of.)

Having verbs as a center creates triples of the form where Text1 is the text preceding the verb until the previous verb Text2 is the text succeeding the verb until the next verb Note, different triples may have overlapping text (end text of one triple is the start text of the next) For example, in a sentence like

"all featured content undergoes a thorough review process to ensure that it meets the highest standards in to serve as the best example of our end goals"

we obtain triples like the following ones.

<"all" "featured" "content">, <"content" "undergoes" "a thorough review process to">, <"a thorough review process to" "ensure" "that it"> <"that it" "meets" "the highest standards in to"> ...and more

Load the extracted triples into Neo4J creating a graph of strings that are parts of the sentence.