package com.rzp.util;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verifry {
    public static Boolean verifyEmpty( String inputStr, String name) {
        if ( inputStr.length()==0 ){
            return true;
        }else {
            return false;
        }
    }
    public static Boolean verifyLetter(String inputStr) {
        Pattern pattern = Pattern.compile("[A-Za-z_]+");
        Matcher isNum = pattern.matcher(inputStr);
        if(!isNum.matches()) {
            return true;
        }else{
            return false;
        }
    }

}
