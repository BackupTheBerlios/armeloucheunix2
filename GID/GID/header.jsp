<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.* , java.util.*" errorPage="" %>
<jsp:useBean id="action" class="gid_metier.Action" scope="session" />
<jsp:useBean id="cced" class="gid_metier.CCED" scope="session" />
<jsp:useBean id="chapitre" class="gid_metier.Chapitre" scope="session" />
<jsp:useBean id="comptabilite" class="gid_metier.Comptabilite" scope="session" />
<jsp:useBean id="consommable" class="gid_metier.Consommable" scope="session" />
<jsp:useBean id="cped" class="gid_metier.CPED" scope="session" />
<jsp:useBean id="ligneBudgetaire" class="gid_metier.LigneBudgetaire" scope="session" />
<jsp:useBean id="operation" class="gid_metier.Operation" scope="session" />
<jsp:useBean id="ordonnateur" class="gid_metier.Ordonnateur" scope="session" />
<jsp:useBean id="sousOrdonnateur" class="gid_metier.SousOrdonnateur" scope="session" />
<jsp:useBean id="tg" class="gid_metier.TG" scope="session" />
<jsp:useBean id="tr_tp" class="gid_metier.TR_TP" scope="session" />
<jsp:useBean id="ordon" class="gid_metier.OrdonnanceDelegation" scope="session" />
<jsp:useBean id="ordonnances" class="Vector" scope="session" />
<jsp:useBean id="titre" class="String" scope="session" />


<link href="/GID/style.css" rel="stylesheet" type="text/css">
<div id="menu">
	<ul>
		<li><a href="/GID/GID?action=ordonnance">Voir les ordonnances a traiter</a></li>
		<li><a href="/GID/GID?action=encours">Voir les ordonnances en cours de traitement</a></li>
		<li><a href="/GID/GID?action=archives">Voir les ordonnances archivées</a></li>
	  	<li><a href="/GID/comptabilite.jsp">Voir la comptabilite</a></li>
	  	
<%if(ordonnateur.identifie()){%>
		<li><a href="saisie_ordonnance.jsp">Saisir une nouvelle ordonnance de délégation</a></li>
		<li><a href="retirer_ordonnance.jsp">Retirer une ordonnance de délégation</a></li>
<%}%>
	</ul>
</div>
<div id="identifier" align="right">
<%	
	if(!cced.identifie() && !cped.identifie() && !ordonnateur.identifie() && !sousOrdonnateur.identifie() && !tg.identifie() && !tr_tp.identifie())
	{
%>
<form action = '/GID/GID' method='post'>
      Identifiant : 
      <input type='text' class="champ" name='identifiant' value="" /> 
      Mot de passe : 
      <input type='password' class="champ" name='password' value="" />
	  <input type="hidden" name="action" value="identification"/>
	  <input type="hidden" name="url" value="<%= request.getServletPath() %>" />
      <input type='submit' class="champ2" value='Envoyer'><br>
</form>
<% 
	}
	else
	{
%>
      <form action='/GID/GID' method= 'post'>
   	  <input type="hidden" name="action" value="deconnexion"/>
      <input type='submit' class="champ2" value='Deconnexion'></input>
      </form>

<%
	}
%>
</div>
