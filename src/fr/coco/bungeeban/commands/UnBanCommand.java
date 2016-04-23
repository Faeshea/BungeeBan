package fr.coco.bungeeban.commands;

import fr.coco.bungeeban.sql.utils.BanUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by coco33910 on 22/04/2016.
 * UnBanCommand
 */
public class UnBanCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;

        if(player.hasPermission("mod.unban")){
            OfflinePlayer pl = Bukkit.getOfflinePlayer(args[0]);
            if(pl == null){
                return false;
            }else{
                BanUtils.getInstance().unBanPlayer(pl);
                player.sendMessage("§4Sytem §8» §cVous avez déban §c" + pl.getName() );
                for (Player pla : Bukkit.getOnlinePlayers()){
                    if(pla.isOp())
                        pla.sendMessage("§4[MOD] --> §c" + player.getName() + "§4 [UNBAN] §c --> " + pl.getName());
                }

            }
        }

        return false;
    }
}
