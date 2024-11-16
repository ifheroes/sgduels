package com.akabex86.game;

import com.akabex86.main.Main;
import com.akabex86.utils.ArenaBuilder;
import com.akabex86.utils.ArenaTracker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameLoop {
    //This class handles all instances of game loops.
    
    //Internal timers.
    private int gameTimer;

    private int startTime;
    private int ingameTime;
    private int dmTime;
    private List<Player> participants = new ArrayList<>();
    
   public GameLoop(int startTime,int ingameTime,int dmTime){
       this.startTime = startTime;
       this.ingameTime = ingameTime;
       this.dmTime = dmTime;
   }
   public void startGame(Player p1, Player p2, ArenaBuilder a){
        int initialStartTime = startTime;
        int initialIngameTime = ingameTime;
        int initialDmTime = dmTime;

       //TODO INITIAL TELEPORT, LOCK LOCATION AREA (with barriers), LOAD ARENA INV, IMPLEMENT NEW CHEST GENERATOR
       Collections.addAll(participants,p1,p2);
       //ChestGenerator.loadChests(a)

       gameTimer = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable() {//TODO replace with lambda(?)
           @Override
           public void run() {
               //Player left?
               if(!p1.isOnline() || !p2.isOnline()) {
                   Player onlinePlayer = p1.isOnline() ? p1 : p2;
                   Player offlinePlayer = p1.isOnline() ? p2 : p1;
                   endGame(onlinePlayer,offlinePlayer,a,EndReason.LEAVE);
               }
               //Player died?
               if(ArenaTracker.inGameDead.contains(p1)||ArenaTracker.inGameDead.contains(p2)){
                   Player winner = ArenaTracker.inGameDead.contains(p1) ? p2 : p1;
                   Player looser = ArenaTracker.inGameDead.contains(p1) ? p1 : p2;
                   endGame(winner,looser,a,EndReason.DEATH);
               }
               //Normal game process | Time visualizer and teleport section
                if(startTime >= 1){
                    if(startTime == initialStartTime){
                        //TODO load arena inv
                    }
                    startTime--;
                }
               endGame(p1,p2,a,EndReason.DRAW);

           }
       },20,20);//Runs every 20 ticks == 1 second.
   }
   private void endGame(Player winner,Player looser,ArenaBuilder a,EndReason reason){
       Bukkit.getScheduler().cancelTask(gameTimer);
       handleWinnerActions(winner);
       handleLooserActions(looser);
   }
   //
   private void handleWinnerActions(Player winner){

   }
   private void handleLooserActions(Player looser){

   }
   private void handleSpectatorActions(List<Player> spectators){

   }

   //Also shown to Spectators (Feature Comming soon)
   private void handlePlayerEndMessages(Player... players){

   }
   private void teleportPlayersToSpawn(){

   }
}
