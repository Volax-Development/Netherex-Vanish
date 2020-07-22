package fr.volax.netherexvanish;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§cVous devez être un joueur pour executer cette commande !");
            return false;
        }
        Player player = (Player)sender;

        if(!player.isOp()){
            player.sendMessage("§cVous n'avez pas la permission d'exécuter cette commande !");
            return false;
        }

        if(args.length == 0){
            if(!NetherexVanish.vanished.contains(player)){
                for(Player players : Bukkit.getOnlinePlayers()){
                    players.hidePlayer(player);
                }
                player.sendMessage("§aVous venez de vous vanish !");
                Bukkit.broadcastMessage("§e" + player.getName() + " §eleft the game");
                NetherexVanish.vanished.add(player);
            }else{
                for(Player players : Bukkit.getOnlinePlayers()){
                    players.showPlayer(player);
                }
                player.sendMessage("§cVous venez de vous dévanish !");
                Bukkit.broadcastMessage("§e" + player.getName() + " §ejoined the game");
                NetherexVanish.vanished.remove(player);
            }
            return false;
        }else if(args.length == 1){
            if(args[0].equalsIgnoreCase("on")){
                if(NetherexVanish.vanished.contains(player)){
                    player.sendMessage("§cVous êtes déjà en vanish !");
                    return false;
                }

                for(Player players : Bukkit.getOnlinePlayers()){
                    players.hidePlayer(player);
                }
                player.sendMessage("§aVous venez de vous vanish !");
                Bukkit.broadcastMessage("§e" + player.getName() + " §eleft the game");
                NetherexVanish.vanished.add(player);
                return false;
            }else if(args[0].equalsIgnoreCase("off")){
                if(!NetherexVanish.vanished.contains(player)){
                    player.sendMessage("§cVous êtes déjà dévanish !");
                    return false;
                }

                for(Player players : Bukkit.getOnlinePlayers()){
                    players.showPlayer(player);
                }
                player.sendMessage("§aVous venez de vous dévanish !");
                Bukkit.broadcastMessage("§e" + player.getName() + " §ejoined the game");
                NetherexVanish.vanished.remove(player);
                return false;
            }else if(args[0].equalsIgnoreCase("list")){
                if(NetherexVanish.vanished.isEmpty()){
                    player.sendMessage("§cIl n'y a personne en vanish !");
                }else{
                    String vanished = String.join(",", NetherexVanish.vanished.stream().map(Player::getName).toArray(String[]::new));
                    player.sendMessage("§aListe des personnes vanish: " + vanished);
                }
                return false;
            }else{
                helpMessage(player);
                return false;
            }
        }else{
            helpMessage(player);
            return false;
        }
    }

    private void helpMessage(Player player){
        player.sendMessage("§7Commande d'aide: /vanish [<on|off|list>]");
    }
}
