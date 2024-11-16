package com.akabex86.commands;

import com.akabex86.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_World implements CommandExecutor {

    public Command_World(Main main){

    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player)sender;
        if(args.length == 1){
            //commands: /world info /world list
            if(args[0].equalsIgnoreCase("info")){
                //Shows info of current world
                World w = p.getWorld();
                p.sendMessage("Welten infos:\n" +
                        "NAME: "+w.getName()+"\n" +
                        "SEED: "+w.getSeed()+"\n" +
                        "SPAWN: "+w.getSpawnLocation()+"\n" +
                        "ENVIRONMENT: "+w.getEnvironment()+"\n" +
                        "WORLD TYPE(DEPRECATED): "+w.getWorldType()+"\n" +
                        "");
                return true;
            }
            if(args[0].equalsIgnoreCase("list")){
                StringBuilder worldList = new StringBuilder();
                for(World w: Bukkit.getWorlds()){
                    worldList.append("\n").append(w.getName());
                }
                p.sendMessage("§eWelten§7:§8"+worldList.toString());
                return true;
            }
            if(args[0].equalsIgnoreCase("test")){
                //Creates a test world with the seed "0"
                //WorldHandler.createWorld("test_world", World.Environment.NORMAL, WorldType.FLAT, (long)0);
            }
            //returning true returns the usage.
            return true;
        }
        return false;
    }
}
