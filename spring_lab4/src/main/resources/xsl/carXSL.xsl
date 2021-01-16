<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" omit-xml-declaration="yes"/>
	<xsl:template match="/">
		<html>
			<head>
				<meta charset="UTF-8"/>
				<title>Cars</title>
				<link rel="stylesheet" href="/static/style.css"/>
			</head>
			<body>
				<div class="header">
					<div class="header-link">
						<a href="/">Main</a>
					</div>
					<div class="header-link">
						<a href="/xml/banks" >Banks</a>
					</div>
					<div class="header-link">
						<a href="/xml/persons">Persons</a>
					</div>
					<div class="header-link current-link">
						<a href="/xml/cars">Cars</a>
					</div>
				</div>
				<table border="1" style="margin-top: 5px">
					<tr bgcolor="#CCCCCC">
						<td><strong>Car_id</strong></td>
						<td><strong>Brand</strong></td>
						<td><strong>Color</strong></td>
					</tr>
					<xsl:for-each select="ArrayList/item">
						<tr>
							<td><xsl:value-of select="car_id"/></td>
							<td><xsl:value-of select="brand"/></td>
							<td><xsl:value-of select="color"/></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>