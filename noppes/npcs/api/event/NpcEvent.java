package noppes.npcs.api.event;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.eventbus.api.Cancelable;
import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLiving;
import noppes.npcs.api.entity.IProjectile;
import noppes.npcs.api.entity.data.ILine;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.entity.IPlayer;

/**
 * <p>Represents an event triggered on NPCs.</p>
 * <p>Must be listened in an NPC script.</p>
 *
 * <p>Prior to 1.11.2(29oct17), this class directly inherits from the Forge <code>Event</code> class.</p>
 */
public class NpcEvent extends CustomNPCsEvent{
	/**
	 * <p>The subject NPC of the event.</p>
	 */
	public final ICustomNpc npc;

	/**
	 * <p>Creates an instance of the {@link NpcEvent} class.</p>
	 * <p>Scripters should not use this constructor.</p>
	 */
	public NpcEvent(ICustomNpc npc) {
		this.npc = npc;
	}

	/**
	 * <p>
	 * Hook function name: <code>init</code><br />
	 * Triggered when the NPC is created, edited, loaded, reset, respawned, or when the NPC travels across dimensions, or 10 game ticks (0.5 seconds expected) after the NPC travels from the End to Overworld, or <em>at most</em> 10 game ticks after its script is edited.
	 * </p>
	 * <p>After the script on the NPC is edited, the first subsequent event triggered on the NPC will also trigger this event. Because the {@link UpdateEvent} event is triggered every 10 game ticks, this event is triggered at most 10 game ticks after the script is edited.</p>
	 * <p>This event is triggered twice after the NPC travels from the End to Overworld, first immediately, and then again 10 game ticks later.</p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class InitEvent extends NpcEvent{
		/**
		 * <p>Creates an instance of the {@link InitEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public InitEvent(ICustomNpc npc) {
			super(npc);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>tick</code><br />
	 * Triggered every 10 game ticks (0.5 seconds expected.)
	 * </p>
	 * <p>This event is first triggered immediately when the NPC is created, is loaded, or travels from the End to Overworld, or 9 game ticks (0.45 seconds expected) after the NPC travels across other dimensions if the NPC is not unloaded. It keeps being triggered until the NPC is despawned or unloaded. Note that it also keeps being triggered after the NPC dies but with respawn enabled.</p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class UpdateEvent extends NpcEvent{
		/**
		 * <p>Creates an instance of the {@link UpdateEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public UpdateEvent(ICustomNpc npc) {
			super(npc);
		}
	}

	/**
	 * <p>
	 * Hook function name: <code>target</code><br />
	 * Triggered when the NPC targets an entity.
	 * </p>
	 * <p>Canceling this event prevents the target of the NPC from being changed and the attack line of the NPC from being said.</p>
	 */
	@Cancelable
	public static class TargetEvent extends NpcEvent{
		/**
		 * <p>The entity targeted.</p>
		 * <p>Setting this field causes the NPC to target the set entity (or to lose target if set to <code>null</code>) instead.</p>
		 */
		public IEntityLiving entity;
		/**
		 * <p>Creates an instance of the {@link TargetEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TargetEvent(ICustomNpc npc, LivingEntity entity) {
			super(npc);
			this.entity = (IEntityLiving) NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>targetLost</code><br />
	 * Triggered when the NPC loses its target.
	 * </p>
	 * <p>Canceling this event prevents the target of the NPC from being lost if possible.</p>
	 */
	@Cancelable
	public static class TargetLostEvent extends NpcEvent{
		/**
		 * <p>The entity target lost.</p>
		 */
		public final IEntityLiving entity;
		/**
		 * <p>Creates an instance of the {@link TargetLostEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TargetLostEvent(ICustomNpc npc, LivingEntity entity) {
			super(npc);
			this.entity = (IEntityLiving) NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>interact</code><br />
	 * Triggered when a player interacts (default control: mouse right click) with the NPC.
	 * </p>
	 * <p>This event is not triggered if the player is interacting with the NPC with an Npc Wand, a Mob Cloner, an Npc Pather, a Mounter, or a Scripter in their main hand.</p>
	 * <p>Canceling this event blocks the original interaction that the NPC would have done.</p>
	 */
	@Cancelable
	public static class InteractEvent extends NpcEvent{
		/**
		 * <p>The player interacting with the NPC.</p>
		 *
		 * <p>Prior to 1.8.9(29oct16), the type of this field is <code>net.minecraft.entity.player.EntityPlayer</code> (refactored to {@link Player}.)</p>
		 */
		public final IPlayer player;
		/**
		 * <p>Creates an instance of the {@link InteractEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public InteractEvent(ICustomNpc npc, Player player) {
			super(npc);
			this.player = (IPlayer) NpcAPI.Instance().getIEntity(player);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>died</code><br />
	 * Triggered when the NPC dies.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class DiedEvent extends NpcEvent{
		/**
		 * <p>The damage source that causes the death.</p>
		 *
		 * <p>Prior to 1.11.2(29oct17), this field is named <code>mcDamageSource</code>, and the type of this field is <code>net.minecraft.util.DamageSource</code> (refactored to {@link DamageSource}.)</p>
		 */
		public final IDamageSource damageSource;
		
