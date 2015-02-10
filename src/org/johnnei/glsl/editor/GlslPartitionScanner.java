package org.johnnei.glsl.editor;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

public class GlslPartitionScanner extends RuleBasedPartitionScanner {
	
	public static final String GLSL_PREPROCESSOR = "__glsl_preprocessor";
	public static final String GLSL_UNIFORM = "__glsl_declaration_uniform";
	public static final String GLSL_VARIABLE = "__glsl_declaration_variable";
	public static final String GLSL_COMMENT = "__glsl_comment";
	
	public static final String[] CONTENT_TYPES = {
		GLSL_PREPROCESSOR,
		GLSL_UNIFORM,
		GLSL_VARIABLE,
		GLSL_COMMENT
	};
	
	public GlslPartitionScanner() {
		IToken preprocessorToken = new Token(GLSL_PREPROCESSOR);
		IToken uniformToken = new Token(GLSL_UNIFORM);
		IToken commentToken = new Token(GLSL_COMMENT);
		
		setPredicateRules(new IPredicateRule[] {
			new SingleLineRule("#", null, preprocessorToken, '\0', true, false),
			new SingleLineRule("//", null, commentToken, '\0', true, false),
			new SingleLineRule("/*", "*/", commentToken),
		});
	}

}
