package org.zohan.api.paint;

import java.awt.Graphics;
import java.util.ArrayList;

public class PaintManager {
	
	private final ArrayList<Paintable> paintables = new ArrayList<Paintable>();
	
	public void add (Paintable p) {
		if (!paintables.contains(p)){
			paintables.add(p);
		}
	}
	
	public void draw(Graphics g) {
		for (Paintable p: paintables){
			p.draw(g);
		}
	}
	
	public ArrayList<Paintable> paintables () {
		return paintables;
	}
}
