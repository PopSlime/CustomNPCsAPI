package noppes.npcs.api.event;

import net.minecraftforge.eventbus.api.Cancelable;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.gui.IButton;
import noppes.npcs.api.gui.ICustomGui;
import noppes.npcs.api.gui.IItemSlot;
import noppes.npcs.api.gui.IScroll;
import noppes.npcs.api.item.IItemStack;

/**
 * <p>Represents an event related to custom GUI, and triggered on players.</p>
 * <p>Must be listened in a player script.</p>
 *
 * @since 1.13.2-24Jun20snapshot
 * @since 1.12.2-14Jun20snapshot
 */
public class CustomGuiEvent extends CustomNPCsEvent {


    /**
     * <p>The player interacting with the custom GUI.</p>
     */
	public final IPlayer player;
    /**
     * <p>The custom GUI being interacted.</p>
     */
    public final ICustomGui gui;
    
	/**
	 * <p>Creates an instance of the {@link CustomGuiEvent} class.</p>
	 * <p>Scripters should not use this constructor.</p>
	 */
    public CustomGuiEvent(IPlayer player, ICustomGui gui) {
        this.player = player;
        this.gui = gui;
    }
    
	/**
	 * <p>
	 * Hook function name: <code>customGuiClosed</code><br />
	 * Triggered when the custom GUI is closed.
	 * </p>
	 * <p>This event is not cancelable.</p>
     *
     * @since 1.13.2-24Jun20snapshot
     * @since 1.12.2-14Jun20snapshot
	 */
    public static class CloseEvent extends CustomGuiEvent {
        
		/**
		 * <p>Creates an instance of the {@link CloseEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
        public CloseEvent(IPlayer player, ICustomGui gui) {
            super(player,gui);
        }

    }
    
	/**
	 * <p>
	 * Hook function name: <code>customGuiButton</code><br />
	 * Triggered when a button in the custom GUI is pressed down by the player.
	 * </p>
	 * <p>This event is not cancelable.</p>
     *
     * @since 1.13.2-24Jun20snapshot
     * @since 1.12.2-14Jun20snapshot
	 */
    public static class ButtonEvent extends CustomGuiEvent {
        /**
         * <p>The ID of the button pressed down.</p>
         */
        public final int buttonId;
        /**
         * <p>The button pressed down.</p>
         *
         * @since 1.18.2.20230716snapshot
         */
        public final IButton button;
        
		/**
		 * <p>Creates an instance of the {@link ButtonEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
        public ButtonEvent(IPlayer player, ICustomGui gui, IButton button) {
            super(player,gui);
            this.button = button;
            this.buttonId = button.getID();
        }

    }
    
	/**
	 * <p>
	 * Hook function name: <code>customGuiSlot</code><br />
	 * Triggered when the item stack in an item slot in the custom GUI is changed.
	 * </p>
     * <p>A change to a slot in the player inventory does not trigger this event.</p>
	 * <p>This event is not cancelable.</p>
     *
     * @since 1.13.2-24Jun20snapshot
     * @since 1.12.2-14Jun20snapshot
	 */
    public static class SlotEvent extends CustomGuiEvent {
        /**
         * <p>The ID of the item slot.</p>
         */
        public final int slotId;
        /**
         * <p>The new item stack in the item slot.</p>
         */
        public final IItemStack stack;
        /**
         * <p>The item slot.</p>
         * 
         * @since 1.18.2.20230716snapshot
         */
        public final IItemSlot slot;
        
		/**
		 * <p>Creates an instance of the {@link SlotEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
        public SlotEvent(IPlayer player, ICustomGui gui, IItemSlot slot) {
            super(player,gui);
            this.slotId = slot.getID();
            this.stack = slot.getStack();
            this.slot = slot;
        }

    }
    
	/**
	 * <p>
	 * Hook function name: <code>customGuiSlotClicked</code><br />
	 * Triggered when an item slot in the custom GUI is clicked.
	 * </p>
     * <p>Clicking an item slot in the player inventory also triggers this event.</p>
	 * <p>Canceling this event prevents the item stack in the item slot from being changed.</p>
     *
     * @since 1.16.5-14Nov21snapshot
     * @since 1.12.2-19Jan22snapshot
	 */
	@Cancelable
    public static class SlotClickEvent extends SlotEvent {
        /**
         * <p>The control action that the player has done.</p>
         * <p>Possible values are:</p>
         * <ul>
         * <li><code>0</code>, a keyboard key is pressed or the mouse left button is clicked;</li>
         * <li><code>1</code>, the mouse left button is released as the end of a dragging action or the mouse right button is clicked;</li>
         * <li><code>2</code>, the mouse middle button is clicked;</li>
         * <li><code>5</code>, the mouse right button is released as the end of a dragging action;</li>
         * <li><code>9</code>, the mouse middle button is released as the end of a dragging action.</li>
         * </ul>
         */
        public final int dragType;
        /**
         * <p>The inventory action that the player has done.</p>
         * <p>For all possible values, see {@link net.minecraft.world.inventory.ClickType}. The value of this field is one of the names of the enum constants.</p>
         */
        public final String clickType;
        
		/**
		 * <p>Creates an instance of the {@link SlotClickEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
        public SlotClickEvent(IPlayer player, ICustomGui gui, IItemSlot slot, int dragType, String clickType) {
            super(player, gui, slot);
            this.dragType = dragType;
            this.clickType = clickType;
        }
	}
    
	/**
	 * <p>
	 * Hook function name: <code>customGuiScroll</code><br />
	 * Triggered when an item in a scroll component in the custom GUI is clicked.
	 * </p>
	 * <p>This event is not cancelable.</p>
     *
     * @since 1.13.2-24Jun20snapshot
     * @since 1.12.2-14Jun20snapshot
	 */
    public static class ScrollEvent extends CustomGuiEvent {
        /**
         * <p>The ID of the scroll component clicked.</p>
         */
        public final int scrollId;
        /**
         * <p>A list of all the selected items in the scroll component.</p>
         */
        public final String[] selection;
        /**
         * <p>Whether this event is triggered by a double click.</p>
         */
        public final boolean doubleClick;
        /**
         * <p>The index of the item clicked within the scroll component.</p>
         */
        public final int scrollIndex;
        /**
         * <p>The scroll component.</p>
         * 
         * @since 1.18.2.20230716snapshot
         */
        public final IScroll scroll;
        
		/**
		 * <p>Creates an instance of the {@link ScrollEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
        public ScrollEvent(IPlayer player, ICustomGui gui, IScroll scroll, int scrollIndex, String[] selection, boolean doubleClick) {
            super(player,gui);
            this.scroll = scroll;
            this.scrollId = scroll.getID();
            this.selection = selection;
            this.doubleClick = doubleClick;
            this.scrollIndex = scrollIndex;
        }

    }

}
