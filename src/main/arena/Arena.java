package main.arena;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private boolean isStarted;
    private Location loc1;
    private Location loc2;
    private List<Player> players;
  

    public Arena(Location loc1, Location loc2) {
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.players = new ArrayList<>();
        this.isStarted = false;
    }
    public Location getFirstloc() {

        return loc1;
    }
    public Location getSecondLoc() {
        return loc2;
    }
    public List<Player> getPlayers() {
        return players;
    }


    public boolean isStarted() {
        return isStarted;
    }
}
