package com.rejectedpixels.gateway_spawners.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.rejectedpixels.gateway_spawners.registries.BlockEntityTypeRegistry;
import com.rejectedpixels.gateway_spawners.registries.BlockRegistry;
import dev.shadowsoffire.gateways.command.GatewayCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Collection;
import java.util.Iterator;

public class GatewaySpawnerCommand {
    public GatewaySpawnerCommand() {
    }

    public static void register(CommandDispatcher<CommandSourceStack> pDispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("gateway_spawners").requires((s) -> {
            return s.hasPermission(2);
        });
        builder.then(Commands.literal("give")
                .then(Commands.argument("targets", EntityArgument.player())
                        .then(Commands.argument("type", ResourceLocationArgument.id()).suggests(GatewayCommand.SUGGEST_TYPE).executes((c) -> {
                                            return giveGatewaySpawner(c, ResourceLocationArgument.getId(c, "type"), EntityArgument.getPlayers(c, "targets"));
                                        }
                                )
                        )));
        pDispatcher.register(builder);
    }

    private static int giveGatewaySpawner(CommandContext<CommandSourceStack> c, ResourceLocation type, Collection<ServerPlayer> targets) {
        ItemStack itemStack = new ItemStack(BlockRegistry.GATEWAY_SPAWNER_BLOCK);
        itemStack.applyComponents(DataComponentPatch.builder()
                .set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(buildGatewaySpawnerTag(type))).build());
        Iterator<ServerPlayer> iterator = targets.iterator();
        iterator.forEachRemaining(
                serverPlayer -> {
                    serverPlayer.getInventory().add(itemStack);
                }
        );
        return 0;
    }

    private static CompoundTag buildGatewaySpawnerTag(ResourceLocation type) {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", BlockEntityType.getKey(BlockEntityTypeRegistry.GATEWAY_SPAWNER_BLOCK_ENTITY.get()).toString());
        tag.putString("gateway", type.toString());
        return tag;
    }
}
