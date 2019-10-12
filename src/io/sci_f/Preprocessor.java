package io.sci_f;

import io.sci_f.handler.error.ErrorManager;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

class Preprocessor {

    private static Preprocessor instance;

    private Preprocessor(){}

    static Preprocessor init(){
        if (instance == null) instance = new Preprocessor();
        return instance;
    }

    static String readFile(String filepath){
        try{
            Scanner scanner = new Scanner(Paths.get(filepath), StandardCharsets.UTF_8.name());
            String content = scanner.useDelimiter("\\A").next();
            scanner.close();
            content = content.replaceAll("[\\s]+"," ");
            return content;
        } catch (Exception e){
            ErrorManager.initError(ErrorManager.EM_NoSuchFile,ErrorManager.ED_NotSciExtension,0);
            return "";
        }
    }

    static void end(){
        instance = null;
    }

}
