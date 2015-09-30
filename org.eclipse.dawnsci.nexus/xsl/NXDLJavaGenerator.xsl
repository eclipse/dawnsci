<?xml version="1.0"?>
<!--
 Java DawnSci Code Generator for NXDL files
 
 Note the apparent untidy nature of some of the white space in this stylesheet is required
 in order to generate plain text with tidy white space.
 
 Copyright (c) 2015 Diamond Light Source Ltd.
 All rights reserved. This program and the accompanying materials
 are made available under the terms of the Eclipse Public License v1.0
 which accompanies this distribution, and is available at
 http://www.eclipse.org/legal/epl-v10.html
 -->
<!--
 To generate the Java interfaces and classes, download and install Saxon XSL processor
 (version HE9-6-0-5J) from saxon.sf.net. Check out the NeXus format definitions from
 github.com/nexusformat/definitions. Then execute the command
   java -cp [/path/to/]saxon9he.jar net.sf.saxon.Transform -xsl:[/path/to/]NXDLJavaGenerator.xsl -it:generate-java nxdlDefinitionsPath=[/path/to/]nexus-definitions javaSourcePath=[/path/to/]src
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

<!-- Find the NeXus classes to generate Java for. -->
<xsl:variable name="base-classes" select="collection(concat($nxdlDefinitionsPath, '/base_classes?select=*.nxdl.xml'))/nx:definition[@name!='NXobject']"/>
<xsl:variable name="appdef-classes" select="collection(concat($nxdlDefinitionsPath, '/applications?select=*.nxdl.xml'))/nx:definition[not(nx:group[@type='NXentry'])]"/>
<xsl:variable name="contributed-classes" select="collection(concat($nxdlDefinitionsPath, '/contributed_definitions?select=*.nxdl.xml'))/nx:definition[not(nx:group[@type='NXentry'])]"/>
<xsl:variable name="nexus-classes" select="$base-classes, $appdef-classes, $contributed-classes"/>

<xsl:output name="text-format" method="text" omit-xml-declaration="yes" indent="no"/>

<!-- Used for running with any XML input file -->
<xsl:template match="/">
	<xsl:call-template name="generate-java"/>
</xsl:template>

<!-- Direct entry point -->
<xsl:template name="generate-java">

	<!-- Generate a Java interface for each NeXus class -->
	<xsl:apply-templates mode="interface" select="$nexus-classes"/>

	<!-- Generate a concrete Java class implementing that interface for each NeXus class -->
	<xsl:apply-templates mode="class" select="$nexus-classes"/>
	
	<xsl:call-template name="base-class-enum"/>
	
	<xsl:call-template name="factory-class"/>
	
</xsl:template>


<!-- Java interface file -->

<xsl:template mode="interface" match="nx:definition">
	<xsl:variable name="interfaceName" select="dawnsci:interface-name(@name)"/>
	
	<xsl:result-document href="{$javaSourcePath}/org/eclipse/dawnsci/nexus/{$interfaceName}.java" format="text-format">
		<xsl:value-of select="$fileHeaderComment"/>
package org.eclipse.dawnsci.nexus;
<xsl:apply-templates mode="imports" select="."/>

/**<xsl:apply-templates select="nx:doc"/><xsl:apply-templates select="nx:symbols"/>
 * <xsl:apply-templates select="@version|@deprecated"/>
 */<xsl:apply-templates mode="typeAnnotations" select="."/>
public interface <xsl:value-of select="$interfaceName"/><xsl:apply-templates mode="interface" select="@extends"/> {
<xsl:apply-templates mode="interface" select="*[not(self::nx:doc)][not(self::nx:symbols)]"/>
}
</xsl:result-document>

</xsl:template>

