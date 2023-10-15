package noppes.npcs.api.event;

import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.Event;
import noppes.npcs.api.NpcAPI;

/**
 * <p>Represents an event dispatched by CNPC to the scripts.</p>
 *
 * @since 1.11.2(29oct17)
 */
public class CustomNPCsEvent extends Event {
	/**
	 * <p>An instance of the {@link NpcAPI} class.</p>
	 */
	public final NpcAPI API = NpcAPI.Instance();
}
