<%@include file="../includes/imports.jsp" %>
<%
    String codAlumno = request.getParameter("id");
    List<Equivalente> equivalenteListado = controlador.equivalenteListarPorAlumno(codAlumno);
%>
<table class="table table-bordered table-striped">
    <thead>
        <tr>
            <th>Carrera</th>
            <th>Asignatura</th>
            <th>Carrera Matriculada</th>
            <th>Asignatura Matriculado</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <% if (equivalenteListado != null && !equivalenteListado.isEmpty()) {
                for (Equivalente tut : equivalenteListado) {%>
        <tr>
            <td><%=tut.getCarrera().getNombre()%></td>
            <td><%=tut.getAsignatura().getNombre()%></td>
            <td><%=tut.getCarreraMatriculada().getNombre()%></td>
            <td><%=tut.getAsignaturaMatriculada().getNombre()%></td>
            <td>
                <button type="button" class="btn btn-danger" title="Eliminar" data-url="formularios/equivalenteEliminar.jsp"
                        data-toggle="modal" data-target="#modal-equivalenteEliminar" 
                        data-id="<%=codAlumno + ","
                                + tut.getAsignatura().getCodAsignatura() + "," + tut.getCarrera().getCodigo() + ","
                                + tut.getAsignaturaMatriculada().getCodAsignatura() + "," + tut.getCarreraMatriculada().getCodigo()%>">
                    <span class="far fa-trash-alt" aria-hidden="true"></span>
                </button>
            </td>
        </tr>
        <%}
        } else {%>
        <tr> 
            <td colspan="5" align="center" >Ninguna equivalente registrada</td>
        </tr>
        <% }%>
    </tbody>
</table>
