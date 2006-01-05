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
					case 1 : message = "L'ordonnance est en cours de traitement par l'Ordonnateur Principal et attend d'�tre r�vis�e ou de mettre fin � la proc�dure";break;
					case 2 : message = "L'ordonnance est est en cours de traitement pour obtenir le visa de la TG";break;
					case 3 : message = "L'ordonnance a �t� vis�e par le CED et la TG";break;
				}
				break;
			}
	case 2 : 
			{
				switch(etat)
				{
					case 2 : message = "L'ordonnance est vis�e par le CED et est en cours de traitement par la TG";break;
					case 3 : message = "L'ordonnance a �t� vis�e par le CED et la TG";break;
				}
				break;
			}
	case 3 : 
			{
				switch(etat)
				{
					case 1 : message = "L'ordonnance est en cours de traitement par le CED et attend son Visa";break;
					case 2 : message = "L'ordonnance est est en cours de traitement et n'est pas encore vis�e par la TG";break;
					case 3 : message = "L'ordonnance a �t� vis�e par le CED et la TG";break;
				}
				break;
			}
	case 4 : 
			{
				switch(etat)
				{
					case 2 : message = "L'ordonnance a �t� vis�e par le CED. Elle a �t� transmise � votre CPED";break;
					case 3 : message = "L'ordonnance est vis�e par la TG. Elle a �t� transmise � votre CPED et � votre TR/TP";break;
				}
				break;
			}
	case 5 : 
			{
				switch(etat)
				{
					case 3 : message = "L'ordonnance a �t� vis�e par le CED et la TG";break;
				}
				break;
			}
}
out.print("<div class=\"message\"><form action=\"/GID/GID\" method=\"post\"><input type=\"hidden\" name=\"action\" value=\"process_ordonnance\">" + message + "</form></div>");
%>
