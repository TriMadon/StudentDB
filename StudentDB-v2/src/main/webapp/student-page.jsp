<%@ page import="controller.StudentServlet" %>
<%@ page import="model.Student" %>
<%@ page
        contentType="text/html;charset=UTF-8"
         pageEncoding="windows-1256"
%>
<% if (session.getAttribute("currentStudent") == null)
    request.getRequestDispatcher("login.jsp").forward(request, response);
%>

<!DOCTYPE html>

<html lang="en">
<body>
<head>
    <link rel="stylesheet" href="css/bootstrap.css">
    <meta http-equiv="Content-Type"
          content="text/html; charset=windows-1256">
    <title>   Student Page   </title>
</head>
<div align="center" style="margin-top: 40%">
    <% Student currentStudent = (Student) (session.getAttribute("currentStudent"));%>
    Welcome, <%= currentStudent.name() + ", Here are you courses and their grades: "%>
<%--     <a href="course-page.jsp?courseID=<%=CS01%>">fd <a/>--%>

    <table  align="center" class="table">
        <thead>
        <tr>
            <th scope="col"> Course ID </th>
            <th scope="col"> Your grade </th>
        </tr>
        </thead>
        <%= new StudentServlet().showTable(request, response) %>
    </table>
</div>
</body>

</html>
