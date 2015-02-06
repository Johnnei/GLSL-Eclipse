package org.johnnei.glsl.preferences;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class PreferenceUtils {
	
	public static final Color colorFromString(String s) {
		String[] components = s.split(",");
		int r = Integer.parseInt(components[0]);
		int g = Integer.parseInt(components[0]);
		int b = Integer.parseInt(components[0]);
		
		return new Color(Display.getCurrent(), new RGB(r, g, b));
	}

}
