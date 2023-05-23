<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


<html lang="en">
<head>
    <link rel="stylesheet" href="css/bootstrap.css"></link>
    <meta charset="ISO-8859-1">
    <title>Login Page</title>
</head>
<body>
<div style="text-align: center; width: 50%; padding: 10px; margin: 50% auto 50% auto;">
    <h1>Student Login</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <table style="with: 80%; margin: auto">
            <tr>
                <th>Student ID: </th>
                <td><input type="text" name="id" /></td>
            </tr>
            <tr>
                <th>Password: </th>
                <td><input type="password" name="pass" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>