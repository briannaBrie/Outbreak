$(document).ready(function (){
    //after change in the dropdown reload the page
   $("#locales").change(function (){
      var selectedOption = $('#locales').val();
      if(selectedOption !=''){
          window.location.replace('?language'+selectedOption);
      }
   });
});