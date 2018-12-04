<%@include file="../includes/imports.jsp" %>
<%
    String datos[] = request.getParameter("id").split(",");

    String alumno = datos[0];
    String asignatura = datos[1];
    String carrera = datos[2];
    String asignaturaM = datos[3];
    String carreraM = datos[4];

%>
<input type="hidden" class="form-control" name="asignatura" required readonly value="<%=asignatura%>"/>
<input type="hidden" class="form-control" name="asignaturaM" required readonly value="<%=asignaturaM%>"/>
<input type="hidden" class="form-control" name="carrera" required readonly value="<%=carrera%>"/>
<input type="hidden" class="form-control" name="carreraM" required readonly value="<%=carreraM%>"/>
<input type="hidden" class="form-control" name="alumno" required readonly value="<%=alumno%>"/>
<label>Desea eliminar el equivalente ?</label>