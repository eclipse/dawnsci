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

<xsl:variable name="base-classes" select="collection(concat($nxdlDefinitionsPath, '/base_classes?select=*.nxdl.xml'))/nx:definition"/>
<xsl:variable name="application-definitions" select="collection(concat($nxdlDefinitionsPath, '/applications?select=*.nxdl.xml'))/nx:definition"/>

<!-- Used for running with any XML input file -->
<xsl:template match="/">
	<xsl:call-template name="generate-java"/>
</xsl:template>

<!-- Direct entry point -->
<xsl:template name="generate-java">
	<xsl:apply-templates select="$application-definitions"/> 
	<xsl:call-template name="appdef-enum"/>
</xsl:template>

<!-- Template matches a definition in an NXDL application definition file. -->
<xsl:template match="nx:definition">

	<!-- The 'NXroot' base class -->
	<xsl:variable name="baseClass" select="$base-classes[@name = 'NXroot']"/>
	<!-- The name of this generated validator class. -->
	<xsl:variable name="validatorClassName" select="concat(@name, 'Validator')"/>
	<!-- The prefix of the validate group method. -->
	<xsl:variable name="validateGroupMethodNamePrefix" select="'validateGroup'"/>

	<!-- Set the output document for the Java output for this application definition. -->
	<xsl:result-document href="{$javaSourcePath}/org/eclipse/dawnsci/nexus/validation/{$validatorClassName}.java" format="text-format">
		<xsl:text>package org.eclipse.dawnsci.nexus.validation;&#10;</xsl:text>

		<!-- Apply the templates for imports. -->
		<xsl:apply-templates mode="imports" select="."/>

		<xsl:text>/**&#10;</xsl:text>
 		<xsl:text> * Validator for the application definition '</xsl:text><xsl:value-of select="@name"/><xsl:text>'.&#10;</xsl:text>
		<xsl:text> */&#10;</xsl:text>
		<xsl:text>public class </xsl:text>
		<xsl:value-of select="$validatorClassName"/>
		<xsl:text> extends AbstractNXValidator implements NXApplicationValidator {&#10;</xsl:text>
		<xsl:text>&#10;</xsl:text>
	
		<!-- Each validator class overrides the validate() method in the superclass-->
		<xsl:text>	@Override&#10;</xsl:text>
		<xsl:text>	public void validate(NXroot root) throws NexusValidationException {&#10;</xsl:text>
	
		<!-- For each group at the root level of the app def, invoke the generated validate method for that group. -->
		<xsl:apply-templates select="nx:group" mode="invocation">
			<xsl:with-param name="parentGroupVariableName" select="'root'"/>
			<xsl:with-param name="baseClass" select="$baseClass"/>
			<xsl:with-param name="validateGroupMethodNamePrefix" select="$validateGroupMethodNamePrefix"/>
		</xsl:apply-templates>
		
		<xsl:text>	}&#10;&#10;</xsl:text> <!-- Closing brace for validate() method -->
		
		<xsl:text>	@Override&#10;</xsl:text>
		<xsl:text>	public void validate(NXentry entry) throws NexusValidationException {&#10;</xsl:text>
		<xsl:text>//		validateGroup_entry(entry);  TODO validate entry&#10;</xsl:text>
		<xsl:text>	}&#10;&#10;</xsl:text>
		<xsl:text>	@Override&#10;</xsl:text>
		<xsl:text>	public void validate(NXsubentry subentry) throws NexusValidationException {&#10;</xsl:text>
		<xsl:text>//		validateGroup_entry(subentry);  TODO validate entry&#10;</xsl:text>
		<xsl:text>	}&#10;&#10;</xsl:text>
		
		<!-- For each group at the root level of the app def, generate a validate method -->
		<xsl:apply-templates select="nx:group" mode="implementation">
			<xsl:with-param name="validateGroupMethodNamePrefix" select="$validateGroupMethodNamePrefix"/>
		</xsl:apply-templates>
	
		<!-- Closing brace for validator class definition -->
		<xsl:text>}&#10;</xsl:text> 

	</xsl:result-document>
