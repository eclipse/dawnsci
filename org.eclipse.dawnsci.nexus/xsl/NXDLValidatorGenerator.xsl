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
	<xsl:apply-templates select="collection(concat($nxdlDefinitionsPath, '/applications?select=*.nxdl.xml'))/nx:definition"/>
</xsl:template>

<xsl:template match="nx:definition">
	<xsl:result-document href="{$javaSourcePath}/org/eclipse/dawnsci/nexus/validation/{@name}Validator.java" format="text-format">
		<xsl:text>package org.eclipse.dawnsci.nexus.validation;
</xsl:text>

		<xsl:apply-templates mode="imports" select="."/>

		<xsl:text>
/**
 * Validator for the application definition '</xsl:text><xsl:value-of select="@name"/><xsl:text>'.
 */
</xsl:text>
		<xsl:text>public class </xsl:text><xsl:value-of select="@name"/><xsl:text>Validator extends AbstractNXValidator implements NXApplicationValidator {
</xsl:text>

		<xsl:variable name="baseClass" select="$baseClasses[@name = 'NXroot']"/>
		<xsl:variable name="validateGroupMethodNamePrefix" select="'validateGroup'"/>
	
		<xsl:text>
	@Override
	public void validate(NXroot root) throws Exception {
</xsl:text>
	
		<xsl:apply-templates select="nx:group" mode="invocation">
			<xsl:with-param name="parentGroupVariableName" select="'root'"/>
			<xsl:with-param name="baseClass" select="$baseClass"/>
			<xsl:with-param name="validateGroupMethodNamePrefix" select="$validateGroupMethodNamePrefix"/>
		</xsl:apply-templates>
		<xsl:text>	}
</xsl:text>

		<xsl:apply-templates select="nx:group" mode="implementation">
			<xsl:with-param name="validateGroupMethodNamePrefix" select="$validateGroupMethodNamePrefix"/>
		</xsl:apply-templates>
	
	<xsl:text>}
</xsl:text>

	</xsl:result-document>
</xsl:template>


<!-- Template matches a group to add the invocation of the group's validate method. -->
<xsl:template match="nx:group" mode="invocation">
	<xsl:param name="baseClass"/>
	<xsl:param name="parentGroupVariableName" select="'group'"/>
	<xsl:param name="validateGroupMethodNamePrefix"/>
	
	<!-- Does the group have a name in the base class, if so use that as the method name, otherwise use the type. -->
	<xsl:variable name="baseClassGroupDef" select="$baseClass/nx:group[@name=current()/@name]"/>
	<xsl:variable name="groupName" select="if ($baseClassGroupDef) then @name else substring(@type, 3)"/>
	<xsl:variable name="multiple" select="not(@name) and not(@maxOccurs='1')"/>
	<xsl:variable name="optional" select="not($multiple) and @minOccurs='0'"/>
	
	<!-- Line comment: validate (optional?) (unnamed?) group (<name>?) of type <type> ((possibly multiple)?) -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>// validate </xsl:text><xsl:if test="$optional">optional </xsl:if>
	<xsl:value-of select="if (@name) then concat('child group ''', @name, '''') else 'unnamed child group'"/>
	<xsl:text> of type </xsl:text><xsl:value-of select="@type"/>
	<xsl:if test="$multiple"> (possibly multiple)</xsl:if>
	<xsl:text>
</xsl:text>

	<!-- Variable for method call to get group (or just group name if multiple) -->
	<xsl:variable name="group">
		<xsl:choose>
			<xsl:when test="$multiple">
				<xsl:value-of select="$groupName"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$parentGroupVariableName"/>
				<xsl:text>.get</xsl:text><xsl:value-of select="dawnsci:capitalise-first($groupName)"/>
				<xsl:text>()</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:variable>
	
	<xsl:if test="$multiple">
		<!-- Line to get the map of all groups of the given type, e.g. final Map<String, NXSample> allSample = group.getAllSample() -->
		<xsl:value-of select="dawnsci:tabs(2)"/>
		<xsl:text>final Map&lt;String, </xsl:text><xsl:value-of select="@type"/>
		<xsl:text>&gt; all</xsl:text><xsl:value-of select="dawnsci:capitalise-first($groupName)"/>
		<xsl:text> = </xsl:text>
		<xsl:value-of select="$parentGroupVariableName"/>
		<xsl:text>.getAll</xsl:text><xsl:value-of select="dawnsci:capitalise-first($groupName)"/><xsl:text>();
