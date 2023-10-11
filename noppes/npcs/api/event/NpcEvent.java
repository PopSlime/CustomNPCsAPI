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
 *
 * <p>Must be listened in an NPC script.</p>
 */
public class NpcEvent extends CustomNPCsEvent{
	/**
	 * The subject NPC of the event.
	 */
	public final ICustomNpc npc;

	/**
	 * <p>Creates an instance of the {@link NpcEvent} class.</p>
	 *
	 * <p>Scripters should not use this constructor.</p>
	 */
	public NpcEvent(ICustomNpc npc) {
		this.npc = npc;
	}

	/**
	 * <p>Triggered when the NPC is created, edited, loaded, reset, or respawned.</p>
	 *
	 * <p>The hook function name of this event is <code>init</code>.</p>
	 *
	 * <p>This event is not cancelable.</p>
	 */
	public static class InitEvent extends NpcEvent{
		/**
		 * <p>Creates an instance of the {@link InitEvent} class.</p>
		 *
		 * <p>Scripters should not use this constructor.</p>
		 */
		public InitEvent(ICustomNpc npc) {
			super(npc);
		}
	}
	
	/**
	 * <p>Triggered every 10 game ticks (0.5 seconds expected.)</p>
	 *
	 * <p>The hook function name of this event is <code>tick</code>.</p>
	 *
	 * <p>This event is first triggered 10 game ticks after the NPC is created or loaded. It keeps being triggered until the NPC is despawned or unloaded. Note that it also keeps being triggered after the NPC dies but with respawn enabled.</p>
	 *
	 * <p>This event is not cancelable.</p>
	 */
	public static class UpdateEvent extends NpcEvent{
		/**
		 * <p>Creates an instance of the {@link UpdateEvent} class.</p>
		 *
		 * <p>Scripters should not use this constructor.</p>
		 */
		public UpdateEvent(ICustomNpc npc) {
			super(npc);
		}
	}

	/**
	 * <p>Triggered when the NPC targets an entity.</p>
	 *
	 * <p>The hook function name of this event is <code>target</code>.</p>
	 *
	 * <p>Canceling this event prevents the target of the NPC from being changed and the attack line of the NPC from being said.</p>
	 */
	@Cancelable
	public static class TargetEvent extends NpcEvent{
		/**
		 * <p>The entity targeted.</p>
		 *
		 * <p>Setting this field will cause the NPC to target the set entity (or to lose target if set to <code>null</code>) instead.</p>
		 */
		public IEntityLiving entity;
		/**
		 * <p>Creates an instance of the {@link TargetEvent} class.</p>
		 *
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TargetEvent(ICustomNpc npc, LivingEntity entity) {
			super(npc);
			this.entity = (IEntityLiving) NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>Triggered when the NPC loses its target.</p>
	 *
	 * <p>The hook function name of this event is <code>targetLost</code>.</p>
	 *
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
		 *
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TargetLostEvent(ICustomNpc npc, LivingEntity entity) {
			super(npc);
			this.entity = (IEntityLiving) NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>Triggered when a player interacts (default control: mouse right click) with the NPC.</p>
	 *
	 * <p>The hook function name of this event is <code>interact</code>.</p>
	 *
	 * <p>Canceling this event blocks the original interaction that the NPC would have done.</p>
	 */
	@Cancelable
	public static class InteractEvent extends NpcEvent{
		/**
		 * <p>The player interacting with the NPC.</p>
		 */
		public final IPlayer player;
		/**
		 * <p>Creates an instance of the {@link InteractEvent} class.</p>
		 *
		 * <p>Scripters should not use this constructor.</p>
		 */
		public InteractEvent(ICustomNpc npc, Player player) {
			super(npc);
			this.player = (IPlayer) NpcAPI.Instance().getIEntity(player);
		}
	}
	
	/**
	 * <p>Triggered when the NPC dies.</p>
	 *
	 * <p>The hook function name of this event is <code>died</code>.</p>
	 *
	 * <p>This event is not cancelable.</p>
	 */
	public static class DiedEvent extends NpcEvent{
		/**
		 * <p>The damage source that causes the death.</p>
		 */
		public final IDamageSource damageSource;
		
		/**
		 * <p>The type of the damage source that causes the death.</p>
		 *
		 * <p>Identical to the value returned by calling {@link IDamageSource#getType()} on {@link #damageSource}.</p>
		 */
		public final String type;
		/**
		 * <p>The ultimate source entity of the damage that causes the death.</p>
		 *
		 * <p>For an indirect damage, this is the indirect source of the damage.</p>
		 */
		public final IEntity source;
		
