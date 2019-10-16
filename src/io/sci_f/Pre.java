package io.sci_f;

class Pre {

    private static Pre instance;

    private Pre(){}

    static void init(){if (instance == null) instance = new Pre();}

    static Pre get(){if (instance == null) init(); return instance;}

    static void end(){ instance = null;}

}
