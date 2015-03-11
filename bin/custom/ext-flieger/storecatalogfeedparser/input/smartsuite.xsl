<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="utf-8" standalone="yes" />
    <xsl:template match="/">
        <PRODUTOS>
            <xsl:apply-templates select="products/product" />
        </PRODUTOS>
    </xsl:template>
    <xsl:template match="product">
        <!-- <xsl:if test="price != '' and isParentProduct = 'false'"> -->
        <PRODUTO>
            <SKU>
                <xsl:value-of select="code" />
            </SKU>
            <NOME>
                <xsl:value-of select="name" />
            </NOME>
            <CATEGORIA>
                <xsl:value-of select="categories[1]" />
            </CATEGORIA>
            <SUBCATEGORIA><!-- blank -->
            </SUBCATEGORIA>
            <PRECO>
                <DE>
                    <xsl:value-of select="price" />
                </DE>
                <POR>
                    <xsl:choose>
                        <xsl:when
                            test="sale_price != '' and sale_price != price">
                            <xsl:value-of select="sale_price" />
                        </xsl:when>
                        <xsl:when
                            test="sale_price = '' or sale_price = price">
                            <xsl:value-of select="price" />
                        </xsl:when>
                    </xsl:choose>
                </POR>
            </PRECO>
            <PARCELAMENTO>
                <N_PARCELAS>5</N_PARCELAS>
                <V_PARCELAS>
                    <xsl:value-of select="format-number(price div 5, '#.00')" />
                </V_PARCELAS>
            </PARCELAMENTO>
            <FRETE>
                <GRATIS>
                    <xsl:choose>
                        <xsl:when test="price &gt; 49.90">
                            1
                        </xsl:when>
                        <xsl:otherwise>
                            0
                        </xsl:otherwise>
                    </xsl:choose>
                </GRATIS>
            </FRETE>
            <URL>
                <xsl:value-of select="url" />
            </URL>
            <IMAGEM>
                <xsl:value-of select="main_image_url" />
            </IMAGEM>
            <DISPONIBILIDADE>
                <xsl:value-of select="stock_level" />
            </DISPONIBILIDADE>
            <VARIAVEIS>
                <VARIAVEL_A><!-- blank -->
                </VARIAVEL_A>
                <VARIAVEL_B><!-- blank -->
                </VARIAVEL_B>
                <VARIAVEL_C><!-- blank -->
                </VARIAVEL_C>
                <VARIAVEL_D><!-- blank -->
                </VARIAVEL_D>
                <VARIAVEL_E><!-- blank -->
                </VARIAVEL_E>
            </VARIAVEIS>
        </PRODUTO>
        <!-- </xsl:if> -->
    </xsl:template>
</xsl:stylesheet>