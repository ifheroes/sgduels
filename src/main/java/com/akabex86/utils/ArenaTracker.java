package com.akabex86.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArenaTracker {
    //this class tracks all running arenas and temporary player data.
    public static List<String> freeArenas = new ArrayList<>();

    public static List<Player> inGamePlaying = new ArrayList<>();
    public static List<Player> inGameDeathmatch = new ArrayList<>();
    public static List<Player> inGameDead = new ArrayList<>();

    public static HashMap<Player, ArenaBuilder> allocatedArenas = new HashMap<>();

    //public static HashMap<String, List<Player>> spectators = new HashMap<>();
    public static ArrayList<Player> editors = new ArrayList<>();
    private Player player1;
    private Player player2;

    public Player getPlayer(int index){
        return switch (index) {
            case 1 -> player1;
            case 2 -> player2;
            default -> null;
        };
    }
    public void setPlayer(int index, Player p,ArenaBuilder builder){
        switch (index) {
            case 1 -> player1 = p;
            case 2 -> player2 = p;
            default -> {
                //TODO PRINT ERROR MESSAGE
                return;
            }
        }
        ArenaTracker.allocatedArenas.put(p, builder);
    }

    public void resetPlayers() {
        player1 = null;
        player2 = null;
    }
}