</xsl:template>

<!-- Template matches a group to add the invocation of the group's validate method. -->
<xsl:template match="nx:group[@type!='NXtransformations']" mode="invocation">
	<xsl:param name="baseClass"/>
	<xsl:param name="parentGroupVariableName" select="'group'"/>
	<xsl:param name="validateGroupMethodNamePrefix"/>
	
	<!-- Get the definition for this group in the base class, if it exists. -->
	<xsl:variable name="baseClassGroupDef" select="$baseClass/nx:group[@type=current()/@type]"/>
	<!-- The group name is the name in the baseclass, if it exists, else the type without the NX prefix.
	     This name, prefixed by 'get' is the name of the method in the method in the base class to use. -->
	<xsl:variable name="groupNameInBaseClass" select="if ($baseClassGroupDef/@name) then @name else substring(@type, 3)"/>
	<!-- True if there can be multiple occurrences of this group. -->
	<xsl:variable name="multiple" select="not(@name) and not(@maxOccurs='1')"/>
	<!-- True if this group is optional (false if group is multiple) -->
	<xsl:variable name="optional" select="not($multiple) and @minOccurs='0'"/>
	
	<!-- Line comment: validate (optional?) (unnamed?) group (<name>?) of type <type> ((possibly multiple)?) -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>// validate </xsl:text><xsl:if test="$optional">optional </xsl:if>
	<xsl:value-of select="if (@name) then concat('child group ''', @name, '''') else 'unnamed child group'"/>
	<xsl:text> of type </xsl:text><xsl:value-of select="@type"/>
	<xsl:if test="$multiple"> (possibly multiple)</xsl:if>
	<xsl:text>&#10;</xsl:text>

	<!-- Variable for method call to get group (or just group name if multiple), used in invocation of validateGroupXXX method -->
	<xsl:variable name="group">
		<xsl:choose>
			<!-- Just use the group name when multiple groups, this will be a variable in the for loop above, which gets the groups as a map -->
			<xsl:when test="$multiple">
				<xsl:value-of select="$groupNameInBaseClass"/>
			</xsl:when>
			<!-- When the base class does not include the group, just use getChild("@name", @type.class)-->
			<xsl:when test="not($baseClassGroupDef)">
				<xsl:value-of select="$parentGroupVariableName"/>
				<xsl:text>.getChild(</xsl:text>
				<xsl:text>"</xsl:text><xsl:value-of select="@name"/><xsl:text>", </xsl:text>
				<xsl:value-of select="@type"/>
				<xsl:text>.class)</xsl:text>
			</xsl:when>
			<!-- In the general case, just do parentGroupName.getChildGroupName() -->
			<xsl:otherwise>
				<xsl:value-of select="$parentGroupVariableName"/>
				<xsl:text>.get</xsl:text><xsl:value-of select="dawnsci:capitalise-first($groupNameInBaseClass)"/>
				<xsl:text>()</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:variable>
	
	<xsl:if test="$multiple">
		
		<!-- Line to get the map of all groups of the given type, e.g. final Map<String, NXSample> allSample = group.getAllSample() -->
		<xsl:value-of select="dawnsci:tabs(2)"/>
		<xsl:text>final Map&lt;String, </xsl:text><xsl:value-of select="@type"/>
		<xsl:text>&gt; all</xsl:text><xsl:value-of select="dawnsci:capitalise-first($groupNameInBaseClass)"/>
		<xsl:text> = </xsl:text><xsl:value-of select="$parentGroupVariableName"/>
		<xsl:text>.getAll</xsl:text><xsl:value-of select="dawnsci:capitalise-first($groupNameInBaseClass)"/>
		<xsl:text>();&#10;</xsl:text>

		<!-- For loop over values -->
		<xsl:value-of select="dawnsci:tabs(2)"/>
		<xsl:text>for (final </xsl:text><xsl:value-of select="@type"/><xsl:text> </xsl:text><xsl:value-of select="$groupNameInBaseClass"/>
		<xsl:text> : all</xsl:text><xsl:value-of select="dawnsci:capitalise-first($groupNameInBaseClass)"/>
		<xsl:text>.values()) {&#10;</xsl:text>
	</xsl:if>
	
	<!-- Null test for optional groups -->
	<xsl:if test="$optional">
		<xsl:value-of select="dawnsci:tabs(2)"/>
		<xsl:text>if (</xsl:text><xsl:value-of select="$group"/>
		<xsl:text> != null) {&#10;</xsl:text>
	</xsl:if>
	
	<!-- Invoke the method to validate the group. -->
	<xsl:value-of select="dawnsci:tabs(if ($multiple or $optional) then 3 else 2)"/>
	<xsl:value-of select="dawnsci:validateGroupMethodName($validateGroupMethodNamePrefix, current())"/>
	<xsl:text>(</xsl:text>
	<xsl:value-of select="$group"/>
	<xsl:text>);&#10;</xsl:text>

	<!-- Closing brace for either for loop (multiple) or null test (optional) -->
	<xsl:if test="$multiple or $optional">
		<xsl:value-of select="dawnsci:tabs(2)"/>
		<xsl:text>}&#10;</xsl:text>
	</xsl:if>
	
	<!-- Blank line if there are more groups. -->
	<xsl:if test="following-sibling::nx:group"><xsl:text>&#10;</xsl:text></xsl:if>

</xsl:template> <!-- End of template for validate group method invocation --> 


<!-- Template to match special case of NXtransformations group to generate invocation of validate method for group-->
<xsl:template match="nx:group[@type='NXtransformations']" mode="invocation">
	<xsl:param name="validateGroupMethodNamePrefix"/>

	<!-- Line comment: validate tranformations -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>// validate NXtransformations groups (special case)&#10;</xsl:text>

	<!-- Line to get all transformations -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>final Map&lt;String, NXtransformations&gt; allTransformations = group.getChildren(NXtransformations.class);&#10;</xsl:text>

	<!-- Call to validate transformations -->
	<!-- Note: we assume that this field has a preceding sibling 'depends_on' -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>validateTransformations(allTransformations, depends_on.getString(0));&#10;</xsl:text>

	<!-- Blank line if there are more groups. -->
	<xsl:if test="following-sibling::nx:group"><xsl:text>&#10;</xsl:text></xsl:if>

</xsl:template>


<!-- Template matches a group to generate the validate method implementation for that group. -->
<xsl:template match="nx:group[@type!='NXtransformations']" mode="implementation">
	<xsl:param name="validateGroupMethodNamePrefix"/>
	<xsl:variable name="validateGroupMethodName" select="dawnsci:validateGroupMethodName($validateGroupMethodNamePrefix, current())"/>
	<xsl:variable name="baseClass" select="$base-classes[@name = current()/@type]"/>
	
	<!-- Javadoc comment for method. -->
	<xsl:text>
	/**
	 * Validate </xsl:text><xsl:if test="@minOccurs='0'">optional </xsl:if><xsl:value-of select="if (@name) then concat('group ''', @name, '''') else 'unnamed group'"/> of type <xsl:value-of select="@type"/><xsl:text>.
	 */
	private void </xsl:text>
	
	<!-- Method signature -->
	<xsl:value-of select="$validateGroupMethodName"/>
	<xsl:text>(final </xsl:text><xsl:value-of select="@type"/><xsl:text> group) throws NexusValidationException {&#10;</xsl:text>
	
	<!-- Line comment for group null validation -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>// validate that the group is not null&#10;</xsl:text>
		
	<!-- Invocation of method validateGroupNotNull in abstract superclass -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>validateGroupNotNull(</xsl:text>
	<xsl:value-of select="if (@name) then concat('&quot;', @name, '&quot;') else 'null'"/>
	<xsl:text>, </xsl:text><xsl:value-of select="@type"/><xsl:text>.class, group);&#10;</xsl:text>
	
	<!-- If the group has dimensions defined in the baseclass, clear the local group placeholder value cache. -->
	<xsl:if test="$baseClass//nx:dim">
		<xsl:value-of select="dawnsci:tabs(2)"/>
		<xsl:text>clearLocalGroupDimensionPlaceholderValues();&#10;</xsl:text>
	</xsl:if>
	
	<xsl:text>&#10;</xsl:text> <!-- blank line -->
	
	<!-- Validate attribute and fields within the group. -->
	<xsl:apply-templates select="nx:attribute|nx:field">
		<xsl:with-param name="baseClass" select="$baseClass"/>
	</xsl:apply-templates>
	
	<!-- Validate child groups  -->
	<xsl:apply-templates select="nx:group" mode="invocation">
		<xsl:with-param name="baseClass" select="$baseClass"/>
		<xsl:with-param name="validateGroupMethodNamePrefix" select="$validateGroupMethodName"/>
	</xsl:apply-templates>
	
	<!-- Closing brace for validateGroup method. -->
	<xsl:text>	}&#10;</xsl:text>
	
	<!-- Recursively generate validateGroup methods for child groups. -->
	<xsl:apply-templates select="nx:group" mode="implementation">
		<xsl:with-param name="validateGroupMethodNamePrefix" select="$validateGroupMethodName"/>
	</xsl:apply-templates>
</xsl:template> <!-- End of template for validate group method implementation -->


<!-- Empty template for NXtransformations group implementation, as this validation is done by
	calling a method in the abstract superclass, rather than calling a generated method in this class. -->
<xsl:template match="nx:group[@type='NXtransformations']" mode="implementation" />

<!-- Template matches an attribute to add method call to validate that attribute. -->
<xsl:template match="nx:attribute">
	<xsl:param name="baseClass"/>
	
	<!-- Definition of attribute in the base class for the group's type, if it exists. -->
	<xsl:variable name="baseClassAttributeDef" select="$baseClass/nx:attribute[@name=current()/@name]"></xsl:variable>
	<!-- The variable name of the attribute -->
	<xsl:variable name="attrVarName" select="concat(@name, 'Attr')"/>
	
	<!-- Line comment: validate attribute 'attributeName' -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>// validate attribute '</xsl:text><xsl:value-of select="@name"/><xsl:text>'&#10;</xsl:text>

	<!-- Get the attribute from the group, e.g. final Attribute entryAttr = group.getAttribute("entry"); -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>final Attribute </xsl:text>
	<xsl:value-of select="$attrVarName"/><xsl:text> = group.getAttribute("</xsl:text>
	<xsl:value-of select="@name"/><xsl:text>");&#10;</xsl:text>

	<!-- Invoke method validateAttributeNotNull in abstract superclass to validate attribute not null. -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>validateAttributeNotNull("</xsl:text><xsl:value-of select="@name"/>
	<xsl:text>", </xsl:text><xsl:value-of select="$attrVarName"/><xsl:text>);&#10;</xsl:text>
	
	<!-- Validate the attribute's type, if defined (either in the application definition or the base class)  -->
	<xsl:call-template name="validate-dataset-type">
		<xsl:with-param name="baseClassFieldOrAttributeDef" select="$baseClassAttributeDef"/>
		<xsl:with-param name="tabLevel" select="2"/>
	</xsl:call-template>

	<!-- Blank line before next attribute / first field validation. -->
	<xsl:text>&#10;</xsl:text>

</xsl:template> <!-- End of template for nx:attribute -->


<!-- Template matches a field to add method calls to validate that field. -->
<xsl:template match="nx:field">
	<xsl:param name="baseClass"/>
	
	<!-- Field definition in base class, if it exists. -->
	<xsl:variable name="baseClassFieldDef" select="$baseClass/nx:field[@name=current()/@name]"/>
	<!-- True if the field is optional (minOccurrs = 0) -->
	<xsl:variable name="optional" select="@minOccurs='0'"/>
	<!-- The method call to get the field's dataset -->
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
	
	<!-- Line comment for field validation: validate (optional)? field 'fieldName' of type 'NXtype' -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>// validate </xsl:text><xsl:if test="$optional">optional </xsl:if>
	<xsl:text>field '</xsl:text><xsl:value-of select="@name"/>
	<xsl:text>' of </xsl:text>
	<xsl:value-of select="if (@type) then concat('type ', @type) else 'unknown type'"/><xsl:text>.</xsl:text>
	<xsl:if test="not($baseClassFieldDef)"> Note: field not defined in base class.</xsl:if>
	<xsl:text>&#10;</xsl:text>
	
	<!-- Line to get the field's dataset, e.g. final IDataset title = group.getTitle(); -->
	<xsl:value-of select="dawnsci:tabs(2)"/>
	<xsl:text>final IDataset </xsl:text>
	<xsl:value-of select="@name"/><xsl:text> = group.</xsl:text>
	<xsl:value-of select="$getFieldDatasetMethod"/><xsl:text>;&#10;</xsl:text>
	
	<!-- Null check for optional fields, validate not null for mandatory fields. -->
	<xsl:choose>
		<xsl:when test="$optional">
			<xsl:value-of select="dawnsci:tabs(2)"/>
			<xsl:text>if (</xsl:text><xsl:value-of select="@name"/><xsl:text> != null) {&#10;</xsl:text>
		</xsl:when>
		<xsl:otherwise>
			<xsl:value-of select="dawnsci:tabs(2)"/>
			<xsl:text>validateFieldNotNull("</xsl:text><xsl:value-of select="@name"/>)<xsl:text>", </xsl:text>
			<xsl:value-of select="@name"/><xsl:text>);&#10;</xsl:text>
		</xsl:otherwise>
	</xsl:choose>
		
	<!-- Note: have to call-templates rather than apply-templates as type/unit/enumeration
	could be defined in the application definition only, the base class only or both.
	Output is only produced if either the application definition or base class defines a type/unit/enumeration -->
	<xsl:variable name="tabLevel" select="if ($optional) then 3 else 2"/>

	<!-- Validate the field's type if defined (either in the application definition or the base class)-->
	<xsl:call-template name="validate-dataset-type">
		<xsl:with-param name="baseClassFieldOrAttributeDef" select="$baseClassFieldDef"/>
		<xsl:with-param name="nodeType" select="'field'"/>
		<xsl:with-param name="tabLevel" select="$tabLevel"/>
	</xsl:call-template>

	<!-- Validate the field's units if defined (either in the application definition or the base class) -->
	<xsl:call-template name="validate-field-units">
		<xsl:with-param name="baseClassFieldDef" select="$baseClassFieldDef"/>
		<xsl:with-param name="tabLevel" select="$tabLevel"/>
	</xsl:call-template>

	<!-- Validate the field's value belongs to the enumeration of permitted values, if defined
			(either in the application definition or the base class) -->
	<xsl:call-template name="validate-enumeration">
		<xsl:with-param name="baseClassFieldDef" select="$baseClassFieldDef"/>
		<xsl:with-param name="tabLevel" select="$tabLevel"/>
	</xsl:call-template>

	<!-- Validate the field's dimensions, if defined (either in the application definition or the base class) -->
	<xsl:call-template name="validate-dimensions">
		<xsl:with-param name="baseClassFieldDef" select="$baseClassFieldDef"/>
		<xsl:with-param name="tabLevel" select="$tabLevel"/>
	</xsl:call-template>

	<!-- Closing brace for null test (only if optional) -->
	<xsl:if test="$optional">
		<xsl:value-of select="dawnsci:tabs(2)"/><xsl:text>}&#10;</xsl:text>
	</xsl:if>
	
	<!-- Blank line before validating next field -->
	<xsl:if test="following-sibling::nx:field|following-sibling::nx:group">
		<xsl:text>&#10;</xsl:text>
	</xsl:if>
	
</xsl:template> <!-- End of parent template for nx:field -->


<!-- Template to validate the type of a field or attribute's dataset -->
<xsl:template name="validate-dataset-type">
	<!-- The definition in the base class for the field or attribute -->
	<xsl:param name="baseClassFieldOrAttributeDef"/>
	<!-- The node type: 'field' or 'attribute' -->
	<xsl:param name="nodeType"/>
	<xsl:param name="tabLevel"/>
	
	<!-- The field or attribute's type. If defined in the app def, this overrides the base class --> 
	<xsl:variable name="type" select="if (@type) then @type else $baseClassFieldOrAttributeDef/@type"/>

	<!-- Invoke method validateFieldType() or validateAttributeType() in abstract superclass. -->
	<xsl:if test="$type">
		<xsl:value-of select="dawnsci:tabs($tabLevel)"/>
		<xsl:text>validate</xsl:text><xsl:value-of select="dawnsci:capitalise-first($nodeType)"/><xsl:text>Type("</xsl:text>
		<xsl:value-of select="@name"/><xsl:text>)", </xsl:text>
		<xsl:value-of select="@name"/>, <xsl:value-of select="$type"/><xsl:text>);&#10;</xsl:text>
	</xsl:if>
	
</xsl:template>

<!-- Template to validate the units of a field -->
<xsl:template name="validate-field-units">
	<xsl:param name="baseClassFieldDef"/>
	<xsl:param name="tabLevel"/>
	
	<xsl:variable name="units" select="if (@units) then @units else $baseClassFieldDef/@units"/>

	<!-- Invoke method validateFieldUnits() in validator abstract superclass. -->
	<xsl:if test="$units">
		<xsl:value-of select="dawnsci:tabs($tabLevel)"/>
		<xsl:text>validateFieldUnits("</xsl:text><xsl:value-of select="@name"/><xsl:text>", </xsl:text>
		<xsl:value-of select="@name"/>, <xsl:value-of select="$units"/><xsl:text>);&#10;</xsl:text>
	</xsl:if>
</xsl:template>

<!-- Template to validate the permitted values of an enumeration -->
<xsl:template name="validate-enumeration">
	<xsl:param name="baseClassFieldDef"/>
	<xsl:param name="tabLevel"/>

	<!-- The enumeration items. Values present in the application definition override those in the base class, if both present. -->
	<xsl:variable name="items" select="if (nx:enumeration) then nx:enumeration/nx:item else $baseClassFieldDef/nx:enumeration/nx:item"/>
	
	<!-- Invoke call to method validateFieldEnumeration() in the abstract superclass. -->
	<xsl:if test="$items">
		<xsl:value-of select="dawnsci:tabs($tabLevel)"/>
		<xsl:text>validateFieldEnumeration("</xsl:text><xsl:value-of select="@name"/><xsl:text>", </xsl:text>
		<xsl:value-of select="@name"/><xsl:for-each select="$items"><xsl:text>,&#10;</xsl:text>
		<xsl:value-of select="dawnsci:tabs($tabLevel + 2)"/>
		<xsl:text>"</xsl:text><xsl:value-of select="@value"/>"</xsl:for-each><xsl:text>);&#10;</xsl:text>
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
		<xsl:text>validateFieldRank("</xsl:text><xsl:value-of select="@name"/><xsl:text>", </xsl:text>
		<xsl:value-of select="@name"/>, <xsl:value-of select="$rank"/><xsl:text>);&#10;</xsl:text>
	</xsl:if>
	
	<!-- Output the invocation of validateFieldDimensions to validate the size of each dimension (where specified). -->
	<xsl:variable name="dimsFromAppDef" select="nx:dimensions/nx:dim"/> 
	<xsl:variable name="dims" select="if ($dimsFromAppDef) then $dimsFromAppDef else $baseClassFieldDef/nx:dimensions/nx:dim"/>
	<xsl:if test="$dims">
		<xsl:value-of select="dawnsci:tabs($tabLevel)"/>
		<xsl:text>validateFieldDimensions("</xsl:text><xsl:value-of select="@name"/>", <xsl:value-of select="@name"/><xsl:text>, </xsl:text>
		<xsl:value-of select="if ($dimsFromAppDef) then 'null' else concat('&quot;', ../@type, '&quot;')"/>
		<xsl:for-each select="$dims"><xsl:text>, </xsl:text>
		<!-- If the dim value is a number, output it as it is, otherwise enclose it in quotes. -->
		<xsl:variable name="dimValue" select="if (number(@value) = number(@value)) then @value else concat('&quot;', @value, '&quot;')"/>
		<xsl:value-of select="$dimValue"/></xsl:for-each><xsl:text>);&#10;</xsl:text>
	</xsl:if>
	
</xsl:template>

<!-- Imports -->
<xsl:template mode="imports" match="nx:definition">

<!-- Imports for all validator classes -->
	<xsl:text>import static org.eclipse.dawnsci.nexus.validation.NexusDataType.*;&#10;</xsl:text>
	<xsl:text>import static org.eclipse.dawnsci.nexus.validation.NexusUnitCategory.*;&#10;</xsl:text>
	<xsl:text>&#10;</xsl:text>

	<!-- Import java.util.Map if there are any unnamed groups unless maxOccurs = 1 -->
	<xsl:if test="//nx:group[not(@name) and not(@maxOccurs='1')]">
		<xsl:text>import java.util.Map;&#10;</xsl:text>
		<xsl:text>&#10;</xsl:text>
	</xsl:if>

	<xsl:text>import org.eclipse.dawnsci.analysis.api.dataset.IDataset;&#10;</xsl:text>
	<xsl:if test="//nx:attribute">
		<xsl:text>import org.eclipse.dawnsci.analysis.api.tree.Attribute;&#10;</xsl:text>
	</xsl:if>
	<xsl:text>&#10;</xsl:text>

	<!-- Import generated base classes as required for group type -->
	<xsl:text>import org.eclipse.dawnsci.nexus.NXroot;&#10;</xsl:text>
	<xsl:text>import org.eclipse.dawnsci.nexus.NXsubentry;&#10;</xsl:text>
	<xsl:apply-templates select="//nx:group" mode="imports"/>
	<xsl:text>&#10;</xsl:text>
	
</xsl:template>

<!-- A template that matches groups and returns an import statement for the base class for that group -->
<xsl:template mode="imports" match="nx:group">import org.eclipse.dawnsci.nexus.<xsl:value-of select="@type"/>;
</xsl:template>

<!-- Template to generate an enumeration of NeXus application definitions -->
<xsl:template name="appdef-enum">
	<xsl:result-document href="{$javaSourcePath}/org/eclipse/dawnsci/nexus/NexusApplicationDefinition.java" format="text-format">
		<xsl:text>package org.eclipse.dawnsci.nexus;
		
/**
 * Enumeration of NeXus application definitions.
 */
public enum NexusApplicationDefinition {

</xsl:text>
		<xsl:apply-templates mode="appdef-enum" select="$application-definitions"/>

<xsl:text>}&#10;</xsl:text>
	</xsl:result-document>
</xsl:template>

<!-- Template to produce the enum value for a nexus application definition -->
<xsl:template mode="appdef-enum" match="nx:definition">
	<xsl:text>	</xsl:text><xsl:value-of select="dawnsci:appdef-enum-name(@name)"/><xsl:text>,&#10;</xsl:text>
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

<!-- A function to get the enum value for an application definition, i.e. convert it to
    the Java convention for constants, e.g. NXtomo -> NX_TOMO -->
<xsl:function name="dawnsci:appdef-enum-name" as="xs:string">
	<xsl:param name="arg" as="xs:string"/>
	<xsl:sequence select="concat(substring($arg, 1, 2), '_', upper-case(substring($arg, 3)))"/>
</xsl:function>

</xsl:stylesheet> 