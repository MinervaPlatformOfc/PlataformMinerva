<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.dto.TeacherHomeDTO" %>
<%@ page import="com.example.minerva.dto.StudentGradeDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página Professor - Minerva</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/teachers/style.css">

    <script src="${pageContext.request.contextPath}/js/searchDivs.js"></script>
</head>

<%
    TeacherHomeDTO teacherDTO = (TeacherHomeDTO) request.getAttribute("teacherHomeDto");
    String teacherHouseNameRaw = teacherDTO.getTeacherHouseName() != null ? teacherDTO.getTeacherHouseName() : "";

String teacherHouseName =
teacherHouseNameRaw.equals("Grifinória") ? "grifinoria" :
teacherHouseNameRaw.equals("Lufa-Lufa") ? "lufalufa" :
teacherHouseNameRaw.equals("Sonserina") ? "sonserina" :
"corvinal";%>
<body class="<%= teacherHouseName %>">

<%

    if (teacherDTO == null) {
        System.out.println("teacherHomeDto está null");
        return;
    }


%>

<header>

    <div class="nav-links">

        <form action="${pageContext.request.contextPath}/professor/home" method="post">
            <input type="hidden" name="teacherId" value="<%=teacherDTO.getTeacherId()%>">
            <button type="submit" class="nav-button">Início</button>
        </form>

        <form action="${pageContext.request.contextPath}/teacher/yearsSubjects" method="post">
            <input type="hidden" name="teacherName" value="<%=teacherDTO.getTeacherName()%>">
            <input type="hidden" name="teacherId" value="<%=teacherDTO.getTeacherId()%>">
            <input type="hidden" name="houseName" value="<%=teacherHouseName%>">
            <button type="submit" class="nav-button">Observações</button>
        </form>

        <form action="${pageContext.request.contextPath}/teacher/yearsSubjects" method="post">
            <input type="hidden" name="showGrades" value="true">
            <input type="hidden" name="teacherName" value="<%=teacherDTO.getTeacherName()%>">
            <input type="hidden" name="teacherId" value="<%=teacherDTO.getTeacherId()%>">
            <input type="hidden" name="houseName" value="<%=teacherHouseName%>">
            <button type="submit" class="nav-button">Notas</button>
        </form>

    </div>

    <img src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png"
         alt="logo-Minerva"
         class="logo-central">

</header>

<main>

    <h1>Bem-vindo a plataforma Minerva!</h1>
    <h2>Professor <%= teacherDTO.getTeacherName() %></h2>

    <div id="buscar-alunos">
        <div class="scroll-area">

            <h1>Buscar Alunos</h1>

            <input type="text" id="searchbar" oninput="search()" placeholder="Digite o nome a ser pesquisado">
            <input type="hidden" id="searchColumns" value="nome,houseName,pointsLost,pointsGained">

            <%

                List<StudentGradeDTO> students = teacherDTO.getStudents();

                if (students != null && !students.isEmpty()) {

                    for (StudentGradeDTO student : students) {

                        String studentHouseNameRaw =
                                student.getStudentHouseName() != null ? student.getStudentHouseName() : "";

                        String studentHouseNameClass =
                                studentHouseNameRaw.equals("Grifinória") ? "grifinoria" :
                                        studentHouseNameRaw.equals("Lufa-Lufa") ? "lufalufa" :
                                                studentHouseNameRaw.equals("Sonserina") ? "sonserina" :
                                                        "corvinal";

                        Double n1 = student.getN1();
                        Double n2 = student.getN2();

                        String n1Display = (n1 != null && n1 != 0) ? String.valueOf(n1.intValue()) : "-";
                        String n2Display = (n2 != null && n2 != 0) ? String.valueOf(n2.intValue()) : "-";

                        String n1Original = (n1 != null && n1 != 0) ? String.valueOf(n1.intValue()) : "0";
                        String n2Original = (n2 != null && n2 != 0) ? String.valueOf(n2.intValue()) : "0";

            %>

            <div class="barra <%= studentHouseNameClass %> linhas">

                <img src="${pageContext.request.contextPath}/assets/<%=studentHouseNameClass%>.png"
                     alt="<%= studentHouseNameRaw %>"
                     data-field="houseImage">

                <form method="post"
                      action="${pageContext.request.contextPath}/teacher/studentProfile"
                      style="display:flex;width:100%;justify-content:space-between;align-items:center;">

                    <input type="hidden" name="teacherId" value="<%= teacherDTO.getTeacherId() %>">
                    <input type="hidden" name="studentId" value="<%= student.getStudentId() %>">
                    <input type="hidden" name="houseName" value="<%= studentHouseNameClass %>">

                    <button type="submit"
                            style="background:none;border:none;width:100%;display:flex;justify-content:space-between;align-items:center;cursor:pointer;color:inherit;font-family:'Almendra',serif;">

<span data-field="nome" data-original="<%= student.getStudentName() %>">
<%= student.getStudentName() %>
</span>

                        <div style="display:flex;gap:20px;">

<span data-field="materia"
      data-original="<%= student.getSubjectName() %>"
      style="display:none;">
<%= student.getSubjectName() %>
</span>

                            <span data-field="houseName"
                                  data-original="<%= studentHouseNameRaw %>"
                                  style="display:none;">
<%= studentHouseNameRaw %>
</span>

                            <span data-field="pointsLost"
                                  style="color:red;"
                                  data-original="<%= n1Original %>">
<%= n1Display %>
</span>

                            <span data-field="pointsGained"
                                  style="color:green;"
                                  data-original="<%= n2Original %>">
<%= n2Display %>
</span>

                        </div>

                    </button>
                </form>
            </div>

            <%
                }
            } else {
            %>

            <div style="text-align:center;color:red;padding:20px;" class="linhas">
<span data-field="nome" data-original="Nenhum aluno encontrado">
Nenhum aluno encontrado para este professor.
</span>
            </div>

            <%
                }
            %>

        </div>
    </div>

</main>

</body>
</html>