		/**
		 * <p>The type of the damage source that causes the death.</p>
		 * <p>Identical to the value returned by calling {@link IDamageSource#getType()} on {@link #damageSource}.</p>
		 */
		public final String type;
		/**
		 * <p>The ultimate source entity of the damage that causes the death.</p>
		 * <p>For an indirect damage, this is the indirect source of the damage.</p>
		 */
		public final IEntity source;
		
		/**
		 * <p>The items to be dropped from the NPC.</p>
		 * <p>Setting this field changes the dropped items.</p>
		 *
		 * @since 1.12.2-08Jan20snapshot
		 */
		public IItemStack[] droppedItems;
		/**
		 * <p>The amount of experiences to be dropped from the NPC.</p>
		 * <p>Setting this field changes the dropped amount of experiences.</p>
		 *
		 * @since 1.12.2-08Jan20snapshot
		 */
		public int expDropped;
		/**
		 * <p>The death line to be said by the NPC.</p>
		 * <p>Setting this field changes the said line (or blocks the line if set to <code>null</code>.)</p>
		 *
		 * @since 1.12.2-08Jan20snapshot
		 */
		public ILine line;
		/**
		 * <p>Creates an instance of the {@link DiedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public DiedEvent(ICustomNpc npc, DamageSource damagesource, Entity entity) {
			super(npc);
			this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
			type = damagesource.msgId;
			this.source = NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>kill</code><br />
	 * Triggered when the NPC kills an entity.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class KilledEntityEvent extends NpcEvent{
		/**
		 * <p>The entity killed.</p>
		 */
		public final IEntityLiving entity;
		/**
		 * <p>Creates an instance of the {@link KilledEntityEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public KilledEntityEvent(ICustomNpc npc, LivingEntity entity) {
			super(npc);
			this.entity = (IEntityLiving) NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>meleeAttack</code><br />
	 * Triggered when the NPC does a melee attack.
	 * </p>
	 * <p>Canceling this event prevents the melee attack from being done.</p>
	 */
	@Cancelable
	public static class MeleeAttackEvent extends NpcEvent{
		/**
		 * <p>The entity to be attacked.</p>
		 */
		public final IEntityLiving target;
		/**
		 * <p>The amount of damage to be applied.</p>
		 * <p>Setting this field changes the amount of damage.</p>
		 */
		public float damage;
		
		/**
		 * <p>Creates an instance of the {@link MeleeAttackEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public MeleeAttackEvent(ICustomNpc npc, LivingEntity target, float damage) {
			super(npc);
			this.target = (IEntityLiving) NpcAPI.Instance().getIEntity(target);
			this.damage = damage;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>rangedLaunched</code><br />
	 * Triggered when the NPC does a ranged attack by shooting projectiles.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 * <p>Prior to 1.12.2-09May19snapshot, this event is cancelable, and canceling this event prevents the projectiles from being shot.</p>
	 */
	public static class RangedLaunchedEvent extends NpcEvent{
		/**
		 * <p>The entity targeted.</p>
		 */
		public final IEntityLiving target;
		/**
		 * <p>The damage of every projectile shot.</p>
		 * <p>Setting this field has no effect.</p>
		 */
		public float damage;
		/**
		 * <p>The list of the projectiles shot.</p>
		 * <p>Setting this field or modifying the list itself has no effect.</p>
		 *
		 * @since 1.12.2-09May19snapshot
		 */
		public List<IProjectile> projectiles = new ArrayList<IProjectile>();
		
