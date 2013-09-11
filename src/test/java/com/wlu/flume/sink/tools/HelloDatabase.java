package com.wlu.flume.sink.tools;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;

public class HelloDatabase {
	public static void main(String[] args) throws Exception {
		// register the driver
		String sDriverName = "org.sqlite.JDBC";
		Class.forName(sDriverName);

		// now we set up a set of fairly basic string variables to use in the
		// body of the code proper
		String sTempDb = "contact.db";
		String sJdbc = "jdbc:sqlite";
		String sDbUrl = sJdbc + ":" + sTempDb;
		// which will produce a legitimate Url for SqlLite JDBC :
		// jdbc:sqlite:hello.db
		int iTimeout = 30;
		String sMakeTable = "create table contact (CONTACT_ID integer, CONTACT_NAME TEXT, CONTACT_PHONE TEXT, CONTACT_EMAIL TEXT);";
		String sMakeInsert = "INSERT INTO contact VALUES(1,'JACK', '12345678', 'JACK@GMAIL.COM')";
		String sMakeSelect = "SELECT CONTACT_NAME from CONTACT";

		// create a database connection
		Connection conn = DriverManager.getConnection(sDbUrl);
		try {
			Statement stmt = conn.createStatement();
			try {
				stmt.setQueryTimeout(iTimeout);
				stmt.executeUpdate(sMakeTable);
				//stmt.executeUpdate(sMakeInsert);
				ResultSet rs = stmt.executeQuery(sMakeSelect);
				try {
					while (rs.next()) {
						String sResult = rs.getString("CONTACT_NAME");
						System.out.println(sResult);
					}
				} finally {
					try {
						rs.close();
					} catch (Exception ignore) {
					}
				}
			} finally {
				try {
					stmt.close();
				} catch (Exception ignore) {
				}
			}
		} finally {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}

}