package io.sci_f.register;

import java.util.HashMap;

public class TokenTableManager {

    private static HashMap<String,Token> tokenRegister;
    private static TokenTableManager instance;

    private TokenTableManager(){
        tokenRegister = new HashMap<>();
        installToken();
    }

    public static TokenTableManager init(){
        if (instance == null) instance = new TokenTableManager();
        return instance;
    }

    private void installToken(){
        tokenRegister.put("use",new Token(1,1,0));
        tokenRegister.put("package",new Token(1,2,0));
        tokenRegister.put("fun",new Token(1,3,0));
        tokenRegister.put("print",new Token(2,1,0));
        tokenRegister.put("println",new Token(2,2,0));
        tokenRegister.put("printf",new Token(2,3,0));
        tokenRegister.put("scan",new Token(3,1,0));
        tokenRegister.put("scanf",new Token(3,2,0));
        tokenRegister.put("scanln",new Token(3,3,0));
        tokenRegister.put("var",new Token(4,1,0));
        tokenRegister.put("$id",new Token(5,1,1));
        tokenRegister.put("$const",new Token(5,2,1));
        tokenRegister.put("if",new Token(6,1,0));
        tokenRegister.put("else",new Token(6,2,0));
        tokenRegister.put("then",new Token(6,3,0));
        tokenRegister.put("switch",new Token(7,1,0));
        tokenRegister.put("case",new Token(7,2,0));
        tokenRegister.put("break",new Token(8,1,0));
        tokenRegister.put("continue",new Token(8,2,0));
        tokenRegister.put("for",new Token(9,1,0));
        tokenRegister.put("do",new Token(9,2,0));
        tokenRegister.put("while",new Token(9,3,0));
        tokenRegister.put("to",new Token(10,1,0));
    }

}
