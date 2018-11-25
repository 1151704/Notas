<%@page import="ufps.modelo.dto.Nota"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%

    String codAlumno = request.getParameter("id");

    List<Nota> notasListado = controlador.notaListarPorAlumno(codAlumno);

%>
<table class="table table-bordered table-striped">
    <thead>
        <tr>
            <th>Carrera</th>
            <th>Asignatura</th>
            <th>Nota</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <% if (notasListado != null && !notasListado.isEmpty()) {
                for (Nota tut : notasListado) {%>
        <tr>
            <td><%=tut.getCarrera().getNombre()%></td>
            <td><%=tut.getAsignatura().getNombre()%></td>
            <td><%=tut.getNota()%></td>
            <td>
                <button type="button" class="btn btn-warning" title="Editar" data-url="formularios/notaEditar.jsp"
                        data-toggle="modal" data-target="#modal-notaEditar" data-id="<%=codAlumno + "," + tut.getAsignatura().getCodAsignatura() + "," + tut.getCarrera().getCodigo() + "," + tut.getNota()%>">
                    <span class="far fa-edit" aria-hidden="true"></span>
                </button>
                <button type="button" class="btn btn-danger" title="Eliminar" data-url="formularios/notaEliminar.jsp"
                        data-toggle="modal" data-target="#modal-notaEliminar" data-id="<%=codAlumno + "," + tut.getAsignatura().getCodAsignatura() + "," + tut.getCarrera().getCodigo() + "," + tut.getNota()%>">
                    <span class="far fa-trash-alt" aria-hidden="true"></span>
                </button>
            </td>
        </tr>
        <%}
        } else {%>
        <tr> 
            <td colspan="5" align="center" >Ninguna nota registrada</td>
        </tr>
        <% }%>
    </tbody>
</table>
