package org.johnnei.glsl.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.johnnei.glsl.preferences.PreferenceConstants;

public class GlslScanner extends RuleBasedScanner {
	
	public GlslScanner() {
		
		final WordRule wordRule = new WordRule(new IWordDetector() {
			
			@Override
			public boolean isWordStart(char c) {
				return c != ' ' && Character.isJavaIdentifierStart(c);
			}
			
			@Override
			public boolean isWordPart(char arg0) {
				return Character.isJavaIdentifierPart(arg0);
			}
		}, Token.WHITESPACE);
		
		int theme = Integer.parseInt(Activator.getDefault().getPreferenceStore().
				getString(PreferenceConstants.P_THEME));
		
		final Token keywordToken = new Token(new TextAttribute(GlslEditor.KEYWORD_COLOR[theme], null, SWT.BOLD));
		final Token preprocessorToken = new Token(new TextAttribute(GlslEditor.PREPROCESSOR_COLOR[theme], null, SWT.BOLD));
		final Token typeToken = new Token(new TextAttribute(GlslEditor.TYPE_COLOR[theme]));
		final Token qualifierToken = new Token(new TextAttribute(GlslEditor.QUALIFIER_COLOR[theme], null, SWT.BOLD));
		final Token functionToken = new Token(new TextAttribute(GlslEditor.FUNCTION_COLOR[theme]));
		final Token builtInVariableToken = new Token(new TextAttribute(GlslEditor.BUILT_IN_VARIABLES_COLOR[theme]));
		
		List<IRule> rules = new ArrayList<>();
		
		rules.add(wordRule);
		
		for (String keyword : Glsl.KEYWORDS) {
			wordRule.addWord(keyword, keywordToken);
		}
		for (String preprocessor : Glsl.PREPROCESSORS) {
			rules.add(new SingleLineRule(preprocessor, null, preprocessorToken));
		}
		for (String type : Glsl.TYPES) {
			wordRule.addWord(type, typeToken);
		}
		for (String qualifier : Glsl.QUALIFIERS) {
			wordRule.addWord(qualifier, qualifierToken);
		}
		for (String function : Glsl.FUNCTIONS) {
			wordRule.addWord(function, functionToken);
		}
		for (String variable : Glsl.VARIABLES) {
			wordRule.addWord(variable, builtInVariableToken);
		}
		
		rules.add(new WhitespaceRule(new IWhitespaceDetector() {
           public boolean isWhitespace(char c) {
              return Character.isWhitespace(c);
           }
        }));
		
		IRule[] rulesArray = new IRule[rules.size()];
		for (int i = 0; i < rulesArray.length; i++) {
			rulesArray[i] = rules.get(i);
		}
		
		setRules(rulesArray);
	}

}
