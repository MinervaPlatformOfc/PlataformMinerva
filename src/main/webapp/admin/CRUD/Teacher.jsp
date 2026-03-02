<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>CRUD - Teachers</title>
</head>
<body>
<header>
    <%String name = (String) request.getAttribute("name");%>
    <%String url = (String) request.getAttribute("url");%>
    <form action="${pageContext.request.contextPath}/admin/users" method="post">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="url" value="<%=url%>">
        <button type="submit">HOME</button>
    </form>

    <form action="${pageContext.request.contextPath}/admin/ViewAdmins" method="get">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="url" value="<%=url%>">
        <button type="submit">CRUD Admins</button>
    </form>

    <form action="${pageContext.request.contextPath}/admin/ViewTeachers" method="get">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="url" value="<%=url%>">
        <button type="submit">CRUD Teachers</button>
    </form>

    <form action="${pageContext.request.contextPath}/admin/ViewSubjects" method="post">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="url" value="<%=url%>">
        <button type="submit">CRUD Subjects</button>
    </form>

    <form action="${pageContext.request.contextPath}/admin/ViewStudents" method="get">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="url" value="<%=url%>">
        <button type="submit">CRUD Subjects</button>
    </form>

    <form action="${pageContext.request.contextPath}/recharge" method="post">
        <input type="hidden" name="endpoint" value="/admin/ViewTeachers">
        <button type="submit">Atualizar dados</button>
    </form>

    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">Sair</button>
    </form>
</header>
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
