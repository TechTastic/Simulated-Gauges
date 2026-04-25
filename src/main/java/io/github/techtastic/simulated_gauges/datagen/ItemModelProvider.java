package io.github.techtastic.simulated_gauges.datagen;

import io.github.techtastic.simulated_gauges.registry.ItemsRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static io.github.techtastic.simulated_gauges.SimulatedGauges.MOD_ID;
import static net.liukrast.deployer.lib.helper.MinecraftHelpers.ModelProvider.Items.createPanel;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        createPanel(this, ItemsRegistry.ALTITUDE_PANEL.get());
        createPanel(this, ItemsRegistry.VELOCITY_PANEL.get());
        createPanel(this, ItemsRegistry.GIMBAL_PANEL.get());

        basicItem(ItemsRegistry.ICON.get());
    }
}
