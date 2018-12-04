<%@include file="../includes/imports.jsp" %>
<%
    String codigo = request.getParameter("id");
%>
<input type="hidden" class="form-control" name="codigo" required readonly value="<%=codigo%>"/>
<label>Desea eliminar el tutor ?</label>