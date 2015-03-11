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
        <!-- <xsl:if test="price != '' and stock &gt; 0 and isParentProduct 
            = 'false'"> -->
        <product>
            <id>
                <xsl:value-of select="code" />
            </id>
            <name>
                <xsl:value-of select="name" />
            </name>
            <description>
                <xsl:value-of select="description" />
            </description>
            <brand>
                <xsl:value-of select="manufacturer" />
            </brand>
            <price>
                <xsl:value-of select="price" />
            </price>
            <link>
                <xsl:value-of select="url" />
            </link>
            <image>
                <xsl:value-of select="main_image_url" />
            </image>
            <category>
                <xsl:value-of select="categories[1]" />
            </category>
            <stock>
                <xsl:value-of select="stock_level" />
            </stock>
            <ean>
                <xsl:value-of select="ean" />
            </ean>
        </product>
        <!-- </xsl:if> -->
    </xsl:template>
</xsl:stylesheet>