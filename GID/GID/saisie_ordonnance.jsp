<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, gid_metier.*" errorPage="" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Document sans titre</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<script language="javascript">
function change()
{
	document.ordonnance.action="saisie_ordonnance.jsp";
	document.ordonnance.submit();
}

function verif()
{
	montant = document.forms.ordonnance.elements[12].value;
	if (document.forms.ordonnance.elements[2].value=="")
	{	
		alert("Vous avez omis de donner un nom à l'ordonnance");
		return false;
	}
	else if(montant=="")
	{
		alert("Vous avez omis d'indiquer une quantité au matériel sélectionné");
		return false;
	}
	else if(parseInt(montant)!=montant)
	{
		alert("Vous n'avez pas indiqué un nombre entier");
		return false;
	}
	else if(montant<1)
	{
		alert("Vous avez indiqué un nombre trop petit");
		return false;
	}
	else
	{
		return true;
	}
}

function calculTotal()
{
	var numSelection = document.forms.ordonnance.elements[1].value;
	var i=0;
	var total=parseInt(0);
	var index=parseInt(16);
	var quantite=document.forms.ordonnance.elements[index];
	var prixunite;
	for(i; i<numSelection; i++)
	{
		quantite=document.forms.ordonnance.elements[index];
		prixunite=document.forms.prix.eval('mat'+document.forms.ordonnance.elements[index+1].value);
		total += parseInt(quantite.value)*parseInt(prixunite.value);
		index+=3;
	}
	document.forms.ordonnance.elements[13].setAttribute('value', total);
	document.getElementById('total').innerHTML = total;
}

function ajout()
{
	if(verif())
	{
		var ajout=true;
		var j=0;
		var materiel;
		var matos;
		var materiel_a_mettre_a_jour;
		var materiel_hidden;
		var k;
		var toto;
		var numOrdo;
		if (document.forms.ordonnance.sel.options.length!=0)
		{
			numOrdo = document.forms.ordonnance.sel.options.length;
			while(j<numOrdo)
			{
				if(document.forms.ordonnance.sel.options[j].value==document.forms.ordonnance.materiel.options[document.forms.ordonnance.materiel.selectedIndex].value)
				{
					ajout=false;
					materiel = document.forms.ordonnance.sel.options[j];
					matos = document.forms.ordonnance.materiel.options[document.forms.ordonnance.materiel.selectedIndex].text;
					k=15;
					toto= new Array();
					toto = document.forms.ordonnance.elements;
					while(k < toto.length)
					{
						if(toto[k].value==document.forms.ordonnance.sel.options[j].value)
						{
							materiel_a_mettre_a_jour = document.forms.ordonnance.elements[k-1];
							materiel_hidden = document.forms.ordonnance.elements[k+1];
						}
						k++;
					}
				}
				j++;
			}
		}
		if(ajout==true)
		{
			document.forms.ordonnance.sel.options[document.forms.ordonnance.sel.options.length] = new Option(document.forms.ordonnance.quantite.value + ' ' + document.forms.ordonnance.materiel.options[document.forms.ordonnance.materiel.options.selectedIndex].text,document.forms.ordonnance.materiel.options[document.forms.ordonnance.materiel.options.selectedIndex].value); 
			var newElem = document.createElement('input');
			newElem.setAttribute('type', 'hidden');
			newElem.setAttribute('name', 'sel[' + document.forms.ordonnance.sel.options.length + ']');
			newElem.setAttribute('value', document.forms.ordonnance.materiel.options[document.forms.ordonnance.materiel.options.selectedIndex].value);
			
			var newElemq = document.createElement('input');
			newElemq.setAttribute('type', 'hidden');
			newElemq.setAttribute('name', 'selq[' + document.forms.ordonnance.sel.options.length + ']');
			newElemq.setAttribute('value', document.forms.ordonnance.elements[12].value);
			
			var newElemn = document.createElement('input');
			newElemn.setAttribute('type', 'hidden');
			newElemn.setAttribute('name', 'seln[' + document.forms.ordonnance.sel.options.length + ']');
			newElemn.setAttribute('value', document.forms.ordonnance.quantite.value + ' ' + document.forms.ordonnance.materiel.options[document.forms.ordonnance.materiel.options.selectedIndex].text);
			
			document.forms.ordonnance.appendChild(newElemq);
			document.forms.ordonnance.appendChild(newElem);
			document.forms.ordonnance.appendChild(newElemn);
			document.forms.ordonnance.elements[1].value++;
			
			calculTotal();
		}
		else
		{	
			if(confirm("Voulez vous mettre à jour la quantité allouée au matériel " + materiel.text))
			{
				materiel_a_mettre_a_jour.value=document.forms.ordonnance.elements[12].value;
				materiel.text = document.forms.ordonnance.quantite.value + ' ' + matos;
				materiel_hidden.value= document.forms.ordonnance.quantite.value + ' ' + matos;
				calculTotal();
			}
		}
	}
}
</script>
<%@ include file="header.jsp" %>
<% 
int nombreOption;
String nomOrdonnance="";
if (request.getParameter("numOption")!=null)
{
	nombreOption = new Integer(request.getParameter("numOption")).intValue();
}
else
{
	nombreOption =0;
}

