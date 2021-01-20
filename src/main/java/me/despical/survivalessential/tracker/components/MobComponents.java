package me.despical.survivalessential.tracker.components;

import me.despical.commonsbox.item.ItemBuilder;
import me.despical.inventoryframework.Gui;
import me.despical.inventoryframework.GuiItem;
import me.despical.inventoryframework.pane.PaginatedPane;
import me.despical.inventoryframework.pane.StaticPane;
import me.despical.survivalessential.tracker.TrackerGui;
import me.despical.survivalessential.tracker.TrackerTask;
import me.despical.survivalessential.user.User;
import me.despical.survivalessential.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.Collections;
import java.util.List;

/**
 * @author Despical
 * <p>
 * Created at 3.01.2021
 */
public class MobComponents implements TrackerComponent {

	private TrackerGui trackerGui;

	@Override
	public void prepare(TrackerGui trackerGui) {
		this.trackerGui = trackerGui;
	}

	@Override
	public void injectComponents(PaginatedPane pane) {
		StaticPane page = new StaticPane(9, 6);
		Gui gui = trackerGui.getGui();

		page.addItem(new GuiItem(new ItemBuilder(Material.ARROW).name("&aGo Back").lore("&7To tracker menu").build(), e -> {
			pane.setPage(0);
			gui.setTitle(Utils.color("&8Select an entity type to track"));
			gui.setRows(3);
			gui.update();
		}),8, 5);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.COW_SPAWN_EGG).name("&cCow Tracker").lore(getTrackMessage()), EntityType.COW).build(), e -> {
			startTrack(EntityType.COW,"Cow");
			e.getWhoClicked().closeInventory();
		}),1, 1);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.SHEEP_SPAWN_EGG).name("&cSheep Tracker").lore(getTrackMessage()), EntityType.SHEEP).build(), e -> {
			startTrack(EntityType.SHEEP,"Sheep");
			e.getWhoClicked().closeInventory();
		}),2, 1);


		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.CHICKEN_SPAWN_EGG).name("&cChicken Tracker").lore(getTrackMessage()), EntityType.CHICKEN).build(), e -> {
			startTrack(EntityType.CHICKEN,"Chicken");
			e.getWhoClicked().closeInventory();
		}),3, 1);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.PIG_SPAWN_EGG).name("&cPig Tracker").lore(getTrackMessage()), EntityType.PIG).build(), e -> {
			startTrack(EntityType.PIG,"Pig");
			e.getWhoClicked().closeInventory();
		}),4, 1);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.WOLF_SPAWN_EGG).name("&cWolf Tracker").lore(getTrackMessage()), EntityType.WOLF).build(), e -> {
			startTrack(EntityType.WOLF,"Wolf");
			e.getWhoClicked().closeInventory();
		}),5, 1);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.OCELOT_SPAWN_EGG).name("&cOcelot Tracker").lore(getTrackMessage()), EntityType.OCELOT).build(), e -> {
			startTrack(EntityType.OCELOT,"Ocelot");
			e.getWhoClicked().closeInventory();
		}),6, 1);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.HORSE_SPAWN_EGG).name("&cHorse Tracker").lore(getTrackMessage()), EntityType.HORSE).build(), e -> {
			startTrack(EntityType.HORSE,"Horse");
			e.getWhoClicked().closeInventory();
		}),7, 1);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.MOOSHROOM_SPAWN_EGG).name("&cMushroom Cow Tracker").lore(getTrackMessage()), EntityType.MUSHROOM_COW).build(), e -> {
			startTrack(EntityType.MUSHROOM_COW,"Mushroom Cow");
			e.getWhoClicked().closeInventory();
		}),1, 2);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.VILLAGER_SPAWN_EGG).name("&cVillager Tracker").lore(getTrackMessage()), EntityType.VILLAGER).build(), e -> {
			startTrack(EntityType.VILLAGER,"Villager");
			e.getWhoClicked().closeInventory();
		}),2, 2);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.BEE_SPAWN_EGG).name("&cBee Tracker").lore(getTrackMessage()), EntityType.BEE).build(), e -> {
			startTrack(EntityType.BEE,"Bee");
			e.getWhoClicked().closeInventory();
		}),3, 2);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.DONKEY_SPAWN_EGG).name("&cDonkey Tracker").lore(getTrackMessage()), EntityType.DONKEY).build(), e -> {
			startTrack(EntityType.DONKEY,"Donkey");
			e.getWhoClicked().closeInventory();
		}),4, 2);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.DOLPHIN_SPAWN_EGG).name("&cDolphin Tracker").lore(getTrackMessage()), EntityType.DOLPHIN).build(), e -> {
			startTrack(EntityType.DOLPHIN,"Dolphin");
			e.getWhoClicked().closeInventory();
		}),5, 2);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.CAT_SPAWN_EGG).name("&cCat Tracker").lore(getTrackMessage()), EntityType.CAT).build(), e -> {
			startTrack(EntityType.CAT,"Cat");
			e.getWhoClicked().closeInventory();
		}),6, 2);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.SALMON_SPAWN_EGG).name("&cSALMON Tracker").lore(getTrackMessage()), EntityType.SALMON).build(), e -> {
			startTrack(EntityType.SALMON,"Salmon");
			e.getWhoClicked().closeInventory();
		}),7, 2);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.COD_SPAWN_EGG).name("&cCod Tracker").lore(getTrackMessage()), EntityType.COD).build(), e -> {
			startTrack(EntityType.COD,"Cod");
			e.getWhoClicked().closeInventory();
		}),1, 3);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.MULE_SPAWN_EGG).name("&cMule Tracker").lore(getTrackMessage()), EntityType.MULE).build(), e -> {
			startTrack(EntityType.MULE,"Mule");
			e.getWhoClicked().closeInventory();
		}),2, 3);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.LLAMA_SPAWN_EGG).name("&cLlama Tracker").lore(getTrackMessage()), EntityType.LLAMA).build(), e -> {
			startTrack(EntityType.LLAMA,"Llama");
			e.getWhoClicked().closeInventory();
		}),3, 3);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.TROPICAL_FISH_SPAWN_EGG).name("&cTropical Fish Tracker").lore(getTrackMessage()), EntityType.TROPICAL_FISH).build(), e -> {
			startTrack(EntityType.TROPICAL_FISH,"Tropical Fish");
			e.getWhoClicked().closeInventory();
		}),4, 3);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.TURTLE_SPAWN_EGG).name("&cTurtle Tracker").lore(getTrackMessage()), EntityType.TURTLE).build(), e -> {
			startTrack(EntityType.TURTLE,"Turtle");
			e.getWhoClicked().closeInventory();
		}),5, 3);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.SQUID_SPAWN_EGG).name("&cSquid Tracker").lore(getTrackMessage()), EntityType.SQUID).build(), e -> {
			startTrack(EntityType.SQUID,"Squid");
			e.getWhoClicked().closeInventory();
		}),6, 3);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.POLAR_BEAR_SPAWN_EGG).name("&cPolar Bear Tracker").lore(getTrackMessage()), EntityType.POLAR_BEAR).build(), e -> {
			startTrack(EntityType.POLAR_BEAR,"Polar Bear");
			e.getWhoClicked().closeInventory();
		}),7, 3);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.FOX_SPAWN_EGG).name("&cFox Tracker").lore(getTrackMessage()), EntityType.FOX).build(), e -> {
			startTrack(EntityType.FOX,"Fox");
			e.getWhoClicked().closeInventory();
		}),1, 4);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.PUFFERFISH_SPAWN_EGG).name("&cPufferfish Tracker").lore(getTrackMessage()), EntityType.PUFFERFISH).build(), e -> {
			startTrack(EntityType.PUFFERFISH,"Pufferfish");
			e.getWhoClicked().closeInventory();
		}),2, 4);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.WANDERING_TRADER_SPAWN_EGG).name("&cWandering Trader Tracker").lore(getTrackMessage()), EntityType.WANDERING_TRADER).build(), e -> {
			startTrack(EntityType.WANDERING_TRADER,"Wandering Trader");
			e.getWhoClicked().closeInventory();
		}),3, 4);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.BAT_SPAWN_EGG).name("&cBat Tracker").lore(getTrackMessage()), EntityType.BAT).build(), e -> {
			startTrack(EntityType.BAT,"Bat");
			e.getWhoClicked().closeInventory();
		}),4, 4);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.PARROT_SPAWN_EGG).name("&cParrot Tracker").lore(getTrackMessage()), EntityType.PARROT).build(), e -> {
			startTrack(EntityType.PARROT,"Parrot");
			e.getWhoClicked().closeInventory();
		}),5, 4);

		page.addItem(new GuiItem(getTrackedEnchant(new ItemBuilder(Material.RABBIT_SPAWN_EGG).name("&cRabbit Tracker").lore(getTrackMessage()), EntityType.RABBIT).build(), e -> {
			startTrack(EntityType.RABBIT,"Rabbit");
			e.getWhoClicked().closeInventory();
		}),6, 4);

		pane.addPane(2, page);
	}

	private ItemBuilder getTrackedEnchant(ItemBuilder builder, EntityType entityType) {
		User user = trackerGui.getPlugin().getUserManager().getUser(trackerGui.getPlayer());

		if (user.getTrackerTask() == null) return builder;

		if (user.getTrackerTask().getEntityType() == entityType) {
			return builder.enchantment(Enchantment.ARROW_DAMAGE).flag(ItemFlag.HIDE_ENCHANTS);
		}

		return builder;
	}

	private void startTrack(EntityType type, String name) {
		User user = trackerGui.getPlugin().getUserManager().getUser(trackerGui.getPlayer());
		TrackerTask task = user.getTrackerTask();

		if (task == null) {
			task = new TrackerTask(trackerGui.getPlayer(), type, TrackerTask.TrackerEntityType.MOB);
			task.start();
			user.setTrackerTask(task);
		} else {
			task.cancel();
			user.setTrackerTask(null);
		}
	}

	private List<String> getTrackMessage() {
		TrackerTask task = trackerGui.getPlugin().getUserManager().getUser(trackerGui.getPlayer()).getTrackerTask();

		if (task != null) {
			if (task.getTrackedEntity() instanceof Player) {
				return Collections.singletonList("&7Click to untrack player!");
			} else if (task.getTrackedEntity() instanceof Monster) {
				return Collections.singletonList("&7Click to untrack monster!");
			} else {
				return Collections.singletonList("&7Click to untrack mob!");
			}
		}

		return Collections.singletonList("&7Click to track mob!");
	}
}