package com.pepay.app.commons.utils;

public class FileTypeVerifyUtil {

    public final static String[] PIC_TYPE = new String[]{"JPG", "JPEG", "PNG"};

    /**
     *
     * @param fileName 文件名
     * @return 验证是否通过
     */
    public static Boolean picTypeVerify(String fileName){
        if(fileName == null || fileName.equals("")){
            return false;
        }
        int index = fileName.lastIndexOf(".")+1;
        if(index == -1){
            return false;
        }
        String suffix = fileName.substring(index).toUpperCase();
        for (String type: PIC_TYPE){
            if(type.equals(suffix)){
                return true;
            }
        }
        return false;
    }
}