if (request.getParameter("nom_ordonnance")!=null)
{
	nomOrdonnance = request.getParameter("nom_ordonnance");
}

int so_select=-1;
if (request.getParameter("sous_ordonnateur")!=null)
{
	so_select =new Integer(request.getParameter("sous_ordonnateur")).intValue();
}
Vector sousO = sousOrdonnateur.retournerTous();

int ced_select=-1;
if (request.getParameter("ced")!=null)
{
	ced_select =new Integer(request.getParameter("ced")).intValue();
}
Vector ced = cced.retournerTous();

int tg_select=-1;
if (request.getParameter("tg")!=null)
{
	tg_select =new Integer(request.getParameter("tg")).intValue();
}
Vector tresG = tg.retournerTous();

int id=-1;
if (request.getParameter("lb")!=null)
{
	id=new Integer(request.getParameter("lb")).intValue();
}
Vector ligneBudgetaire = ligne_budgetaire.retournerTous();

LigneBudgetaire lbc = new LigneBudgetaire();
Vector chapitres;

Vector cons = new Vector();
cons = consommable.retournerTous();
Vector table = new Vector();

String hidden="";

String total = "";
if(request.getParameter("montant")!=null)
{
	total = request.getParameter("montant");
}
else
{
	total="0";
}
%>

	<div class="centre">
		<blockquote>
			<form action="/GID/GID" method="post" name="ordonnance">
				<input type="hidden" name="action" value="nouvelle_ordonnance">
				<input type="hidden" name="numOption" value="<%=nombreOption%>">
