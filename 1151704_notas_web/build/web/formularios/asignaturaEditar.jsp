<%@include file="../includes/imports.jsp" %>
<%
    String codigo = request.getParameter("id");
    String nombre = "";
    int creditos = 0;
    Carrera carrera = null;
    List<Carrera> carrerasListado = controlador.carreraListar();

    if (codigo != null && !codigo.isEmpty()) {
        Asignatura asignatura = controlador.asignaturaDatos(codigo);

        if (asignatura != null) {
            nombre = asignatura.getNombre();
            creditos = asignatura.getCreditos();
            carrera = asignatura.getCarrera();
        }
    } else {
        codigo = "No existe la asignatura";
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
    <label>Creditos</label>
    <input type="number" class="form-control" min="1" name="creditos" value="<%=creditos%>" />
</div>