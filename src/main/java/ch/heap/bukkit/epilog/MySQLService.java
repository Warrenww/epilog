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
			"`blockFace` varchar(255)" +
			")");

	}


		// String time,
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
		//
	@Override
	public void addRecord(JSONObject data) throws SQLException {
		PreparedStatement pst = database.getConnection().prepareStatement(
			"INSERT INTO " +
			this.table +
			"(" +
			"time" +
			"event" +
			"player" +
			"worldUUID" +
			"msg" +
			"x" +
			"y" +
			"z" +
			"pitch" +
			"yaw" +
			"health" +
			"damage" +
			"cause" +
			"projectile" +
			"entityID" +
			"entity" +
			"material" +
			"blockX" +
			"blockY" +
			"blockZ" +
			"var" +
			"enum" +
			"cmd" +
			"itemName" +
			"blockFace" +
			") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
		);
		pst.setString(1, data.getString("time"));
		pst.setString(2, data.getString("event"));
		pst.setString(3, data.getString("player"));
		pst.setString(4, data.getString("worldUUID"));
		pst.setString(5, data.getString("msg"));
		pst.setDouble(6, data.getDouble("x"));
		pst.setDouble(7, data.getDouble("y"));
		pst.setDouble(8, data.getDouble("z"));
		pst.setDouble(9, data.getDouble("pitch"));
		pst.setDouble(10, data.getDouble("yaw"));
		pst.setDouble(11, data.getDouble("health"));
		pst.setDouble(12, data.getDouble("damage"));
		pst.setString(13, data.getString("cause"));
		pst.setString(14, data.getString("projectile"));
		pst.setString(15, data.getString("entityID"));
		pst.setString(16, data.getString("entity"));
		pst.setString(17, data.getString("material"));
		pst.setInt(18, data.getInt("blockX"));
		pst.setInt(19, data.getInt("blockY"));
		pst.setInt(20, data.getInt("blockZ"));
		pst.setInt(21, data.getInt("var"));
		pst.setString(22, data.getString("enum"));
		pst.setString(23, data.getString("cmd"));
		pst.setString(24, data.getString("itemName"));
		pst.setString(25, data.getString("blockFace"));

		pool.execute(new Runnable() {

			@Override
			public void run() {
				database.update(pst);

			}
		});

	}

}
