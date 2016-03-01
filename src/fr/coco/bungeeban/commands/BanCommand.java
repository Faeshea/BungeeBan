package fr.coco.bungeeban.commands;

import fr.coco.bungeeban.sql.utils.BanUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;


/**
 * Created by coco33910 on 22/02/2016.
 * BanCommand
 */
public class BanCommand extends Command {

    public BanCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {


        if (commandSender instanceof ConsoleCommandSender) {


        } else if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (player.hasPermission("bb.ban")) {

                if (strings.length < 2) {
                    player.sendMessage(new TextComponent("§cError : /ban <Player> <Reason<"));
                } else {
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);
                    if (target == null) {
                        player.sendMessage(new TextComponent("§cError : This player is offline"));
                        return;
                    }
                    if (target.hasPermission("bb.noban")) {
                        player.sendMessage(new TextComponent("§cError : You can't ban this player"));
                        return;
                    }

                    if(strings[1].equalsIgnoreCase("permanent")){

                        StringBuilder str = new StringBuilder();
                               for(int i = 1; strings.length > i; i++){
                                   str.append(strings[i] + " ");

                               }


                        BanUtils.getInstance().banPlayer(target, str.toString());
                    }else{

                    }


                }


            } else {
                player.sendMessage(new TextComponent("§cError : You do not have permission to perform this command"));
            }
        }


    }
}