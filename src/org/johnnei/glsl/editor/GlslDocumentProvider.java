package org.johnnei.glsl.editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class GlslDocumentProvider extends FileDocumentProvider {
	
	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		
		if (document == null) {
			return null;
		}
		
		IDocumentPartitioner partioner = createPartitioner();
		partioner.connect(document);
		document.setDocumentPartitioner(partioner);
		
		return document;
	}
	
	private IDocumentPartitioner createPartitioner() {
		IPartitionTokenScanner scanner = new GlslPartitionScanner();
		IDocumentPartitioner partioner = new GlslDebugPartitioner(
			scanner,
			GlslPartitionScanner.CONTENT_TYPES
		);
		
		return partioner;
	}

}
