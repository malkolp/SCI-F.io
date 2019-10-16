package io.sci_f.register;

class Token {
    private double code;
    private int action;

    Token(double code,int action){
        this.code = code;
        this.action = action;
    }

    public double getCode() {
        return code;
    }

    public int getAction() {
        return action;
    }
}
