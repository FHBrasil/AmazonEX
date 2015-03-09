<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="2.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fn="http://www.w3.org/2005/xpath-functions">
    <xsl:output method="xml" encoding="utf-8" standalone="yes"/>
    <xsl:template match="/">
        <rss version="2.0">
            <channel>
                <title><xsl:value-of select="products/storeTitle"/></title>
                <link><xsl:value-of select="products/storeUrl"/></link>
                <description><xsl:value-of select="products/storeDescription"/> Chaordic</description>
                <xsl:apply-templates select="products/product"/>
            </channel>
        </rss>
    </xsl:template>
    <xsl:template match="product">
    	<xsl:if test="name != '' and firstVariantPrice &gt; 0 and productTotalStock &gt; 0">
        <item>
            <id><xsl:value-of select="code"/></id>
            <title><xsl:value-of select="name"/></title>
            <description><xsl:value-of select="description"/></description>
            <link><xsl:value-of select="url"/></link>
            <tags>
            	<tag><xsl:value-of select="category"/></tag>
            	<tag>
            		<xsl:choose>
            			<xsl:when test="gender = 'MALE'">Masculino</xsl:when>
						<xsl:when test="gender = 'FEMALE'">Feminino</xsl:when>
						<xsl:otherwise>Unissex</xsl:otherwise>
            		</xsl:choose>
            	</tag>
           	</tags>
            <images>
	            <xsl:for-each select="additionalImages/image">
		            <xsl:if test="additionalImageDimensions != ''">
		            	<xsl:element name="{concat('_', additionalImageDimensions)}">
		            		<xsl:value-of select="additionalUrl"/>
		            	</xsl:element>
	            	</xsl:if>	
		        </xsl:for-each>
	            <default><xsl:value-of select="image"/></default>
          	</images>
            <condition>new</condition>
            <stock><xsl:value-of select="productTotalStock"/></stock>
            <status>
            	<xsl:choose>
                    <xsl:when test="productTotalStock &gt; 0">available</xsl:when>
            		<xsl:otherwise>unavailable</xsl:otherwise>
                </xsl:choose>
            </status>
            <old_price><xsl:value-of select="oldPrice"/></old_price>
           	<price><xsl:value-of select="firstVariantPrice"/> BRL</price>
            <xsl:if test="sale_price &gt; 0">
            	<sale_price><xsl:value-of select="sale_price"/> BRL</sale_price>
	            <sale_price_effective_date>
	                <xsl:value-of select="sale_date"/>
	            </sale_price_effective_date>
            </xsl:if>
            <brand><xsl:value-of select="brand"/></brand>
            <gtin><xsl:value-of select="ean"/></gtin>
            <gender>
            	<xsl:choose>
           			<xsl:when test="gender = 'MALE'">Masculino</xsl:when>
					<xsl:when test="gender = 'FEMALE'">Feminino</xsl:when>
					<xsl:otherwise>Unissex</xsl:otherwise>
           		</xsl:choose>
            </gender>
            <xsl:if test="price &gt; 99.99">
                <shipping>
                    <country>BR</country>
                    <price>0.0</price>
                </shipping>
            </xsl:if>
            <installment>
                <count><xsl:value-of select="parcelQty"/></count>
                <price><xsl:value-of select="parcelAmount"/></price>
            </installment>
            <specs>
            	<color><xsl:value-of select="availableVariantColors"/></color>
            	<size><xsl:value-of select="availableVariantSizes"/></size>
            </specs>
            <skus>
            	<xsl:for-each select="variants/variant">
            		<xsl:if test="stock &gt; 0">
	            		<sku>
		            		<id><xsl:value-of select="code"/></id>
		            		<xsl:if test="oldPrice != '' and oldPrice != price">
		            			<old_price><xsl:value-of select="oldPrice"/></old_price>
		            		</xsl:if>
		            		<price><xsl:value-of select="price"/></price>
		            		<installment>
				                <count><xsl:value-of select="../../parcelQty"/></count>
				                <price><xsl:value-of select="parcelAmount"/></price>
				            </installment>
		            		<stock><xsl:value-of select="stock"/></stock>
		            		<status>available</status>
		            		<ean><xsl:value-of select="ean"/></ean>
		            		<xsl:if test="image != ''">
		            			<images>
		            				<default><xsl:value-of select="image"/></default>
	            				</images>
	            			</xsl:if>
	            			<xsl:if test="color != '' and size != ''">
			            		<specs>
			            			<color><xsl:value-of select="color"/></color>
			            			<size><xsl:value-of select="size"/></size>
			            		</specs>
		            		</xsl:if>
		            		<extra_info>
		            			<hexa_color><xsl:value-of select="hexaColor"/></hexa_color>
		            		</extra_info>
		            	</sku>
	            	</xsl:if>
            	</xsl:for-each>
            </skus>
        </item>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>