<!-- Interface get methods -->
<xsl:template mode="interface" match="
	nx:definition/nx:attribute
	| nx:definition/nx:field[not(@name = preceding-sibling::nx:field/@name)]
 	| nx:field/nx:attribute
 	| nx:definition/nx:group[@name or not(@type = preceding-sibling::nx:group[not(@name)]/@type)]">

	<xsl:variable name="fieldName"><xsl:apply-templates select="." mode="fieldName"/></xsl:variable>

	<xsl:variable name="fieldType"><xsl:apply-templates select="." mode="fieldType"/></xsl:variable>
	<xsl:variable name="extendedFieldType"><xsl:apply-templates select="." mode="extendedFieldType"/></xsl:variable>

	<xsl:variable name="methodNameSuffix"><xsl:apply-templates select="." mode="methodNameSuffix">
		<xsl:with-param name="fieldName" select="$fieldName"/>
	</xsl:apply-templates></xsl:variable>
	/**<xsl:apply-templates select="nx:doc"/><xsl:if test="self::nx:field/@type|@units|nx:dimensions|nx:enumeration">
	 * &lt;p><xsl:apply-templates select="self::nx:field/@type|@units|nx:dimensions|nx:enumeration"/>
	 * &lt;/p></xsl:if>
	 * <xsl:apply-templates select="@deprecated"/>
	 * @return  the value.
	 */<xsl:apply-templates mode="methodAnnotations" select="."/>
	public <xsl:value-of select="$fieldType"/> get<xsl:value-of select="$methodNameSuffix"/>();
<xsl:if test="self::nx:field[@nameType = 'any']">  <!-- All fields with nameType="any" -->
	/**
	 * Get all <xsl:value-of select="$methodNameSuffix"/> fields:
	 *<xsl:apply-templates select="nx:doc"/><xsl:if test="self::nx:field/@type|@units|nx:dimensions|nx:enumeration">
	 * &lt;p><xsl:apply-templates select="self::nx:field/@type|@units|nx:dimensions|nx:enumeration"/>
	 * &lt;/p></xsl:if>
	 * <xsl:apply-templates select="@deprecated"/>
	 * @return  a map from node names to the <xsl:value-of select="$extendedFieldType"/> for that node.
	 */<xsl:apply-templates mode="methodAnnotations" select="."/>
	public Map&lt;String, <xsl:value-of select="$extendedFieldType"/>> getAll<xsl:value-of select="$methodNameSuffix"/>();
</xsl:if>
<xsl:if test="self::nx:group[not(@name)]">  <!-- All unnamed groups could be repeated (unless @maxOccurs=1 - rare) -->
	/**
	 * Get a <xsl:value-of select="$fieldType"/> node by name:
	 * &lt;ul><xsl:for-each select="../nx:group[@type = current()/@type]">
	 * &lt;li><xsl:apply-templates select="nx:doc"/>&lt;/li></xsl:for-each>
	 * &lt;/ul>
	 * <xsl:apply-templates select="@deprecated"/>
	 * @param name  the name of the node.
	 * @return  a map from node names to the <xsl:value-of select="$fieldType"/> for that node.
	 */<xsl:apply-templates mode="methodAnnotations" select="."/>
	public <xsl:value-of select="$fieldType"/> get<xsl:value-of select="$methodNameSuffix"/>(String name);
	
	/**
	 * Get all <xsl:value-of select="$fieldType"/> nodes:
	 * &lt;ul><xsl:for-each select="../nx:group[@type = current()/@type]">
	 * &lt;li><xsl:apply-templates select="nx:doc"/>&lt;/li></xsl:for-each>
	 * &lt;/ul>
	 * <xsl:apply-templates select="@deprecated"/>
	 * @return  a map from node names to the <xsl:value-of select="$fieldType"/> for that node.
	 */<xsl:apply-templates mode="methodAnnotations" select="."/>
	public Map&lt;String, <xsl:value-of select="$fieldType"/>> getAll<xsl:value-of select="$methodNameSuffix"/>();
</xsl:if>
<xsl:apply-templates mode="interface" select="*[not(self::nx:doc)][not(self::nx:dimensions)][not(self::nx:enumeration)]"/>
</xsl:template>


<!-- Java class implementation file -->

<xsl:template mode="class" match="nx:definition">

	<xsl:variable name="interfaceName" select="dawnsci:interface-name(@name)"/>
	<xsl:variable name="className" select="dawnsci:class-name(@name)"/>
	
	<xsl:result-document href="{$javaSourcePath}/org/eclipse/dawnsci/nexus/impl/{$className}.java" format="text-format">
		<xsl:value-of select="$fileHeaderComment"/>
package org.eclipse.dawnsci.nexus.impl;
<xsl:apply-templates mode="imports" select="."/>

import org.eclipse.dawnsci.nexus.*;

/**<xsl:apply-templates select="nx:doc"/>
 * <xsl:apply-templates select="@version|@deprecated"/>
 */<xsl:apply-templates mode="typeAnnotations" select="."/>
