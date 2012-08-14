package com.stuzzhosting.totallylogical;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PeopleKillPeople implements Listener {
	@EventHandler
	public void onPlayerDeath( PlayerDeathEvent event ) {
		World world = event.getEntity().getWorld();
		if ( world.getGenerator() instanceof TotallyLogicalGenerator ) {
			if (event.getEntity().getLocation().getY() < 0) {
				// Fell out of world. Not my problem.
				return;
			}

			// Ka-boom
			world.createExplosion( event.getEntity().getLocation(), 4 );
			world.strikeLightning( event.getEntity().getLocation() );
		}
	}

}
