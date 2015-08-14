<?xml version="1.0"?>
<!-- 
 Java DawnSci Validator Generator for NXDL files
 
 Note the apparent untidy nature of some of the white space in this stylesheet is required
 in order to generate plain text with tidy white space.
 
 Copyright (c) 2015 Diamond Light Source Ltd.
 All rights reserved. This program and the accompanying materials
 are made available under the terms of the Eclipse Public License v1.0
 which accompanies this distribution, and is available at
 http://www.eclipse.org/legal/epl-v10.html
 -->
<!--
 To generate the Java validator classes, download and install Saxon XSL processor
 (version HE9-6-0-5J) from saxon.sf.net. Check out the NeXus format definitions from
 github.com/nexusformat/definitions. Then execute the command
   java -cp [/path/to/]saxon9he.jar net.sf.saxon.Tranform -xsl:/[/path/to/]NXDLJavaGenerator.xml -it:generate-validators nxdlDefinitionsPath=[/path/to/]nexus-definitions javaSourcePath=[/path/to]src
 -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:xs="http://www.w3.org/2001/XMLSchema"
 xmlns:nx="http://definition.nexusformat.org/nxdl/@NXDL_RELEASE@"
 xmlns:dawnsci="urn:import:org.eclipse.dawnsci.nexus"
 exclude-result-prefixes="xs dawnsci">

<!-- The path containing the nxdl definitions files to transform -->
<xsl:param name="nxdlDefinitionsPath" select="'.'"/>
<!-- The path containing the Java source tree to write to -->
<xsl:param name="javaSourcePath" select="'.'"/>

<xsl:output name="text-format" method="text" omit-xml-declaration="yes" indent="no"/>

<xsl:variable name="baseClasses" select="collection(concat($nxdlDefinitionsPath, '/base_classes?select=*.nxdl.xml'))/nx:definition"/>

<!-- Used for running with any XML input file -->
<xsl:template match="/">
	<xsl:call-template name="generate-validators"/>
</xsl:template>

<!-- Direct entry point -->
<xsl:template name="generate-validators">

	<!-- <xsl:apply-templates select="collection(concat($nxdlDefinitionsPath, '/applications?select=*.nxdl.xml'))/nx:definition"/> -->
	<xsl:apply-templates select="collection(concat($nxdlDefinitionsPath, '/applications?select=NXarpes.nxdl.xml'))/nx:definition"/>
</xsl:template>

<xsl:template match="nx:definition">

	<xsl:result-document href="{$javaSourcePath}/org/eclipse/dawnsci/nexus/validation/{@name}Validator.java" format="text-format">
<xsl:text>
package org.eclipse.dawnsci.nexus.validation;
</xsl:text>

<xsl:apply-templates mode="imports" select="."/>

public class <xsl:value-of select="@name"/>Validator extends AbstractNXValidator implements NXApplicationValidator {
	<xsl:variable name="baseClass" select="$baseClasses[@name = 'NXroot']"/>
	<xsl:variable name="validateGroupMethodNamePrefix" select="'validateGroup'"/>

	@Override
	public void validate(NXroot root) throws Exception {
		<xsl:apply-templates select="nx:group" mode="invocation">
			<xsl:with-param name="group" select="'root'"/>
			<xsl:with-param name="baseClass" select="$baseClass"/>
			<xsl:with-param name="validateGroupMethodNamePrefix" select="$validateGroupMethodNamePrefix"/>
		</xsl:apply-templates>
	}

<xsl:apply-templates select="nx:group" mode="implementation"><xsl:with-param name="validateGroupMethodNamePrefix" select="$validateGroupMethodNamePrefix"/></xsl:apply-templates>
	
}
	</xsl:result-document>

</xsl:template>

<!-- Template matches a group to add the invocation of the group's validate method. -->
<xsl:template match="nx:group" mode="invocation">
	<xsl:param name="baseClass"/>
	<xsl:param name="group" select="'group'"/>
	<xsl:param name="validateGroupMethodNamePrefix"/>
	
	<xsl:variable name="getChildGroupMethodName" select="concat('get', dawnsci:capitalise-first(substring(@type, 3)))"/>
	<xsl:text>		</xsl:text><xsl:value-of select="concat($validateGroupMethodNamePrefix, '_', @name, @type)"/>(<xsl:value-of select="$group"/>.<xsl:value-of select="$getChildGroupMethodName"/>());
</xsl:template>

<!-- Template matches a group to add the implementation of the group's validation method. -->
<xsl:template match="nx:group" mode="implementation">
	<xsl:param name="validateGroupMethodNamePrefix"/>
	<xsl:variable name="validateGroupMethodName" select="concat($validateGroupMethodNamePrefix, '_', @name, @type)"/>
	<xsl:variable name="baseClass" select="$baseClasses[@name = current()/@type]"/>
	
	/**
	 * Validate <xsl:value-of select="if (@name) then concat('group ''', @name, '''') else 'unnamed group'"/> of type <xsl:value-of select="@type"/>.
	 */
	public void <xsl:value-of select="$validateGroupMethodName"/>(final <xsl:value-of select="@type"/> group) throws Exception {
<xsl:apply-templates select="nx:attribute|nx:field">
			<xsl:with-param name="baseClass" select="$baseClass"/>
		</xsl:apply-templates>
		<xsl:apply-templates select="nx:group" mode="invocation">
			<xsl:with-param name="baseClass" select="$baseClass"/>
			<xsl:with-param name="validateGroupMethodNamePrefix" select="$validateGroupMethodName"/>
		</xsl:apply-templates>
	}
	
	<xsl:apply-templates select="nx:group" mode="implementation">
		<xsl:with-param name="validateGroupMethodNamePrefix" select="$validateGroupMethodName"/>
	</xsl:apply-templates>
