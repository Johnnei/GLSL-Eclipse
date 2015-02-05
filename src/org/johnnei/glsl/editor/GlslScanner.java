package org.johnnei.glsl.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.johnnei.glsl.preferences.PreferenceConstants;

public class GlslScanner extends RuleBasedScanner {
	
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
		
		int theme = Integer.parseInt(Activator.getDefault().getPreferenceStore().
				getString(PreferenceConstants.P_THEME));
		
		final Token keywordToken = new Token(new TextAttribute(GlslEditor.KEYWORD_COLOR[theme]));
		final Token typeToken = new Token(new TextAttribute(GlslEditor.TYPE_COLOR[theme]));
		
		List<IRule> rules = new ArrayList<>();
		
		rules.add(wordRule);
		
		for (String keyword : Glsl.KEYWORDS) {
			wordRule.addWord(keyword, keywordToken);
		}
		for (String keyword : Glsl.KEYWORDS_STYLED) {
			rules.add(new SingleLineRule(keyword, null, keywordToken));
		}
		for (String type : Glsl.TYPES) {
			wordRule.addWord(type, typeToken);
		}
		
		IRule[] rulesArray = new IRule[rules.size()];
		for (int i = 0; i < rulesArray.length; i++) {
			rulesArray[i] = rules.get(i);
		}
		
		setRules(rulesArray);
	}

}
