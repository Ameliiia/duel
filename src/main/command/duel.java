package command;

import arena.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
    
public class duel implements CommandExecutor {

    private Map<Player, Player> players = new HashMap<>();
    private ArenaManager arenamanager = new ArenaManager();


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("duel") && sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {

                p.sendMessage("§6/§rduel <player>");
                p.sendMessage("§6/§rduel <accept/refuse>");
                return true;
            }
            if (args.length == 1) {
                String targetName = args[0];

                if (args[0].equalsIgnoreCase("accept")) {

                    if (players.containsKey(p)) {
                        p.sendMessage("Votre duel contre§r va commencer");
                        Player firstP = players.get(p);
                        firstP.sendMessage("Votre duel contre §1" + p.getName() + "§r va commencer");

                        p.teleport(new Location(Bukkit.getWorld("world"), 0, 63, 0));
                        firstP.teleport(new Location(Bukkit.getWorld("world"), 0, 63, 0));

                        players.remove(p);
                    }
                } else if (args[0].equalsIgnoreCase("refuse")) {

                    if (players.containsKey(p)) {
                        p.sendMessage("Vous avez refusé le duel!");
                        Player firstP = players.get(p);
                        firstP.sendMessage("Votre duel à été annulé");

                    }
                } else if (Bukkit.getPlayer(targetName) != null) {

                    Player target = Bukkit.getPlayer(targetName);

                    if (players.containsKey(target)) {
                        p.sendMessage("§4 Il semblerait qu'on vous à voler votre cible :D");
                    }
                    players.put(target, p);
                    p.sendMessage("Vous avez demander un duel à §1" + targetName);
                    target.sendMessage("Vous venez de recevoir une proposition de duel de §1" + p.getName());


                } else {
                    p.sendMessage("Le joueur §1" + targetName + "§r n'est pas connecté!");
                }
            }

        }
        return false;
    }




}
