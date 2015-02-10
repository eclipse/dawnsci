/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.plotting.api.jreality.util;

/**
 *
 */
public class JOGLGLSLShaderGenerator {

    private static final String FRAGLOGPNAME = "Log";
    private static final String FRAGPROGNAME = "FloatProg";
    private static final String FRAGCOLORPASSTHROUGHNAME = "RGBPassThrough";
    private static final String DIFFRAGPROGNAME = "Diff";
    private static final String GRADIENTPROGNAME = "GradientImage";
    
    private static final String FRAGCOLORPASSTHROUGH =
    	"uniform sampler2D sampler;\n"+
    	"uniform sampler2D overlaySampler;\n"+
    	"void main(void)\n"+
    	"{\n"+
    	"vec4 image = texture2D(sampler,gl_TexCoord[0].st);\n"+
    	"vec4 overlay = texture2D(overlaySampler,gl_TexCoord[0].st);\n"+
    	"gl_FragColor = image;\n"+
    	"}\n";
    
    private static final String COMMOMFRAGPROGHEADPART = 
    	"uniform sampler2D sampler;\n"+
        "uniform sampler2D tableSampler;\n"+
        "uniform sampler2D overlaySampler;\n"+
        "uniform float maxValue;\n"+
        "uniform float minValue;\n";
//    	"uniform float cdf[256];\n";

    private static final String DIFFRACTIONHEADPART =
    	   "uniform float threshold;\n";

    private static final String GRADIENTHEADERPART = 
	    "uniform float threshold;\n"+
	    "uniform vec2 delta;\n";
    	
    private static final String LOGPROGPART = 
        "const float EULER = 2.7182818284590452353602874713527;\n"+
        "\n"+
        "float scaling(float invalue) {" +
        "	bool negValue = (invalue < 0.0);\n"+
        "	if (negValue)\n" +
        "		invalue = abs(invalue); \n"+
        "	if (invalue < EULER) \n" +
        "		invalue+=(EULER - invalue) / EULER;\n"+
        "	float outvalue = log(invalue); \n"+
        "	return (negValue ? -outvalue : outvalue);\n"+
        "}\n";
    
    private static final String COMMONMAINPART =
        "void main(void)\n"+
        "{\n"+
        " float dataValue = texture2D(sampler,gl_TexCoord[0].st).x;\n";

    private static final String DIFFRACTIONPART =
        " if (dataValue < -1.0) image = vec4(0.3,1.0,0.15,1.0);\n" +
        " if (dataValue >= threshold) image = vec4(1,1,0,1);\n";
    	
    private static final String COMMONENDPART = 
        " vec4 overlay = texture2D(overlaySampler, gl_TexCoord[0].st);\n"+
        " image = image * (1.0-overlay.w) + overlay * overlay.w;\n"+
    	" gl_FragColor = image;\n"+
    	"}\n";
    
    private static final String NORMALLINEARPART = 
        " float nDataValue = min(1.0,(dataValue - minValue) / (maxValue-minValue));\n"+
        " vec4 image = texture2D(tableSampler,vec2(nDataValue,nDataValue));\n";
    
