package main.week1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jojoldu@gmail.com on 2016-06-27.
 */
public class StringCalculator {
    public int add(String str){
        if (isBlank(str)) return 0;

        return sum(getStrArray(str, getSplitChar(str)));
    }

    private boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }

    private int sum(String[] strs) {
        int result = 0;
        for(String s : strs){
            result += str2int(s);
        }
        return result;
    }

    public String getSplitChar(String str){
        String defaultSplit = ",|:";
        String regex = "\\/\\/(.*?)\\\n";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if(!m.find()){
            return defaultSplit;
        }
        return  m.group(1);
    }

    public String[] getStrArray(String str, String split){
        String[] arr = str.split(split);
        if(isNotNumber(arr[0]) && isNotNumber(arr[1])){
            arr[0] = "0";
            arr[1] = arr[1].replaceAll("\\\n", "");
        }
        return arr;
    }

    private boolean isNotNumber(String str){
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private int str2int(String str){
        try {
            int result = Integer.parseInt(str);
            if(result < 0){
                throw new RuntimeException();
            }
            return result;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
