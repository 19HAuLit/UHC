package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.UHC;
import org.bukkit.entity.Player;

import java.sql.*;

public class mysql {
    private final UHC plugin;

    public mysql(UHC plugin) {
        this.plugin = plugin;
    }

    public void start() throws SQLException {
        String database = plugin.getConfig().getString("db.database");
        // CREATE CONNECTION
        Connection connection = createConnection();
        // CREATE TABLES
        Statement statement = connection.createStatement();
        statement.execute("create table IF NOT EXISTS players(uuid text null, name text null, timePlayed bigint null, playerRank text null)");
        // CHECK IF COLUMNs EXIST AND RESET TYPE
        // UUID
        try {
            statement.execute("alter table players add uuid text null");
            plugin.getLogger().info("uuid has been created in " + database + ".players");
        } catch (SQLException sqlException) {
            plugin.getLogger().info("uuid exists in " + database + ".players");
            statement.execute("alter table players modify uuid text null ");
        }
        // NAME
        try {
            statement.execute("alter table players add name text null");
            plugin.getLogger().info("name has been created in " + database + ".players");
        } catch (SQLException sqlException) {
            plugin.getLogger().info("name exists in " + database + ".players");
            statement.execute("alter table players modify name text null ");
        }
        // TIME PLAYED
        try {
            statement.execute("alter table players add timePlayed bigint null");
            plugin.getLogger().info("timePlayed has been created in " + database + ".players");
        } catch (SQLException sqlException) {
            plugin.getLogger().info("timePlayed exists in " + database + ".players");
            statement.execute("alter table players modify timePlayed bigint null");
        }
        // RANK
        try {
            statement.execute("alter table players add playerRank text null");
            plugin.getLogger().info("playerRank has been created in " + database + ".players");
        } catch (SQLException sqlException) {
            plugin.getLogger().info("playerRank exists in " + database + ".players");
            statement.execute("alter table players modify playerRank text null");
        }
        // CLOSE
        statement.close();
        connection.close();
    }

    public Connection createConnection() throws SQLException {
        String ip = plugin.getConfig().getString("db.ip");
        String port = plugin.getConfig().getString("db.port");
        String database = plugin.getConfig().getString("db.database");
        String user = plugin.getConfig().getString("db.user");
        String password = plugin.getConfig().getString("db.password");
        return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + database + "?characterEncoding=utf8", user, password);
    }

    public void registerPlayer(Player player) throws SQLException {
        Connection connection = createConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from players where uuid='" + player.getUniqueId() + "'");
        if (!resultSet.next())
            statement.executeUpdate("insert into players (uuid, name, timePlayed) VALUES ('" + player.getUniqueId() + "', '" + player.getName() + "', 0, \"Player\")");
        else
            statement.executeUpdate("update players SET name='" + player.getName() + "' where uuid='" + player.getUniqueId() + "'");
        resultSet.close();
        statement.close();
        connection.close();
    }

    public void updateTimePlayed(Player player) throws SQLException {
        Connection connection = createConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from players where uuid='" + player.getUniqueId() + "'");
        resultSet.next();
        int timePlayed = resultSet.getInt("timePlayed") + 1;
        statement.executeUpdate("update players SET timePlayed=" + timePlayed + " where uuid='" + player.getUniqueId() + "'");
        resultSet.close();
        statement.close();
        connection.close();
    }
}
