<?xml version="1.0" encoding="UTF-8" ?>
<%@page
	import="com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page
	import="com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Test console</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="../static/test/js/main.js"></script>
</head>

<body>
	<h1>PayPal extension testing console</h1>
	<%
		Map<String, String[]> params = request.getParameterMap();
			if (params.size() > 0) {
	%>
	<h3>
		Parameters passed <a href="console.jsp">(clean)</a>
	</h3>
	<%
		for (final Map.Entry<String, String[]> param : params.entrySet()) {
	%>
	<pre><%=param.getKey()%>=<%=param.getValue()[0]%></pre>
	<%
		}
	%>
	<%
		}
	%>
	<h3>Express Checkout Execution Flow</h3>
	<form>
		<table width="100%" border="1" cellpadding="3" cellspacing="0"
			bordercolor="#EAEAFF">

			<tr>
				<td width="1%"><input type="button" value="SetExpressCheckout"
					onclick="
						send('app=' + appName
                        + '&c=setExpressCheckout'
                        + '&amountCurrency=' + $('#amountCurrency').val()
                        + '&amountShipping=' + $('#amountShipping').val()
                        + '&itemName=' + $('#itemName').val()
                        + '&itemNumber=' + $('#itemNumber').val()
                        + '&itemDescription=' + $('#itemDescription').val()
                        + '&amountSubtotal=' + $('#amountSubtotal').val()
                        + '&amountTax=' + $('#amountTax').val()
                        + '&paymentAction=' + $('#paymentAction').val()                        
                        + '&solutionType=' + ($('#accountOptional').is(':checked')?'SOLE':'MARK')
                        /*+ '&isTest=true'*/
                        ,
						'setExpressCheckout', setExpressCheckoutCallback);" /><br />
					<input type="button" value="Approve payment"
						onclick="redirectButtClick()" />
				</td>
				<td>
					<div>
						Currency: <input type="text" id="amountCurrency" value="USD"
							maxlength="15" />
					</div>
					<div>
						Shipping: <input type="text" id="amountShipping" value=".1"
							maxlength="15" />
					</div>
					<div>
						Item name: <input type="text" id="itemName" value="Test Item Name"
							maxlength="255" />
					</div>
					<div>
						Item Number: <input type="text" id="itemNumber" value="TestItemNumber"
							maxlength="32" />
					</div>
					<div>
						Item Description: <input type="text" id="itemDescription" value="Short item description"
							maxlength="255" />
					</div>
					<div>
						Item Total: <input type="text" id="amountSubtotal" value=".5"
							maxlength="15" />
					</div>
					<div>
						PaymentAction: <input type="text" id="paymentAction"
							value="SALE/AUTHORIZATION/ORDER" maxlength="60" />
					</div>
					<div>
						Tax: <input type="text" id="amountTax" value=".1" maxlength="15" />
					</div>

					<div>
						<input id="accountOptional" name="accountOptional" type="checkbox"
							value="true"> AccountOptional</input>
					</div> <pre id="setExpressCheckout"></pre>
				</td>
			</tr>

			<tr>
				<td><input type="button" value="GetExpressCheckoutDetails"
					onclick="send('app=' + appName
					+ '&c=getExpressCheckoutDetails'
					+ '&token=' + $('#token').val()
					,
					'GetExpressCheckoutDetails');" /><br /></td>
				<td>
					<div>
						Token: <input type="text" id="token" value="${param.token}"
							maxlength="60" />
					</div> <pre id="GetExpressCheckoutDetails"></pre>
				</td>
			</tr>

			<tr>
				<td><input type="button" value="DoExpressCheckoutPayment"
					onclick="send('app=' + appName
					+ '&c=doExpressCheckoutPayment'
					+ '&token=' + $('#token2').val()
					+ '&payerId=' + $('#payerId').val()
					+ '&paymentAction=' + $('#decpPaymentAction').val()
					,
					'DoExpressCheckoutPayment', getTransactionDetailsCallback);" /><br /></td>
				<td>
					<div>
						Token: <input type="text" id="token2" value="${param.token}"
							maxlength="60" />
					</div>
					<div>
						PayerID: <input type="text" id="payerId" value="${param.PayerID}"
							maxlength="60" />
					</div>
					<div>
						Payment Action: <input type="text" id="decpPaymentAction"
							value="SALE/AUTHORIZATION/ORDER" maxlength="60" />
					</div> <pre id="DoExpressCheckoutPayment"></pre>
				</td>
			</tr>
		</table>
	</form>


	<h3>Back-Office API</h3>
	<form>
		<table width="100%" border="1" cellpadding="3" cellspacing="0"
			bordercolor="#EAEAFF">

			<tr>
				<td width='1%'><input type="button" value="DoAuthorization"
					onclick="send('app=' + appName
					+ '&c=DoAuthorization'
					+ '&transactionId=' + $('#daTransactionId').val()
					+ '&amount=' + $('#daAmount').val()
					+ '&amountCurrency=' + $('#daAmountCurrency').val()
					,
					'DoAuthorization');" /><br /></td>
				<td>

					<div>
						TransactionId: <input type="text" id="daTransactionId"
							value="${param.transactionId}" maxlength="60" />
					</div>
					<div>
						Amount: <input type="text" id="daAmount" value=".2" maxlength="60" />
					</div>
					<div>
						Currency: <input type="text" id="daAmountCurrency" value="USD"
							maxlength="60" />
					</div> <pre id="DoAuthorization"></pre>
				</td>
			</tr>

			<tr>
				<td width='1%'><input type="button" value="DoReauthorization"
					onclick="send('app=' + appName
					+ '&c=DoReauthorization'
					+ '&transactionId=' + $('#drTransactionId').val()
					+ '&amount=' + $('#drAmount').val()
					+ '&amountCurrency=' + $('#drAmountCurrency').val()
					,
					'DoReauthorization');" /><br /></td>
				<td>

					<div>
						TransactionId: <input type="text" id="drTransactionId"
							value="${param.transactionId}" maxlength="60" />
					</div>
					<div>
						Amount: <input type="text" id="drAmount" value=".3" maxlength="60" />
					</div>
					<div>
						Currency: <input type="text" id="drAmountCurrency" value="USD"
							maxlength="60" />
					</div> <pre id="DoReauthorization"></pre>
				</td>
			</tr>

			<tr>
				<td width='1%'><input type="button" value="DoVoid"
					onclick="send('app=' + appName
					+ '&c=DoVoid'
					+ '&authorizationId=' + $('#authId').val()
					+ '&note=' + $('#note').val()
					+ '&msgSubId=' + $('#msg').val()
					,
					'DoVoid');" /><br /></td>
				<td>
					<div>
						*AuthorizationId: <input type="text" id="authId" value=""
							maxlength="60" />
					</div>
					<div>
						Note: <input type="text" id="note" value="" maxlength="60" />
					</div>
					<div>
						MsgSubId: <input type="text" id="msg" value="" maxlength="60" />
					</div> <pre id="DoVoid"></pre>
				</td>
			</tr>



			<tr>
				<td width='1%'><input type="button" value="DoCapture"
					onclick="send('app=' + appName
					+ '&c=DoCapture'
					+ '&authorizationId=' + $('#authorizationIdDoCapture').val()
					+ '&amount=' + $('#amountDoCapture').val()
					+ '&amountCurrency=' + $('#amountCurrencyDoCapture').val()
					+ '&completeType=' + $('#completeTypeDoCapture').val()
					+ '&invoiceId=' + $('#invoiceIdDoCapture').val()
					+ '&note=' + $('#note').val()
					+ '&softDescriptor=' + $('#softDescriptorDoCapture').val()
					+ '&merchantStoreDetails=' + $('#merchantStoreDetailsDoCapture').val()
					+ '&msgSubId=' + $('#msgSubIdDoCapture').val()
					+ '&storeId=' + $('#storeIdDoCapture').val()
					+ '&terminalId=' + $('#terminalIdDoCapture').val()
					
					,
					'DoCapture');" /><br /></td>
				<td>
					<div>
						*AuthorizationId: <input type="text" id="authorizationIdDoCapture"
							value="" maxlength="60" />
					</div>
					<div>
						*AmountValue: <input type="text" id="amountDoCapture" value="0.00"
							maxlength="60" />
					</div>
					<div>
						*AmountCurrency: <input type="text" id="amountCurrencyDoCapture"
							value="USD" maxlength="60" />
					</div>
					<div>
						*CompleteType: <input type="text" id="completeTypeDoCapture"
							value="NotComplete/Complete" maxlength="60" />
					</div>
					<div>
						InvoiceId: <input type="text" id="invoiceIdDoCapture" value=""
							maxlength="60" />
					</div>
					<div>
						Note: <input type="text" id="noteDoCapture" value=""
							maxlength="60" />
					</div>
					<div>
						SoftDescriptor: <input type="text" id="softDescriptorDoCapture"
							value="" maxlength="60" />
					</div>
					<div>
						MerchantStoreDetails: <input type="text"
							id="merchantStoreDetailsDoCapture" value="" maxlength="60" />
					</div>
					<div>
						MsgSubId: <input type="text" id="msgSubIdDoCapture" value=""
							maxlength="60" />
					</div>
					<div>
						StoreId: <input type="text" id="storeIdDoCapture" value=""
							maxlength="60" />
					</div>
					<div>
						TerminalId: <input type="text" id="terminalIdDoCapture" value=""
							maxlength="60" />
					</div> <pre id="DoCapture"></pre>
				</td>
			</tr>

			<tr>
				<td width='1%'><input type="button" value="RefundTransaction"
					onclick="send('app=' + appName
					+ '&c=RefundTransaction'
					+ '&transactionId=' + $('#rtTransactionId').val()
					,
					'RefundTransaction');" /><br /></td>
				<td>
					<div>
						TransactionId: <input type="text" id="rtTransactionId"
							value="${param.transactionId}" maxlength="60" />
					</div> <pre id="RefundTransaction"></pre>
				</td>
			</tr>
			<tr>
				<td width='1%'><input type="button" value="TransactionSearch"
					onclick="send('app=' + appName
					+ '&c=TransactionSearch'
					+ '&startDate=' + encodeURIComponent($('#startDateForSearch').val())
					
					+  ($('#endDateForSearchOpt').is(':checked') ?  '&endDate=' + $('#endDateForSearch').val(): '')
					+  ($('#transactionIdForSearchOpt').is(':checked') ?  '&transactionId=' + $('#transactionIdForSearch').val(): '')
					+  ($('#payerEmailForSearchOpt').is(':checked') ?  '&payerEmail=' + $('#payerEmailForSearch').val(): '')
					+  ($('#statusForSearchOpt').is(':checked') ?  '&status=' + $('#statusForSearch').val(): '')
					+  ($('#payerNameForSearchOpt').is(':checked') ?  '&payerName=' + $('#payerNameForSearch').val(): '')
					+  ($('#receiverForSearchOpt').is(':checked') ?  '&receiver=' + $('#receiverForSearch').val(): '')
					+  ($('#profileIDForSearchOpt').is(':checked') ?  '&profileId=' + $('#profileIDForSearch').val(): '')
					+  ($('#receiptIDForSearchOpt').is(':checked') ?  '&receiptId=' + $('#receiptIDForSearch').val(): '')
					+  ($('#invoiceIDForSearchOpt').is(':checked') ?  '&invoiceId=' + $('#invoiceIDForSearch').val(): '')
					+  ($('#cardNumberForSearchOpt').is(':checked') ?  '&cardNumber=' + $('#cardNumberForSearch').val(): '')
					+  ($('#auctionItemNumberForSearchOpt').is(':checked') ?  '&auctionItemNumber=' + $('#auctionItemNumberForSearch').val(): '')
					+  ($('#transactionClassForSearchOpt').is(':checked') ?  '&transactionClass=' + $('#transactionClassForSearch').val(): '')
					+  ($('#currencyCodeForSearchOpt').is(':checked') ?  '&amountCurrency=' + $('#currencyCodeForSearch').val(): '')
					+  ($('#amountForSearchOpt').is(':checked') ?  '&amount=' + $('#amountForSearch').val(): '')
					,
					'TransactionSearch');" /><br /></td>
				<td>
					<div>
						StartDate: <input type="text" id="startDateForSearch"
							value="2013-07-24 16:30:00 GMT+3:00" maxlength="60" />
					</div>
					<div>
						<input id="endDateForSearchOpt" name="endDateForSearchOpt"
							type="checkbox" value="false" /> EndDate: <input type="text"
							id="endDateForSearch" value="" maxlength="60" />
					</div>

					<div>
						<input id="transactionIdForSearchOpt"
							name="transactionIdForSearchOpt" type="checkbox" value="false" />
						TransactionId: <input type="text" id="transactionIdForSearch"
							value="" maxlength="60" />
					</div>
					<div>
						<input id="payerEmailForSearchOpt" name="payerEmailForSearchOpt"
							type="checkbox" value="false" /> PayerEmail: <input type="text"
							id="payerEmailForSearch" value="" maxlength="60" />
					</div>
					<div>
						<input id="statusForSearchOpt" name="statusForSearchOpt"
							type="checkbox" value="false" /> Status: <input type="text"
							id="statusForSearch" value="" maxlength="60" />
					</div>
					<div>
						<input id="payerNameForSearchOpt" name="payerNameForSearchOpt"
							type="checkbox" value="false" /> PayerName: <input type="text"
							id="payerNameForSearch" value="" maxlength="60" />
					</div>
					<div>
						<input id="receiverForSearchOpt" name="receiverForSearchOpt"
							type="checkbox" value="false" /> Receiver: <input type="text"
							id="receiverForSearch" value="" maxlength="60" />
					</div>
					<div>
						<input id="profileIDForSearchOpt" name="profileIDForSearchOpt"
							type="checkbox" value="false" /> ProfileID: <input type="text"
							id="profileIDForSearch" value="" maxlength="60" />
					</div>
					<div>
						<input id="receiptIDForSearchOpt" name="receiptIDForSearchOpt"
							type="checkbox" value="false" /> ReceiptID: <input type="text"
							id="receiptIDForSearch" value="" maxlength="60" />
					</div>
					<div>
						<input id="invoiceIDForSearchOpt" name="invoiceIDForSearchOpt"
							type="checkbox" value="false" /> InvoiceID: <input type="text"
							id="invoiceIDForSearch" value="" maxlength="60" />
					</div>
					<div>
						<input id="cardNumberForSearchOpt" name="cardNumberForSearchOpt"
							type="checkbox" value="false" /> CardNumber: <input type="text"
							id="cardNumberForSearch" value="" maxlength="60" />
					</div>
					<div>
						<input id="auctionItemNumberForSearchOpt"
							name="auctionItemNumberForSearchOpt" type="checkbox"
							value="false" /> AuctionItemNumber: <input type="text"
							id="auctionItemNumberForSearch" value="" maxlength="60" />
					</div>
					<div>
						<input id="transactionClassForSearchOpt"
							name="transactionClassForSearchOpt" type="checkbox" value="false" />
						TransactionClass: <input type="text"
							id="transactionClassForSearch" value="" maxlength="60" />
					</div>
					<div>
						<input id="currencyCodeForSearchOpt"
							name="currencyCodeForSearchOpt" type="checkbox" value="false" />
						CurrencyCode: <input type="text" id="currencyCodeForSearch"
							value="" maxlength="60" />
					</div>
					<div>
						<input id="amountForSearchOpt" name="amountForSearchOpt"
							type="checkbox" value="false" /> Amount: <input type="text"
							id="amountForSearch" value="" maxlength="60" />
					</div> <pre id="TransactionSearch"></pre> <pre id="TransactionSearch"></pre>
				</td>
			</tr>

			<tr>
				<td width='1%'><input type="button"
					value="GetTransactionDetails"
					onclick="send('app=' + appName
                    + '&c=GetTransactionDetails'
                    + '&transactionId=' + $('#gtdTransactionId').val()
					,
					'GetTransactionDetails',getTransactionDetailsCallback);" /><br /></td>
				<td>
					<div>
						TransactionId: <input type="text" id="gtdTransactionId"
							value="${param.transactionId}" maxlength="60" />
					</div> <pre id="GetTransactionDetails"></pre>
				</td>
			</tr>


		</table>
	</form>
	<h3>Helpers</h3>
	<form>
		<table width="100%" border="1" cellpadding="3" cellspacing="0"
			bordercolor="#EAEAFF">

			<tr>
				<td width="1%"> <input type="button"
						value="AutoApprovePayment"
						onclick="send('app=' + appName
	                        + '&c=autoAccept'
	                        + '&token=' + paypal.data['response']['token']
	                        ,
	                        'autoAccept');" />
				</td>
				<td>
					<div><pre id="autoAccept"></pre></div>
				</td>
			</tr>
			<tr>
				<td width='1%'><input type="button" value="DoPropUpdate"
					onclick="send('app=' + appName
						+ '&c=DoPropUpdate'
					,
					'DoPropUpdate');" /></td>
				<td>Update properties in DB (override old props and add new props from
					paypal.properties file)<br /><pre id="DoPropUpdate"></pre></td>
			</tr>
			<tr>
				<td width='1%'><input type="button" value="DoPropRewrite"
					onclick="send('app=' + appName
						+ '&c=DoPropRewrite'
					,
					'DoPropRewrite');" /></td>
				<td>Rewrite properties in DB (clear old props and add new props from
					paypal.properties file)<br /><pre id="DoPropRewrite"></pre></td>
			</tr>
			<tr>
				<td width='1%'><input type="button" value="MakeRandomOperations"
					onclick="send('app=' + appName
						+ '&c=MakeRandomOperations'
					,
					'MakeRandomOperations');" /></td>
				<td>
					<div></div>
					<div><pre id="MakeRandomOperations"></pre></div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
