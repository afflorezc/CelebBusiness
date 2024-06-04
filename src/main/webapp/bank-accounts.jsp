<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Celeb Business Bank</title>
        <meta content="" name="descriptison">
        <meta content="" name="keywords">

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

    </head>

    <body>
        <%@ include file="header_bank.jsp" %>

        <main id="main">

            <section id="accounts" class="services section-bg">
                
                <div class="container">
                    <div class="section-title">
                        <h4>Savings and Investment options</h4>
                    </div>

                    <div class="row">
           
                        <div class="col-lg-4 col-md-6 icon-box">
                            <div class="icon"><i class="icofont-money-bag"></i></div>
                            <h4 class="title"><a href="open-bank-account.jsp">Saving Account</a></h4>
                            <p class="description">A great profitable option to have access to your money and 
                                make business in any place of the world!
                            </p>
                        </div>
    
                        <div class="col-lg-4 col-md-6 icon-box">
                            <div class="icon"><i class="icofont-coins"></i></div>
                            <h4 class="title"><a href="open-bank-account.jsp">Checking Account</a></h4>
                            <p class="description">High liquidity. Full access to your money at any time
                            </p>
                        </div>
    
                        <div class="col-lg-4 col-md-6 icon-box">
                            <div class="icon"><i class="icofont-chart-growth"></i></div>
                            <h4 class="title"><a href="open_investment_request">Investments</a></h4>
                            <p class="description">Access to one of the best portfolios for inversion,
                                increase your earnings with the best financial instruments arround the
                                world.
                            </p>

                            <p class="description">
                                <% if(request.getAttribute("deposit")!= null){ %>
                                    <%= request.getAttribute("message")%> 
                                <% } %>
                            </p>

                            <p class="text-center php-email-form"><a href="bank-transactions.jsp">
                                <% if(request.getAttribute("message")!= null){ %>
                                    <%= request.getAttribute("deposit")%> 
                                <% } %>
                                </a>
                            </p>

                        </div>

                    </div>
                </div>
            
            </section>

        </main><!-- End #main -->

        <!-- ======= Footer ======= -->
        <footer id="footer">
            <div class="container">
            <div class="copyright">
                &copy; Copyright <strong><span>Celeb Business</span></strong>. All Rights Reserved
            </div>
            <div class="credits">
                <!-- All the links in the footer should remain intact. -->
                <!-- You can delete the links only if you purchased the pro version. -->
                <!-- Licensing information: https://bootstrapmade.com/license/ -->
                <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/free-one-page-bootstrap-template-amoeba/ -->
                Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
            </div>
            </div>
        </footer><!-- End #footer -->

        <a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/jquery/jquery.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/jquery.easing/jquery.easing.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>
        <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="assets/vendor/venobox/venobox.min.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>

    </body>
</html>