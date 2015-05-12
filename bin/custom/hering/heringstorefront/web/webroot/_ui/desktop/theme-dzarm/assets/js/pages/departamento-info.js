//*****************************************
// ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! 
// ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! 
// Ver comentário no método de mesmo nome
// em scripts.js
// Este método sobrescreve o HTML do 
// infinite scroll para a página de busca.
// Cada página tem um HTML diferente.
// Utilizar método $.ajax() conforme 
// exemplificado no script.js e não este
// deferred.
//*****************************************
function infiniteScrollLoadProducts() {
    var defer = $.Deferred();
    window.setTimeout(function(){
    	var pageHTML = '';
    	for (var i = 0; i < 8; i++){
    		pageHTML+= '<div class="product"><div class="photo"><img src="images/produto-0'+((i%3)+1)+'.jpg" width="237"></div><div class="info"><div class="info-white"><h2>Camisa Jeans Manga Longa masculina com Lavagem Diferenciada</h2><div class="selos"><ul><li class="frete">Frete Grátis</li><li class="promocao">Promoção</li></ul></div><div class="precos"><s>De: R$229,99</s><strong>Por: R$199,99</strong><p>5x de <b>R$39,99</b></p></div></div><div class="cores"><h3>Cores:</h3><ul class="bxslider-cores"><li class="laranja"></li><li class="verde"></li><li class="azul"></li><li class="roxo"></li></ul></div><div class="tamanhos"><h3>Tamanhos:</h3><ul><li><a href="#">P</a></li><li><a href="#">M</a></li><li><a href="#">G</a></li><li><a href="#">XG</a></li></ul></div><a href="#" class="add btn btn-cta">Adicionar a sacola</a><a href="#" class="detalhes">Veja mais detalhes do produto</a></div></div>';
    	}
        defer.resolve(pageHTML);
    }, 2000);
    return defer.promise();

}