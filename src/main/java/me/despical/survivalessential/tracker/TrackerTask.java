package me.despical.survivalessential.tracker;

import me.despical.survivalessential.Main;
import me.despical.survivalessential.utils.Utils;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * @author Despical
 * <p>
 * Created at 3.01.2021
 */
public class TrackerTask extends BukkitRunnable {

	private final Main plugin = JavaPlugin.getPlugin(Main.class);
	private final Player player;
	private final EntityType entityType;
	private final TrackerEntityType trackerEntityType;
	private Entity trackedEntity;
	private Entity entity;
	private static final DecimalFormat decimalFormat;

	static {
		decimalFormat = new DecimalFormat("0.0");
		DecimalFormatSymbols formatSymbols = decimalFormat.getDecimalFormatSymbols();
		formatSymbols.setDecimalSeparator('.');
		decimalFormat.setDecimalFormatSymbols(formatSymbols);
	}

	public TrackerTask(Player player, EntityType entityType, TrackerEntityType trackerEntityType) {
		this.player = player;
		this.entityType = entityType;
		this.trackerEntityType = trackerEntityType;
	}

	@Override
	public void run() {
		Entity entity = findNearestTrackedEntity();

		if (entity == null) {
			player.sendActionBar("Couldn't find tracked entity, please move around to help us.");
			return;
		}

		if (((LivingEntity) entity).isInvisible()) {
			player.sendActionBar("Tracked entity has gone invisible so you are no longer tracking them!");
			return;
		}

		double distance = player.getLocation().distance(entity.getLocation());
		player.setCompassTarget(entity.getLocation());
		player.sendActionBar("Tracked Entity: " + entity.getName() + " - " + decimalFormat.format(distance) + " " + Utils.getCardinalDirection(player) + " meters ahead.");
	}

	public void start() {
		runTaskTimer(plugin, 5L, 5L);
	}

	@Override
	public synchronized void cancel() throws IllegalStateException {
		if (entity != null) {
			entity.setGlowing(false);
		}

		super.cancel();
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity() {
		return entity;
	}

	public Player getPlayer() {
		return player;
	}

	public EntityType getEntityType() {
		return entityType;
	}

	public TrackerEntityType getTrackerEntityType() {
		return trackerEntityType;
	}

	public Entity getTrackedEntity() {
		return trackedEntity;
	}

	private Entity findNearestTrackedEntity() {
		if (entity != null) {
			return entity;
		}

		for (Entity entity : player.getNearbyEntities(64, 64, 64)) {
			if (entity.getType() == entityType) {
				trackedEntity = entity;
				return entity;
			}
		}

		return null;
	}

	public enum TrackerEntityType {
		MOB("Mob"), MONSTER("Monster"), PLAYER("Player");

		String name;

		TrackerEntityType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
}