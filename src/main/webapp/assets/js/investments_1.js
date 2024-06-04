jQuery(document).ready(function($) {
    "use strict";

    $('#investment-type').change(function() {
        
      let this_select = $('#investment-type');
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
            break;
        case "Investment Fund":
            $(".cdt-parameters").each(function() {
                $(this).slideUp();
                $(this).attr("required", false);
            });
            $(".portfolios").each(function() {
                $(this).slideDown();
                $(this).attr("required", true);
            });
            break;
        default:
            $(".cdt-parameters").each(function() {
                $(this).slideUp();
                $(this).attr("required", false);
            });
            $(".portfolios").each(function() {
                $(this).slideUp();
                $(this).attr("required", false);
            });
            break;
        }
      return false;
    });
  
  });