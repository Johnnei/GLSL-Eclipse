package org.johnnei.glsl.editor.scanners;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.johnnei.glsl.editor.Activator;
import org.johnnei.glsl.editor.Glsl;
import org.johnnei.glsl.editor.GlslEditor;

public class GlslPreprocessorScanner extends RuleBasedScanner {
	
	public GlslPreprocessorScanner() {
		final TextAttribute attribute = new TextAttribute(
			GlslEditor.PREPROCESSOR_COLOR[Activator.getDefault().getTheme()], null, SWT.BOLD
		);
		final Token preprocessorToken = new Token(attribute);
		
		IRule[] rules = new IRule[Glsl.PREPROCESSORS.length];
		for (int i = 0; i < rules.length; i++) {
			rules[i] = new SingleLineRule(Glsl.PREPROCESSORS[i], null, preprocessorToken, '\0', true, false);
			
		}
		
		setRules(rules);
	}

}
