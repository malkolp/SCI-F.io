package io.sci_f;

class Lexer {

    private static Lexer instance;

    private Lexer(){}

    static void init(){if (instance == null) instance = new Lexer();}

    static Lexer get(){if (instance == null)init(); return instance;}

    static void end(){instance = null;}

    void process(String code){
        Queue queue = Queue.get();
        int point = 0;
        String point_temp = "";

        while (point <= code.length()){

        }
    }

}
