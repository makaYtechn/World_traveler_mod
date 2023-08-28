package net.mcreator.worldtravaler.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.worldtravaler.WorldTravalerModVariables;
import net.mcreator.worldtravaler.WorldTravalerMod;

import java.util.Map;

public class LifeCounterGradient1Procedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WorldTravalerMod.LOGGER.warn("Failed to load dependency world for procedure LifeCounterGradient1!");
			return false;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WorldTravalerMod.LOGGER.warn("Failed to load dependency entity for procedure LifeCounterGradient1!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (WorldTravalerModVariables.MapVariables.get(world).timer_to_gradient >= 100) {
			if ((entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new WorldTravalerModVariables.PlayerVariables())).LifeCounter >= 40) {
				return true;
			}
		}
		return false;
	}
}