public class <xsl:value-of select="$className"/><xsl:apply-templates mode="class" select="@extends"/> implements <xsl:value-of select="$interfaceName"/> {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible
<xsl:apply-templates mode="classFields" select="*"/>

	protected <xsl:value-of select="$className"/>(long oid) {
		super(oid);
	}

	@Override
	public Class&lt;? extends NXobject> getNXclass() {
		return <xsl:value-of select="$interfaceName"/>.class;
	}
	
	@Override
	public NXbaseClass getNXbaseClass() {
		return NXbaseClass.<xsl:value-of select="dawnsci:base-class-enum-name(@name)"/>;
	}
<xsl:apply-templates mode="class" select="*[not(self::nx:doc)][not(self::nx:symbols)]"/>
}
</xsl:result-document>

</xsl:template>

<!-- Class fields -->
<xsl:template mode="classFields" match="
	nx:definition/nx:attribute
	| nx:definition/nx:field[not(@name = preceding-sibling::nx:field/@name)]
 	| nx:field/nx:attribute">
	<xsl:variable name="fieldName">
		<xsl:apply-templates select="." mode="fieldName"/>
	</xsl:variable>
	<xsl:variable name="fieldLabel">
		<xsl:apply-templates select="." mode="fieldLabel">
			<xsl:with-param name="fieldName" select="$fieldName"/>
		</xsl:apply-templates>
	</xsl:variable>
	public static final String <xsl:value-of select="$fieldLabel"/> = "<xsl:value-of select="$fieldName"/><xsl:text>";</xsl:text>
	<xsl:apply-templates mode="classFields" select="*"/>
</xsl:template>

<xsl:template mode="classFields" match="*"/>

<!-- Class get/set methods -->
<xsl:template mode="class" match="
	nx:definition/nx:attribute
	| nx:definition/nx:field[not(@name = preceding-sibling::nx:field/@name)]
 	| nx:field/nx:attribute
 	| nx:definition/nx:group[@name or not(@type = preceding-sibling::nx:group[not(@name)]/@type)]">

	<xsl:variable name="fieldName"><xsl:apply-templates select="." mode="fieldName"/></xsl:variable>
	<xsl:variable name="validJavaFieldName">
		<xsl:choose>
			 <xsl:when test="$fieldName='default'"><xsl:value-of select="concat($fieldName, '_')"/></xsl:when>
			 <xsl:otherwise><xsl:value-of select="$fieldName"/></xsl:otherwise>
		</xsl:choose>
	</xsl:variable>
	
	<xsl:variable name="fieldLabel"><xsl:apply-templates select="." mode="fieldLabel">
		<xsl:with-param name="fieldName" select="$fieldName"/>
	</xsl:apply-templates></xsl:variable>
	
	<xsl:variable name="fieldType"><xsl:apply-templates select="." mode="fieldType"/></xsl:variable>
	<xsl:variable name="extendedFieldType"><xsl:apply-templates select="." mode="extendedFieldType"/></xsl:variable>
	
	<xsl:variable name="methodNameSuffix"><xsl:apply-templates select="." mode="methodNameSuffix">
		<xsl:with-param name="fieldName" select="$fieldName"/>
	</xsl:apply-templates></xsl:variable>
	@Override<xsl:apply-templates mode="methodAnnotations" select="."/>
	public <xsl:value-of select="$fieldType"/> get<xsl:value-of select="$methodNameSuffix"/>() {<xsl:apply-templates select="." mode="getMethod">
		<xsl:with-param name="fieldName" select="$fieldName"/>
		<xsl:with-param name="fieldLabel" select="$fieldLabel"/>
		<xsl:with-param name="fieldType" select="$fieldType"/>
	</xsl:apply-templates>
	}
<xsl:apply-templates mode="methodAnnotations" select="."/>
	public void set<xsl:value-of select="$methodNameSuffix"/>(<xsl:value-of select="$fieldType"/><xsl:text> </xsl:text><xsl:value-of select="$validJavaFieldName"/>) {<xsl:apply-templates select="." mode="setMethod">
		<xsl:with-param name="fieldName" select="$validJavaFieldName"/>
		<xsl:with-param name="fieldLabel" select="$fieldLabel"/>
	</xsl:apply-templates>
	}
