package gid_metier;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import java.net.*;
import java.beans.*;
import javax.servlet.jsp.*;
import java.util.Vector;
import java.util.Collection;
import java.util.GregorianCalendar;

public class RequeteSql extends HttpServlet
{
	private DataSource ds; //la source de données
	private ResultSet res;
	private Connection conn;
	private java.sql.Statement s;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String action = null;
		JspFactory jspFactory = null;
		PageContext pageContext = null;
		HttpSession session = null;
		
		gid_metier.CCED cced = null;
		gid_metier.Comptabilite comptabilite = null;
		gid_metier.CPED cped = null;
		gid_metier.Ordonnateur ordonnateur = null;
		gid_metier.SousOrdonnateur sousOrdonnateur = null;
		gid_metier.TG tg = null;
		gid_metier.TR_TP tr_tp = null;
		gid_metier.LigneBudgetaire ligneBudgetaire = null;
		gid_metier.Consommable consommable = null;
		Vector etats = null;
		java.util.Vector ordo = null;
	    java.util.Vector ordonnances=null;
		gid_metier.OrdonnanceDelegation ordon = null;
		String titre=null;

		int id_acteur=0;
		
		try
		{
			jspFactory = JspFactory.getDefaultFactory();
			pageContext = jspFactory.getPageContext(this, request, response, "error.jsp", true, 8192, true );
			session = pageContext.getSession();
		
			ordonnateur = (Ordonnateur)pageContext.getAttribute("ordonnateur",PageContext.SESSION_SCOPE);
	        if(ordonnateur==null)
	        {
	            ordonnateur=(gid_metier.Ordonnateur)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.Ordonnateur" );
				pageContext.setAttribute("ordonnateur", ordonnateur, PageContext.SESSION_SCOPE );
				ordonnateur.setDataSource(ds);
	        }
	        tg = (TG)pageContext.getAttribute("tg",PageContext.SESSION_SCOPE);
	        if(tg==null)
	        {
				tg=(gid_metier.TG)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.TG" );
				pageContext.setAttribute("tg", tg, PageContext.SESSION_SCOPE );
				tg.setDataSource(ds);
	        }
	        tr_tp = (TR_TP)pageContext.getAttribute("tr_tp",PageContext.SESSION_SCOPE);
	        if (tr_tp==null)
	        {
				tr_tp=(gid_metier.TR_TP)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.TR_TP" );
				pageContext.setAttribute("tr_tp", tr_tp, PageContext.SESSION_SCOPE );
				tr_tp.setDataSource(ds);
	        }
	        cced = (CCED)pageContext.getAttribute("cced",PageContext.SESSION_SCOPE);
	        if (cced==null)
	        {
				cced=(gid_metier.CCED)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.CCED" );
				pageContext.setAttribute("cced", cced, PageContext.SESSION_SCOPE );
				cced.setDataSource(ds);
	        }
	        cped = (CPED)pageContext.getAttribute("cped",PageContext.SESSION_SCOPE);
	        if (cped==null)
	        {
				cped=(gid_metier.CPED)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.CPED" );
				pageContext.setAttribute("cped", cped, PageContext.SESSION_SCOPE );
				cped.setDataSource(ds);
	        }
	        sousOrdonnateur = (SousOrdonnateur)pageContext.getAttribute("sousOrdonnateur",PageContext.SESSION_SCOPE);
	        if (sousOrdonnateur==null)
	        { 
				sousOrdonnateur=(gid_metier.SousOrdonnateur)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.SousOrdonnateur" );
				pageContext.setAttribute("sousOrdonnateur", sousOrdonnateur, PageContext.SESSION_SCOPE );
				sousOrdonnateur.setDataSource(ds);
	        }
	        ligneBudgetaire = (LigneBudgetaire)pageContext.getAttribute("ligneBudgetaire",PageContext.SESSION_SCOPE);
	        if (ligneBudgetaire==null)
	        { 
				ligneBudgetaire=(gid_metier.LigneBudgetaire)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.LigneBudgetaire" );
				pageContext.setAttribute("ligneBudgetaire", ligneBudgetaire, PageContext.SESSION_SCOPE );
				ligneBudgetaire.setDataSource(ds);
	        }	
	        
			if (request.getParameter("action") != null )
			{
				action = request.getParameter("action");
			}

		}
		catch( Exception e )
		{
			action = e.toString();

			// Il n'y a rien de plus que l'on puisse faire
			if (response.isCommitted())
				return;
	
			// Affiche le message de l'exception sur la page d'erreurs
			if (e.getMessage() != null ) {
				response.sendRedirect("error.jsp?error=" + e.getMessage());
			}
			else {
				response.sendRedirect("error.jsp");
			}
		}
		if (action != null)
		{
			if (action.equalsIgnoreCase("identification") || action.equalsIgnoreCase("deconnexion"))
			{
				try
				{
				    ordonnateur=(gid_metier.Ordonnateur)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.Ordonnateur" );
					pageContext.setAttribute("ordonnateur", ordonnateur, PageContext.SESSION_SCOPE );
					ordonnateur.setDataSource(ds);
					
					sousOrdonnateur=(gid_metier.SousOrdonnateur)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.SousOrdonnateur" );
					pageContext.setAttribute("sousOrdonnateur", sousOrdonnateur, PageContext.SESSION_SCOPE );
					sousOrdonnateur.setDataSource(ds);
					
					cced=(gid_metier.CCED)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.CCED" );
					pageContext.setAttribute("cced", cced, PageContext.SESSION_SCOPE );
					cced.setDataSource(ds);
					
					cped=(gid_metier.CPED)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.CPED" );
					pageContext.setAttribute("cped", cped, PageContext.SESSION_SCOPE );
					cped.setDataSource(ds);
					
					tg=(gid_metier.TG)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.TG" );
					pageContext.setAttribute("tg", tg, PageContext.SESSION_SCOPE );
					tg.setDataSource(ds);
					
					tr_tp=(gid_metier.TR_TP)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.TR_TP" );
					pageContext.setAttribute("tr_tp", tr_tp, PageContext.SESSION_SCOPE );
					tr_tp.setDataSource(ds);
					
					ligneBudgetaire=(gid_metier.LigneBudgetaire)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.LigneBudgetaire" );
					pageContext.setAttribute("ligneBudgetaire", ligneBudgetaire, PageContext.SESSION_SCOPE );
					ligneBudgetaire.setDataSource(ds);
					
					consommable=(gid_metier.Consommable)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.Consommable" );
					pageContext.setAttribute("consommable", consommable, PageContext.SESSION_SCOPE );
					consommable.setDataSource(ds);
			        
				}
			    catch( Exception e )
				{
					// Il n'y a rien de plus que l'on puisse faire
					if (response.isCommitted())
						return;
			
					// Affiche le message de l'exception sur la page d'erreurs
					if (e.getMessage() != null ) {
						response.sendRedirect("error.jsp?error=" + e.getMessage());
					}
					else {
						response.sendRedirect("error.jsp");
					}
				}
				
				try
				{
				    conn = ds.getConnection();
					s = conn.createStatement();
					res = s.executeQuery("SELECT role FROM (SELECT id,login,mdp,'Ordonnateur' as role FROM ordonnateur UNION SELECT id,login,mdp,'SousOrdonnateur' as role FROM sousordonnateur UNION SELECT id,login,mdp,'CCED' as role FROM cced UNION SELECT id,login,mdp,'CPED' as role FROM cped UNION SELECT id,login,mdp,'TG' as role FROM tg UNION SELECT id,login,mdp,'TR_TP' as role FROM tr_tp) as foo WHERE login='" + request.getParameter("identifiant") + "' AND mdp='" + request.getParameter("password") + "'");
				    if (res.next())
				    {
					    if (res.getString("role").equalsIgnoreCase("TG"))
					    {
							tg.authentifie(request.getParameter("identifiant"), request.getParameter("password"));
					    }
					    else if (res.getString("role").equalsIgnoreCase("cced"))
					    {
					        cced.authentifie(request.getParameter("identifiant"), request.getParameter("password"));
					    }
					    else if (res.getString("role").equalsIgnoreCase("cped"))
					    {
					        cped.authentifie(request.getParameter("identifiant"), request.getParameter("password"));
					    }
					    else if (res.getString("role").equalsIgnoreCase("ordonnateur"))
					    {
					        ordonnateur.authentifie(request.getParameter("identifiant"), request.getParameter("password"));
					    }
					    else if (res.getString("role").equalsIgnoreCase("sousordonnateur"))
					    {
					        sousOrdonnateur.authentifie(request.getParameter("identifiant"), request.getParameter("password"));
					    }
					    else if (res.getString("role").equalsIgnoreCase("tr_tp"))
					    {
					        tr_tp.authentifie(request.getParameter("identifiant"), request.getParameter("password"));
					    }
				    }
				}
				catch(Exception e)
				{
				    System.out.println(e.getMessage());
				}
				finally
				{
					if (res != null)
					{
						try {
							res.close();
						} catch (SQLException e) {}
						res = null;
					}
					if (s != null) {
						try {
							s.close();
						} catch (SQLException e) {}
						s = null;
					}
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {}
						conn = null;
					}
				}
				if (request.getParameter("url")!=null)
				{
				    gotoPage(request.getParameter("url"),request,response);
				}
				else
				{
				    gotoPage("/header.jsp",request,response);
				}
			}
			if (action.equalsIgnoreCase("archives"))
			{
			    try
			    {
			        ordonnances = (java.util.Vector)Beans.instantiate(this.getClass().getClassLoader(), "java.util.Vector" );
			    }
			    catch(Exception e)
			    {
//			      Il n'y a rien de plus que l'on puisse faire
					if (response.isCommitted())
						return;
			
					// Affiche le message de l'exception sur la page d'erreurs
					if (e.getMessage() != null ) {
						response.sendRedirect("error.jsp?error=" + e.getMessage());
					}
					else {
						response.sendRedirect("error.jsp");
					}
			    }
			    if (cced.identifie())
			    {
			    	ordonnances = cced.getArchives();
			    }
			    else if(cped.identifie())
			    {
			    	ordonnances = cped.getArchives();
			    }
			    else if(ordonnateur.identifie())
			    {
			    	ordonnances = ordonnateur.getArchives();
			    }
			    else if(sousOrdonnateur.identifie())
			    {
			    	ordonnances = sousOrdonnateur.getArchives();
			    }
			    else if(tg.identifie())
			    {
			    	ordonnances = tg.getArchives();
			    }
			    else if(tr_tp.identifie())
			    {
			    	ordonnances = tr_tp.getArchives();
			    }
			    titre = "Archives des ordonnances de delegation";
			    pageContext.setAttribute("titre", titre, PageContext.SESSION_SCOPE);
			    pageContext.setAttribute("ordonnances", ordonnances, PageContext.SESSION_SCOPE );
			    gotoPage("/tableau_de_bord.jsp",request,response);
			}
			if (action.equalsIgnoreCase("encours"))
			{
			    try
			    {
			        ordonnances = (java.util.Vector)Beans.instantiate(this.getClass().getClassLoader(), "java.util.Vector" );
			    }
			    catch(Exception e)
			    {
//			      Il n'y a rien de plus que l'on puisse faire
					if (response.isCommitted())
						return;
			
					// Affiche le message de l'exception sur la page d'erreurs
					if (e.getMessage() != null ) {
						response.sendRedirect("error.jsp?error=" + e.getMessage());
					}
					else {
						response.sendRedirect("error.jsp");
					}
			    }
			    if (cced.identifie())
			    {
			    	ordonnances = cced.getEnCours();
			    }
			    else if(cped.identifie())
			    {
			    	ordonnances = cped.getEnCours();
			    }
			    else if(ordonnateur.identifie())
			    {
			    	ordonnances = ordonnateur.getEnCours();
			    }
			    else if(sousOrdonnateur.identifie())
			    {
			    	ordonnances = sousOrdonnateur.getEnCours();
			    }
			    else if(tg.identifie())
			    {
			    	ordonnances = tg.getEnCours();
			    }
			    else if(tr_tp.identifie())
			    {
			    	ordonnances = tr_tp.getEnCours();
			    }
			    titre = "Ordonnances de delegation en cours de traitement";
			    pageContext.setAttribute("titre", titre, PageContext.SESSION_SCOPE);
			    pageContext.setAttribute("ordonnances", ordonnances, PageContext.SESSION_SCOPE );
			    gotoPage("/tableau_de_bord.jsp",request,response);
			}
			if (action.equalsIgnoreCase("ordonnance"))
			{
			    try
			    {
			        ordonnances = (java.util.Vector)Beans.instantiate(this.getClass().getClassLoader(), "java.util.Vector" );
			    }
			    catch(Exception e)
			    {
//			      Il n'y a rien de plus que l'on puisse faire
					if (response.isCommitted())
						return;
			
					// Affiche le message de l'exception sur la page d'erreurs
					if (e.getMessage() != null ) {
						response.sendRedirect("error.jsp?error=" + e.getMessage());
					}
					else {
						response.sendRedirect("error.jsp");
					}
			    }
			    if (cced.identifie())
			    {
			    	ordonnances = cced.getATraiters();
			    }
			    else if(cped.identifie())
			    {
			    	ordonnances = cped.getATraiters();
			    }
			    else if(ordonnateur.identifie())
			    {
			    	ordonnances = ordonnateur.getATraiters();
			    }
			    else if(sousOrdonnateur.identifie())
			    {
			    	ordonnances = sousOrdonnateur.getATraiters();
			    }
			    else if(tg.identifie())
			    {
			    	ordonnances = tg.getATraiters();
			    }
			    else if(tr_tp.identifie())
			    {
			    	ordonnances = tr_tp.getATraiters();
			    }
			    titre = "Ordonnances de delegation a traiter";
			    pageContext.setAttribute("titre", titre, PageContext.SESSION_SCOPE);
			    pageContext.setAttribute("ordonnances", ordonnances, PageContext.SESSION_SCOPE );
			    gotoPage("/tableau_de_bord.jsp",request,response);
			}
			
