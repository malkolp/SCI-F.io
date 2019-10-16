package io.sci_f.register;

import java.util.HashMap;

class SymbolTable{

    private static SymbolTable instance;
    private static HashMap<String,Identifier> symboltable;

    private SymbolTable(){symboltable = new HashMap<>();}

    static void init(){if (instance == null) instance = new SymbolTable();}

    static SymbolTable get(){if (instance == null) init();return instance;}

    static void end(){
        symboltable = null;
        instance = null;
    }
}
