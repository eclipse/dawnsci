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
   java -cp [/path/to/]saxon9he.jar net.sf.saxon.Transform -xsl:[/path/to/]NXDLJavaGenerator.xsl -it:generate-java nxdlDefinitionsPath=[/path/to/]nexus-definitions javaSourcePath=[/path/to/]src javaOutputPath=[/path/to]autogen
 Alternatively use the build.xml Ant build file in this project. 
 -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:xs="http://www.w3.org/2001/XMLSchema"
 xmlns:nx="http://definition.nexusformat.org/nxdl/3.1"
 xmlns:dawnsci="urn:import:org.eclipse.dawnsci.nexus"
 exclude-result-prefixes="xs dawnsci">

<!-- The path containing the nxdl definitions files to transform -->
<xsl:param name="nxdlDefinitionsPath" select="'.'"/>
<!-- The path containing the Java source tree to write to -->
<xsl:param name="javaSourcePath" select="'../src'"/>
<xsl:param name="javaOutputPath" select="'../autogen'"/>

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
	
	<xsl:result-document href="{$javaOutputPath}/org/eclipse/dawnsci/nexus/{$interfaceName}.java" format="text-format">
		<xsl:value-of select="$fileHeaderComment"/>
package org.eclipse.dawnsci.nexus;
<xsl:apply-templates mode="imports" select="."><xsl:with-param name="implementation" select="false()"/></xsl:apply-templates>

/**<xsl:apply-templates select="nx:doc"/><xsl:apply-templates select="nx:symbols"/>
 * <xsl:apply-templates select="@version|@deprecated"/>
 */<xsl:apply-templates mode="typeAnnotations" select="."/>
public interface <xsl:value-of select="$interfaceName"/>
<xsl:choose>
	<xsl:when test="@name='NXentry'"> extends NXsubentry</xsl:when>
	<xsl:otherwise><xsl:apply-templates mode="interface" select="@extends"/></xsl:otherwise>
</xsl:choose> {
<xsl:apply-templates mode="classFields" select="*"/>
<xsl:apply-templates mode="interface" select="*[not(self::nx:doc)][not(self::nx:symbols)]"/>
}
</xsl:result-document>

</xsl:template>

<!-- Interface get and set methods -->
<xsl:template mode="interface" match="
	nx:definition/nx:attribute
	| nx:definition/nx:field[not(@name = preceding-sibling::nx:field/@name)]
 	| nx:field/nx:attribute
 	| nx:definition/nx:group[@name or not(@type = preceding-sibling::nx:group[not(@name)]/@type)]">

	<!-- Template variables -->
	<xsl:variable name="fieldName"><xsl:apply-templates select="." mode="fieldName"/></xsl:variable>
	<xsl:variable name="validJavaFieldName" select="if ($fieldName='default') then concat($fieldName, '_') else $fieldName"/>
	<xsl:variable name="fieldType"><xsl:apply-templates select="." mode="fieldType"/></xsl:variable>
	<xsl:variable name="extendedFieldType"><xsl:apply-templates select="." mode="extendedFieldType"/></xsl:variable>
	<xsl:variable name="methodNameSuffix"><xsl:apply-templates select="." mode="methodNameSuffix">
		<xsl:with-param name="fieldName" select="$fieldName"/>
	</xsl:apply-templates></xsl:variable>
	<xsl:variable name="setMethodReturnType" select="if (self::nx:field) then 'DataNode' else 'void'"/>
	
	<!-- Get method -->
	<xsl:call-template name="methodJavadoc"/>
	<xsl:apply-templates mode="methodAnnotations" select="."/>
	public <xsl:value-of select="$fieldType"/> get<xsl:value-of select="$methodNameSuffix"/><xsl:text>();
	</xsl:text>	
	
	<!-- Set method -->
	<xsl:call-template name="methodJavadoc">
		<xsl:with-param name="setParamName" select="$validJavaFieldName"/>
	</xsl:call-template>
	<xsl:apply-templates mode="methodAnnotations" select="."/>
	public <xsl:value-of select="$setMethodReturnType"/> set<xsl:value-of select="$methodNameSuffix"/>(<xsl:value-of select="$fieldType"/>
	<xsl:text> </xsl:text><xsl:value-of select="$validJavaFieldName"/><xsl:text>);