    private static final String GRADIENTPART =
	    " float temp_X_Y = texture2D(sampler,gl_TexCoord[0].st - delta).x;\n"+
	    " float temp_Y = texture2D(sampler,gl_TexCoord[0].st + vec2(0,-delta.y)).x;\n"+
	    " float tempX_Y = texture2D(sampler,gl_TexCoord[0].st + vec2(delta.x,-delta.y)).x;\n"+
	    " float temp_X = texture2D(sampler,gl_TexCoord[0].st + vec2(-delta.x,0.0)).x;\n"+
	    " float tempX = texture2D(sampler,gl_TexCoord[0].st + vec2(delta.x,0.0)).x;\n"+
	    " float temp_XY = texture2D(sampler,gl_TexCoord[0].st + vec2(-delta.x,delta.y)).x;\n"+
	    " float tempY = texture2D(sampler,gl_TexCoord[0].st + vec2(0.0,delta.y)).x;\n"+
	    " float tempXY = texture2D(sampler,gl_TexCoord[0].st + delta).x;\n"+
	    " float gy = temp_XY + 2.0 * tempY + tempXY - temp_X_Y - 2.0 * temp_Y - tempX_Y;\n"+
	    " float gx = tempX_Y + 2.0 * tempX + tempXY - temp_X_Y - 2.0 * temp_X - temp_XY;\n"+
	    " float g = threshold * sqrt(gx*gx+gy*gy);\n"+
	    " float nDataValue = min(1.0,(g - minValue) / (maxValue-minValue));\n"+
    	" vec4 image = texture2D(tableSampler,vec2(nDataValue,nDataValue));\n";
    
    private static final String NORMALLOGPART = 
        " float nDataValue = min(1.0,(scaling(dataValue) - minValue) / (maxValue-minValue));\n"+
        " vec4 image = texture2D(tableSampler,vec2(nDataValue,nDataValue));\n";
	
	public static String generateShader(boolean useLog, 
										boolean isColour,
										boolean useDiffraction,
										boolean useGradient) {
		if (isColour) {
			return FRAGCOLORPASSTHROUGH;
		}
		String shaderCode = COMMOMFRAGPROGHEADPART;
		if (useDiffraction)
			shaderCode += DIFFRACTIONHEADPART;
		if (useGradient) {
			shaderCode+= GRADIENTHEADERPART;
			shaderCode+=COMMONMAINPART;
			shaderCode+=GRADIENTPART;
			shaderCode+=COMMONENDPART;
		} else {
			if (useLog) {
				shaderCode += LOGPROGPART;
				shaderCode += COMMONMAINPART;
				shaderCode += NORMALLOGPART;
				if (useDiffraction)
					shaderCode += DIFFRACTIONPART;
				shaderCode += COMMONENDPART;
			} else {
				shaderCode += COMMONMAINPART;
				shaderCode += NORMALLINEARPART;
				if (useDiffraction)
					shaderCode += DIFFRACTIONPART;
				shaderCode += COMMONENDPART;
			}
		}
		return shaderCode;
	}
	
	public static String generateShaderName(boolean useLog,
											boolean isColour,
											boolean userDiffraction,
											boolean useGradient) {
		
		if (isColour)
			return FRAGCOLORPASSTHROUGHNAME;
		String name = FRAGPROGNAME;
		if (useLog)
			name+="."+FRAGLOGPNAME;
		if (userDiffraction)
			name+="."+DIFFRAGPROGNAME;
		if (useGradient)
			name+="."+GRADIENTPROGNAME;
		return name;
	}
	
