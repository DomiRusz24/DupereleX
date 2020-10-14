package me.domirusz24.as.duperele.duperele.items.thorhammer;

import me.domirusz24.as.duperele.duperele.abstractItems.ItemEnum;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static me.domirusz24.as.duperele.duperele.items.thorhammer.FlyingThorHammer.getEnchantedAxe;

public class StuckAxe {

    public static List<StuckAxe> stuckAxes = new ArrayList<>();

    private Player player;

    private ArmorStand armorStand;

    public StuckAxe(Location location, Player player) {
        this.player = player;
        this.armorStand = location.getWorld().spawn(location, ArmorStand.class);
        armorStand.setItemInHand(getEnchantedAxe());
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCollidable(false);
        armorStand.setInvulnerable(true);
        stuckAxes.add(this);
    }

    public Player getPlayer() {
        return player;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public void returnItem() {
        ItemEnum.ThorHammer.getItem().givePlayer(player);
        armorStand.remove();
        stuckAxes.remove(this);
    }
}
