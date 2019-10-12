package io.sci_f.register;

class Identifier {

    private String id;
    private double datatype;
    private boolean usefinal;
    private boolean usestatic;
    private boolean fixed;
    private String value;

    Identifier(String id,double datatype, boolean fixed){
        this.id = id;
        this.datatype = datatype;
        this.fixed = fixed;
    }

    void setValue(String value){
        this.value = value;
    }

    void setFixed(boolean fixed){
        this.fixed = fixed;
    }

    String getId(){
        return id;
    }

    String getValue(){
        return value;
    }

    boolean isFixed(){
        return fixed;
    }

    boolean isUsefinal(){
        return usefinal;
    }

    boolean isUsestatic(){
        return usestatic;
    }


}
