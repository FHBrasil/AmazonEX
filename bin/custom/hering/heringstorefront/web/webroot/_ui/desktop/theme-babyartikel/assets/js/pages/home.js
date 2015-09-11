// init
$(document).ready(function(){
   
        // validação de formulários (em script.js)
    hering.form.validate();
    getInstagramPhotos(); // em script.js
   
    //coloca transparencia no canto esquerdo : tira o no-before
    var wtela = $(window).width();
    $('.product-infinite-wrapper').each(function(){
        var wcar = $(this).width()
        if(wcar==wtela){
                $(this).removeClass('no-before');
        }
    });
   
    $('.fancybox[href*="modal-"]').fancybox({maxWidth: 700, minWidth: 320, padding: 20 });
 

   
});