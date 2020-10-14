package me.domirusz24.as.duperele.duperele.items.lightsaber;

import me.domirusz24.as.duperele.duperele.Duperele;
import me.domirusz24.as.duperele.duperele.abstractItems.CustomItem;
import me.domirusz24.as.duperele.duperele.abstractItems.ItemEnum;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class LightSaber extends CustomItem {

    private static Random random = new Random();

    public static HashMap<String, Long> cooldowns = new HashMap<>();

    @Override
    public ItemStack getItem() {
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(ChatColor.DARK_AQUA + "Light saber");
        m.setLore(Collections.singletonList(ChatColor.AQUA + "" + ChatColor.ITALIC + "Let the force be with you!"));
        m.addEnchant(Enchantment.LUCK, 1, false);
        i.setItemMeta(m);
        return i;
    }

    @Override
    public String getLabel() {
        return "LightSaber";
    }

    @Override
    public void onClick(PlayerInteractEvent event) {

    }

    @Override
    public void onShift(PlayerToggleSneakEvent event) {
        if (cooldowns.containsKey(event.getPlayer().getName())) {
            if (System.currentTimeMillis() - cooldowns.get(event.getPlayer().getName()) > 3000) {
                event.getPlayer().getLocation().getWorld().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                event.getPlayer().getLocation().getWorld().spawnParticle(Particle.CRIT, event.getPlayer().getEyeLocation().add(event.getPlayer().getEyeLocation().getDirection()), 5);
                event.getPlayer().getInventory().setItemInMainHand(ItemEnum.HiddenSaber.getItem().getItem());
                cooldowns.put(event.getPlayer().getName(), System.currentTimeMillis());
            }
        } else {
            event.getPlayer().getLocation().getWorld().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
            event.getPlayer().getLocation().getWorld().spawnParticle(Particle.CRIT, event.getPlayer().getEyeLocation().add(event.getPlayer().getEyeLocation().getDirection()), 5);
            event.getPlayer().getInventory().setItemInMainHand(ItemEnum.HiddenSaber.getItem().getItem());
            cooldowns.put(event.getPlayer().getName(), System.currentTimeMillis());
        }
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        event.setDamage(15);
        new BukkitRunnable() {
            @Override
            public void run() {
                event.getEntity().setVelocity(event.getEntity().getVelocity().multiply(2));
            }
        }.runTaskLater(Duperele.plugin, 1);

    }

    @Override
    public void onTakenHit(EntityDamageByEntityEvent event) {
    }

    @Override
    public void onTakenHit(EntityDamageEvent event) {
    }

    @Override
    public void start() {

    }
}
