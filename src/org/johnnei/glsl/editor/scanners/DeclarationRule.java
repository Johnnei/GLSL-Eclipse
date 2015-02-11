package org.johnnei.glsl.editor.scanners;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.johnnei.glsl.editor.Glsl;

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
		int whiteSpacecount = readWhitespace(scanner);
		
		if (whiteSpacecount > 0) {
			while (whiteSpacecount-- > 0) {
				scanner.unread();
			}
			return Token.UNDEFINED;
		}
		
		if (!startsWith(scanner, Glsl.QUALIFIERS))
			return Token.UNDEFINED;
		
		if (!startsWith(scanner, Glsl.TYPES))
			return Token.UNDEFINED;
		
		consumeLine(scanner);
		
		return succesToken;
	}
	
	private int readWhitespace(ICharacterScanner scanner) {
		int character = 0;
		int readCount = -1;
		
		do {
			readCount++;
			character = scanner.read();
		}
		while (Character.isWhitespace(character));
		
		scanner.unread();
		return readCount;
	}
	
	private void consumeLine(ICharacterScanner scanner) {
		int character = 0;
		do {
			character = scanner.read();
		} while (character != '\r' && character != '\n' && character != ICharacterScanner.EOF);
	}
	
	private boolean startsWith(ICharacterScanner scanner, String[] options) {
		Collection<String> optionsCollection = new LinkedList<>();
		for (String s : options) {
			optionsCollection.add(s);
		}
		
		int readCount = readWhitespace(scanner);
		int index = -1;
		StringBuilder readString = new StringBuilder();
		
		do {
			readCount++;
			index++;
			int character = scanner.read();
			
			if (Character.isWhitespace(character)) {
				// End of word
				break;
			}
			
			readString.append((char) character);
			
			final int charIndex = index;
			optionsCollection.removeIf(s -> charIndex >= s.length() || s.charAt(charIndex) != character);
		} while (optionsCollection.size() > 0);
		
		if (optionsCollection.contains(readString.toString())) {
			return true;
		}
		
		// Failed to find match. Reset state
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
