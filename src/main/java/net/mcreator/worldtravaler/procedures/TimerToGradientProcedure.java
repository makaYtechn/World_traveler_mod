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

public class TimerToGradientProcedure {
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
				WorldTravalerMod.LOGGER.warn("Failed to load dependency world for procedure TimerToGradient!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (WorldTravalerModVariables.MapVariables.get(world).timer_to_gradient == 0) {
			WorldTravalerModVariables.MapVariables.get(world).timer_to_gradient = 1000;
			WorldTravalerModVariables.MapVariables.get(world).syncData(world);
		} else {
			WorldTravalerModVariables.MapVariables.get(world).timer_to_gradient = (WorldTravalerModVariables.MapVariables.get(world).timer_to_gradient
					- 1);
			WorldTravalerModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
