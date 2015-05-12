(function() {
    try {
        var lastClick = false;
        var viz = document.createElement("script");
        viz.type = "text/javascript";
        viz.async = true;
        viz.src = ("https:" == document.location.protocol ? "https://ssl.vizury.com"
                : "http://www.vizury.com")
                + "/analyze/pixel.php?account_id=VIZVRM1206";
        var s = document.getElementById("scriptVizury");
        s.parentNode.insertBefore(viz, s);
        viz.onload = function() {
            try {
                pixel.parse(lastClick);
            } catch (i) {
            }
        };
        viz.onreadystatechange = function() {
            if (viz.readyState == "complete" || viz.readyState == "loaded") {
                try {
                    pixel.parse(lastClick);
                } catch (i) {
                }
            }
        };
    } catch (i) {
    }
}());