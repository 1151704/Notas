<%@page import="ufps.modelo.dto.Tutor"%>
<%@page import="ufps.modelo.dto.Alumno"%>
<%@page import="ufps.modelo.dto.Requisito"%>
<%@page import="ufps.modelo.dto.Asignatura"%>
<%@page import="java.util.List"%>
<%@page import="ufps.modelo.dto.Carrera"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%
    String codigo = request.getParameter("id");
    Carrera carrera = null;
    Alumno alumno = null;
    List<Carrera> carrerasListado = controlador.carreraListar();
    List<Alumno> alumnoListado = controlador.alumnoListar();

    if (codigo != null && !codigo.isEmpty()) {
        Tutor tutor = controlador.tutorDatos(codigo);

        if (tutor != null) {
            carrera = tutor.getCarrera();
            alumno = tutor.getAlumno();
        }
    } else {
        codigo = "No existe el tutor";
    }
%>
<div class="form-group">
    <label>Codigo</label>
    <input type="text" class="form-control" maxlength="4" name="codigo" readonly value="<%=codigo%>" />
</div>
<% if (carrerasListado != null && !carrerasListado.isEmpty()) { %>
<div class="form-group">
    <label>Carrera</label>
    <select class="form-control" name="carrera">
        <% for (Carrera car : carrerasListado) {%>
        <option value="<%=car.getCodigo()%>" <%=carrera != null && car.equals(carrera) ? "selected" : ""%> ><%=car.getNombre()%></option>
        <% } %>
    </select>
</div>
<% } else {%>
<div class="alert alert-warning" role="alert">
    No existen carreras registradas
</div>
<% }%>
<% if (alumnoListado != null && !alumnoListado.isEmpty()) { %>
<div class="form-group">
    <label>Alumno</label>
    <select class="form-control" name="alumno">
        <% for (Alumno asig : alumnoListado) {%>
        <option value="<%=asig.getCodAlumno()%>" <%=alumno != null && asig.equals(alumno) ? "selected" : ""%> ><%=asig.getNombre()%></option>
        <% } %>
    </select>
</div>
<% } else {%>
<div class="alert alert-warning" role="alert">
    No existen alumnos registrados
</div>
<% }%>