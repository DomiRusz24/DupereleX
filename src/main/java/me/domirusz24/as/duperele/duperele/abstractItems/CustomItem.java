package me.domirusz24.as.duperele.duperele.abstractItems;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public abstract class CustomItem {

    public static final ArrayList<CustomItem> customItems = new ArrayList<>();

    public CustomItem() {
        customItems.add(this);
    }

    public void givePlayer(Player player) {
        player.getInventory().addItem(getItem());
    }

    public void givePlayer(Player player, int slot) {
        player.getInventory().setItem(slot, getItem());
    }

    public void removePlayer(Player player) {
        player.getInventory().remove(getItem());
    }

    abstract public ItemStack getItem();

    abstract public String getLabel();

    abstract public void onClick(PlayerInteractEvent event);

    abstract public void onShift(PlayerToggleSneakEvent event);

    abstract public void onHit(EntityDamageByEntityEvent event);

    abstract public void onTakenHit(EntityDamageByEntityEvent event);

    abstract public void onTakenHit(EntityDamageEvent event);

    abstract public void start();

}
