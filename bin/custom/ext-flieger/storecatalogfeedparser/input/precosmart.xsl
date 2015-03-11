<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="utf-8" standalone="yes" />
    <xsl:template match="/">
        <productos>
            <xsl:apply-templates select="products/product" />
        </productos>
    </xsl:template>
    <xsl:template match="product">
        <!-- <xsl:if test="price != '' and isParentProduct = 'false'"> -->
        <produto>
            <titulo>
                <xsl:value-of select="name" />
            </titulo>
            <descricao>
                <xsl:value-of select="description" />
            </descricao>
            <imagem>
                <xsl:value-of select="main_image_url" />
            </imagem>
            <categoria>
                <xsl:value-of select="categories[1]" />
            </categoria>
            <id>
                <xsl:value-of select="code" />
            </id>
            <url>
                <xsl:value-of select="url" />
            </url>
            <preco>
                <xsl:value-of select="price" />
            </preco>
            <marca>
                <xsl:value-of select="manufacturer" />
            </marca>
            <cor></cor>
            <tipo_produto></tipo_produto>
            <xsl:if test="previous_price != '' and previous_price != price">
                <preco_original>
                    <xsl:value-of select="previous_price" />
                </preco_original>
            </xsl:if>
            <numero_parcelas>
                <xsl:value-of select="installments_quantity" />
            </numero_parcelas>
            <parcela>
                <xsl:value-of select="installments_amount" />
            </parcela>
        </produto>
        <!-- </xsl:if> -->
    </xsl:template>
</xsl:stylesheet>