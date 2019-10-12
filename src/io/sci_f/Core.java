package io.sci_f;

public class Core {

    private static Core instance;

    private Core(){
        Lexer.init();
    }

    public static Core init(){
        if (instance == null) instance = new Core();
        return instance;
    }
}
