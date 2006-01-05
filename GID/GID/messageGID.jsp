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
					case 1 : message = "<b>Viser l'ordonnance? </b><div class=\"reponse\"><input name=\"viser\" type=\"radio\" value=\"true\">Oui<br/><input name=\"viser\" type=\"radio\" value=\"false\">Non</div><br><input class=\"valider\" type=\"submit\" value=\"Valider\">";break;
				}
				break;
			}
	case 2 : 
			{
				switch(etat)
				{
					case 2 : message = "L'ordonnance est visée par le CED.<br><input class=\"transmettre\" type=\"submit\" value=\"Inscrire à la comptabilité\"";break;
					case 3 : message = "L'ordonnance a été visée par la TG.<br><input class=\"transmettre\" type=\"submit\" value=\"Inscrire à la comptabilité\"";break;
				}
				break;
			}
	case 3 : 
			{
				switch(etat)
				{
					case 1 : 
					{
						message = "Le visa n'a pas été accordé par le CCED<br> Modifier l'ordonnance?<input name=\"modOrdonnance\" type=\"radio\" value=\"oui\">Oui<br><input name=\"modOrdonnance\" type=\"radio\" value=\"non\">Non, mettre fin à la procédure<br><input class=\"valider\" type=\"submit\" value=\"Valider\">";
						break;
					}
					case 2 : 
					{
						message = "L'ordonnance a été visée par le CED<br>\n<input name=\"transmettre\"type=\"checkbox\" checked value=\"transmettre\">\nTransmettre egalement au sous ordonnateur délégataire<br>\n<input class=\"transmettre\" type=\"submit\" name=\"tg\" value=\"Transmettre à la TG\">";
						break;
					}
					case 3 : message = "L'ordonnance a été visée par la TG<input class=\"transmettre\" type=\"submit\" value=\"Transmettre au sous ordonnateur\">";break;
				}
				break;
			}
	case 4 : 
			{
				switch(etat)
				{
					case 2 : message = "L'ordonnance est visée par le CED et inscrite à votre comptabilité. <input class=\"transmettre\" type=\"submit\" value=\"Transmettre au CPED\"";break;
					case 3 : message = "L'ordonnance est visée par la TG et inscrite à votre comptabilité. <input class=\"transmettre\" type=\"submit\" value=\"Transmettre au TR/TP\"";break;
				}
				break;
			}
	case 5 : 
			{
				switch(etat)
				{
					case 2 : message = "<b>Viser l'ordonnance?</b> <input name=\"viser\" type=\"radio\" value=\"true\">Oui<br><input name=\"viser\" type=\"radio\" value=\"false\">Non<input class=\"valider\" type=\"submit\" value=\"Valider\">";break;
				}
				break;
			}
	case 6 : 
			{
				switch(etat)
				{
					case 3 : message = "L'ordonnance est inscrite à votre comptabilité <br><input class=\"transmettre\" type=\"submit\" value=\"Clore la procédure\"";break;
				}
				break;
			}
}
out.print("<div class=\"message\"><form action=\"/GID/GID\" method=\"post\"><input type=\"hidden\" name=\"action\" value=\"process_ordonnance\">" + message + "</form></div>");
%>
