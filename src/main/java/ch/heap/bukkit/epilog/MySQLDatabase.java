package ch.heap.bukkit.epilog;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ch.heap.bukkit.epilog.Database;
import ch.heap.bukkit.epilog.DatabaseService;

public class MySQLDatabase extends Database {

	private Connection connection;
	private MySQLService service;

	public MySQLDatabase(String host, int port, String database, String user, String password, String prefix) {
		super(host, port, database, user, password);

		this.service = new MySQLService(this, prefix);

	}

	@Override
	public String getDataSourceName() {
		return String.format("jdbc:mysql://%s:%d/%s?useSSL=false&useUnicode=true&characterEncoding=utf8", this.host, this.port, this.database);
	}

	@Override
	public void connect() {
		try {
			String dsn = this.getDataSourceName();
			this.connection = DriverManager.getConnection(dsn, this.user, this.password);
			System.out.println("Successfully conntected to MySQL database");
		} catch (SQLException e) {
			System.out.println("Unable to connect to MySQL database: " + e.getMessage());
		}
	}

	@Override
	public void disconnect() {
		try {
			if(this.connection != null) {
				this.connection.close();
				System.out.println("Connection closed");
			}
		} catch (SQLException e) {
			System.out.println("Error while closing the connection: " + e.getMessage());

		}

	}

	@Override
	public DatabaseService getService() {
		return this.service;
	}

	public void update(PreparedStatement pst) {
		try {
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			connect();
			System.err.println(e);
		}
	}

	public void update(String qry) {
		try {
			Statement st = this.connection.createStatement();
			st.executeUpdate(qry);
			st.close();
		} catch (SQLException e) {
			connect();
			System.err.println(e);
		}
	}

	public ResultSet query(PreparedStatement pst) {
		ResultSet rs = null;

		try {
			rs = pst.executeQuery();
		} catch (SQLException e) {
			connect();
			System.err.println(e);
		}
		return rs;
	}
	public boolean hasConnection(){
		try{
			return this.connection != null || this.connection.isValid(1);
		}catch(SQLException e){
			return false;
		}
	}
	public String getDatabase() {
		return database;
	}

	public Connection getConnection() {
		return connection;
	}


	public void closeRessources(ResultSet rs , PreparedStatement st){
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException e){

			}
		}
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
