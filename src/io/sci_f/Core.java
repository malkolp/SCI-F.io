package io.sci_f;

import io.sci_f.brain.Brain;
import io.sci_f.register.Register;

public class Core {

    private static Core instance;

    private Core(){}

    public static void init(){
        if (instance == null) instance = new Core();
        Brain.init();
        Register.init();
    }

    public void load(){
        Brain br = Brain.get();
        Register.init_token(br.keyword(),br.code(),br.action());
    }

    public static Core get(){
        if (instance == null) init();
        return instance;
    }

    public void end(){
        Register.end();
        Brain.end();
        instance = null;
    }
}
