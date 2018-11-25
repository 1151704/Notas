<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%
    String datos[] = request.getParameter("id").split(",");

    String alumno = datos[0];
    String asignatura = datos[1];
    String carrera = datos[2];

%>
<input type="hidden" class="form-control" name="asignatura" required readonly value="<%=asignatura%>"/>
<input type="hidden" class="form-control" name="carrera" required readonly value="<%=carrera%>"/>
<input type="hidden" class="form-control" name="alumno" required readonly value="<%=alumno%>"/>
<label>Desea eliminar la nota ?</label>