			if (action.equalsIgnoreCase("detailOrdonnance"))
			{
			    ordo = (Vector)pageContext.getAttribute("ordonnances",PageContext.SESSION_SCOPE);
			    
			    int place = new Integer(request.getParameter("place")).intValue();
			    if(ordo.size()>place)
			    {
			        ordon =(OrdonnanceDelegation)ordo.elementAt(place);
			    }
			    try
			    {
				    titre = (java.lang.String)pageContext.getAttribute("titre",PageContext.SESSION_SCOPE);
			    }
			    catch( Exception e )
				{
					// Il n'y a rien de plus que l'on puisse faire
					if (response.isCommitted())
						return;
			
					// Affiche le message de l'exception sur la page d'erreurs
					if (e.getMessage() != null ) {
						response.sendRedirect("error.jsp?error=" + e.getMessage());
					}
					else {
						response.sendRedirect("error.jsp");
					}
				}
			    pageContext.setAttribute("ordon", ordon, PageContext.SESSION_SCOPE );
			    pageContext.setAttribute("titre", titre, PageContext.SESSION_SCOPE );
				gotoPage("/detail_ordonnance.jsp",request,response);
			}
			if (action.equalsIgnoreCase("process_ordonnance"))
			{
			    try
			    {
			        ordon = (OrdonnanceDelegation)pageContext.getAttribute("ordon",PageContext.SESSION_SCOPE);
			    }
			    catch( Exception e )
				{
					// Il n'y a rien de plus que l'on puisse faire
					if (response.isCommitted())
						return;
			
					// Affiche le message de l'exception sur la page d'erreurs
					if (e.getMessage() != null ) {
						response.sendRedirect("error.jsp?error=" + e.getMessage());
					}
					else {
						response.sendRedirect("error.jsp");
					}
				}
			    int etatOrdo = ordon.getEtat();
			    Acteur acteur=null;
			    GregorianCalendar dateG = new GregorianCalendar();
			    java.util.Date date = dateG.getTime();
			    if (cced.identifie())
			    {
			    	switch(etatOrdo)
			    	{
			    		case 1:
			    		{
			    		    if(request.getParameter("viser").equalsIgnoreCase("true"))
			    		    {
			    		        ordon.setVisaCCED(true);
			    		        ordon.setRefVisaCCED("True");
			    		        ordon.setDateVisaCCED(date);
			    		        ordon.setEtat(2);
			    		        try 
			    		        {
			    		            cced.majComptabilite(ordon);
			    		        }
			    		        catch(Exception e)
			    		        {
			    		            System.out.println(e.getMessage());
			    		        }
			    		    }
			    		    else
			    		    {
			    		        ordon.setVisaCCED(false);
			    		        ordon.setRefVisaCCED("False");
			    		        ordon.setDateVisaCCED(date);
			    		        ordon.setEtat(1);
			    		    }
			    		    acteur=ordon.getInitiateur();
			    		    break;
			    		}
			    	}
		    	    try
		    	    {
			    	    
		    		    cced.viser(ordon);
			    	    cced.transmettreOrdonnance(ordon, acteur);
			    	    cced.prendreOrdonnanceEnCharge(ordon);
			    	    cced.addEnCours(ordon);
			    	    Action act = new Action(ds);
			    	    act.setLibelle("Prise en charge");
			    	    act.setDate(date);
			    	    act.setType(1);
			    	    act.setParticipant(cced);
			    	    act.setOrdonnance(ordon);
			    	    act.sauver();
			    	    act.setLibelle("Visa");
			    	    act.setType(3);
			    	    act.sauver();
			    	    act.setLibelle("Transmission");
			    	    act.setType(2);
			    	    act.sauver();
		    	    }
		    	    catch(Exception e)
		    	    {
		    	        System.out.println(e.getMessage());
		    	    }
			    }
			    else if (cped.identifie())
			    {
			        try
			    	{
			            if (cped.verifOrdoEnvoye(ordon))
			            {
				            cped.transmettreOrdonnance(ordon, ordon.getDelegataire());
			            }
			            cped.prendreOrdonnanceEnCharge(ordon);
			            cped.majComptabilite(ordon);
	    		        cped.addEnCours(ordon);
			            Action act = new Action(ds);
			    	    act.setLibelle("Prise en charge");
			    	    act.setDate(date);
			    	    act.setType(1);
			    	    act.setParticipant(cped);
			    	    act.setOrdonnance(ordon);
			    	    act.sauver();
			    	    act.setLibelle("Transmission");
			    	    act.setType(2);
			    	    act.sauver();
			    	}
			    	catch(Exception e)
			    	{
			    	    
			    	}
			    }
			    else if (ordonnateur.identifie())
			    {
			        switch(etatOrdo)
			    	{
			    		case 1:
			    		    {
			    		    	if (request.getParameter("modOrdonnance").equalsIgnoreCase("non"))
			    		    	{
			    		    	    try
			    			        {
			    			            ordonnateur.retirerOrdonnanceDelegation(ordon);
			    			            gotoPage("/ordonnance_retiree.jsp",request,response);
			    			            
			    			        }
			    			        catch (Exception e)
			    			        {
			    			            System.out.println(e.getMessage());
			    			        }
			    		    	}
			    		    	else
			    		    	{
			    		    	    
			    		    	}
			    		    	break;
			    		    }
			    		case 2:
			    		{
			    		    if(request.getParameter("transmettre").equalsIgnoreCase("transmettre"))
			    		    {
			    		        try
			    		        {
			    		            ordonnateur.transmettreOrdonnance(ordon, ordon.getDelegataire());
			    		        }
			    		        catch(Exception e)
			    		        {
			    		            
			    		        }
			    		    }
			    		    acteur=ordon.getComptable();
			    		   	break;
			    		}
			    		case 3:
		    		    {
			    		    try
			    		    {
			    		        acteur = ordon.getDelegataire();
			    		    }
			    		    catch (Exception e)
			    		    {
			    		        
			    		    }
			    		    break;
		    		    }
			    	}
			        try
			    	{
			            ordonnateur.transmettreOrdonnance(ordon, acteur);
			            ordonnateur.prendreOrdonnanceEnCharge(ordon);
			            ordonnateur.majComptabilite(ordon);
			            ordonnateur.addEnCours(ordon);
			            Action act = new Action(ds);
			    	    act.setLibelle("Prise en charge");
			    	    act.setDate(date);
			    	    act.setType(1);
			    	    act.setParticipant(ordonnateur);
			    	    act.setOrdonnance(ordon);
			    	    act.sauver();
			    	    act.setLibelle("Transmission");
			    	    act.setType(2);
			    	    act.sauver();
			    	}
			    	catch(Exception e)
			    	{
			    	    
			    	}
			    }
			    else if (sousOrdonnateur.identifie())
			    {
			        switch(etatOrdo)
			    	{
			    		case 3:
			    		    {
			    		    	try
			    		    	{
			    		    	    sousOrdonnateur.transmettreOrdonnance(ordon, sousOrdonnateur.getTr_tp());
			    		    	    break;
			    		    	}
			    		    	catch(Exception e)
			    		    	{
			    		    	    
			    		    	}
			    		    }
			    	}
			        try
			    	{
			            if(!sousOrdonnateur.verifOrdoEnvoye(ordon))
			            {
			                sousOrdonnateur.transmettreOrdonnance(ordon, sousOrdonnateur.getCped());
			            }
			            sousOrdonnateur.prendreOrdonnanceEnCharge(ordon);
			            sousOrdonnateur.addEnCours(ordon);
			            sousOrdonnateur.majComptabilite(ordon);
			            Action act = new Action(ds);
			    	    act.setLibelle("Prise en charge");
			    	    act.setDate(date);
			    	    act.setType(1);
			    	    act.setParticipant(sousOrdonnateur);
			    	    act.setOrdonnance(ordon);
			    	    act.sauver();
			    	    act.setLibelle("Transmission");
			    	    act.setType(2);
			    	    act.sauver();
			            
			    	}
			    	catch(Exception e)
			    	{
			    	    
			    	}
			    }
			    else if (tg.identifie())
			    {
			        switch(etatOrdo)
			    	{
			    		case 2:
		    		    {
			    		    if(request.getParameter("viser").equalsIgnoreCase("true"))
			    		    {
			    		        ordon.setVisaTG(true);
			    		        ordon.setRefVisaTG("True");
			    		        ordon.setDateVisaTG(date);
			    		        ordon.setEtat(3);
			    		        try 
			    		        {
			    		            tg.majComptabilite(ordon);
			    		        }
			    		        catch(Exception e)
			    		        {
			    		            System.out.println(e.getMessage());
			    		        }
			    		    }
			    		    else
			    		    {
			    		        ordon.setVisaTG(false);
			    		        ordon.setRefVisaTG("False");
			    		        ordon.setDateVisaTG(date);
			    		        ordon.setEtat(2);
			    		    }
			    		    acteur=ordon.getInitiateur();
		    		    	break;
		    		    }
			    	}
			        try
			    	{
			            tg.transmettreOrdonnance(ordon, acteur);
			            tg.prendreOrdonnanceEnCharge(ordon);
			            tg.addEnCours(ordon);
			            tg.viser(ordon);
			            Action act = new Action(ds);
			    	    act.setLibelle("Prise en charge");
			    	    act.setDate(date);
			    	    act.setType(1);
			    	    act.setParticipant(tg);
			    	    act.setOrdonnance(ordon);
			    	    act.sauver();
			    	    act.setLibelle("Visa");
			    	    act.setType(3);
			    	    act.sauver();
			    	    act.setLibelle("Transmission");
			    	    act.setType(2);
			    	    act.sauver();
			    	}
			    	catch(Exception e)
			    	{
			    	    
			    	}
			    }
			    else if (tr_tp.identifie())
			    {
			        try
			    	{
			            if (tr_tp.peut_clore(ordon))
			            {
				            ordon.clore();
				            tr_tp.prendreOrdonnanceEnCharge(ordon);
		    		        tr_tp.majComptabilite(ordon);
				            Action act = new Action(ds);
				    	    act.setLibelle("Prise en charge");
				    	    act.setDate(date);
				    	    act.setType(1);
				    	    act.setParticipant(tr_tp);
				    	    act.setOrdonnance(ordon);
				    	    act.sauver();
				    	    act.setLibelle("Transmission");
				    	    act.setType(2);
				    	    act.sauver();
				            tr_tp.addArchives(ordon);
			            }
			            else
			            {
			                gotoPage("/pas-clore.jsp",request,response);
			            }
			    	}
			    	catch(Exception e)
			    	{
			    	    
			    	}
			    }
			    gotoPage("/tableau_de_bord.jsp",request,response);
			}
			if (action.equalsIgnoreCase("nouvelle_ordonnance"))
			{
			    try
				{
			        ordonnateur = (Ordonnateur)pageContext.getAttribute("ordonnateur",PageContext.SESSION_SCOPE);
			        if (ordonnateur==null)
			        {
			            ordonnateur=(gid_metier.Ordonnateur)Beans.instantiate(this.getClass().getClassLoader(), "gid_metier.Ordonnateur" );
			            pageContext.setAttribute("ordonnateur", ordonnateur, PageContext.SESSION_SCOPE );
			            ordonnateur.setDataSource(ds);
			        }
				}
			    catch( Exception e )
				{
					// Il n'y a rien de plus que l'on puisse faire
					if (response.isCommitted())
						return;
			
					// Affiche le message de l'exception sur la page d'erreurs
					if (e.getMessage() != null ) {
						response.sendRedirect("error.jsp?error=" + e.getMessage());
					}
					else {
						response.sendRedirect("error.jsp");
					}
				}
			    int nombreOption;
			    String nomOrdonnance="";
			    int so_select=-1;
			    int ced_select=-1;
			    int tg_select=-1;
			    int id=-1;
			    int total = 0;
			   // Vector commande = new Vector();
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
			    
			    if (request.getParameter("sous_ordonnateur")!=null)
			    {
			    	so_select =new Integer(request.getParameter("sous_ordonnateur")).intValue();
			    }
			    
			    if (request.getParameter("ced")!=null)
			    {
			    	ced_select =new Integer(request.getParameter("ced")).intValue();
			    }
			    
			    if (request.getParameter("tg")!=null)
			    {
			    	tg_select =new Integer(request.getParameter("tg")).intValue();
			    }
			    
			    if (request.getParameter("lb")!=null)
			    {
			    	id=new Integer(request.getParameter("lb")).intValue();
			    }
			    
			    if(request.getParameter("montant")!=null)
			    {
			    	total = new Integer(request.getParameter("montant")).intValue();
			    }
			    
			    OrdonnanceDelegation ordonnance = new OrdonnanceDelegation(ds);
			    ordonnance.setLibelle(nomOrdonnance);
			    ordonnance.setMontant(total);
			    ordonnance.setInitiateur(ordonnateur);
			    ordonnance.setEtat(1);
			    GregorianCalendar date = new GregorianCalendar();
			    ordonnance.setDate(new java.util.Date(date.get(GregorianCalendar.YEAR) - 1900,date.get(GregorianCalendar.MONTH), date.get(GregorianCalendar.DAY_OF_MONTH)));
			    
			    SousOrdonnateur so = new SousOrdonnateur(ds);
			    try 
			    {
			        so.chargeParId(so_select);
			        ordonnance.setDelegataire(so);
			    }
			    catch (Exception e)
			    {
			        System.out.println(e);
			    }
			    
			    TG tres = new TG(ds);
			    try
			    {
			        tres.chargeParId(tg_select);
			        ordonnance.setComptable(tres);
			    }
			    catch (Exception e)
			    {
			        System.out.println(e);
			    }
			    for (int i=1; i<nombreOption +1; i++)
			    {
			        System.out.println(i);
			        int numeroProduit = new Integer(request.getParameter("sel[" + i + "]")).intValue();
			        int quantiteProduit = new Integer(request.getParameter("selq[" + i + "]")).intValue();
			        Consommable conso = new Consommable(ds);
			        conso.setId(numeroProduit);
			        conso.setQuantite(quantiteProduit);
			        ordonnance.addConsommable(conso);
			    }
			    try
			    {
			        ordonnateur.saisirOrdonnanceDelegation(ordonnance);
			        ordonnateur.majComptabilite(ordonnance);
			        ordonnateur.transmettreOrdonnance(ordonnance,ordonnateur.getControleur());
			    }
			    catch(Exception e)
			    {
			        System.out.println(e);
			    } 
			   
			    gotoPage("/tableau_de_bord.jsp",request,response);
			}
			if (action.equalsIgnoreCase("retrait_ordonnance"))
			{
			    if(request.getParameter("encours")!=null)
			    {
			        try
			        {
			            ordonnateur.retirerOrdonnanceDelegation((OrdonnanceDelegation)ordonnateur.getEnCours().elementAt(new Integer(request.getParameter("place")).intValue()));
			            ordonnateur.removeEnCours((OrdonnanceDelegation)ordonnateur.getEnCours().elementAt(new Integer(request.getParameter("place")).intValue()));
			        }
			        catch (Exception e)
			        {
			            System.out.println(e.getMessage());
			        }
			    }
			    else if (request.getParameter("a_traiter")!=null)
			    {
			        try
			        {
			            ordonnateur.retirerOrdonnanceDelegation((OrdonnanceDelegation)ordonnateur.getATraiters().elementAt(new Integer(request.getParameter("place")).intValue()));
			            ordonnateur.removeATraiter((OrdonnanceDelegation)ordonnateur.getATraiters().elementAt(new Integer(request.getParameter("place")).intValue()));
			        }
			        catch (Exception e)
			        {
			            System.out.println(e.getMessage());
			        }
			    }
			    Comptabilite comp = new Comptabilite(ds);
			    try
			    {
			        comp.chargeParIdActeur(ordonnateur.getId());
			    }
			    catch(Exception e)
			    {
			        
			    }
			    ordonnateur.setComptaPerso(comp);
			    gotoPage("/ordonnance_retiree.jsp",request,response);
			}
		}
	}
		
		
	
	public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		doPost(request,response);
	}

	private void gotoPage( String address, HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	public void init() throws ServletException {
		try {
			//récupération de la source de donnée
			Context initCtx = new InitialContext();
			ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RequeteSql");
		} catch (Exception e) {
			throw new UnavailableException(e.getMessage());
		}
	}
}