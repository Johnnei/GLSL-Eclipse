package org.johnnei.glsl.editor;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

public class GlslDebugPartitioner extends FastPartitioner {

	public GlslDebugPartitioner(IPartitionTokenScanner scanner,
			String[] legalContentTypes) {
		super(scanner, legalContentTypes);
	}

	@Override
	public void connect(IDocument document, boolean delayInitialise) {
		super.connect(document, delayInitialise);
		printPartitions(document);
	}

	public void printPartitions(IDocument document) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("\n\nNEW DOCUMENT\n\n");

		ITypedRegion[] partitions = computePartitioning(0, document.getLength());
		for (int i = 0; i < partitions.length; i++) {
			try {
				buffer.append("Partition type: ");
				buffer.append(partitions[i].getType());
				buffer.append(", offset: ");
				buffer.append(partitions[i].getOffset());
				buffer.append(", length: ");
				buffer.append(partitions[i].getLength());
				buffer.append("\n");
				buffer.append(document.get(partitions[i].getOffset(),
						partitions[i].getLength()));
				buffer.append("\n");
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		System.out.print(buffer);
	}

}
