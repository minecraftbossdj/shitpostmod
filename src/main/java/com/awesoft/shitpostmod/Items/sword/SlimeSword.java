package com.awesoft.shitpostmod.Items.sword;

import com.awesoft.shitpostmod.Shitpostmod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.awt.*;
import java.util.Optional;

import static com.awesoft.shitpostmod.Utils.RaycastingUtils.raycastEntity;

public class SlimeSword extends SwordItem {
    public SlimeSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) target;

            player.getInventory().dropAll();

            Shitpostmod.LOGGER.info("hit player named: "+player.getDisplayName());
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {

            Optional<Entity> targetEntity = raycastEntity(player, 5.0);
            if (targetEntity.isPresent()) {
                Entity entity = targetEntity.get();
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;

                    //if (entity instanceof Blaze || entity instanceof ZombifiedPiglin || entity instanceof Piglin || entity instanceof Hoglin || entity instanceof Zoglin || entity instanceof MagmaCube) {
                    if (!(entity instanceof Strider) && livingEntity.fireImmune()) {
                        world.setBlock(livingEntity.blockPosition(), Blocks.POWDER_SNOW.defaultBlockState(), 3);
                        world.setBlock(livingEntity.blockPosition().offset(0,1,0), Blocks.POWDER_SNOW.defaultBlockState(), 3);
                    } else if (entity instanceof EnderDragon || entity instanceof WitherBoss || entity instanceof ElderGuardian || livingEntity.isBaby()) {
                        ItemStack itemstack = player.getItemInHand(hand);
                        itemstack.setCount(0);
                    } else if (entity instanceof Strider) {
                        ItemStack itemstack = player.getItemInHand(hand);
                        itemstack.setCount(0);

                        Holder<DamageType> damageTypeHolder = world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC);
                        DamageSource damageSource = new DamageSource(damageTypeHolder);
                        player.die(damageSource);

                        player.remove(Entity.RemovalReason.valueOf("bud dont do that"));

                    } else {
                        world.setBlock(livingEntity.blockPosition(), Blocks.LAVA.defaultBlockState(), 3);
                    }

                    ItemStack itemstack = player.getItemInHand(hand);
                    itemstack.hurtAndBreak(50,player,(p) -> p.broadcastBreakEvent(hand));

                }
            }
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }


}
