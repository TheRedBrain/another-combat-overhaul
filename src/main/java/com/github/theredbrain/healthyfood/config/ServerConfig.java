package com.github.theredbrain.healthyfood.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.LinkedHashMap;

@Config(
		name = "server"
)
public class ServerConfig implements ConfigData {
	@Comment("""
			Eating a food item applies an item cooldown of this many ticks (20 ticks per second) to that item.
			This prevents the "You are full" message to immediately pop up.
			""")
	public int item_cooldown_after_eating = 20;
	@Comment("""
			Food items in this map grant the corresponding fullness when eaten.
			Food items not present here grant the default of 1 fullness.
			Food items with fullness of 0 can always be eaten.
			""")
	public LinkedHashMap<String, Integer> food_fullness = new LinkedHashMap<>() {{
		put("minecraft:cooked_porkchop", 2);
		put("bonfires:estus_flask", 0);
		put("minecraft:cookie", 0);
	}};

	public ServerConfig() {

	}
}
