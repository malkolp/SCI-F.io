package io.sci_f;

import io.sci_f.register.Register;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Lexer {

    private static Lexer instance;
    private Queue queue = Queue.get();
    private Register reg = Register.get();
    private static Pattern numeric = Pattern.compile("^[\\d]+$");
    private static Pattern decimal = Pattern.compile("^[\\d]+[.][\\d]+$");
    private static Pattern floats = Pattern.compile("^[\\d]+[.]?[\\d]+f$");
    private static Pattern alphanumerical = Pattern.compile("^[\\w_$]+$");
    private static Matcher matcher;

    private Lexer(){}

    static void init(){if (instance == null) instance = new Lexer();}

    static Lexer get(){if (instance == null)init(); return instance;}

    static void end(){instance = null;}

    void process(String code){
        int pointer = 0;
        String key = "";
        String chr;
        int nonalphanum = 0;

        while(pointer < code.length()){
            chr = code.charAt(pointer)+"";
            if (isSpace(chr)){
                if (isAlphanumerical(key) || isDecimal(key) || isFloat(key)){
                    register_token(key,true);
                    key = "";
                } else {
                    register_token(key,false);
                    key = "";
                    nonalphanum = 0;
                }
            } else {
                if (isAlphanumerical(chr)){
                    if (isF(chr)){
                        if (isDecimal(key) || isAlphanumerical(key)){
                            key = key + chr;
                        } else {
                            if (isFloat(key)){
                                register_token(key,true);
                                key = chr;
                            } else {
                                register_token(key,false);
                                key = chr;
                                nonalphanum = 0;
                            }
                        }
                    } else {
                        if (isAlphanumerical(key)){
                            if (isDot(chr)){
                                if (isNumeric(key)){
                                    key = key + chr;
                                } else {
                                    register_token(key,true);
                                    key = chr;
                                    nonalphanum = 1;
                                }
                            } else {
                                register_token(key,true);
                                key = chr;
                                nonalphanum = 1;
                            }
                        } else {
                            if (isFloat(key)){
                                register_token(key,true);
                                key = chr;
                                nonalphanum = 1;
                            } else {
                                if (nonalphanum == 3){
                                    register_token(key,false);
                                    key = chr;
                                    nonalphanum = 1;
                                } else {
                                    key = key + chr;
                                    nonalphanum++;
                                }
                            }
                        }
                    }
                }
            }
            pointer++;
        }
    }

    private void register_token(String key, boolean usealphanum){
        double[] value;
        int length = key.length();

        if (usealphanum){
            if (isAlphanumerical(key) || isDecimal(key) || isFloat(key)){
                value = reg.getToken(key);
                queue.enqueue(value[0]);
                queue.enqueue(value[1]);
                queue.enqueue(value[2]);
            } else {
                if (reg.checkNonAlphanumeric(key)){
                    value = reg.getToken(key);
                    queue.enqueue(value[0]);
                    queue.enqueue(value[1]);
                    queue.enqueue(value[2]);
                } else {
                    StringBuilder key1 = new StringBuilder();
                    String key2;
                    if (length <= 2){
                        key1 = new StringBuilder(key.charAt(0) + "");
                        key2 = key.charAt(1)+"";
                    } else {
                        for (int i = 0; i < length - 2; i++ )
                            key1.append(key.charAt(i));
                        key2 = key.charAt(length-1)+"";
                    }
                    register_token(key1.toString(),false);
                    register_token(key2,false);
                }
            }
        } else {
            if (reg.checkNonAlphanumeric(key)){
                if (length > 1){
                    StringBuilder key1 = new StringBuilder();
                    String key2;
                    if (length > 2){
                        for (int i = 0; i < length - 2; i ++)
                            key1.append(key.charAt(i));
                        key2 = key.charAt(length-1)+"";
                    } else {
                        key1 = new StringBuilder(key.charAt(0) + "");
                        key2 = key.charAt(1)+"";
                    }
                    register_token(key1.toString(),false);
                    register_token(key2,false);
                } else {
                    if (reg.checkNonAlphanumeric(key)){
                        value = reg.getToken(key);
                        queue.enqueue(value[0]);
                        queue.enqueue(value[1]);
                        queue.enqueue(value[2]);
                    } else {
                        queue.enqueue(-1);
                        queue.enqueue(-1);
                        queue.enqueue(-1);
                    }
                }
            }
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

    private boolean isFloat(String temp){
        matcher = floats.matcher(temp);
        return matcher.find();
    }

    private boolean isAlphanumerical(String temp){
        matcher = alphanumerical.matcher(temp);
        return matcher.find();
    }

    private boolean isSpace(String character){
        return character.equals(" ");
    }

    private boolean isF(String character){
        return character.equals("f");
    }

    private boolean isDot(String character){
        return character.equals(".");
    }

}
