package org.zohan.scripts.urnmaker.jobs;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.zohan.api.BotScript;
import org.zohan.api.job.Job;
import org.zohan.scripts.urnmaker.UrnMaker;

public class WalkToBank extends Job{
	
	private final int doorId = 24381;
	private final int urnId;
	private final Tile bankTile = UrnMaker.BANK_TILE;
	private final Tile doorTile = UrnMaker.DOOR_TILE;
	
	public WalkToBank(ClientContext arg0, BotScript bs, int id) {
		super(arg0, bs);
		urnId = id;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		return ((ctx.backpack.select().id(UrnMaker.CLAY_ID).count() <= 1
				&& ctx.backpack.select().id(urnId).count() == 0) && (bankTile
				.distanceTo(ctx.players.local()) > 6));
	}

	@Override
	public void execute() {
		GameObject door = ctx.objects.select().select(new Filter<GameObject>() {

			@Override
			public boolean accept(GameObject arg0) {
				return arg0.id() == doorId && arg0.tile().equals(doorTile)
						&& arg0.tile().distanceTo(ctx.players.local().tile()) < 6;
			}

		}).poll();
		
		
		if (door.valid()) {
			if (door.inViewport()){
				door.interact("Open");
			} else {
				ctx.camera.turnTo(door);
			}
		} else {
			ctx.movement.step(bankTile.derive(Random.nextInt(1, -1), Random.nextInt(1, -1)));
		}
		Condition.sleep(Random.nextInt(750, 1200));
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Walking to bank.";
	}

}
