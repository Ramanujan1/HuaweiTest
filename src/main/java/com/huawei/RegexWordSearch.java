package com.huawei;


import com.huawei.util.InputDataUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexWordSearch {

    private static String  searchString=  "";

    public RegexWordSearch(String searchString) {
        this.searchString = searchString;
    }

    private static String REG_EX_PREV_STRING = "[a-zA-Z_0-9]* ";
    private static Logger logger
            = Logger.getLogger(
            RegexWordSearch.class.getName());

    public String printPreviousString()  {
        String inputText= "";
        String stringOfPreviousMatches = "";

        //load input text from file
        try {
            inputText = InputDataUtil.loadInputFile();
        } catch(Exception ex) {
            logger.info("The file was not loaded successfully");
        }

        if( searchString != "") {
            List<String> previousWordList = matchString(inputText, searchString);
            stringOfPreviousMatches = previousWordList.stream()
                    .collect(Collectors.joining(","));
            String.format("(%s)", stringOfPreviousMatches);
        }

        return String.format("(%s)", stringOfPreviousMatches);
    }

    public List<String> matchString(String inputText, String searchString) {
        List<String> previousWordList = new ArrayList<>();

        //Regular expression pattern matchers
        Pattern pattern = Pattern.compile(REG_EX_PREV_STRING+ searchString);
        Matcher matcher = pattern.matcher(inputText);

        while (matcher.find() && searchString != "") {
            previousWordList.add(matcher.group(0).replace(searchString,"").trim());
        }

        //stop processing if there are no searches
        if (previousWordList.size() == 0) {
            return new ArrayList<>();
        }

        // add double quotes around each string
        previousWordList = previousWordList.stream()
                .map(s -> String.format("\"%s\"", s)).collect(Collectors.toList());

        previousWordList.add(String.valueOf(previousWordList.size()));

        return previousWordList;
    }
}