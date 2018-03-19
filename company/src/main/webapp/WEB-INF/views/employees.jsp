<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
   href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Employees</title>
</head>
<body>
   <section>
      <div class="jumbotron">
         <div class="container">
            <h1>Employees</h1>
            <p>All the available Employees in our Company</p>
         </div>
      </div>
   </section>

   <section class="container">
      <div class="row">
         <c:forEach items="${employees}" var="employee">
            <div class="col-sm-6 col-md-3">
               <div class="thumbnail">
                  <div class="caption">
                     <h3>${employee.name}</h3>
                     <p>${employee.employeeId}</p>
                     <p>${employee.description}</p>
                     <p>$${employee.salary}</p>
<p>Is employee inactive?: ${employee.inactive} </p>
                  </div>
               </div>
            </div>
         </c:forEach>
      </div>
   </section>
</body>
</html>
