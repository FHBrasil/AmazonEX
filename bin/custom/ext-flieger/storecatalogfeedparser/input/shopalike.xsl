<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="utf-8" standalone="yes" />
    <xsl:template match="/">
        <items>
            <xsl:apply-templates select="products/product" />
        </items>
    </xsl:template>
    <xsl:template match="product">
        <!-- <xsl:if test="isParentProduct = 'false' and price != '' and 
            stock &gt; 0"> -->
        <item>
            <itemId>
                <xsl:value-of select="code" />
            </itemId>
            <name>
                <xsl:value-of select="name" />
            </name>
            <description>
                <xsl:value-of select="description" />
            </description>
            <color></color>
            <sizes>
                <xsl:template match="variants/variant">
                    <xsl:value-of select="variant_size" /> 
                </xsl:template>
            </sizes>
            <price>
                <xsl:value-of select="price" />
            </price>
            <xsl:if test="previous_price != '' and previous_price != price">
                <oldPrice>
                    <xsl:value-of select="previous_price" />
                </oldPrice>
            </xsl:if>
            <parcelamento>
                <xsl:value-of select="installments_quantity" />
                X
                <xsl:value-of select="installments_amount" />
            </parcelamento>
            <image>
                <xsl:value-of select="main_image_url" />
            </image>
            <deepLink>
                <xsl:value-of select="url" />
            </deepLink>
            <gender>
                <xsl:choose>
                    <xsl:when test="gender = 'MALE'">
                        Masculino
                    </xsl:when>
                    <xsl:when test="gender = 'FEMALE'">
                        Feminino
                    </xsl:when>
                    <xsl:otherwise>
                        Unissex
                    </xsl:otherwise>
                </xsl:choose>
            </gender>
            <fullCategory>
                <xsl:value-of select="categories[1]" />
            </fullCategory>
            <brand>
                <xsl:value-of select="manufacturer" />
            </brand>
            <currency>EURO</currency>
            <shippingCosts>variable</shippingCosts>
        </item>
        <!-- </xsl:if> -->
    </xsl:template>
</xsl:stylesheet>