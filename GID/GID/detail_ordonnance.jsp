<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, gid_metier.*" errorPage="" %>

<%@ include file="header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Détail de l'ordonnance</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<div class="centre">
<blockquote>
<div class="nom"><b><%=ordon.getLibelle()%></b></div>
<div class="etat">
	Etat d'avancement : <br>
<%
	int etat = ordon.getEtat();
	Vector tabEtats = new Vector();
	tabEtats.addElement("1) Saisie par l'ordonnateur");
	tabEtats.addElement("2) Visa du CCED");
	tabEtats.addElement("3) Visa de la TG");
	tabEtats.addElement("4) Procedure terminee");
	switch(etat)
	{
		case 1 : tabEtats.removeElementAt(0); tabEtats.insertElementAt("<span class=\"etatSelect\">1) Saisie par l'ordonnateur</span>",0); break;
		case 2 : tabEtats.removeElementAt(1); tabEtats.insertElementAt("<span class=\"etatSelect\">2) Visa du CCED</span>",1);break;
		case 3 : tabEtats.removeElementAt(2); tabEtats.insertElementAt("<span class=\"etatSelect\">3) Visa de la TG</span>",2);break;
		case 4 : tabEtats.removeElementAt(3); tabEtats.insertElementAt("<span class=\"etatSelect\">4) Procedure terminee</span>",3);break;
	}
	for(int i=0; i <tabEtats.size();i++)
	{
		out.print((String)tabEtats.elementAt(i)+ "<br>\n");
	}
%>
</div>
<div class="message">
Vous avez un message GID : 
</div>
<% 
	if(titre.equalsIgnoreCase("Ordonnances de delegation a traiter"))
	{
%>
 		<%@ include file="messageGID.jsp" %>
<%
	}
	else if (titre.equalsIgnoreCase("Ordonnances de delegation en cours de traitement"))
	{
%>

<%
	}
	else
	{
%>
		La procédure est terminée.
<%
	}
%>
</blockquote>
</div>

</body>
</html>
