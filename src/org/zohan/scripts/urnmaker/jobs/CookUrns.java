package org.zohan.scripts.urnmaker.jobs;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;
import org.powerbot.script.rt6.GameObject;
import org.zohan.api.BotScript;
import org.zohan.api.job.Job;

public class CookUrns extends Job{
	
	int draw = 0;
	private final int id;
	private final int ovenId = 5087;
	private final Component mouldButton = ctx.widgets.component(1370, 38);
	
	public CookUrns(ClientContext arg0, BotScript bs, int id) {
		super(arg0, bs);
		this.id = id;
	}

	@Override
	public boolean activate() {
		draw = 100;
		return ctx.backpack.select().id(id).count() > 0;
	}

	@Override
	public void execute() {
		
		if (mouldButton.visible()){
			mouldButton.click();
			Condition.sleep(Random.nextInt(250, 750));
		} else {
			GameObject oven = ctx.objects.select().id(ovenId).nearest().poll();
			if (oven != null && oven.valid()) {
				if (oven.inViewport()){
					oven.interact("Fire");
					Condition.sleep(Random.nextInt(250, 750));
				} else {
					ctx.camera.turnTo(oven);
				}
			}			
		}
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Interacting with oven";
	}
	

	@Override
	public int priority() {
		return 10;
	}


}
