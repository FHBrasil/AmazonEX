<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="product" required="true"
    type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty product.measurements}">
    <!-- Size Full List -->
    <c:set var="varSizeFullListArray" value="" />
    <c:set var="varSizeCounter" value="0" />
    <c:set var="varMeasurementsCount" value="0" />
    <c:forEach var="measurement" items="${product.measurements}">
        <c:choose>
            <c:when test="${varSizeCounter > 0 }">
                <c:set var="varSizeFullListArray"
                    value="${varSizeFullListArray},${measurement.size}" />
            </c:when>
            <c:otherwise>
                <c:set var="varSizeFullListArray" value="${varSizeFullListArray}${measurement.size}" />
            </c:otherwise>
        </c:choose>
        <c:set var="varSizeCounter" value="${varSizeCounter + 1}" />
    </c:forEach>
    <c:set var="varMeasurementsCount" value="${varSizeCounter}" />
    <!-- /Size Full List -->
    <!-- Size List Without Duplicates -->
    <c:set var="varSizeListWithoutDuplicates" value="" />
    <c:set var="varTemDuplicado" value="0" />
    <c:set var="varcount1" value="0" />
    <c:set var="varcount2" value="0" />
    <c:set var="varEncntrouDuplicados" value="0" />
    <c:forTokens items="${varSizeFullListArray}" delims="," var="itemArray">
        <c:if test="${varcount1 == 0}">
            <c:set var="varSizeListWithoutDuplicates">${itemArray}</c:set>
        </c:if>
        <c:set var="varcount1">${varcount1 + 1}</c:set>
    </c:forTokens>
    <c:forTokens items="${varSizeFullListArray}" delims="," var="itemArray">
        <c:set var="contitemduplicado" value="0" />
        <c:forTokens items="${varSizeListWithoutDuplicates}" delims="," var="duplicados">
            <c:set var="varcount2" value="${varcount2 + 1}" />
            <c:choose>
                <c:when test="${itemArray eq duplicados}">
                    <c:set var="contitemduplicado" value="${contitemduplicado + 1}" />
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </c:forTokens>
        <c:choose>
            <c:when test="${contitemduplicado > 0}">
            </c:when>
            <c:otherwise>
                <!-- Add Item to List -->
                <c:set var="varSizeListWithoutDuplicates">${varSizeListWithoutDuplicates},${itemArray}</c:set>
            </c:otherwise>
        </c:choose>
    </c:forTokens>
    <!-- Size Type List -->
    <c:set var="varTypeFullList" value="" />
    <c:set var="varTypeCounter" value="0" />
    <c:forEach var="measurement" items="${product.measurements}">
        <c:choose>
            <c:when test="${varTypeCounter > 0 }">
                <c:set var="varTypeFullList" value="${varTypeFullList}/${measurement.type}" />
            </c:when>
            <c:otherwise>
                <c:set var="varTypeFullList" value="${varTypeFullList}${measurement.type}" />
            </c:otherwise>
        </c:choose>
        <c:set var="varTypeCounter" value="${varTypeCounter + 1}" />
    </c:forEach>
    <!-- /Size Type List -->
    <!-- Type List Without Duplicates -->
    <c:set var="varTypeListWithoutDuplicate" value="" />
    <c:set var="varTemDuplicado" value="0" />
    <c:set var="varcount1" value="0" />
    <c:set var="varcount2" value="0" />
    <c:set var="varEncntrouDuplicados" value="0" />
    <c:forTokens items="${varTypeFullList}" delims="/" var="itemArray">
        <c:if test="${varcount1 == 0}">
            <c:set var="varTypeListWithoutDuplicate">${itemArray}</c:set>
        </c:if>
        <c:set var="varcount1">${varcount1 + 1}</c:set>
    </c:forTokens>
    <c:forTokens items="${varTypeFullList}" delims="/" var="itemArray">
        <c:set var="contitemduplicado" value="0" />
        <c:forTokens items="${varTypeListWithoutDuplicate}" delims="/" var="duplicados">
            <c:set var="varcount2" value="${varcount2 + 1}" />
            <c:choose>
                <c:when test="${itemArray eq duplicados}">
                    <c:set var="contitemduplicado" value="${contitemduplicado + 1}" />
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
        </c:forTokens>
        <c:choose>
            <c:when test="${contitemduplicado > 0}">
            </c:when>
            <c:otherwise>
                <!-- Add Item to List -->
                <c:set var="varTypeListWithoutDuplicate">${varTypeListWithoutDuplicate}/${itemArray}</c:set>
            </c:otherwise>
        </c:choose>
    </c:forTokens>
    <c:set var="varTypeCount" value="0" />
    <c:forTokens items="${varTypeListWithoutDuplicate}" delims="/" var="itemArray">
        <c:set var="varTypeCount" value="${varTypeCount +1}" />
    </c:forTokens>
    <div id="modal-tabela-medidas">
        <%-- 			<product:productPrimaryImage  product="${product}" format="product"/> --%>
        <div class="content">
            <h3>Tabela de medidas</h3>
            <div class="itensTabelaMedidas">
                <dl class="tableMedidasTitle">
                    <dt class="barraTitle"></dt>
                    <c:forTokens items="${varTypeListWithoutDuplicate}" delims="/" var="itemArray">
                        <dt>${itemArray}</dt>
                    </c:forTokens>
                </dl>
                <div class="colright">
                    <dl class="tableMedidasTitle2">
                        <c:set var="sizeListSize" value="0" />
                        <c:forTokens items="${varSizeListWithoutDuplicates}" delims=","
                            var="itemArray">
                            <dt>${itemArray}</dt>
                            <c:set var="sizeListSize" value="${sizeListSize + 1}" />
                        </c:forTokens>
                    </dl>
                    <dl class="tableMedidasItens">
                        <c:forEach var="measurement" items="${product.measurements}"
                            varStatus="status">
                            <c:set var="clearBoth" value="style=\"clear: both;\"" />
                            <c:set var="style"
                                value="${(status.first or status.index % sizeListSize == 0) ? clearBoth : ''}" />
                            <dd class="${status.index % 2 == 0 ? 'even' : 'odd'}" ${style}>${measurement.measurement}</dd>
                        </c:forEach>
                    </dl>
                </div>
            </div>
            <strong>BUSTO / T�RAX</strong>
            <p>Posicione a fita m�trica abaixo dos bra�os, medindo somente a parte frontal sobre
                a parte mais volumosa do busto, de uma lateral � outra do corpo. Tome cuidado para
                que a fita fique bem reta.</p>
            <strong>CINTURA</strong>
            <p>Me�a sua cintura posicionando a fita m�trica na altura do umbigo, medindo somente
                a parte frontal, de uma lateral � outra. Cuide para que a fita esteja bem reta.
                Relaxe, expire o ar e ajuste a fita: ela precisa estar justa, mas nunca apertada.</p>
            <strong>COMPRIMENTO</strong>
            <p>Chegou a hora de contar com aquela ajudinha especial. Fique em p�, com a postura
                bem reta, e pe�a para algu�m verificar suas medidas. Elas servem para conferir como
                o comprimento da pe�a ficar� no seu corpo. Cal�as, shorts, bermudas e saias: coloque
                a ponta da fita na linha da cintura e me�a at� chegar ao comprimento que voc�
                esejar.Vestidos: coloque a ponta da fita no topo do ombro e me�a at� chegar ao
                comprimento desejado.</p>
            <strong>DICA ESPECIAL</strong>
            <p>Outra forma de saber se o look escolhido ficar� bem no seu corpo � medir modelos
                semelhantes do seu guarda-roupa. Depois � s� comparar as medidas. Assim � poss�vel
                ter uma boa ideia sobre o tamanho das pe�as.</p>
        </div>
    </div>
</c:if>
