package io.sci_f.register;

import java.util.ArrayList;

public class Register {

    private static Register instance;

    private Register(){}

    public static void init(){
        if (instance == null) {
            instance = new Register();
            SymbolTable.init();
            TokenTable.init();
        }
    }

    public static void init_token(ArrayList<String> key,ArrayList<Double> code, ArrayList<Integer> action, ArrayList<Integer> type){
        TokenTable tkn = TokenTable.get();
        for (int i = 0 ; i < key.size();i++){
            tkn.add(key.get(i),code.get(i),action.get(i),type.get(i));
        }
    }

    public boolean checkNonAlphanumeric(String key){
        return TokenTable.get().isNotAlphanumeric(key);
    }

    public double[] getToken(String key){
        return TokenTable.get().getValue(key);
    }

    public static Register get(){if (instance == null) init();return instance;}

    public static void end(){
        TokenTable.end();
        SymbolTable.end();
        instance = null;
    }
}
