package org.johnnei.glsl.editor;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

public class GlslScanner extends RuleBasedScanner {
	
	private final String[] KEYWORDS = {
		"uniform",
		"in",
		"out",
		"#version",
		"void"
	};
	
	private final String[] TYPES = {
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
		"bvec4"
	};
	
	public GlslScanner() {
		final WordRule wordRule = new WordRule(new IWordDetector() {
			
			@Override
			public boolean isWordStart(char arg0) {
				return Character.isJavaIdentifierStart(arg0);
			}
			
			@Override
			public boolean isWordPart(char arg0) {
				return Character.isJavaIdentifierPart(arg0);
			}
		});
		
		final Token keywordToken = new Token(new TextAttribute(GlslEditor.KEYWORD_COLOR));
		final Token typeToken = new Token(new TextAttribute(GlslEditor.TYPE_COLOR));
		
		for (String keyword : KEYWORDS) {
			wordRule.addWord(keyword, keywordToken);
		}
		for (String type : TYPES) {
			wordRule.addWord(type, typeToken);
		}
		
		setRules(new IRule[] {
				wordRule
		});
	}

}
