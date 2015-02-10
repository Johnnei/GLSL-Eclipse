package org.johnnei.glsl.editor.scanners;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * Attempts to match the given data with the format of a variable declaration
 *
 */
public class DeclarationRule implements IPredicateRule {
	
	private IToken succesToken;
	
	public DeclarationRule(IToken token) {
		succesToken = token;
	}

	@Override
	public IToken getSuccessToken() {
		return succesToken;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		return Token.UNDEFINED;
	}
	
	private void readWhitespace(ICharacterScanner scanner) {
		int character = 0;
		
		do {
			character = scanner.read();
		}
		while (Character.isWhitespace(character));
		
		scanner.unread();
	}
	
	private boolean startsWith(ICharacterScanner scanner, String[] options) {
		List<String> optionsCollection = Arrays.asList(options);
		int readCount = 0;
		
		readWhitespace(scanner);
		
		do {
			int index = readCount++;
			int character = scanner.read();
			
			optionsCollection.removeIf(s -> s.charAt(index) != character);
		} while (optionsCollection.size() > 0);
		
		// Reset state
		while (readCount-- > 0) {
			scanner.unread();
		}
		
		return false;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		return evaluate(scanner, false);
	}

}
