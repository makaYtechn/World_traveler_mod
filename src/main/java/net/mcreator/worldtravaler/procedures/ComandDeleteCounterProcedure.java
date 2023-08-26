package net.mcreator.worldtravaler.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.worldtravaler.WorldTravalerModVariables;
import net.mcreator.worldtravaler.WorldTravalerMod;

import java.util.Map;

public class ComandDeleteCounterProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WorldTravalerMod.LOGGER.warn("Failed to load dependency entity for procedure ComandDeleteCounter!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = ((entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new WorldTravalerModVariables.PlayerVariables())).counter - 10);
			entity.getCapability(WorldTravalerModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.counter = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
