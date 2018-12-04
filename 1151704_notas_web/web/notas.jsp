<%@include file="includes/imports.jsp" %>
<%
    List<Alumno> alumnosListado = controlador.alumnoListar();
    List<Carrera> carrerasListado = controlador.carreraListar();
    List<Asignatura> asignaturasListado = controlador.asignaturaListar();
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="includes/head.jsp" %>
        <title>Notas</title>
    </head>
    <body>
        <%@include file="includes/menu.jsp" %>

        <section class="main-page">
            <div class="ed-container">
                <div class="ed-item">
                    <h3 class="main-titulo">Administración de Notas</h3>
                </div>
                <div class="ed-item s-mb-1">
                    <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#modal-notaRegistrar">Registrar</button>
                </div>
                <div class="ed-item s-mb-1">
                    <div class="form-group">
                        <label>Alumno</label>
                        <select class="form-control" id="selectListadoFiltro" 
                                data-url="listados/notasListado.jsp">
                            <option value="null">Seleccione un alumno</option>
                            <% for (Alumno alumno : alumnosListado) {%>
                            <option value="<%=alumno.getCodAlumno()%>"><%=alumno.getNombre() + " " + alumno.getApellido()%></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="ed-item table-responsive" id="cajaListadoFiltro"></div>
            </div>
        </section>
        <div class="modal fade" id="modal-notaRegistrar" role="dialog">
            <form action="registrarNota" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Registrar Nota</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <% if (carrerasListado != null && !carrerasListado.isEmpty()) { %>
                            <div class="form-group">
                                <label>Carrera</label>
                                <select class="form-control" name="carrera">
                                    <% for (Carrera car : carrerasListado) {%>
                                    <option value="<%=car.getCodigo()%>"><%=car.getNombre()%></option>
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
                                    <option value="<%=asig.getCodAsignatura()%>"><%=asig.getNombre()%></option>
                                    <% } %>
                                </select>
                            </div>
                            <% } else {%>
                            <div class="alert alert-warning" role="alert">
                                No existen asignaturas registradas
                            </div>
                            <% }%>
                            <% if (alumnosListado != null && !alumnosListado.isEmpty()) { %>
                            <div class="form-group">
                                <label>Alumno</label>
                                <select class="form-control" name="alumno">
                                    <% for (Alumno asig : alumnosListado) {%>
                                    <option value="<%=asig.getCodAlumno()%>"><%=asig.getNombre()%></option>
                                    <% } %>
                                </select>
                            </div>
                            <% } else {%>
                            <div class="alert alert-warning" role="alert">
                                No existen alumnos registrados
                            </div>
                            <% }%>
                            <div class="form-group">
                                <label>Nota</label>
                                <input type="text" class="form-control" name="nota" value="0.0" />
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-success">Registrar</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal fade" id="modal-notaEditar" role="dialog">
            <form action="editarNota" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Editar Nota</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-success">Guardar</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal fade" id="modal-notaEliminar" role="dialog">
            <form action="eliminarNota" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Eliminar Nota</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-success">Eliminar</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>
