package com.awesoft.shitpostmod.Utils;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class RaycastingUtils {

    public static Optional<Entity> raycastEntity(Player player, double distance) {
        Level world = player.level();
        Vec3 startVec = player.getEyePosition(); // Player's eye position
        Vec3 lookVec = player.getViewVector(1.0F).scale(distance); // Direction the player is looking
        Vec3 endVec = startVec.add(lookVec); // Calculate the end position based on the distance

        // Ray trace to check for block collision
        BlockHitResult blockHitResult = world.clip(new ClipContext(
                startVec,
                endVec,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                player
        ));

        // Adjust the end vector to not go through blocks
        double blockHitDistance = blockHitResult.getLocation().distanceTo(startVec);
        if (blockHitDistance < distance) {
            endVec = blockHitResult.getLocation();
        }

        // Create an axis-aligned bounding box for entity collision detection
        AABB boundingBox = new AABB(startVec, endVec).inflate(1.0D); // Inflate the box for easier detection
        Entity closestEntity = null;
        double closestDistance = distance;

        // Check entities within the bounding box
        for (Entity entity : world.getEntities(player, boundingBox)) {
            // Skip if the entity is the player or is immune to being targeted
            if (entity == player || !entity.isPickable()) continue;

            // Calculate the entity's collision box and check for intersection with the ray
            AABB entityBoundingBox = entity.getBoundingBox().inflate(0.3D);
            Optional<Vec3> intersection = entityBoundingBox.clip(startVec, endVec);

            if (intersection.isPresent()) {
                double entityDistance = startVec.distanceTo(intersection.get());
                if (entityDistance < closestDistance) {
                    closestEntity = entity;
                    closestDistance = entityDistance;
                }
            }
        }

        return Optional.ofNullable(closestEntity);
    }

}
