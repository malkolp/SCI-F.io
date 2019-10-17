package io.sci_f.register;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TokenTable {

    private static TokenTable instance;
    private static HashMap<String,Token> tokentable;
    private static HashMap<String,Integer> nonaplhanumeric;

    private static final Pattern identifier = Pattern.compile("^([_]+[\\$\\w_]+[\\w]*|[\\$a-zA-Z]+[\\w]*)");
    private static final Pattern constant = Pattern.compile("^([\\d]+[.]?[\\d]+[f]?|[\\d]+)$");
    private static final Pattern double_constant = Pattern.compile("^([\\d]+[.]?)$");
    private static Matcher matcher;

    private TokenTable(){
        tokentable = new HashMap<>();
        nonaplhanumeric = new HashMap<>();
    }

    static void init(){if (instance == null) instance = new TokenTable();}

    static TokenTable get(){if (instance == null) init();return instance;}

    boolean isNotAlphanumeric(String key){
        return nonaplhanumeric.containsKey(key);
    }

    void add(String key,double code, int action, int type){
        if (type != 0 &&type != 1) nonaplhanumeric.put(key,type);
        tokentable.put(key,new Token(code, action,type));
    }

    double[] getValue(String key){
        double[] value = new double[3];
        Token token;
        if (tokentable.containsKey(key)){
            token = tokentable.get(key);
            value[0] = token.getCode();
            value[1] = token.getAction();
            value[2] = token.getType();
            return value;
        }
        matcher = identifier.matcher(key);
        if (matcher.find()){
            token = tokentable.get("$id");
            value[0] = token.getCode();
            value[1] = token.getAction();
            value[2] = token.getType();
            return value;
        }
        matcher = constant.matcher(key);
        if (matcher.find()){
            token = tokentable.get("$const");
            value[0] = token.getCode();
            value[1] = token.getAction();
            value[2] = token.getType();
            return value;
        }
        value[0] = -1;
        value[1] = -1;
        value[2] = -1;
        return value;
    }

    int get_action(String key){return tokentable.get(key).getAction();}

    double get_code(String key){return tokentable.get(key).getCode();}

    double get_type(String key){return tokentable.get(key).getType();}

    static void end() {
        tokentable = null;
        nonaplhanumeric = null;
        instance = null;
    }

}
