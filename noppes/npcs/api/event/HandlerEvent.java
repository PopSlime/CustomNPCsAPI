package noppes.npcs.api.event;

import noppes.npcs.api.handler.IFactionHandler;
import noppes.npcs.api.handler.IRecipeHandler;

/**
 * <p>Represents an event related to CNPC handlers.</p>
 * <p>Cannot be listened in any scripts. Used by modders.</p>
 *
 * @since 1.8.9(29oct16)
 */
public class HandlerEvent {
	
	/**
	 * <p>Triggered when the custom recipes are loaded.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * <p>Prior to 1.11.2(29oct17), this class directly inherits from the Forge <code>Event</code> class.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	public static class RecipesLoadedEvent extends CustomNPCsEvent{
		/**
		 * <p>The recipe handler.</p>
		 */
		public final IRecipeHandler handler;
		
		/**
		 * <p>Creates an instance of the {@link RecipesLoadedEvent} class.</p>
		 * <p>Modders should not use this constructor.</p>
		 */
		public RecipesLoadedEvent(IRecipeHandler handler) {
			this.handler = handler;
		}
	}
	
	/**
	 * <p>Triggered when the factions are loaded.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * <p>Prior to 1.11.2(29oct17), this class directly inherits from the Forge <code>Event</code> class.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	public static class FactionsLoadedEvent extends CustomNPCsEvent{
		/**
		 * <p>The faction handler.</p>
		 */
		public final IFactionHandler handler;
		
		/**
		 * <p>Creates an instance of the {@link FactionsLoadedEvent} class.</p>
		 * <p>Modders should not use this constructor.</p>
		 */
		public FactionsLoadedEvent(IFactionHandler handler) {
			this.handler = handler;
		}
	}
}
