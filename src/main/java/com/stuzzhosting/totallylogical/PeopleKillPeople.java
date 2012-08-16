package com.stuzzhosting.totallylogical;

import com.avaje.ebean.EbeanServer;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PeopleKillPeople implements Listener {
	private final EbeanServer database;

	public PeopleKillPeople( EbeanServer database ) {
		this.database = database;
	}

	@EventHandler
	public void onPlayerDeath( PlayerDeathEvent event ) {
		World world = event.getEntity().getWorld();
		if ( world.getGenerator() instanceof TotallyLogicalGenerator ) {
			int score = event.getEntity().getTotalExperience();
			ScoreHolder scoreHolder = database.find( ScoreHolder.class ).where().ieq( "player", event.getEntity().getName() ).findUnique();
			if ( scoreHolder == null ) {
				scoreHolder = new ScoreHolder();
				scoreHolder.Player = event.getEntity().getName();
			}
			scoreHolder.addScore( score );
			database.save( scoreHolder );

			event.setDeathMessage( event.getDeathMessage() + " (Score: " + ChatColor.BOLD + score + ChatColor.RESET + " / " + scoreHolder.MaxScore + ")" );

			if ( event.getEntity().getLocation().getY() < 0 ) {
				// Fell out of world. Not my problem.
				return;
			}

			// Ka-boom
			world.createExplosion( event.getEntity().getLocation(), 4 );
			world.strikeLightning( event.getEntity().getLocation() );
		}
	}

}
