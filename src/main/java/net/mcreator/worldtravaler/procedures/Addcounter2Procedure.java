package net.mcreator.worldtravaler.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.worldtravaler.WorldTravalerModVariables;
import net.mcreator.worldtravaler.WorldTravalerMod;

import java.util.Map;
import java.util.HashMap;

public class Addcounter2Procedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WorldTravalerMod.LOGGER.warn("Failed to load dependency world for procedure Addcounter2!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WorldTravalerMod.LOGGER.warn("Failed to load dependency entity for procedure Addcounter2!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new WorldTravalerModVariables.PlayerVariables())).dask == false) {
			if ((entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new WorldTravalerModVariables.PlayerVariables())).counter < 60) {
				if (WorldTravalerModVariables.MapVariables.get(world).global_timer == 0) {
					{
						double _setval = ((entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new WorldTravalerModVariables.PlayerVariables())).counter + 1);
						entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.counter = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		}
	}
}
