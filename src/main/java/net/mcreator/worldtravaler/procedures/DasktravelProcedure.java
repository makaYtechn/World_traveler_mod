package net.mcreator.worldtravaler.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;

import net.minecraft.world.World;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.worldtravaler.item.OghraniennyiSkintItem;
import net.mcreator.worldtravaler.WorldTravalerModVariables;
import net.mcreator.worldtravaler.WorldTravalerMod;

import java.util.Map;
import java.util.HashMap;

public class DasktravelProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityTravelToDimension(EntityTravelToDimensionEvent event) {
			Entity entity = event.getEntity();
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("dimension", event.getDimension());
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("dimension") == null) {
			if (!dependencies.containsKey("dimension"))
				WorldTravalerMod.LOGGER.warn("Failed to load dependency dimension for procedure Dasktravel!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WorldTravalerMod.LOGGER.warn("Failed to load dependency entity for procedure Dasktravel!");
			return;
		}
		RegistryKey<World> dimension = (RegistryKey<World>) dependencies.get("dimension");
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WorldTravalerModVariables.PlayerVariables())).dask == true) {
			if (((entity instanceof PlayerEntity)
					? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(OghraniennyiSkintItem.block))
					: false) == false) {
				if ((dimension) == (World.THE_NETHER)) {
					entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 2000);
				}
				if ((dimension) == (World.THE_END)) {
					entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 2000);
				}
				if ((dimension) == (World.OVERWORLD)) {
					entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, (float) 2000);
				}
				{
					double _setval = ((entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new WorldTravalerModVariables.PlayerVariables())).counter
							- (entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new WorldTravalerModVariables.PlayerVariables())).counter);
					entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.counter = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
