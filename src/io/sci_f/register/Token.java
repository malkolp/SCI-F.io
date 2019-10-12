package io.sci_f.register;

class Token {
    private int[] value;

    Token(int value,int subset,int action){
        this.value = new int[]{value,subset,action};
    }

    int[] getValue(){return value;}
}
