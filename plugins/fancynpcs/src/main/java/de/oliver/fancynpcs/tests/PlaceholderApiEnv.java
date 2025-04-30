package de.oliver.fancynpcs.tests;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderApiEnv extends PlaceholderExpansion {
    public static String parsedString = "Grabsky";

    public static void registerPlaceholders() {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            return;
        }

        PlaceholderExpansion expansion = new PlaceholderApiEnv();
        expansion.register();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "fn-test";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Oliver";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return null;
        }

        return parsedString;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (player == null) {
            return null;
        }

        return parsedString;
    }

}
