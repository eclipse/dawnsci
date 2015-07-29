<?xml version="1.0"?>
<!--
 Validator Generator for NXDL application files.
 
 Copyright (c) 2015 Diamond Light Source Ltd.
 All rights reserved. This program and the accompanying materials
 are made available under the terms of the Eclipse Public License v1.0
 which accompanies this distribution, and is available at
 http://www.eclipse.org/legal/epl-v10.html
-->
<!--
TODO: Usage 
 -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:xs="http://www.w3.org/2001/XMLSchema"
  xmlns:nx="http://definition.nexusformat.org/nxdl/@NXDL_RELEASE@"
  xmlns:dawnsci="urn:import:org.eclipse.dawnsci.nexus"
  exclude-result-prefixes="xs dawnsci">

<!-- The path containing the nxdl application definition files to transform -->
<xsl:param name="nxdlDefinitionsPath" select="'.'"/>
<!-- The path containing the Java source tree to write to -->
<xsl:param name="javaSourcePath" select="'.'"/>

<xsl:output name="text-format" method="text" omit-xml-declaration="yes" indent="no" />

<!-- Used for running with any XML input file -->
<xsl:template match="/">
	<xsl:call-template name="generate-java"/>
</xsl:template>

<xsl:template name="generate-java">
	<xsl:apply-templates select="collection(concat($nxdlDefinitionsPath, '/applications?select=*.nxdl.xml'))/nx:definition"/>
</xsl:template>

<xml:template match="nx:definition">
	<xsl:result-document href="{$javaSourcePath}/org/eclipse/dawnsci/nexus/{$validatorClassName}.java" format="text-format">
	
	</xsl:result-document>
</xml:template>
  
</xsl:stylesheet>