Nom de l'ordonnance : <input type="text" maxlength="30" name="nom_ordonnance" value="<%= nomOrdonnance %>"><br/>
			<FIELDSET class="field_part">
				<LEGEND align="left">
   					 Partenaires 
  				</LEGEND>
				<div class="partenaire">
					<table>
						<tr>
							<th>Sous Ordonnateur : </th>
							<td>
								<select name="sous_ordonnateur">
								<%
									for(int i=0; i < sousO.size(); i++)
									{
										SousOrdonnateur s = (SousOrdonnateur)sousO.elementAt(i);
										if (so_select ==s.getId())
										{
										%>
									<option value="<%= s.getId()%>" selected><%=s.getNom() + s.getPrenom()%></option>
										<%
										}
										else
										{
										%>
									<option value="<%= s.getId()%>"><%=s.getNom() + s.getPrenom()%></option>
										<%
										}
									}
								%>
								</select>
							</td>
						</tr>
						<tr>
							<th>CED :</th>
							<td>
								<select name="ced">
								<%
									for(int i=0; i < ced.size(); i++)
									{
										CCED c = (CCED)ced.elementAt(i);
										if (ced_select ==c.getId())
										{
										%>
									<option value="<%= c.getId()%>" selected><%=c.getNom() + c.getPrenom()%></option>
										<%	
										}
										else
										{
										%>
									<option value="<%= c.getId()%>"><%=c.getNom() + c.getPrenom()%></option>
										<%
										}
									}
								%>
								</select>
							</td>
						</tr>
						<tr>
							<th>TG :</th>
							<td>
								<select name="tg">
								<%
									for(int i=0; i < tresG.size(); i++)
									{
										TG t = (TG)tresG.elementAt(i);
										if (tg_select ==t.getId())
										{
										%>
									<option value="<%= t.getId()%>" selected><%=t.getNom() + t.getPrenom()%></option>
										<%
										}
										else
										{
										%>
									<option value="<%= t.getId()%>"><%=t.getNom() + t.getPrenom()%></option>
										<%
										}
									}
								%>
								</select>
							</td>
						</tr>
					</table>
					<div class="ligne_budgetaire"><b>Ligne budgétaire : </b>
						<select name="lb" id="lb" onChange="change()">
							<%
								for(int i=0; i < ligneBudgetaire.size(); i++)
								{
									LigneBudgetaire lb = (LigneBudgetaire)ligneBudgetaire.elementAt(i);
									if(id!=lb.getId())
									{
										%>
								<option value="<%= lb.getId()%>"><%=lb.getLibelle()%></option>
										<%
									}
									else
									{
										%>
								<option value="<%= lb.getId()%>" selected><%=lb.getLibelle()%></option>
										<%
									}
								}
							%>
						</select>
					</div>
					<div class="chapitre"><b>Chapitre : </b>
						<select name="chapitre">
							<%
								
								if (request.getParameter("lb")!=null)
								{
									lbc.chargeParId(new Integer(request.getParameter("lb")).intValue());
									chapitres = lbc.retournerChapitres();
									for(int i=0; i<chapitres.size(); i++)
									{
										Chapitre c = (Chapitre)chapitres.elementAt(i);
							%>
							<option value="<%=c.getId() %>"><%=c.getLibelle()%></option>
							<%
									}
								}
								else
								{
									Vector line = ligne_budgetaire.retournerTous();
									if(line !=null)
									{
										lbc.chargeParId(((LigneBudgetaire)line.elementAt(0)).getId());
										chapitres = lbc.retournerChapitres();
										for(int i=0; i<chapitres.size(); i++)
										{
											Chapitre c = (Chapitre)chapitres.elementAt(i);
							%>
							<option value="<%=c.getId() %>"><%=c.getLibelle()%></option>
							<%
										}
									}
								}
							%>
						</select>
					</div>
				</div>
					</FIELDSET>
					<FIELDSET class="conso">
	  					<LEGEND align="left">
	   						 Consommables
	 					 </LEGEND>
						<div class="consommables">
							<table>
								<tr>
									<th>Matériel : </th>
									<td>
										<select name="materiel">
										<%
											for (int i = 0; i<cons.size();i++)
											{
												consommable = (Consommable)cons.elementAt(i);
										%>
											<option value="<%=consommable.getId()%>"><%=consommable.getLibelle() + "&nbsp &nbsp " + consommable.getPrix() + "&euro" + "/" + consommable.getUnite()%></option>
										<%
												String[] conso = new String[2];
												conso[0] = new Integer(consommable.getPrix()).toString();
												conso[1] = new Integer(consommable.getId()).toString();
												table.addElement(conso);
											}
										%>
										</select>
									</td>
								</tr>
								<tr>
									<th>Sélection : </th>
									<td>
										<select name="sel" id="sel" size="5" multiple>
										<%
											for (int i=1 ; i< nombreOption+1 ; i++)
											{
												String opt = request.getParameter("sel[" +i + "]");
												String quantite = request.getParameter("selq[" +i + "]");
												String name = request.getParameter("seln[" +i + "]");
												hidden+= "<input type=\"hidden\" name=\"selq[" +i  + "]\" value=\"" + quantite + "\">\n";
												hidden+= "<input type=\"hidden\" name=\"sel[" +i  + "]\" value=\"" + opt + "\">\n";
												hidden+= "<input type=\"hidden\" name=\"seln[" +i  + "]\" value=\"" + name +" \">\n";
												out.print("<option value=\"" +opt +"\">" + name + "</option>");
											}
										%>
										</select>
									</td>
								</tr>
							</table>
						</div>
						<div  class="quantite">
							<table>
								<tr>
									<th>Quantité : </th>
									<td><input type="text" name="quantite" size="5"></td>
								</tr>
								<tr>
									<th>Total : </th>
									<td><span id="total" class="total"><%= total%></span>&nbsp &euro;</td>
								</tr>
							</table>
						</div>
						<input type="hidden" name="montant" value="<%=total%>">
						<input type="button" value="Ajouter" class="ajout" onclick="ajout()">

					</FIELDSET>
				<input type="submit" value="Valider la saisie">

				<%=hidden%>
				</form>
				<form name="prix">
<%
	for (int i = 0; i < table.size(); i++)
	{
		String nomCons[] = (String[])table.elementAt(i);
		%>
			<input type="hidden" name="mat<%=nomCons[1]%>" value="<%=nomCons[0]%>">
		<%		
	}
%>
				</form>
			</blockquote>
		</div>
	</body>
</html>
