package org.zohan.scripts.urnmaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt6.Skills;
import org.zohan.api.BotScript;
import org.zohan.api.job.Job;
import org.zohan.scripts.urnmaker.jobs.BankItems;
import org.zohan.scripts.urnmaker.jobs.ClickMouldWidget;
import org.zohan.scripts.urnmaker.jobs.CookUrns;
import org.zohan.scripts.urnmaker.jobs.MouldClay;
import org.zohan.scripts.urnmaker.jobs.WaitForMoulding;
import org.zohan.scripts.urnmaker.jobs.WalkToBank;
import org.zohan.scripts.urnmaker.jobs.WalkToHouse;
import org.zohan.scripts.urnmaker.ui.Gui;

@Script.Manifest(description = "Makes urns in East Varrock", name = "Zohan Urn Maker")
public class UrnMaker extends BotScript {

	private Gui gui = null;
	public String status = "Waiting for GUI...";

	// Constants
	public static final int CLAY_ID = 1761;
	public static final Tile BANK_TILE = new Tile(3254, 3420);
	public static final Tile DOOR_TILE = new Tile(3259, 3400);

	// Paint
	private int startExp;
	private int amountCrafted;
	private int paintX = 0;
	private int paintY = 0;
	private final MouseTrail mouseTrail = new MouseTrail();
	private static final Color MOUSE_COLOR = new Color(132, 198, 99),
			MOUSE_BORDER_COLOR = new Color(225, 200, 25),
			MOUSE_CENTER_COLOR = new Color(168, 9, 9);

	@Override
	public void start() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				gui = new Gui(UrnMaker.this);
			}

		});
		startExp = ctx.skills.experience(Skills.CRAFTING);
	}

	@Override
	public void poll() {
		if (!ready())
			return;
		Job j = jm().get();
		if (j != null) {
			status = j.status();
			j.execute();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		paintX = e.getX();
		paintY = e.getY();
	}

	@Override
	public void messaged(MessageEvent arg0) {
		String msg = arg0.text();
		if (msg.contains("from the oven."))
			amountCrafted++;
	}

	@Override
	public void repaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(new Color(5, 102, 41, 175));
		g.fillRect(paintX, paintY, 300, 150);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.PLAIN, 20));
		g.drawString("Urn Crafter", paintX + 15, paintY + 25);

		g.setFont(new Font("Tahoma", Font.PLAIN, 14));
		g.drawString("Status: " + status, paintX + 15, paintY + 45);
		g.drawString("Crafted: " + formatNumber(amountCrafted), paintX + 15,
				paintY + 60);
		g.drawString("Crafted/HR: " + formatNumber(pH(amountCrafted)),
				paintX + 15, paintY + 75);

		int exp = ctx.skills.experience(Skills.CRAFTING) - startExp;
		g.drawString("Exp: " + formatNumber(exp), paintX + 15, paintY + 90);
		g.drawString("Exp/HR: " + formatNumber(pH(exp)), paintX + 15,
				paintY + 105);

		String runTime = formatTime(getRuntime());
		g.drawString("Runtime: " + runTime, paintX + 15, paintY + 120);
		g.drawString("Developed by: Zohan", paintX + 15, paintY + 135);


		mouseTrail.add(ctx.mouse.getLocation());
		mouseTrail.draw(g);
		drawMouse(g);

	}

	private int pH(int val) {
		return (int) ((val) * 3600000D / getRuntime());
	}

	private String formatTime(long time) {
		String hms = String.format(
				"%02d:%02d:%02d",
				TimeUnit.MILLISECONDS.toHours(time),
				TimeUnit.MILLISECONDS.toMinutes(time)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
								.toHours(time)),
				TimeUnit.MILLISECONDS.toSeconds(time)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes(time)));
		return hms;
	}

	private String formatNumber(int i) {
		return NumberFormat.getIntegerInstance().format(i);
	}

	public boolean ready() {
		if (gui == null)
			return false;
		return !gui.isVisible();
	}

	private void drawMouse(Graphics g) {
		((Graphics2D) g).setRenderingHints(new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON));
		Point p = ctx.mouse.getLocation();
		Graphics2D spinG = (Graphics2D) g.create();
		Graphics2D spinGRev = (Graphics2D) g.create();
		Graphics2D spinG2 = (Graphics2D) g.create();
		spinG.setColor(MOUSE_BORDER_COLOR);
		spinGRev.setColor(MOUSE_COLOR);
		spinG.rotate(System.currentTimeMillis() % 2000d / 2000d * (360d) * 2
				* Math.PI / 180.0, p.x, p.y);
		spinGRev.rotate(System.currentTimeMillis() % 2000d / 2000d * (-360d)
				* 2 * Math.PI / 180.0, p.x, p.y);
		final int outerSize = 20;
		final int innerSize = 12;
		spinG.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));
		spinGRev.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));
		spinG.drawArc(p.x - (outerSize / 2), p.y - (outerSize / 2), outerSize,
				outerSize, 100, 75);
		spinG.drawArc(p.x - (outerSize / 2), p.y - (outerSize / 2), outerSize,
				outerSize, -100, 75);
		spinGRev.drawArc(p.x - (innerSize / 2), p.y - (innerSize / 2),
				innerSize, innerSize, 100, 75);
		spinGRev.drawArc(p.x - (innerSize / 2), p.y - (innerSize / 2),
				innerSize, innerSize, -100, 75);
		g.setColor(MOUSE_CENTER_COLOR);
		g.fillOval(p.x, p.y, 2, 2);
		spinG2.setColor(MOUSE_CENTER_COLOR);
		spinG2.rotate(System.currentTimeMillis() % 2000d / 2000d * 360d
				* Math.PI / 180.0, p.x, p.y);
		spinG2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));
		spinG2.drawLine(p.x - 5, p.y, p.x + 5, p.y);
		spinG2.drawLine(p.x, p.y - 5, p.x, p.y + 5);
	}

	private final static class MouseTrail {
		private final int SIZE = 50;
		private final double ALPHA_STEP = (255.0 / SIZE);
		private final Point[] points;
		private int index;

		public MouseTrail() {
			points = new Point[SIZE];
			index = 0;
		}

		public void add(final Point p) {
			points[index++] = p;
			index %= SIZE;
		}

		public void draw(final Graphics g) {
			((Graphics2D) g).setStroke(new BasicStroke(4));
			double alpha = 0;
			for (int i = index; i != (index == 0 ? SIZE - 1 : index - 1); i = (i + 1)
					% SIZE) {
				if (points[i] != null && points[(i + 1) % SIZE] != null) {
					Color rainbow = Color.getHSBColor((float) (alpha / 255), 1,
							1);
					g.setColor(new Color(rainbow.getRed(), rainbow.getGreen(),
							rainbow.getBlue(), (int) alpha));

					g.drawLine(points[i].x, points[i].y,
							points[(i + 1) % SIZE].x, points[(i + 1) % SIZE].y);
					alpha += ALPHA_STEP;
				}
			}
		}
	}

	public void startScript(Gui g) {
		final Job jobs[] = new Job[] { new CookUrns(ctx, this, g.urn().id()),
				new WaitForMoulding(ctx, this),
				new ClickMouldWidget(ctx, this, g.urn()),
				new MouldClay(ctx, this),
				new WalkToHouse(ctx, this, g.urn().id()),
				new WalkToBank(ctx, this, g.urn().id()),
				new BankItems(ctx, this) };
		jm().add(jobs);
	}
}
