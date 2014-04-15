package org.zohan.scripts.urnmaker.jobs;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;
import org.zohan.api.BotScript;
import org.zohan.api.job.Job;
import org.zohan.scripts.urnmaker.data.Urn;

public class ClickMouldWidget extends Job{

	private final Urn urn;
	
	public ClickMouldWidget(ClientContext arg0, BotScript bs, Urn u) {
		super(arg0, bs);
		urn = u;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		return ctx.widgets.component(1371, 51).valid();
	}

	@Override
	public void execute() {
		final Component menuSelected = ctx.widgets.component(1371, 51).component(0);
		if (menuSelected.text().equals(urn.category())){
			final Component urnIcon = ctx.widgets.component(1371, 44).component(urn.widgetId());
			if (urnIcon.textureId() == 15201) {
				ctx.widgets.component(1370, 38).click();
				Condition.sleep(Random.nextInt(250, 750));
			} else if (urnIcon.textureId() == 13989){
				ctx.widgets.component(1370, 31).click();
				Condition.sleep(Random.nextInt(500, 750));
			} else {
				urnIcon.click();
				Condition.sleep(Random.nextInt(250, 500));
			}
		} else {
			final Component subMenu = ctx.widgets.component(1371, 62).component(urn.subMenuId());
			if (subMenu.valid() && subMenu.visible()){
				subMenu.click();
				Condition.sleep(Random.nextInt(250, 500));
			} else {
				menuSelected.click();
				Condition.sleep(Random.nextInt(250, 500));
			}
		}
	}


	@Override
	public int priority() {
		return 25;
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Clickig Widgets";
	}
	
}
