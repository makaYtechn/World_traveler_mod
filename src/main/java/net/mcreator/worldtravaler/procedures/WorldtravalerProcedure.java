package net.mcreator.worldtravaler.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.worldtravaler.WorldTravalerMod;

import java.util.Map;

public class WorldtravalerProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WorldTravalerMod.LOGGER.warn("Failed to load dependency entity for procedure Worldtravaler!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
	}
}