<xsl:if test="self::nx:field[@nameType='any']">
	@Override<xsl:apply-templates mode="methodAnnotations" select="."/>
	public Map&lt;String, Dataset> getAll<xsl:value-of select="$methodNameSuffix"/>() {
		return getAllDatasets(null);
	}
<xsl:apply-templates mode="methodAnnotations" select="."/>
	public void set<xsl:value-of select="$methodNameSuffix"/>(String name, <xsl:value-of select="$fieldType"/><xsl:text> </xsl:text><xsl:value-of select="$validJavaFieldName"/>) {
		setDataset(name, <xsl:value-of select="$validJavaFieldName"/>);
	}
</xsl:if>
<xsl:if test="self::nx:group[not(@name)]">
	@Override<xsl:apply-templates mode="methodAnnotations" select="."/>
	public <xsl:value-of select="$fieldType"/> get<xsl:value-of select="$methodNameSuffix"/>(String name) {
		return getChild(name, <xsl:value-of select="$fieldType"/>.class);
	}
<xsl:apply-templates mode="methodAnnotations" select="."/>
	public void set<xsl:value-of select="$methodNameSuffix"/>(String name, <xsl:value-of select="$fieldType"/><xsl:text> </xsl:text><xsl:value-of select="$validJavaFieldName"/>) {
		putChild(name, <xsl:value-of select="$validJavaFieldName"/>);
	}

	@Override<xsl:apply-templates mode="methodAnnotations" select="."/>
	public Map&lt;String, <xsl:value-of select="$fieldType"/>> getAll<xsl:value-of select="$methodNameSuffix"/>() {
		return getChildren(<xsl:value-of select="$fieldType"/>.class);
	}
<xsl:apply-templates mode="methodAnnotations" select="."/>
	public void setAll<xsl:value-of select="$methodNameSuffix"/>(Map&lt;String, <xsl:value-of select="$fieldType"/>> <xsl:value-of select="$validJavaFieldName"/>) {
		setChildren(<xsl:value-of select="$validJavaFieldName"/>);
	}
</xsl:if>
<xsl:apply-templates mode="class" select="*[not(self::nx:doc)][not(self::nx:dimensions)][not(self::nx:enumeration)]"/>
</xsl:template>

<!-- Get method bodies -->
<xsl:template mode="getMethod" match="nx:field[not(nx:scalar)]">
	<xsl:param name="fieldLabel"/>
		return getDataset(<xsl:value-of select="$fieldLabel"/>);</xsl:template>

<xsl:template mode="getMethod" match="nx:field[nx:scalar][matches(@type, 'ISO8601|(NX_(DATE_TIME|CHAR|INT|POSINT|UINT|FLOAT|NUMBER|BOOLEAN))') or not(@type)]">
	<xsl:param name="fieldLabel"/>
	<xsl:param name="fieldType"/>
		return get<xsl:value-of select="dawnsci:capitalise-first($fieldType)"/>(<xsl:value-of select="$fieldLabel"/>);</xsl:template>

<xsl:template mode="getMethod" match="nx:field[nx:scalar][@type='NX_BINARY']">
	<xsl:param name="fieldLabel"/>
		return getDataNode(<xsl:value-of select="$fieldLabel"/>).getDataset();</xsl:template>

<xsl:template mode="getMethod" match="nx:group">
	<xsl:param name="fieldLabel"/>
	<xsl:param name="fieldName"/>
		return getChild("<xsl:value-of select="$fieldName"/>", <xsl:apply-templates mode="fieldType" select="."/>.class);</xsl:template>

<xsl:template mode="getMethod" match="nx:definition/nx:attribute">
	<xsl:param name="fieldLabel"/>
	<xsl:param name="fieldType"/>
		return getAttr<xsl:value-of select="dawnsci:capitalise-first($fieldType)"/>(null, <xsl:value-of select="$fieldLabel"/>);</xsl:template>

<xsl:template mode="getMethod" match="nx:field/nx:attribute">
	<xsl:param name="fieldLabel"/>
	<xsl:param name="fieldType"/>
		return getAttr<xsl:value-of select="dawnsci:capitalise-first($fieldType)"/>(<xsl:apply-templates mode="fieldLabel" select=".."/>, <xsl:value-of select="$fieldLabel"/>);</xsl:template>

