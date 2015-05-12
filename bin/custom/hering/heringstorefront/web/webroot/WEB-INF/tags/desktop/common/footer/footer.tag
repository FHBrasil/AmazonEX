<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="newsletter" tagdir="/WEB-INF/tags/desktop/newsletter" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<%-- FOOTER DZARM STORE --%>
<c:if test="${themeName == 'black'}">

	<div id="footer" class="footer">
		<div class="contentFooter">
			<dl class="institucional">
				<dt>Institucional</dt> 	      
				<dd><a href="/store/pt/sobre-a-dzarm">Sobre a Dzarm</a></dd> 	      
				<dd><a href="/store/pt/politica-de-privacidade">Pol&iacute;tica de Privacidade</a></dd> 	      
				<dd><a href="/store/pt/cia-hering">Cia Hering </a></dd> 	      
				<dd><a href="/store/pt/sustentabilidade">Sustentabilidade</a></dd> 	      
				<dd><a href="/store/pt/trabalhe-conosco">Trabalhe Conosco</a></dd> 	      
				<dd><a href="/store/pt/store-finder-google">Nossas Lojas</a></dd> 	    
			</dl> 	    
			<dl class="ajudaSuporte"><dt>Ajuda e suporte</dt> 	      
				<dd><a href="/store/pt/entrega">Entrega</a></dd> 	      
				<dd><a href="/store/pt/troca-e-devolucao">Troca e devolu&ccedil;&atilde;o</a></dd> 	      
				<dd><a href="/store/pt/pagamento">Pagamento</a></dd> 	      
				<dd><a href="/store/pt/seguranca">Seguran&ccedil;a</a></dd> 	      
				<dd><a href="/store/pt/my-account">Meu cadastro</a></dd> 	      
				<dd><a href="/store/pt/my-account/orders">Meus pedidos</a></dd> 	      
				<dd><a href="javascript:void(0);" class="ai">Central de atendimento</a></dd> 	    
			</dl> 	    
			<dl class="conhecaTambem"> 	    	
				<dt>Outras Marcas</dt> 	      		 	      			
					<dd class="puc"><a href="http://www.pucwebstore.com.br/loja/" target="_blank" title="PUC Webstore">PUC Webstore</a></dd> 	      		 	      		 	      		
					<dd class="hering"><a href="http://hering.com.br/" target="_blank" title="Hering">Hering</a></dd> 	      	
				<dt class="social">Redes Sociais</dt> 	      		
					<dd class="pinterest"><a href="https://instagram.com/dzarmoficial" target="_blank" title="Instagram">Instagram</a></dd> 	      		
					<dd class="twitter t-space"><a href="https://twitter.com/dzarm_Oficial" target="_blank" title="Twitter">Twitter</a></dd> 	      		
					<dd class="facebook"><a href="https://www.facebook.com/dzarm.oficial" target="_blank" title="Facebook">Facebook</a></dd> 	    
			</dl> 	    
			<dl class="meioPagamento"> 	    	
				<dt>Meio de pagamento</dt> 	      		
					<dd class="visa">Visa</dd> 			    
					<dd class="master">Master</dd> 			    
					<dd class="dinners">Dinners</dd> 			    
					<dd class="amex">Amex</dd> 			    
					<dd class="boleto">Boleto</dd> 	    
			</dl>
		</div>
		<div class="contentFooter">
		    <div class="news">
		    	<newsletter:newsletterRegister />
		    </div>	    
		    <dl class="certificadosSeguranca">
			    <dt class="certificados">
			   		<spring:theme code="general.certificados.e.seguranca" />
			    </dt>
			    <dd class="certisign c-indent">
					<a href="http://www.certisign.com.br" target="_blank"></a>
				</dd>
				<dd class="ebitOuro"> <a id="seloEbit" href="http://www.ebit.com.br/#dzarm" target="_blank" onclick="redir(this.href);"></a></dd>
				<script type="text/javascript" id="getSelo" src="https://558701205.r.anankecdn.com.br/ebitBR/static/getSelo.js?11526" ></script>
		    </dl>
		</div>
		<cms:pageSlot position="FooterBottom" var="feature" element="div" class="textFooter">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	  	<div class="textFooter">
	    	<p>Cia. Hering | Rua Hermann Hering, 1790, Bom Retiro | CEP 89010-900 | Blumenau | SC</p>
	    	<p>CNPJ: 78.876.950/0001-71 | Insc. Estadual: 251.213.749 |  <a href="javascript:void(0)" class="ai tooltip-link"><spring:theme code="general.central.de.atendimento" /></a></p>
	  	</div>
	</div>
	
</c:if>

<%-- FOOTER HERING STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<div id="footer" class="footer">
		<cms:pageSlot position="Footer" var="feature" element="div">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	</div>
</c:if>