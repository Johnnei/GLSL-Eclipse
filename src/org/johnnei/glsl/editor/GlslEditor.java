package org.johnnei.glsl.editor;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.johnnei.glsl.preferences.PreferenceUtils;

public class GlslEditor extends TextEditor {

	public static final Color KEYWORD_COLOR[] = {
		new Color(Display.getCurrent(), 221, 40, 103),
		new Color(Display.getCurrent(), 221, 40, 103)
	};
	
	public static final Color QUALIFIER_COLOR[] = {
		new Color(Display.getCurrent(), 221, 40, 103),
		new Color(Display.getCurrent(), 221, 40, 103)
	};
	
	public static final Color PREPROCESSOR_COLOR[] = {
		new Color(Display.getCurrent(), 221, 40, 103),
		new Color(Display.getCurrent(), 221, 40, 103)
	};
	
	public static final Color TYPE_COLOR[] = {
		new Color(Display.getCurrent(), 0, 0, 0),
		new Color(Display.getCurrent(), 18, 144, 195)
	};
	
	public static final Color FUNCTION_COLOR[] = {
		new Color(Display.getCurrent(), 0, 0, 0),
		new Color(Display.getCurrent(), 167, 236, 33)
	};
	
	public static final Color BUILT_IN_VARIABLES_COLOR[] = {
		new Color(Display.getCurrent(), 0, 0, 0),
		new Color(Display.getCurrent(), 128, 242, 246)
	};
	
	public static final Color COMMENTS_COLOR[] = {
		new Color(Display.getCurrent(), 167, 236, 33),
		new Color(Display.getCurrent(), 222, 222, 222)
	};

	public GlslEditor() {
		super();
		setDocumentProvider(new GlslDocumentProvider());
		setSourceViewerConfiguration(new GlslViewerConfiguration());
	}
	
	@Override
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {
		
		ISourceViewer sourceViewer = super.createSourceViewer(parent, ruler, styles);
		
		// Apply preference settings from the default editor
		IPreferenceStore store = EditorsUI.getPreferenceStore();
		
		Color backgroundColor = PreferenceUtils.colorFromString(
				store.getString(PREFERENCE_COLOR_BACKGROUND));
		Color foregroundColor = PreferenceUtils.colorFromString(
				store.getString(PREFERENCE_COLOR_FOREGROUND));
		Color selectionBackgroundColor = PreferenceUtils.colorFromString(
				store.getString(PREFERENCE_COLOR_SELECTION_BACKGROUND));
		Color selectionForegroundColor = PreferenceUtils.colorFromString(
				store.getString(PREFERENCE_COLOR_SELECTION_FOREGROUND));
		
		final StyledText textWidget = sourceViewer.getTextWidget();
		textWidget.setBackground(backgroundColor);
		textWidget.setForeground(foregroundColor);
		textWidget.setSelectionBackground(selectionBackgroundColor);
		textWidget.setSelectionForeground(selectionForegroundColor);
		
		return sourceViewer;
	}

}
