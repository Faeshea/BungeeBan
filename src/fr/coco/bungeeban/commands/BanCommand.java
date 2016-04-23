package fr.coco.bungeeban.commands;

import fr.coco.bungeeban.sql.utils.Ban;
import fr.coco.bungeeban.sql.utils.BanUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * Created by coco33910 on 22/02/2016.
 * BanCommand
 */
public class BanCommand implements CommandExecutor {











    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if (player.hasPermission("mod.ban")) {

                if (strings.length < 3) {
                    player.sendMessage("§cError : /ban <Player> <time in hours or permanent> <Reason>");
                } else {
                    Player target = Bukkit.getPlayer(strings[0]);
                    if (target == null) {
                        player.sendMessage("§cError : This player is offline");
                        return false;
                    }
                    if (target.hasPermission("bb.noban")) {
                        player.sendMessage("§cError : You can't ban this player");
                        return false;
                    }
                    if(BanUtils.getInstance().isBanned(player)){
                        player.sendMessage("§cError : This player is already banned");
                    }
                    else {

                        if (strings[1].equalsIgnoreCase("permanent") || strings[1].equalsIgnoreCase("perma") || strings[1].equalsIgnoreCase("perm")) {

                            StringBuilder str = new StringBuilder();
                            for (int i = 2; strings.length > i; i++) {
                                str.append(strings[i] + " ");

                            }

                            new Ban(target, str.toString());
                            for(Player pla : Bukkit.getOnlinePlayers()){
                                if(pla.isOp()){
                                    pla.sendMessage("§4[MOD] --> §c" + player.getName() + "§4 [BAN] --> Definitif §4 --> §c" + target.getName() );
                                }
                            }


                        } else {

                        }


                    }
                }


            } else {
                player.sendMessage("§cError : You do not have permission to perform this command");
            }
        }
        return false;

    }
}

