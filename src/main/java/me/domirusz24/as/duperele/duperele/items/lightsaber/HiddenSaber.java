package me.domirusz24.as.duperele.duperele.items.lightsaber;

import me.domirusz24.as.duperele.duperele.abstractItems.CustomItem;
import me.domirusz24.as.duperele.duperele.abstractItems.ItemEnum;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class HiddenSaber extends CustomItem {
    @Override
    public ItemStack getItem() {
        ItemStack i = new ItemStack(Material.STICK, 1);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(ChatColor.GRAY + "Light saber");
        m.setLore(Arrays.asList(ChatColor.AQUA + "" + ChatColor.ITALIC + "Let the force be with you!"));
        i.setItemMeta(m);
        return i;
    }

    @Override
    public String getLabel() {
        return "HiddenSaber";
    }

    @Override
    public void onClick(PlayerInteractEvent event) {

    }

    @Override
    public void onShift(PlayerToggleSneakEvent event) {
        if (LightSaber.cooldowns.containsKey(event.getPlayer().getName())) {
            if (System.currentTimeMillis() - LightSaber.cooldowns.get(event.getPlayer().getName()) > 3000) {
                event.getPlayer().getLocation().getWorld().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                event.getPlayer().getLocation().getWorld().spawnParticle(Particle.CRIT, event.getPlayer().getEyeLocation().add(event.getPlayer().getEyeLocation().getDirection()), 5);
                event.getPlayer().getInventory().setItemInMainHand(ItemEnum.LightSaber.getItem().getItem());
                LightSaber.cooldowns.put(event.getPlayer().getName(), System.currentTimeMillis());
            }
        } else {
            event.getPlayer().getLocation().getWorld().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
            event.getPlayer().getLocation().getWorld().spawnParticle(Particle.CRIT, event.getPlayer().getEyeLocation().add(event.getPlayer().getEyeLocation().getDirection()), 5);
            event.getPlayer().getInventory().setItemInMainHand(ItemEnum.LightSaber.getItem().getItem());
            LightSaber.cooldowns.put(event.getPlayer().getName(), System.currentTimeMillis());
        }
    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {

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
