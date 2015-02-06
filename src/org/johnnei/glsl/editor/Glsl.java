package org.johnnei.glsl.editor;

public class Glsl {

	public static final String[] KEYWORDS = {
		"uniform",
		"in",
		"out",
		"void"
	};
	
	/**
	 * Keywords which are not in the same style as java
	 * and need to be registered as a line parser
	 */
	public static final String[] KEYWORDS_STYLED = {
		"#version"
	};
	
	public static final String[] TYPES = {
		// Float types
		"float",
		"vec2",
		"vec3",
		"vec4",
		"mat2",
		"mat2x2",
		"mat2x3",
		"mat2x4",
		"mat3",
		"mat3x2",
		"mat3x3",
		"mat3x4",
		"mat4",
		"mat4x2",
		"mat4x3",
		"mat4x4",
		
		// Double types
		"double",
		"dvec2",
		"dvec3",
		"dvec4",
		"dmat2",
		"dmat2x2",
		"dmat2x3",
		"dmat2x4",
		"dmat3",
		"dmat3x2",
		"dmat3x3",
		"dmat3x4",
		"dmat4",
		"dmat4x2",
		"dmat4x3",
		"dmat4x4",
		
		// signed int types
		"int",
		"ivec2",
		"ivec3",
		"ivec4",
		
		// unsigned int types
		"uint",
		"uvec2",
		"uvec3",
		"uvec4",
		
		// boolean types
		"bool",
		"bvec2",
		"bvec3",
		"bvec4",
		
		// floating point opaque types
		"sampler1D",
		"sampler2D",
		"sampler3D",
		"samplerCube",
		"sampler2DRect",
		"sampler1DArray",
		"sampler2DArray",
		"samplerBuffer",
		"sampler2DMS",
		"sampler2DMSArray",
		"samplerCubeArray",
		"image1D",
		"image2D",
		"image3D",
		"imageCube",
		"image2DRect",
		"image1DArray",
		"image2DArray",
		"imageBuffer",
		"image2DMS",
		"image2DMSArray",
		"imageCubeArray",
		"sampler1DShadow",
		"sampler2DShadow",
		"sampler2DRectShadow",
		"sampler1DArrayShadow",
		"sampler2DArrayShadow",
		"samplerCubeShadow",
		"samplerCubeArrayShadow"
	};

}
