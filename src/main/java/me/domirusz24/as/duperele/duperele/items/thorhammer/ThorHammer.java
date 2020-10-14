package me.domirusz24.as.duperele.duperele.items.thorhammer;

import me.domirusz24.as.duperele.duperele.Duperele;
import me.domirusz24.as.duperele.duperele.abstractItems.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;

public class ThorHammer extends CustomItem {

    @Override
    public ItemStack getItem() {
        ItemStack i = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(ChatColor.DARK_AQUA + "Thors hammer");
        m.setLore(Arrays.asList(ChatColor.GRAY + "" + ChatColor.ITALIC + "how?"));
        m.addEnchant(Enchantment.DAMAGE_ALL, 3, false);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i.setItemMeta(m);
        return i;
    }

    @Override
    public String getLabel() {
        return "ThorsHammer";
    }

    @Override
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            new FlyingThorHammer(event.getPlayer(), 1);
            event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
        }
    }

    @Override
    public void onShift(PlayerToggleSneakEvent event) {

    }

    @Override
    public void onHit(EntityDamageByEntityEvent event) {
        event.getEntity().getLocation().getWorld().strikeLightning(event.getEntity().getLocation());

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
