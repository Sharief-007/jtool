<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version = "1.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">  
	<xsl:template match="/">
		<xsl:for-each select="cfdi-Complemento/pago10-Pagos">
			<xsl:for-each select="pago10-Pago/pago10-DoctoRelacionado">
				<p>Invoice Amount : <xsl:value-of select="@ImpSaldoAnt"/></p>
				<p>Payment Amount : <xsl:value-of select="@ImpPagado"/>	</p>
				<p>Due Amount : <xsl:value-of select="@ImpSaldoInsoluto"/></p>
			</xsl:for-each>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>