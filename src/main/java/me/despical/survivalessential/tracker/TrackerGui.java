package me.despical.survivalessential.tracker;

import me.despical.inventoryframework.Gui;
import me.despical.inventoryframework.pane.PaginatedPane;
import me.despical.survivalessential.Main;
import me.despical.survivalessential.tracker.components.MainComponent;
import me.despical.survivalessential.tracker.components.MobComponents;
import me.despical.survivalessential.tracker.components.PlayerComponent;
import me.despical.survivalessential.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Despical
 * <p>
 * Created at 3.01.2021
 */
public class TrackerGui {

	private final Main plugin = JavaPlugin.getPlugin(Main.class);
	private Gui gui;
	private final Player player;

	public TrackerGui(Player player) {
		this.player = player;

		prepareGui();
	}

	private void prepareGui() {
		this.gui = new Gui(plugin, 3, Utils.color("&8Select an entity type to track"));
		this.gui.setOnGlobalClick(e -> e.setCancelled(true));

		PaginatedPane pane = new PaginatedPane(9, 3);
		prepareComponents(pane);
		gui.addPane(pane);
	}

	private void prepareComponents(PaginatedPane pane) {
		MainComponent mainComponent = new MainComponent();
		mainComponent.prepare(this);
		mainComponent.injectComponents(pane);

		MobComponents selectionComponent = new MobComponents();
		selectionComponent.prepare(this);
		selectionComponent.injectComponents(pane);

		PlayerComponent playerComponent = new PlayerComponent();
		playerComponent.prepare(this);
		playerComponent.injectComponents(pane);
	}

	public void openGui() {
		gui.show(player);
	}

	public Player getPlayer() {
		return player;
	}

	public Main getPlugin() {
		return plugin;
	}

	public Gui getGui() {
		return gui;
	}
}
