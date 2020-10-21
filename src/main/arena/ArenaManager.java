package fr.ayust.arena;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private List<Arena> arenas = new ArrayList<>();

    public void addArena(Arena arena) {
        this.arenas.add(arena);

    }

    public void joinArena(Player firstPlayer, Player secondPlayer) {

        Arena nextArena = getNextArena();

        if(nextArena != null) {
            nextArena.getPlayers().add(firstPlayer);
            nextArena.getPlayers().add(secondPlayer);

        } else {
            firstPlayer.sendMessage("Il y a pas d'arène dispo");
            secondPlayer.sendMessage("Yab");
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
