package net.mcreator.worldtravaler.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.IWorld;

import net.mcreator.worldtravaler.WorldTravalerModVariables;
import net.mcreator.worldtravaler.WorldTravalerMod;

import java.util.Map;
import java.util.HashMap;

public class AddCounterProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldTick(TickEvent.WorldTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				IWorld world = event.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("world", world);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WorldTravalerMod.LOGGER.warn("Failed to load dependency world for procedure AddCounter!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (WorldTravalerModVariables.MapVariables.get(world).global_timer == 0) {
			WorldTravalerModVariables.MapVariables.get(world).global_timer = 1200;
			WorldTravalerModVariables.MapVariables.get(world).syncData(world);
		} else {
			WorldTravalerModVariables.MapVariables.get(world).global_timer = (WorldTravalerModVariables.MapVariables.get(world).global_timer - 1);
			WorldTravalerModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
