package me.despical.survivalessential;

import me.despical.survivalessential.events.QuitEvent;
import me.despical.survivalessential.tracker.TrackerGui;
import me.despical.survivalessential.tracker.TrackerTask;
import me.despical.survivalessential.user.User;
import me.despical.survivalessential.user.UserManager;
import me.despical.survivalessential.utils.ExceptionLogHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author Despical
 * <p>
 * Created at 3.01.2021
 */
public class Main extends JavaPlugin implements CommandExecutor {

	private ExceptionLogHandler exceptionLogHandler;
	private UserManager userManager;

    @Override
    public void onEnable() {
    	exceptionLogHandler = new ExceptionLogHandler(this);
    	userManager = new UserManager();
    	new QuitEvent(this);
    	saveDefaultConfig();

    	Optional.ofNullable(getCommand("tracker")).ifPresent(cmd -> cmd.setExecutor(this));
    }

	@Override
	public void onDisable() {
		Bukkit.getLogger().removeHandler(exceptionLogHandler);

		for (User user : userManager.getUsers()) {
			TrackerTask trackerTask = user.getTrackerTask();

			if (user.getTrackerTask() == null) {
				continue;
			}

			if (trackerTask.getEntity() != null) {
				trackerTask.getEntity().setGlowing(false);
			}
		}
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}

		Player player = (Player) sender;

		if (args.length == 0) {
			new TrackerGui(player).openGui();
			return true;
		}

		if (args.length == 1) {
			Player trackedEntity = Bukkit.getPlayer(args[0]);

			if (trackedEntity == null) {
				player.sendActionBar("Sorry but there is no player called " + args[0] + "!");
				return false;
			}

			if (sender.equals(trackedEntity)) {
				player.sendActionBar("Sorry but you can't track yourself!");
				return false;
			}

			startTrack(player, trackedEntity);
		}

    	return false;
	}

	private void startTrack(Player tracker, Player entity) {
		User user = userManager.getUser(tracker);
		TrackerTask task = user.getTrackerTask();

		if (task == null) {
			task = new TrackerTask(tracker, EntityType.PLAYER, TrackerTask.TrackerEntityType.PLAYER);
			task.setEntity(entity);
			entity.setGlowing(true);
			task.start();
			user.setTrackerTask(task);
		} else {
			entity.setGlowing(false);
			task.cancel();
			user.setTrackerTask(null);
		}
	}

	public UserManager getUserManager() {
		return userManager;
	}


}