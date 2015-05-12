<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:g="http://base.google.com/ns/1.0"
		xmlns:date="http://exslt.org/dates-and-times">
	<xsl:output method="xml" encoding="utf-8" standalone="yes"/>
	<xsl:template match="/">
		<rss version="2.0">
			<channel>
				<title><xsl:value-of select="products/storeTitle"/></title>
				<link><xsl:value-of select="products/storeUrl"/></link>
				<description><xsl:value-of select="products/storeDescription"/> Google Merchant</description>
				<xsl:apply-templates select="products/product/variants/variant"/>
			</channel>
		</rss>
	</xsl:template>
	<xsl:template match="variant">
		<item>
			<title><xsl:value-of select="../../name"/></title>
			<description><xsl:value-of select="../../description"/></description>
			<g:product_type>
				<xsl:choose>
					<xsl:when test="../../gender = 'FEMALE'">Feminino &gt; </xsl:when>
					<xsl:when test="../../gender = 'MALE'">Masculino &gt; </xsl:when>
				</xsl:choose>
				<xsl:value-of select="../../category"/>
			</g:product_type>
			<g:item_group_id><xsl:value-of select="../../code"/></g:item_group_id>
			<g:id><xsl:value-of select="code"/></g:id>
			<link><xsl:value-of select="../../url"/></link>
			<g:image_link><xsl:value-of select="image"/></g:image_link>
			<g:condition>new</g:condition>
			<g:availability>
				<xsl:choose>
					<xsl:when test="stock &gt; 0">in stock</xsl:when>
					<xsl:otherwise>out of stock</xsl:otherwise>
				</xsl:choose>
			</g:availability>
			<xsl:choose>
				<xsl:when test="oldPrice &gt; price"><g:price><xsl:value-of select="oldPrice"/> BRL</g:price></xsl:when>
				<xsl:when test="price &gt; 0"><g:price><xsl:value-of select="price"/> BRL</g:price></xsl:when>
			</xsl:choose>
			<xsl:if test="sale_price &gt; 0">
				<g:sale_price><xsl:value-of select="sale_price"/> BRL</g:sale_price>
			</xsl:if>
			<g:gtin><xsl:value-of select="ean"/></g:gtin>
			<g:identifier_exists>
				<xsl:choose>
					<xsl:when test="ean != ''">TRUE</xsl:when>
					<xsl:otherwise>FALSE</xsl:otherwise>
				</xsl:choose>
			</g:identifier_exists>
			<g:brand><xsl:value-of select="../../brand"/></g:brand>
			<g:gender><xsl:value-of select="../../gender"/></g:gender>
			<g:color><xsl:value-of select="color"/></g:color>
			<g:size><xsl:value-of select="size"/></g:size>
			<g:installment>
				<g:months><xsl:value-of select="../../parcelQty"/></g:months>
				<g:amount><xsl:value-of select="../../parcelAmount"/></g:amount>
			</g:installment>
			<xsl:if test="price &gt; 99.99">
				<g:shipping>
					<g:country>BR</g:country>
					<g:service>Frete Grátis</g:service>
					<g:price>0.0</g:price>
				</g:shipping>
			</xsl:if>
			<g:custom_label_0><xsl:value-of select="hexaColor"/></g:custom_label_0>
			<xsl:if test="oldPrice &gt; price">
				<g:custom_label_1>Oferta</g:custom_label_1>
			</xsl:if>
			<xsl:call-template name="isProductNew"/>
		</item>
	</xsl:template>

	<xsl:template name="isProductNew">
		<xsl:if test="sale_date != ''">
			<xsl:variable name="releaseDate" select="substring(sale_date,1,10)"/>
			<xsl:variable name="referenceDate" select="substring(date:date(),1,10)"/>
			<xsl:variable name="releaseDays" select="date:day-in-year($releaseDate)"/>
			<xsl:variable name="referenceDays">
			<xsl:choose>
				<xsl:when test="date:day-in-year($referenceDate) > 30">
					<xsl:value-of select="date:day-in-year($referenceDate) - 30"/>
				</xsl:when>
				<!-- ano bisexto faz diferenca nos calculos -->
				<xsl:when test="string(date:leap-year(string(date:year($referenceDate)-1))) = 'true'">
					<xsl:value-of select="366 + date:day-in-year($referenceDate) - 30"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="365 + date:day-in-year($referenceDate) - 30"/>
				</xsl:otherwise>
			</xsl:choose>
			</xsl:variable>
			<xsl:variable name="releaseYear" select="date:year($releaseDate)"/>
			<xsl:variable name="referenceYear">
			<xsl:choose>
				<xsl:when test="date:day-in-year($referenceDate) > 30">
					<xsl:value-of select="date:year($referenceDate)"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="date:year($referenceDate)-1"/>
				</xsl:otherwise>
			</xsl:choose>
			</xsl:variable>
			<!--<debug><xsl:value-of select="$releaseDate"/></debug>
			<debug><xsl:value-of select="$referenceDate"/></debug>
			<debug><xsl:value-of select="$releaseDays"/></debug>
			<debug><xsl:value-of select="$referenceDays"/></debug>
			<debug><xsl:value-of select="$releaseYear"/></debug>
			<debug><xsl:value-of select="$referenceYear"/></debug>-->
			<!-- It's new for 30 days -->
			<xsl:if test="$releaseYear &gt;= $referenceYear and $releaseDays &gt;= $referenceDays">
				<g:custom_label_2>Lançamento</g:custom_label_2>
			</xsl:if>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>