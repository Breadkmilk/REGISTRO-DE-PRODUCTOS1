<%@page import="modelo.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/styles.css">

<div class ="container">
    
    <h2>Lista de Productos</h2>
    
    <a href="producto?accion=nuevo">Nuevo Producto</a>
    
    <table>
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Acciones</th>
        </tr>
        
        <%
        List<Producto> lista = (List<Producto>) request.getAttribute("lista");
        for(Producto p : lista){
        %>
<tr>
<td><%=p.getId()%></td>
<td><%=p.getNombre()%></td>
<td><%=p.getPrecio()%></td>
<td>
<a href="producto?accion=editar&id=<%=p.getId()%>">Editar</a>
<a href="producto?accion=eliminar&id=<%=p.getId()%>">Eliminar</a>
</td>
</tr>
<% } %>
</table>

</div>