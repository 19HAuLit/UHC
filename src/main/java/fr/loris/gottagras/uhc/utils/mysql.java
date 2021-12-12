package fr.loris.gottagras.uhc.utils;

import fr.loris.gottagras.uhc.UHC;

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
        statement.execute("create table IF NOT EXISTS players(uuid varchar(20) null, name varchar(16) null, timePlayed bigint null)");
        // CHECK IF COLUMNs EXIST AND RESET TYPE
            // UUID
        try{
            statement.execute("alter table players add uuid varchar(20) null");
            plugin.getLogger().info("uuid has been created in "+database+".players");
        }
        catch (SQLException sqlException){
            plugin.getLogger().info("uuid exists in "+database+".players");
            statement.execute("alter table players modify uuid varchar(20) null ");
        }
            // NAME
        try{
            statement.execute("alter table players add name varchar(16) null");
            plugin.getLogger().info("name has been created in "+database+".players");
        }
        catch (SQLException sqlException){
            plugin.getLogger().info("name exists in "+database+".players");
            statement.execute("alter table players modify name varchar(16) null ");
        }
            // TIME PLAYED
        try{
            statement.execute("alter table players add timePlayed bigint null");
            plugin.getLogger().info("timePlayed has been created in "+database+".players");
        }
        catch (SQLException sqlException){
            plugin.getLogger().info("timePlayed exists in "+database+".players");
            statement.execute("alter table players modify timePlayed bigint null");
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
}
