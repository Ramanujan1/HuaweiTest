package com.huawei.util;

import org.apache.commons.io.IOUtils;
public class InputDataUtil {

    public static String loadInputFile() throws Exception {
        String content = IOUtils.toString(InputDataUtil.class.getClassLoader()
                .getResourceAsStream("textInput.txt"));

        return content;
    }
}
