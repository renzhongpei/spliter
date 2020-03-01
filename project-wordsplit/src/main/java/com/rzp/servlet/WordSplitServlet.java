package com.rzp.servlet;

import com.rzp.util.Verifry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSplitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //建立基本、客户定制、共同词典， build basic/custom/both dictionary map
        Map basic = new HashMap();
        Map custom = new HashMap();
        Map both = new HashMap();

        //获取页面录入信息,get messages from jsp
        String sentence = req.getParameter("inputStr1");
        String customdi = req.getParameter("inputStr2");
        String scope = req.getParameter("scope");
        req.getSession().setAttribute("inputStr1",sentence);
        req.getSession().setAttribute("inputStr2",customdi);
        req.getSession().setAttribute("radio",scope);
        req.getSession().setAttribute("msg"," ");


        //用客户字典时必须填写字典， must input dictionary when not using basic
        if(!"basic".equals(scope)&&("".equals(customdi)||customdi==null)){
            req.getSession().setAttribute("msg", "   customized dictionary should not be null when using customized dictionary or both");
            req.getRequestDispatcher("word-spliter.jsp").forward(req,resp);
        }else{
            //remove all space ,line feed...
            sentence = this.removeEntry(sentence);
            customdi = this.removeEntry(customdi);
            sentence = sentence.trim().toLowerCase();

            if (!"basic".equals(scope)){
                //把客户给的字典按逗号分隔开,split custom dictionary by ","
                String[] cusdic = customdi.split(",");
                //验证录入是否为英文,verify is letter or not
                for (String temp:cusdic) {
                    System.out.println("|"+temp+"|");
                    temp = temp.trim().toLowerCase();
                    System.out.println(Verifry.verifyLetter(temp));
                    if(Verifry.verifyLetter(temp)){
                        req.getSession().setAttribute("msg", temp + " should be only letters");
                        req.getRequestDispatcher("word-spliter.jsp").forward(req,resp);
                        return;
                    }else{
                        if(!both.containsKey(temp))both.put(temp,temp);
                        if(!custom.containsKey(temp))custom.put(temp,temp);
                    }
                }
            }
        }

        //获取基本词典 get basic dictionary
        String[] queryName = this.getBasicDictionary();

        for(String temp:queryName) {
            if(!basic.containsKey(temp))basic.put(temp,temp);
            if(!both.containsKey(temp))both.put(temp,temp);
        }

        List resultList = new ArrayList<>();
        //根据选项使用不同的字典执行分割程序，break word by using different dictionary according choice
        if("both".equals(scope)){
            this.spliter(both,sentence,0,"",resultList);
        }else if("custom".equals(scope)){
            this.spliter(custom,sentence,0,"",resultList);
        }else{
            this.spliter(basic,sentence,0,"",resultList);
        }
        if(resultList.size()<=0){
            req.getSession().setAttribute("msg", " There has no result by using this dictionary.");
        }
        req.setAttribute("resultList",resultList);
        req.setAttribute("resultListSize",resultList.size());
        req.getRequestDispatcher("word-spliter.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void spliter(Map Dictionar, String sentence, int start, String result,List resultList) {
        if(start == sentence.length()) {
            resultList.add(result);
//            System.out.println(result);
            return;
        }
        //循环检查sentence的每个组合，如果是就放到result里，exam every combination from sentence,when combaination in dictionary.put in result
        for(int i = start + 1; i <= sentence.length(); i++) {
            String exam = sentence.substring(start, i);
            if(Dictionar.containsKey(exam)){
                this.spliter(Dictionar,sentence, i, result + exam + " ",resultList);
            }
        }
    }
    public String[] getBasicDictionary(){
        int querySize = 11;
        String[] queryName = new String[querySize];
        querySize = 0;
        queryName[querySize++] = "i";
        queryName[querySize++] = "like";
        queryName[querySize++] = "sam";
        queryName[querySize++] = "sung";
        queryName[querySize++] = "samsung";
        queryName[querySize++] = "mobile";
        queryName[querySize++] = "ice";
        queryName[querySize++] = "cream";
        queryName[querySize++] = "man";
        queryName[querySize++] = "go";
        queryName[querySize++] = "and";
        return queryName;
    }
    public String removeEntry(String sentence){
        if (sentence!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n|[ ]");
            Matcher m = p.matcher(sentence);
            sentence = m.replaceAll("");
        }
        sentence = sentence.trim().toLowerCase();
        return sentence;
    }
}
