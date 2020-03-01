<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>words-spliter</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
    <script type="text/javascript"></script>
</head>
<body>
    <section>
        <section>
	        <form action="${pageContext.request.contextPath }/WordSplit.do"  name="actionForm" id="actionForm"  method="post" >
                <table width="80%" align="center" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="left" nowrap class="ed0" style="border-top:none;">WORD SPLITER</td>
                    </tr>
                    <tr>
                        <td align="left" nowrap class="ed1" style="border-top:none;">
                            Entry Sentences need to be split:
                            <c:if test="${radio==null}"><c:set var="radio"  value="basic"></c:set></c:if>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;by using
                            <c:if test="${radio=='basic'}"><input type="radio" name="scope" value="basic" CHECKED>&nbsp;basic dictionary&nbsp;</c:if>
                            <c:if test="${radio!='basic'}"><input type="radio" name="scope" value="basic">&nbsp;basic dictionary&nbsp;</c:if>
                            <c:if test="${radio=='custom'}"><input type="radio" name="scope" value="custom" CHECKED>&nbsp;customized dictionary&nbsp;</c:if>
                            <c:if test="${radio!='custom'}"><input type="radio" name="scope" value="custom" >&nbsp;customized dictionary&nbsp;</c:if>
                            <c:if test="${radio=='both'}"><input type="radio" name="scope" value="both" CHECKED>&nbsp;both dictionary&nbsp;</c:if>
                            <c:if test="${radio!='both'}"><input type="radio" name="scope" value="both" >&nbsp;both dictionary&nbsp;</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td align="left" nowrap class="ed1" style="border-top:none;">
                            <textarea class="edit_textarea" id="inputStr1" name="inputStr1" onFocus="select();"
                                      onKeyDown="textarea_length(event, 'inputStr1', 100);">${inputStr1}</textarea>（100 char limited）
                            <input type="submit" value="SPLIT"/>
                        </td>
                    </tr>
                    <tr>
                        <td nowrap align="left" class="ed1" style="border-top:none;"><b>result:</b>${msg}</td>
                    </tr>
                    <tr>
                        <td nowrap align="left" class="ed1" style="border-top:none;">
                        </td>
                    </tr>
                    <c:if test="${resultListSize>0}">
                    <c:forEach items="${resultList}" var="result">
                        <tr>
                            <td nowrap align="left" class="ed1" style="border-top:none;">${result}</td>
                        </tr>
                    </c:forEach>
                    </c:if>
                    <tr>
                        <td nowrap align="left" class="ed1" style="border-top:none;"><br><br></td>
                    </tr>
                    <tr>
                        <td nowrap align="left" class="ed1" style="border-top:none;"><b>basic dictionary :</b></td>
                    </tr>
                    <tr>
                        <td nowrap align="left" class="ed1" style="border-top:none;">i, like, sam, sung, samsung, mobile, ice, cream, man, go </td>
                    </tr>
                    <tr>
                        <td nowrap align="left" class="ed1" style="border-top:none;"><b>customized dictionary:</b></td>
                    </tr>
                    <tr>
                        <td nowrap align="left" class="ed1" style="border-top:none;">example: do,mango,love,orange,perfect</td>
                    </tr>
                    <tr>
                        <td nowrap align="left" class="ed1" style="border-top:none;">
                            <textarea class="edit_textarea" id="inputStr2" name="inputStr2" onFocus="select();"
                                      onKeyDown="textarea_length(event, 'inputStr2', 100);">${inputStr2}</textarea>（100 char limited）
                        </td>
                    </tr>
                </table>
            </form>
        </section>
    </section>
</body>
</html>
