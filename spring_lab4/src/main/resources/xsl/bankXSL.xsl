<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" omit-xml-declaration="yes"/>
	<xsl:template match="/">
		<html>
			<head>
				<meta charset="UTF-8"/>
				<title>Banks</title>
				<link rel="stylesheet" href="/static/style.css"/>
			</head>
			<body>
                <div class="header">
					<div class="header-link">
						<a href="/">Main</a>
					</div>
                    <div class="header-link current-link">
                        <a href="/xml/banks" >Banks</a>
                    </div>
                    <div class="header-link">
                        <a href="/xml/persons">Persons</a>
                    </div>
                    <div class="header-link">
                        <a href="/xml/cars">Cars</a>
                    </div>
                </div>
				<table border="1" style="margin-top: 5px">
					<tr bgcolor="#CCCCCC">
						<td><strong>Account_num</strong></td>
						<td><strong>Account_year</strong></td>
						<td><strong>Number_card</strong></td>
						<td><strong>Manager_name</strong></td>
					</tr>
					<xsl:for-each select="ArrayList/item">
						<tr>
							<td><xsl:value-of select="account_num"/></td>
							<td><xsl:value-of select="account_year"/></td>
							<td><xsl:value-of select="number_card"/></td>
							<td><xsl:value-of select="manager_name"/></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>