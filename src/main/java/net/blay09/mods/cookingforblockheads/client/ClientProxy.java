package net.blay09.mods.cookingforblockheads.client;

import net.blay09.mods.cookingforblockheads.CommonProxy;
import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class ClientProxy extends CommonProxy {

    public static final TextureAtlasSprite[] ovenToolIcons = new TextureAtlasSprite[4];

    public ClientProxy() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void registerModels() {
        /*ModelLoader.setCustomStateMapper(ModBlocks.fridge, new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(BlockState state) {
                if (state.getValue(FridgeBlock.TYPE) == FridgeBlock.FridgeType.LARGE) {
                    return new ModelResourceLocation(CookingForBlockheads.MOD_ID + ":fridge_large", getPropertyString(state.getProperties()));
                } else if (state.getValue(FridgeBlock.TYPE) == FridgeBlock.FridgeType.INVISIBLE) {
                    return new ModelResourceLocation(CookingForBlockheads.MOD_ID + ":fridge_invisible", getPropertyString(state.getProperties()));
                }
                return super.getModelResourceLocation(state);
            }
        });

        DefaultStateMapper ignorePropertiesStateMapper = new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(BlockState state) {
                ResourceLocation location = state.getBlock().getRegistryName();
                return new ModelResourceLocation(location != null ? location.toString() : "", "normal");
            }
        };
        ModelLoader.setCustomStateMapper(ModBlocks.cuttingBoard, ignorePropertiesStateMapper);
        ModelLoader.setCustomStateMapper(ModBlocks.fruitBasket, ignorePropertiesStateMapper);*/
    }

    @Override
    public List<ITextComponent> getItemTooltip(ItemStack itemStack, PlayerEntity player) {
        return itemStack.getTooltip(player, ITooltipFlag.TooltipFlags.NORMAL);
    }

    @SubscribeEvent
    public void registerIconsPre(TextureStitchEvent.Pre event) {
        event.addSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_bakeware"));
        event.addSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_pot"));
        event.addSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_saucepan"));
        event.addSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_skillet"));
    }

    @SubscribeEvent
    public void registerIconsPost(TextureStitchEvent.Post event) {
        ovenToolIcons[0] = event.getMap().getSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_bakeware"));
        ovenToolIcons[1] = event.getMap().getSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_pot"));
        ovenToolIcons[2] = event.getMap().getSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_saucepan"));
        ovenToolIcons[3] = event.getMap().getSprite(new ResourceLocation(CookingForBlockheads.MOD_ID, "items/slot_skillet"));
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event) {
        /*try {
            IModel model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/milk_jar_liquid"));
            MilkJarRenderer.modelMilkLiquid = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/sink_liquid"));
            SinkRenderer.modelSinkLiquid = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/oven_door"));
            OvenRenderer.modelDoor = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/oven_door_active"));
            OvenRenderer.modelDoorActive = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_door"));
            FridgeRenderer.modelDoor = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_large_door"));
            FridgeRenderer.modelDoorLarge = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_door_handle"));
            FridgeRenderer.modelHandle = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_large_door_handle"));
            FridgeRenderer.modelHandleLarge = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_door_ice_unit"));
            FridgeRenderer.modelDoorIceUnit = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            model = ModelLoaderRegistry.getModel(new ResourceLocation(CookingForBlockheads.MOD_ID, "block/fridge_large_door_ice_unit"));
            FridgeRenderer.modelDoorIceUnitLarge = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, ModelLoader.defaultTextureGetter());

            IModel cuttingBoard = ModelLoaderRegistry.getModel(new ResourceLocation("cookingforblockheads", "block/cutting_board"));
            event.getModelRegistry().putObject(new ModelResourceLocation("cookingforblockheads:cutting_board", "normal"), new LowerableFacingOnDemandModel(cuttingBoard));

            IModel fruitBasket = ModelLoaderRegistry.getModel(new ResourceLocation("cookingforblockheads", "block/fruit_basket"));
            event.getModelRegistry().putObject(new ModelResourceLocation("cookingforblockheads:fruit_basket", "normal"), new LowerableFacingOnDemandModel(fruitBasket));
        } catch (Exception e) {
            e.printStackTrace();
        }

        EnumDyeColor[] colors = EnumDyeColor.values();
        CounterRenderer.models = new IBakedModel[4][colors.length];
        CounterRenderer.modelsFlipped = new IBakedModel[4][colors.length];
        CabinetRenderer.models = new IBakedModel[4][colors.length];
        CabinetRenderer.modelsFlipped = new IBakedModel[4][colors.length];
        BlockState defaultState = ModBlocks.counter.getDefaultState();
        BlockState state = defaultState.withProperty(CounterBlock.PASS, CounterBlock.ModelPass.DOOR);
        BlockState flippedState = defaultState.withProperty(CounterBlock.PASS, CounterBlock.ModelPass.DOOR_FLIPPED);
        for (int i = 0; i < 4; i++) {
            EnumFacing facing = EnumFacing.getHorizontal(i);
            for (int j = 0; j < colors.length; j++) {
                EnumDyeColor color = colors[j];
                CounterRenderer.models[i][j] = event.getModelRegistry().getObject(new ModelResourceLocation(CounterBlock.registryName, dummyStateMapper.getPropertyString(state.withProperty(CounterBlock.FACING, facing).withProperty(CounterBlock.COLOR, color).getProperties())));
                CounterRenderer.modelsFlipped[i][j] = event.getModelRegistry().getObject(new ModelResourceLocation(CounterBlock.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(CounterBlock.FACING, facing).withProperty(CounterBlock.COLOR, color).getProperties())));

                CabinetRenderer.models[i][j] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinet.registryName, dummyStateMapper.getPropertyString(state.withProperty(CounterBlock.FACING, facing).withProperty(CounterBlock.COLOR, color).getProperties())));
                CabinetRenderer.modelsFlipped[i][j] = event.getModelRegistry().getObject(new ModelResourceLocation(BlockCabinet.registryName, dummyStateMapper.getPropertyString(flippedState.withProperty(CounterBlock.FACING, facing).withProperty(CounterBlock.COLOR, color).getProperties())));
            }
        }*/
    }

}
