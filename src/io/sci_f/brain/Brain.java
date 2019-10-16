package io.sci_f.brain;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Brain {

    private static Brain instance;
    private ArrayList<String> keyword;
    private ArrayList<Double> code;
    private ArrayList<Integer> action;

    private Brain(){
        keyword = new ArrayList<>();
        code = new ArrayList<>();
        action = new ArrayList<>();
        File file = new File("src/io/sci_f/brain/keytoken.neur");
        Pattern pattern = Pattern.compile("\"([\\w,'\";.\\*/%=?<>!&|^$()\\[\\]{}+-]*)\"\\s+\\|\\s*(\\d+[.\\d]*)\\s*\\|\\s*(\\d+)") ;
        Matcher m;
        String tmp = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((tmp = reader.readLine())!= null){
                m = pattern.matcher(tmp);
                if (m.find()){
                    keyword.add(m.group(1));
                    code.add(Double.parseDouble(m.group(2)));
                    action.add(Integer.parseInt(m.group(3)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init(){if (instance == null) instance = new Brain();}

    public static Brain get(){if (instance == null)init();return instance;}

    public static void end(){
        instance.keyword = null;
        instance.code = null;
        instance.action = null;
        instance = null;
    }

    public ArrayList<String> keyword() {
        return keyword;
    }

    public ArrayList<Double> code() {
        return code;
    }

    public ArrayList<Integer> action() {
        return action;
    }
}
