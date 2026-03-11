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
    <style>
        .logo {
            width: 190px;
            margin-bottom: -35px;
            justify-content: center;
        }
    </style>
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

    <img class="logo" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png" alt="IMAGEM JOAOOOO">

    <form action="${pageContext.request.contextPath}/teacher/profile" method="post" id="perfil">
        <input type="hidden" name="teacherId" value="<%=teacherDTO.getTeacherId()%>">
        <input type="hidden" name="houseName" value="<%=teacherDTO.getTeacherHouseName()%>">
        <button type="submit" style="background: none; border: none; color: white; font-family: 'Almendra', serif; font-size: 1rem; cursor: pointer; display: flex; align-items: center; gap: 5px;">
            <%=teacherDTO.getTeacherName()%>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -960 960 960" fill="#FFFFFF" width="24" height="24">
                <path d="M367-527q-47-47-47-113t47-113q47-47 113-47t113 47q47 47 47 113t-47 113q-47 47-113 47t-113-47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Zm80-80h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm296.5-343.5Q560-607 560-640t-23.5-56.5Q513-720 480-720t-56.5 23.5Q400-673 400-640t23.5 56.5Q447-560 480-560t56.5-23.5ZM480-640Zm0 400Z"/>
            </svg>
        </button>
    </form>
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