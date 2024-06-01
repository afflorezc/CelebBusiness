<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

  <head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Celeb Business</title>
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
    <section id="contact" class="contact section-bg">
      <div class="container">

        <div class="section-title">
          <h2>Welcome back </h2>
          <p>Please log in into your account and help us to manage our clients business
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
            <form action="login" method="post" role="form" class="php-email-form"> 

              <div class="form-group">
                <input type="text" name="username" class="form-control" id="name" placeholder="User Name" required /> <!--data-rule="minlen:4" data-msg="Please enter your username" -->
                <div class="validate"></div>
              </div>

              <div class="form-group">
                <input type="password" name="password" class="form-control" id="name" placeholder="Password" required /> <!--data-rule="minlen:4" data-msg="Please enter your password" -->
                <div class="validate"></div>
              </div>

              <div class="mb-3">
                <!--<div class="loading">Loading</div>
                <div class="error-message"></div>-->
                <div class="message"></div>
              </div>
              <div class="text-center"><button type="submit">Log in</button></div>
            </form>
          </div>

        </div>

      </div>

    </section>

  </body>

   <!-- Vendor JS Files -->
   <script src="assets/vendor/jquery/jquery.min.js"></script>
   <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
   <script src="assets/vendor/jquery.easing/jquery.easing.min.js"></script>
   <!--<script src="assets/vendor/php-email-form/validate.js"></script>-->
   <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
   <script src="assets/vendor/venobox/venobox.min.js"></script>

   <!-- Template Main JS File -->
   <script src="assets/js/main.js"></script>
    
</html>