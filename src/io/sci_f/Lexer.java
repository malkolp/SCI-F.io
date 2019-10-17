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
        int point = 0;
        String point_temp = "";
        int non_alphanumeric_count = 0;

        while (point <= code.length()){
            if (code.charAt(point) == ' '){
                if (non_alphanumeric_count == 3) {
                    register_trinary(point_temp);
                    non_alphanumeric_count = 0;
                }
                else if (non_alphanumeric_count == 2){
                    register_secondary(point_temp);
                    non_alphanumeric_count = 0;
                }
                else
                    register_value(reg.getToken(point_temp));
                point_temp = "";
            } else {
                if (non_alphanumeric_count == 3){
                    register_trinary(point_temp);
                    non_alphanumeric_count = 0;
                    point_temp = "";
                }
                if (code.charAt(point) == '.' && !point_temp.equals("")){
                    if (isNumeric(point_temp)){
                        point_temp = point_temp+code.charAt(point);
                        point++;
                        continue;
                    }
                }
                if (reg.checkNonAlphanumeric(code.charAt(point))){
                    point_temp = point_temp+code.charAt(point);
                    non_alphanumeric_count++;
                } else {
                    if (code.charAt(point) == 'f' && !point_temp.equals("")){
                        if (isDecimal(point_temp)){
                            point_temp = point_temp+code.charAt(point);
                            point++;
                            continue;
                        }
                    } else {
                        if (!isAlphanumerical(point_temp)){
                        }
                    }
                }
            }
        point++;
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
