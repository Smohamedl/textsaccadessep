<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
    <head>
        <title>Struts 2 hello world example</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style type="text/css">
            #textParagraph{
                width: 1000px;
                font-size: 20px;
            }

            .textSaccade1{
                color: green;
            }
            .textSaccade2{
                color: red;
            }
            .textSaccade3{
                color: blue;
            }
            .textSaccade4{
                color: black;
            }
            .textSaccade5{
                color: purple;
            }

        </style>
    </head>

    <body>
    <h1><s:text name="welcome" /></h1>

    <s:if test="hasActionErrors()">
        <div id="fieldErrors">
            <s:actionerror/>
        </div>
    </s:if>


    <s:form action="separatorAction" namespace="/" method="post" name="myForm" theme="xhtml">
        <s:textfield name="saccadesNbr" type="number" min="2" max="5" label="How many columns ? "/>

        <s:textarea name="text" cols="100" rows="7" label="Please enter your text below"/>

        <s:submit key="submit" label="Go" />
    </s:form>

    <div id="textParagraph">
        <s:iterator var="ligne" value="lignes" status="rowStatus">
            <s:iterator var="c" value="ligne" status="i">
                <s:if test="%{#i.index==0}">
                    <span class="textSaccade1">
                </s:if>
                <s:if test="%{#i.index==1}">
                    <span class="textSaccade2">
                </s:if>
                <s:if test="%{#i.index==2}">
                    <span class="textSaccade3">
                </s:if>
                <s:if test="%{#i.index==3}">
                    <span class="textSaccade4">
                </s:if>
                <s:if test="%{#i.index==4}">
                    <span class="textSaccade5">
                </s:if>
              <s:property value="c" escapeHtml="false" />
            </span>
            </s:iterator>
            <br/>
        </s:iterator>


    </div>

    </body>
</html>