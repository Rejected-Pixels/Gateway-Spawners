package com.rejectedpixels.gateway_spawners.common.block;

import com.rejectedpixels.gateway_spawners.common.block.entity.GatewaySpawnerBlockEntity;
import dev.shadowsoffire.gateways.GatewayObjects;
import dev.shadowsoffire.gateways.gate.Gateway;
import dev.shadowsoffire.gateways.gate.GatewayRegistry;
import dev.shadowsoffire.placebo.block_entity.TickingEntityBlock;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class GatewaySpawnerBlock extends Block implements TickingEntityBlock {
    public GatewaySpawnerBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops()
                .strength(5.0F)
                .sound(SoundType.METAL)
                .noOcclusion());
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        ItemStack stack = new ItemStack(this);
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if(tileEntity != null){
            tileEntity.saveToItem(stack, level.registryAccess());
        }
        return stack;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pBlockPos, BlockState pBlockState) {
        return new GatewaySpawnerBlockEntity(pBlockPos, pBlockState);
    }

    public static void setGateway(ItemStack pStack, Gateway pGateway){
        pStack.set(GatewayObjects.GATEWAY_COMPONENT,  GatewayRegistry.INSTANCE.holder(pGateway));
    }

    public static DynamicHolder<Gateway> getGateway(ItemStack pStack){
        return pStack.getOrDefault(GatewayObjects.GATEWAY_COMPONENT, GatewayRegistry.INSTANCE.emptyHolder());
    }

    public static DynamicHolder<Gateway> getRandomGateway() {
        Gateway randomGateway = GatewayRegistry.INSTANCE.getValues()
                .stream()
                .skip((int) (GatewayRegistry.INSTANCE.getValues().size() * Math.random()))
                .findFirst().orElse(null);

        return GatewayRegistry.INSTANCE.holder(randomGateway);
    }
}
