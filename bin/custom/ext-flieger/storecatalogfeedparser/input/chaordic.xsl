<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fn="http://www.w3.org/2005/xpath-functions">
    <xsl:output method="xml" encoding="utf-8" standalone="yes" />
    <xsl:template match="/">
        <rss version="2.0">
            <channel>
                <title>
                    <xsl:value-of select="storeTitle" />
                </title>
                <link>
                    <xsl:value-of select="storeUrl" />
                </link>
                <description>
                    <xsl:value-of select="storeDescription" />
                    Chaordic
                </description>
                <xsl:apply-templates select="products/product" />
            </channel>
        </rss>
    </xsl:template>
    <xsl:template match="product">
        <xsl:if test="name != '' and price &gt; 0 and stock_level &gt; 0">
            <item>
                <id>
                    <xsl:value-of select="code" />
                </id>
                <title>
                    <xsl:value-of select="name" />
                </title>
                <description>
                    <xsl:value-of select="description" />
                </description>
                <link>
                    <xsl:value-of select="url" />
                </link>
                <tags>
                    <xsl:for-each select="categories/category">
                        <tag>
                            <xsl:value-of select="." />
                        </tag>
                    </xsl:for-each>
                    <tag>
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
                    </tag>
                </tags>
                <images>
                    <xsl:for-each select="images/additional_image">
                        <xsl:if test="additional_image_dimensions != ''">
                            <xsl:element
                                name="{concat('_', additional_image_dimensions)}">
                                <xsl:value-of select="additional_image_url" />
                            </xsl:element>
                        </xsl:if>
                    </xsl:for-each>
                    <default>
                        <xsl:value-of select="main_image_url" />
                    </default>
                </images>
                <condition>new</condition>
                <stock>
                    <xsl:value-of select="stock_level" />
                </stock>
                <status>
                    <xsl:choose>
                        <xsl:when test="stock_level &gt; 0">
                            available
                        </xsl:when>
                        <xsl:otherwise>
                            unavailable
                        </xsl:otherwise>
                    </xsl:choose>
                </status>
                <old_price>
                    <xsl:value-of select="previous_price" />
                </old_price>
                <price>
                    <xsl:value-of select="price" />
                    BRL
                </price>
                <xsl:if test="sale_price &gt; 0">
                    <sale_price>
                        <xsl:value-of select="sale_price" />
                        BRL
                    </sale_price>
                    <sale_price_effective_date>
                        <xsl:value-of select="sale_date_begin" />
                        /
                        <xsl:value-of select="sale_date_end" />
                    </sale_price_effective_date>
                </xsl:if>
                <brand>
                    <xsl:value-of select="manufacturer" />
                </brand>
                <gtin>
                    <xsl:value-of select="ean" />
                </gtin>
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
                <xsl:if test="price &gt; 99.99">
                    <shipping>
                        <country>BR</country>
                        <price>0.0</price>
                    </shipping>
                </xsl:if>
                <installment>
                    <count>
                        <xsl:value-of select="installments_quantity" />
                    </count>
                    <price>
                        <xsl:value-of select="installments_amount" />
                    </price>
                </installment>
                <specs>
                    <color>
                        <xsl:for-each select="variants/variant">
                            <xsl:value-of select="variant_color" />
                            ,
                        </xsl:for-each>
                    </color>
                    <size>
                        <xsl:for-each select="variants/variant">
                            <xsl:value-of select="variant_size" />
                            ,
                        </xsl:for-each>
                    </size>
                </specs>
                <skus>
                    <xsl:for-each select="variants/variant">
                        <xsl:if test="variant_stock_level &gt; 0">
                            <sku>
                                <id>
                                    <xsl:value-of select="variant_code" />
                                </id>
                                <xsl:if
                                    test="variant_previous_price != '' and variant_previous_price != variant_price">
                                    <old_price>
                                        <xsl:value-of
                                            select="variant_previous_price" />
                                    </old_price>
                                </xsl:if>
                                <price>
                                    <xsl:value-of select="variant_price" />
                                </price>
                                <installment>
                                    <count>
                                        <xsl:value-of
                                            select="../../installments_quantity" />
                                    </count>
                                    <price>
                                        <xsl:value-of
                                            select="../../installments_amount" />
                                    </price>
                                </installment>
                                <stock>
                                    <xsl:value-of select="variant_stock" />
                                </stock>
                                <status>available</status>
                                <ean>
                                    <xsl:value-of select="variant_ean" />
                                </ean>
                                <xsl:if test="variant_main_image != ''">
                                    <images>
                                        <default>
                                            <xsl:value-of
                                                select="variant_main_image" />
                                        </default>
                                    </images>
                                </xsl:if>
                                <xsl:if
                                    test="variant_color != '' and variant_size != ''">
                                    <specs>
                                        <color>
                                            <xsl:value-of
                                                select="variant_color" />
                                        </color>
                                        <size>
                                            <xsl:value-of
                                                select="variant_size" />
                                        </size>
                                    </specs>
                                </xsl:if>
                                <extra_info>
                                    <hexa_color>
                                        <xsl:value-of
                                            select="variant_hexa_color" />
                                    </hexa_color>
                                </extra_info>
                            </sku>
                        </xsl:if>
                    </xsl:for-each>
                </skus>
            </item>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>