</xsl:template>

<!-- Template matches an attribute to add method call to validate that attribute. -->
<xsl:template match="nx:attribute">
		// validate attribute '<xsl:value-of select="@name"/>'
		validateAttributeNotNull("<xsl:value-of select="@name"/>", group);
</xsl:template>

<!-- Template matches a field to add method calls to validate that field. -->
<xsl:template match="nx:field">
	<xsl:param name="baseClass"/>
	<xsl:variable name="baseClassFieldDef" select="$baseClass/nx:field[@name=current()/@name]"/>
	<xsl:variable name="getFieldDatasetMethod">
		<xsl:choose>
			<xsl:when test="$baseClassFieldDef">
				<xsl:value-of select="concat('get', dawnsci:capitalise-first(@name), '()')"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="concat('getDataset(&quot;', @name, '&quot;)')"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:variable>
		// <xsl:if test="not($baseClassFieldDef)">Note: field not defined in base class</xsl:if>
		final IDataset <xsl:value-of select="@name"/> = group.<xsl:value-of select="$getFieldDatasetMethod"/>;
		validateFieldNotNull("<xsl:value-of select="@name"/>)", <xsl:value-of select="@name"/>);<!--
		note: have to call-templates rather than apply-templates as type/unit/enumeration
		could be defined in the application definition only, the base class only or both.
		Output is only produced if either the application definition or base class defines a type/unit/enumeration -->
		<xsl:call-template name="validate-field-type">
			<xsl:with-param name="baseClassFieldDef" select="$baseClassFieldDef"/>
		</xsl:call-template>
		<xsl:call-template name="validate-field-units">
			<xsl:with-param name="baseClassFieldDef" select="$baseClassFieldDef"/>
		</xsl:call-template>
		<xsl:call-template name="validate-enumeration">
			<xsl:with-param name="baseClassFieldDef" select="$baseClassFieldDef"/>
		</xsl:call-template>
		<xsl:call-template name="validate-dimensions">
			<xsl:with-param name="baseClassFieldDef" select="$baseClassFieldDef"/>
		</xsl:call-template>
</xsl:template>

<!-- Template to validate the type of a field -->
<xsl:template name="validate-field-type">
	<xsl:param name="baseClassFieldDef"/>
	<xsl:variable name="type" select="if (@type) then @type else $baseClassFieldDef/@type"/>
		<xsl:if test="$type">validateFieldType("<xsl:value-of select="@name"/>)", <xsl:value-of select="@name"/>, <xsl:value-of select="$type"/>);
		</xsl:if>
</xsl:template>

<!-- Template to validate the units of a field -->
<xsl:template name="validate-field-units">
	<xsl:param name="baseClassFieldDef"/>
	<xsl:variable name="units" select="if (@units) then @units else $baseClassFieldDef/@units"/>
		<xsl:if test="$units">validateFieldUnits("<xsl:value-of select="@name"/>", <xsl:value-of select="@name"/>, <xsl:value-of select="$units"/>);
		</xsl:if>
</xsl:template>

<xsl:template name="validate-enumeration">
	<xsl:param name="baseClassFieldDef"/>
	<xsl:variable name="items" select="if (nx:enumeration) then nx:enumeration/nx:item else $baseClassFieldDef/nx:enumeration/nx:item"/>
	<xsl:if test="$items">validateFieldEnumeration("<xsl:value-of select="@name"/>", <xsl:value-of select="@name"/><xsl:for-each select="$items">,
			"<xsl:value-of select="@value"/>"</xsl:for-each>);
	</xsl:if>
</xsl:template>

<xsl:template name="validate-dimensions">
	<xsl:param name="baseClassFieldDef"/>
	<xsl:variable name="rank" select="if (nx:dimensions/@rank) then nx:dimensions/@rank else $baseClassFieldDef/nx:dimensions/@rank"/>
	<xsl:if test="$rank and not($rank = 'anyRank')">validateFieldRank("<xsl:value-of select="@name"/>", <xsl:value-of select="@name"/>, <xsl:value-of select="$rank"/>);
	</xsl:if>
	<xsl:variable name="dimsFromAppDef" select="nx:dimensions/nx:dim"/> 
	<xsl:variable name="dims" select="if ($dimsFromAppDef) then $dimsFromAppDef else $baseClassFieldDef/nx:dimensions/nx:dim"/>
	<xsl:if test="$dims">validateFieldDimensions("<xsl:value-of select="@name"/>", <xsl:value-of select="@name"/>,
			<xsl:value-of select="if ($dimsFromAppDef) then concat('&quot;', 'GROUP_NAME', '&quot;') else 'null'"/><xsl:for-each select="$dims">,
			<xsl:variable name="dimValue" select="if (number(@value) = number(@value)) then @value else concat('&quot;', @value, '&quot;')"/>
			<xsl:value-of select="$dimValue"/></xsl:for-each>);
	</xsl:if>
</xsl:template>

<!-- Imports -->
<xsl:template mode="imports" match="nx:definition">
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.nexus.NXroot;
<xsl:apply-templates select="//nx:group" mode="imports"/>
</xsl:template>

<xsl:template mode="imports" match="nx:group">import org.eclipse.dawnsci.nexus.<xsl:value-of select="@type"/>;
</xsl:template>

<!-- A function that takes a string and returns the same string but with the first letter capitalised. -->
<xsl:function name="dawnsci:capitalise-first" as="xs:string?">
	<xsl:param name="arg" as="xs:string?"/>
	<xsl:sequence select="concat(upper-case(substring($arg,1,1)), substring($arg,2))"/>
</xsl:function>

</xsl:stylesheet> 