package org.johnnei.glsl.editor.scanners;

import java.util.Collection;

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
	
	private DeclarationSection[] sections;
	
	public DeclarationRule(IToken token, DeclarationSection[] sections) {
		this.sections = sections;
		succesToken = token;
	}

	@Override
	public IToken getSuccessToken() {
		return succesToken;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		ConsumeResult whitespaceConsume = readWhitespace(scanner);
		
		if (whitespaceConsume.foundLineEnding || whitespaceConsume.foundEOF) {
			while (whitespaceConsume.readCount-- > 0) {
				scanner.unread();
			}
			return Token.UNDEFINED;
		}
		
		for (DeclarationSection declaration : sections) {
			if (!startsWith(scanner, declaration.getOptions()) 
					&& !declaration.isOptional()) {
				return Token.UNDEFINED;
			}
		}
		
		consumeLine(scanner);
		
		return succesToken;
	}
	
	private ConsumeResult readWhitespace(ICharacterScanner scanner) {
		int character = 0;
		int readCount = -1;
		boolean lineBreak = false;
		
		do {
			readCount++;
			character = scanner.read();
			
			if (character == '\r' || character == '\n') {
				lineBreak = true;
			}
			
			if (character == ICharacterScanner.EOF) {
				return new ConsumeResult(lineBreak, true, readCount);
			}
		}
		while (Character.isWhitespace(character));
		
		scanner.unread();
		return new ConsumeResult(lineBreak, false, readCount);
	}
	
	private void consumeLine(ICharacterScanner scanner) {
		int character = 0;
		do {
			character = scanner.read();
		} while (character != '\r' && character != '\n' && character != ICharacterScanner.EOF);
	}
	
	private boolean startsWith(ICharacterScanner scanner, Collection<String> options) {
		int readCount = readWhitespace(scanner).readCount;
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
			options.removeIf(s -> charIndex >= s.length() || s.charAt(charIndex) != character);
		} while (options.size() > 0);
		
		if (options.contains(readString.toString())) {
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
	
	private class ConsumeResult {
		
		public final boolean foundLineEnding;
		
		public final boolean foundEOF; 
		
		public int readCount;
		
		public ConsumeResult(final boolean foundLineEnding, 
				final boolean foundEOF, final int readCount) {
			this.foundLineEnding = foundLineEnding;
			this.foundEOF = foundEOF;
			this.readCount = readCount;
		}
		
	}

}
