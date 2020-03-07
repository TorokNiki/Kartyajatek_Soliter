package backEnd.services.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLite {
    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:src/main/java/backEnd/services/database/" + fileName;

        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable() {
        String url = "jdbc:sqlite:src/main/java/backEnd/services/database/score.sqlite";
        String sql = "CREATE TABLE IF NOT EXISTS winners (\n" +
                " id integer PRIMARY KEY,\n" +
                " score integer,\n" +
                " name text NOT NULL\n" +
                ");";
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        String url = "jdbc:sqlite:src/main/java/backEnd/services/database/score.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(String name, int score) {
        String sql = "INSERT INTO winners(name, score) VALUES(?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAll() {
        String sql = "SELECT * FROM winners order by score desc";

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getInt("score"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<String> selectName() {
        String sql = "SELECT name FROM winners order by score desc limit 10";
        List<String> name=new ArrayList<>();
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("name"));
                name.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return name;
    }
    public List<String> selectScore() {
        String sql = "SELECT score FROM winners order by score desc Limit 10";

        List<String> score=new ArrayList<>();
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("score"));
                score.add(rs.getString("score"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return score;
    }
    public void deleteAll() {
        String sql = "DELETE FROM winners";

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getInt("score"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
