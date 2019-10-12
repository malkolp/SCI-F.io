package io.sci_f.register;

import java.util.HashMap;

public class SymbolTableManager {

    private static HashMap<String,Identifier> symbolTable;
    private static SymbolTableManager instance;

    private SymbolTableManager(){
        symbolTable = new HashMap<>();
    }

    public static SymbolTableManager init(){
        if (instance == null) instance = new SymbolTableManager();
        return instance;
    }

}
