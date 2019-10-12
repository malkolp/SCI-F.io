package io.sci_f;

import io.sci_f.handler.error.ErrorManager;

public class Core {

    private static Core instance;
    private static String code;

    private Core(){
        Preprocessor.init();
        Lexer.init();
        Syntaxer.init();
        Semantic.init();
    }

    public static Core init(){
        if (instance == null) instance = new Core();
        return instance;
    }

    public void start(String file){
        String temp = Preprocessor.readFile(file);
        if (!ErrorManager.isError()){
            code = temp;
        }
    }

    public void compile(){
        if (!ErrorManager.isError()){
            
        }
    }
}
