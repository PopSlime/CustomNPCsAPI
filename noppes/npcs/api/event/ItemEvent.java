package noppes.npcs.api.event;

import net.minecraftforge.eventbus.api.Cancelable;
import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityItem;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.item.IItemScripted;

/**
 * <p>Represents an event triggered on scripted items.</p>
 * <p>Must be listened in an item script.</p>
 *
 * @since 1.12.2-15mar18snapshot
 */
public class ItemEvent extends CustomNPCsEvent {
	/**
	 * The subject scripted item of the event.
	 */
	public IItemScripted item;
	
	/**
	 * <p>Creates an instance of the {@link ItemEvent} class.</p>
	 * <p>Scripters should not use this constructor.</p>
	 */
	public ItemEvent(IItemScripted item) {
		this.item = item;
	}
	
	/**
	 * <p>
	 * Hook function name: <code>init</code><br />
	 * Triggered when the scripted item is created, loaded, or thrown, or <em>at most</em> 10 game ticks after it is picked up or its script is edited.
	 * </p>
	 * <p>After the script on the scripted item is edited, the first subsequent event triggered on the scripted item will also trigger this event. Because the {@link UpdateEvent} event is triggered every 10 game ticks, this event is triggered at most 10 game ticks after the script is edited.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	public static class InitEvent extends ItemEvent {
		/**
		 * <p>Creates an instance of the {@link InitEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public InitEvent(IItemScripted item) {
			super(item);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>tick</code><br />
	 * Triggered every 10 game ticks (0.5 seconds expected) when the scripted item is in the player inventory.
	 * </p>
	 * <p>This event is first triggered immediately when the scripted item is created or loaded.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	public static class UpdateEvent extends ItemEvent {
		/**
		 * <p>The player holding the scripted item.</p>
		 */
		public IPlayer player;
		/**
		 * <p>Creates an instance of the {@link UpdateEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public UpdateEvent(IItemScripted item, IPlayer player) {
			super(item);
			this.player = player;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>spawn</code><br />
	 * Triggered when the entity for the scripted item is spawned.
	 * </p>
	 * <p>This event is usually triggered when the scripted item is thrown.</p>
	 * <p>Canceling this event prevents the item entity from being spawned, but the item will not be returned.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	@Cancelable
	public static class SpawnEvent extends ItemEvent {
		/**
		 * <p>The item entity carrying the scripted item.</p>
		 */
		public IEntityItem entity;
		/**
		 * <p>Creates an instance of the {@link SpawnEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public SpawnEvent(IItemScripted item, IEntityItem entity) {
			super(item);
			this.entity = entity;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>tossed</code><br />
	 * Triggered when the scripted item is thrown.
	 * </p>
	 * <p>Canceling this event prevents the item entity from being spawned, but the item will not be returned.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	@Cancelable
	public static class TossedEvent extends ItemEvent {
		/**
		 * <p>The item entity carrying the scripted item.</p>
		 */
		public IEntityItem entity;
		/**
		 * <p>The player throwing the scripted item.</p>
		 */
		public IPlayer player;
		/**
		 * <p>Creates an instance of the {@link TossedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TossedEvent(IItemScripted item, IPlayer player, IEntityItem entity) {
			super(item);
			this.entity = entity;
			this.player = player;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>pickedUp</code><br />
	 * Triggered when the scripted item is picked up.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	public static class PickedUpEvent extends ItemEvent {
		/**
		 * <p>The item entity carrying the scripted item.</p>
		 */
		public IEntityItem entity;
		/**
		 * <p>The player picking up the scripted item.</p>
		 */
		public IPlayer player;
		/**
		 * <p>Creates an instance of the {@link PickedUpEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public PickedUpEvent(IItemScripted item, IPlayer player, IEntityItem entity) {
			super(item);
			this.entity = entity;
			this.player = player;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>interact</code><br />
	 * Triggered when a player interacts (default control: mouse right click) with the scripted item in the main hand.
	 * </p>
	 * <p>Canceling this event blocks the original interaction.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	@Cancelable
	public static class InteractEvent extends ItemEvent {
		/**
		 * <p>The type of the object that the player is interacting with.</p>
		 * <p>Possible values are:</p>
		 * <ul>
		 * <li><code>0</code>, the object is the air;</li>
		 * <li><code>1</code>, the object is an entity;</li>
		 * <li><code>2</code>, the object is a block.</li>
		 * </ul>
		 */
		public final int type;
		/**
		 * <p>The target object that the player is interacting with.</p>
		 * <p>The type of this field depends on the value of {@link type}:</p>
		 * <ul>
		 * <li>if {@link type} is <code>0</code>, this is <code>null</code>;</li>
		 * <li>if {@link type} is <code>1</code>, this is an instance of the {@link IEntity} interface;</li>
		 * <li>if {@link type} is <code>2</code>, this is an instance of the {@link noppes.npcs.api.block.IBlock} interface.</li>
		 * </ul>
		 */
		public final Object target;
		/**
		 * <p>The player interacting with the scripted item.</p>
		 */
		public IPlayer player;
		
		/**
		 * <p>Creates an instance of the {@link InteractEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public InteractEvent(IItemScripted item, IPlayer player, int type, Object target) {
			super(item);
			this.type = type;
			this.target = target;
			this.player = player;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>attack</code><br />
	 * Triggered when a player starts destroying (default control: mouse left click) a block, attacks (default control: mouse left click) an entity or the air with the scripted item in the main hand.
	 * </p>
	 * <p>Canceling this event prevents the block from being destroyed or the attack from being done. Canceling this event has no effect if the player is attacking the air.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	@Cancelable
	public static class AttackEvent extends ItemEvent {
		/**
		 * <p>The type of the object that the player is attacking or destroying.</p>
		 * <p>Possible values are:</p>
		 * <ul>
		 * <li><code>0</code>, the object is the air;</li>
		 * <li><code>1</code>, the object is an entity;</li>
		 * <li><code>2</code>, the object is a block.</li>
		 * </ul>
		 */
		public final int type;
		
		/**
		 * <p>The target object that the player is attacking or destroying.</p>
		 * <p>The type of this field depends on the value of {@link type}:</p>
		 * <ul>
		 * <li>if {@link type} is <code>0</code>, this is <code>null</code>;</li>
		 * <li>if {@link type} is <code>1</code>, this is an instance of the {@link IEntity} interface;</li>
		 * <li>if {@link type} is <code>2</code>, this is an instance of the {@link noppes.npcs.api.block.IBlock} interface.</li>
		 * </ul>
		 */
		public final Object target;
		
		/**
		 * <p>The player attacking or destroying.</p>
		 */
		public IPlayer player;
		
		/**
		 * <p>The damage source to be applied to the {@link target} entity.</p>
		 * <p>The value of this field is <code>null</code> if {@link type} is <code>0</code> or <code>1</code>.</p>
		 *
		 * @since 1.16.5-14Nov21snapshot
		 * @since 1.12.2-19Jan22snapshot
		 */
		public final IDamageSource damageSource;
		
		/**
		 * <p>Creates an instance of the {@link AttackEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public AttackEvent(IItemScripted item, IPlayer player, int type, Object target) {
			super(item);
			this.type = type;
			this.target = target;
			this.player = player;
			this.damageSource = null;
		}
		
		/**
		 * <p>Creates an instance of the {@link AttackEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public AttackEvent(IItemScripted item, IPlayer player, IEntity target, IDamageSource damageSource) {
			super(item);
			this.type = 1;
			this.target = target;
			this.player = player;
			this.damageSource = damageSource;
		}
	}

}
