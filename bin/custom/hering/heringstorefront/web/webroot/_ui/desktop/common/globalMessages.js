GlobalMessages = {
	globalMessagesDiv : $('div#globalMessages'),
	
	
	infoMessage : function(message, isFlash) {
		$(GlobalMessages.globalMessagesDiv).empty();
		var infoDiv = $('<div></div>').prop('class', 'alert positive');
		$(infoDiv).text(message);
		$(GlobalMessages.globalMessagesDiv).append(infoDiv);
		if(isFlash) {
			(function(fadeAwayDiv) {
				setTimeout(function() {
					$(fadeAwayDiv).fadeOut(750);
				}, 8000);
			})(infoDiv);
		}
	},
	
	
	warningMessage : function(message, isFlash) {
		$(GlobalMessages.globalMessagesDiv).empty();
		var warningDiv = $('<div></div>').prop('class', 'alert neutral');
		$(warningDiv).text(message);
		$(GlobalMessages.globalMessagesDiv).append(warningDiv);
		if(isFlash) {
			(function(fadeAwayDiv) {
				setTimeout(function() {
					$(fadeAwayDiv).fadeOut(750);
				}, 8000);
			})(warningDiv);
		}
	},
	
	
	errorMessage : function(message, isFlash) {
		$(GlobalMessages.globalMessagesDiv).empty();
		var messages = message;
		if(!(message instanceof Array)) {
			messages = new Array();
			messages.push(message);
		}
		for(var i = 0; i < messages.length; i++) {
			var errorDiv = $('<div></div>');
			$(errorDiv).prop('class', 'alert negative');
			$(errorDiv).prop('id', 'errorGlobalMessageDiv' + i);
			$(errorDiv).text(messages[i]);
			$(GlobalMessages.globalMessagesDiv).append(errorDiv);
			if(isFlash) {
				(function(fadeAwayDiv) {
					setTimeout(function() {
						$(fadeAwayDiv).fadeOut(750);
					}, 8000);
				})(errorDiv);
			}
		}
	}
}