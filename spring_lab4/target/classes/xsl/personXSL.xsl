<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" omit-xml-declaration="yes"/>
	<xsl:template match="/">
		<html>
			<head>
				<meta charset="UTF-8"/>
				<title>Persons</title>
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
					<div class="header-link current-link">
						<a href="/xml/persons">Persons</a>
					</div>
					<div class="header-link">
						<a href="/xml/cars">Cars</a>
					</div>
				</div>
				<table border="1" style="margin-top: 5px">
					<tr bgcolor="#CCCCCC">
						<td><strong>Id</strong></td>
						<td><strong>Name</strong></td>
						<td><strong>First_name</strong></td>
						<td><strong>City</strong></td>
						<td><strong>Birthday</strong></td>
						<td><strong>Car</strong></td>
						<td><strong>Bank</strong></td>
					</tr>
					<xsl:for-each select="ArrayList/item">
						<tr>
							<td><xsl:value-of select="id"/></td>
							<td><xsl:value-of select="name"/></td>
							<td><xsl:value-of select="first_name"/></td>
							<td><xsl:value-of select="city"/></td>
							<td><xsl:value-of select="birthday"/></td>
							<td>
								<xsl:value-of select="car/brand"/>
								<xsl:text xml:space="preserve"> </xsl:text>
								<xsl:value-of select="car/color"/>
							</td>
							<td>
								<xsl:value-of select="bank/account_year"/>
								<xsl:text xml:space="preserve"> </xsl:text>
								<xsl:value-of select="bank/number_card"/>
								<xsl:text xml:space="preserve"> </xsl:text>
								<xsl:value-of select="bank/manager_name"/>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>