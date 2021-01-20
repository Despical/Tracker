package me.despical.survivalessential.user;

import me.despical.survivalessential.tracker.TrackerTask;
import org.bukkit.entity.Player;

/**
 * @author Despical
 * <p>
 * Created at 3.01.2021
 */
public class User {

	private final Player player;
	private TrackerTask trackerTask;

	public User(Player player) {
		this.player = player;
	}

	public void setTrackerTask(TrackerTask task) {
		this.trackerTask = task;
	}

	public TrackerTask getTrackerTask() {
		return trackerTask;
	}

	public Player getPlayer() {
		return player;
	}
}