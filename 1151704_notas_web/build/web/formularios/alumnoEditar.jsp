<%@page import="ufps.modelo.dto.Alumno"%>
<%@page import="ufps.modelo.dto.Asignatura"%>
<%@page import="java.util.List"%>
<%@page import="ufps.modelo.dto.Carrera"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%
    String codigo = request.getParameter("id");
    String nombre = "";
    String apellido = "";
    String direccion = "";
    String telefono = "";
    String email = "";
    Carrera carrera = null;
    List<Carrera> carrerasListado = controlador.carreraListar();

    if (codigo != null && !codigo.isEmpty()) {
        Alumno alumno = controlador.alumnoDatos(codigo);

        if (alumno != null) {
            nombre = alumno.getNombre();
            apellido = alumno.getApellido();
            direccion = alumno.getDireccion();
            telefono = alumno.getTelefono();
            email = alumno.getEmail();
        }
    } else {
        codigo = "No existe el alumno";
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
<div class="form-group">
    <label>Nombre</label>
    <input type="text" class="form-control" name="nombre" required value="<%=nombre%>" />
</div>
<div class="form-group">
    <label>Apellido</label>
    <input type="text" class="form-control" name="apellido" required value="<%=apellido%>" />
</div>
<div class="form-group">
    <label>Dirección</label>
    <input type="text" class="form-control" name="direccion"  value="<%=direccion%>" />
</div>
<div class="form-group">
    <label>Telefono</label>
    <input type="text" class="form-control" name="telefono" value="<%=telefono%>"  />
</div>
<div class="form-group">
    <label>Email</label>
    <input type="email" class="form-control" name="email"  value="<%=email%>" />
</div>