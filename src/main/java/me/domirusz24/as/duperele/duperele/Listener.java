package me.domirusz24.as.duperele.duperele;

import me.domirusz24.as.duperele.duperele.abstractItems.ItemEnum;
import me.domirusz24.as.duperele.duperele.items.thorhammer.FlyingThorHammer;
import me.domirusz24.as.duperele.duperele.items.thorhammer.StuckAxe;
import me.domirusz24.as.duperele.duperele.items.thorhammer.ThorHammer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getItemMeta() != null) {
            for (ItemEnum item : ItemEnum.values()) {
                if (item.getItem().getItem().getType().equals(event.getItem().getType()) && item.getItem().getItem().getItemMeta().equals(event.getItem().getItemMeta())) {
                    item.getItem().onClick(event);
                }
            }
        }
    }

    @EventHandler
    public void onShift(PlayerToggleSneakEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand() != null) {
            ItemStack i = event.getPlayer().getInventory().getItemInMainHand();
            for (ItemEnum item : ItemEnum.values()) {
                if (item.getItem().getItem().getType().equals(i.getType()) && item.getItem().getItem().getItemMeta().equals(i.getItemMeta())) {
                    item.getItem().onShift(event);
                }
            }
        }
    }

    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            if (event.getDamager().getType().equals(EntityType.PLAYER)) {
                Player p = (Player) event.getDamager();
                for (ItemEnum item : ItemEnum.values()) {
                    if (item.getItem().getItem().getType().equals(p.getInventory().getItemInMainHand().getType()) && item.getItem().getItem().getItemMeta().equals(p.getInventory().getItemInMainHand().getItemMeta())) {
                        item.getItem().onHit(event);
                    }
                }
            }
        }
            if (event.getEntity().getType().equals(EntityType.PLAYER)) {
                Player p = (Player) event.getEntity();
                for (ItemEnum item : ItemEnum.values()) {
                    if (item.getItem().getItem().getType().equals(p.getInventory().getItemInMainHand().getType()) && item.getItem().getItem().getItemMeta().equals(p.getInventory().getItemInMainHand().getItemMeta())) {
                        item.getItem().onTakenHit(event);
                    }
                }
            }
    }

    @EventHandler
    public void onHit(EntityDamageEvent event) {
        if (!event.getEntity().getType().equals(EntityType.PLAYER)) return;
        Player p = (Player) event.getEntity();
        for (ItemEnum item : ItemEnum.values()) {
            if (item.getItem().getItem().getType().equals(p.getInventory().getItemInMainHand().getType()) && item.getItem().getItem().getItemMeta().equals(p.getInventory().getItemInMainHand().getItemMeta())) {
                item.getItem().onTakenHit(event);
            }
        }
    }

    @EventHandler
    public void onArmorStand(PlayerArmorStandManipulateEvent event) {
        for (FlyingThorHammer e : FlyingThorHammer.hammers) {
            if (event.getRightClicked().equals(e.getHammer())) {
                event.setCancelled(true);
                return;
            }
        }
        for (StuckAxe e : StuckAxe.stuckAxes) {
            if (event.getRightClicked().equals(e.getArmorStand())) {
                if (event.getPlayer().equals(e.getPlayer())) {
                    e.returnItem();
                }
                event.setCancelled(true);
                return;
            }
        }
    }
}
