<%--
  Created by IntelliJ IDEA.
  User: plarralde
  Date: 18/8/15
  Time: 10:38
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html >
<head>
    <title>Búsqueda en MELI</title>
    <asset:stylesheet src="bootstrap.min.css"/>
</head>

<body ng-controller="testController">
    <asset:javascript src="angular.min.js"/>
    <asset:javascript src="app.js"/>

    <h2>Búsqueda de items</h2>
    <h3>Ingrese búsqueda</h3>

    <a href="${g.resource(file:'angularTest.html')}">My Link</a>

    <g:form action="meli_new_search" method="POST">
        <table>
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <label>Búsqueda:</label>
                </td>
                <td valign="top">
                    <input type="text" name="search" params="[search:${search}]" /><br/>
                </td>
                <td>
                    <g:select id="site" name="site" params='"[site:${value}]"'
                              from="${['MLA': 'Argentina', 'MLB': 'Brasil', 'MCO': 'Colombia',
                                       'MCR': 'Costa Rica', 'MEC': 'Ecuador', 'MLC': 'Chile',
                                       'MLM': 'Mexico','MLU': 'Uruguay','MLV': 'Venezuela',
                                       'MPA': 'Panamá','MPE': 'Perú','MPT': 'Portugal',
                                       'MRD': 'Dominicana','MBO': 'Bolivia']}"
                              optionKey="key" optionValue="value" />
                </td>
            </tr>
            </tbody>
        </table>
        <br/>
        <g:submitButton name="boton" class="save" value="Buscar en MELI" />
    </g:form>
</body>
</html>