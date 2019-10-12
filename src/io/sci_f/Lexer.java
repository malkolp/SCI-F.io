package io.sci_f;

import io.sci_f.register.SymbolTableManager;
import io.sci_f.register.TokenTableManager;

public class Lexer {

    private static Lexer instance;

    private Lexer(){
        SymbolTableManager.init();
        TokenTableManager.init();
    }

    public static Lexer init(){
        if (instance == null) instance = new Lexer();
        return instance;
    }

}
