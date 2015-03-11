<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="utf-8" standalone="yes" />
    <xsl:template match="/">
        <products>
            <xsl:apply-templates select="products/product" />
        </products>
    </xsl:template>
    <xsl:template match="product">
        <!-- <xsl:if test="price != '' and is_base_product = 'false'"> -->
        <product>
            <id>
                <xsl:value-of select="code" />
            </id>
            <title>
                <xsl:value-of select="name" />
            </title>
            <description>
                <xsl:value-of select="description" />
            </description>
            <category>
                <xsl:value-of select="categories[1]/code" />
            </category>
            <price>
                <xsl:value-of select="price" />
            </price>
            <xsl:if test="previous_price != '' and previous_price != price">
                <price_old>
                    <xsl:value-of select="previous_price" />
                </price_old>
            </xsl:if>
            <url_product>
                <xsl:value-of select="url" />
            </url_product>
            <url_image>
                <xsl:value-of select="main_image_url" />
            </url_image>
        </product>
        <!-- </xsl:if> -->
    </xsl:template>
</xsl:stylesheet>