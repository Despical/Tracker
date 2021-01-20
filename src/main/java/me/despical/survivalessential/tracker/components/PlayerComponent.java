package me.despical.survivalessential.tracker.components;

import me.despical.commonsbox.compat.XMaterial;
import me.despical.commonsbox.item.ItemBuilder;
import me.despical.commonsbox.item.ItemUtils;
import me.despical.inventoryframework.Gui;
import me.despical.inventoryframework.GuiItem;
import me.despical.inventoryframework.pane.PaginatedPane;
import me.despical.inventoryframework.pane.StaticPane;
import me.despical.survivalessential.tracker.TrackerGui;
import me.despical.survivalessential.tracker.TrackerTask;
import me.despical.survivalessential.user.User;
import me.despical.survivalessential.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Despical
 * <p>
 * Created at 4.01.2021
 */
public class PlayerComponent implements TrackerComponent {

	private TrackerGui trackerGui;

	@Override
	public void prepare(TrackerGui trackerGui) {
		this.trackerGui = trackerGui;
	}

	@Override
	public void injectComponents(PaginatedPane pane) {
		StaticPane page = new StaticPane(9, 3);
		Gui gui = trackerGui.getGui();

		page.addItem(new GuiItem(new ItemBuilder(Material.ARROW).name("&aGo Back").lore("&7To tracker menu").build(), e -> {
			pane.setPage(0);
			gui.setTitle(Utils.color("&8Select an entity type to track"));
			gui.setRows(3);
			gui.update();
		}),8, 2);

		for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
			if (Bukkit.getOnlinePlayers().size() == 1) {
				page.addItem(new GuiItem(new ItemBuilder(Material.RED_STAINED_GLASS_PANE).name("&cOh no!").lore("&7You are alone in the server!", "&7There is nobody to track!").build()), 4, 1);
				break;
			} else {
				Player player = new ArrayList<>(Bukkit.getOnlinePlayers()).get(i);

				if (player.equals(trackerGui.getPlayer())) {
					continue;
				}

				ItemStack skull = XMaterial.PLAYER_HEAD.parseItem();
				SkullMeta meta = (SkullMeta) skull.getItemMeta();
				meta = ItemUtils.setPlayerHead(player, meta);
				meta.setDisplayName(Utils.color("&cPlayer Tracker"));
				meta.setLore(getTrackMessage(player.getName()));
				skull.setDurability((short) SkullType.PLAYER.ordinal());
				skull.setItemMeta(meta);

				page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(skull)).build(), e -> {
					if (e.getClick() == ClickType.LEFT) {
						startTrack(player);
					} else if (e.getClick() == ClickType.RIGHT) {
						User user = trackerGui.getPlugin().getUserManager().getUser(trackerGui.getPlayer());
						TrackerTask task = user.getTrackerTask();

						if (task != null) {
							player.setGlowing(!player.isGlowing());
						} else {
							startTrack(player);
						}
					}

					e.getWhoClicked().closeInventory();
				}), 1, 1);
			}
		}

		pane.addPane(1, page);
	}

	private ItemBuilder getTrackedEnchant(ItemBuilder builder) {
		User user = trackerGui.getPlugin().getUserManager().getUser(trackerGui.getPlayer());

		if (user.getTrackerTask() == null) return builder;

		if (user.getTrackerTask().getEntityType() == EntityType.PLAYER) {
			return builder.enchantment(Enchantment.ARROW_DAMAGE).flag(ItemFlag.HIDE_ENCHANTS);
		}

		return builder;
	}

	private void startTrack(Player player) {
		User user = trackerGui.getPlugin().getUserManager().getUser(trackerGui.getPlayer());
		TrackerTask task = user.getTrackerTask();

		if (task == null) {
			task = new TrackerTask(trackerGui.getPlayer(), EntityType.PLAYER, TrackerTask.TrackerEntityType.PLAYER);
			task.setEntity(player);
			player.setGlowing(true);
			task.start();
			user.setTrackerTask(task);
		} else {
			player.setGlowing(false);
			task.cancel();
			user.setTrackerTask(null);
		}
	}

	private List<String> getTrackMessage(String name) {
		TrackerTask task = trackerGui.getPlugin().getUserManager().getUser(trackerGui.getPlayer()).getTrackerTask();

		if (task != null) {
			return Arrays.asList(Utils.color("&7RIGHT CLICK to make player " + (task.getEntity().isGlowing() ? "not" : "") + " glow."), Utils.color("&7Click to untrack &b" + name + "&7!"));
		}

		return Collections.singletonList(Utils.color("&7Click to track " + name + "!"));
	}
}