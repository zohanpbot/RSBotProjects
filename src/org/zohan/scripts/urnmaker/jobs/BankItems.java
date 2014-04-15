package org.zohan.scripts.urnmaker.jobs;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.Bank;
import org.powerbot.script.rt6.ClientContext;
import org.zohan.api.BotScript;
import org.zohan.api.job.Job;
import org.zohan.scripts.urnmaker.UrnMaker;

public class BankItems extends Job{

	private final Tile bankTile = UrnMaker.BANK_TILE;
	
	public BankItems(ClientContext arg0, BotScript bs) {
		super(arg0, bs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		return bankTile.distanceTo(ctx.players.local().tile()) <= 6;
	}

	@Override
	public void execute() {
		if (ctx.bank.opened()){
			if (ctx.backpack.select().id(UrnMaker.CLAY_ID).count() < ctx.backpack.select().count()){
				if (ctx.bank.depositInventory()) {
					Condition.sleep(Random.nextInt(200, 800));
				}
			} else {
				// Anti-pattern purposes
				int r = Random.nextInt(0, 2);
				Bank.Amount ba;
				if (r == 1){
					ba = Bank.Amount.ALL;
				} else {
					ba = Bank.Amount.ALL_BUT_ONE;
				}
				if (ctx.bank.withdraw(UrnMaker.CLAY_ID, ba)){
					Condition.sleep();
				}
			}
		} else {
			if (ctx.bank.open()){
				Condition.sleep(Random.nextInt(500, 1200));
			}
		}
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Banking";
	}
	
	
}
