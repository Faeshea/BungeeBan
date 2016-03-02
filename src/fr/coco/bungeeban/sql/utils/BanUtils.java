package fr.coco.bungeeban.sql.utils;

import fr.coco.bungeeban.BungeeBan;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by coco33910 on 22/02/2016.
 * BanUtils
 */
public class BanUtils {

    public int id;
    private PreparedStatement sts1 = null;

    private static BanUtils ourInstance = new BanUtils();

    public static BanUtils getInstance() {
        return ourInstance;
    }


    public void banPlayerTime(ProxiedPlayer player, String reason, int time) {
        try {
            if (BungeeBan.getInstance().getDataBase().isConnected() == false) {
                return;
            }

            PreparedStatement sql = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("INSERT INTO 'banTemp'" + "('UUID', 'NAME', 'TIME', 'REASON') VALUES (?, ?, ?, ?); ");
            sql.setString(1, player.getUniqueId().toString());
            sql.setString(2, player.getName());


            sql.setInt(3, time);
            sql.setString(4, reason);
            sql.execute();
            sql.close();


        } catch (SQLException e) {
            e.printStackTrace();
            BungeeBan.getInstance().getDataBase().disconnection();
        }
        BungeeBan.getInstance().getDataBase().disconnection();

    }


    public void unBanPlayer(ProxiedPlayer player) {

    }

    public boolean isBanned(ProxiedPlayer player) {
        if (BungeeBan.getInstance().getDataBase().getConnection() == null) {
            return false;
        }

        try {


             sts1 = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("SELECT UUID FROM ban WHERE NAME = ? ");
            sts1.setString(1, player.getUniqueId().toString());
            sts1.execute();
            sts1.close();


            ResultSetMetaData resultSetMetaData1 = sts1.getMetaData();
            ResultSet resultSet1 = sts1.getResultSet();
            if (!sts1.getResultSet().next()) {
                return false;
            } else {
                String string = resultSet1.getString("UUID");
                if (string == null) {
                    return false;
                } else {
                    return true;
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();

        }


        return false;


    }

    public String getReasonPlayerBan() {
        return "";
    }


}
