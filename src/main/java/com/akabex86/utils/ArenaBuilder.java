package com.akabex86.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ArenaBuilder {

    //needed Parameters for a working arena
    private String ID;
    private String mapName;
    private String mapAuthor;
    private Location spawn; //used for edit mode only!
    private Location spawn1;
    private Location spawn2;
    private Location deathmatchSpawn1;
    private Location deathmatchSpawn2;
    private List<String> crates;

    private ArenaBuilder(String ID, String mapName, String mapAuthor, Location spawn , Location spawn1, Location spawn2, Location deathmatchSpawn1, Location deathmatchSpawn2, List<String> crates){
        this.ID = ID;
        this.mapName = mapName;
        this.mapAuthor = mapAuthor;
        this.spawn = spawn;
        this.spawn1 = spawn1;
        this.spawn2 = spawn2;
        this.deathmatchSpawn1 = deathmatchSpawn1;
        this.deathmatchSpawn2 = deathmatchSpawn2;
        this.crates = crates;
    }

    public String getID() {
        return ID;
    }
    public String getMapName(){
        return mapName;
    }
    public String getMapAuthor(){
        return mapAuthor;
    }
    public Location getSpawn(){
        return spawn;
    }

    public Location getPlayerSpawn(int index) {
        return switch (index) {
            case 1 -> spawn1;
            case 2 -> spawn2;
            default -> null;
        };
    }
    public Location getDeathmatchSpawn(int index) {
        return switch (index) {
            case 1 -> deathmatchSpawn1;
            case 2 -> deathmatchSpawn2;
            default -> null;
        };
    }
    public List<String> getCrates(){
        return crates;
    }
    //Only creates the arena file. It doesn't load it.
    public static void createArena(String ID, String mapName, String mapAuthor, Location spawn, Location spawn1, Location spawn2, Location deathmatchSpawn1, Location deathmatchSpawn2, List<String> crates){
        Map<String, Object> configData = Map.of(
                "MapName", mapName,
                "MapAuthor", mapAuthor,
                "Spawn", spawn,
                "PlayerSpawn1", spawn1,
                "PlayerSpawn2", spawn2,
                "DeathmatchSpawn1", deathmatchSpawn1,
                "DeathmatchSpawn2", deathmatchSpawn2,
                "Crates", crates
        );
        Config.write("maps"+ File.separator+ID+".yml", configData);
    }
    //Loads the arena from the filesystem.
    public static ArenaBuilder loadArena(String ID){
        Map<String, Object> configData = Config.read("maps"+File.separator+ID+".yml");

        //Extract all necessary values to build an arena
        assert configData != null; //Only for debugging purposes!
        String mapName = (String)configData.get("MapName");
        String mapAuthor = (String)configData.get("MapAuthor");
        Location Spawn = (Location)configData.get("Spawn");
        Location PlayerSpawn1 = (Location)configData.get("PlayerSpawn1");
        Location PlayerSpawn2 = (Location)configData.get("PlayerSpawn2");
        Location DeathmatchSpawn1 = (Location)configData.get("DeathmatchSpawn1");
        Location DeathmatchSpawn2 = (Location)configData.get("DeathmatchSpawn2");
        List<String> Crates = (List<String>) configData.get("Crates"); //TODO build a safe converter in utils (Something like "toStringList")

        return new ArenaBuilder(
                ID,
                mapName,
                mapAuthor,
                Spawn,
                PlayerSpawn1,
                PlayerSpawn2,
                DeathmatchSpawn1,
                DeathmatchSpawn2,
                Crates

        );
    }
    public static ArenaBuilder getArenaFromPlayer(Player p){
        return ArenaTracker.allocatedArenas.get(p);
    }
    public static List<String> listArenas(){
        return Config.list("maps"+File.separator);
    }
}
