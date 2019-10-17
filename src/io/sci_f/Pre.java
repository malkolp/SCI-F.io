package io.sci_f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Pre {

    private static Pre instance;

    private Pre(){}

    static void init(){if (instance == null) instance = new Pre();}

    static Pre get(){if (instance == null) init(); return instance;}

    static void end(){ instance = null;}

    String process(String url){
        String input = read_code(url);
        String multiline_comment = "/\\*[\\w\\s~`!@#$%^&*\\(\\)\\{\\}\\[\\]\\-\\+\\=;:'\",./<>?\\\\|]*\\*/";
        String singleline_comment = "//[\\w~`!@#$%^&*\\\\(\\\\)\\\\{\\\\}\\\\[\\\\]\\\\-\\\\+\\\\=;:'\\\",./<>?\\\\\\\\|[^\\S\\n]]*";
        String multispace = "\\s+";
        input = input.replaceAll(multiline_comment,"");
        input = input.replaceAll(singleline_comment,"");
        input = input.replaceAll(multispace," ");
        if (input.length()>0){
            if (input.charAt(0) == ' '){
                StringBuilder sb = new StringBuilder(input);
                sb.deleteCharAt(0);
                input = sb.toString();
            }
        }
        return input;
    }

    private String read_code(String url){
        try {
            String[] temparr = url.split("/");
            String[] tempar2 = temparr[temparr.length-1].split(".");
            if (tempar2[tempar2.length-1].equals("sci")){
                File file = new File(url);
                String tmp;
                String out = "";
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while ((tmp = reader.readLine())!= null){
                    out = out + tmp;
                }
                return out;
            }
        } catch (IOException e) {
            return "";
        }
        return "";
    }

}