		/**
		 * <p>Creates an instance of the {@link RangedLaunchedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public RangedLaunchedEvent(ICustomNpc npc, LivingEntity target, float damage) {
			super(npc);
			this.target = (IEntityLiving) NpcAPI.Instance().getIEntity(target);
			this.damage = damage;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>damaged</code><br />
	 * Triggered when the NPC is damaged.
	 * </p>
	 * <p>Canceling this event prevents the damage from being applied.</p>
	 */
	@Cancelable
	public static class DamagedEvent extends NpcEvent{
		/**
		 * <p>The damage source.</p>
		 *
		 * <p>Prior to 1.11.2(29oct17), this field is named <code>mcDamageSource</code>, and the type of this field is <code>net.minecraft.util.DamageSource</code> (refactored to {@link DamageSource}.)</p>
		 */
		public final IDamageSource damageSource;
		/**
		 * <p>The ultimate source entity of the damage that causes the death.</p>
		 * <p>For an indirect damage, this is the indirect source of the damage.</p>
		 *
		 * <p>Prior to 1.10.2(21jul17) or 1.11.2(29oct17), the type of this field is <code>noppes.npcs.api.entity.IEntityLivingBase</code> (refactored to {@link IEntityLiving}.)</p>
		 */
		public final IEntity source;
		/**
		 * <p>The amount of damage to be applied.</p>
		 * <p>Setting this field changes the amount of damage.</p>
		 */
		public float damage;
		/**
		 * <p>Whether to clear the target of the NPC after the event.</p>
		 */
		public boolean clearTarget = false;
		
		/**
		 * <p>Creates an instance of the {@link DamagedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public DamagedEvent(ICustomNpc npc, Entity source, float damage, DamageSource damagesource) {
			super(npc);
			this.source = (IEntity) NpcAPI.Instance().getIEntity(source);
			this.damage = damage;
			this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>collide</code><br />
	 * Triggered every 4 game ticks (0.2 seconds expected) when the hitbox of another entity has intersection with the hitbox of the NPC extended by 2 blocks in width and 1 block in height if the NPC is not riding an entity, or with the minimum hitbox that includes the hitboxes of both the NPC and the entity the NPC is riding, extended by 2 blocks in width.
	 * </p>
	 * <p>This event may not be triggered immediately when an entity enters the said hitbox. CNPC checks every 4 game ticks so there can be a delay of maximum 4 game ticks.</p>
	 * <p>If the NPC is not riding an entity, then the checked hitbox is the hitbox of the NPC extended by 2 blocks in width and 1 block in height. For example, if the hitbox of an NPC is 1 block * 1 block wide and 1 block high, then the checked hitbox is 3 blocks * 3 blocks wide and 2 blocks high.</p>
	 * <p>If the NPC is riding an entity, then the checked hitbox is the minimum hitbox that includes the hitboxes of both the NPC and the entity the NPC is riding, extended by 2 blocks in width. For example, if the hitbox of an NPC is 1 block * 1 block wide and 1 block high, and the hitbox of the ridden entity is 2 blocks * 2 blocks wide and 2 blocks high, and the bottom face of the hitbox of an NPC is 2 blocks above the bottom face of the hitbox of the ridden entity, then the checked hitbox is 4 blocks * 4 blocks wide and 3 blocks high.</p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class CollideEvent extends NpcEvent{
		/**
		 * <p>The entity colliding with the NPC.</p>
		 */
		public final IEntity entity;
		
		/**
		 * <p>Creates an instance of the {@link CollideEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public CollideEvent(ICustomNpc npc, Entity entity) {
			super(npc);
			this.entity = (IEntity) NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>timer</code><br />
	 * Triggered when a timer on the NPC is done.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.8.9(29oct16)
	 *
	 * @see ICustomNpc#getTimers()
	 */
	public static class TimerEvent extends NpcEvent{
		/**
		 * <p>The ID of the timer.</p>
		 */
		public final int id;
		
		/**
		 * <p>Creates an instance of the {@link TimerEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TimerEvent(ICustomNpc npc, int id) {
			super(npc);
			this.id = id;
		}
	}
}
