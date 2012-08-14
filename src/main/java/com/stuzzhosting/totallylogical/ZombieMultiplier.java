package com.stuzzhosting.totallylogical;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class ZombieMultiplier implements Listener {
	private boolean ignoreSpawn;

	@EventHandler
	public void onCreatureSpawn( CreatureSpawnEvent event ) {
		if ( ignoreSpawn || !( event.getEntity().getWorld().getGenerator() instanceof TotallyLogicalGenerator ) || event.getEntityType() != EntityType.ZOMBIE ) {
			return;
		}

		if ( Main.r.nextDouble() < Main.ZOMBIE_GIANT_THRESHOLD ) {
			event.setCancelled( true );
			event.getEntity().getWorld().spawnEntity( event.getLocation(), EntityType.GIANT );
		}

		if ( Main.r.nextDouble() < Main.ZOMBIE_SWARM_THRESHOLD ) {
			ignoreSpawn = true;
			int count = Main.r.nextInt( Main.ZOMBIE_SWARM_MAX - Main.ZOMBIE_SWARM_MIN + 1 ) + Main.ZOMBIE_SWARM_MIN;
			for ( int i = 0; i < count; i++ ) {
				event.getEntity().getWorld().spawnEntity( event.getLocation(), EntityType.ZOMBIE );
			}
			ignoreSpawn = false;
		}
	}

}
