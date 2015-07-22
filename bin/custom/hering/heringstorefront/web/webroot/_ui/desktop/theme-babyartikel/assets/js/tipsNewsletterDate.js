$(function() {
  $('#tipsNewsletterDate').on('input', function() {
	  if($(this).val().match(/^\d{2}\.\d{2}\.\d{4}$/g)) {
		  $('#tipsNewsletterCheckbox').prop('disabled', false);
	  } 
      else {
    	  $('#tipsNewsletterCheckbox').prop('disabled', true);
      };
  });
});