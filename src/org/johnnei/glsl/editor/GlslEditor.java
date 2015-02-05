package org.johnnei.glsl.editor;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.editors.text.TextEditor;

public class GlslEditor extends TextEditor {

	public static final Color KEYWORD_COLOR = new Color(Display.getCurrent(), 255, 0, 255);
	public static final Color TYPE_COLOR = new Color(Display.getCurrent(), 32, 87, 32);

	public GlslEditor() {
		super();
		setSourceViewerConfiguration(new GlslViewerConfiguration());
	}

}
