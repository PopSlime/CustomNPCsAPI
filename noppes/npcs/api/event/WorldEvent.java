package noppes.npcs.api.event;

import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;

/**
 * <p>Represents a world event triggered on any scripts.</p>
 *
 * <p>This class is first added in 1.12.2-15jul18snapshot, later removed in 1.12.2-23jul18snapshot, and later re-added in 1.12.2-09May19snapshot.</p>
 *
 * @since 1.12.2-09May19snapshot
 */
public class WorldEvent extends CustomNPCsEvent{
	/**
	 * <p>The subject world of the event.</p>
	 */
	public final IWorld world;
	/**
	 * <p>Creates an instance of the {@link WorldEvent} class.</p>
	 * <p>Scripters should not use this constructor.</p>
	 */
	public WorldEvent(IWorld world) {
		this.world = world;
	}
	
	/**
	 * <p>
	 * Hook function name: <code>trigger</code><br />
	 * Triggered when the <code>trigger()</code> method is called on the scripted object, or the <code>/noppes script trigger</code> command is executed on the scripted object.
	 * </p>
	 * <p>This event is triggered either when:</p>
	 * <ul>
	 * <li>if in a block script, {@link noppes.npcs.api.block.IBlockScripted#trigger(int,java.lang.Object...)} is called on the scripted block;</li>
	 * <li>if in an NPC script, {@link noppes.npcs.api.entity.ICustomNpc#trigger(int,java.lang.Object...)} is called on the NPC;</li>
	 * <li>if in a player script, {@link noppes.npcs.api.entity.IPlayer#trigger(int,java.lang.Object...)} is called on the player;</li>
	 * <li>if in a Forge script, {@link IWorld#trigger(int,java.lang.Object...)} is called;</li>
	 * <li>the <code>/noppes script trigger</code> command is executed on the scripted object.</li>
	 * </ul>
	 * <p>This event is not cancelable.</p>
	 *
	 * <p>Prior to 1.16.5.20220607snapshot, this event can only be triggered via command, and its hook function name is <code>scriptCommand</code>.</p>
	 *
	 * @since 1.12.2-09May19snapshot
	 */
	public static class ScriptTriggerEvent extends WorldEvent{
		/**
		 * <p>A list of arguments passed from the calling method or the command.</p>
		 *
		 * <p>Prior to 1.16.5.20220607snapshot, the type of this field is <code>{@link String}[]</code>.</p>
		 */
		public final Object[] arguments;
		/**
		 * <p>The position of the scripted object, or zero in a Forge script.</p>
		 */
		public final IPos pos;
		/**
		 * <p>The entity on which the <code>/noppes script trigger</code> command is executed, or <code>null</code> if not triggered by a command.</p>
		 *
		 * @since 1.16.5.20220607snapshot
		 */
		public final IEntity entity;
		
		/**
		 * <p>The ID of the trigger specified by the calling method or the command.</p>
		 *
		 * <p>Prior to 1.16.5.20220608snapshot, this field is private.</p>
		 *
		 * @since 1.16.5.20220607snapshot
		 */
		public final int id;
		
		/**
		 * <p>Creates an instance of the {@link ScriptTriggerEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public ScriptTriggerEvent(int id, IWorld level, IPos pos, IEntity entity, Object[] arguments) {
			super(level);
			this.id = id;
			this.arguments = arguments;
			this.pos = pos;
			this.entity = entity;
		}
	}

	/**
	 * <p>
	 * No hook function name<br />
	 * Triggered when an entity is spawned or loaded.
	 * </p>
	 * <p>Canceling this event prevents the entity from being spawned.</p>
	 *
	 * @deprecated Removed in 1.12.2-23jul18snapshot
	 * @since 1.12.2-15jul18snapshot
	 */
	@net.minecraftforge.eventbus.api.Cancelable
	public static class EntitySpawnEvent extends WorldEvent{
		/**
		 * <p>The entity spawned or loaded.</p>
		 */
		public final IEntity entity;
		
		/**
		 * <p>Creates an instance of the {@link EntitySpawnEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public EntitySpawnEvent(IWorld world, IEntity entity) {
			super(world);
			this.entity = entity;
		}
		
	}
}
