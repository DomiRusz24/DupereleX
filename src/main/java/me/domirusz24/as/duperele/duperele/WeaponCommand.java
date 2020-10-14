package me.domirusz24.as.duperele.duperele;

import me.domirusz24.as.duperele.duperele.abstractItems.CustomItem;
import me.domirusz24.as.duperele.duperele.abstractItems.ItemEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WeaponCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getLabel().equalsIgnoreCase("giveDuperel")) {
            if (args.length >= 1) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (player.hasPermission("DupereleX")) {
                        for (ItemEnum e : ItemEnum.values()) {
                            if (args[0].equalsIgnoreCase(e.getItem().getLabel())) {
                                e.getItem().givePlayer(player);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> e = new ArrayList<>();
        if (args.length == 1) {
            for (ItemEnum i : ItemEnum.values()) {
                e.add(i.getItem().getLabel());
            }
        }
        String argumentToFindCompletionFor = args[args.length - 1];
        ArrayList<String> listOfPossibleCompletions = new ArrayList<>();

        for (String foundString : e) {
            if (foundString.regionMatches(true, 0, argumentToFindCompletionFor, 0, argumentToFindCompletionFor.length())) {
                listOfPossibleCompletions.add(foundString);
            }
        }
        return listOfPossibleCompletions;
    }
}
