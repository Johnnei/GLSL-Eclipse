package org.johnnei.glsl.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ColorManager {
	
	private Map<RGB, Color> colorMap;
	
	private int theme;
	
	public ColorManager(int theme) {
		this.theme = theme;
		colorMap = new HashMap<>();
	}
	
	public Color getColor(RGB[] rgb) {
		Color color = null;
		synchronized (colorMap) {
			color = colorMap.get(rgb);
			if (color == null) {
				color = new Color(Display.getCurrent(), rgb[theme]);
				colorMap.put(rgb[theme], color);
			}
		}
		
		return color;
	}
	
	public void setTheme(int theme) {
		this.theme = theme;
		dispose();
	}
	
	public void dispose() {
		synchronized (colorMap) {
			colorMap.forEach((rgb, color) -> color.dispose());
			colorMap.clear();
		}
	}

}
