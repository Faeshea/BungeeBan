package fr.coco.bungeeban.sql.utils;

import fr.coco.bungeeban.BungeeBan;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by coco33910 on 02/03/2016.
 * Ban
 */
public class Ban {

    private Player player;
    private String reason;


    public Ban(Player player, String reason) {
        this.player = player;
        this.reason = reason;
        try {
            if (BungeeBan.getInstance().getDataBase().getConnection() == null)
                return;




            if(BanUtils.getInstance().isBanned(player)) return;
            PreparedStatement sql = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("INSERT INTO ban " + "(UUID, NAME, TIME, REASON) VALUES (?, ?, ?, ?) ");
            sql.setString(1, player.getUniqueId().toString());
            sql.setString(2, player.getName());

            sql.setString(3, "permanent");
            sql.setString(4, reason);
            sql.execute();
            sql.close();
            player.kickPlayer("§cVous avez été banni définitivement pour " + reason.replace("&", "§"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public String getReason() {
        return reason;
    }
}
