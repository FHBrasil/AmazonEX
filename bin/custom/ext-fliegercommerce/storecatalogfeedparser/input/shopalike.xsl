<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="2.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" encoding="utf-8" standalone="yes"/>
<xsl:template match="/">
	<items>
		<xsl:apply-templates select="products/product"/>
	</items>
</xsl:template>
<xsl:template match="product">
	<!-- <xsl:if test="isParentProduct = 'false' and price != '' and stock &gt; 0"> -->
		<item>
			<itemId><xsl:value-of select="code"/></itemId>
			<name><xsl:value-of select="name"/></name>
			<description><xsl:value-of select="description"/></description>
			<color><xsl:value-of select="color"/></color>
			<sizes><xsl:value-of select="size"/></sizes>
			<price><xsl:value-of select="price"/></price>
			<xsl:if test="oldPrice != '' and oldPrice != price">
				<oldPrice><xsl:value-of select="oldPrice"/></oldPrice>
			</xsl:if>
			<PARCELAMENTO><xsl:value-of select="parcelQty"/> X <xsl:value-of select="parcelAmount"/></PARCELAMENTO>
			<image><xsl:value-of select="image"/></image>
			<deepLink><xsl:value-of select="url"/></deepLink>
			<gender>
				<xsl:choose>
					<xsl:when test="gender = 'MALE'">Masculino</xsl:when>
					<xsl:when test="gender = 'FEMALE'">Feminino</xsl:when>
					<xsl:otherwise>Unissex</xsl:otherwise>
				</xsl:choose>
			</gender>
			<fullCategory><xsl:value-of select="category"/></fullCategory>
			<brand><xsl:value-of select="brand"/></brand>
			<currency>REAIS</currency>
			<shippingCosts>variável</shippingCosts>
		</item>
	<!-- </xsl:if> -->
</xsl:template>
</xsl:stylesheet>