<%@page import="ufps.modelo.dto.Nota"%>
<%@page import="ufps.modelo.dto.Tutor"%>
<%@page import="ufps.modelo.dto.Alumno"%>
<%@page import="ufps.modelo.dto.Requisito"%>
<%@page import="ufps.modelo.dto.Asignatura"%>
<%@page import="java.util.List"%>
<%@page import="ufps.modelo.dto.Carrera"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%
    String datos[] = request.getParameter("id").split(",");

    String alumno = datos[0];
    String asignatura = datos[1];
    String carrera = datos[2];
    String _nota = datos[3];

    double nota = 0;

    if (datos != null) {
        try {
            nota = Double.parseDouble(_nota);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>
<input type="hidden" class="form-control" name="asignatura" required readonly value="<%=asignatura%>"/>
<input type="hidden" class="form-control" name="carrera" required readonly value="<%=carrera%>"/>
<input type="hidden" class="form-control" name="alumno" required readonly value="<%=alumno%>"/>
<div class="form-group">
    <label>Nota</label>
    <input type="text" class="form-control"  name="nota" value="<%=nota%>" />
</div>