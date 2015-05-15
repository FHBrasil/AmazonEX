<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<script>
	$(document).ready(function() { 
		$('.submit_form').click(function() {
			$(".error").hide();
		    var hasError = false;
		    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		    //var emailblockReg =/^([\w-\.]+@(?!gmail.com)(?!yahoo.com)(?!hotmail.com)([\w-]+\.)+[\w-]{2,4})?$/;
		 
		    var emailaddressVal = $(".emailaddress").val();
		    if(emailaddressVal == '') {
		      $(".submit_form").after('<span class="error" style="color:red">E-Mail invalido!</span>');
		      hasError = true;
		    }
		 
		    else if(!emailReg.test(emailaddressVal)) {
		      $(".submit_form").after('<span class="error" style="color:red">E-Mail invalido!</span>');
		      hasError = true;
		    }
		 
		    /*else if(!emailblockReg.test(emailaddressVal)) {
		      $(".emailaddress").after('<span class="error">No yahoo, gmail or hotmail emails.</span>');
		      hasError = true
		    }*/ 
		 
		    if(hasError == true) { return false; }
		 
		});
		
		$('.submit_form_notify').click(function() {
			$(".error").hide();
		    var hasError = false;
		    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		    //var emailblockReg =/^([\w-\.]+@(?!gmail.com)(?!yahoo.com)(?!hotmail.com)([\w-]+\.)+[\w-]{2,4})?$/;
		 	
		    var nameVal = $(".nameaddressnotify").val();
		    if(nameVal == '') {
		      $(".submit_form_notify").after('<span class="error" style="color:red">Digite seu nome!</span>');
		      hasError = true;
		      return false;
		    }
		    
		    var emailaddressVal = $(".emailaddressnotify").val();
		    if(emailaddressVal == '') {
		      $(".submit_form_notify").after('<span class="error" style="color:red">E-Mail invalido!</span>');
		      hasError = true;
		    }
		 
		    
		    else if(!emailReg.test(emailaddressVal)) {
		      $(".submit_form_notify").after('<span class="error" style="color:red">E-Mail invalido!</span>');
		      hasError = true;
		    }
		 
		    /*else if(!emailblockReg.test(emailaddressVal)) {
		      $(".emailaddress").after('<span class="error">No yahoo, gmail or hotmail emails.</span>');
		      hasError = true
		    }*/ 
		 
		    if(hasError == true) { return false; }
		 
		});
		<c:choose>
			<c:when test="${pageType == 'ORDERCONFIRMATION'}">
				(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
					ga('create', 'UA-1100733-8', 'auto');
				ga('send', 'pageview');
				ga('require','ecommerce','ecommerce.js');
				ga('ecommerce:addTransaction',{ 
					id: '${orderData.code}',
						affiliation: 'hering.com.br',
					revenue: '${orderData.totalPrice.value}', 
					shipping: '${orderData.deliveryCost.value}', 
					tax: '${orderData.totalTax.value}'
				});
				<c:forEach items="${orderData.deliveryOrderGroups}" var="orderGroup">
					<c:forEach items="${orderGroup.entries}" var="entry">
						<c:forEach items="${entry.product.categories }" var="cat">
							<c:choose>
								<c:when test="${categories == ''}">
									<c:set var="categories" value="${cat.name}" />
								</c:when>
								<c:when test="${categories != ''}">
									<c:set var="categories" value="${categories}, ${cat.name}" />
								</c:when>
							</c:choose>
						</c:forEach>
						ga('ecommerce:addItem',{
							id: '${orderData.code}',
							sku: '${entry.product.code}',
							name: '${entry.product.name}',
							category: '${categories}',
							price: '${entry.basePrice.value }',
							quantity: '${entry.quantity }'});
					</c:forEach>
				</c:forEach>
				ga('ecommerce:send');
			</c:when>
		</c:choose>

		maskTelefone($('input[id="address.phone1"]'));
		maskTelefone($('input[id="address.phone2"]'));
		fctValidaData($('input[id="guest.birthday"]'));
		fctValidaData($('input[id="profile.birthday"]'));
		fctValidaData($('input[id="address.birthday"]'));
		fctValidaData($('input[id="register.birthday"]'));

		$(document).on('focus', 'input[id="address.postcode"]', function() {
			onlyNumbers($(this));
			$(this).attr('maxlength','8');
		});
		$(document).on('focus', 'input[id="profile.rgIe"]', function() {
			onlyNumbers($(this));
		});
		$(document).on('focus', 'input[id="register.rgIe"]', function() {
			onlyNumbers($(this));
		});
		
		$(document).on('blur', 'input[id="address.postcode"]', function() {
			findCEP($(this).val(), $(this));
		});
		
		$(document).on('focus', 'input[id="cep"]', function() {
			onlyNumbers($(this));
			$(this).attr('maxlength','8');
		});
		$(document).on('focus', 'input[id="register.postcode"]', function() {
			$(this).mask("99999999");
		});
		$(document).on('focus', 'input[id="register.birthday"]', function() {
			$(this).mask("99/99/9999");
		});
		$(document).on('focus', 'input[id="address.birthday"]', function() {
			$(this).mask("99/99/9999");
		});
		$(document).on('focus', 'input[id="profile.birthday"]', function() {
			$(this).mask("99/99/9999");
		});
		$(document).on('focus', 'input[id="guest.birthday"]', function() {
			$(this).mask("99/99/9999");
		});
		
		$(document).on('blur', 'input[id="register.birthday"]', function() {
			fctValidaData($(this));
		});
		$(document).on('blur', 'input[id="address.birthday"]', function() {
			fctValidaData($(this));
		});
		$(document).on('blur', 'input[id="profile.birthday"]', function() {
			fctValidaData($(this));
		});
		$(document).on('blur', 'input[id="guest.birthday"]', function() {
			fctValidaData($(this));
		});
		
		function fctValidaData(obj)
		{
			if(obj.val() == undefined || obj.val() == null || obj.val() == ''){
				return false;
			}

		    var data = obj.val();
		    var dia = data.substring(0,2)
		    var mes = data.substring(3,5)
		    var ano = data.substring(6,10)
		    
		    var anoBissexto = ((ano % 4 == 0) || (ano % 100 == 0) || (ano % 400 == 0)) == true ? true : false; 
		    
		    if(dia > 31){
		    	obj.css("border", "1px solid #c90400");
		    	return false;
		    }else if((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30){
		    	obj.css("border", "1px solid #c90400");
		    	return false;
		    }else if(mes == 2){
		    	if ((!anoBissexto && dia > 28) || dia > 29){
		    		obj.css("border", "1px solid #c90400");
		    		return false;
		    	}
		    }else if(mes > 12){
		    	obj.css("border", "1px solid #c90400");
		    	return false;
		    }else if(ano < 1900){
		    	obj.css("border", "1px solid #c90400");
		    	return false;
		    }
		    
		    
		    
		    if(ano.substring(0,1) > 0){
		    	//Criando um objeto Date usando os valores ano, mes e dia.
			    var novaData = new Date(ano,(mes-1),dia);
			    var today = new Date();
			    
		    	if(novaData > today){
		    		obj.css("border", "1px solid #c90400");
		    		return false;
		    	}
			 
			    var mesmoDia = parseInt(dia,10) == parseInt(novaData.getDate());
			    var mesmoMes = parseInt(mes,10) == parseInt(novaData.getMonth())+1;
			    var mesmoAno = parseInt(ano) == parseInt(novaData.getFullYear());
			 
			    if (!((mesmoDia) && (mesmoMes) && (mesmoAno)))
			    {
			    	obj.css("border", "1px solid #c90400");
			        return false;
			    }  
			    else{
					obj.css("border", "1px solid black");
				}
			    return true;
		    }
		    else{
		    	obj.css("border", "1px solid #c90400");
		    	return false;
		    }
		}
		
		
		$(document).on('focus', 'input[id="cardNumber"]', function() {
			$(this).attr('maxlength','16');
			onlyNumbers($(this));
		});
		
		$(document).on('focus', 'input[id="cv2Number"]', function() {
			onlyNumbers($(this));
		});
		
		$(document).on('focus', 'input[id="profile.cpfcnpj"]', function() {
		     onlyNumbers($(this));
		     if($("input[id=radiopj]").is(":checked")) {
			 	$(this).mask('00000000000000', {reverse: true});
		     } else {
		    	$(this).mask('00000000000', {reverse: true}); 
		     }
		});
		$(document).on('focus', 'input[id="register.cpfcnpj"]', function() {
			 onlyNumbers($(this));
			 if($("input[id=radiopj]").is(":checked")) {
				 	$(this).mask('00000000000000', {reverse: true});
			     } else {
			    	$(this).mask('00000000000', {reverse: true}); 
			     }
		});
		
		$(document).on('focus', 'input[id="forgottenPwd.cpfCnpj"]', function() {
			onlyNumbers($(this));
			$(this).mask('00000000000000', {reverse: true});
		});
		
		$(document).on('focus', 'input[id="guest.cpfcnpj"]', function() {
			onlyNumbers($(this));
			$(this).mask('00000000000000', {reverse: true});
		});
		
		$(document).on('blur', 'input[id="register.cpfcnpj"]', function() {
		    if(!Validar($(this).val())){
		    	$(this).css("border", "1px solid red");
		    	return false
		    }
		    else{$(this).css("border", "1px solid black");}
		    $(this).mask('00000000000000', {reverse: true});
		});
		$(document).on('blur', 'input[id="guest.cpfcnpj"]', function() {
		    if(!Validar($(this).val())){
		    	$(this).css("border", "1px solid red");
		    	return false
		    }
		    else{$(this).css("border", "1px solid black");}
		    $(this).mask('00000000000000', {reverse: true});
		});
		$(document).on('blur', 'input[id="profile.cpfcnpj"]', function() {
		    if(!Validar($(this).val())){
		    	$(this).css("border", "1px solid red");
		    	return false
		    }
		    else{$(this).css("border", "1px solid black");}
		    $(this).mask('00000000000000', {reverse: true});
		});
		$(document).on('blur', 'input[id="forgottenPwd.cpfcnpj"]', function() {
		    if(!Validar($(this).val())){
		    	$(this).css("border", "1px solid red");
		    	return false
		    }
		    else{$(this).css("border", "1px solid black");}
		    $(this).mask('00000000000000', {reverse: true});
		});
		
		
		
		$(document).on('blur', 'input[id="cardNumber"]', function() {
		    var hasError = false;
		    var card = GetCardType( $(this).val() );
		    if(!Mod10($(this).val())) {
		      $(this).css("border", "1px solid red");
		      hasError = true;
		    }
		    else{$(this).css("border", "1px solid black");}
		    if( card != "" ){
				 $("#creditCardsPayment input[value='" + card + "']").attr('checked',true);
			     $("#cardType").val(card);
		    }
		});
		
		$(document).on('blur', 'input[id="debitPayment.accountNumber"]', function() {
		    var hasError = false;
		    var card = GetCardType( $(this).val() , 'electron');
		    if(!Mod10($(this).val())) {
		      $(this).css("border", "1px solid red");
		      $(this).focus();
		      hasError = true;	      
		    }
		    else{$(this).css("border", "1px solid black");}
		    if( card != "" ){ 
				 $("#debitDetails input[value='" + card + "']").attr('checked',true);
			     $("#cardType").val(card);
			}	
		    if(hasError == true) { return false; }
		});
	});

	function init(){
		$('input[id="cv2Number"]').val("");
		$('input[id="cardNumber"]').val("");
	}
	
	function GetCardType(number, electron)
    {            
        if (number.match( new RegExp("^4")) != null) {
        	if(electron) {
        		return "visa-electron";
        	}
        	return "visa";
        }

        if (number.match( new RegExp("^(34|37)") ) != null) {
            return "amex";
        }

        if (number.match( new RegExp("^5[1-5]") ) != null) {
            return "master";
        }

        if (number.match( new RegExp("^3(6|8)|(00|01|02|03|04|05)") ) != null) {
            return "diners";
        }

        return "";
	}
	
	function Mod10(ccNumb) {  // v2.0
		var valid = "0123456789"  // Valid digits in a credit card number
		var len = ccNumb.length;  // The length of the submitted cc number
		var iCCN = parseInt(ccNumb);  // integer of ccNumb
		var sCCN = ccNumb.toString();  // string of ccNumb
		sCCN = sCCN.replace (/^\s+|\s+$/g,'');  // strip spaces
		var iTotal = 0;  // integer total set at zero
		var bNum = true;  // by default assume it is a number
		var bResult = false;  // by default assume it is NOT a valid cc
		var temp;  // temp variable for parsing string
		var calc;  // used for calculation of each digit

		// Determine if the ccNumb is in fact all numbers
		for (var j=0; j<len; j++) {
		  temp = "" + sCCN.substring(j, j+1);
		  if (valid.indexOf(temp) == "-1"){bNum = false;}
		}

		// if it is NOT a number, you can either alert to the fact, or just pass a failure
		if(!bNum){
		  /*alert("Not a Number");*/bResult = false;
		}

		// Determine if it is the proper length 
		if((len == 0)&&(bResult)){  // nothing, field is blank AND passed above # check
		  bResult = false;
		} else{  // ccNumb is a number and the proper length - let's see if it is a valid card number
		  if(len >= 14){  // 14 - Diners 15 or 16 for Amex or V/MC
		    for(var i=len;i>0;i--){  // LOOP throught the digits of the card
		      calc = parseInt(iCCN) % 10;  // right most digit
		      calc = parseInt(calc);  // assure it is an integer
		      iTotal += calc;  // running total of the card number as we loop - Do Nothing to first digit
		      i--;  // decrement the count - move to the next digit in the card
		      iCCN = iCCN / 10;                               // subtracts right most digit from ccNumb
		      calc = parseInt(iCCN) % 10 ;    // NEXT right most digit
		      calc = calc *2;                                 // multiply the digit by two
		      // Instead of some screwy method of converting 16 to a string and then parsing 1 and 6 and then adding them to make 7,
		      // I use a simple switch statement to change the value of calc2 to 7 if 16 is the multiple.
		      switch(calc){
		        case 10: calc = 1; break;       //5*2=10 & 1+0 = 1
		        case 12: calc = 3; break;       //6*2=12 & 1+2 = 3
		        case 14: calc = 5; break;       //7*2=14 & 1+4 = 5
		        case 16: calc = 7; break;       //8*2=16 & 1+6 = 7
		        case 18: calc = 9; break;       //9*2=18 & 1+8 = 9
		        default: calc = calc;           //4*2= 8 &   8 = 8  -same for all lower numbers
		      }                                               
		    iCCN = iCCN / 10;  // subtracts right most digit from ccNum
		    iTotal += calc;  // running total of the card number as we loop
		  }  // END OF LOOP
		  if ((iTotal%10)==0){  // check to see if the sum Mod 10 is zero
		    bResult = true;  // This IS (or could be) a valid credit card number.
		  } else {
		    bResult = false;  // This could NOT be a valid credit card number
		    }
		  }
		}
		  return bResult; // Return the results
	}
	
	function onlyNumbers(field){
		
		field.keydown(function(event) {
			
	    	if ( event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9) {
	    		
	    		return;
	    	}
	    	else {
	    		
	    		if (!((event.keyCode > 95 && event.keyCode < 106) || (event.keyCode > 47 && event.keyCode < 58))) {
	    			event.preventDefault();	
	    		}	
	    	}
	    });
	}

	function findCEP(postcode, element) {
		var parentForm = $(element).closest('form') 
				? 'form#' + $(element).closest('form').prop('id') : '';
	    if(postcode != ""){
	    	$('#loadingAddress').show();
	    	 $.ajax({
	    		url : '/store/checkout/multi/get-by-zipcode', /* URL que sera chamada */ 
	            type : 'GET', /* Tipo da requisicao */ 
	            data: 'zipcode='+postcode,/* dado que sera enviado via POST */
	            dataType: 'json', /* Tipo de transmissao */
	            
	             success: function(data) {
	            	$('#loadingAddress').hide();
	            	if(data != null && data != '') {
	            		var obj = jQuery.parseJSON(data);
	            		if(obj){
	            			var street = obj.logradouro;
		            		var city = obj.cidade;
		            		var state = "BR-"+obj.estado;
		            		var neighborhood = obj.bairro;
		            		$(parentForm + ' input[id="address.district"]')
		            				.val(neighborhood);
		            		$(parentForm + ' input[id="address.line1"]')
		            				.val(street);
		            		$(parentForm + ' select[id="address.state"]')
		            				.val(state);
		            		$(parentForm + ' input[id="address.townCity"]')
		            				.val(city);
	            		}
					}
	    		} 
	    		});
	        }
   }
	
	function localJsonpCallback(json) {
        if (!json.Error) {
        	alert(json.Message);
        }
        else {
        	alert(json.Message);
            alert(json.Message);
        }
    } 
		
	function Validar(theCPF) 
	{ 
	  theCPF = theCPF.replace('.','');
	  theCPF = theCPF.replace('.','');
	  theCPF = theCPF.replace('-','');
	  theCPF = theCPF.replace('/','');
	  if (theCPF == "") 
	  { 
	    //alert("Campo inv�lido. � necess�rio informar o CPF ou CNPJ"); 
	    //theCPF.focus(); 
	    return (false); 
	  } 
	  if (((theCPF.length == 11) && (theCPF == 11111111111) || (theCPF == 22222222222) || (theCPF == 33333333333) || (theCPF == 44444444444) || (theCPF == 55555555555) || (theCPF == 66666666666) || (theCPF == 77777777777) || (theCPF == 88888888888) || (theCPF == 99999999999) || (theCPF == 00000000000))) 
	  { 
	    //alert("CPF/CNPJ inv�lido."); 
	    //theCPF.focus(); 
	    return (false); 
	  } 


	  if (!((theCPF.length == 11) || (theCPF.length == 14))) 
	  { 
	    //alert("CPF/CNPJ inv�lido."); 
	    //theCPF.focus(); 
	    return (false); 
	  } 

	  var checkOK = "0123456789"; 
	  var checkStr = theCPF; 
	  var allValid = true; 
	  var allNum = ""; 
	  for (i = 0;  i < checkStr.length;  i++) 
	  { 
	    ch = checkStr.charAt(i); 
	    for (j = 0;  j < checkOK.length;  j++) 
	      if (ch == checkOK.charAt(j)) 
	        break; 
	    if (j == checkOK.length) 
	    { 
	      allValid = false; 
	      break; 
	    } 
	    allNum += ch; 
	  } 
	  if (!allValid) 
	  { 
	    //alert("Favor preencher somente com d�gitos o campo CPF/CNPJ."); 
	    //theCPF.focus(); 
	    return (false); 
	  } 

	  var chkVal = allNum; 
	  var prsVal = parseFloat(allNum); 
	  if (chkVal != "" && !(prsVal > "0")) 
	  { 
	    //alert("CPF zerado !"); 
	    //theCPF.focus(); 
	    return (false); 
	  } 

	if (theCPF.length == 11) 
	{ 
	  var tot = 0; 

	  for (i = 2;  i <= 10;  i++) 
	    tot += i * parseInt(checkStr.charAt(10 - i)); 

	  if ((tot * 10 % 11 % 10) != parseInt(checkStr.charAt(9))) 
	  { 
	    //alert("CPF/CNPJ inv�lido."); 
	    //theCPF.focus(); 
	    return (false); 
	  } 
	  
	  tot = 0; 
	  
	  for (i = 2;  i <= 11;  i++) 
	    tot += i * parseInt(checkStr.charAt(11 - i)); 

	  if ((tot * 10 % 11 % 10) != parseInt(checkStr.charAt(10))) 
	  { 
	    //alert("CPF/CNPJ inv�lido�."); 
	    //theCPF.focus(); 
	    return (false); 
	  } 
	} 
	else 
	{ 
	  var tot  = 0; 
	  var peso = 2; 
	  
	  for (i = 0;  i <= 11;  i++) 
	  { 
	    tot += peso * parseInt(checkStr.charAt(11 - i)); 
	    peso++; 
	    if (peso == 10) 
	    { 
	        peso = 2; 
	    } 
	  } 

	  if ((tot * 10 % 11 % 10) != parseInt(checkStr.charAt(12))) 
	  { 
	    //alert("CPF/CNPJ inv�lido."); 
	    //theCPF.focus(); 
	    return (false); 
	  } 
	  
	  tot  = 0; 
	  peso = 2; 
	  
	  for (i = 0;  i <= 12;  i++) 
	  { 
	    tot += peso * parseInt(checkStr.charAt(12 - i)); 
	    peso++; 
	    if (peso == 10) 
	    { 
	        peso = 2; 
	    } 
	  } 

	  if ((tot * 10 % 11 % 10) != parseInt(checkStr.charAt(13))) 
	  { 
	    //alert("CPF/CNPJ inv�lido."); 
	    //theCPF.focus(); 
	    return (false); 
	  } 
	} 
	  return(true); 
	} 
	
	function maskTelefone(field){
			if(field.val() != undefined &&
				field.val() != '' && 
				field.val().length > 10){
				field.mask("(99) 99999-9999")  
	        .keydown(function () {
	            var $elem = $(this);
	            var tamanhoAnterior = this.value.replace(/\D/g, '').length;
	            
	            setTimeout(function() { 
	                var novoTamanho = $elem.val().replace(/\D/g, '').length;
	                if (novoTamanho !== tamanhoAnterior) {
	                    if (novoTamanho === 11) {  
	                        $elem.unmask();  
	                        $elem.mask("(99) 99999-9999");  
	                    } else if (novoTamanho === 10) {  
	                        $elem.unmask();  
	                        $elem.mask("(99) 9999-9999?9");  
	                    }
	                }
	            }, 1);
	        });
		}else if(field.val() != undefined){
			field.mask("(99) 9999-9999?9")  
		        .keydown(function () {
		            var $elem = $(this);
		            var tamanhoAnterior = this.value.replace(/\D/g, '').length;
		            
		            setTimeout(function() { 
		                var novoTamanho = $elem.val().replace(/\D/g, '').length;
		                if (novoTamanho !== tamanhoAnterior) {
		                    if (novoTamanho === 11) {  
		                        $elem.unmask();  
		                        $elem.mask("(99) 99999-9999");  
		                    } else if (novoTamanho === 10) {  
		                        $elem.unmask();  
		                        $elem.mask("(99) 9999-9999?9");  
		                    }
		                }
		            }, 1);
		        });
		}
	}
</script>
