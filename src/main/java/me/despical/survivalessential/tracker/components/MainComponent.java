package me.despical.survivalessential.tracker.components;

import me.despical.inventoryframework.Gui;
import me.despical.inventoryframework.pane.PaginatedPane;
import me.despical.inventoryframework.pane.StaticPane;
import me.despical.survivalessential.tracker.TrackerGui;
import me.despical.survivalessential.tracker.TrackerTask;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Despical
 * <p>
 * Created at 3.01.2021
 */
public class MainComponent implements TrackerComponent {

	private TrackerGui trackerGui;

	@Override
	public void prepare(TrackerGui trackerGui) {
		this.trackerGui = trackerGui;
	}

	@Override
	public void injectComponents(PaginatedPane pane) {
		StaticPane p = new StaticPane(9, 3);
		Gui gui = trackerGui.getGui();

		pane.addPane(0, p);
	}

	private List<String> getTrackMessage(String type, TrackerTask.TrackerEntityType trackerEntityType) {
		TrackerTask task = trackerGui.getPlugin().getUserManager().getUser(trackerGui.getPlayer()).getTrackerTask();

		if (task != null) {
			String name = task.getTrackerEntityType() != TrackerTask.TrackerEntityType.PLAYER ? trackerEntityType.getName() : task.getEntity().getName();

			if (task.getTrackerEntityType() == trackerEntityType) {
				return Arrays.asList("&7Tracked " + trackerEntityType.getName() + ": " + name, "&7Click to untrack!");
			}
		}

		return Collections.singletonList("&7Click to track " + type + "!");
	}
}