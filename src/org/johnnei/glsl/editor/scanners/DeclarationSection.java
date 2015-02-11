package org.johnnei.glsl.editor.scanners;

import java.util.Collection;
import java.util.LinkedList;

public class DeclarationSection {
	
	private final boolean optional;
	
	private final String[] options;
	
	public DeclarationSection(String[] options, boolean optional) {
		this.optional = optional;
		this.options = options;
	}
	
	public DeclarationSection(String[] options) {
		this(options, false);
	}
	
	public Collection<String> getOptions() {
		Collection<String> optionCollection = new LinkedList<>();
		for (String s : options) {
			optionCollection.add(s);
		}
		return optionCollection;
	}
	
	public boolean isOptional() {
		return optional;
	}

}
