package fr.coco.bungeeban.sql.utils;

import fr.coco.bungeeban.BungeeBan;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by coco33910 on 02/03/2016.
 * Ban
 */
public class Ban {

    private ProxiedPlayer player;
    private String reason;


    public Ban(ProxiedPlayer player, String reason) {
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
            player.disconnect(new TextComponent("§cVous avez été banni définitivement pour " + reason));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public String getReason() {
        return reason;
    }
}
