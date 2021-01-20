package me.despical.survivalessential.utils;

import org.bukkit.Bukkit;
import me.despical.survivalessential.Main;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * @author Despical
 * <p>
 * Created at 3.01.2021
 */
public class ExceptionLogHandler extends Handler {

	private final Main plugin;

	public ExceptionLogHandler(Main plugin) {
		this.plugin = plugin;

		Bukkit.getLogger().addHandler(this);
	}

	@Override
	public void close() throws SecurityException {
	}

	@Override
	public void flush() {
	}

	@Override
	public void publish(LogRecord record) {
		Throwable throwable = record.getThrown();

		if (!(throwable instanceof Exception) || !throwable.getClass().getSimpleName().contains("Exception")) {
			return;
		}

		if (throwable.getStackTrace().length <= 0) {
			return;
		}

		if (throwable.getCause() != null && throwable.getCause().getStackTrace() != null) {
			if (!throwable.getCause().getStackTrace()[0].getClassName().contains("me.despical.tracker")) {
				return;
			}
		}

		if (!throwable.getStackTrace()[0].getClassName().contains("me.despical.tracker")) {
			return;
		}

		if (containsBlacklistedClass(throwable)) {
			return;
		}

		record.setThrown(null);

		Exception exception = throwable.getCause() != null ? (Exception) throwable.getCause() : (Exception) throwable;
		StringBuilder stacktrace = new StringBuilder(exception.getClass().getSimpleName());

		if (exception.getMessage() != null) {
			stacktrace.append(" (").append(exception.getMessage()).append(")");
		}

		stacktrace.append("\n");

		for (StackTraceElement str : exception.getStackTrace()) {
			stacktrace.append(str.toString()).append("\n");
		}

		plugin.getLogger().log(Level.WARNING, "[Reporter Service] <<-----------------------------[START]----------------------------->>");
		plugin.getLogger().log(Level.WARNING, stacktrace.toString());
		plugin.getLogger().log(Level.WARNING, "[Reporter Service] <<------------------------------[END]------------------------------>>");

		record.setMessage("[Tracker] We have found a bug in the code. Please contact Despical or Breakthrough with the following error given above!");
	}

	private boolean containsBlacklistedClass(Throwable throwable) {
		for (StackTraceElement element : throwable.getStackTrace()) {
			if (element.getClassName().contains("me.despical.utils.commonsbox.database.MysqlDatabase")) {
				return true;
			}
		}

		return false;
	}
}