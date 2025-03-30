package de.oliver.fancynpcs.v1_20_1.attributes;

import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcAttribute;
import de.oliver.fancynpcs.v1_20_1.ReflectionHelper;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.item.DyeColor;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class WolfAttributes {

    public static List<NpcAttribute> getAllAttributes() {
        List<NpcAttribute> attributes = new ArrayList<>();

        attributes.add(new NpcAttribute(
                "pose",
                List.of("standing", "sitting"),
                List.of(EntityType.WOLF),
                WolfAttributes::setPose
        ));

        attributes.add(new NpcAttribute(
                "angry",
                List.of("true", "false"),
                List.of(EntityType.WOLF),
                WolfAttributes::setAngry
        ));

        attributes.add(new NpcAttribute(
                "color",
                List.of("RED", "BLUE", "YELLOW", "GREEN", "PURPLE", "ORANGE", "LIME", "MAGENTA", "BROWN", "WHITE", "GRAY", "LIGHT_GRAY", "LIGHT_BLUE", "BLACK", "CYAN", "PINK", "NONE"),
                List.of(EntityType.WOLF),
                WolfAttributes::setColor
        ));

        return attributes;
    }

    private static void setPose(Npc npc, String value) {
        Wolf wolf = ReflectionHelper.getEntity(npc);

        switch (value.toLowerCase()) {
            case "standing" -> wolf.setInSittingPose(false, false);
            case "sitting" -> wolf.setInSittingPose(true, false);
        }
    }

    private static void setAngry(Npc npc, String value) {
        Wolf wolf = ReflectionHelper.getEntity(npc);

        boolean angry = Boolean.parseBoolean(value.toLowerCase());

        wolf.setRemainingPersistentAngerTime(angry ? 100 : 0);
    }

    private static void setColor(Npc npc, String value) {
        Wolf wolf = ReflectionHelper.getEntity(npc);

        if (value.equalsIgnoreCase("none") || value.isEmpty()) {
            // Reset to no collar
            wolf.setTame(false);
            return;
        }

        try {
            DyeColor color = DyeColor.valueOf(value.toUpperCase());
            if (!wolf.isTame()){
                wolf.setTame(true);
            }
            wolf.setCollarColor(color);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid wolf collar color: " + value);
        }
    }
}
