package com.stuzzhosting.totallylogical;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public ChunkGenerator getDefaultWorldGenerator( String worldName, String id ) {
		return new TotallyLogicalGenerator();
	}
}
