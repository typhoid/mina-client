package com.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.server.Config;

public class DatabaseManager
{
	private static Connection _connection = null;

	public static void open()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			_connection = DriverManager.getConnection("jdbc:mysql://"
					+ Config.DB_SERVER, Config.DB_USERNAME, Config.DB_PASSWORD);
			System.out.println("Database connection established");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			System.err.println("Mysql driver not found!");
		} catch (InstantiationException e)
		{
			e.printStackTrace();
			System.err.println("Mysql driver not found!");
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.err.println("Cannot connect to database server");
			_connection = null;
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
			System.err.println("Mysql driver not found!");
		} catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("Mysql driver not found!");
		}
	}

	public static void close()
	{
		if (_connection != null)
		{
			try
			{
				_connection.close();
				System.out.println("Database connection terminated");
			} catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Error when Database connection terminating");
			}
		}
	}

	/**
	 * create table
	 * 
	 * @param tableName
	 * @param columnSet
	 *            Example: createTable("ABC",
	 *            "id INT UNSIGNED NOT NULL AUTO_INCREMENT," +
	 *            "PRIMARY KEY (id)," + "name CHAR(40), category CHAR(40)");
	 */
	public static boolean createTable(String tableName, String columnSet)
	{
		open();

		if (_connection == null) 
		{
			System.out.println("Connect lost!");
			return false;
		}
		try
		{
			Statement s = _connection.createStatement();
			Statement s1 = _connection.createStatement();

			// create new tables
			// String searchTable = "SELECT table_name "
			// + " FROM information_schema.tables "
			// + "WHERE table_schema = '" + tableName + "'";
			//
			// ResultSet resultSet = s.executeQuery(searchTable);
			//
			// if (!resultSet.next())
			
			System.out.println("DROP TABLE IF EXISTS " + tableName);
			s.executeUpdate("DROP TABLE IF EXISTS " + tableName);
			s.close();
			
			String sql = "CREATE TABLE " + tableName + "(" + columnSet + ")";
			System.out.println(sql);
			s1.execute(sql);
			s1.close();

			close();

		} catch (SQLException exception)
		{
			close();
			exception.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @param tableName
	 * @param columnNameSet
	 * @param columnValueSet
	 * 
	 *            Example: insert(animal, "name, category", "'snake', 'reptile'"
	 *            );
	 */

	public static boolean insert(String tableName, String columnNameSet,
			String columnValueSet)
	{
		open();

		if (_connection == null)
		{
			System.out.println("Connect lost!");
			return false;
		}
		try
		{
			Statement s = _connection.createStatement();
			int count;

			System.out.println("INSERT INTO " + tableName + " ("
					+ columnNameSet + ")" + " VALUES(" + columnValueSet + ")");

			count = s.executeUpdate("INSERT INTO " + tableName + " ("
					+ columnNameSet + ")" + " VALUES(" + columnValueSet + ")");

			s.close();
			System.out.println(count + " rows were inserted");

			close();

		} catch (SQLException e)
		{
			close();
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
