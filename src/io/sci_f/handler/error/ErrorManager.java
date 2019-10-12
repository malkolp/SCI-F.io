package io.sci_f.handler.error;

public class ErrorManager {

    private static Error error;
    private static boolean hasError;

    public static final String EM_NoSuchFile = "can not compile file";

    public static final String ED_NotSciExtension = "not sci-f.io file.\n file not using sci extension \"*.sci\"";

    public static void initError(String EM,String ED, int line){
        error = new Error(EM,ED,line);
        hasError = true;
    }

    public static void endError(){
        error = null;
        hasError = false;
    }

    public static boolean isError(){
        return hasError;
    }

}
