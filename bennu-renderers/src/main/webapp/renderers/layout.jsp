<!Doctype html>

<%@page import="pt.ist.bennu.renderers.actions.DefaultContext"%>
<%
	DefaultContext context = (DefaultContext) request.getAttribute("_CONTEXT_");
%>

<html>
	<head>
	</head>
	<body>
		<jsp:include page="<%=context.getBody()%>" />
	</body>
</html>