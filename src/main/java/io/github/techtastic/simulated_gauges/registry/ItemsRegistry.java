package io.github.techtastic.simulated_gauges.registry;

import io.github.techtastic.simulated_gauges.SimulatedGauges;
import net.liukrast.deployer.lib.logistics.board.PanelBlockItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemsRegistry {
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SimulatedGauges.MOD_ID);
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimulatedGauges.MOD_ID);

    public static final DeferredItem<Item> ICON = ITEMS.register("icon", () -> new Item(new Item.Properties()));
    public static final DeferredItem<PanelBlockItem> ALTITUDE_PANEL = ITEMS.register("altitude_gauge",
            () -> new PanelBlockItem(PanelRegistry.ALTITUDE::get, new Item.Properties()));
    public static final DeferredItem<PanelBlockItem> VELOCITY_PANEL = ITEMS.register("velocity_gauge",
            () -> new PanelBlockItem(PanelRegistry.VELOCITY::get, new Item.Properties()));
    public static final DeferredItem<PanelBlockItem> GIMBAL_PANEL = ITEMS.register("gimbal_gauge",
            () -> new PanelBlockItem(PanelRegistry.GIMBAL::get, new Item.Properties()));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("tab", () ->
            CreativeModeTab.builder().title(Component.translatable("itemGroup." + SimulatedGauges.MOD_ID)).icon(ICON::toStack).build());

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
        TABS.register(bus);
    }
}