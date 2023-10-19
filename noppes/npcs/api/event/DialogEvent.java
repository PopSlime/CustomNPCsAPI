package noppes.npcs.api.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Cancelable;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IDialog;
import noppes.npcs.api.handler.data.IDialogOption;

/**
 * <p>Represents an event related to dialogs, and triggered on NPCs.</p>
 * <p>Must be listened in an NPC script.</p>
 */
public class DialogEvent extends NpcEvent {
	/**
	 * <p>The dialog.</p>
	 */
	public final IDialog dialog;
	/**
	 * <p>The player reading the dialog.</p>
	 */
	public final IPlayer player;
	
	/**
	 * <p>Creates an instance of the {@link DialogEvent} class.</p>
	 * <p>Scripters should not use this constructor.</p>
	 */
	public DialogEvent(ICustomNpc npc, Player player, IDialog dialog) {
		super(npc);
		this.dialog = dialog;
		this.player = (IPlayer) NpcAPI.Instance().getIEntity(player);
	}
	
	/**
	 * <p>
	 * Hook function name: <code>dialog</code><br />
	 * Triggered when the dialog is opened.
	 * </p>
	 * <p>Canceling this event prevents the dialog from being opened, and closes the dialog GUI if the player is currently in it.</p>
	 */
	@Cancelable
    public static class OpenEvent extends DialogEvent {
		/**
		 * <p>Creates an instance of the {@link OpenEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public OpenEvent(ICustomNpc npc, Player player, IDialog dialog) {
			super(npc, player, dialog);
		}
    	
    }
	
	/**
	 * <p>
	 * Hook function name: <code>dialogClose</code><br />
	 * Triggered when the dialog is closed.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
    public static class CloseEvent extends DialogEvent {
		/**
		 * <p>Creates an instance of the {@link CloseEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public CloseEvent(ICustomNpc npc, Player player, IDialog dialog) {
			super(npc, player, dialog);
		}
    	
    }
	
	/**
	 * <p>
	 * Hook function name: <code>dialogOption</code><br />
	 * Triggered when an option in the dialog is selected.
	 * </p>
	 * <p>Canceling this event closes the dialog GUI, and prevents the original action for the option from being done.</p>
	 */
	@Cancelable
    public static class OptionEvent extends DialogEvent {
		/**
		 * <p>The dialog option selected.</p>
		 */
    	public final IDialogOption option;
		/**
		 * <p>Creates an instance of the {@link OptionEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public OptionEvent(ICustomNpc npc, Player player, IDialog dialog, IDialogOption option) {
			super(npc, player, dialog);
			this.option = option;
		}
    	
    }
}
