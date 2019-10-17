package io.sci_f;

import io.sci_f.brain.Brain;
import io.sci_f.register.Register;

public class Core {

    private static Core instance;
    private String preprocess;

    private Core(){}

    public static void init(){
        if (instance == null) instance = new Core();
        Brain.init();
        Register.init();
        Pre.init();
        Queue.init();
        Lexer.init();
    }

    public void load(){
        Brain br = Brain.get();
        Register.init_token(br.keyword(),br.code(),br.action(),br.type());
    }

    public String preprocess(String url){
        preprocess = Pre.get().process(url);
        return preprocess;
    }

    public void process(){
        Lexer lexer = Lexer.get();
        lexer.process(preprocess);
    }

    public static Core get(){
        if (instance == null) init();
        return instance;
    }

    public void reset(){

    }

    public void end(){
        Lexer.end();
        Queue.end();
        Pre.end();
        Register.end();
        Brain.end();
        instance = null;
    }
}
