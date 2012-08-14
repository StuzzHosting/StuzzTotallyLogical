package com.stuzzhosting.totallylogical;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

class ConsolationPrize implements Runnable {
	@Override
	public void run() {
		for ( Player player : Bukkit.getServer().getOnlinePlayers() ) {
			if ( player.getWorld().getGenerator() instanceof TotallyLogicalGenerator ) {
				player.giveExp( 1 );
			}
		}
	}

}