		/**
		 * <p>The items to be dropped from the NPC.</p>
		 *
		 * <p>Setting this field will change the dropped items.</p>
		 */
		public IItemStack[] droppedItems;
		/**
		 * <p>The amount of experiences to be dropped from the NPC.</p>
		 *
		 * <p>Setting this field will change the dropped amount of experiences.</p>
		 */
		public int expDropped;
		/**
		 * <p>The death line to be said by the NPC.</p>
		 *
		 * <p>Setting this field will change the said line (or blocks the line if set to <code>null</code>.)</p>
		 */
		public ILine line;
		/**
		 * <p>Creates an instance of the {@link DiedEvent} class.</p>
		 *
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
	 * <p>Triggered when the NPC kills an entity.</p>
	 *
	 * <p>The hook function name of this event is <code>kill</code>.</p>
	 *
	 * <p>This event is not cancelable.</p>
	 */
	public static class KilledEntityEvent extends NpcEvent{
		/**
		 * <p>The entity killed.</p>
		 */
		public final IEntityLiving entity;
		/**
		 * <p>Creates an instance of the {@link KilledEntityEvent} class.</p>
		 *
		 * <p>Scripters should not use this constructor.</p>
		 */
		public KilledEntityEvent(ICustomNpc npc, LivingEntity entity) {
			super(npc);
			this.entity = (IEntityLiving) NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>Triggered when the NPC is going to do a melee attack.</p>
	 *
	 * <p>The hook function name of this event is <code>meleeAttack</code>.</p>
	 *
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
		 *
		 * <p>Setting this field changes the amount of damage.</p>
		 */
		public float damage;
		
		/**
		 * <p>Creates an instance of the {@link MeleeAttackEvent} class.</p>
		 *
		 * <p>Scripters should not use this constructor.</p>
		 */
		public MeleeAttackEvent(ICustomNpc npc, LivingEntity target, float damage) {
			super(npc);
			this.target = (IEntityLiving) NpcAPI.Instance().getIEntity(target);
			this.damage = damage;
		}
	}
	
	/**
	 * <p>Triggered when the NPC does a ranged attack by shooting projectiles.</p>
	 *
	 * <p>The hook function name of this event is <code>rangedLaunched</code>.</p>
	 *
	 * <p>This event is not cancelable.</p>
	 */
	public static class RangedLaunchedEvent extends NpcEvent{
		/**
		 * <p>The entity targeted.</p>
		 */
		public final IEntityLiving target;
		/**
		 * <p>The damage of every projectile shot.</p>
		 *
		 * <p>Setting this field has no effect.</p>
		 */
		public float damage;
		/**
		 * <p>The list of the projectiles shot.</p>
		 *
		 * <p>Modifying the list itself has no effect.</p>
		 */
		public List<IProjectile> projectiles = new ArrayList<IProjectile>();
		
		/**
		 * <p>Creates an instance of the {@link RangedLaunchedEvent} class.</p>
		 *
		 * <p>Scripters should not use this constructor.</p>
		 */
		public RangedLaunchedEvent(ICustomNpc npc, LivingEntity target, float damage) {
			super(npc);
			this.target = (IEntityLiving) NpcAPI.Instance().getIEntity(target);
			this.damage = damage;
		}
	}
	
	/**
	 * <p>Triggered when the NPC is damaged.</p>
	 *
	 * <p>The hook function name of this event is <code>damaged</code>.</p>
	 *
	 * <p>Canceling this event prevents the damage from being applied.</p>
	 */
	@Cancelable
	public static class DamagedEvent extends NpcEvent{
		/**
		 * <p>The damage source.</p>
		 */
		public final IDamageSource damageSource;
		/**
		 * <p>The ultimate source entity of the damage that causes the death.</p>
		 *
		 * <p>For an indirect damage, this is the indirect source of the damage.</p>
		 */
		public final IEntity source;
		/**
		 * <p>The amount of damage to be applied.</p>
		 *
		 * <p>Setting this field changes the amount of damage.</p>
		 */
		public float damage;
		/**
		 * <p>Whether to clear the target of the NPC after the event.</p>
		 */
		public boolean clearTarget = false;
		
		/**
		 * <p>Creates an instance of the {@link DamagedEvent} class.</p>
		 *
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
	 * <p>Triggered when the NPC collides with another entity.</p>
	 *
	 * <p>The hook function name of this event is <code>collide</code>.</p>
	 *
	 * <p>This event is not cancelable.</p>
	 */
	public static class CollideEvent extends NpcEvent{
		/**
		 * <p>The entity colliding with the NPC.</p>
		 */
		public final IEntity entity;
		
		/**
		 * <p>Creates an instance of the {@link CollideEvent} class.</p>
		 *
		 * <p>Scripters should not use this constructor.</p>
		 */
		public CollideEvent(ICustomNpc npc, Entity entity) {
			super(npc);
			this.entity = (IEntity) NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>Triggered when a timer on the NPC is done.</p>
	 *
	 * <p>The hook function name of this event is <code>timer</code>.</p>
	 *
	 * <p>See {@link ICustomNpc#getTimers()} for more detail.</p>
	 *
	 * <p>This event is not cancelable.</p>
	 */
	public static class TimerEvent extends NpcEvent{
		/**
		 * <p>The ID of the timer.</p>
		 */
		public final int id;
		
		public TimerEvent(ICustomNpc npc, int id) {
			super(npc);
			this.id = id;
		}
	}
}
