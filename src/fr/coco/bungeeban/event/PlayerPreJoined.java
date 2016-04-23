package fr.coco.bungeeban.event;

import fr.coco.bungeeban.sql.utils.BanUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by coco33910 on 22/04/2016.
 * PlayerPreJoined
 */
public class PlayerPreJoined implements Listener {

   @EventHandler
    public void playerPreJoined(PlayerLoginEvent e){
       Player player = e.getPlayer();
       System.out.println("DEBUG 1");
       if(BanUtils.getInstance().isBanned(player)){
           System.out.println("DEBUG 2");
          e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
           e.setKickMessage("§c"+BanUtils.getInstance().getReasonPlayerBan(player));
       }
   }


}
