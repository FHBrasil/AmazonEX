<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="UTF-8" indent="yes" />
	<xsl:strip-space elements="*" />
	<xsl:template match="/">	
		<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9" >
			<xsl:apply-templates select="products/product" />
		</urlset>
	</xsl:template>
	<xsl:template match="product">
		<url>
			<loc><xsl:value-of select="url"/></loc>
			<changefreq>weekly</changefreq>
			<lastmod><xsl:value-of select="lastmod"/></lastmod>
			<priority>0.9</priority>
		</url>
	</xsl:template>	
</xsl:stylesheet>