<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@page import="java.util.List"%>
<%@page import="com.afflorezc.model.BankAccount"%>
<%@page import="com.afflorezc.model.Portfolio" %>
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

            <section id="contact" class="contact section-bg">
                <div class="container">
      
                  <div class="section-title">
                    <h2>Account selection</h2>
                    <p>
                        Please select the account of interest and start your business.
                    </p>
                  </div>
      
                  <div class="row">
      
                      <div class="col-lg-4 col-md-6">
                          <div class="contact-about">
                          <h3>Celeb Business</h3>
                          <p>The experts are waiting for you. Please follow us. links to our official
                              social media accounts
                          </p>
                          <div class="social-links">
                              <a href="#" class="twitter"><i class="icofont-twitter"></i></a>
                              <a href="#" class="facebook"><i class="icofont-facebook"></i></a>
                              <a href="#" class="instagram"><i class="icofont-instagram"></i></a>
                              <a href="#" class="linkedin"><i class="icofont-linkedin"></i></a>
                          </div>
                          </div>
                      </div>
      
                    <div class="col-lg-5 col-md-12">
                      <form action="open_investment" method="post" role="form" class="php-email-form"> 

                        <select class="form-control investment" name="investment" id="investment" >
                            <option value="">Select the type of inversion</option>
                            <option value="CDT">CDT</option>
                            <option value="Investment Fund">Investment Fund</option>
                            
                        </select>

                        <select class="form-control user-accounts" name="user-accounts" id="user-accounts" >
                            <option value="">Select your Bank account for funding the inversion</option>
                            <% List<BankAccount> accounts = (List<BankAccount>) request.getAttribute("accounts");
                                for(BankAccount account:accounts){ %>
                            <option value= '<%= account.getAccountNumber() %>'></option>
                            <% } %>
                        </select>

                        <select class="form-control portfolios" name="portfolios" id="portfolios" >
                            <option value=""> Select the fund of your interest</option>
                            <% List<Portfolio> portfolios = (List<Portfolio>) request.getAttribute("portfolios-min-inversion");
                                for(Portfolio portfolio:portfolios){ %>
                            <option value= '<%= portfolio.getPortfolioName() %>'></option>
                            <% } %>
                        </select>
                        <input type="number" class="form-control investment-value" name="investment" id="investment" placeholder="Investment amount" required/>

                        <input type="date" class="form-control cdt-parameters" name="due-date" id="due-date">

                        <div class="mb-3">
                          <!--<div class="loading">Loading</div>
                          <div class="error-message"></div>-->
                            <div class="text-center">
                              <% if(request.getAttribute("message")!= null){ %>
                                <%= request.getAttribute("message")%> 
                              <% } %>
                            </div>
                        </div>
                        <div class="text-center"><button type="submit">Open Investment</button></div>
                      </form>
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
        <!--<script src="assets/vendor/php-email-form/validate.js"></script>-->
        <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="assets/vendor/venobox/venobox.min.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>
        <script src="assets/js/investments.js"></script>

    </body>
</html>