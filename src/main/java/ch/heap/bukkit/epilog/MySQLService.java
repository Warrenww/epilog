package ch.heap.bukkit.epilog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

import ch.heap.bukkit.epilog.DatabaseService;

public class MySQLService extends DatabaseService {

	private MySQLDatabase database;

	private String table = "epilog";

	public MySQLService(MySQLDatabase database, String prefix) {
		if (prefix != null && prefix.length() > 0) {
			this.table = prefix + this.table;
		}

		this.database = database;
	}

	@Override
	public void createTable() {
		database.update(
			"CREATE TABLE IF NOT EXISTS " +
			this.table +
			" (" +
			"`time` varchar(255)," +
			"`event` varchar(255)," +
			"`player` varchar(255)," +
			"`worldUUID` varchar(255)," +
			"`msg` varchar(255)," +
			"`x` double," +
			"`y` double," +
			"`z` double," +
			"`pitch` double," +
			"`yaw` double," +
			"`health` double," +
			"`damage` double," +
			"`cause` varchar(255)," +
			"`projectile` varchar(255)," +
			"`entityID` varchar(255)," +
			"`entity` varchar(255)," +
			"`material` varchar(255)," +
			"`blockX` int," +
			"`blockY` int," +
			"`blockZ` int," +
			"`var` int," +
			"`enum` varchar(255)," +
			"`cmd` varchar(255)," +
			"`itemName` varchar(255)," +
			"`blockFace` varchar(255)," +
			"`delta` varchar(255)," +
			"`content` varchar(511)," +
			"`advancement` varchar(255)" +
			")");

	}


		// long time,
		// String event,
		// String player,
		// String worldUUID,
		// String msg,
		// double x,
		// double y,
		// double z,
		// float pitch,
		// float yaw,
		// double health,
		// double damage,
		// String cause,
		// String projectile,
		// String entityID,
		// String entity,
		// String material,
		// int blockX,
		// int blockY,
		// int blockZ,
		// int var,
		// String enum,
		// String cmd,
		// String itemName,
		// String blockFace,
		// String delta : HashMap -> JSONObject -> String
		// String content : HashMap -> JSONObject -> String
		// String advancement
	@Override
	public void addRecord(JSONObject data) throws SQLException {
		PreparedStatement pst = database.getConnection().prepareStatement(
			"INSERT INTO " +
			this.table +
			"(" +
			"time," +
			"event," +
			"player," +
			"worldUUID," +
			"msg," +
			"x," +
			"y," +
			"z," +
			"pitch," +
			"yaw," +
			"health," +
			"damage," +
			"cause," +
			"projectile," +
			"entityID," +
			"entity," +
			"material," +
			"blockX," +
			"blockY," +
			"blockZ," +
			"var," +
			"enum," +
			"cmd," +
			"itemName," +
			"blockFace," +
			"delta," +
			"content," +
			"advancement" +
			") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
		);
		pst.setString(1, Long.toString(data.getLong("time")));
		pst.setString(2, data.optString("event", null));
		pst.setString(3, data.optString("player", null));
		pst.setString(4, data.optString("worldUUID", null));
		pst.setString(5, data.optString("msg", null));
		pst.setObject(6, data.isNull("x") ? null : data.getDouble("x"));
		pst.setObject(7, data.isNull("y") ? null : data.getDouble("y"));
		pst.setObject(8, data.isNull("z") ? null : data.getDouble("z"));
		pst.setObject(9, data.isNull("pitch") ? null : data.getDouble("pitch"));
		pst.setObject(10, data.isNull("yaw") ? null : data.getDouble("yaw"));
		pst.setObject(11, data.isNull("health") ? null : data.getDouble("health"));
		pst.setObject(12, data.isNull("damage") ? null : data.getDouble("damage"));
		pst.setString(13, data.optString("cause", null));
		pst.setString(14, data.optString("projectile", null));
		pst.setString(15, data.optString("entityID", null));
		pst.setString(16, data.optString("entity", null));
		pst.setString(17, data.optString("material", null));
		pst.setObject(18, data.isNull("blockX") ? null : data.getInt("blockX"));
		pst.setObject(19, data.isNull("blockY") ? null : data.getInt("blockY"));
		pst.setObject(20, data.isNull("blockZ") ? null : data.getInt("blockZ"));
		pst.setObject(21, data.isNull("var") ? null : data.getInt("var"));
		pst.setString(22, data.optString("enum", null));
		pst.setString(23, data.optString("cmd", null));
		pst.setString(24, data.optString("itemName", null));
		pst.setString(25, data.optString("blockFace", null));
		pst.setString(26, data.isNull("delta") ? null : data.getJSONObject("delta").toString());
		pst.setString(27, data.isNull("content") ? null : data.getJSONObject("content").toString());
		pst.setString(28, data.optString("advancement"));

		pool.execute(new Runnable() {

			@Override
			public void run() {
				database.update(pst);

			}
		});

	}

}
