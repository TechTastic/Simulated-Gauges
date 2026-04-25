package io.github.techtastic.simulated_gauges.datagen;

import com.simibubi.create.AllItems;
import dev.simulated_team.simulated.index.SimBlocks;
import io.github.techtastic.simulated_gauges.registry.ItemsRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider implements IConditionBuilder {
    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        makeGauge(ItemsRegistry.ALTITUDE_PANEL.get(), SimBlocks.ALTITUDE_SENSOR.asItem(), recipeOutput);
        makeGauge(ItemsRegistry.VELOCITY_PANEL.get(), SimBlocks.VELOCITY_SENSOR.asItem(), recipeOutput);
        makeGauge(ItemsRegistry.GIMBAL_PANEL.get(), SimBlocks.GIMBAL_SENSOR.asItem(), recipeOutput);
    }

    public void makeGauge(Item result, Item ingredient, @NotNull RecipeOutput output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, result, 2)
                .requires(AllItems.PRECISION_MECHANISM)
                .requires(ingredient)
                .unlockedBy("has_precision_mechanism", has(AllItems.PRECISION_MECHANISM)).save(output);
    }
}
