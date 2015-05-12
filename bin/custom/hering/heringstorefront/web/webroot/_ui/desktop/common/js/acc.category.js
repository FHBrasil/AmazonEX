var paginationCat = {
	
	currentPage: 0,
	paginationInfo: "",
	changeUrlValue: "",
	isLastPage: false,
	
	setGetData: function( key, value )
	{
		paginationCat.postData[ key ] = value;
	},
	
	changeUrl: function( realPage )
	{
		href = window.location.href;
		
		if( href.indexOf('page=') > -1 )
		{
			href = href.replace( "page=" + paginationCat.currentPage , "page=" + ( realPage ) );
			href = href.replace( "&amp;page=0", "" );
		}
		else
		{
			href += "&amp;page=" + realPage;
		}
		
		paginationCat.changeUrlValue = href;
	},
	
	getCurrentPageNumber: function()
	{
		if( window.location.href.indexOf('page=') > -1 )
		{
			part = window.location.href.split('page=');
			paginationCat.currentPage = parseInt( part[ 1 ] ); 
		}
	},
	
	insertPagination : function(param)
	{
		var pageModificator = 0;		
		var pageNumberModificator = 0;
		var currentModificator = 1;

		if (param == "afterTwoPages") {
			pageModificator = 1;		
			pageNumberModificator = 1;
		} else if (param == "lastPage") {
			pageModificator = 1;		
			pageNumberModificator = 2;
			currentModificator = 0;
		}	

		paginationCat.getCurrentPageNumber();
		href = window.location.href;	
		
		currentPageNumber = paginationCat.currentPage;
		page = currentPageNumber + 1;
		
		loadedPage = page + ACC.category.maxPagesLoaded - pageModificator;
		loadedPageNumber = currentPageNumber + ACC.category.maxPagesLoaded - pageNumberModificator;
		
		activePage = page -1;
		activePageNumber = currentPageNumber - 1 ;
		
		if( window.location.href.indexOf('page=') > -1 ){}
		else{ parcial = href.split('page='); }
		
		newUrl = href.replace( "page=" + currentPageNumber , "page=" + ( activePage ) );
		newUrl = newUrl.replace( "&amp;page=0", "" );
		
		$( ".paginationBar" ).show();
		
		if (param != "lastPage") {
			$( ".pagination #" + ( loadedPageNumber ) ).html( "<strong>" + loadedPage + "</strong>" );
			$( ".pagination #" + currentPageNumber ).html( '<a href="' + newUrl + '">' + page + '</a>' );
		}

		if (param == "afterThreePages"){
			if( currentPageNumber != 0 ) paginationCat.rollOverPagination( currentPageNumber, page );
			else
			{
				paginationCat.changeUrl( loadedPage );
				$( ".next" ).remove();
				$( ".paginationBar ul" ).append('<li class="next"><a href="'
					+ paginationCat.changeUrlValue +'">next</a></li>');
			}
		}
		
		if (param == "afterTwoPages"){
			paginationCat.changeUrl( loadedPage );
			$( ".next" ).remove();
		}

		if (param == "lastPage"){
			$( ".next" ).remove();
		}
	},
	
	rollOverPagination: function( loadedPageNumber, page )
	{
        toAdd = 0;
        var nextButtonPage = loadedPageNumber + 3;
        if( ( loadedPageNumber + 1 ) <= paginationCat.paginationInfo.numberOfPages ) toAdd = 1;
        if( ( loadedPageNumber + 2 ) <= paginationCat.paginationInfo.numberOfPages ) toAdd = 2;
        
        if( toAdd > 0 )
        {
        	for( i = 1, r = 2; toAdd >= i; i++, r-- )
        	{
        		newPage = page + i + 2;
            	newloadedPageNumber = loadedPageNumber + i + 2;
                
            	if ( document.getElementById(newloadedPageNumber) == null && 
            		 newloadedPageNumber < paginationCat.paginationInfo.numberOfPages )
            	{
	            	paginationCat.changeUrl( newloadedPageNumber );
	            	
	            	$( ".paginationBar ul" ).append( '<li id="' + ( newloadedPageNumber ) 
	        		+ '" ><a href="' + paginationCat.changeUrlValue + '">' 
	        		+ ( newPage ) + "</a></li>" );
	            	
	            	$( ".pagination #" + ( loadedPageNumber - r ) ).remove();
        		}
        	}
        	
        	$( ".next" ).remove();
        	
        	/*! Controle de aparecimento do botão 'next' e de sua url */
        	if ( newloadedPageNumber <= paginationCat.paginationInfo.numberOfPages ) {
        		paginationCat.changeUrl( nextButtonPage );
	        	
	        	$( ".paginationBar ul" ).append('<li class="next"><a href="'
				+ paginationCat.changeUrlValue +'">next</a></li>');
        	}
        }
	}
	
};
ACC.category = {
	 infiniteScrollingConfig : {
	        offset : '100%'
	    },
	firstPage : 0,
	currentPage : 0,
    numberOfPages : Number.MAX_VALUE,
    $showMoreResultsArea : $('#footer'),
    searchPath : $("#currentPath").attr("data-current-path"),
    baseQuery : $("#sort_form1 input[name='q']").val() || "",
    isShowListEnabled : $(".productList-li").length > 0 ? true : false,
    maxPagesLoaded : 2,
    pagesLoaded : 0,
		    		
    init : function() {
        
        if ($("#spinner").length > 0) {
            with (ACC.category) {
                bindShowMoreResults($showMoreResultsArea);
            }
        }
        	
    },
    
    triggerLoadMoreResults : function() 
    {
    	if (paginationCat.isLastPage == false){
	    	
	        if (
	        	( ACC.category.currentPage < ACC.category.numberOfPages ) &&
	        	( ACC.category.pagesLoaded < ACC.category.maxPagesLoaded )
	    	) {
	            ACC.common.showSpinnerById('spinner');
	            if (parseInt(ACC.category.currentPage) != -1 )
	            {
	            	ACC.category.loadMoreResults(parseInt(ACC.category.currentPage) + 1);
	            }
	            else 
	            {
	            	ACC.common.hideSpinnerById('spinner');
	            }
	        }
	        
		    if( ACC.category.numberOfPages > 1 ){
		    	
		    	/*! Inserção de paginação quando o número total de páginas é igual a 2 */
		    	if( 
		        	(ACC.category.numberOfPages == 2) && 
		        	(ACC.category.pagesLoaded + 1 == ACC.category.maxPagesLoaded) 
		    	){
		    		paginationCat.insertPagination("afterTwoPages");
		    		paginationCat.insertPagination("lastPage");
		    		paginationCat.isLastPage = true;
		    		
		        } 
		    	
		    	/*! Inserção normal de paginação, quando a página selecionada é a antepenúltima página */
		    	else if(parseInt(ACC.category.currentPage) == -2)
		    	{
		    		paginationCat.insertPagination("afterThreePages");
		    		paginationCat.insertPagination("lastPage");
		    		paginationCat.isLastPage = true;
		    	}
		    	
		        /*! Inserção de paginação, quando a página selecionada é a penúltima página */
		        else if(parseInt(ACC.category.currentPage) == -1) 
	        	{
		        	paginationCat.insertPagination("afterTwoPages");
	        	}
		    	
		        /*! Inserção normal de paginação */
		        else if( ACC.category.pagesLoaded == ACC.category.maxPagesLoaded )
		    	{
		        	paginationCat.insertPagination("afterThreePages");
		    	}
		        
		    } 
		    else if ( ACC.category.numberOfPages == 1 )
		    {
		    	paginationCat.isLastPage = true;
		    }
		    
	    	ACC.category.pagesLoaded++;
	    	
    	} 
    	
    },
    
    loadMoreResults : function(page) {
    	
    	if (paginationCat.isLastPage == false) {	
			$.ajax({
                url : ACC.category.searchPath + "/results",
                data : {
                    q : ACC.category.baseQuery,
                    page : page,
                    isShowListEnabled : ACC.category.isShowListEnabled != ""
                },
                success : function(data) {
                    if (data.pagination !== undefined) {
                    	
                        if ( $("#resultsList").length > 0 && data.productListerHtml != "" ) {/*! Product List */
                        	
                        	data.productListerHtml = data.productListerHtml.replace( /class="/g, 'class="hiddenProduct ' );
                                                           
                            $("#resultsList").append(data.productListerHtml);
                            ACC.product.bindToAddToCartForm();
                            
    	                    paginationCat.paginationInfo = data.pagination;
    	                    
                            $("#resultsList").find(".hiddenProduct").animate({ opacity: 1 }, 2000 );
                        }
                        
                        /*! Verifica se a última página foi selecionada pelo usuário e mostra a paginação para a última página */
                        if(parseInt(data.pagination.currentPage) == parseInt(data.pagination.numberOfPages) )
                        {
                        	paginationCat.isLastPage = true;
                        	$( ".paginationBar" ).show();
                        	
                        }
                        
                        /*! Verifica se a penúltima página foi selecionada pelo usuário */
                        else if( parseInt(data.pagination.currentPage) == parseInt(data.pagination.numberOfPages) -1 &&
                        		ACC.category.firstPage == parseInt(data.pagination.numberOfPages) -2 )
                        {
                        	ACC.category.currentPage = -1;
                        	
                        }
                        
                        /*! Verifica se a antepenúltima página foi selecionada pelo usuário */
                        else if( parseInt(data.pagination.currentPage) == parseInt(data.pagination.numberOfPages) -1 &&
                        		ACC.category.firstPage == parseInt(data.pagination.numberOfPages) -3 )
                        {
                        	ACC.category.currentPage = -2;
                        	
                        }
                        
                        else
                        {
                        	ACC.category.currentPage = parseInt(data.pagination.currentPage);
                        
                        }
                        
                        ACC.category.numberOfPages = parseInt(data.pagination.numberOfPages);
                        ACC.common.hideSpinnerById('spinner');
                        ACC.category.$showMoreResultsArea
                                .waypoint(ACC.category.infiniteScrollingConfig); /*! reconfigure
                                                                                     waypoint
                                                                                     eventhandler */
                                                            
                        ACC.category.bindQuickViewOnInfiniteScroll(page);
                        
                        ACC.category.bindColorCarouselOnInfiniteScroll();
                        
                        $('.productGridItem ')
                                .hover(
                                        function() {
                                            $(this)
                                                    .find(
                                                            '.SuperSearchDesignColorsPriceBuy')
                                                    .slideDown("fast");
                                        },
                                        function() {
                                            $(this)
                                                    .find(
                                                            '.SuperSearchDesignColorsPriceBuy')
                                                    .slideUp("fast");
                                        });
                    } else {
                        ACC.common.hideSpinnerById('spinner');
                    }
                }
            })
    	}
    },
    
    updatePaginationInfos : function(paginationInfo) {
        ACC.category.currentPage = parseInt(paginationInfo.currentPage);
        ACC.category.numberOfPages = parseInt(paginationInfo.numberOfPages);
    },
    
    updateCurrentPage : function(currentPage) {
    	ACC.category.currentPage = parseInt(currentPage);
    },
    
    scrollingHandler : function(event, direction) {
        if (direction === "down") {
            ACC.category.triggerLoadMoreResults();
        }
    },
    
    bindShowMoreResults : function(showMoreResultsArea) {
        showMoreResultsArea.live("click", function() {
            ACC.category.triggerLoadMoreResults();
        });
        
        showMoreResultsArea.waypoint(ACC.category.scrollingHandler,
                ACC.category.infiniteScrollingConfig);
    },
    
    /*! Faz a ligação do Infinite Scroll com o Quick View */
    bindQuickViewOnInfiniteScroll : function(currentPage) {
    	ACC.images.preloadImagesAgain(currentPage);
    },
    
    /*! Faz a ligação do Carrossel de Cores de produtos da Grid com o Infinite Scroll */
    bindColorCarouselOnInfiniteScroll : function() {
    	homeHoverProdutos.insertCarousel();
    },
    
    loadAccPreloadImagesJs : function() {
    	ACC.images.loadImages();
    },
    
    loadHomeHoverProdutosJs : function() {
    	homeHoverProdutos.insertCarousel();
    },
    
}


$(document).ready(function() {
	ACC.category.loadAccPreloadImagesJs();
	ACC.category.loadHomeHoverProdutosJs();
    ACC.category.init();
    paginationCat.getCurrentPageNumber();
	ACC.category.updateCurrentPage(paginationCat.currentPage);
	ACC.category.firstPage = paginationCat.currentPage;
});
