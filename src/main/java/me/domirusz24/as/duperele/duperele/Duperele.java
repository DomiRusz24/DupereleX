package me.domirusz24.as.duperele.duperele;

import me.domirusz24.as.duperele.duperele.abstractItems.ItemEnum;
import me.domirusz24.as.duperele.duperele.items.thorhammer.FlyingThorHammer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public final class Duperele extends JavaPlugin implements Listener {

    public static Duperele plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.getCommand("giveDuperel").setExecutor(new WeaponCommand());
        this.getCommand("giveDuperel").setTabCompleter(new WeaponCommand());
        Bukkit.getPluginManager().registerEvents(new me.domirusz24.as.duperele.duperele.Listener(), this);
        new BukkitRunnable() {
            @Override
            public void run() {
                for (FlyingThorHammer hammer : new ArrayList<>(FlyingThorHammer.hammers)) {
                    hammer.flight();
                }
            }
        }.runTaskTimer(Duperele.plugin, 0, 1);
        for (ItemEnum i : ItemEnum.values()) {
            i.getItem().start();
        }
        Bukkit.getLogger().info("DupereleX has been enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("DupereleX has been enabled!");
    }
}
