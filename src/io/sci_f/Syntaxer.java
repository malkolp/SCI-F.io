package io.sci_f;

class Syntaxer {

    private static Syntaxer instance;

    private Syntaxer(){

    }

    static Syntaxer init(){
        if (instance == null) instance = new Syntaxer();
        return instance;
    }

}
