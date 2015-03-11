<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" encoding="UTF-8" indent="yes" />
    <xsl:strip-space elements="*" />
    <xsl:param name="delim" select="string(';')" />
    <xsl:param name="quote" select="string('&quot;')" />
    <xsl:param name="break" select="string('&#xD;')" />
    <xsl:param name="header" select="string('pid;detail_url')" />
    <xsl:template match="/">
        <xsl:value-of select="$header" />
        <xsl:value-of select="$break" />
        <xsl:apply-templates select="products/product" />
    </xsl:template>
    <xsl:template match="product">
        <xsl:value-of select="code" />
        <xsl:value-of select="$delim" />
        <xsl:value-of select="url" />
        <xsl:value-of select="$break" />
    </xsl:template>
    <xsl:template match="value">
        <xsl:value-of select="normalize-space(.)" />
    </xsl:template>
    <xsl:template match="*">
        <xsl:value-of select="concat($quote, normalize-space(.), $quote)" />
        <xsl:if test="following-sibling::*">
            <xsl:value-of select="$delim" />
        </xsl:if>
    </xsl:template>
    <xsl:template match="text()" />
</xsl:stylesheet>