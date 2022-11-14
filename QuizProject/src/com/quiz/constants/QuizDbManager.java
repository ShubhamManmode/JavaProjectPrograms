package com.quiz.constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuizDbManager {

	public static Connection createConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/quizproject?characterEncoding=utf8";
			con=DriverManager.getConnection(url, "root", "Root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
