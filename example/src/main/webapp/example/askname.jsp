<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr" %>

<fr:edit id="hello" name="hello" action="/hello.do?method=hello">
	<fr:schema bundle="EXAMPLE_RESOURCES" type="pt.ist.bennu.renderers.example.HelloWorldAction$HelloUser">
		<fr:slot name="user" layout="menu-select">
			<fr:property name="from" value="systemUsers"/>
		</fr:slot>
	</fr:schema>
</fr:edit>