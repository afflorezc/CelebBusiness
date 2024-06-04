<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
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

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">

    <%@ page import="com.afflorezc.utils.Country" %>

    <title>register property</title>
</head>
<body>
    <%@ include file="./assets/css/style.jsp" %>

    <%@ include file="header_user.jsp" %>

    <div class="my-5 spacing-div"></div>

    <section id="contact" class="contact section-bg">
        
        <div class="container">

            <div class="section-title">
            <h2>Property</h2>
            <p>Publish the property you want to offer for sale.</p>
            </div>

            <div class="row">

                <div class="col-lg-5 col-md-12">
                    <form action="register_property" method="post" role="form" class="php-email-form">

                        <div class="form-group">
                            <!--<label for="countries" class="px-1">Select country</label>-->
                            <select name="countryLocation" id="countries" class="form-control" required>
                                <option value="">Select the country</option>
                                <% for(int i=0; i<Country.countries.length; i++){ %>
                                    <option value="<%= Country.countries[i] %>"> <%= Country.countries[i] %> </option>
                                <% } %>
                            </select>
                            <div class="validate"></div>
                        </div>

                        <div class="form-group">
                            <!--<label for="stateLocation">write the state</label>-->
                            <input type="text" class="form-control" name="stateLocation" id="stateLocation" placeholder="State of the property" required />
                            <div class="validate"></div>
                        </div>

                        <div class="form-group">
                            <!--<label for="cityLocation">write the city</label>-->
                            <input type="text" class="form-control" name="cityLocation" id="cityLocation" placeholder="City of the property" required />
                            <div class="validate"></div>
                        </div>

                        <div class="form-group">
                            <!--<label for="address">write the address</label>-->
                            <input type="text" class="form-control" name="address" id="address" placeholder="Address of the property" required />
                            <div class="validate"></div>
                        </div>

                        <!--<div class="form-group">
                            <label for="propertyStatus">Select the state of the property</label>
                            <select class="form-control" name="propertyStatus" id="propertyStatus" required >
                                <option value="On Sale">On Sale</option>
                                <option value="On Auction">On Auction</option>
                            </select>
                            <div class="validate"></div>
                        </div>-->

                        <div class="form-group">
                            <!--<label for="valuation">write the value of the property</label>-->
                            <input type="number" class="form-control" name="valuation" id="valuation" placeholder="Valuation of the property" min="0" step="0.01" required />
                            <div class="validate"></div>
                        </div>

                        <div class="form-group">
                            <!--<label for="description">Description of the property</label>-->
                            <textarea class="form-control" name="description" id="description" rows="5" placeholder="Description of the property" required></textarea> 
                            <div class="validate"></div>
                        </div>

                        <div class="mb-3">
                            <div class="loading"></div>
                            <div class="error"> <% if(request.getAttribute("error") != null){ %> <%= request.getAttribute("error") %> <% }%></div>
                            <div class="sent-message"></div>
                        </div>
                        <div class="text-center"><button type="submit">Publish</button></div>

                    </form>
                </div>

            </div>

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
</html>