	public static String generateCompositeShader(boolean useLog, int numTextures) {
		String returnStr = "";
		for (int i = 0; i < numTextures; i++)
			returnStr +=  "uniform sampler2D sampler"+i+";\n";
		
		returnStr +=  "uniform sampler2D tableSampler;\n"+
					  "uniform float maxValue;\n"+
					  "uniform float minValue;\n"+ 
					  "uniform float weight[7];\n"+
					  "uniform int ops[7];\n"+
					  "uniform int isRGB[7];\n"+
					  "uniform vec3 mask[7];\n"+
	       			  "void main(void)\n"+
	       			  "{\n"+
					  "  vec4 result = vec4(0,0,0,1);\n"+
					  "  vec4 image = vec4(0,0,0,0);\n"+
					  "  float dataResult = 0.0;\n"+
					  "  float dataValue = 0.0;\n"+
					  "  float nDataValue = 0.0;\n"+
					  "  float totalRGBWeights = 0.0;\n"+
					  "  float totalNONRGBWeights = 0.0;\n";
		for (int i = 0; i < numTextures; i++) {
			if (i == 0) {
				returnStr += "  if (isRGB["+i+"] == 1) {\n" +
						     "     image = texture2D(sampler"+i+",gl_TexCoord[0].st);\n" +
						     "     result.xyz += weight["+i+"] * mask["+i+"].xyz * image.xyz;\n" +
						     "     totalRGBWeights += weight["+i+"];\n"+
						     "  } else {\n" +
						     "     dataValue = texture2D(sampler"+i+",gl_TexCoord[0].st).x;\n"+
						     "     nDataValue = min(1.0,(dataValue - minValue) / (maxValue-minValue));\n"+
						     "     dataResult += weight["+i+"] * nDataValue;\n"+
						     "     totalNONRGBWeights += weight["+i+"];\n"+
						     "  }\n";
			} else {
				returnStr += "  if (isRGB["+i+"] == 1) {\n" +
			                 "     image = texture2D(sampler"+i+",gl_TexCoord[0].st);\n" +
			                 "     totalRGBWeights += weight["+i+"];\n"+
			                 "     if (ops["+i+"] == 0)\n"+
			                 "        result.xyz += weight["+i+"] * mask["+i+"].xyz * image.xyz;\n" +
			                 "     else if (ops["+i+"] == 1)\n" +
			                 "        result.xyz -= weight["+i+"] * mask["+i+"].xyz * image.xyz;\n" +
			                 "     else if (ops["+i+"] == 2)\n" +
			                 "        result.xyz *= weight["+i+"] * mask["+i+"].xyz * image.xyz;\n" +
			                 "     else if (ops["+i+"] == 3)\n" +
			                 "        result.xyz /= weight["+i+"] * mask["+i+"].xyz * image.xyz;\n"+
			                 "     else if (ops["+i+"] == 4)\n" +
			                 "        result.xyz = min(result.xyz,weight["+i+"] * mask["+i+"].xyz * image.xyz);\n"+
			                 "     else if (ops["+i+"] == 5)\n" +
			                 "        result.xyz = max(result.xyz,weight["+i+"] * mask["+i+"].xyz * image.xyz);\n"+
			                 "  } else {\n" +
			                 "     totalNONRGBWeights += weight["+i+"];\n"+
			                 "     dataValue = texture2D(sampler"+i+",gl_TexCoord[0].st).x;\n"+
			                 "     nDataValue = min(1.0,(dataValue - minValue) / (maxValue-minValue));\n"+
			                 "     if (ops["+i+"] == 0)\n"+
				             "        dataResult += weight["+i+"] * nDataValue;\n"+
				             "     else if (ops["+i+"] == 1)\n"+
				             "        dataResult -= weight["+i+"] * nDataValue;\n"+
				             "     else if (ops["+i+"] == 2)\n"+
				             "        dataResult *= weight["+i+"] * nDataValue;\n"+
				             "     else if (ops["+i+"] == 3)\n"+
				             "        dataResult /= weight["+i+"] * nDataValue;\n"+
				             "     else if (ops["+i+"] == 4)\n"+
				             "        dataResult = min(dataResult,weight["+i+"] * nDataValue);\n"+
				             "     else if (ops["+i+"] == 5)\n"+
				             "        dataResult = max(dataResult,weight["+i+"] * nDataValue);\n"+
			                 "  }\n";				
			}
		}
		returnStr += "  totalNONRGBWeights = min(totalNONRGBWeights,1.0);\n";
		returnStr += "  totalRGBWeights = min(totalRGBWeights,1.0);\n";
		returnStr += "  image = texture2D(tableSampler,vec2(dataResult,dataResult));\n";
		returnStr += "  gl_FragColor = totalNONRGBWeights * image + totalRGBWeights * result;\n";
		returnStr += "}\n";
		return returnStr;
	}
	
	public static String generateCompositeShaderName(boolean useLog, int numTextures) {
		String returnStr = "compositeShader.#"+numTextures;
		return returnStr;
	}
}
