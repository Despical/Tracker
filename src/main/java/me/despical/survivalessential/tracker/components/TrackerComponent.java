package me.despical.survivalessential.tracker.components;

import me.despical.inventoryframework.pane.PaginatedPane;
import me.despical.survivalessential.tracker.TrackerGui;

public interface TrackerComponent {

	void prepare(TrackerGui trackerGui);

	void injectComponents(PaginatedPane pane);
}
