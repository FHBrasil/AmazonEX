function getInstagramPhotos() {
    // script de requisição de fotos do Instagram
    jQuery.ajax({
        type: "GET",
        dataType: "jsonp",
        cache: false,
        url: "https://api.instagram.com/v1/users/215480059/media/recent/?access_token=187043677.a3d96df.61742d785d714ac2891f56be05f95757",
        success: function(data) {
            var instaList = $(".instagram-list li");
            for (var i = 0; i < instaList.length; i++) {
                var selectedItem = instaList.eq(i);

                selectedItem.append('<a href="#" title="" target="_blank"><img src="" alt=""></a>')
                var itemAnchor = selectedItem.find('a');
                var itemImage = selectedItem.find('img');

                itemAnchor.attr({
                    "href": data.data[i].link,
                    "title": data.data[i].caption.text
                });
                itemImage.attr({
                    "src": data.data[i].images.low_resolution.url,
                    "width": selectedItem.css("width"),
                    "height": selectedItem.css("height"),
                    "alt": data.data[i].caption.text
                });
            }
        }
    });
}
	
