package com.stuzzhosting.totallylogical;

import java.util.Random;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	static final Random r = new Random();

	static double POWERED_CREEPER_THRESHOLD, CREEPER_BOMB_THRESHOLD;

	static double ZOMBIE_GIANT_THRESHOLD, ZOMBIE_SWARM_THRESHOLD;

	static int ZOMBIE_SWARM_MIN, ZOMBIE_SWARM_MAX;

	@Override
	public void onEnable() {
		FileConfiguration config = getConfig();
		config.options().copyDefaults( true );

		config.addDefault( "creeper-powered-chance", 0.1 );
		config.addDefault( "creeper-bomb-chance", 0.65 );

		config.addDefault( "zombie-giant-chance", 0.15 );
		config.addDefault( "zombie-swarm-chance", 0.5 );
		config.addDefault( "zombie-swarm-min", 2 );
		config.addDefault( "zombie-swarm-max", 5 );

		saveConfig();

		POWERED_CREEPER_THRESHOLD = config.getDouble( "creeper-powered-chance" );
		CREEPER_BOMB_THRESHOLD = config.getDouble( "creeper-bomb-chance" );

		ZOMBIE_GIANT_THRESHOLD = config.getDouble( "zombie-giant-chance" );
		ZOMBIE_SWARM_THRESHOLD = config.getDouble( "zombie-swarm-chance" );
		ZOMBIE_SWARM_MIN = config.getInt( "zombie-swarm-min" );
		ZOMBIE_SWARM_MAX = config.getInt( "zombie-swarm-max" );

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents( new CreeperBooster(), this );
		pm.registerEvents( new ZombieMultiplier(), this );
		pm.registerEvents( new PeopleKillPeople(), this );

		getServer().getScheduler().scheduleSyncRepeatingTask( this, new ConsolationPrize(), 40, 40 );
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator( String worldName, String id ) {
		return new TotallyLogicalGenerator();
	}

}
