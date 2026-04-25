package io.github.techtastic.simulated_gauges;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.RegistrateDistExecutor;
import io.github.techtastic.simulated_gauges.datagen.BlockModelProvider;
import io.github.techtastic.simulated_gauges.datagen.ItemModelProvider;
import io.github.techtastic.simulated_gauges.datagen.LanguageProvider;
import io.github.techtastic.simulated_gauges.datagen.RecipeProvider;
import io.github.techtastic.simulated_gauges.registry.ItemsRegistry;
import io.github.techtastic.simulated_gauges.registry.PanelRegistry;
import io.github.techtastic.simulated_gauges.registry.PartialModelsRegistry;
import io.github.techtastic.simulated_gauges.renderer.PanelRenderers;
import net.liukrast.deployer.lib.helper.ClientRegisterHelpers;
import net.minecraft.data.tags.InstrumentTagsProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

@Mod(SimulatedGauges.MOD_ID)
public class SimulatedGauges {
    public static final String MOD_ID = "simulated_gauges";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimulatedGauges(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::generateData);
        modEventBus.addListener(this::addItemsToTab);

        RegistrateDistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            PartialModelsRegistry.init();

            //ClientRegisterHelpers.registerPanelRenderer(new PanelRenderers());
        });

        PanelRegistry.init(modEventBus);
        ItemsRegistry.init(modEventBus);
    }

    private void generateData(GatherDataEvent event) {
        event.createProvider(LanguageProvider::new);
        event.createProvider(output -> new BlockModelProvider(output, event.getExistingFileHelper()));
        event.createProvider(output -> new ItemModelProvider(output, event.getExistingFileHelper()));
        event.createProvider(output -> new RecipeProvider(output, event.getLookupProvider()));
    }

    private void addItemsToTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ItemsRegistry.TAB.get()) {
            event.accept(ItemsRegistry.ALTITUDE_PANEL.get());
            event.accept(ItemsRegistry.VELOCITY_PANEL.get());
            event.accept(ItemsRegistry.GIMBAL_PANEL.get());
        }
    }
}
