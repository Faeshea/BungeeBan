package fr.coco.bungeeban.sql.utils;

import fr.coco.bungeeban.BungeeBan;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by coco33910 on 22/02/2016.
 * BanUtils
 */
public class BanUtils {

    private static BanUtils ourInstance = new BanUtils();

    public static BanUtils getInstance() {
        return ourInstance;
    }


    public void banPlayerTime(ProxiedPlayer player, String reason, int temps) {
        try {
            if(BungeeBan.getInstance().getDataBase().isConnected() == false){
                return;
            }

            PreparedStatement sql = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("INSERT INTO 'banTemp'" + "('UUID', 'NAME', 'TIME', 'REASON') VALUES (?, ?, ?, ?); " );
            sql.setString(1, player.getUniqueId().toString());
            sql.setString(2, player.getName());


                sql.setInt(3, temps);
            sql.setString(4, reason);
            sql.execute();
            sql.close();



        }catch (SQLException e){
            e.printStackTrace();
            BungeeBan.getInstance().getDataBase().disconnection();
        }
        BungeeBan.getInstance().getDataBase().disconnection();

    }

    public void banPlayer(ProxiedPlayer player, String reason) {
        try {
            if(BungeeBan.getInstance().getDataBase().isConnected() == false){
                return;
            }

            PreparedStatement sql = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("INSERT INTO 'ban'" + "('UUID', 'NAME', 'TIME', 'REASON') VALUES (?, ?, ?, ?); " );
            sql.setString(1, player.getUniqueId().toString());
            sql.setString(2, player.getName());

            sql.setString(3, "permanent");
            sql.setString(4, reason);
            sql.execute();
            sql.close();
            player.disconnect(new TextComponent("§cVous avez été banni définitivement pour " + reason));



        }catch (SQLException e){
            e.printStackTrace();
            BungeeBan.getInstance().getDataBase().disconnection();
        }
        BungeeBan.getInstance().getDataBase().disconnection();

    }


    public void unBanPlayer(ProxiedPlayer player) {

    }
    public boolean isBanned(ProxiedPlayer player){
        if(BungeeBan.getInstance().getDataBase().isConnected() == false){
         return false;
        }

        try {
          ArrayList<UUID> banned = new ArrayList<>();
            BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("SELECT * FROM 'ban' WHE");
        }catch (SQLException e){
            e.printStackTrace();
        }



        return  false;

    }


}
