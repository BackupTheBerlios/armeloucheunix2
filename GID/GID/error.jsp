

   <FONT SIZE="+5">
      <CENTER>
         <FONT COLOR="RED">An ERROR has occured!!!</FONT>
         <BR>
         <BR>
         <%
            String error = request.getParameter("error") ;
            if ( error != null ) {
               out.println( error );
            }
         %>

      </CENTER>
   </FONT>
   

