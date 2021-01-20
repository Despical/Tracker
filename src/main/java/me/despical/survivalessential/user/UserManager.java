package me.despical.survivalessential.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Despical
 * <p>
 * Created at 3.01.2021
 */
public class UserManager {

	private final List<User> userList = new ArrayList<>();

	public UserManager() {
		Bukkit.getOnlinePlayers().forEach(this::getUser);
	}

	@NotNull
	public User getUser(Player player) {
		for (User user : userList) {
			if (user.getPlayer().equals(player)) {
				return user;
			}
		}

		User user = new User(player);
		userList.add(user);
		return user;
	}

	public List<User> getUsers() {
		return userList;
	}

	public void removeUser(User user) {
		userList.remove(user);
	}
}