package io.sci_f.handler.error;

class Error {

    private String message;
    private String detail;
    private int line;

    Error(String message,String detail, int line){
        this.message = message;
        this.detail = detail;
        this.line = line;
    }

    @Override
    public String toString() {
        System.out.println("COMPILE ERROR!");
        System.out.println("error cause  : "+this.message);
        System.out.println("error detail : "+this.detail+" at line "+this.line);
        return "";
    }
}
