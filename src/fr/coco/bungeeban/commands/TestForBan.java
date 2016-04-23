package fr.coco.bungeeban.commands;

import fr.coco.bungeeban.sql.utils.BanUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by coco33910 on 02/03/2016.
 * TestForBan
 */
public class TestForBan implements CommandExecutor {







    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage(String.valueOf(BanUtils.getInstance().isBanned(player)));
        return false;
    }






}
