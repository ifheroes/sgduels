package com.akabex86.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    //The main config path of which all files for SGDuels are stored in.
    private static String pluginPath = "plugins" + File.separator + "sgduels" + File.separator;

    public static void write(String fileName, Map<String,Object> configData){
        File file = new File(pluginPath+fileName);
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        configData.forEach(cfg::set);
        //Try to write data
        try{
            cfg.save(file);
        } catch (IOException e) {
            System.err.println("Error: Failed to write data to '"+pluginPath+fileName+"'!");
            e.printStackTrace();
        }
    }
    public static Map<String,Object> read(String fileName){
        File file = new File(pluginPath+fileName);
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        Map<String, Object> configData = new HashMap<>();
        //Try to read data
        cfg.getKeys(true).forEach(key -> configData.put(key, cfg.get(key)));
        if(configData.isEmpty()){
            System.err.println("Error: File at '"+pluginPath+fileName+"' is empty or contains invalid data.");
            return null;
        }

        return configData;
    }
    private static List<String> list(Optional<String> pathOpt){
        String path = pathOpt.map(p -> p.endsWith(File.separator) ? p:p + File.separator).orElse("");
        File folder = new File(pluginPath+path);
        File[] files = folder.listFiles();

        if(files == null || files.length == 0){
            System.err.println("No file found in the folder '"+folder+"'.");
            return Collections.emptyList();
        }

        return Arrays.stream(files)
                .filter(file -> file.isFile() && file.getName().endsWith(".yml"))
                .map(file -> {
                    String fileNameWithoutExtension = file.getName().replace(".yml","");
                    System.err.println("Found file with the name '"+fileNameWithoutExtension+".yml' @'"+folder+"' ");
                    return fileNameWithoutExtension;
                })
                .collect(Collectors.toList());
    }
    public static List<String> list(){
        return list(Optional.empty());
    }
    public static List<String> list(String path){
        return list(Optional.of(path));
    }
}
