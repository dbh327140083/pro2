package com.ujiuye.utils;

import org.junit.Test;

public class FileNameUtils {

    public static String subFileName(String fileName){
       //    http://localhost/img/350af2b8-d4b3-458a-97d7-9fa5c26b0001java基础.jpg
        return fileName.substring(fileName.indexOf("g") + 2);
    }

}
