<%@include file="../includes/imports.jsp" %>
<%
    String codigo = request.getParameter("id");
    String nombre = "";
    int creditos = 0;
    Carrera carrera = null;
    Asignatura asignatura = null;
    List<Carrera> carrerasListado = controlador.carreraListar();
    List<Asignatura> asignaturasListado = controlador.asignaturaListar();

    if (codigo != null && !codigo.isEmpty()) {
        Requisito requisito = controlador.requisitoDatos(codigo);

        if (requisito != null) {
            carrera = requisito.getCarrera();
            asignatura = requisito.getAsignatura();
        }
    } else {
        codigo = "No existe el requisito";
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
<% if (asignaturasListado != null && !asignaturasListado.isEmpty()) { %>
<div class="form-group">
    <label>Asignatura</label>
    <select class="form-control" name="asignatura">
        <% for (Asignatura asig : asignaturasListado) {%>
        <option value="<%=asig.getCodAsignatura()%>" <%=asignatura != null && asig.equals(asignatura) ? "selected" : ""%> ><%=asig.getNombre()%></option>
        <% } %>
    </select>
</div>
<% } else {%>
<div class="alert alert-warning" role="alert">
    No existen asignaturas registradas
</div>
<% }%>