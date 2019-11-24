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

        try {
            inputText = InputDataUtil.loadInputFile();
        } catch(Exception ex) {
            logger.info("The file was not loaded successfully");
        }

        if( searchString != "") {
            stringOfPreviousMatches = matchString(inputText, searchString);
        }

        return String.format("(%s)", stringOfPreviousMatches);
    }

    public String matchString(String inputText, String searchString) {
        List<String> previousWordList = new ArrayList<>();

        Pattern pattern = Pattern.compile(REG_EX_PREV_STRING+ searchString);
        Matcher matcher = pattern.matcher(inputText);

        while (matcher.find() && searchString != "") {
            previousWordList.add(matcher.group(0).replace(searchString,"").trim());
        }

        if (previousWordList.size() == 0) {
            return "";
        }

        // add double quotes around each string
        previousWordList = previousWordList.stream()
                .map(s -> String.format("\"%s\"", s)).collect(Collectors.toList());

        previousWordList.add(searchString);

        return previousWordList.stream()
                .collect(Collectors.joining(","));
    }
}