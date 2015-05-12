$.widget( 'hering.productPreview' , {
	
	// Default options.
	options: {
		// options
		contextPath: ACC.config.contextPath,
		colorSelector: '.cores li',
		sizeSelector: '.tamanhos li',
		imageSelector: 'img',
		loadingImageSrc: '/store/_ui/desktop/common/images/ajax-loader.gif',
		placeholder: 'http://placehold.it/115x154',
		activeColorSelector: '.cores li.active',
		activeSizeSelector: '.tamanhos li:has(.active)',
		currentStyle: '',
		currentSize: '',
		useOldImplementation: false,
		codeInputSelector: 'input[name="productCodePost"]',
		productUrlSelector: 'a',
		updateCode: false,
		updateUrl: false
		// events:
		// - loadDone: once when finished loading
		// - updateSizes: every time after color is selected
		// - variantChanged: every time after size is selected 
	},
	
	_create: function() {
		var self = this;
		
		if(this.options.useOldImplementation) {
			this._useOldImplementations();
		}

		this._loadImages().done(function() {
			self._trigger('loadDone', null, null);
			// this has to wait all preview data to be loaded to work properly
			self._fillCurrentSelection();
		});
		
//		$(this.element).find('.cores .variantSelectorHandler').click($.proxy(this._callback, this));
		$(this.element).find(this.options.colorSelector).click(function(e) {
			var code = self._getColorCode(this);
			self.options.currentStyle = code;
			var imageUrl = self._getImage(code);
			if(imageUrl == '') {
				return;
			}
			var img = $(self.element).find(self.options.imageSelector);
		
			var animation = function() {
				img.fadeOut(function(){
					img.attr("src", imageUrl).fadeIn();
				});
			};
			if(self._isPreloaded(imageUrl)) {
				animation();
			} else {
				var loadingImage = $(self.element).find('.loading');
				if(!loadingImage.length) {
					var left = Math.floor(img.width() / 2) - 16;
					var top = Math.floor(img.height() / 2) - 16;
					loadingImage = $('<img class="loading" style="position: absolute !important;'
							+ 'left: ' + left + 'px !important;'
							+ 'top: ' + top + 'px !important;'
							+ 'width: 32px !important;'
							+ 'height: 32px !important;" />');
				}
				loadingImage.attr('src', self.options.loadingImageSrc);
				img.after(loadingImage);
				loadingImage.show();
				
				self._preload(imageUrl, function(){
					$('.loading').remove();
					animation();
				});
			}
			self._trigger('updateSizes', e, {code: code, sizes: self._getSizes(code)});
		});
		
		$(this.element).find(this.options.sizeSelector).click(function(e) {
			var code = self._getSizeCode(this);
			var text = self._getSizeText(this);
			self.options.currentSize = code;
			var inStock = self._inStock(self.options.currentStyle, text);
			if(inStock) {
				var url = self._getUrl(code);
				if(self.options.updateCode) {
					$(self.element).find(self.options.codeInputSelector).val(code);
				}
				if(self.options.updateUrl) {
					$(self.element).find(self.options.productUrlSelector).attr("href", self.options.contextPath + url);
				}
				self._trigger("variantChanged", e, {code: code, url: url});
			}
		});
	},
	
	_getColorCode: function(colorElement) {
		return $(colorElement).data('variantcode');
	},
	
	_getSizeCode: function(sizeElement) {
		var sizeCode = $(sizeElement).data('variantcode');
		if(sizeCode) {
			return sizeCode;
		} else {
			var styleCode = this.options.currentStyle;
			var sizeText = $(sizeElement).text();
			var style = this._map[styleCode];
			if(style) {
				for(var index = 0; index < style.availableSizes.length; index++) {
					var size = style.availableSizes[index];
					if(size.size == sizeText){
						return size.code;
					}
				}
			}
		}
	},
	
	_getSizeText: function(sizeElement) {
		return $(sizeElement).text();
	},
	
	_getImage: function(code) {
		var entry = this._map[code];
		if(!entry) {
			return this.options.placeholder;
		} else {
			return entry.imageUrl;
		}
	},
	
	_getSizes: function(code) {
		var entry = this._map[code];
		if(!entry) {
			return [];
		} else {
			return entry.availableSizes;
		}
	},
	
	_inStock: function(styleCode, sizeText) {
		var style = this._map[styleCode];
		if(style) {
			for(var index = 0; index < style.availableSizes.length; index++) {
				var size = style.availableSizes[index];
				if(size.size == sizeText){
					return true;
				}
			}
		}
		return false;
	},

	_getUrl: function(code) {
		var entry = this._map[code];
		if(entry) {
			return style.url;
		}
		for(var styleCode in this._map) {
			if(this._map.hasOwnProperty(styleCode)) {
				var style = this._map[styleCode];
				for(var index = 0; index < style.availableSizes.length; index++) {
					var size = style.availableSizes[index];
					if(size.code == code){
						return size.url;
					}
				}
			}
		}		
	},

	// === loads information === \\
	_getProductCodes: function() {
		var productCodes = [];
		$(this.element).find(this.options.colorSelector).each(function() {
			productCodes.push($(this).data('variantcode'));
		});
		return productCodes;
	},
	
	_loadImages: function() {
		var self = this;
		var productCodes = this._getProductCodes();
		var ajaxRequests = [];
		if (productCodes.length > 0) 
		{
			$.ajaxSetup({
				cache: false,
				traditional: true
			});
			
			var i, j, temparray, chunk = 6;
			for (i = 0, j = productCodes.length; i < j; i += chunk) 
			{
				temparray = productCodes.slice(i, i + chunk);
				
				ajaxRequests.push(
				$.ajax({
					url : self.options.contextPath + '/search/previews',
					data: {products: temparray},
					context : document.body
				}).done(function(json) {
					self._appendMap(JSON.parse(json));
				})
				);
			}
		}
		return $.when.apply(null, ajaxRequests);
	},
	
	_fillCurrentSelection: function() {
		var selectedCode = $(this.element).find(this.options.codeInputSelector).val();
		if(selectedCode) {
			var found = false;
			for(var styleCode in this._map) {
				if(styleCode == selectedCode) {
					this.options.currentStyle = styleCode;
					found = true;
				} else if(this._map.hasOwnProperty(styleCode)) {
					var style = this._map[styleCode];
					for(var index = 0; index < style.availableSizes.length; index++) {
						var size = style.availableSizes[index];
						if(size.code == selectedCode){
							this.options.currentStyle = styleCode;
							this.options.currentSize = size.code;
							found = true;
							break;
						}
					}
				}
				if(found)
					break;
			}
		}
		// current style
		var selectedColor = $(this.element).find(this.options.colorSelector).filter(this.options.activeColorSelector);
		if(selectedColor.length) {
			this.options.currentStyle = this._getColorCode(selectedColor);
		}
		this._trigger('updateSizes', null, {code: this.options.currentStyle, sizes: this._getSizes(this.options.currentStyle)});
		// current size
		var selectedSize = $(this.element).find(this.options.sizeSelector).filter(this.options.activeSizeSelector);
		if(selectedSize.length) {
			this.options.currentSize = this._getSizeCode(selectedSize);
		}
	},
	
	_map: {},
	_appendMap: function(map) {
		for(var key in map) {
			if(map.hasOwnProperty(key)) {
				this._map[key] = map[key];
			}
		}
	},
	
	_preloaded: [],
	_preload: function(src, callback) {
		var self = this;
		var imageComponents = [];
		if(!self._isPreloaded(src)) {
			self._preloaded.push(src);
			var img = $('<img/>')[0];
			imageComponents.push(img);
			img.src = src;
		}
		$(imageComponents).load(callback);
	},
	_isPreloaded: function(src) {
		return this._preloaded.indexOf(src) >= 0;
	},
	
//	_callback: function(e) {
//		var code = $(this).data("productCode");
//		this.element.find(":hidden[name='productListCodePost']");
//		alert('asdf');
//	}
	
	// old implementation
	_useOldImplementations: function() {
		this._getColorCode = this._oldGetColorCode;
		this._getSizeCode = this._oldGetSizeCode;
		this._getProductCodes = this._oldGetProductCodes;
		this._loadImages = this._oldLoadImages;
		this._getImage = this._oldGetImage;
		this._getSizes = this._oldGetSizes;
		this._inStock = this._oldInStock;
		this._getUrl = this._oldGetUrl;
	},
	
	_oldGetColorCode: function(colorElement) {
		return $(colorElement).find(":hidden[name='codeData']").val().split("-")[1];
	},
	
	_sizeCache: {},
	_oldGetSizeCode: function(sizeElement) {
		var self = this;
		var size = $(sizeElement).text();
		var baseCode = this._productImages[this.options.currentStyle][3];
		var color = this.options.currentStyle.substring(5);
		var sku = '';
		$.ajax({
			url : self.options.contextPath + '/search/urlfromsize/' + baseCode + '/' + size + '/' + color,
			async: false,
			cache: true,
			success: function(json) {
				if (json != '') {
					var data = JSON.parse(json);
					sku = data['sku'];
					var url = data['url'];
					self._sizeCache[sku] = url;
				}
			}
		});
		return sku;
	},
	
	_oldGetUrl: function(code) {
		return this._sizeCache[code];
	},
	
	_oldGetProductCodes: function() {
		var productCodes = [];
		$(this.element).find(this.options.colorSelector).first().each(function() {
			productCodes.push($(this).find(":hidden[name='codeData']").val().split("-")[0]);
		});
		return productCodes;
	},
	
	_oldLoadImages: function () {
		var self = this;
		var productCodes = this._getProductCodes();
		var ajaxRequests = [];
		if (productCodes.length > 0)
		{
			$.ajaxSetup({
				cache: false
			});
			
			var i, j, temparray, chunk = 6;
			for (i = 0, j = productCodes.length; i < j; i += chunk) 
			{
				temparray = productCodes.slice(i, i + chunk);
				
				ajaxRequests.push(
				$.ajax({
					url : self.options.contextPath + '/search/images',
					data: {products: temparray},
					context : document.body
				})
				.done(function(json) {
					self._oldAppendJson(json);
				})
				);
			}
		}
		return $.when.apply(null, ajaxRequests);
	},
	_jsonAppended: '',
	_productImages: '',
	_oldAppendJson: function(json) {
		if(json != '') {
			if( this._jsonAppended != '' )
			{
				this._jsonAppended = this._jsonAppended.replace( '}', '' );
				json = json.replace( '{', '' );
				this._jsonAppended += ',' + json;
			}
			else
			{
				this._jsonAppended = json;
			}
	
			if (this._jsonAppended != '') {
				var imageList = JSON.parse(this._jsonAppended);
				this._productImages = imageList;
			} else {
				this._jsonAppended = json;
			}
		}
	},
	_oldGetImage: function(code) {
		var data = this._productImages[code];
		if(!data) {
			return this.options.placeholder;
		} else {
			return data[0];
		}
	},
	_oldGetSizes: function(code) {
		var data = this._productImages[code];
		if(!data) {
			return [];
		} else {
			return data[2];
		}
	},
	_oldInStock: function(styleCode, sizeText) {
		var style = this._productImages[styleCode];
		if(style) {
			for(var index = 0; index < style[2].length; index++) {
				var size = style[2][index];
				if(size == sizeText){
					return true;
				}
			}
		}
		return false;
	}
});
