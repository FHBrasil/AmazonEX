
function getVizuryId(){
	
	if($(location).attr('host').toLowerCase().search('dzarm') >= 0){
		return "VIZVRM1206";
	}
	
	if($(location).attr('host').toLowerCase().search('foryou') >= 0){		
		return "VIZVRM3168";
	}
	
	if($(location).attr('host').toLowerCase().search('kids') >= 0){		
		return "VIZVRM3167";
	}
	
	if($(location).attr('host').toLowerCase().search('puc') >= 0){		
		return "VIZVRM1205";
	}
	
	if($(location).attr('host').toLowerCase().search('hering') >= 0){		
		return "VIZVRM1195";
	}
	
}

(function() {
    try {
        var viz = document.createElement("script");
        viz.type = "text/javascript";
        viz.async = true;
        viz.src = ("https:" == document.location.protocol ?"https://ssl.vizury.com" : "http://www.vizury.com")+ "/analyze/pixel.php?account_id=" + getVizuryId();

        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(viz, s);
        viz.onload = function() {
            try {
                pixel.parse();
            } catch (i) {
            }
        };
        viz.onreadystatechange = function() {
            if (viz.readyState == "complete" || viz.readyState == "loaded") {
                try {
                    pixel.parse();
                } catch (i) {
                }
            }
        };
    } catch (i) {
    }
})();