package com.awesoft.shitpostmod.Items.misc;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CursedChain extends Item {

    private static final Map<UUID, UUID> heldEntities = new HashMap<>();

    public CursedChain(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player player)) return false;

        Vec3 pushDirection = target.position().subtract(player.position()).normalize();
        double pushStrength = 1.5;

        //knockback
        target.setDeltaMovement(pushDirection.scale(pushStrength));

        heldEntities.remove(player.getUUID());


        stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));


        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (world.isClientSide) return InteractionResultHolder.success(player.getItemInHand(hand)); // Client-side check

        UUID playerId = player.getUUID();

        if (heldEntities.containsKey(playerId)) {
            heldEntities.remove(playerId); // Drop the entity
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        Entity target = raycastEntity(player, 15.0);
        if (target instanceof LivingEntity) {
            heldEntities.put(playerId, target.getUUID());
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    private Entity raycastEntity(Player player, double distance) {
        Vec3 start = player.getEyePosition();
        Vec3 end = start.add(player.getViewVector(1.0F).scale(distance));

        // Find the closest entity in range
        for (Entity entity : player.level().getEntities(player, player.getBoundingBox().inflate(distance))) {
            if (entity instanceof LivingEntity && entity.getBoundingBox().clip(start, end).isPresent()) {
                return entity;
            }
        }
        return null;
    }

    @Mod.EventBusSubscriber
    public static class TelekinesisHandler {
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            Player player = event.player;
            UUID playerId = player.getUUID();

            if (!heldEntities.containsKey(playerId)) return;

            UUID entityUUID = heldEntities.get(playerId);
            if (entityUUID == null || !(player.level() instanceof ServerLevel serverLevel)) return;

            Entity entity = serverLevel.getEntity(entityUUID);

            if (entity != null) {
                Vec3 targetPos = player.getEyePosition().add(player.getViewVector(1.0F).scale(2.5)); // Float in front
                entity.setDeltaMovement(targetPos.subtract(entity.position()).scale(0.2)); // Smooth movement
            }
        }
    }
}
