package io.github.techtastic.simulated_gauges.datagen;

import io.github.techtastic.simulated_gauges.SimulatedGauges;
import io.github.techtastic.simulated_gauges.registry.ItemsRegistry;
import net.liukrast.deployer.lib.helper.datagen.DeployerLanguageProviderImpl;
import net.minecraft.data.PackOutput;

public class LanguageProvider extends DeployerLanguageProviderImpl {
    public LanguageProvider(PackOutput output) {
        super(output, SimulatedGauges.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + SimulatedGauges.MOD_ID, "Simulated Gauges");

        addItem(ItemsRegistry.ALTITUDE_PANEL, "Altitude Gauge");
        addItem(ItemsRegistry.VELOCITY_PANEL, "Velocity Gauge");
        addItem(ItemsRegistry.GIMBAL_PANEL, "Gimbal Gauge");

        add("create.logistics.altitude_selection", "Altitude/Air Pressure Selection");
        add("altitude_gauge.mode.height", "Altitude");
        add("altitude_gauge.mode.pressure", "Air Pressure");

        add("create.logistics.vector_selection", "Output Axis Selection");
        add("vector_gauge.mode.x", "X Axis");
        add("vector_gauge.mode.y", "Y Axis");
        add("vector_gauge.mode.z", "Z Axis");
    }
}
