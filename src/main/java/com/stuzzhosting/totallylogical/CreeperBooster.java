package com.stuzzhosting.totallylogical;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

class CreeperBooster implements Listener {
	private static final Vector half = new Vector( 0.5, 0.5, 0.5 );

	@EventHandler
	public void onCreatureSpawn( CreatureSpawnEvent event ) {
		if ( event.getEntityType() != EntityType.CREEPER || !( event.getEntity().getWorld().getGenerator() instanceof TotallyLogicalGenerator ) ) {
			return;
		}

		Creeper creeper = (Creeper) event.getEntity();
		if ( Main.r.nextDouble() < Main.POWERED_CREEPER_THRESHOLD ) {
			creeper.setPowered( true );
		}
	}

	@EventHandler
	public void onEntityExplode( EntityExplodeEvent event ) {
		if ( event.getEntityType() != EntityType.CREEPER || !( event.getEntity().getWorld().getGenerator() instanceof TotallyLogicalGenerator ) ) {
			return;
		}

		Creeper creeper = (Creeper) event.getEntity();
		if ( Main.r.nextDouble() < Main.CREEPER_BOMB_THRESHOLD ) {
			// Intentionally recomputing length each time to make less TNT more common.
			for ( int i = 0; i < Main.r.nextInt( 3 ); i++ ) {
				TNTPrimed tnt = (TNTPrimed) creeper.getWorld().spawnEntity( creeper.getLocation(), EntityType.PRIMED_TNT );
				tnt.setFuseTicks( 10 + Main.r.nextInt( 10 ) );
				tnt.setVelocity( Vector.getRandom().subtract( half ) );
			}
		}
	}

}
