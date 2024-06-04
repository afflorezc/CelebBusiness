<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Favicons -->
    <link href="assets/img/logo2.jpg" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Lato:400,300,700,900" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
    <link href="assets/vendor/icofont/icofont.min.css" rel="stylesheet">
    <link href="assets/vendor/venobox/venobox.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">

    <%@ page import="com.afflorezc.utils.Images" %>
    <%@ page import="com.afflorezc.model.Property" %> 

    <%
        Property property = (Property) request.getAttribute("property");
        String name = (String) request.getAttribute("name");
        if(property == null){
            System.out.println("la propiedad es nula");
            //response.sendRedirect("search_properties_borrar.jsp");
        }
    %>

    <title>Property</title>
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="my-5"></div>

    <section id="about" class="about">
        <div class="container">

            <div class="section-title">
            <h2>Property</h2>
            </div>
    
            <div class="row"> 
                <div class="col-lg-6 order-1 order-lg-2">
                    <img src="assets/img/portfolio/<%= Images.randomImage() %>" class="img-fluid" alt="">
                </div>
            
                <!-- Atributes of property -->

                <%  if(property != null){ %>

                <div class="col-lg-6 pt-4 pt-lg-0 order-2 order-lg-1">
                    <h3>Information of property</h3>
                    <br />
                    <p class="property-properties">
                        <span class="property-sub-tittle">Owner: </span>  
                        <span class="font-italic"><%= name %></span>
                    </p>
                    <p class="property-properties">
                        <span class="property-sub-tittle">Country: </span>  
                        <span class="font-italic"><%= property.getCountryLocation() %></span>
                    </p>
                    <p class="property-properties">
                        <span class="property-sub-tittle">State: </span>  
                        <span class="font-italic"><%= property.getStateLocation() %></span>
                    </p>
                    <p class="property-properties">
                        <span class="property-sub-tittle">City: </span>  
                        <span class="font-italic"><%= property.getCityLocation() %></span>
                    </p>
                    <p class="property-properties">
                        <span class="property-sub-tittle">Addres: </span>  
                        <span class="font-italic"><%= property.getAddress() %></span>
                    </p>
                    <p class="property-properties">
                        <span class="property-sub-tittle">Valuation: </span>  
                        <span class="font-italic"><%= property.getValuation() %></span>
                    </p>

                    <br />
                    <!-- Description of property -->
                    
                </div>
            </div>
            <div class="row">
                <p class="pt-3">
                    <%= property.getDescription() %>
                </p>
            </div>
            <% } %>
        </div>
        
    </section><!-- End About Us Section -->

    <div class="my-5"></div>
    <div class="my-5"></div> 

    <%@ include file="footer.jsp" %>

    <!-- Vendor JS Files -->
    <script src="assets/vendor/jquery/jquery.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendor/jquery.easing/jquery.easing.min.js"></script>
    <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
    <script src="assets/vendor/venobox/venobox.min.js"></script>

</body>
</html>