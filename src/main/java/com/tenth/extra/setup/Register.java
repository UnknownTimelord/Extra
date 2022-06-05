package com.tenth.extra.setup;

import com.tenth.extra.Extra;
import com.tenth.extra.blocks.QuickRevive;
import com.tenth.extra.blocks.QuickReviveEntity;
import com.tenth.extra.effects.QuickReviveEffect;
import com.tenth.extra.recipe.QuickReviveRecipe;
import com.tenth.extra.screen.QuickReviveMenu;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.awt.event.InputEvent;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.jar.Attributes;

public class Register
{
    public static boolean hasDied = false; // Turns true when the player has died (must be set manually)
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Extra.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Extra.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Extra.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Extra.MOD_ID);
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Extra.MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Extra.MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Extra.MOD_ID);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType (IContainerFactory<T> factory, String name) {
        return MENUS.register(name, ()-> IForgeMenuType.create(factory));
    }

    public static void init()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
        SOUND_EVENTS.register(eventBus);
        EFFECTS.register(eventBus);
        MENUS.register(eventBus);
        SERIALIZERS.register(eventBus);
    }
    public static final CreativeModeTab COD_MENU = new CreativeModeTab("extra.cod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(QUICK_REVIVE.get());
        }
    };

    // ALL
    private static final ToIntFunction PERK_LIGHT = new ToIntFunction() {@Override public int applyAsInt(Object value) {return 10;}};
    public static final RegistryObject<SoundEvent> HUM_LOOP = SOUND_EVENTS.register("hum_loop", ()-> new SoundEvent(new ResourceLocation(Extra.MOD_ID, "hum_loop")));
    public static final RegistryObject<SoundEvent> ZOMBIE_LAUGH = SOUND_EVENTS.register("zombie_laugh", ()-> new SoundEvent(new ResourceLocation(Extra.MOD_ID, "zombie_laugh")));
    public static final RegistryObject<Item> ESSENCE = ITEMS.register("essence", ()-> new Item(new Item.Properties().tab(COD_MENU)));

    // QUICK REVIVE
    public static final RegistryObject<Block> QUICK_REVIVE = BLOCKS.register("quick_revive", ()-> new QuickRevive(BlockBehaviour.Properties.of(Material.METAL).randomTicks().lightLevel(PERK_LIGHT)));
    public static final RegistryObject<BlockItem> QUICK_REVIVE_ITEM = ITEMS.register("quick_revive", ()-> new BlockItem(QUICK_REVIVE.get(), new Item.Properties().tab(COD_MENU)));
    public static final RegistryObject<BlockEntityType<QuickReviveEntity>> QUICK_REVIVE_BLOCK_ENTITY = BLOCK_ENTITIES.register("quick_revive", ()-> BlockEntityType.Builder.of(QuickReviveEntity::new,QUICK_REVIVE.get()).build(null));
    public static final RegistryObject<SoundEvent> QUICK_REVIVE_JINGLE = SOUND_EVENTS.register("quick_revive_jingle", ()-> new SoundEvent(new ResourceLocation(Extra.MOD_ID, "quick_revive_jingle")));
    public static final RegistryObject<SoundEvent> QUICK_REVIVE_STING = SOUND_EVENTS.register("quick_revive_sting", ()-> new SoundEvent(new ResourceLocation(Extra.MOD_ID, "quick_revive_sting")));
    public static final RegistryObject<MobEffect> QUICK_REVIVE_EFFECT = EFFECTS.register("quick_revive_effect", ()-> new QuickReviveEffect(MobEffectCategory.BENEFICIAL, 3124687));
    public static final RegistryObject<MenuType<QuickReviveMenu>> QUICK_REVIVE_MENU = registerMenuType(QuickReviveMenu::new, "quick_revive_menu");
    public static final RegistryObject<RecipeSerializer<QuickReviveRecipe>> QUICK_REVIVE_SERIALIZER = SERIALIZERS.register("quick_buy", ()-> QuickReviveRecipe.Serializer.INSTANCE);
}