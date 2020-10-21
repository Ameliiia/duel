package main.command;

import main.arena.ArenaManager;
import main.arena.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;


import java.util.HashMap;
import java.util.Map;
    
public class Duel implements CommandExecutor {
    // TODO : Liste de Players pour qu'un joueur puisse recevoir pls invitations de duel
    private Map<Player, Player> players = new HashMap<>();
    private ArenaManager arenamanager = ArenaManager.getInstance();


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO : Empêcher le joueur de s'envoyer une demande de duel à lui même
        // TODO : Ajouter condition fin duel (mort ou fin d'un timer)
        // TODO : Impossibilité d'envoyer deux requêtes au même joueur
        // TODO : Pendant un duel les joueurs ne peuvent pas faire de commande liée aux duels.

        if (label.equalsIgnoreCase("duel") && sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage("§c/!\\ Votre commande est invalide, utilisez :");
                p.sendMessage("§6/§rduel <player>");
                p.sendMessage("§6/§rduel <accept/refuse>");
                return true;
            }
            if (args.length == 1) {
                String targetName = args[0];

                if (args[0].equalsIgnoreCase("accept")) {

                    if (players.containsKey(p)) {
                        p.sendMessage("Votre duel contre §r va commencer");
                        Player firstP = players.get(p);
                        firstP.sendMessage("Votre duel contre §1" + p.getName() + "§r va commencer");

                        // TODO : Utiliser les coordonnées du .yml
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
                    // Si qqn a déjà envoyé une requête au joueur ciblé, la requête n'a pas lieu
                    if (players.containsKey(target)) {
                        p.sendMessage("§4 Le joueur ciblé a déjà une demande de duel en attente");
                    }
                    
                    else {
                        players.put(target, p);
                        p.sendMessage("Vous avez demandé un duel à §1" + targetName);
                        target.sendMessage("Vous venez de recevoir une proposition de duel de §1" + p.getName());
                    }

                } else {
                    p.sendMessage("Le joueur §1" + targetName + "§r n'est pas connecté!");
                }
            }

        }
        return false;
    }


}
