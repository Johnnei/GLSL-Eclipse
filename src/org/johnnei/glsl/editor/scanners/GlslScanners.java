package org.johnnei.glsl.editor.scanners;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.johnnei.glsl.editor.Activator;
import org.johnnei.glsl.editor.Glsl;
import org.johnnei.glsl.editor.GlslEditor;


public class GlslScanners {

	public static final WordRule createWordRule() {
		return new WordRule(new IWordDetector() {
			
			@Override
			public boolean isWordStart(char c) {
				return c != ' ' && Character.isJavaIdentifierStart(c);
			}
			
			@Override
			public boolean isWordPart(char arg0) {
				return Character.isJavaIdentifierPart(arg0);
			}
		}, Token.WHITESPACE);
	}
	
	public static final RuleBasedScanner createPreprocessorScanner() {
		final TextAttribute attribute = new TextAttribute(
			GlslEditor.PREPROCESSOR_COLOR[Activator.getDefault().getTheme()], null, SWT.BOLD
		);
		final Token preprocessorToken = new Token(attribute);
		
		IRule[] rules = new IRule[Glsl.PREPROCESSORS.length];
		for (int i = 0; i < rules.length; i++) {
			rules[i] = new SingleLineRule(Glsl.PREPROCESSORS[i], null, preprocessorToken, '\0', true, false);
		}
		
		RuleBasedScanner scanner = new RuleBasedScanner();
		scanner.setRules(rules);
		return scanner;
	}
	
	public static final RuleBasedScanner createCommentScanner() {
		final TextAttribute attribute = new TextAttribute(
			GlslEditor.COMMENTS_COLOR[Activator.getDefault().getTheme()]
		);
		
		final Token commentToken = new Token(attribute);
		
		RuleBasedScanner scanner = new RuleBasedScanner();
		scanner.setDefaultReturnToken(commentToken);
		scanner.setRules(new IRule[0]);
		return scanner;
	}
	
}
