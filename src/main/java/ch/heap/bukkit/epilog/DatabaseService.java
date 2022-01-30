package ch.heap.bukkit.epilog;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public abstract class DatabaseService {

	protected ExecutorService pool;

	public DatabaseService() {
		this.pool = Executors.newCachedThreadPool();
	}

	public abstract void createTable();

	public abstract void addRecord(JSONObject data) throws SQLException;

	public ExecutorService getPool() {
		return pool;
	}

}
