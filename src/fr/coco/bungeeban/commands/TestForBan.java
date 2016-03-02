package fr.coco.bungeeban.commands;

import fr.coco.bungeeban.sql.utils.BanUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by coco33910 on 02/03/2016.
 * TestForBan
 */
public class TestForBan extends Command {


    public TestForBan(String tfb) {
        super(tfb);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {


        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        player.sendMessage(String.valueOf(BanUtils.getInstance().isBanned(player)));
        player.sendMessage(BanUtils.getInstance().id + "");
    }


}
