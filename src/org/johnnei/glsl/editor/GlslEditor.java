package org.johnnei.glsl.editor;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.johnnei.glsl.preferences.PreferenceUtils;

public class GlslEditor extends TextEditor {

	public static final RGB KEYWORD_COLOR[] = {
		new RGB(149, 0, 85),
		new RGB(221, 40, 103)
	};
	
	public static final RGB QUALIFIER_COLOR[] = {
		new RGB(149, 0, 85),
		new RGB(221, 40, 103)
	};
	
	public static final RGB PREPROCESSOR_COLOR[] = {
		new RGB(149, 0, 85),
		new RGB(221, 40, 103)
	};
	
	public static final RGB TYPE_COLOR[] = {
		new RGB(149, 0, 85),
		new RGB(18, 144, 195)
	};
	
	public static final RGB FUNCTION_COLOR[] = {
		new RGB(0, 0, 0),
		new RGB(167, 236, 33)
	};
	
	public static final RGB BUILT_IN_VARIABLES_COLOR[] = {
		new RGB(0, 0, 192),
		new RGB(128, 242, 246)
	};
	
	public static final RGB COMMENTS_COLOR[] = {
		new RGB(63, 127, 95),
		new RGB(91, 98, 84)
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
				store.getString(PREFERENCE_COLOR_SELECTION_BACKGROUND), new RGB(99, 99, 99));
		Color selectionForegroundColor = PreferenceUtils.colorFromString(
				store.getString(PREFERENCE_COLOR_SELECTION_FOREGROUND), new RGB(0, 0, 0));
		
		final StyledText textWidget = sourceViewer.getTextWidget();
		textWidget.setBackground(backgroundColor);
		textWidget.setForeground(foregroundColor);
		textWidget.setSelectionBackground(selectionBackgroundColor);
		textWidget.setSelectionForeground(selectionForegroundColor);
		
		return sourceViewer;
	}

}
