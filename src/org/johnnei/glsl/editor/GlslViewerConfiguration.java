package org.johnnei.glsl.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.johnnei.glsl.editor.scanners.GlslScanners;

public class GlslViewerConfiguration extends SourceViewerConfiguration {
	
	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler pr = new PresentationReconciler();
		DefaultDamagerRepairer 
		ddr = new DefaultDamagerRepairer(GlslScanners.createPreprocessorScanner());
		
		pr.setRepairer(ddr, GlslPartitionScanner.GLSL_PREPROCESSOR);
		pr.setDamager(ddr, GlslPartitionScanner.GLSL_PREPROCESSOR);
		
		ddr = new DefaultDamagerRepairer(GlslScanners.createCommentScanner());
		pr.setRepairer(ddr, GlslPartitionScanner.GLSL_COMMENT);
		pr.setDamager(ddr, GlslPartitionScanner.GLSL_COMMENT);
		
		ddr = new DefaultDamagerRepairer(new GlslScanner());
		pr.setRepairer(ddr, IDocument.DEFAULT_CONTENT_TYPE);
		pr.setDamager(ddr, IDocument.DEFAULT_CONTENT_TYPE);
		
		return pr;
	}
	
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return GlslPartitionScanner.CONTENT_TYPES;
	}
	
}