</xsl:text>

	<xsl:if test="self::nx:field[not(nx:scalar)]">
		<!-- Get scalar method -->	
		<xsl:variable name="scalarFieldType"><xsl:apply-templates select="." mode="scalarFieldType"/></xsl:variable>
		<xsl:call-template name="methodJavadoc"/>
		<xsl:apply-templates mode="methodAnnotations" select="."/>
	public <xsl:value-of select="$scalarFieldType"/> get<xsl:value-of select="$methodNameSuffix"/><xsl:text>Scalar();
</xsl:text>

		<!-- Set scalar method -->
		<xsl:call-template name="methodJavadoc">
			<xsl:with-param name="setParamName" select="$validJavaFieldName"/>
		</xsl:call-template>
		<xsl:apply-templates mode="methodAnnotations" select="."/>
	public <xsl:value-of select="$setMethodReturnType"/> set<xsl:value-of select="$methodNameSuffix"/>Scalar(<xsl:value-of select="$scalarFieldType"/><xsl:text> </xsl:text><xsl:value-of select="$validJavaFieldName"/><xsl:text>);
</xsl:text>
		
</xsl:if>

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
	 * Set a <xsl:value-of select="$fieldType"/> node by name:
	 * &lt;ul><xsl:for-each select="../nx:group[@type = current()/@type]">
	 * &lt;li><xsl:apply-templates select="nx:doc"/>&lt;/li></xsl:for-each>
	 * &lt;/ul>
	 * <xsl:apply-templates select="@deprecated"/>
	 * @param name the name of the node
	 * @param <xsl:value-of select="$validJavaFieldName"/> the value to set
	 */<xsl:apply-templates mode="methodAnnotations" select="."/>
	public void set<xsl:value-of select="$methodNameSuffix"/>(String name, <xsl:value-of select="$fieldType"/><xsl:text> </xsl:text><xsl:value-of select="$validJavaFieldName"/>);
	
	/**
	 * Get all <xsl:value-of select="$fieldType"/> nodes:
	 * &lt;ul><xsl:for-each select="../nx:group[@type = current()/@type]">
	 * &lt;li><xsl:apply-templates select="nx:doc"/>&lt;/li></xsl:for-each>
	 * &lt;/ul>
	 * <xsl:apply-templates select="@deprecated"/>
	 * @return  a map from node names to the <xsl:value-of select="$fieldType"/> for that node.
	 */<xsl:apply-templates mode="methodAnnotations" select="."/>
	public Map&lt;String, <xsl:value-of select="$fieldType"/>> getAll<xsl:value-of select="$methodNameSuffix"/>();
	
	/**
	 * Set multiple child nodes of a particular type.
	 * &lt;ul><xsl:for-each select="../nx:group[@type = current()/@type]">
	 * &lt;li><xsl:apply-templates select="nx:doc"/>&lt;/li></xsl:for-each>
	 * &lt;/ul>
	 * <xsl:apply-templates select="@deprecated"/>
	 * @param <xsl:value-of select="$validJavaFieldName"/> the child nodes to add 
	 */
	<xsl:apply-templates mode="methodAnnotations" select="."/>
	public void setAll<xsl:value-of select="$methodNameSuffix"/>(Map&lt;String, <xsl:value-of select="$fieldType"/>> <xsl:value-of select="$validJavaFieldName"/>);
	
</xsl:if>
<xsl:apply-templates mode="interface" select="*[not(self::nx:doc)][not(self::nx:dimensions)][not(self::nx:enumeration)]"/>
</xsl:template>


<!-- Java class implementation file -->

<xsl:template mode="class" match="nx:definition">

	<xsl:variable name="interfaceName" select="dawnsci:interface-name(@name)"/>
	<xsl:variable name="className" select="dawnsci:class-name(@name)"/>
	
	<xsl:result-document href="{$javaOutputPath}/org/eclipse/dawnsci/nexus/impl/{$className}.java" format="text-format">
		<xsl:value-of select="$fileHeaderComment"/>
