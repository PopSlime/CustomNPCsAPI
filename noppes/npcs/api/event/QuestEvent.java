package noppes.npcs.api.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Cancelable;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.api.item.IItemStack;

/**
 * <p>Represents an event related to quests, and triggered on players.</p>
 * <p>Must be listened in a player script.</p>
 *
 * <p>Prior to 1.11.2(29oct17), this class directly inherits from the Forge <code>Event</code> class.</p>
 */
public class QuestEvent extends CustomNPCsEvent {
	/**
	 * <p>The subject quest of the event.</p>
	 */
	public final IQuest quest;
	/**
	 * <p>The subject player of the event.</p>
	 */
	public final IPlayer player;
	/**
	 * <p>Creates an instance of the {@link QuestEvent} class.</p>
	 * <p>Scripters should not use this constructor.</p>
	 */
	public QuestEvent(IPlayer player, IQuest quest) {
		this.quest = quest;
		this.player = player;
	}
	
	/**
	 * <p>
	 * Hook function name: <code>questStart</code><br />
	 * Triggered when the quest is started for the player.
	 * </p>
	 * <p>Canceling this event prevents the quest from being started.</p>
	 */
	@Cancelable
	public static class QuestStartEvent extends QuestEvent{
		
		/**
		 * <p>Creates an instance of the {@link QuestStartEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public QuestStartEvent(IPlayer player, IQuest quest) {
			super(player, quest);
		}
		
	}
	
	/**
	 * <p>
	 * Hook function name: <code>questCompleted</code><br />
	 * Triggered when the quest is completed by the player.
	 * </p>
	 * <p>A quest is completed when its objective is met. Before it is turned in, it can be completed multiple times, and thus this event may be triggered multiple times for a single quest.</p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class QuestCompletedEvent extends QuestEvent{
		
		/**
		 * <p>Creates an instance of the {@link QuestCompletedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public QuestCompletedEvent(IPlayer player, IQuest quest) {
			super(player, quest);
		}
		
	}
	
	/**
	 * <p>
	 * Hook function name: <code>questTurnIn</code><br />
	 * Triggered when the quest is turned in by the player.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 */
	public static class QuestTurnedInEvent extends QuestEvent{
		/**
		 * <p>The amount of experiences to be rewarded to the player.</p>
		 * <p>Setting this field will change the rewarded amount of experiences.</p>
		 *
		 * @since 1.11.2(29oct17)
		 * @since 1.10.2(21jul17)
		 */
		public int expReward;		
		/**
		 * <p>The item stacks to be rewarded to the player.</p>
		 * <p>Setting this field will change the rewarded item stacks.</p>
		 *
		 * @since 1.11.2(29oct17)
		 * @since 1.10.2(21jul17)
		 */
		public IItemStack[] itemRewards = new IItemStack[0];
		
		/**
		 * <p>Creates an instance of the {@link QuestTurnedInEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public QuestTurnedInEvent(IPlayer player, IQuest quest) {
			super(player, quest);
		}
		
	}
}
