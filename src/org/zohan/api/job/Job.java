package org.zohan.api.job;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.zohan.api.BotScript;

public abstract class Job extends ClientAccessor {

	private final BotScript bs;
	
	public Job(ClientContext arg0, BotScript bs) {
		super(arg0);
		this.bs = bs;
	}
	
	public BotScript botScript() {
		return bs;
	}

	public int priority() {
		return 1;
	}
	
	public abstract boolean activate();
	public abstract void execute();
	public abstract String status();
}
