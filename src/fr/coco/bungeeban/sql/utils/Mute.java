package fr.coco.bungeeban.sql.utils;


import fr.coco.bungeeban.BungeeBan;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by coco33910 on 22/04/2016.
 * Mute
 */
public class Mute {

    private Player player;
    private String reason;


    public Mute(Player player, String reason) {
        this.player = player;
        this.reason = reason;
        try {
            if (BungeeBan.getInstance().getDataBase().getConnection() == null)
                return;



            if(fr.coco.bungeeban.sql.utils.MuteUtils.getInstance().isMuted(player)) return;
            PreparedStatement sql = BungeeBan.getInstance().getDataBase().getConnection().prepareStatement("INSERT INTO mute " + "(UUID, NAME, TIME, REASON) VALUES (?, ?, ?, ?) ");
            sql.setString(1, player.getUniqueId().toString());
            sql.setString(2, player.getName());

            sql.setString(3, "permanent");
            sql.setString(4, reason);
            sql.execute();
            sql.close();
            player.sendMessage("§cVous avez été muté définitivement pour " + reason.replace("&", "§"));


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