package org.eclipse.dawnsci.nexus.impl;
<xsl:apply-templates mode="imports" select=".">
	<xsl:with-param name="implementation" select="true()"/>
</xsl:apply-templates>

import org.eclipse.dawnsci.nexus.*;

/**<xsl:apply-templates select="nx:doc"/>
 * <xsl:apply-templates select="@version|@deprecated"/>
 */<xsl:apply-templates mode="typeAnnotations" select="."/>
public class <xsl:value-of select="$className"/><xsl:apply-templates mode="class" select="@extends"/> implements <xsl:value-of select="$interfaceName"/> {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible
<xsl:text>

	public static final Set&lt;NexusBaseClass&gt; PERMITTED_CHILD_GROUP_CLASSES = </xsl:text>
		<xsl:choose>
			<xsl:when test="nx:group">
				<xsl:text>EnumSet.of(&#10;</xsl:text>
				<xsl:for-each select="nx:group">
					<xsl:text>		NexusBaseClass.</xsl:text><xsl:value-of select="dawnsci:base-class-enum-name(@type)"/>
					<xsl:value-of select="if (position()=last()) then '' else ',&#10;'"/>
				</xsl:for-each>
			</xsl:when>
			<xsl:otherwise>EnumSet.noneOf(NexusBaseClass.class</xsl:otherwise>
		</xsl:choose>);

	public <xsl:value-of select="$className"/>() {
		super();
	}

	public <xsl:value-of select="$className"/>(final long oid) {
		super(oid);
	}
	
	@Override
	public Class&lt;? extends NXobject> getNXclass() {
		return <xsl:value-of select="$interfaceName"/>.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.<xsl:value-of select="dawnsci:base-class-enum-name(@name)"/>;
	}
	
	@Override
	public Set&lt;NexusBaseClass&gt; getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
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
	<xsl:variable name="validJavaFieldName" select="if ($fieldName='default') then concat($fieldName, '_') else $fieldName"/>
	
	<xsl:variable name="fieldLabel"><xsl:apply-templates select="." mode="fieldLabel">
		<xsl:with-param name="fieldName" select="$fieldName"/>
	</xsl:apply-templates></xsl:variable>
	
	<xsl:variable name="fieldType"><xsl:apply-templates select="." mode="fieldType"/></xsl:variable>
	<xsl:variable name="extendedFieldType"><xsl:apply-templates select="." mode="extendedFieldType"/></xsl:variable>
	<xsl:variable name="setMethodReturnType" select="if (self::nx:field) then 'DataNode' else 'void'"/>
	
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
<xsl:if test="self::nx:field[not(nx:scalar)]">
	<xsl:variable name="scalarFieldType"><xsl:apply-templates select="." mode="scalarFieldType"/></xsl:variable>
	@Override<xsl:apply-templates mode="methodAnnotations" select="."/>
	public <xsl:value-of select="$scalarFieldType"/> get<xsl:value-of select="$methodNameSuffix"/>Scalar() {<xsl:apply-templates select="." mode="getScalarMethod">
		<xsl:with-param name="fieldName" select="$fieldName"/>
		<xsl:with-param name="fieldLabel" select="$fieldLabel"/>
		<xsl:with-param name="fieldType" select="$scalarFieldType"/>
	</xsl:apply-templates>
	}
</xsl:if>
	@Override<xsl:apply-templates mode="methodAnnotations" select="."/>
	public <xsl:value-of select="$setMethodReturnType"/> set<xsl:value-of select="$methodNameSuffix"/>(<xsl:value-of select="$fieldType"/><xsl:text> </xsl:text><xsl:value-of select="$validJavaFieldName"/>) {<xsl:apply-templates select="." mode="setMethod">
		<xsl:with-param name="fieldName" select="$validJavaFieldName"/>
		<xsl:with-param name="fieldLabel" select="$fieldLabel"/>
	</xsl:apply-templates>
	}
<xsl:if test="self::nx:field[not(nx:scalar)]">
	<xsl:variable name="scalarFieldType"><xsl:apply-templates select="." mode="scalarFieldType"/></xsl:variable>
	@Override<xsl:apply-templates mode="methodAnnotations" select="."/>
	public <xsl:value-of select="$setMethodReturnType"/> set<xsl:value-of select="$methodNameSuffix"/>Scalar(<xsl:value-of select="$scalarFieldType"/><xsl:text> </xsl:text><xsl:value-of select="$validJavaFieldName"/>) {<xsl:apply-templates select="." mode="setScalarMethod">
		<xsl:with-param name="fieldName" select="$validJavaFieldName"/>
		<xsl:with-param name="fieldLabel" select="$fieldLabel"/>
	</xsl:apply-templates>
	}
</xsl:if>
<xsl:if test="self::nx:field[@nameType='any']">
	@Override<xsl:apply-templates mode="methodAnnotations" select="."/>
	public Map&lt;String, IDataset> getAll<xsl:value-of select="$methodNameSuffix"/>() {
		return getAllDatasets();
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

	@Override<xsl:apply-templates mode="methodAnnotations" select="."/>
	public void set<xsl:value-of select="$methodNameSuffix"/>(String name, <xsl:value-of select="$fieldType"/><xsl:text> </xsl:text><xsl:value-of select="$validJavaFieldName"/>) {
		putChild(name, <xsl:value-of select="$validJavaFieldName"/>);
	}

	@Override<xsl:apply-templates mode="methodAnnotations" select="."/>
	public Map&lt;String, <xsl:value-of select="$fieldType"/>> getAll<xsl:value-of select="$methodNameSuffix"/>() {
		return getChildren(<xsl:value-of select="$fieldType"/>.class);
	}
	
	@Override<xsl:apply-templates mode="methodAnnotations" select="."/>
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

<!-- Get method bodies for getScalar methods for non scalar fields -->
<xsl:template mode="getScalarMethod" match="nx:field[@type='NX_BINARY']">
	<xsl:param name="fieldLabel"/>
		return getDataNode(<xsl:value-of select="$fieldLabel"/>).getDataset();</xsl:template>
		
<xsl:template mode="getScalarMethod" match="nx:field">
	<xsl:param name="fieldLabel"/>
	<xsl:param name="fieldType"/>
		return get<xsl:value-of select="dawnsci:capitalise-first($fieldType)"/>(<xsl:value-of select="$fieldLabel"/>);</xsl:template>

<!-- Set method bodies -->
<xsl:template mode="setMethod" match="nx:field[not(nx:scalar)]">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		return setDataset(<xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>

<xsl:template mode="setMethod" match="nx:field[nx:scalar][@type='NX_DATE_TIME' or @type='ISO8601']">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		return setDate(<xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>

<xsl:template mode="setMethod" match="nx:field[nx:scalar][@type='NX_CHAR' or not(@type)]">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		return setString(<xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>

<xsl:template mode="setMethod" match="nx:field[nx:scalar][matches(@type, 'NX_(INT|POSINT|UINT|FLOAT|NUMBER|BOOLEAN|BINARY)')]">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		return setField(<xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>

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

<!-- Set method bodies for setScalar methods for non scalar fields -->
<xsl:template mode="setScalarMethod" match="nx:field[@type='NX_DATE_TIME' or @type='ISO8601']">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		return setDate(<xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>
		
<xsl:template mode="setScalarMethod" match="nx:field[@type='NX_CHAR' or not (@type)]">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		return setString(<xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>
		
<xsl:template mode="setScalarMethod" match="nx:field[matches(@type, 'NX_(INT|POSINT|UNIT|FLOAT|NUMBER|BOOLEAN|BINARY)')]">
	<xsl:param name="fieldName"/>
	<xsl:param name="fieldLabel"/>
		return setField(<xsl:value-of select="$fieldLabel"/>, <xsl:value-of select="$fieldName"/>);</xsl:template>
		
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

<xsl:template name="methodJavadoc">
	<xsl:param name="setParamName"/>
	/**<xsl:apply-templates select="nx:doc"/><xsl:if test="self::nx:field/@type|@units|nx:dimensions|nx:enumeration">
	 * &lt;p><xsl:apply-templates select="self::nx:field/@type|@units|nx:dimensions|nx:enumeration"/>
	 * &lt;/p></xsl:if>
	 * <xsl:apply-templates select="@deprecated"/>
	<xsl:choose>
		<xsl:when test="$setParamName">
	 * @param <xsl:value-of select="$setParamName"/> the <xsl:value-of select="if (ends-with($setParamName, '_')) then substring($setParamName, 0, string-length($setParamName)) else $setParamName"/>
	 	</xsl:when>
		<xsl:otherwise>
	 * @return  the value.</xsl:otherwise>
	</xsl:choose>
	 */</xsl:template>

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
	<xsl:param name="implementation"/>
	<xsl:variable name="types">
		<xsl:apply-templates select="descendant::*" mode="fieldType"/>
		<xsl:apply-templates select="descendant::*" mode="scalarFieldType"/>
		<xsl:if test="descendant::nx:group[not(@name)] | descendant::nx:field[@nameType='any']">Map</xsl:if>
	</xsl:variable>
<xsl:if test="contains($types, 'Date')">
import java.util.Date;</xsl:if>
<xsl:if test="$implementation">
import java.util.Set;
import java.util.EnumSet;</xsl:if>
<xsl:if test="contains($types, 'Map')">
import java.util.Map;</xsl:if>
<xsl:if test="(contains($types, 'IDataset') or contains($types, 'Binary'))
		  and (contains($types, 'Date') or contains($types, 'Map'))"><xsl:text>
</xsl:text></xsl:if>
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
<xsl:if test="contains($types, 'IDataset')">
import org.eclipse.january.dataset.IDataset;</xsl:if>
<xsl:if test="contains($types, 'Binary')">
import org.eclipse.january.dataset.DatasetFactory;</xsl:if>
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
<xsl:template mode="fieldType" match="*[self::nx:attribute|self::nx:field[nx:scalar]][matches(@type, 'NX_(INT|POSINT|UINT)')]">Long</xsl:template>
<xsl:template mode="fieldType" match="*[self::nx:attribute|self::nx:field[nx:scalar]][@type='NX_FLOAT']">Double</xsl:template>
<xsl:template mode="fieldType" match="*[self::nx:attribute|self::nx:field[nx:scalar]][@type='NX_NUMBER']">Number</xsl:template>
<xsl:template mode="fieldType" match="*[self::nx:attribute|self::nx:field[nx:scalar]][@type='NX_BOOLEAN']">Boolean</xsl:template>
<xsl:template mode="fieldType" match="*[self::nx:attribute|self::nx:field[nx:scalar]][@type='NX_CHAR' or not(@type)]">String</xsl:template>
<xsl:template mode="fieldType" match="nx:field[nx:scalar][@type='NX_BINARY']">Object</xsl:template>
<xsl:template mode="fieldType" match="nx:group"><xsl:value-of select="dawnsci:interface-name(@type)"/></xsl:template>
<xsl:template mode="extendedFieldType" match="nx:field[not(nx:scalar)]">? extends IDataset</xsl:template>

<!-- Scalar field types. This is useful where we want to get/set a field as a scalar that is not (yet) marked with nx:scalar -->
<xsl:template mode="scalarFieldType" match="nx:field[@type='NX_DATE_TIME' or @type='ISO8601']">Date</xsl:template>
<xsl:template mode="scalarFieldType" match="nx:field[matches(@type, 'NX_(INT|POSINT|UNIT)')]">Long</xsl:template>
<xsl:template mode="scalarFieldType" match="nx:field[@type='NX_FLOAT']">Double</xsl:template>
<xsl:template mode="scalarFieldType" match="nx:field[@type='NX_NUMBER']">Number</xsl:template>
<xsl:template mode="scalarFieldType" match="nx:field[@type='NX_BOOLEAN']">Boolean</xsl:template>
<xsl:template mode="scalarFieldType" match="nx:field[@type='NX_CHAR' or not(@type)]">String</xsl:template>
<xsl:template mode="scalarFieldType" match="nx:field[@type='NX_BINARY']">Object</xsl:template>

<!-- Field names in Java -->
<!-- Simple case, the field has the same name as the attribute, field or group name in the NXDL -->
<xsl:template mode="fieldName" match="nx:attribute|nx:field|nx:group[@name]">
	<xsl:value-of select="@name"/>
</xsl:template>

<!-- For unnamed groups we use the type, minux the 'NX' prefix -->
<xsl:template mode="fieldName" match="nx:group">
	<xsl:variable name="fieldName" select="substring(@type, 3)"/>
	<xsl:choose>
		<!-- Special case when a field exists with the same name. NXsample.sampleComponent is an example of this -->
		<xsl:when test="../nx:field[@name = $fieldName]"><xsl:value-of select="$fieldName"/>Group</xsl:when>
		<xsl:otherwise><xsl:value-of select="$fieldName"/></xsl:otherwise>
	</xsl:choose>
</xsl:template>

<!-- Field labels in Java -->
<xsl:template mode="fieldLabel" match="nx:field|nx:group"><xsl:param name="fieldName">
		<xsl:apply-templates mode="fieldName" select="."/>
	</xsl:param>NX_<xsl:value-of select="upper-case($fieldName)"/>
</xsl:template>
<xsl:template mode="fieldLabel" match="nx:definition/nx:attribute">
	<xsl:param name="fieldName"/>NX_ATTRIBUTE_<xsl:value-of select="upper-case($fieldName)"/>
</xsl:template>
<xsl:template mode="fieldLabel" match="nx:field/nx:attribute">
	<xsl:param name="fieldName"/><xsl:apply-templates mode="fieldLabel" select=".."/>_ATTRIBUTE_<xsl:value-of select="upper-case($fieldName)"/>
</xsl:template>

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

	<xsl:result-document href="{$javaOutputPath}/org/eclipse/dawnsci/nexus/NexusBaseClass.java" format="text-format">
		<xsl:value-of select="$fileHeaderComment"/>
		<xsl:text>
package org.eclipse.dawnsci.nexus;
	
/**
 * Eumeration of NeXus base classes.
 */
public enum NexusBaseClass {

</xsl:text>

	<xsl:apply-templates mode="base-class-enum" select="$nexus-classes"/>

	<xsl:text>
	private String name;
	
	private Class&lt;? extends NXobject&gt; javaClass;
	
	private NexusBaseClass(final String name, final Class&lt;? extends NXobject&gt; javaClass) {
		this.name = name;
		this.javaClass = javaClass;
	}
	
	public Class&lt;? extends NXobject&gt; getJavaClass() {
		return javaClass;
	} 
	
	public String toString() {
		return name;
	}
	
	/**
	 * Returns the nexus base class constant for the given name string.
	 */
	public static NexusBaseClass getBaseClassForName(final String name) {
		// Note: this method will not work correctly if any base classes include
		// capital letters in their name (excluding the initial 'NX')
		final String enumName = name.substring(0, 2) + '_' + name.substring(2).toUpperCase();
		return NexusBaseClass.valueOf(enumName);
	}
	

}&#10;</xsl:text>
	</xsl:result-document>
	

</xsl:template>

<!-- Template to produce the enum value for a nexus base class-->
<xsl:template mode="base-class-enum" match="nx:definition">
	<xsl:text>	</xsl:text><xsl:value-of select="dawnsci:base-class-enum-name(@name)"/>
	<xsl:text>("</xsl:text><xsl:value-of select="@name"/><xsl:text>", </xsl:text>
	<xsl:value-of select="@name"/><xsl:text>.class)</xsl:text>
	<xsl:value-of select="if (position()=last()) then ';' else ','"/><xsl:text>&#10;</xsl:text>
</xsl:template>


<!-- Template to generate The NeXus factory class for creating instances of the generated classes.-->
<xsl:template name="factory-class">

	<xsl:result-document href="{$javaOutputPath}/org/eclipse/dawnsci/nexus/NexusNodeFactory.java" format="text-format">
		<xsl:value-of select="$fileHeaderComment"/>
		<xsl:text>
package org.eclipse.dawnsci.nexus;

import java.net.URI;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.SymbolicNode;
import org.eclipse.dawnsci.analysis.api.tree.Tree;
import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.tree.TreeFactory;
import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.dawnsci.analysis.tree.impl.TreeImpl;
import org.eclipse.dawnsci.nexus.NexusBaseClass;
</xsl:text>
<xsl:for-each select="$nexus-classes">
<xsl:text>import org.eclipse.dawnsci.nexus.impl.</xsl:text>
<xsl:value-of select="@name"/>
<xsl:text>Impl;&#10;</xsl:text>
</xsl:for-each>

<xsl:text>
/**
 * Factory class for creating instances of NeXus base classes.
 */
public class NexusNodeFactory {
	
	// static field so that we can create unique oids without having access to
	// the same node factory instances. Values should be unique within this VM,
	// except in the unlikely case that the value rolls over
	private static long nextOid = 1l;

	private NexusNodeFactory() {
		// private constructor to prevent instantiation
	}

	public static NXobject createNXobjectForClass(String baseClassName, long oid) {
		final NexusBaseClass baseClass = NexusBaseClass.getBaseClassForName(baseClassName);
		return createNXobjectForClass(baseClass, oid);
	}
	
	public static NXobject createNXobjectForClass(NexusBaseClass baseClass, long oid) {
		switch (baseClass) {&#10;</xsl:text>
	<xsl:for-each select="$nexus-classes">
		<xsl:text>			case </xsl:text><xsl:value-of select="dawnsci:base-class-enum-name(@name)"/><xsl:text>:&#10;</xsl:text>
		<xsl:text>				return create</xsl:text><xsl:value-of select="@name"/><xsl:text>(oid);&#10;</xsl:text>
	</xsl:for-each>
	<xsl:text>		}&#10;</xsl:text>
	<xsl:text>		throw new IllegalArgumentException("Unknown base class: " + baseClass);&#10;</xsl:text>
	<xsl:text>	}&#10;</xsl:text>
	
<xsl:text>

	public static NXobject createNXobjectForClass(String baseClassName) {
		final NexusBaseClass baseClass = NexusBaseClass.getBaseClassForName(baseClassName);
		return createNXobjectForClass(baseClass);
	}

	public static NXobject createNXobjectForClass(NexusBaseClass baseClass) {
		switch (baseClass) {&#10;</xsl:text>
	<xsl:for-each select="$nexus-classes">
		<xsl:text>			case </xsl:text><xsl:value-of select="dawnsci:base-class-enum-name(@name)"/><xsl:text>:&#10;</xsl:text>
		<xsl:text>				return create</xsl:text><xsl:value-of select="@name"/><xsl:text>();&#10;</xsl:text>
	</xsl:for-each>
	<xsl:text>		}&#10;</xsl:text>
	<xsl:text>		throw new IllegalArgumentException("Unknown base class: " + baseClass);&#10;</xsl:text>
	<xsl:text>	}&#10;</xsl:text>
	
	<xsl:text>
	/**
	 * Get the next unique object id. Note that this value is unique across all instances in
	 * this VM (i.e. it is a static field).
	 * @return the next oid
	 */
	public static long getNextOid() {
		long oid = nextOid++;
		if (oid == 0) { // oids may no longer be unique
			throw new RuntimeException("maximum number of oids reached");
		}
		return oid;
	}
	
	/**
	 * Create a new {@link Tree} with given URI.
	 * @param uri
	 * @return new tree
	 */
	public static Tree createTree(final URI uri) {
		return new TreeImpl(getNextOid(), uri);
	}
	
	/**
	 * Create a new {@link TreeFile} given URI.
	 * @param uri uri
	 * @return new tree file
	 */
	public static TreeFile createTreeFile(final URI uri) {
		return new TreeFileImpl(getNextOid(), uri);
	}
	
	/**
	 * Create a new tree file with given file name
	 * @param fileName filename
	 * @return new tree file
	 */
	public static TreeFileImpl createTreeFile(final String fileName) {
		return new TreeFileImpl(getNextOid(), fileName);
	}
	
	/**
	 * Create a new data node.
	 * @return new data node
	 */
	public static DataNode createDataNode() {
		return TreeFactory.createDataNode(getNextOid());
	}
	
	/**
	 * Create a new symbolic node
	 * @param uri uri
	 * @param pathToNode path to node
	 * @return new symbolic node
	 */
	public static SymbolicNode createSymbolicNode(URI uri, String pathToNode) {
		return TreeFactory.createSymbolicNode(getNextOid(), uri, null, pathToNode);
	}
	
</xsl:text>
	
		<xsl:apply-templates mode="factory-methods" select="$nexus-classes"/>
		<xsl:text>}&#10;</xsl:text>
	
	</xsl:result-document>

</xsl:template>

<xsl:template mode="factory-methods" match="nx:definition">
	<!-- Method to create an object of a particular NXclass, taking an oid -->
	<xsl:apply-templates mode="factory-method" select=".">
		<xsl:with-param name="has-oid-param" select="true()"/>
	</xsl:apply-templates>
	<!--  Method to create an object of a particular NXclass, taking this node factory -->
	<xsl:apply-templates mode="factory-method" select=".">
		<xsl:with-param name="has-oid-param" select="false()"/>
	</xsl:apply-templates>
</xsl:template>

<!-- Template to create the factory method for a NeXus class -->
<xsl:template mode="factory-method" match="nx:definition">
	<xsl:param name="has-oid-param"/>

	<!-- Javadoc for factory method -->
	<xsl:text>	/**&#10;</xsl:text>
	<xsl:text>	 * Create a new {@link </xsl:text><xsl:value-of select="@name"/>
	<xsl:text>}</xsl:text>
	<xsl:if test="$has-oid-param"><xsl:text> with the given oid</xsl:text></xsl:if>
	<xsl:text>.&#10;</xsl:text>
	<xsl:text>	 *&#10;</xsl:text>
	<xsl:if test="$has-oid-param"><xsl:text>	 * @param oid unique object oid.&#10;</xsl:text></xsl:if>
	<xsl:text>	 * @return new </xsl:text><xsl:value-of select="@name"/>
	<xsl:text>&#10;</xsl:text>
	<xsl:text>	 */&#10;</xsl:text>
	
	<!-- Method implementation -->
	<xsl:text>	public static </xsl:text><xsl:value-of select="@name"/>
	<xsl:text> create</xsl:text><xsl:value-of select="@name"/>
	<xsl:value-of select="if ($has-oid-param) then '(long oid)' else '()'"/><xsl:text> {&#10;</xsl:text>
	<xsl:text>		return new </xsl:text>
	<xsl:value-of select="@name"/><xsl:text>Impl(</xsl:text>
	<xsl:value-of select="if ($has-oid-param) then 'oid' else ''"/><xsl:text>);&#10;</xsl:text>
	<xsl:text>	}&#10;</xsl:text>
	<xsl:text>&#10;</xsl:text>

</xsl:template>

<!-- Java identifier transform functions -->
<!-- capitalises the first letter of its string argument -->
<xsl:function name="dawnsci:capitalise-first" as="xs:string?">
	<xsl:param name="arg" as="xs:string?"/>
	<xsl:sequence select="concat(upper-case(substring($arg,1,1)), substring($arg,2))"/>
</xsl:function>

<!-- Returns the name for the Java interface for an NX base class name. -->
<xsl:function name="dawnsci:interface-name" as="xs:string?">
	<xsl:param name="arg" as="xs:string?"/>
	<xsl:sequence select="$arg"/>
</xsl:function>

<!-- Returns the name for the Java class for an NX base class name. -->
<xsl:function name="dawnsci:class-name" as="xs:string?">
	<xsl:param name="arg" as="xs:string?"/>
	<xsl:sequence select="concat($arg, 'Impl')"/>
</xsl:function>

<!-- Returns the name of the enum for an NX base class name -->
<xsl:function name="dawnsci:base-class-enum-name" as="xs:string">
	<xsl:param name="arg" as="xs:string"/>
	<xsl:sequence select="concat(substring($arg, 1, 2), '_', upper-case(substring($arg, 3)))"/>
</xsl:function>

</xsl:stylesheet>