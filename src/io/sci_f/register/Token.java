package io.sci_f.register;

class Token {
    private double code;
    private int action;
    private int type;

    Token(double code,int action, int type){
        this.code = code;
        this.action = action;
        this.type = type;
    }

    double getCode() {
        return code;
    }

    int getAction() {
        return action;
    }

    int getType() { return type; }
}
