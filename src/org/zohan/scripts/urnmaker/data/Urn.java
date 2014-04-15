package org.zohan.scripts.urnmaker.data;

public enum Urn {


	CRACKED_MINING("Cracked Mining (1)", 20379, 1, "Mining Urns", 3),
	CRACKED_FISHING("Cracked Fishing (2)", 20319, 1, "Fishing Urns", 2),
	CRACKED_COOKING("Cracked Cooking (2)", 20349, 1, "Cooking Urns", 1),
	IMPIOUS_URN("Impious Urn (2)", 20409, 1, "Prayer Urns", 4),
	CRACKED_WOODCUTTING("Cracked Woodcutting (4)", 20295, 1, "Woodcutting Urns", 6),
	CRACKED_SMELTING("Cracked Smelting (4)", 20271, 1, "Smithing Urns", 5),
	FRAGILE_COOKING("Fragile Cooking (12)", 20355, 5, "Cooking Urns", 1),
	FRAGILE_FISHING("Fragile Fishing (15)", 20325, 5, "Fishing Urns", 2),
	FRAGILE_WOODCUTTING("Fragile Woodcutting (15)", 20301, 5, "Woodcutting Urns", 6),
	FRAGILE_MINING("Fragile Mining(17)", 20385, 5, "Mining Urns", 3),
	FRAGILE_SMELTING("Fragile Smelting(17)", 20277, 5, "Smithing Urns", 5);
	
	public final String name;
	public final int id;
	public final int widgetId;
	public final String category;
	public final int subMenuId;
	
	Urn (String name, int id, int widgetId, String category, int subMenuId) {
		this.name = name;
		this.id = id;
		this.widgetId = widgetId;
		this.category = category;
		this.subMenuId = subMenuId;
	}
	
	public int id () {
		return id;
	}
	
	public int widgetId () {
		return widgetId;
	}
	
	public String category() {
		return category;
	}
	
	public int subMenuId() {
		return subMenuId;
	}
	
	@Override
	public String toString () {
		return name;
	}
}
