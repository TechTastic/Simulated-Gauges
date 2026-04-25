package io.github.techtastic.simulated_gauges.registry;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import io.github.techtastic.simulated_gauges.SimulatedGauges;
import net.minecraft.resources.ResourceLocation;

public class PartialModelsRegistry {
    public static final PartialModel ALTITUDE_PANEL = block("altitude_gauge");
    public static final PartialModel VELOCITY_PANEL = block("velocity_gauge");
    public static final PartialModel GIMBAL_PANEL = block("gimbal_gauge");
    public static final PartialModel VELOCITY_PANEL_FAN = block("velocity_gauge_fan");
    public static final PartialModel GIMBAL_PANEL_POINTER = block("gimbal_gauge_pointer");

    private static PartialModel block(String path) {
        return PartialModel.of(ResourceLocation.fromNamespaceAndPath(SimulatedGauges.MOD_ID, "block/" + path));
    }

    public static void init() {}
}
