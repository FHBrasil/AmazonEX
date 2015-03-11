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
        <xsl:if test="stock &gt; 0 and price != ''">
            <xsl:element name="product">
                <xsl:attribute name="id"><xsl:value-of
                    select="code" /></xsl:attribute>
                <name>
                    <xsl:value-of select="name" />
                </name>
                <description>
                    <xsl:value-of select="description" />
                </description>
                <category>
                    <xsl:value-of select="categories[1]" />
                </category>
                <producturl>
                    <xsl:value-of select="url" />
                </producturl>
                <price>
                    <xsl:value-of select="price" />
                </price>
                <xsl:if
                    test="previous_price != '' and previous_price != price">
                    <old_price>
                        <xsl:value-of select="previous_price" />
                    </old_price>
                    <retailprice>
                        <xsl:value-of select="previous_price" />
                    </retailprice>
                </xsl:if>
                <instock>
                    <xsl:value-of select="stock_level" />
                </instock>
                <smallimage>
                    <xsl:value-of select="thumbnail_url" />
                </smallimage>
                <bigimage>
                    <xsl:value-of select="main_image_url" />
                </bigimage>
            </xsl:element>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>