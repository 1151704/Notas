<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%
    String codigo = request.getParameter("id");
%>
<input type="hidden" class="form-control" name="codigo" required readonly value="<%=codigo%>"/>
<label>Desea eliminar la carrera ?</label>