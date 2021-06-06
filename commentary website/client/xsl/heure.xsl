<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="ISO-8859-1" doctype-public="-//W3C//DTD HTML 4.01//EN" doctype-system="http://www.w3.org/TR/html4/strict.dtd" indent="yes" />
    <xsl:template match="t">
        <html>
            <body>
                <p>L'heure est la suivante :</p>
                <br />
                <xsl:apply-templates match="h" />
                <xsl:apply-templates match="m" />
                <!-- <xsl:apply-templates match="s" /> -->
            </body>
        </html>
    </xsl:template>
    <xsl:template match="h">
        <li>
            <xsl:value-of select="." />
            <xsl:text> "test" </xsl:text>
        </li>
    </xsl:template>
    <xsl:template match="m">
        <li>
            <xsl:value-of select="." />
            <xsl:text> lol </xsl:text>
        </li>
    </xsl:template>
    <xsl:template match="s">
        <li>
            <xsl:value-of select="." />
        </li>
    </xsl:template>
</xsl:stylesheet>