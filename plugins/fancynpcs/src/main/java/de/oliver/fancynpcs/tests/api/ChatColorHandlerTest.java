package de.oliver.fancynpcs.tests.api;

import de.oliver.fancynpcs.tests.PlaceholderApiEnv;
import de.oliver.plugintests.annotations.FPTest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.lushplugins.chatcolorhandler.ChatColorHandler;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

import static de.oliver.plugintests.Expectable.expect;

public class ChatColorHandlerTest {

    @FPTest(name = "Test Placeholders")
    public void testPlaceholders(Player player) {
        if (!isPlaceholderAPIEnabled()) {
            return;
        }

        String input = "Player name: %fn-test%";
        String got = ChatColorHandler.translate(input, player, ParserTypes.placeholder());
        String expected = "Player name: " + PlaceholderApiEnv.parsedString;

        expect(got).toEqual(expected);
    }

    private boolean isPlaceholderAPIEnabled() {
        Plugin placeholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        return placeholderAPI != null && placeholderAPI.isEnabled();
    }

}
