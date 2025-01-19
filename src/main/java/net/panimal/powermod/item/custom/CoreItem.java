package net.panimal.powermod.item.custom;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CoreItem extends Item {

    private static class CoreExpansion {
        public final ServerWorld world;
        public final BlockPos center;
        public int currentRadius = 1;
        public final int maxRadius;
        public final int expansionDelay;
        private int tickCounter = 0;

        private CoreExpansion(ServerWorld world, BlockPos center, int maxRadius, int expansionDelay) {
            this.world = world;
            this.center = center;
            this.maxRadius = maxRadius;
            this.expansionDelay = expansionDelay;
        }
        public boolean tick() {
            tickCounter++;
            if (tickCounter >= expansionDelay) {
                tickCounter = 0;
                spawnRing();
                currentRadius++;

                return currentRadius > maxRadius;
            }
            return false;
        }

        private void SpawnRing() {
            int points = 16;
            double angleStep = 2 * Math.PI / points;

            for (int i = 0; i < points; i++) {
                double angle = i * angleStep;
                double offsetX = currentRadius * Math.cos(angle);
                double offsetZ = currentRadius * Math.sin(angle);

                BlockPos tntPos = center.add(
                        (int) offsetX,
                        0,
                        (int) offsetZ);

                SpawnTNT(tntPos);
            }
        }

        private void spawnRing() {
            // Spawn a ring of TNT at distance = currentRadius * ringSpacing
            int points = 32; // Number of points in the ring
            double angleStep = 2 * Math.PI / points; // Angle between each point
            // Distance between consecutive rings
            int ringSpacing = 2;
            double radius = currentRadius * ringSpacing; // Adjust radius based on spacing

            for (int i = 0; i < points; i++) {
                double angle = i * angleStep;
                double offsetX = radius * Math.cos(angle);
                double offsetZ = radius * Math.sin(angle);

                // Calculate the position for the TNT
                BlockPos tntPos = center.add(
                                (int) offsetX,
                                0,
                                (int) offsetZ);


                // Call the spawnTNT method to spawn TNT at the calculated position
                SpawnTNT(tntPos);
            }
        }

        private void SpawnTNT(BlockPos pos) {
            TntEntity tnt = new TntEntity(EntityType.TNT, world);
            tnt.setPos(pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5);
            tnt.setFuse(30);
            world.spawnEntity(tnt);
        }
    }
    private static final List<CoreExpansion> ACTIVE_EXPANSIONS = new ArrayList<>();

    static {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            // We iterate over expansions from the last to the first so we can remove safely
            Iterator<CoreExpansion> iterator = ACTIVE_EXPANSIONS.iterator();
            while (iterator.hasNext()) {
                CoreExpansion expansion = iterator.next();
                boolean done = expansion.tick();
                if (done) {
                    iterator.remove();
                }
            }
        });
    }

    public CoreItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        // Only run logic on the server side
        ServerWorld serverWorld = (ServerWorld) world;
        BlockPos pos = context.getBlockPos();

        // Create a new expansion with desired parameters
        CoreExpansion expansion = new CoreExpansion(
                serverWorld,
                pos,
                40,
                8
        );
        ACTIVE_EXPANSIONS.add(expansion);

        world.playSound(
                null,
                pos,
                SoundEvents.ENTITY_TNT_PRIMED,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f
        );

        return ActionResult.SUCCESS;
    }
}