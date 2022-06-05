package com.tenth.extra.events;

import com.tenth.extra.Extra;
import com.tenth.extra.recipe.QuickReviveRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.tenth.extra.setup.Register.hasDied;

@Mod.EventBusSubscriber(modid = Extra.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents
{
    @SubscribeEvent
    public static void quickDead(PlayerEvent.PlayerRespawnEvent event) {
        if(hasDied)
        {
            event.getPlayer().addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0));
            hasDied = false;
        }
    }

    @SubscribeEvent
    public void registerCaps(RegisterCapabilitiesEvent event) {

    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, QuickReviveRecipe.Type.ID, QuickReviveRecipe.Type.INSTANCE);
    }
}
