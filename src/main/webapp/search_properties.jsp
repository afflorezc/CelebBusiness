<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
    <link href="assets/vendor/icofont/icofont.min.css" rel="stylesheet">
    <link href="assets/vendor/venobox/venobox.css" rel="stylesheet">

    <!-- Favicons -->
    <link href="assets/img/logo2.jpg" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
    <link href="assets/vendor/icofont/icofont.min.css" rel="stylesheet">
    <link href="assets/vendor/venobox/venobox.css" rel="stylesheet">

    <!-- Favicons -->
    <link href="assets/img/logo2.jpg" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">



    <title>Search Propieties</title>
</head>
<body>
    <%@ page import="com.afflorezc.utils.Images" %>
    <%@ page import="com.afflorezc.model.Property" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>

    <%  
        List<Property> properties = (List<Property>) request.getAttribute("properties");;
        boolean propertiesExist;
        if(request.getAttribute("properties") != null){       
            propertiesExist = true;
            
        }else{
            propertiesExist = false;
            
        }
    %>

    <%@ include file="header_user.jsp" %>

    <%@ include file="./assets/css/style.jsp" %>

    <div class="my-5"></div>

    <section id="properties" class="portfolio">
        <div class="container">

            <div class="section-title">
                <h2>Properties</h2>
            </div>
    
            <div class="row">
    
            <div class="row">
                <div class="col-lg-12">
                    <ul id="portfolio-flters">
                    <li data-filter="*" class="filter-active">All</li>
                    <li data-filter=".filter-house">Houses</li>
                    <li data-filter=".filter-aptment">Apartments</li>
                    <li data-filter=".filter-smallapt">Small apartments</li>
                    <li data-filter=".filter-art">Fine Art</li>
                    <li data-filter=".filter-collectible">Collectibles</li>
                    </ul>
                </div>
            </div>
    
            <div class="row portfolio-container">

                <%  
                    if(propertiesExist == true){
                        
                        for (int i=0; i < properties.size(); i++) {
                            Property property = properties.get(i);
                            String image = Images.randomImage();

                %>
                <!--Houses-->
                <div class="col-lg-4 col-md-6 filter-house">
                    <div class="card-property">
                        <div class="portfolio-item">
                            <img src="assets/img/portfolio/<%= image %>" class="img-fluid" alt="">
                            <div class="portfolio-info">
                                <form action="property" method="get">
                                    <input type="text" name="propertyID" value="<%= property.getPropertyID() %>" id="propertyID" hidden>
                                    <button type="submit" class="button-property">
                                        <h3><a class="venobox " title="House 1">View property</a></h3>
                                        <a class="venobox" title="House 1"><i class="icofont-plus"></i></a>
                                    </button>
                                </form>
                                
                            </div>
                        </div>
                        <div class="mb-4 description-properties-div">
                            <p class="description-properties"><span>Country: </span><%= property.getCountryLocation() %></p>
                            <p class="description-properties"><span>State: </span><%= property.getStateLocation() %></p>
                            <p class="description-properties"><span>City: </span><%= property.getCityLocation() %></p>
                            <p class="description-properties"><span>Value: </span><%= property.getValuation() %></p>
                        </div>
                    </div>

                </div>
                <% 
                        } // cerrando el for
                    }  //cerrando el if
                %>

    
            </div>
    
        </div>
    </section>

    <%@ include file="footer.jsp" %>
    
        </div>
    </section>

    <%@ include file="footer.jsp" %>

    <!-- Vendor JS Files -->
    <script src="assets/vendor/jquery/jquery.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendor/jquery.easing/jquery.easing.min.js"></script>
    <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
    <script src="assets/vendor/venobox/venobox.min.js"></script>
</body>
    <!-- Vendor JS Files -->
    <script src="assets/vendor/jquery/jquery.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendor/jquery.easing/jquery.easing.min.js"></script>
    <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
    <script src="assets/vendor/venobox/venobox.min.js"></script>
</body>
</html>