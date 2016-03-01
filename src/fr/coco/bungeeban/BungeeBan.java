package fr.coco.bungeeban;

import fr.coco.bungeeban.commands.BanCommand;
import fr.coco.bungeeban.sql.SqlUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

/**
 * Created by coco33910 on 22/02/2016.
 * BungeeBan
 */
public class BungeeBan extends Plugin {

    private static BungeeBan ourInstance;
    private SqlUtils sqlUtils;


    @Override
    public void onEnable() {
        SqlUtils sqlUtilss = new SqlUtils("gameurseirsite.mysql.db", "gameurseirsite", "Az026cb56", "gameurseirsite", "ban");
        sqlUtils = new SqlUtils("sql.ovh.net", "gameurseirsite", "Az026cb56", "gameurseirsite", "ban");
        ourInstance = this;
        sqlUtils.connection();
        PluginManager pm = ProxyServer.getInstance().getPluginManager();
        pm.registerCommand(this, new BanCommand("ban"));
    }

    public static BungeeBan getInstance() {
        return ourInstance;
    }


    public SqlUtils getDataBase(){
        return  sqlUtils;
    }


}