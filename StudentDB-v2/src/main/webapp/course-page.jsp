<%@ page import="controller.StudentServlet" %>
<%@ page import="model.Student" %>
<%@ page import="controller.CourseServlet" %>
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
  <title>   Course Page   </title>
</head>
<div align="center" style="margin-top: 50%">
  <% Student currentStudent = (Student) (session.getAttribute("currentStudent"));%>
  <%= " Here are the class grades for " + request.getParameter("courseID") %>
  <table  align="center" class="table">

    <%= new CourseServlet().showTable(request, response) %>
  </table>
</div>
</body>

</html>
