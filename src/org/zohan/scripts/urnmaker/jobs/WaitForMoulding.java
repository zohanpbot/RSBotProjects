package org.zohan.scripts.urnmaker.jobs;

import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;
import org.zohan.api.BotScript;
import org.zohan.api.job.Job;

public class WaitForMoulding extends Job {

	private final Component waiting = ctx.widgets.component(1251, 8);
	
	public WaitForMoulding(ClientContext arg0, BotScript bs) {
		super(arg0, bs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		return waiting.visible();
	}

	@Override
	public void execute() {}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Currently crafting: " + waiting.text();
	}
	

	@Override
	public int priority() {
		return 40;
	}

}
