package io.sci_f;

import io.sci_f.register.Register;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Lexer {

    private static Lexer instance;
    private Queue queue = Queue.get();
    private Register reg = Register.get();
    private static Pattern numeric = Pattern.compile("^[\\d]+$");
    private static Pattern decimal = Pattern.compile("^[\\d]+[.]?$[\\d]+$");
    private static Pattern alphanumerical = Pattern.compile("^[\\w_$]+$");
    private static Matcher matcher;

    private Lexer(){}

    static void init(){if (instance == null) instance = new Lexer();}

    static Lexer get(){if (instance == null)init(); return instance;}

    static void end(){instance = null;}

    void process(String code){
        int pointer = 0;
        String key = "";
        int non_alphanumeric_count = 0;

        while(pointer < code.length()){
            if (code.charAt(pointer) == ' '){
                //token.register key
                non_alphanumeric_count = 0;
                key = "";
                pointer++;
            } else {
                if (isAlphanumerical(code.charAt(pointer)+"")){
                    if (isAlphanumerical(key)){
                        key = key+code.charAt(pointer);
                        pointer++;
                    } else {
                        //token.register key
                        non_alphanumeric_count++;
                        pointer++;
                    }
                }
            }
        }
    }

    private boolean register_value(double[] value){
        if (value[0] != -1){
            queue.enqueue(value[0]);
            queue.enqueue(value[1]);
            queue.enqueue(value[2]);
            return true;
        }
        return false;
    }

    private void register_trinary(String temp){
        if (!register_value(reg.getToken(temp))){
            String third_token = temp.charAt(2)+"";
            temp = temp.charAt(0)+temp.charAt(1)+"";
            if (!register_value(reg.getToken(temp))){
                String second_token = temp.charAt(1)+"";
                temp = temp.charAt(0)+"";
                register_value(reg.getToken(temp));
                register_value(reg.getToken(second_token));
            }
            register_value(reg.getToken(third_token));
        }
    }

    private void register_secondary(String temp){
        if (!register_value(reg.getToken(temp))){
            String second_token = temp.charAt(1)+"";
            temp = temp.charAt(0)+"";
            register_value(reg.getToken(temp));
            register_value(reg.getToken(second_token));
        }
    }

    private boolean isNumeric(String temp){
        matcher = numeric.matcher(temp);
        return matcher.find();
    }

    private boolean isDecimal(String temp){
        matcher = decimal.matcher(temp);
        return matcher.find();
    }

    private boolean isAlphanumerical(String temp){
        matcher = alphanumerical.matcher(temp);
        return matcher.find();
    }

}
