$(document).ready(function() {
	/* Applies bonus points */
	$('form#paymentDetailsForm').find('.applyBP').click(function() {
		var url = $(this).siblings('input[type=hidden][name=applyBPUrl]').val();
		var points = $('input[type=text][name=points]').val();
		if(points) {
	        var pointsFormatted = points.replace('.','').replace(',','.');
			$.ajax({
				async : true,
					url : url +  pointsFormatted,
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						if(data) {
							var htmlSubTotals = data.subtotalsAmount.productsAmount;
							htmlSubTotals += data.subtotalsAmount.freightAmount;
							htmlSubTotals += data.subtotalsAmount.discountAmount;
							var htmlDeliveryMethodSelector = data.deliveryMethodSelector;
							$('div#CreditCardInstalment').html(data.instalmentCreditCard);
							$('div#CreditCardInstalment').find("#numero-parcelas").prop("name", 'creditCardForms[0].instalment');
							$('div#CreditCardInstalment1').html(data.instalmentCreditCard1);
							$('div#CreditCardInstalment1').find("#numero-parcelas1").prop("name", 'creditCardForms[1].instalment');
							$('div#checkoutSubTotals').html(htmlSubTotals);
							$('div#checkoutPaymentTotals').html(data.paymentTotalAmount);
							$('section#shipping-methods').html(htmlDeliveryMethodSelector);
							var cardValue = getTotalAmount().toString();
							cardValue = cardValue.replace('.',',');
							$("#cardValue").val(cardValue);
							$("#cardValue1").val("0");
							$("#cardValue").change();
							showToaster(data.message);
							updateUsedPoints(data);
						}
					},
					complete : function() {
						bindDeliveryMethod_onChange();
					}
			});
		}
	});

	function updateUsedPoints(data) {
		if(data) {
			var usedBonusPoints = data.usedBonusPoints.replace('.',',');
			var discountFormatted = (data.usedBonusPoints / 100).toFixed(2).replace('.',',');
			$('#points').val(usedBonusPoints);
			$('#bonusPointsUsedLabel').text("Desconto atual: " + usedBonusPoints + " pontos = R$ " + discountFormatted);
		}
	}

	if($('#points-option-label')[0].checked) {
		$("#other-options div.points-option-field").show();
	} else {
		$("#other-options div.points-option-field").hide();
	}
	// Exibe content de pontos
	$("#points-option-label").click(function() {
		$("#other-options div.points-option-field").stop(0,1).slideToggle(200);
	});
});