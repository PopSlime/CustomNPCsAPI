package noppes.npcs.api.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Cancelable;
import noppes.npcs.api.IPos;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.block.IBlock;
import noppes.npcs.api.constants.SideType;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;

/**
 * <p>Represents an event triggered on scripted blocks (including scripted doors.)</p>
 * <p>Must be listened in a block script.</p>
 *
 * <p>Prior to 1.11.2(29oct17), this class directly inherits from the Forge <code>Event</code> class.</p>
 */
public class BlockEvent extends CustomNPCsEvent {
	/**
	 * The subject scripted block of the event.
	 */
	public IBlock block;
	/**
	 * <p>Creates an instance of the {@link BlockEvent} class.</p>
	 * <p>Scripters should not use this constructor.</p>
	 */
	public BlockEvent(IBlock block){
		this.block = block;
	}
	
	/**
	 * <p>
	 * Hook function name: <code>fallenUpon</code><br />
	 * Triggered when an entity is fallen upon the scripted block.
	 * </p>
	 * <p>Canceling this event prevents the fall damage from being applied and any fall sounds from being played.</p>
	 */
	@Cancelable
	public static class EntityFallenUponEvent extends BlockEvent{
		/**
		 * <p>The entity fallen upon the scripted block.</p>
		 */
		public final IEntity entity;
		/**
		 * <p>The distance the entity has fallen.</p>
		 * <p>Setting this field changes the distance factor used to calculate the fall damage and determine the fall sound.</p>
		 */
		public float distanceFallen;
		/**
		 * <p>Creates an instance of the {@link EntityFallenUponEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public EntityFallenUponEvent(IBlock block, Entity entity, float distance){
			super(block);
			this.distanceFallen = distance;
			this.entity = NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>interact</code><br />
	 * Triggered when a player interacts (default control: mouse right click) with the scripted block.
	 * </p>
	 * <p>This event is not triggered if the player is interacting with the scripted block with an Npc Wand or a Scripter in their main hand.</p>
	 * <p>For scripted doors, this event is also not triggered if the player is holding a Scripted Door in their main hand.</p>
	 * <p>Canceling this event blocks the original interaction. For scripted doors, canceling this event also blocks the {@link DoorToggleEvent} event from being triggered.</p>
	 */
	@Cancelable
	public static class InteractEvent extends BlockEvent{
		/**
		 * <p>The player interacting with the scripted block.</p>
		 */
		public final IPlayer player;
		
		/**
		 * <p>The X component of the position on the hitbox of the scripted block where the players hits, relative to the west side of the block position where the hitbox is in.</p>
		 * <p>For a scripted block that has multiple hitboxes, it is not possible to tell which hitbox is hit, because the value is relative to the block position where the hitbox is in, instead of the position of the scripted block itself. For example, it is not possible to tell whether the upper part or the lower part is hit on a scripted door, and if the same position is hit on each of the two hitboxes of the scripted door, the value of this field would be the same.</p>
		 */
		public final float hitX;
		/**
		 * <p>The Y component of the position on the hitbox of the scripted block where the players hits, relative to the lower side of the block position where the hitbox is in.</p>
		 * <p>For a scripted block that has multiple hitboxes, it is not possible to tell which hitbox is hit, because the value is relative to the block position where the hitbox is in, instead of the position of the scripted block itself. For example, it is not possible to tell whether the upper part or the lower part is hit on a scripted door, and if the same position is hit on each of the two hitboxes of the scripted door, the value of this field would be the same.</p>
		 */
		public final float hitY;
		/**
		 * <p>The Z component of the position on the hitbox of the scripted block where the players hits, relative to the north side of the block position where the hitbox is in.</p>
		 * <p>For a scripted block that has multiple hitboxes, it is not possible to tell which hitbox is hit, because the value is relative to the block position where the hitbox is in, instead of the position of the scripted block itself. For example, it is not possible to tell whether the upper part or the lower part is hit on a scripted door, and if the same position is hit on each of the two hitboxes of the scripted door, the value of this field would be the same.</p>
		 */
		public final float hitZ;
		
