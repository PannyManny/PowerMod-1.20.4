package net.panimal.powermod.item.custom;

import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LaserItem extends Item {
    public LaserItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        // Play the TNT priming sound
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_TNT_PRIMED,
                SoundCategory.NEUTRAL,
                0.1F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        if (!world.isClient) {
            // Create the TNT entity
            TntEntity tntEntity = new TntEntity(world, user.getX(), user.getEyeY() - 0.1, user.getZ(), user);

            // Set the fuse time (default 80 ticks)
            tntEntity.setFuse(10);

            // Set the velocity of the TNT
            Vec3d direction = user.getRotationVec(1.0F).multiply(1.5); // Speed multiplier
            tntEntity.setVelocity(direction);

            // Spawn the TNT entity in the world
            world.spawnEntity(tntEntity);
        }

        // Track the item usage in stats
        user.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.pass(itemStack);
    }
}