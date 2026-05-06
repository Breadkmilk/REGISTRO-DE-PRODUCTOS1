<%@page import="modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/form.css">

<%
Producto p = (Producto) request.getAttribute("p");
%>

<div class="container">

<h2>Registro de Producto</h2>

<form action="producto" method="post">

<input type="hidden" name="id" value="<%= (p!=null)?p.getId():"" %>">

<input type="text" name="nombre" placeholder="Nombre"
value="<%= (p!=null)?p.getNombre():"" %>" required>

<input type="number" step="0.01" name="precio" placeholder="Precio"
value="<%= (p!=null)?p.getPrecio():"" %>" required>

<button>Guardar</button>

</form>

<a href="producto" class="volver">Volver</a>

</div>