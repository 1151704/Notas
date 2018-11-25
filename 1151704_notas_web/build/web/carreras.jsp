<%@page import="ufps.modelo.dto.Carrera"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="ufps.modelo.util.Servicio"/>
<%
    List<Carrera> carrerasListado = controlador.carreraListar();
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="includes/head.jsp" %>
        <title>Carreras</title>
    </head>
    <body>
        <%@include file="includes/menu.jsp" %>

        <section class="main-page">
            <div class="ed-container">
                <div class="ed-item">
                    <h3 class="main-titulo">Administración de Carreras</h3>
                </div>
                <div class="ed-item s-mb-1">
                    <button class="btn btn-primary" type="button" data-toggle="modal" 
                            data-target="#modal-carreraRegistrar">Registrar</button>
                </div>
                <div class="ed-item table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Semestres</th>
                                <th>Facultad</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (carrerasListado != null && !carrerasListado.isEmpty()) {
                                    for (Carrera carrera : carrerasListado) {%>
                            <tr>
                                <td><%=carrera.getCodigo()%></td>
                                <td><%=carrera.getNombre()%></td>
                                <td><%=carrera.getSemestres()%></td>
                                <td><%=carrera.getFacultad()%></td>
                                <td>
                                    <button type="button" class="btn btn-warning" title="Editar" aria-label="Editar" data-url="formularios/carreraEditar.jsp"
                                            data-toggle="modal" data-target="#modal-carreraEditar" data-id="<%=carrera.getCodigo()%>">
                                        <span class="far fa-edit" aria-hidden="true"></span>
                                    </button>
                                    <button type="button" class="btn btn-danger" title="Eliminar" aria-label="Eliminar" data-url="formularios/carreraEliminar.jsp"
                                            data-toggle="modal" data-target="#modal-carreraEliminar" data-id="<%=carrera.getCodigo()%>">
                                        <span class="far fa-trash-alt" aria-hidden="true"></span>
                                    </button>
                                </td>
                            </tr>
                            <%}
                            } else {%>
                            <tr> 
                                <td colspan="5" align="center" >Ninguna carrera registrada</td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <div class="modal fade" id="modal-carreraRegistrar" role="dialog">
            <form action="registrarCarrera" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Registrar Carrera</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Codigo</label>
                                <input type="text" class="form-control" maxlength="3" name="codigo" required />
                            </div>
                            <div class="form-group">
                                <label>Nombre</label>
                                <input type="text" class="form-control" name="nombre" required />
                            </div>
                            <div class="form-group">
                                <label>Semestres</label>
                                <input type="number" class="form-control" min="1" name="semestres" />
                            </div>
                            <div class="form-group">
                                <label>Facultad</label>
                                <input type="text" class="form-control" maxlength="1" name="facultad" />
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
        <div class="modal fade" id="modal-carreraEditar" role="dialog">
            <form action="editarCarrera" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Editar Carrera</h4>
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
        <div class="modal fade" id="modal-carreraEliminar" role="dialog">
            <form action="eliminarCarrera" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Eliminar Carrera</h4>
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
