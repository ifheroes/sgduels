package com.akabex86.listeners;

import com.akabex86.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class _EventLoader implements Listener {


    public _EventLoader(Main plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler public void onCommandSend(PlayerCommandSendEvent e){
        Events_CommandSend.onCommandSend(e);
    }
}
