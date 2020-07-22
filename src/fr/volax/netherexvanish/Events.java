package fr.volax.netherexvanish;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        for(Player vanished : NetherexVanish.vanished){
            event.getPlayer().hidePlayer(vanished);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        NetherexVanish.vanished.remove(event.getPlayer());
        removePotionsEffect(event.getPlayer());
    }

    @EventHandler
    public void onInteract(PlayerMoveEvent event){
        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();
        
        if(inv.getHelmet() == null || inv.getChestplate() == null || inv.getLeggings() == null || inv.getBoots() == null) removePotionsEffect(player);
        if(inv.getHelmet().getType() != Material.NETHERITE_HELMET || inv.getChestplate().getType() != Material.NETHERITE_CHESTPLATE || inv.getLeggings().getType() != Material.NETHERITE_LEGGINGS || inv.getBoots().getType() != Material.NETHERITE_BOOTS) removePotionsEffect(player);
        if(inv.getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase("§cCasque en Netherite") && inv.getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§cPlastron en Netherite") && inv.getLeggings().getItemMeta().getDisplayName().equalsIgnoreCase("§cJambières en Netherite") && inv.getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§cBottes en Netherite")){
            if(!NetherexVanish.netherite.contains(player)){
                NetherexVanish.netherite.add(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 2000000, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200000, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200000, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 200000, 4));
            }
        }else removePotionsEffect(player);
    }


    private void removePotionsEffect(Player player){
        if(NetherexVanish.netherite.contains(player)){
            NetherexVanish.netherite.remove(player);
            player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            player.removePotionEffect(PotionEffectType.SPEED);
            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
        }
    }
}
