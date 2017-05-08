<%-- 
    Document   : listadoBusquedas
    Created on : 19/04/2017, 09:56:51
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="Busqueda.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Búsquedas</title>
    </head>
    <body>
        <h1>Listado de Búsquedas</h1>
        <c:forEach items="${listaBusquedas}" var="b">
            Búsqueda: <c:out value="${b.busqueda}"></c:out>
             - Fecha: <c:out value="${b.fecha}"></c:out><br>
        </c:forEach>
            <a href="index.html">Atrás<a>
    </body>    
</html>
