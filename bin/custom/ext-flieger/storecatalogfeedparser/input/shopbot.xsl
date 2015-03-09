<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0"
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="utf-8" standalone="yes"/>
	<xsl:template match="/">
		<rss version="2.0">
			<channel>
				<title><xsl:value-of select="products/storeTitle"/></title>
				<link><xsl:value-of select="products/storeUrl"/></link>
				<description><xsl:value-of select="products/storeDescription"/> ShopBot</description>
				<xsl:apply-templates select="products/product"/>
			</channel>
		</rss>
	</xsl:template>
	<xsl:template match="product">
		<!-- <xsl:if test="name != '' and price &gt; 0 and stock &gt; 0"> -->
		<item>
			<id><xsl:value-of select="code"/></id>
			<title><xsl:value-of select="name"/></title>
			<description><xsl:value-of select="description"/></description>
			<link><xsl:value-of select="url"/></link>
			<product_type><xsl:value-of select="category"/></product_type>
			<image_link><xsl:value-of select="image"/></image_link>
			<xsl:for-each select="additionalImagesUrl">
				<additional_image_link>
					<xsl:value-of select="."/>
				</additional_image_link>
			</xsl:for-each>
			<condition>new</condition>
			<availability>in stock</availability>
		   	<price><xsl:value-of select="price"/> BRL</price>
			<xsl:if test="sale_price &gt; 0">
				<sale_price><xsl:value-of select="sale_price"/> BRL</sale_price>
				<sale_price_effective_date><xsl:value-of select="sale_date"/></sale_price_effective_date>
			</xsl:if>
			<brand><xsl:value-of select="brand"/></brand>
			<gtin><xsl:value-of select="ean"/></gtin>
			<identifier_exists>
				<xsl:choose>
					<xsl:when test="ean != ''">TRUE</xsl:when>
					<xsl:otherwise>FALSE</xsl:otherwise>
				</xsl:choose>
			</identifier_exists>
			<gender><xsl:value-of select="gender"/></gender>
			<!--g:age_group></g:age_group -->
			<xsl:if test="price &gt; 99.99">
				<shipping>
					<country>BR</country>
					<price>0.0</price>
				</shipping>
			</xsl:if>
			<installment>
				<months><xsl:value-of select="parcelQty"/></months>
				<amount><xsl:value-of select="parcelAmount"/></amount>
			</installment>
			<color><xsl:value-of select="color"/></color>
			<size><xsl:value-of select="size"/></size>
			<xsl:if test="string-length(code) &gt; 4">
				<item_group_id><xsl:value-of select="baseProductCode"/></item_group_id>
			</xsl:if>
		</item>
		<!-- </xsl:if> -->
	</xsl:template>
</xsl:stylesheet>