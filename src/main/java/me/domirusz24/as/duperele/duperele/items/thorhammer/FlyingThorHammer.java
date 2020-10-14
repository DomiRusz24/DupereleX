package me.domirusz24.as.duperele.duperele.items.thorhammer;

import me.domirusz24.as.duperele.duperele.abstractItems.ItemEnum;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlyingThorHammer {

    public static ItemStack getEnchantedAxe() {
        ItemStack i = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta m = i.getItemMeta();
        m.addEnchant(Enchantment.DAMAGE_ALL, 3, false);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i.setItemMeta(m);
        return i;
    }

    public static List<FlyingThorHammer> hammers = new ArrayList<>();

    private ArmorStand hammer;

    private Location location;

    private Location hitbox;

    private Vector vector;

    private Player player;

    private int lightingTick = 0;

    private boolean ground = false;

    private int hitBlocks = 0;

    public FlyingThorHammer(Player player, double speed) {
        this.vector = player.getLocation().getDirection().multiply(speed);
        this.location = player.getEyeLocation().add(player.getEyeLocation().getDirection());
        this.hitbox = location.clone();
        this.location.add(rotateVectorAroundY(location.getDirection(), -90).multiply(0.5));
        location.add(0, -1, 0);
        hammer = location.getWorld().spawn(location, ArmorStand.class);
        this.player = player;
        setUpArmorStand();
        hammers.add(this);
    }

    private void setUpArmorStand() {
        hammer.setItemInHand(getEnchantedAxe());
        hammer.setVisible(false);
        hammer.setGravity(false);
        hammer.setCollidable(false);
        hammer.setInvulnerable(true);
    }

    public void flight() {
        if (lightingTick == 0) {
            location.add(vector);
            hitbox.add(vector);
            hammer.teleport(location);
            hitbox.getWorld().spawnParticle(Particle.SPELL_INSTANT, hitbox, 3);
            if (!hitbox.getBlock().getType().equals(Material.AIR)) {
                if (hitBlocks > 5) {
                    location.subtract(vector);
                    hitbox.subtract(vector);
                    for (int i = 0; i <= 1; i+=0.05) {
                        location.add(vector.clone().multiply(0.05));
                        if (!hitbox.add(vector.clone().multiply(0.05)).getBlock().getType().equals(Material.AIR)) {
                            break;
                        }
                    }
                    location.subtract(vector.clone().multiply(0.5));
                    new StuckAxe(location, player);
                    hammer.remove();
                    hitbox.getWorld().strikeLightning(hitbox);
                    ground = true;
                    lightingTick++;
                } else {
                    hitbox.getBlock().setType(Material.AIR);
                    int xorg = location.getBlockX();
                    int yorg = location.getBlockY();
                    int zorg = location.getBlockZ();
                    double radius = 2;
                    int r = (int) radius * 4;
                    for(int x = xorg - r; x <= xorg + r; ++x) {
                        for(int y = yorg - r; y <= yorg + r; ++y) {
                            for(int z = zorg - r; z <= zorg + r; ++z) {
                                Block block = location.getWorld().getBlockAt(x, y, z);
                                if (block.getLocation().distanceSquared(location) <= radius * radius) {
                                    block.setType(Material.AIR);
                                }
                            }
                        }
                    }
                    hitBlocks++;
                }
            } else {
                for (Entity e : hitbox.getWorld().getNearbyEntities(hitbox, 0.5, 0.5, 0.5)) {
                    if (e == hammer) continue;
                    if (e == player) continue;
                    if (e instanceof LivingEntity) {
                        hammer.remove();
                        hitbox = e.getLocation().clone();
                        hitbox.getWorld().strikeLightning(hitbox);
                        ((LivingEntity) e).damage(10, player);
                        lightingTick++;
                        break;
                    }
                }
            }
            vector.setY(vector.getY() - 0.02);
        } else {
            if (lightingTick <= 20) {
                electroDamage();
                lightingTick++;
            } else {
                if (!ground) hitbox.getWorld().dropItemNaturally(hitbox, ItemEnum.ThorHammer.getItem().getItem());
                hammers.remove(this);
            }
        }
    }

    private void electroDamage() {
        Location loc = hitbox.clone();
        for (Entity e : hitbox.getWorld().getNearbyEntities(hitbox, 2, 2, 2)) {
            if (e instanceof LivingEntity) {
                ((LivingEntity) e).damage(1, player);
            }
        }
        for (int i = 0; i < 6; i++) {
            loc.getWorld().spawnParticle(Particle.CRIT_MAGIC, loc.getX() + Math.random() * 2, loc.getY() + Math.random() * 2, loc.getZ() + Math.random() * 2, 2);
        }
    }

    public ArmorStand getHammer() {
        return hammer;
    }

    public static Vector rotateVectorAroundY(Vector vector, double degrees) {
        double rad = Math.toRadians(degrees);

        double currentX = vector.getX();
        double currentZ = vector.getZ();

        double cosine = Math.cos(rad);
        double sine = Math.sin(rad);

        return new Vector((cosine * currentX - sine * currentZ), vector.getY(), (sine * currentX + cosine * currentZ));
    }
}
