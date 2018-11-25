<%@page import="ufps.modelo.dto.Carrera"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%
    String codigo = request.getParameter("id");
    String nombre = "";
    int semestres = 0;
    String facultad = "";

    if (codigo != null && !codigo.isEmpty()) {
        Carrera carrera = controlador.carreraDatos(codigo);

        if (carrera != null) {
            nombre = carrera.getNombre();
            semestres = carrera.getSemestres();
            facultad = carrera.getFacultad();
        }
    } else {
        codigo = "No existe la carrera";
    }
%>
<div class="form-group">
    <label>Codigo</label>
    <input type="text" class="form-control" name="codigo" required readonly value="<%=codigo%>"/>
</div>
<div class="form-group">
    <label>Nombre</label>
    <input type="text" class="form-control" name="nombre" required value="<%=nombre%>" />
</div>
<div class="form-group">
    <label>Semestres</label>
    <input type="number" class="form-control" min="1" name="semestres" value="<%=semestres%>" />
</div>
<div class="form-group">
    <label>Facultad</label>
    <input type="text" class="form-control" maxlength="1" name="facultad" value="<%=facultad%>" />
</div>