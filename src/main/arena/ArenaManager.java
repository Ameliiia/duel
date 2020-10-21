package main.arena;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {
    // create an object ArenaManager
    private static final ArenaManager instance = new ArenaManager();
    private static List<Arena> arenas;
    
    // make the constructor private so that this class cannot be instantiated
    private ArenaManager(){
        arenas = new ArrayList<Arena>();
    }
    
    // Get the only object available
    public static ArenaManager getInstance(){
        return instance;
    }
    
    
    public void addArena(Arena arena) {
        this.arenas.add(arena);

    }

    public void joinArena(Player firstPlayer, Player secondPlayer) {

        Arena nextArena = getNextArena();

        if(nextArena != null) {
            nextArena.getPlayers().add(firstPlayer);
            nextArena.getPlayers().add(secondPlayer);

        } else {
            firstPlayer.sendMessage("Il n'y a pas d'arène disponible");
            secondPlayer.sendMessage("Il n'y a pas d'arène disponible");
        }
    }

    private Arena getNextArena() {

        for(Arena arena : arenas) {
            if(!arena.isStarted()); {
                return arena;
            }
        }
        return null;
    }
    public List<Arena> getArenas(){
        return arenas;
    }

}
