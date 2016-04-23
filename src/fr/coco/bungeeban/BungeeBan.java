package fr.coco.bungeeban;

import fr.coco.bungeeban.commands.BanCommand;
import fr.coco.bungeeban.commands.TestForBan;
import fr.coco.bungeeban.commands.UnBanCommand;
import fr.coco.bungeeban.event.PlayerPreJoined;
import fr.coco.bungeeban.event.PlayerSendMessageEvent;
import fr.coco.bungeeban.sql.SqlUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by coco33910 on 22/02/2016.
 * BungeeBan
 */
public class BungeeBan extends JavaPlugin {

    private static BungeeBan ourInstance;
    private SqlUtils sqlUtils;


    @Override
    public void onEnable() {
        sqlUtils = new SqlUtils("localhost", "root", "password", "ban", "ban");
        ourInstance = this;
        sqlUtils.connection();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerPreJoined(), this);
        pm.registerEvents(new PlayerSendMessageEvent(), this);

        getCommand("ban").setExecutor(new BanCommand());
        getCommand("tfb").setExecutor(new TestForBan());
        getCommand("unban").setExecutor(new UnBanCommand());

    }

    public static BungeeBan getInstance() {
        return ourInstance;
    }


    public SqlUtils getDataBase() {
        return sqlUtils;
    }


}