<!-- Set method bodies -->
<xsl:template mode="setMethod" match="nx:field[not(nx:scalar)]">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		setDataset(<xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>

<xsl:template mode="setMethod" match="nx:field[nx:scalar][@type='NX_DATE_TIME' or @type='ISO8601']">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		setDate(<xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>

<xsl:template mode="setMethod" match="nx:field[nx:scalar][@type='NX_CHAR' or not(@type)]">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		setString(<xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>

<xsl:template mode="setMethod" match="nx:field[nx:scalar][matches(@type, 'NX_(INT|POSINT|UINT|FLOAT|NUMBER|BOOLEAN)')]">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		set(<xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>

<xsl:template mode="setMethod" match="nx:field[nx:scalar][@type='NX_BINARY']">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		getDataNode(<xsl:value-of select="$fieldLabel"/>).setDataset(DatasetFactory.createFromObject(<xsl:value-of select="$fieldName"/>));</xsl:template>

<xsl:template mode="setMethod" match="nx:definition/nx:attribute">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		setAttribute(null, <xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>

<xsl:template mode="setMethod" match="nx:field/nx:attribute">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		setAttribute(<xsl:apply-templates mode="fieldLabel" select=".."/>, <xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>

<xsl:template mode="setMethod" match="nx:group">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		putChild("<xsl:value-of select="$fieldName"/>", <xsl:value-of select="$fieldName"/>);</xsl:template>


<!-- Unprocessed -->

<xsl:template mode="interface" match="*">	// Unprocessed <xsl:value-of select="concat(name(), ': ', @name)"/>
<xsl:text>
</xsl:text>
</xsl:template>
<xsl:template mode="class" match="*">	// Unprocessed <xsl:value-of select="concat(name(), ': ', @name)"/>
<xsl:text>
</xsl:text>
</xsl:template>

<!-- Ignore repeated unnamed groups of same type -->
<xsl:template mode="interface" match="nx:definition/nx:group[not(@name)][@type = preceding-sibling::nx:group[not(@name)]/@type]"/>
<xsl:template mode="class" match="nx:definition/nx:group[not(@name)][@type = preceding-sibling::nx:group[not(@name)]/@type]"/>

<!-- Ignore fields and attributes within a group -->
<xsl:template mode="interface" match="nx:group/nx:attribute|nx:group/nx:field"/>
<xsl:template mode="class" match="nx:group/nx:attribute|nx:group/nx:field"/>


<!-- Documentation -->

<xsl:variable name="fileHeaderComment">/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: <xsl:value-of select="current-dateTime()"/>
 *******************************************************************************/
</xsl:variable>

<xsl:template match="nx:doc">
	<xsl:variable name="indent"><xsl:if test="not(parent::nx:definition|parent::nx:symbols|parent::nx:symbol)"><xsl:text>	</xsl:text></xsl:if></xsl:variable>
	<xsl:for-each select="tokenize(., '&#10;')">
		<xsl:if test="string-length(normalize-space(.)) > 0"><xsl:text>
</xsl:text><xsl:value-of select="$indent"/> * <xsl:value-of select="normalize-space(.)"/>
		</xsl:if>
	</xsl:for-each>
</xsl:template>

<xsl:template match="nx:symbols">
 * &lt;p>&lt;b>Symbols:&lt;/b> <xsl:apply-templates select="nx:doc"/>&lt;ul><xsl:apply-templates select="nx:symbol"/>&lt;/ul>&lt;/p></xsl:template>
<xsl:template match="nx:symbols/nx:symbol">
 * &lt;li><xsl:value-of select="concat('&lt;b>', @name, '&lt;/b> ')"/><xsl:apply-templates select="nx:doc"/>&lt;/li></xsl:template>

<xsl:template match="nx:field/@type">
	 * &lt;b>Type:&lt;/b> <xsl:value-of select="."/></xsl:template>

<xsl:template match="nx:field/@units">
	 * &lt;b>Units:&lt;/b> <xsl:value-of select="."/></xsl:template>

<xsl:template match="nx:field/nx:dimensions">
	 * &lt;b>Dimensions:&lt;/b><xsl:apply-templates select="nx:dim"/></xsl:template>
<xsl:template match="nx:dimensions/nx:dim"><xsl:value-of select="concat(' ', @index, ': ', @value, ';')"/></xsl:template>

<xsl:template match="nx:enumeration">
	 * &lt;p>&lt;b>Enumeration:&lt;/b>&lt;ul><xsl:apply-templates select="nx:item"/>&lt;/ul>&lt;/p></xsl:template>
<xsl:template match="nx:enumeration/nx:item">
	 * &lt;li><xsl:value-of select="concat('&lt;b>', @value, '&lt;/b> ')"/><xsl:apply-templates select="nx:doc"/>&lt;/li></xsl:template>

<xsl:template match="@version">
 * @version <xsl:value-of select="."/></xsl:template>

<xsl:template match="nx:definition/@deprecated">
 * @deprecated <xsl:value-of select="."/></xsl:template>

<xsl:template match="*[not(self::nx:definition)]/@deprecated">
	 * @deprecated <xsl:value-of select="."/></xsl:template>


<!-- Imports -->
<xsl:template mode="imports" match="nx:definition">
	<xsl:variable name="types">
		<xsl:apply-templates select="descendant::*" mode="fieldType"/>
		<xsl:if test="descendant::nx:group[not(@name)] | descendant::nx:field[@nameType='any']">Map</xsl:if>
	</xsl:variable>
<xsl:if test="contains($types, 'Date')">
import java.util.Date;</xsl:if>
<xsl:if test="contains($types, 'Map')">
import java.util.Map;</xsl:if>
<xsl:if test="(contains($types, 'IDataset') or contains($types, 'Binary'))
		  and (contains($types, 'Date') or contains($types, 'Map'))"><xsl:text>
</xsl:text></xsl:if>
<xsl:if test="contains($types, 'IDataset')">
import org.eclipse.dawnsci.analysis.api.dataset.IDataset;</xsl:if>
<xsl:if test="contains($types, 'Dataset')">
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;</xsl:if>
<xsl:if test="contains($types, 'Binary')">
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;</xsl:if>
</xsl:template>

<!-- Extends expression -->
<xsl:template mode="interface" match="nx:definition/@extends"> extends <xsl:value-of select="."/></xsl:template>
<xsl:template mode="class" match="nx:definition/@extends"> extends <xsl:value-of select="."/>Impl</xsl:template>

<!-- Annotations -->
<xsl:template mode="typeAnnotations" match="*[@deprecated]">
@Deprecated</xsl:template>
<xsl:template mode="typeAnnotations" match="*"/>

<xsl:template mode="methodAnnotations" match="*[@deprecated]">
	@Deprecated</xsl:template>
<xsl:template mode="methodAnnotations" match="*"/>

<!-- Field types in Java: nx:scalar is some expected future feature that indicates the node will never have additional dimensions -->
<xsl:template mode="fieldType" match="nx:field[not(nx:scalar)]">IDataset</xsl:template>
<xsl:template mode="fieldType" match="*[self::nx:attribute|self::nx:field[nx:scalar]][@type='NX_DATE_TIME' or @type='ISO8601']">Date</xsl:template>
<xsl:template mode="fieldType" match="*[self::nx:attribute|self::nx:field[nx:scalar]][matches(@type, 'NX_(INT|POSINT|UINT)')]">long</xsl:template>
<xsl:template mode="fieldType" match="*[self::nx:attribute|self::nx:field[nx:scalar]][@type='NX_FLOAT']">double</xsl:template>
<xsl:template mode="fieldType" match="*[self::nx:attribute|self::nx:field[nx:scalar]][@type='NX_NUMBER']">Number</xsl:template>
<xsl:template mode="fieldType" match="*[self::nx:attribute|self::nx:field[nx:scalar]][@type='NX_BOOLEAN']">boolean</xsl:template>
<xsl:template mode="fieldType" match="*[self::nx:attribute|self::nx:field[nx:scalar]][@type='NX_CHAR' or not(@type)]">String</xsl:template>
<xsl:template mode="fieldType" match="nx:field[nx:scalar][@type='NX_BINARY']">Object</xsl:template>
<xsl:template mode="fieldType" match="nx:group"><xsl:value-of select="dawnsci:interface-name(@type)"/></xsl:template>
<xsl:template mode="extendedFieldType" match="nx:field[not(nx:scalar)]">? extends IDataset</xsl:template>

<!-- Field names in Java -->
<xsl:template mode="fieldName" match="nx:attribute|nx:field|nx:group[@name]"><xsl:value-of select="@name"/></xsl:template>
<xsl:template mode="fieldName" match="nx:group"><xsl:value-of select="substring(@type, 3)"/></xsl:template>

<!-- Field labels in Java -->
<xsl:template mode="fieldLabel" match="nx:field|nx:group"><xsl:param name="fieldName">
		<xsl:apply-templates mode="fieldName" select="."/>
	</xsl:param>NX_<xsl:value-of select="upper-case($fieldName)"/></xsl:template>
<xsl:template mode="fieldLabel" match="nx:definition/nx:attribute">
	<xsl:param name="fieldName"/>NX_ATTRIBUTE_<xsl:value-of select="upper-case($fieldName)"/></xsl:template>
<xsl:template mode="fieldLabel" match="nx:field/nx:attribute">
	<xsl:param name="fieldName"/><xsl:apply-templates mode="fieldLabel" select=".."/>_ATTRIBUTE_<xsl:value-of select="upper-case($fieldName)"/></xsl:template>

<!-- Method name suffixes (to get/set) -->
<xsl:template mode="methodNameSuffix" match="nx:field|nx:group">
	<xsl:param name="fieldName">
		<xsl:apply-templates mode="fieldName" select="."/>
	</xsl:param><xsl:value-of select="dawnsci:capitalise-first($fieldName)"/></xsl:template>

<xsl:template mode="methodNameSuffix" match="nx:definition/nx:attribute">
	<xsl:param name="fieldName"/>Attribute<xsl:value-of select="dawnsci:capitalise-first($fieldName)"/></xsl:template>

<xsl:template mode="methodNameSuffix" match="nx:field/nx:attribute">
	<xsl:param name="fieldName"/>
	<xsl:apply-templates mode="methodNameSuffix" select="..">
		<xsl:with-param name="fieldName"><xsl:apply-templates mode="fieldName" select=".."/></xsl:with-param>
	</xsl:apply-templates>Attribute<xsl:value-of select="dawnsci:capitalise-first($fieldName)"/></xsl:template>


<!-- Template to generate an enumeration of NeXus base classes -->
<xsl:template name="base-class-enum">

	<xsl:result-document href="{$javaSourcePath}/org/eclipse/dawnsci/nexus/impl/NXbaseClass.java" format="text-format">
		<xsl:text>package org.eclipse.dawnsci.nexus.impl;
	
/**
 * Eumeration of NeXus base classes.
 */
public enum NXbaseClass {

</xsl:text>

	<xsl:apply-templates mode="base-class-enum" select="$nexus-classes"/>

	<xsl:text>
	private String name;

	private NXbaseClass(final String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	/**
	 * Returns the nexus base class constant for the given name string.
	 */
	public static NXbaseClass getBaseClassForName(final String name) {
		// Note: this method will not work correctly if any base classes include
		// capital letters in their name (excluding the initial 'NX')
		final String enumName = name.substring(0, 2) + '_' + name.substring(2).toUpperCase();
		return NXbaseClass.valueOf(enumName);
	}

}&#10;</xsl:text>
	</xsl:result-document>
	

</xsl:template>

<!-- The enum value -->
<xsl:template mode="base-class-enum" match="nx:definition">
	<xsl:text>	</xsl:text><xsl:value-of select="dawnsci:base-class-enum-name(@name)"/>
	<xsl:text>("</xsl:text><xsl:value-of select="@name"/><xsl:text>")</xsl:text>
	<xsl:value-of select="if (position()=last()) then ';' else ','"/><xsl:text>&#10;</xsl:text>
</xsl:template>


<!-- Template to generate The NeXus factory class for creating instances of the generated classes.-->
<xsl:template name="factory-class">

	<xsl:result-document href="{$javaSourcePath}/org/eclipse/dawnsci/nexus/impl/NXobjectFactory.java" format="text-format">
		<xsl:text>package org.eclipse.dawnsci.nexus.impl;

import java.net.URI;

import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.dawnsci.analysis.tree.impl.TreeImpl;

/**
 * Factory class for creating instances of NeXus base classes.
 */
public class NXobjectFactory {
	
	private long nextOid = 1l;

</xsl:text>

	<xsl:text>	public static NXobjectImpl createNXobjectForClass(String baseClassName, long oid) {
		final NXbaseClass baseClass = NXbaseClass.getBaseClassForName(baseClassName);
		return createNXobjectForClass(baseClass, oid);
	}
	
	public static NXobjectImpl createNXobjectForClass(NXbaseClass baseClass, long oid) {
		switch (baseClass) {&#10;</xsl:text>
	<xsl:for-each select="$nexus-classes">
		<xsl:text>			case </xsl:text><xsl:value-of select="dawnsci:base-class-enum-name(@name)"/><xsl:text>:&#10;</xsl:text>
		<xsl:text>				return create</xsl:text><xsl:value-of select="@name"/><xsl:text>(oid);&#10;</xsl:text>
	</xsl:for-each>
	
	<xsl:text>		}&#10;</xsl:text>
	<xsl:text>		throw new IllegalArgumentException("Unknown base class: " + baseClass);</xsl:text>
	<xsl:text>	}&#10;</xsl:text>
	
	<xsl:text>/**
	 * Create a new tree with given URI
	 * @param uri
	 */
	public TreeImpl createTree(final URI uri) {
		return new TreeImpl(nextOid++, uri);
	}
	
	/**
	 * Create a new tree file with given URI
	 * @param uri uri
	 */
	public TreeFileImpl createTreeFile(final URI uri) {
		return new TreeFileImpl(nextOid++, uri);
	}
	
	/**
	 * Create a new tree file with given file name
	 * @param filename filename
	 * @return
	 */
	public TreeFileImpl createTreeFile(final String fileName) {
		return new TreeFileImpl(nextOid++, fileName);
	}
	
</xsl:text>
	
		<xsl:apply-templates mode="factory-methods" select="$nexus-classes"/>
		<xsl:text>}&#10;</xsl:text>
	
	</xsl:result-document>

</xsl:template>

<xsl:template mode="factory-methods" match="nx:definition">
	<xsl:apply-templates mode="factory-method" select=".">
		<xsl:with-param name="has-oid-param" select="true()"/>
	</xsl:apply-templates>
	<xsl:apply-templates mode="factory-method" select=".">
		<xsl:with-param name="has-oid-param" select="false()"/>
	</xsl:apply-templates>
</xsl:template>

<!-- Template to create the factory method for a NeXus class -->
<xsl:template mode="factory-method" match="nx:definition">
	<xsl:param name="has-oid-param"/>

	<xsl:text>	/**&#10;</xsl:text>
	<xsl:text>	 * Create a new </xsl:text><xsl:value-of select="@name"/>
	<xsl:if test="$has-oid-param"><xsl:text> with the given oid</xsl:text></xsl:if>
	<xsl:text>.&#10;</xsl:text>
	<xsl:text>	 */&#10;</xsl:text>
	<xsl:text>	public </xsl:text><xsl:if test="$has-oid-param">static </xsl:if>
	<xsl:value-of select="@name"/><xsl:text>Impl</xsl:text>
	<xsl:text> create</xsl:text><xsl:value-of select="@name"/>
	<xsl:value-of select="if ($has-oid-param) then '(long oid)' else '()'"/><xsl:text> {&#10;</xsl:text>
	<xsl:text>		return new </xsl:text>
	<xsl:value-of select="@name"/><xsl:text>Impl(</xsl:text>
	<xsl:value-of select="if ($has-oid-param) then 'oid' else 'nextOid++'"/><xsl:text>);&#10;</xsl:text>
	<xsl:text>	}&#10;</xsl:text>
	<xsl:text>&#10;</xsl:text>

</xsl:template>

<!-- Java identifier transform functions -->

<xsl:function name="dawnsci:capitalise-first" as="xs:string?">
	<xsl:param name="arg" as="xs:string?"/>
	<xsl:sequence select="concat(upper-case(substring($arg,1,1)), substring($arg,2))"/>
</xsl:function>

<xsl:function name="dawnsci:interface-name" as="xs:string?">
	<xsl:param name="arg" as="xs:string?"/>
	<xsl:sequence select="$arg"/>
</xsl:function>

<xsl:function name="dawnsci:class-name" as="xs:string?">
	<xsl:param name="arg" as="xs:string?"/>
	<xsl:sequence select="concat($arg, 'Impl')"/>
</xsl:function>

<xsl:function name="dawnsci:base-class-enum-name" as="xs:string">
	<xsl:param name="arg" as="xs:string"/>
	<xsl:sequence select="concat(substring($arg, 1, 2), '_', upper-case(substring($arg, 3)))"/>
</xsl:function>

</xsl:stylesheet>