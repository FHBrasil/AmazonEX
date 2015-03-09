<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:g="http://base.google.com/ns/1.0">
	<xsl:output method="xml" encoding="utf-8" standalone="yes"/>
	<xsl:template match="/">
		<rss version="2.0">
			<channel>
				<title><xsl:value-of select="products/storeTitle"/></title>
				<link><xsl:value-of select="products/storeUrl"/></link>
				<description><xsl:value-of select="products/storeDescription"/> Sociomantic</description>
				<xsl:apply-templates select="products/product"/>
			</channel>
		</rss>
	</xsl:template>
	<xsl:template match="product">
		<!-- <xsl:if test="name != '' and price &gt; 0 and stock &gt; 0"> -->
		<item>
			<g:id><xsl:value-of select="code"/></g:id>
			<title><xsl:value-of select="name"/></title>
			<description><xsl:value-of select="description"/></description>
			<link><xsl:value-of select="url"/></link>
			<g:product_type><xsl:value-of select="category"/></g:product_type>
			<g:image_link><xsl:value-of select="image"/></g:image_link>
			<xsl:for-each select="additionalImagesUrl">
				<g:additional_image_link>
					<xsl:value-of select="."/>
				</g:additional_image_link>
			</xsl:for-each>
			<g:condition>new</g:condition>
			<g:availability>in stock</g:availability>
		   	<g:price><xsl:value-of select="price"/> BRL</g:price>
			<xsl:if test="sale_price &gt; 0">
				<g:sale_price><xsl:value-of select="sale_price"/> BRL</g:sale_price>
				<g:sale_price_effective_date><xsl:value-of select="sale_date"/></g:sale_price_effective_date>
			</xsl:if>
			<g:brand><xsl:value-of select="brand"/></g:brand>
			<g:gtin><xsl:value-of select="ean"/></g:gtin>
			<g:identifier_exists>
				<xsl:choose>
					<xsl:when test="ean != ''">TRUE</xsl:when>
					<xsl:otherwise>FALSE</xsl:otherwise>
				</xsl:choose>
			</g:identifier_exists>
			<g:gender><xsl:value-of select="gender"/></g:gender>
			<!--g:age_group></g:age_group -->
			<xsl:if test="price &gt; 99.99">
				<g:shipping>
					<g:country>BR</g:country>
					<g:price>0.0</g:price>
				</g:shipping>
			</xsl:if>
			<g:installment>
				<g:months><xsl:value-of select="parcelQty"/></g:months>
				<g:amount><xsl:value-of select="parcelAmount"/></g:amount>
			</g:installment>
			<g:color><xsl:value-of select="color"/></g:color>
			<g:size><xsl:value-of select="size"/></g:size>
			<xsl:if test="string-length(code) &gt; 4">
				<g:item_group_id><xsl:value-of select="baseProductCode"/></g:item_group_id>
			</xsl:if>
		</item>
		<!-- </xsl:if> -->
	</xsl:template>
</xsl:stylesheet>