		/**
		 * <p>One of the {@link SideType} values indicating the side the player interacts with on the scripted block.</p>
		 */
		public final int side;
		
		/**
		 * <p>Creates an instance of the {@link InteractEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public InteractEvent(IBlock block, Player player, int side, float hitX, float hitY, float hitZ) {
			super(block);
			this.player = (IPlayer) NpcAPI.Instance().getIEntity(player);

			this.hitX = hitX;
			this.hitY = hitY;
			this.hitZ = hitZ;
			this.side = side;
		}
		
	}
	
	/**
	 * <p>
	 * Hook function name: <code>redstone</code><br />
	 * Triggered when the redstone power the scripted block receives changes.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class RedstoneEvent extends BlockEvent{
		/**
		 * The redstone power previously received by the scripted block.
		 */
		public final int prevPower;
		/**
		 * The redstone power currently received by the scripted block.
		 */
		public final int power;
		/**
		 * <p>Creates an instance of the {@link RedstoneEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public RedstoneEvent(IBlock block, int prevPower, int power) {
			super(block);
			this.power = power;
			this.prevPower = prevPower;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>doorToggle</code><br />
	 * Triggered when the scripted door is toggled.
	 * </p>
	 * <p>This event is only triggered on scripted doors.</p>
	 * <p>If the scripted door is toggled by player's interaction (default control: mouse right click,) the {@link InteractEvent} will be triggered first. And if that event is canceled, this event will not be triggered.</p>
	 * <p>Canceling this event prevents the scripted door from being toggled.</p>
	 */
	@Cancelable
	public static class DoorToggleEvent extends BlockEvent{
		/**
		 * <p>Creates an instance of the {@link DoorToggleEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public DoorToggleEvent(IBlock block) {
			super(block);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>broken</code><br />
	 * Triggered when the scripted block is destroyed.
	 * </p>
	 * <p>Canceling this event prevents the block from being destroyed.</p>
	 */
	public static class BreakEvent extends BlockEvent{
		/**
		 * <p>Creates an instance of the {@link BreakEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public BreakEvent(IBlock block) {
			super(block);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>exploded</code><br />
	 * Triggered when the scripted block is destroyed by an explosion.
	 * </p>
	 * <p>Canceling this event prevents the block from being destroyed.</p>
	 */
	@Cancelable
	public static class ExplodedEvent extends BlockEvent{
		/**
		 * <p>Creates an instance of the {@link ExplodedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public ExplodedEvent(IBlock block) {
			super(block);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>rainFilled</code><br />
	 * Triggered at a probability of 1/4096 every game tick (triggered every 204.8 seconds expected in average) when the scripted block is exposed under rain.
	 * </p>
	 * <p>Within a chunk, every game tick, there is a probability of 1/16 that, the game chooses a random block that has the highest Y coordinate of all the motion-blocking blocks with the same X and Z coordinates. If it is raining in the biome where that block is, the block will be notified being rained upon. Since there are a total of 16 * 16 = 256 blocks to be chosen in this way within a chunk, the probability for a specific block to be chosen every game tick is 1/256 * 1/16 = 1/4096.</p>
	 * <p>The <code>randomTickSpeed</code> game rule does not affect this probability.</p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class RainFillEvent extends BlockEvent{
		/**
		 * <p>Creates an instance of the {@link RainFillEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public RainFillEvent(IBlock block) {
			super(block);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>neighborChanged</code><br />
	 * Triggered when a block is placed right next to the scripted block, the state of one of the neighbor blocks changes, or one of the neighbor blocks is destroyed.
	 * </p>
	 * <p>For a scripted block that has multiple hitboxes, all the neighbor blocks of all the hitboxes can trigger this event.</p>
	 * <p>Not all the state changes can trigger this event.</p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class NeighborChangedEvent extends BlockEvent{
		/**
		 * <p>The position of the changed neighbor block.</p>
		 *
		 * @since 1.12.2-08Jan20snapshot
		 */
		public final IPos changedPos;
		/**
		 * <p>Creates an instance of the {@link NeighborChangedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public NeighborChangedEvent(IBlock block, IPos changedPos) {
			super(block);
			this.changedPos = changedPos;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>init</code><br />
	 * Triggered when the scripted block is placed or loaded, or <em>at most</em> 10 game ticks (0.5 seconds expected) after the block script is edited.
	 * </p>
	 * <p>After the block script is edited, the first subsequent event triggered on the scripted block will also trigger this event. Because the {@link UpdateEvent} event is triggered every 10 game ticks, this event is triggered at most 10 game ticks after the script is edited.</p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class InitEvent extends BlockEvent{
		/**
		 * <p>Creates an instance of the {@link InitEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public InitEvent(IBlock block) {
			super(block);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>tick</code><br />
	 * Triggered every 10 game ticks (0.5 seconds expected.)
	 * </p>
	 * <p>This event is first triggered immediately when the scripted block is placed.</p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class UpdateEvent extends BlockEvent{
		/**
		 * <p>Creates an instance of the {@link UpdateEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public UpdateEvent(IBlock block) {
			super(block);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>clicked</code><br />
	 * Triggered when a player starts destroying (default control: mouse left click) the scripted block.
	 * </p>
	 * <p>This event is not triggered when the player tries to destroy the scripted block in adventure mode without an item that can be used to destroy the scripted block.</p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class ClickedEvent extends BlockEvent{
		/**
		 * <p>The player destroying the scripted block.</p>
		 */
		public final IPlayer player;
		/**
		 * <p>Creates an instance of the {@link ClickedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public ClickedEvent(IBlock block, Player player) {
			super(block);
			this.player = (IPlayer) NpcAPI.Instance().getIEntity(player);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>harvested</code><br />
	 * Triggered when a player is done destroying (default control: mouse left click) the scripted block.
	 * </p>
	 * <p>The {@link PlayerEvent.BreakEvent} is triggered before this event, and canceling that event prevents this event from being triggered.</p>
	 * <p>Canceling this event prevents the block from being destroyed.</p>
	 *
	 * <p>Prior to 1.10.2(21jul17) or 1.11.2(29oct17), this event is not cancelable.</p>
	 */
	@Cancelable
	public static class HarvestedEvent extends BlockEvent{
		/**
		 * <p>The player destroying the scripted block.</p>
		 */
		public final IPlayer player;
		/**
		 * <p>Creates an instance of the {@link HarvestedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public HarvestedEvent(IBlock block, Player player) {
			super(block);
			this.player = (IPlayer) NpcAPI.Instance().getIEntity(player);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>collide</code><br />
	 * Triggered periodically when an entity collides with the scripted block.
	 * </p>
	 * <p>This event may be triggered multiple times within a single game tick for an entity.</p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class CollidedEvent extends BlockEvent{
		/**
		 * <p>The entity colliding with the scripted block.</p>
		 */
		public final IEntity entity;
		/**
		 * <p>Creates an instance of the {@link CollidedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public CollidedEvent(IBlock block, Entity entity) {
			super(block);
			this.entity = NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>timer</code><br />
	 * Triggered when a timer on the scripted block is done.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.8.9(29oct16)
	 *
	 * @see noppes.npcs.api.block.IBlockScripted#getTimers()
	 * @see noppes.npcs.api.block.IBlockScriptedDoor#getTimers()
	 */
	public static class TimerEvent extends BlockEvent{
		/**
		 * <p>The ID of the timer.</p>
		 */
		public final int id;
		
		/**
		 * <p>Creates an instance of the {@link TimerEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TimerEvent(IBlock block, int id) {
			super(block);
			this.id = id;
		}
	}
}
