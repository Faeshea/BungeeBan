package fr.coco.bungeeban.sql.utils;

import fr.coco.bungeeban.BungeeBan;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by coco33910 on 22/04/2016.
 * MuteUtils
 */
public class MuteUtils {

    public int id;
    private PreparedStatement sts1;

    private static MuteUtils ourInstance = new MuteUtils();

    public static MuteUtils getInstance() {
        return ourInstance;
    }


    public void banPlayerTime(Player player, String reason, int time) {
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


        public void unMutePlayer(OfflinePlayer player) {

            try {
                sts1 = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("DELETE FROM `mute`.`mute` WHERE `mute`.`UUID` = ?");

                sts1.setString(1, player.getUniqueId().toString());

                sts1.execute();







            }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public boolean isMuted(Player player) {
        if (BungeeBan.getInstance().getDataBase().getConnection() == null) {
            return false;
        }

        try {


            sts1 = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("SELECT * FROM mute WHERE UUID = ?");


            sts1.setString(1, player.getUniqueId().toString());
            sts1.execute();
            player.sendMessage(player.getUniqueId().toString());



            ResultSet resultSet1 = sts1.getResultSet();


            while (resultSet1.next()) {
                sts1.close();
                return true;
            }
            sts1.close();
            return false;





        } catch (SQLException e) {
            e.printStackTrace();

        }

        try {
            sts1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;


    }

    public String getReasonPlayerMute(Player player) {
        String str = "";

        if (BungeeBan.getInstance().getDataBase().getConnection() == null) {
            return "";
        }

        try {
            sts1 = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("SELECT REASON FROM mute WHERE UUID = ?");

            sts1.setString(1, player.getUniqueId().toString());

            sts1.execute();
            ResultSet resultSet1 = sts1.getResultSet();


            while (resultSet1.next())
                str = resultSet1.getString(1).replace("&", "§");



        }catch (SQLException e){
            e.printStackTrace();
        }



        return str;
    }
    public MuteUtils(){

    }



}



