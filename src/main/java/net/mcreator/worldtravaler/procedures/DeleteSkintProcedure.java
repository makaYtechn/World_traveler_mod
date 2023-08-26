package net.mcreator.worldtravaler.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;

import net.minecraft.world.World;
import net.minecraft.util.RegistryKey;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.worldtravaler.item.SkintItem;
import net.mcreator.worldtravaler.WorldTravalerModVariables;
import net.mcreator.worldtravaler.WorldTravalerMod;

import java.util.Map;
import java.util.HashMap;

public class DeleteSkintProcedure {
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
				WorldTravalerMod.LOGGER.warn("Failed to load dependency dimension for procedure DeleteSkint!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WorldTravalerMod.LOGGER.warn("Failed to load dependency entity for procedure DeleteSkint!");
			return;
		}
		RegistryKey<World> dimension = (RegistryKey<World>) dependencies.get("dimension");
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WorldTravalerModVariables.PlayerVariables())).dask == true) {
			if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(SkintItem.block)) : false) == true) {
				if ((dimension) == (World.OVERWORLD)) {
					if (entity instanceof PlayerEntity) {
						ItemStack _stktoremove = new ItemStack(SkintItem.block);
						((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
								((PlayerEntity) entity).container.func_234641_j_());
					}
				}
				if ((dimension) == (World.THE_NETHER)) {
					if (entity instanceof PlayerEntity) {
						ItemStack _stktoremove = new ItemStack(SkintItem.block);
						((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
								((PlayerEntity) entity).container.func_234641_j_());
					}
				}
				if ((dimension) == (World.THE_END)) {
					if (entity instanceof PlayerEntity) {
						ItemStack _stktoremove = new ItemStack(SkintItem.block);
						((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
								((PlayerEntity) entity).container.func_234641_j_());
					}
				}
			}
		}
	}
}
