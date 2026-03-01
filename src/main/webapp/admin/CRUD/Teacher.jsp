<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>CRUD - Teachers</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>Email</th>
            <th>Password</th>
            <th>Name</th>
            <th>House</th>
            <th>Wand</th>
            <th>Past Experiences</th>
            <th>Wizard Title</th>
            <th> Photo </th>
        </tr>
        <c:forEach var="teacher" items="${teacherList}">
            <tr>
                <td>${teacher.getEmail()}</td>
                <td>${teacher.getPassword()}</td>
                <td>${teacher.getName()}</td>
                <td>${teacher.getHouse()}</td>
                <td>${teacher.getWand()}</td>
                <td>${teacher.getPastExperiences()}</td>
                <td>${teacher.getWizardTitle()}</td>
                <td><img src="${teacher.getImageUrl()}"></td>
                <td>
                    <c:forEach var="comment" items="${teacher.comments}">
                        <p style="display: none">${comment.getContent()}</p><br>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
