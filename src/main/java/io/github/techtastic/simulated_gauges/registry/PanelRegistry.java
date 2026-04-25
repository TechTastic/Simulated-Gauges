package io.github.techtastic.simulated_gauges.registry;

import io.github.techtastic.simulated_gauges.SimulatedGauges;
import io.github.techtastic.simulated_gauges.content.logistics.board.AltitudeSensorPanelBehaviour;
import io.github.techtastic.simulated_gauges.content.logistics.board.GimbalSensorPanelBehaviour;
import io.github.techtastic.simulated_gauges.content.logistics.board.VelocitySensorPanelBehaviour;
import net.liukrast.deployer.lib.logistics.board.PanelType;
import net.liukrast.deployer.lib.registry.DeployerRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PanelRegistry {
    private static final DeferredRegister<PanelType<?>> REGISTER = DeferredRegister.create(DeployerRegistries.PANEL, SimulatedGauges.MOD_ID);

    public static final DeferredHolder<PanelType<?>, PanelType<AltitudeSensorPanelBehaviour>> ALTITUDE =
            REGISTER.register("altitude", () -> new PanelType<>(AltitudeSensorPanelBehaviour::new, AltitudeSensorPanelBehaviour.class));
    public static final DeferredHolder<PanelType<?>, PanelType<VelocitySensorPanelBehaviour>> VELOCITY =
            REGISTER.register("velocity", () -> new PanelType<>(VelocitySensorPanelBehaviour::new, VelocitySensorPanelBehaviour.class));
    public static final DeferredHolder<PanelType<?>, PanelType<GimbalSensorPanelBehaviour>> GIMBAL =
            REGISTER.register("gimbal", () -> new PanelType<>(GimbalSensorPanelBehaviour::new, GimbalSensorPanelBehaviour.class));

    public static void init(IEventBus eventBus) {
        REGISTER.register(eventBus);
    }
}
