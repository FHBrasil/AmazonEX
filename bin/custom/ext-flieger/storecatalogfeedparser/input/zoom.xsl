﻿<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="utf-8" />
    <xsl:output cdata-section-elements="sku" />
    <xsl:output cdata-section-elements="url" />
    <xsl:output cdata-section-elements="nome" />
    <xsl:output cdata-section-elements="departamento" />
    <xsl:output cdata-section-elements="descricao" />
    <xsl:output cdata-section-elements="imagem" />
    <xsl:template match="/">
        <produtos>
            <xsl:apply-templates select="products/product" />
        </produtos>
    </xsl:template>
    <xsl:template match="product">
        <produto>
            <sku>
                <xsl:value-of select="code" />
            </sku>
            <url>
                <xsl:value-of select="url" />
            </url>
            <nome>
                <xsl:value-of select="name" />
            </nome>
            <departamento>
                <xsl:value-of select="categories[1]" />
            </departamento>
            <preco_de>
                <xsl:value-of select="previous_price" />
            </preco_de>
            <preco>
                <xsl:value-of select="price" />
            </preco>
            <nparcela>
                <xsl:value-of select="installments_quantity" />
            </nparcela>
            <vparcela>
                <xsl:value-of select="installments_amount" />
            </vparcela>
            <descricao>
                <xsl:value-of select="description" />
            </descricao>
            <imagem>
                <xsl:value-of select="main_image_url" />
            </imagem>
        </produto>
    </xsl:template>
</xsl:stylesheet>