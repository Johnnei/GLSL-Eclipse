package org.johnnei.glsl.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.johnnei.glsl.editor.scanners.GlslScanners;

public class GlslScanner extends RuleBasedScanner {
	
	public GlslScanner() {
		final WordRule wordRule = GlslScanners.createWordRule();
		int theme = Activator.getDefault().getTheme();
		
		final Token keywordToken = new Token(new TextAttribute(GlslEditor.KEYWORD_COLOR[theme], null, SWT.BOLD));
		final Token typeToken = new Token(new TextAttribute(GlslEditor.TYPE_COLOR[theme]));
		final Token qualifierToken = new Token(new TextAttribute(GlslEditor.QUALIFIER_COLOR[theme], null, SWT.BOLD));
		final Token functionToken = new Token(new TextAttribute(GlslEditor.FUNCTION_COLOR[theme]));
		final Token builtInVariableToken = new Token(new TextAttribute(GlslEditor.BUILT_IN_VARIABLES_COLOR[theme]));
		final Token commentToken = new Token(new TextAttribute(GlslEditor.COMMENTS_COLOR[theme]));
		
		List<IRule> rules = new ArrayList<>();

		// Rules which affect entire lines at once
		rules.add(new SingleLineRule("//", null, commentToken, '\0', true, false));
		
		// Rules which don't affect entire lines
		rules.add(new SingleLineRule("/*", "*/", commentToken));
		rules.add(wordRule);
		
		GlslScanners.addToWordRule(wordRule, Glsl.KEYWORDS, keywordToken);
		GlslScanners.addToWordRule(wordRule, Glsl.TYPES, typeToken);
		GlslScanners.addToWordRule(wordRule, Glsl.QUALIFIERS, qualifierToken);
		GlslScanners.addToWordRule(wordRule, Glsl.FUNCTIONS, functionToken);
		GlslScanners.addToWordRule(wordRule, Glsl.VARIABLES, builtInVariableToken);
		
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
