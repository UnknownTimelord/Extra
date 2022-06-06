package com.tenth.extra.events;

import com.mojang.logging.LogUtils;
import com.tenth.extra.Extra;
import com.tenth.extra.recipe.QuickReviveRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Extra.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        LogUtils.getLogger().info("RECIPE TYPE FOR QUICK REVIVE HAS BEEN REGISTERED");
        Registry.register(Registry.RECIPE_TYPE, QuickReviveRecipe.Type.ID, QuickReviveRecipe.Type.INSTANCE);
    }
}
