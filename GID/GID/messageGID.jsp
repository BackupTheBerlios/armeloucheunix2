<%
int typeActeur=-1;
String message = "";
if (cced.identifie())
{
	typeActeur = 1;
}
else if (cped.identifie())
{
	typeActeur = 2;
}
else if (ordonnateur.identifie())
{
	typeActeur = 3;
}
else if (sousOrdonnateur.identifie())
{
	typeActeur = 4;
}
else if (tg.identifie())
{
	typeActeur = 5;
}
else if (tr_tp.identifie())
{
	typeActeur = 6;
}
switch(typeActeur)
{
	case 1 : 
			{
				switch(etat)
				{
					case 1 : message = "<b>Viser l'ordonnance? </b><input name=\"viser\" type=\"radio\" value=\"true\">Oui<br/><input name=\"viser\" type=\"radio\" value=\"false\">Non<br><input type=\"submit\" value=\"Valider\">";break;
					case 2 : message = "";break;
					case 3 : message = "";break;
					case 4 : message = "";break;
				}
				break;
			}
	case 2 : 
			{
				switch(etat)
				{
					case 1 : message = "";break;
					case 2 : message = "L'ordonnance est vis�e par le CED et inscrite � votre comptabilit�";break;
					case 3 : message = "L'ordonnance a �t� vis�e par la TG <input type=\"submit\" value=\"Transmettre au sous ordonnateur\"";break;
					case 4 : message = "";break;
				}
				break;
			}
	case 3 : 
			{
				switch(etat)
				{
					case 1 : 
					{
						message = "Le visa n'a pas �t� accord� par le CCED<br> Modifier l'ordonnance?<input name=\"modOrdonnance\" type=\"radio\" value=\"oui\">Oui<br><input name=\"modOrdonnance\" type=\"radio\" value=\"non\">Non, mettre fin � la proc�dure<br><input type=\"submit\" value=\"Valider\">";
						break;
					}
					case 2 : 
					{
						message = "L'ordonnance a �t� vis�e par le CED<br>\n<input name=\"transmettre\"type=\"checkbox\" checked value=\"transmettre\">\nTransmettre egalement au sous ordonnateur d�l�gataire<br>\n<input type=\"submit\" name=\"tg\" value=\"Transmettre � la TG\">";
						break;
					}
					case 3 : message = "L'ordonnance a �t� vis�e par la TG<input type=\"submit\" value=\"Transmettre au sous ordonnateur\">";break;
					case 4 : message = "";break;
				}
				break;
			}
	case 4 : 
			{
				switch(etat)
				{
					case 1 : message = "";break;
					case 2 : message = "L'ordonnance est vis�e par le CED et inscrite � votre comptabilit�. Elle a �t� transmise � votre CPED";break;
					case 3 : message = "L'ordonnance est vis�e par la TG et inscrite � votre comptabilit�. Elle a �t� transmise � votre CPED et � votre TR/TP";break;
					case 4 : message = "";break;
				}
				break;
			}
	case 5 : 
			{
				switch(etat)
				{
					case 1 : message = "";break;
					case 2 : message = "<b>Viser l'ordonnance?</b> <input name=\"viser\" type=\"radio\" value=\"true\">Oui<br><input name=\"viser\" type=\"radio\" value=\"false\">Non<input type=\"submit\" value=\"Valider\">";break;
					case 3 : message = "";break;
					case 4 : message = "";break;
				}
				break;
			}
	case 6 : 
			{
				switch(etat)
				{
					case 1 : message = "";break;
					case 2 : message = "";break;
					case 3 : message = "L'ordonnance est inscrite � votre comptabilit�";break;
					case 4 : message = "";break;
				}
				break;
			}
}
out.print("<div class=\"message\"><form action=\"/GID/GID\" method=\"post\"><input type=\"hidden\" name=\"action\" value=\"process_ordonnance\">" + message + "</form></div>");
%>
