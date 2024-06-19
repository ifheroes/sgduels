package com.akabex86.main;

import com.akabex86.commands.*;
import com.akabex86.listeners._EventLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public static Main main;
    Logger logger = this.getLogger();

    @Override
    public void onEnable() {
        //TODO create a messages.yml file for editing if it doesnt exist already.
        super.onEnable();
        main = this;

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null){
            //Bukkit.getPluginManager().registerEvents(this, this);
            //TODO REFERING TO https://github.com/PlaceholderAPI/PlaceholderAPI/wiki/Hook-into-PlaceholderAPI
            getLogger().warning("PlaceholderAPI nicht gefunden! Plugin texte werden eventuell fehlerhaft dargestellt!");
        }

        loadConfig();

        loadCommands();
        loadListeners();
        logger.log(Level.INFO,"SGDUELS aktiviert!");
    }
    @Override
    public void onDisable() {
        super.onDisable();
        logger.log(Level.INFO,"SGDUELS deaktiviert!");
    }
    private void loadListeners(){
        new _EventLoader(this);
    }
    private void loadCommands(){

        //HOME COMMANDS
        CommandWorld world= new CommandWorld(this);
        getCommand("world").setExecutor(world);

    }
    private void loadConfig(){
        //TODO setup config
        //Config.createMainFolder();
        //Config.createFolder("kits");
        //Config.createFolder("userdata");
    }
}
