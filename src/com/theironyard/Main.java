package com.theironyard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");


        Statement stmt = conn.createStatement();

        stmt.execute("CREATE TABLE IF NOT EXISTS players (id IDENTITY, name VARCHAR, health DOUBLE, is_alive BOOLEAN, score INT)");
        stmt.execute("INSERT INTO players VALUES (NULL, 'Bob', 7.5, true, 50)");
        stmt.execute("UPDATE players SET health = 10.0 WHERE name = 'Bob'");
        stmt.execute("DELETE FROM players WHERE name = 'Bob'");

        //THIS IS THE WRONG WAY
        //String name = "Alice";
        String name = "', 10.0, true, 50); DROP TABLE players; --";
        stmt.execute(String.format("INSERT INTO players VALUES (Null, '%s', 10.0, true, 50)", name));


        conn.close();


    }
}
