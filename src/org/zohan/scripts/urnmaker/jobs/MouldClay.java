package org.zohan.scripts.urnmaker.jobs;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.zohan.api.BotScript;
import org.zohan.api.job.Job;
import org.zohan.scripts.urnmaker.UrnMaker;

public class MouldClay extends Job{
	
	public MouldClay(ClientContext arg0, BotScript bs) {
		super(arg0, bs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		return ctx.backpack.select().id(UrnMaker.CLAY_ID).count() > 1;
	}

	@Override
	public void execute() {
		GameObject wheel = ctx.objects.select().id(5092).nearest().limit(2).shuffle().poll();	
		if (wheel.valid()){
			if (wheel.inViewport()) {
				wheel.interact("Form");
				Condition.sleep(Random.nextInt(250, 1000));
			} else {
				ctx.camera.turnTo(wheel);
			}
		}
		
	}
	
	@Override
	public int priority() {
		return 20;
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Moulding Clay";
	}

}
