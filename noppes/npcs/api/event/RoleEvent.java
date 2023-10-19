package noppes.npcs.api.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Cancelable;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.entity.data.IPlayerMail;
import noppes.npcs.api.entity.data.role.IRoleTransporter.ITransportLocation;
import noppes.npcs.api.item.IItemStack;

/**
 * <p>
 * Hook function name: <code>role</code><br />
 * Represents an event related to roles, and triggered on NPCs.
 * </p>
 * <p>Must be listened in an NPC script.</p>
 *
 * <p>Prior to 1.11.2(29oct17), this class directly inherits from the Forge <code>Event</code> class.</p>
 *
 * @since 1.8.9(29oct16)
 */
public class RoleEvent extends CustomNPCsEvent {
	/**
	 * <p>The subject NPC of the event.</p>
	 */
	public final ICustomNpc npc;
	/**
	 * <p>The player interacting with the NPC.</p>
	 */
	public final IPlayer player;
	
	/**
	 * <p>Creates an instance of the {@link RoleEvent} class.</p>
	 * <p>Scripters should not use this constructor.</p>
	 */
	public RoleEvent(Player player, ICustomNpc npc){
		this.npc = npc;
		this.player = (IPlayer) NpcAPI.Instance().getIEntity(player);
	}
	
	/**
	 * <p>
	 * Hook function name: <code>role</code><br />
	 * Triggered when the player uses the transporter.
	 * </p>
	 * <p>Canceling this event prevents the teleportation.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	@Cancelable
	public static class TransporterUseEvent extends RoleEvent{
		/**
		 * <p>The target transport location selected by the player.</p>
		 *
		 * @since 1.10.2(21jul17)
		 */
		public final ITransportLocation location;
		/**
		 * <p>Creates an instance of the {@link TransporterUseEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TransporterUseEvent(Player player, ICustomNpc npc, ITransportLocation location) {
			super(player, npc);
			this.location = location;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>role</code><br />
	 * Triggered when the transporter is unlocked for the player.
	 * </p>
	 * <p>Canceling this event prevents the transporter from being unlocked.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	@Cancelable
	public static class TransporterUnlockedEvent extends RoleEvent{
		
		/**
		 * <p>Creates an instance of the {@link TransporterUnlockedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TransporterUnlockedEvent(Player player, ICustomNpc npc) {
			super(player, npc);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>role</code><br />
	 * Triggered when the mailman sends a mail for the player.
	 * </p>
	 * <p>Canceling this event prevents the mail from being sent.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	@Cancelable
	public static class MailmanEvent extends RoleEvent{
		/**
		 * <p>The mail to be sent by the player.</p>
		 */
		public final IPlayerMail mail;
		
		/**
		 * <p>Creates an instance of the {@link MailmanEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public MailmanEvent(Player player, ICustomNpc npc, IPlayerMail mail) {
			super(player, npc);
			this.mail = mail;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>role</code><br />
	 * Triggered when the follower is hired by the player, or the hire is renewed.
	 * </p>
	 * <p>Canceling this event prevents the follower from being hired, or prevents the hire from being renewed.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	@Cancelable
	public static class FollowerHireEvent extends RoleEvent{
		/**
		 * <p>The number of days of the hire, or the number of days renewed.</p>
		 */
		public int days;
		
		/**
		 * <p>Creates an instance of the {@link FollowerHireEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public FollowerHireEvent(Player player, ICustomNpc npc, int days) {
			super(player, npc);
			this.days = days;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>role</code><br />
	 * Triggered when the hire of the follower ends.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	public static class FollowerFinishedEvent extends RoleEvent{
		
		/**
		 * <p>Creates an instance of the {@link FollowerFinishedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public FollowerFinishedEvent(Player player, ICustomNpc npc) {
			super(player, npc);
		}		
	}
	
	/**
	 * <p>
	 * Hook function name: <code>role</code><br />
	 * Triggered when a trade between the trader and the player succeeds.
	 * </p>
	 * <p>Canceling this event prevents the trade from being done.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	@Cancelable
	public static class TraderEvent extends RoleEvent{
		/**
		 * <p>The item stack sold to the player.</p>
		 */
		public IItemStack sold;
		/**
		 * <p>The first item stack bought from the player, if present.</p>
		 */
		public IItemStack currency1;
		/**
		 * <p>The second item stack bought from the player, if present.</p>
		 */
		public IItemStack currency2;
		
		/**
		 * <p>Creates an instance of the {@link TraderEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TraderEvent(Player player, ICustomNpc npc, IItemStack sold, IItemStack currency1, IItemStack currency2) {
			super(player, npc);
			this.currency1 = currency1.copy();
			this.currency2 = currency2.copy();
			this.sold = sold.copy();
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>role</code><br />
	 * Triggered when a trade between the trader and the player fails.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.10.2(21jul17)
	 */
	public static class TradeFailedEvent extends RoleEvent{
		/**
		 * <p>The item stack to be sold to the player.</p>
		 */
		public final IItemStack sold;
		/**
		 * <p>The first item stack to be bought from the player, if present.</p>
		 */
		public final IItemStack currency1;
		/**
		 * <p>The second item stack to be bought from the player, if present.</p>
		 */
		public final IItemStack currency2;
		/**
		 * <p>This is a useless field.</p>
		 */
		public IItemStack receiving;
		
		/**
		 * <p>Creates an instance of the {@link TradeFailedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TradeFailedEvent(Player player, ICustomNpc npc, IItemStack sold, IItemStack currency1, IItemStack currency2) {
			super(player, npc);
			this.currency1 = currency1.copy();
			this.currency2 = currency2.copy();
			this.sold = sold.copy();
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>role</code><br />
	 * Triggered when a tab in the bank is unlocked.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	public static class BankUnlockedEvent extends RoleEvent{
		/**
		 * <p>The zero-based index of the slot unlocked.</p>
		 */
		public final int slot;
		
		/**
		 * <p>Creates an instance of the {@link BankUnlockedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public BankUnlockedEvent(Player player, ICustomNpc npc, int slot) {
			super(player, npc);
			this.slot = slot;
		}		
	}
	
	/**
	 * <p>
	 * Hook function name: <code>role</code><br />
	 * Triggered when a tab in the bank is upgraded.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	public static class BankUpgradedEvent extends RoleEvent{
		/**
		 * <p>The zero-based index of the slot upgraded.</p>
		 */
		public final int slot;
		
		/**
		 * <p>Creates an instance of the {@link BankUpgradedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public BankUpgradedEvent(Player player, ICustomNpc npc, int slot) {
			super(player, npc);
			this.slot = slot;
		}		
	}
}
