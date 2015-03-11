<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="utf-8" standalone="yes" />
    <xsl:template match="/">
        <Loja>
            <xsl:attribute name="nome">
				 <xsl:value-of select="products/storeName" />
				 <xsl:text>webstore</xsl:text>
			</xsl:attribute>
            <xsl:apply-templates select="products/product" />
        </Loja>
    </xsl:template>
    <xsl:template match="product">
        <!-- <xsl:if test="productTotalStock > 0 and firstVariantPrice > 
            0"> -->
        <curproduto>
            <idproduto>
                <xsl:value-of select="code" />
            </idproduto>
            <link>
                <xsl:value-of select="url" />
            </link>
            <imagem>
                <xsl:value-of select="main_image_url" />
            </imagem>
            <categoria>
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
                ;
                <xsl:value-of select="categories[1]" />
            </categoria>
            <nomeproduto>
                <xsl:value-of select="name" />
                [ ] [
                <xsl:template match="variants/variant">
                    <xsl:value-of select="variant_size" />
                </xsl:template>
                ]
            </nomeproduto>
            <descproduto>
                <xsl:value-of select="description" />
            </descproduto>
            <valor>
                <xsl:value-of select="price" />
            </valor>
            <cor>
                <xsl:value-of select="availableVariantColors" />
            </cor>
            <tamanho>
                <xsl:template match="variants/variant">
                    <xsl:value-of select="variant_size" />
                </xsl:template>
            </tamanho>
        </curproduto>
        <!-- </xsl:if> -->
    </xsl:template>
</xsl:stylesheet>