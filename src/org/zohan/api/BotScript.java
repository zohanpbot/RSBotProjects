package org.zohan.api;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.rt6.ClientContext;
import org.zohan.api.job.JobManager;
import org.zohan.api.paint.PaintManager;

public abstract class BotScript extends PollingScript<ClientContext> implements PaintListener, MouseListener, MessageListener, MouseMotionListener{

	private final PaintManager pm = new PaintManager();
	private final JobManager jm = new JobManager();
	
	@Override
	public abstract void poll();

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void repaint(Graphics arg0) {
	}
	

	@Override
	public void messaged(MessageEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public PaintManager pm () {
		return pm;
	}
	
	public JobManager jm() {
		return jm;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
