<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<jsp:useBean id="Action" class="gid_metier.Action" scope="session" />
<jsp:useBean id="CCED" class="gid_metier.CCED" scope="session" />
<jsp:useBean id="Chapitre" class="gid_metier.Chapitre" scope="session" />
<jsp:useBean id="Comptabilite" class="gid_metier.Comptabilite" scope="session" />
<jsp:useBean id="Consommable" class="gid_metier.Consommable" scope="session" />
<jsp:useBean id="CPED" class="gid_metier.CPED" scope="session" />
<jsp:useBean id="Ligne_budgetaire" class="gid_metier.LigneBudgetaire" scope="session" />
<jsp:useBean id="Operation" class="gid_metier.Operation" scope="session" />
<jsp:useBean id="Ordonnance" class="gid_metier.OrdonnanceDelegation" scope="session" />
<jsp:useBean id="Ordonnateur" class="gid_metier.Ordonnateur" scope="session" />
<jsp:useBean id="SousOrdonnateur" class="gid_metier.SousOrdonnateur" scope="session" />
<jsp:useBean id="TG" class="gid_metier.TG" scope="session" />
<jsp:useBean id="TR_TP" class="gid_metier.TR_TP" scope="session" />


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Document sans titre</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<% 
String id = new String(request.getParameter("identifiant"));
String pwd = new String(request.getParameter("password"));
String sql = new String("SELECT * FROM ACTEUR");// WHERE Login = " + id + " AND MDP = " + pwd);
RequeteSql.setRequete(sql);
ResultSet rs=null;
rs = RequeteSql.executeRequete();
out.print(rs);
/*while (rs.next()) {
				out.println(rs.getString(1) + "      ");
				out.println(rs.getString(2) + "<br/>");
			}*/
out.print(sql);
%>
</body>
</html>
