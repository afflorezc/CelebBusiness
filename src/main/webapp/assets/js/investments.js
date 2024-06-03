jQuery(document).ready(function($) {
    "use strict";

    $('#investment').change(function() {
        
      let this_select = $(this);
      let value = this_select.val();
      switch(value){
        case "CDT":
            $(".cdt-parameters").each(function() {
                $(this).slideDown();
                $(this).attr("required", true);
            });
            $(".portfolios").each(function() {
                $(this).slideUp();
                $(this).attr("required", false);
            });
        case "Investment Fund":
            $(".cdt-parameters").each(function() {
                $(this).slideUp();
                $(this).attr("required", false);
            });
            $(".portfolios").each(function() {
                $(this).slideDown();
                $(this).attr("required", true);
            });
        default:
            $(".cdt-parameters").each(function() {
                $(this).slideUp();
                $(this).attr("required", false);
            });
            $(".portfolios").each(function() {
                $(this).slideUp();
                $(this).attr("required", false);
            });
        }
      return false;
    });
  
  });