package io.sci_f.register;

import java.util.HashMap;

class TokenTable {

    private static TokenTable instance;
    private static HashMap<String,Token> tokentable;

    private TokenTable(){tokentable = new HashMap<>();}

    static void init(){if (instance == null) instance = new TokenTable();}

    static TokenTable get(){if (instance == null) init();return instance;}

    void add(String key,double code, int action, int type){
        tokentable.put(key,new Token(code, action,type));
    }

    int get_action(String key){return tokentable.get(key).getAction();}

    double get_code(String key){return tokentable.get(key).getCode();}

    double get_type(String key){return tokentable.get(key).getCode();}

    static void end(){
        tokentable = null;
        instance = null;
    }

}
