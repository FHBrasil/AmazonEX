ACC.updatebillingaddress = {
	bindCycleFocusEvent: function ()
	{
		$('#lastInTheForm').blur(function ()
		{
			$('#paymentDetailsForm [tabindex$="10"]').focus();
		})
	},

	updateBillingAddressForm: function ()
	{
		var newAddress = $('#differentAddress').attr("checked");
		if (newAddress)
		{
			$("#newBillingAddressFields").show("fast");
			$('input:hidden[name=differingBillingAddress]').attr('value', 'true');
		}
		else
		{
			$("#newBillingAddressFields").hide("fast");
			$('input:hidden[name=differingBillingAddress]').removeAttr('value');
		}
	}
}

$(document).ready(function ()
{
//	ACC.updatebillingaddress.updateBillingAddressForm();
	//$('#differentAddress').attr("checked",true);
	$("#newBillingAddressFields").hide();
	ACC.silentorderpost.displayStartDateIssueNum();

	if ($("#differentAddress").length > 0)
	{
		$("#differentAddress").click(function ()
		{
			ACC.updatebillingaddress.updateBillingAddressForm();
		})
	}
	else
	{
		$("#newBillingAddressFields :input").removeAttr('disabled');
	}

	$('#paymentDetailsForm [tabindex="1"]').change(function ()
	{
		ACC.silentorderpost.displayStartDateIssueNum();
	});

	ACC.updatebillingaddress.updateBillingAddressForm();
	ACC.updatebillingaddress.bindCycleFocusEvent();
});
