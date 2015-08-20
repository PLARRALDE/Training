<%--
  Created by IntelliJ IDEA.
  User: plarralde
  Date: 19/8/15
  Time: 12:18
--%>

<%@ page import="racetrack.SearchResult" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <asset:stylesheet src="bootstrap.css"/>
    <title>Searched Items</title>
</head>

<body>

<h1>Searched Items</h1>
<h3>Cantidad de registros: ${cant}</h3>
<g:paginate total="${cant}" next="Next" prev="Prev"
            controller="busqueda"
            action="list"
/>
<table>
    <g:each var="it" in="${list}">
        <tr><td>${it.site_id}</td><td>${it.title}</td><td><img src="${it.image}"/></td></tr>
    </g:each>


</table>

</body>
</html>
