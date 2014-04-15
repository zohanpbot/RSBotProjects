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

public class WalkToHouse extends Job {

	private GameObject door;
	private final int doorId = 24381;
	private final Tile doorTile = UrnMaker.DOOR_TILE;
	private final int urnId;

	public WalkToHouse(ClientContext arg0, BotScript bs, int urn) {
		super(arg0, bs);
		urnId = urn;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		door = ctx.objects.select().select(new Filter<GameObject>() {

			@Override
			public boolean accept(GameObject arg0) {
				return arg0.id() == doorId && arg0.tile().equals(doorTile)
						&& arg0.inViewport();
			}

		}).poll();
		return ((ctx.backpack.select().id(UrnMaker.CLAY_ID).count() >= 2
				|| ctx.backpack.select().id(urnId).count() >= 1) && (doorTile
				.distanceTo(ctx.players.local()) > 6 || door.valid()));
	}

	@Override
	public void execute() {
		if (door.valid()) {
			door.interact("Open");
		} else {
			ctx.movement.step(doorTile.derive(Random.nextInt(1, -1), Random.nextInt(1, -1)));
		}
		Condition.sleep(Random.nextInt(750, 1200));
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Walking to house";
	}

	@Override
	public int priority() {
		return 35;
	}

}
