package me.despical.survivalessential.events;

import me.despical.survivalessential.Main;
import me.despical.survivalessential.tracker.TrackerTask;
import me.despical.survivalessential.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Despical
 * <p>
 * Created at 5.01.2021
 */
public class QuitEvent implements Listener {

	private final Main plugin;

	public QuitEvent(Main plugin) {
		this.plugin = plugin;

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		User user = plugin.getUserManager().getUser(event.getPlayer());
		TrackerTask task = user.getTrackerTask();

		for (User u : plugin.getUserManager().getUsers()) {
			if (u.getTrackerTask() != null) {
				if (u.getTrackerTask().getEntity() != null && u.getTrackerTask().getEntity().equals(event.getPlayer())) {
					u.getTrackerTask().getEntity().setGlowing(false);
					u.getTrackerTask().cancel();
				}
			}
		}

		if (task != null) {
			if (task.getEntity() != null) {
				task.getEntity().setGlowing(false);
				task.cancel();
			}
		}

		plugin.getUserManager().removeUser(user);
	}
}