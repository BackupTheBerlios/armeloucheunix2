<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, gid_metier.*" errorPage="" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Document sans titre</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<%@ include file="header.jsp" %>
<div class="centre">
<blockquote>
<%
java.util.Vector operations=null;
int solde=0;
GregorianCalendar date = new GregorianCalendar();

if (cced.identifie() && cced.getComptaPerso()!=null)
{
	operations = cced.getComptaPerso().getOperations();
	solde = cced.getComptaPerso().getSolde();
}
else if(cped.identifie()&& cped.getComptaPerso()!=null)
{
	operations = cped.getComptaPerso().getOperations();
	solde = cped.getComptaPerso().getSolde();
}
else if(ordonnateur.identifie() && ordonnateur.getComptaPerso()!=null)
{
	operations = ordonnateur.getComptaPerso().getOperations();
	solde = ordonnateur.getComptaPerso().getSolde();
}
else if(sousOrdonnateur.identifie()&& sousOrdonnateur.getComptaPerso()!=null)
{
	operations = sousOrdonnateur.getComptaPerso().getOperations();
	solde = sousOrdonnateur.getComptaPerso().getSolde();
}
else if(tg.identifie()&& tg.getComptaPerso()!=null)
{
	operations = tg.getComptaPerso().getOperations();
	solde = tg.getComptaPerso().getSolde();
}
else if(tr_tp.identifie()&& tr_tp.getComptaPerso()!=null)
{
	operations = tr_tp.getComptaPerso().getOperations();
	solde = tr_tp.getComptaPerso().getSolde();
}
%>

<div class="solde">Solde au  <%=date.get(GregorianCalendar.YEAR) + "/" + (date.get(GregorianCalendar.MONTH)+1) + "/" + date.get(GregorianCalendar.DAY_OF_MONTH)%> : <b><%= solde %> &euro;uros </b></div>
<table class="operation">
	<tr>
		<th>Date Opération</th>
		<th>Libelle</td>
		<th>Débit (Euros)</th>
		<th>Crédit (Euros)</th>
	</tr>
<%
if (operations!=null)
{
	for (int i = 0; i < operations.size(); i++)
	{
		Operation op = (Operation)operations.elementAt(i);
%>
	<tr>
		<td class="date_operation"><%=op.getDate()%></td>
		<td class="libelle"><%=op.getLibelle()%></td>
		<td class="debit"><% if (op.getType().equalsIgnoreCase("d")){out.print(op.getMontant());}%></td>
		<td class="credit"><% if (op.getType().equalsIgnoreCase("c")){out.print(op.getMontant());}%></td>
	</tr>
<%
	}
}
%>
</table>
</div>
</blockquote>
</div>
</body>
</html>