</xsl:text>
		<!-- For loop over values -->
		<xsl:value-of select="dawnsci:tabs(2)"/>
		<xsl:text>for (final </xsl:text><xsl:value-of select="@type"/><xsl:text> </xsl:text><xsl:value-of select="$groupName"/>
		<xsl:text> : all</xsl:text><xsl:value-of select="dawnsci:capitalise-first($groupName)"/>
		<xsl:text>.values()) {
</xsl:text>
	</xsl:if>
	<xsl:if test="$optional">
		<!-- Null test for optional groups -->
		<xsl:value-of select="dawnsci:tabs(2)"/>
		<xsl:text>if (</xsl:text><xsl:value-of select="$group"/>
		<xsl:text> != null) {
</xsl:text>
	</xsl:if>
	<xsl:value-of select="dawnsci:tabs(if ($multiple or $optional) then 3 else 2)"/>
	<xsl:value-of select="dawnsci:validateGroupMethodName($validateGroupMethodNamePrefix, current())"/>
	<xsl:text>(</xsl:text>
	<xsl:value-of select="$group"/>
	<xsl:text>);
</xsl:text>
	<xsl:if test="$multiple or $optional"> <!-- Closing brace for either for loop (multiple) or null test (optional) -->
		<xsl:value-of select="dawnsci:tabs(2)"/>
		<xsl:text>}
</xsl:text>
	</xsl:if>
	<xsl:text>
</xsl:text>
</xsl:template>

<!-- Template matches a group to add the implementation of the group's validation method. -->
<xsl:template match="nx:group" mode="implementation">
	<xsl:param name="validateGroupMethodNamePrefix"/>
	<xsl:variable name="validateGroupMethodName" select="dawnsci:validateGroupMethodName($validateGroupMethodNamePrefix, current())"/>
	<xsl:variable name="baseClass" select="$baseClasses[@name = current()/@type]"/>
	<xsl:text>
	/**
	 * Validate </xsl:text><xsl:if test="@minOccurs='0'">optional </xsl:if><xsl:value-of select="if (@name) then concat('group ''', @name, '''') else 'unnamed group'"/> of type <xsl:value-of select="@type"/><xsl:text>.
	 */
	private void </xsl:text><xsl:value-of select="$validateGroupMethodName"/>(final <xsl:value-of select="@type"/><xsl:text> group) throws Exception {
</xsl:text>
		<xsl:value-of select="dawnsci:tabs(2)"/>
		<xsl:text>// validate that the group is not null
</xsl:text>
		<xsl:value-of select="dawnsci:tabs(2)"/>
		<xsl:text>validateGroupNotNull(</xsl:text><xsl:value-of select="if (@name) then concat('&quot;', @name, '&quot;') else 'null'"/>, <xsl:value-of select="@type"/><xsl:text>.class, group);
</xsl:text>
		<xsl:if test="$baseClass//nx:dim">
			<xsl:value-of select="dawnsci:tabs(2)"/>
			<xsl:text>clearLocalGroupDimensionPlaceholderValues();</xsl:text>
		</xsl:if>
		<xsl:text>
</xsl:text>
		<xsl:apply-templates select="nx:attribute|nx:field">
			<xsl:with-param name="baseClass" select="$baseClass"/>
		</xsl:apply-templates>
		<xsl:apply-templates select="nx:group" mode="invocation">
			<xsl:with-param name="baseClass" select="$baseClass"/>
			<xsl:with-param name="validateGroupMethodNamePrefix" select="$validateGroupMethodName"/>
		</xsl:apply-templates>
	<xsl:text>
	}
</xsl:text>
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
	
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>// validate </xsl:text><xsl:if test="@minOccurs='0'">optional </xsl:if>field '<xsl:value-of select="@name"/>' of <xsl:value-of select="if (@type) then concat('type ', @type) else 'unknown type'"/><xsl:text>.</xsl:text>
		<xsl:if test="not($baseClassFieldDef)"> Note: field not defined in base class.</xsl:if>
		final IDataset <xsl:value-of select="@name"/> = group.<xsl:value-of select="$getFieldDatasetMethod"/><xsl:text>;
</xsl:text>
		<xsl:choose>
			<xsl:when test="@minOccurs='0'">
				<xsl:text>		if (</xsl:text><xsl:value-of select="@name"/><xsl:text> != null) {
</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>		validateFieldNotNull("</xsl:text><xsl:value-of select="@name"/>)", <xsl:value-of select="@name"/><xsl:text>);
</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
		<!-- Note: have to call-templates rather than apply-templates as type/unit/enumeration
		could be defined in the application definition only, the base class only or both.
		Output is only produced if either the application definition or base class defines a type/unit/enumeration -->
		<xsl:variable name="tabLevel" select="if (@minOccurs='0') then 3 else 2"/>

		<xsl:call-template name="validate-field-type">
			<xsl:with-param name="baseClassFieldDef" select="$baseClassFieldDef"/>
			<xsl:with-param name="tabLevel" select="$tabLevel"/>
		</xsl:call-template>

		<xsl:call-template name="validate-field-units">
			<xsl:with-param name="baseClassFieldDef" select="$baseClassFieldDef"/>
			<xsl:with-param name="tabLevel" select="$tabLevel"/>
		</xsl:call-template>

		<xsl:call-template name="validate-enumeration">
			<xsl:with-param name="baseClassFieldDef" select="$baseClassFieldDef"/>
			<xsl:with-param name="tabLevel" select="$tabLevel"/>
		</xsl:call-template>

		<xsl:call-template name="validate-dimensions">
			<xsl:with-param name="baseClassFieldDef" select="$baseClassFieldDef"/>
			<xsl:with-param name="tabLevel" select="$tabLevel"/>
		</xsl:call-template>

		<!-- Closing brace for null test (only if minOccurs="0") -->
		<xsl:if test="@minOccurs='0'">
			<xsl:text>		}
</xsl:text>
		</xsl:if>
		<!-- Blank line before validating next field -->
		<xsl:text>
</xsl:text>
</xsl:template>

<!-- Template to validate the type of a field -->
<xsl:template name="validate-field-type">
	<xsl:param name="baseClassFieldDef"/>
	<xsl:param name="tabLevel"/>
	<xsl:variable name="type" select="if (@type) then @type else $baseClassFieldDef/@type"/>

	<xsl:if test="$type">
		<xsl:value-of select="dawnsci:tabs($tabLevel)"/>
		<xsl:text>validateFieldType("</xsl:text><xsl:value-of select="@name"/>)", <xsl:value-of select="@name"/>, <xsl:value-of select="$type"/><xsl:text>);
</xsl:text>
	</xsl:if>
</xsl:template>

<!-- Template to validate the units of a field -->
<xsl:template name="validate-field-units">
	<xsl:param name="baseClassFieldDef"/>
	<xsl:param name="tabLevel"/>
	
	<xsl:variable name="units" select="if (@units) then @units else $baseClassFieldDef/@units"/>
		<xsl:if test="$units">
			<xsl:value-of select="dawnsci:tabs($tabLevel)"/>
			<xsl:text>validateFieldUnits("</xsl:text><xsl:value-of select="@name"/>", <xsl:value-of select="@name"/>, <xsl:value-of select="$units"/><xsl:text>);
</xsl:text>
		</xsl:if>
</xsl:template>

<!-- Template to validate the permitted values of an enumeration -->
<xsl:template name="validate-enumeration">
	<xsl:param name="baseClassFieldDef"/>
	<xsl:param name="tabLevel"/>

	<xsl:variable name="items" select="if (nx:enumeration) then nx:enumeration/nx:item else $baseClassFieldDef/nx:enumeration/nx:item"/>
	<xsl:if test="$items">
			<xsl:value-of select="dawnsci:tabs($tabLevel)"/>
			<xsl:text>validateFieldEnumeration("</xsl:text><xsl:value-of select="@name"/>", <xsl:value-of select="@name"/><xsl:for-each select="$items"><xsl:text>,
</xsl:text>
			<xsl:value-of select="dawnsci:tabs($tabLevel + 2)"/>
			<xsl:text>"</xsl:text><xsl:value-of select="@value"/>"</xsl:for-each><xsl:text>);
</xsl:text>
	</xsl:if>
</xsl:template>

<!-- Template to validate the dimensions of a field, possibly including the rank and shape -->
<xsl:template name="validate-dimensions">
	<xsl:param name="baseClassFieldDef"/>
	<xsl:param name="tabLevel"/>
	
	<!-- Output the invocation of validateFieldRank to validate the rank of the field's dataset -->
	<xsl:variable name="rank" select="if (nx:dimensions/@rank) then nx:dimensions/@rank else $baseClassFieldDef/nx:dimensions/@rank"/>
	<xsl:if test="$rank and (number($rank) = number($rank))">
		<xsl:value-of select="dawnsci:tabs($tabLevel)"/>
		<xsl:text>validateFieldRank("</xsl:text><xsl:value-of select="@name"/>", <xsl:value-of select="@name"/>, <xsl:value-of select="$rank"/><xsl:text>);
</xsl:text>
	</xsl:if>
	
	<!-- Output the invocation of validateFieldDimensions to validate the size of each dimension (where specified). -->
	<xsl:variable name="dimsFromAppDef" select="nx:dimensions/nx:dim"/> 
	<xsl:variable name="dims" select="if ($dimsFromAppDef) then $dimsFromAppDef else $baseClassFieldDef/nx:dimensions/nx:dim"/>
	<xsl:if test="$dims">
		<xsl:value-of select="dawnsci:tabs($tabLevel)"/>
		<xsl:text>validateFieldDimensions("</xsl:text><xsl:value-of select="@name"/>", <xsl:value-of select="@name"/><xsl:text>, </xsl:text>
		<xsl:value-of select="if ($dimsFromAppDef) then 'null' else concat('&quot;', ../@type, '&quot;')"/>
		<xsl:for-each select="$dims"><xsl:text>, </xsl:text>
		<xsl:variable name="dimValue" select="if (number(@value) = number(@value)) then @value else concat('&quot;', @value, '&quot;')"/>
		<xsl:value-of select="$dimValue"/></xsl:for-each><xsl:text>);
</xsl:text>
	</xsl:if>
</xsl:template>

<!-- Imports -->
<xsl:template mode="imports" match="nx:definition">
<xsl:text>
import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;
import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.nexus.NXroot;
</xsl:text>
<xsl:if test="//nx:group[not(@name) and not(@maxOccurs='1')]">
import java.util.Map;
</xsl:if>
<xsl:apply-templates select="//nx:group" mode="imports"/>
<xsl:text>
</xsl:text>
</xsl:template>

<!-- A template that matches groups and returns an import statement for the base class for that group -->
<xsl:template mode="imports" match="nx:group">import org.eclipse.dawnsci.nexus.<xsl:value-of select="@type"/>;
</xsl:template>

<!-- A function to insert n tab characters into the output document. -->
<xsl:function name="dawnsci:tabs" as="xs:string">
	<xsl:param name="count" as="xs:integer"/>
	
	<xsl:sequence select="string-join((for $i in 1 to $count return '&#009;'), '')"/>
</xsl:function>

<!-- A function that takes a string and returns the same string but with the first letter capitalised. -->
<xsl:function name="dawnsci:capitalise-first" as="xs:string?">
	<xsl:param name="arg" as="xs:string?"/>
	<xsl:sequence select="concat(upper-case(substring($arg,1,1)), substring($arg,2))"/>
</xsl:function>

<!-- A function to get the name of the validate method for a group -->
<xsl:function name="dawnsci:validateGroupMethodName" as="xs:string">
	<xsl:param name="validateGroupMethodNamePrefix" as="xs:string"/>
	<xsl:param name="group" as="node()"/>
	<xsl:variable name="newSegment" select="if ($group/@name) then $group/@name else $group/@type"/>
	<xsl:sequence select="concat($validateGroupMethodNamePrefix, '_', $newSegment)"/>
</xsl:function>

</xsl:stylesheet> 