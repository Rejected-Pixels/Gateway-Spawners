package com.rejectedpixels.gateway_spawners.common.block.entity;

import com.rejectedpixels.gateway_spawners.registries.BlockEntityTypeRegistry;
import dev.shadowsoffire.gateways.entity.GatewayEntity;
import dev.shadowsoffire.gateways.gate.Gateway;
import dev.shadowsoffire.gateways.gate.GatewayRegistry;
import dev.shadowsoffire.placebo.block_entity.TickingBlockEntity;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.Optional;

import static com.rejectedpixels.gateway_spawners.common.block.GatewaySpawnerBlock.getRandomGateway;

public class GatewaySpawnerBlockEntity extends BlockEntity implements TickingBlockEntity {
    protected int ticks = 0;
    private DynamicHolder<Gateway> gateway;

    public GatewaySpawnerBlockEntity(BlockPos pPos, BlockState pState) {
        this(pPos, pState, getRandomGateway());
    }

    public GatewaySpawnerBlockEntity(BlockPos pPos, BlockState pState, DynamicHolder<Gateway> gateway) {
        super(BlockEntityTypeRegistry.GATEWAY_SPAWNER_BLOCK_ENTITY.get(), pPos, pState);
        this.gateway = gateway;
    }

    @Override
    public void serverTick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (this.ticks++ % 40 == 0) {
            Optional<Player> opt = this.level.getEntities(EntityType.PLAYER, new AABB(this.worldPosition).inflate(8, 8, 8), EntitySelector.NO_CREATIVE_OR_SPECTATOR).stream().findFirst();
            opt.ifPresent(player -> {
                this.level.setBlockAndUpdate(this.worldPosition, Blocks.AIR.defaultBlockState());
                BlockPos pos = this.worldPosition;

                if (gateway.isBound()) {
                    GatewayEntity gatewayEntity = gateway.get().createEntity(pLevel, player);
                    gatewayEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
                    pLevel.addFreshEntity(gatewayEntity);
                    gatewayEntity.onGateCreated();
                }
            });
        }
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pProvider) {
        super.loadAdditional(pTag, pProvider);
        gateway = GatewayRegistry.INSTANCE.holder(ResourceLocation.parse(pTag.getString("gateway")));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pProvider) {
        super.saveAdditional(pTag, pProvider);
        pTag.putString("gateway", gateway.getId().toString());
    }
}
