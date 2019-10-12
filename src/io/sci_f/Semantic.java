package io.sci_f;

class Semantic {

    private static Semantic instance;

    private Semantic(){

    }

    static Semantic init(){
        if (instance == null) instance = new Semantic();
        return instance;
    }

}
