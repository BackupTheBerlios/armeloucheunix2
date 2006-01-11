<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,gid_metier.*" errorPage="" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<%@ include file="header.jsp" %>
<title>Retrait d'une Ordonnance de délégation de crédit</title>
<div class="centre">
<h2 align="center">Retrait d'une Ordonnance de délégation de crédit</h2>
<blockquote>
<table class="a_traiter">
	<tr>
		<th>Date d'initiation de l'ordonnance</th>
		<th>Libelle de l'ordonnance</th>
		<th>Partenaires</th>
		<th>Consommables</th>
		<th>Montant</th>
	</tr>
<%

if (ordonnateur.getEnCours()!= null)
{
	for (int i=0 ; i<ordonnateur.getEnCours().size(); i++)
	{
		OrdonnanceDelegation ord = (OrdonnanceDelegation)(ordonnateur.getEnCours().elementAt(i));
%>
	<tr>
		<td class="date_ordo"><%= ord.getDate() %></td>
		<td class="libelle"><a href="/GID/GID?action=retrait_ordonnance&encours&place=<%=i %>"><%= ord.getLibelle() %></td></a>
		<td class="partenaires">Ordonnateur : <%= ord.getInitiateur().getNom() + " " + ord.getInitiateur().getPrenom() %><br>Délégataire : <%= ord.getDelegataire().getNom() + " " + ord.getDelegataire().getPrenom() %><br>Comptable assignataire : <%= ord.getComptable().getNom() + " " + ord.getComptable().getPrenom() %></td>
		<td class="consommables">
			<select size="3">
				<%Consommable conso = new Consommable();
				for(int j=0; j<ord.getConsommables().size(); j++)
				{
					conso = (Consommable)(ord.getConsommables()).elementAt(j);
				%>
				<option><%=conso.getLibelle() + " &nbsp " + conso.getQuantite() +" &nbsp " + conso.getPrix()*conso.getQuantite()%></option>
				<%
				}
				%>
			</select>
		</td>
		<td class="montant"><%= ord.getMontant() %></td>
	</tr>
<%
	}
}
if (ordonnateur.getATraiters()!= null)
{
	for (int i=0 ; i<ordonnateur.getATraiters().size(); i++)
	{
		OrdonnanceDelegation ord = (OrdonnanceDelegation)(ordonnateur.getATraiters().elementAt(i));
%>
	<tr>
		<td class="date_ordo"><%= ord.getDate() %></td>
		<td class="libelle"><a href="/GID/GID?action=retrait_ordonnance&a_traiter&place=<%=i%>"><%= ord.getLibelle() %></td></a>
		<td class="partenaires">Ordonnateur : <%= ord.getInitiateur().getNom() + " " + ord.getInitiateur().getPrenom() %><br>Délégataire : <%= ord.getDelegataire().getNom() + " " + ord.getDelegataire().getPrenom() %><br>Comptable assignataire : <%= ord.getComptable().getNom() + " " + ord.getComptable().getPrenom() %></td>
		<td class="consommables">
			<select size="3">
				<%Consommable conso = new Consommable();
				for(int j=0; j<ord.getConsommables().size(); j++)
				{
					conso = (Consommable)(ord.getConsommables()).elementAt(j);
				%>
				<option><%=conso.getLibelle() + " &nbsp " + conso.getQuantite() +" &nbsp " + conso.getPrix()*conso.getQuantite()%></option>
				<%
				}
				%>
			</select>
		</td>
		<td class="montant"><%= ord.getMontant() %></td>
	</tr>
<%
	}
}
%>
</table>
</blockquote>
</div>
</body>
</html>
