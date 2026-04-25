package io.github.techtastic.simulated_gauges.datagen;

import io.github.techtastic.simulated_gauges.registry.ItemsRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static io.github.techtastic.simulated_gauges.SimulatedGauges.MOD_ID;
import static net.liukrast.deployer.lib.helper.MinecraftHelpers.ModelProvider.Blocks.createPanel;

public class BlockModelProvider extends net.neoforged.neoforge.client.model.generators.BlockModelProvider {
    public BlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        createPanel(this, ItemsRegistry.ALTITUDE_PANEL.get());
        createPanel(this, ItemsRegistry.VELOCITY_PANEL.get());
        createPanel(this, ItemsRegistry.GIMBAL_PANEL.get());
    }
}
