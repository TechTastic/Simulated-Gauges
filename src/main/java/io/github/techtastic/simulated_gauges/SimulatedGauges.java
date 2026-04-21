package io.github.techtastic.simulated_gauges;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import org.slf4j.Logger;

@Mod(SimulatedGauges.MOD_ID)
public class SimulatedGauges {
    public static final String MOD_ID = "simulated_gauges";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimulatedGauges(IEventBus modEventBus, ModContainer modContainer) {
    }
}
