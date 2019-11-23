package com.huawei.util;

import com.huawei.NLPProcessor;

import java.io.File;
import java.nio.file.Files;

public class InputDataUtil {

    public static String loadInputFile() throws Exception {
        ClassLoader classLoader = new NLPProcessor().getClass().getClassLoader();

        File file = new File(classLoader.getResource("textInput.txt").getFile());

        //Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));

        return content;
    }
}
