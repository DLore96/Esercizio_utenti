<%@page import="it.prova.model.Automobile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.text.SimpleDateFormat" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../header.jsp" />
		<title>modifica elemento</title>
		
		<!-- style per le pagine diverse dalla index -->
	    <link href="./assets/css/global.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica libro</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
					<form method="post" action="ExecuteUpdateAutomobileServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Marca <span class="text-danger">*</span></label>
								<input type="text" name="marca" id="marca" class="form-control" value="${Auto_Modifica.marca}">
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Genere <span class="text-danger">*</span></label>
								<input type="text" name="modello" id="modello" class="form-control" value="${Auto_Modifica.modello}">
							</div>
							
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label>cilindrata <span class="text-danger">*</span></label>
								<input type="number" class="form-control" name="cilindrata" id="cilindrata" value="${Auto_Modifica.cilindrata}">
							</div>
							
							<div class="form-group col-md-3">
								<label>Data Immatricolazione<span class="text-danger">*</span></label>
                        		<input class="form-control" id="dataImmatricolazione" type="date" value="<fmt:formatDate type="date" pattern="yyy/MM/dd" value="${Auto_Modifica.dataImmatricolazione}"/>"
                            		title="formato : gg/mm/aaaa"  name="dataImmatricolazioneInput">
							</div>
							
						</div>

						<div class="form-group col-md-6">	
							<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
							<input type="hidden" name="idauto" value="${Auto_Modifica.id}">
							 <a href="ListAutomobileServlet" class='btn btn-outline-secondary' style='width:80px'>
			                <i class='fa fa-chevron-left'></i> Back
			        </a>
						</div>
	
					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	</body>
</html>