package noppes.npcs.api.event;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;

/**
 * <p>
 * Varied hook function name (see detailed description)<br />
 * Represents an event triggered in the Forge script.
 * </p>
 * <p>Must be listened in a Forge script.</p>
 * <h2>Events listened</h2>
 * <p>This event is triggered when any Forge event that meets all the following criteria is fired:</p>
 * <ul>
 * <li>the event class is in the <code>net.minecraftforge.event</code> package or one of its subpackages, excluding the <code>net.minecraftforge.event.terraingen</code> package;</li>
 * <li>the event class is not assignable to any of the following classes:
 * <ul>
 * <li><code>net.minecraftforge.eventbus.api.GenericEvent</code></li>
 * <li>{@link net.minecraftforge.event.entity.EntityEvent.EntityConstructing}</li>
 * <li>{@link net.minecraftforge.event.world.WorldEvent.PotentialSpawns}</li>
 * <li>{@link net.minecraftforge.event.TickEvent.ClientTickEvent}</li>
 * <li>{@link net.minecraftforge.network.NetworkEvent.ClientCustomPayloadEvent}</li>
 * <li>{@link net.minecraftforge.event.entity.player.ItemTooltipEvent}</li>
 * </ul>
 * </li>
 * <li>the event class is public and not abstract.</li>
 * </ul>
 * <p>This event is also triggered when any Forge event that meets all the following criteria is fired:</p>
 * <ul>
 * <li>the event class is in the <code>com.pixelmonmod.pixelmon.api.events</code> package or one of its subpackages;</li>
 * <li>the event class is public and not abstract.</li>
 * </ul>
 * <h2>Hook function name</h2>
 * <p>For a non-nested class, the hook function name is its name with the first letter uncapitalized.</p>
 * <p>For a nested event class, the hook function name is the name of its enclosing class, with the first letter uncapitalized, joined with the name of the nested class itself.</p>
 * <p>For example, use <code>function livingFallEvent(e) { }</code> to listen to the {@link net.minecraftforge.event.entity.living.LivingFallEvent} event; use <code>function livingEventLivingUpdateEvent(e) { }</code> to listen to the {@link net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent} event.</p>
 * <h2>Notes</h2>
 * <p>The names of the fields and methods from the Minecraft code base are obfuscated, look up the corresponding "Searge" entry in <a href="https://mappings.cephx.dev/index.html">the mapping reference</a> to use those members.</p>
 * <p>Prior to 1.18.2.20231008snapshot, the events must be canceled on this class instead of the Forge event itself, otherwise the canceled state will be overwritten.</p>
 * <p>Prior to a certain version (to be added,) the instance passed as the parameter of the hook function is the Forge event itself instead of an instance of this class. Check this before get started.</p>
 * <p>Due to a bug from one of the dependencies of CNPC, the folder names in the path to Minecraft must contain only the <a href="https://www.rfc-editor.org/rfc/rfc3986.html#section-2.3">unreserved characters defined in RFC 3986</a> (specifically, uppercase letters <code>A</code>~<code>Z</code>, lowercase
   letters <code>a</code>~<code>z</code>, decimal digits <code>0</code>~<code>9</code>, hyphen <code>-</code>, period <code>,</code>, underscore <code>_</code>, and tilde <code>~</code>) for Forge script to work properly. Other characters, including common characters like spaces, will cause Forge events fail to be registered.</p>
 * <p>Call {@link isCancelable()} to check if the event is cancelable.</p>
 *
 * @since 1.12.2-23jul18snapshot
 */
@Cancelable
public class ForgeEvent extends CustomNPCsEvent {
	/**
	 * <p>The subject Forge event.</p>
	 */
	public final Event event;
	/**
	 * <p>Creates an instance of the {@link ForgeEvent} class.</p>
	 * <p>Scripters should not use this constructor.</p>
	 */
	public ForgeEvent(Event event) {
		this.event = event;
	}

	@Override
	public boolean isCancelable() {
		return event.isCancelable();
	}
	@Override
	public boolean isCanceled() {
		return event.isCanceled();
	}
	@Override
	public void setCanceled(boolean cancel) {
		event.setCanceled(cancel);
	}
	
	/**
	 * <p>
	 * Hook function name: <code>init</code><br />
	 * Triggered when the Forge script is initialized.
	 * </p>
	 * <p>The value of the {@link event} field is <code>null</code> for this event.</p>
	 *
	 * @since 1.12.2-24jul18snapshot
	 */
	public static class InitEvent extends ForgeEvent {
		/**
		 * <p>Creates an instance of the {@link InitEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public InitEvent() {
			super(null);
		}
	}
	
	/**
	 * <p>
	 * Varied hook function name (see detailed description of {@link ForgeEvent})<br />
	 * Triggered when an event whose class is assignable to {@link net.minecraftforge.event.entity.EntityEvent} is fired.
	 * </p>
	 *
	 * @since 1.12.2-23jul18snapshot
	 */
	@Cancelable
	public static class EntityEvent extends ForgeEvent{
		/**
		 * <p>The subject entity of the event.</p>
		 */
		public final IEntity entity;
		
		/**
		 * <p>Creates an instance of the {@link EntityEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public EntityEvent(net.minecraftforge.event.entity.EntityEvent event, IEntity entity) {
			super(event);
			this.entity = entity;
		}
		
	}
	
	/**
	 * <p>
	 * Varied hook function name (see detailed description of {@link ForgeEvent})<br />
	 * Triggered when an event whose class is assignable to {@link net.minecraftforge.event.world.WorldEvent} is fired.
	 * </p>
	 *
	 * @since 1.12.2-23jul18snapshot
	 */
	@Cancelable
	public static class LevelEvent extends ForgeEvent{
		/**
		 * <p>The subject world of the event.</p>
		 */
		public final IWorld world;
		
		/**
		 * <p>Creates an instance of the {@link LevelEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public LevelEvent(net.minecraftforge.event.world.WorldEvent event, IWorld world) {
			super(event);
			this.world = world;
		}
		
	}
}
