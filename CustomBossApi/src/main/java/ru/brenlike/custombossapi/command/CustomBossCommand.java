package ru.brenlike.custombossapi.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.brenlike.custombossapi.CustomBossApi;
import ru.brenlike.custombossapi.util.ErrorMessages;

import java.util.ArrayList;
import java.util.List;

public final class CustomBossCommand implements TabExecutor {
    private final CustomBossApi plugin;
    List<String> container = new ArrayList<>();

    public CustomBossCommand(CustomBossApi p_4808_) {
        this.plugin = p_4808_;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) ErrorMessages.LITTLE_ARGS.send(sender);

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.m_2010_();
            plugin.m_2011_();
            CustomBossApi.m_7011_().send(sender, "plugin.reloaded");
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (container.isEmpty()) {
            if (sender.hasPermission("custombossapi.command.customboss.reload")) container.add("reload");
            if (sender.hasPermission("custombossapi.command.customboss.boss")) container.add("boss");
            if (sender instanceof Player) container.add("slime_chunk_check");
        }

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a:
                    container){
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) result.add(a);
                else return container;
            }
            return result;
        }
        return null;
    }
}
