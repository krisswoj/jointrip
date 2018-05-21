<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<html>
<head>
    <title>Add Employee Form</title> 
    <style>
    .error
    {
        color: #ff0000;
        font-weight: bold;
    }
    </style>
</head>
 
<body>
    <h2><spring:message code="lbl.page" text="Add New Employee" /></h2>
    <br/>
    <form:form method="post" modelAttribute="employee">
        <%-- <form:errors path="*" cssClass="error" /> --%>
        <table>
            <tr>
                <td><spring:message code="lbl.userNick" text="First Name" /></td>
                <td><form:input path="userNick" /></td>
                <td><form:errors path="userNick" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="lbl.userEmail" text="Last Name" /></td>
                <td><form:input path="userEmail" /></td>
                <td><form:errors path="userEmail" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="lbl.userPassword" text="Email Id" /></td>
                <td><form:input path="userPassword" /></td>
                <td><form:errors path="userPassword" cssClass="error" /></td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit" value="Add Employee"/></td>
            </tr>
        </table>
    </form:form>
</body>
</html>