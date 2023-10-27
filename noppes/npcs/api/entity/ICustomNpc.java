package noppes.npcs.api.entity;

import net.minecraft.world.entity.Mob;
import noppes.npcs.api.ITimers;
import noppes.npcs.api.entity.data.INPCAdvanced;
import noppes.npcs.api.entity.data.INPCAi;
import noppes.npcs.api.entity.data.INPCDisplay;
import noppes.npcs.api.entity.data.INPCInventory;
import noppes.npcs.api.entity.data.INPCJob;
import noppes.npcs.api.entity.data.INPCRole;
import noppes.npcs.api.entity.data.INPCStats;
import noppes.npcs.api.handler.data.IDialog;
import noppes.npcs.api.handler.data.IFaction;
import noppes.npcs.api.item.IItemStack;

/**
 * <p>Represents a custom NPC.</p>
 * <p>The following entity types in CNPC are represented by this interface:</p>
 * <ul>
 * <li><code>customnpcs:customnpc</code></li>
 * <li><code>customnpcs:customnpc64x32</code></li>
 * <li><code>customnpcs:customnpcalex</code></li>
 * <li><code>customnpcs:customnpcclassic</code></li>
 * <li><code>customnpcs:npccrystal</code></li>
 * <li><code>customnpcs:npcdragon</code></li>
 * <li><code>customnpcs:npcgolem</code></li>
 * <li><code>customnpcs:npcpony</code></li>
 * <li><code>customnpcs:npcslime</code></li>
 * </ul>
 */
public interface ICustomNpc<T extends Mob> extends IMob<T> {
	
	/**
	 * <p>Gets the display settings of the custom NPC.</p>
	 * @return The display settings of the custom NPC.
	 */
	public INPCDisplay getDisplay();
	
	/**
	 * <p>Gets the inventory settings of the custom NPC.</p>
	 * @return The inventory settings of the custom NPC.
	 */
	public INPCInventory getInventory();
	
	/**
	 * <p>Gets the stats settings of the custom NPC.</p>
	 * @return The stats settings of the custom NPC.
	 */
	public INPCStats getStats();
	
	/**
	 * <p>Gets the AI settings of the custom NPC.</p>
	 * @return The AI settings of the custom NPC.
	 *
	 * @since 1.8.9(29oct16)
	 */
	public INPCAi getAi();
	
	/**
	 * <p>Gets the advanced settings of the custom NPC.</p>
	 * @return The advanced settings of the custom NPC.
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.9.4(29oct16)
	 * @since 1.8.9(29oct16)
	 */
	public INPCAdvanced getAdvanced();
	
	/**
	 * <p>Gets the faction of the custom NPC.</p>
	 * @return The faction of the custom NPC.
	 *
	 * @since 1.8.9(29oct16)
	 */
	public IFaction getFaction();
	
	/**
	 * <p>Sets the faction of the custom NPC.</p>
	 * @param id The ID of the faction.
	 * @throws noppes.npcs.api.CustomNPCsException The faction with the specified ID is not found.
	 *
	 * @since 1.8.9(29oct16)
	 */
	public void setFaction(int id);
	
	/**
	 * <p>Gets the role settings of the custom NPC.</p>
	 * @return The role settings of the custom NPC.
	 * <p>If the custom NPC does not have a role, this method returns an instance of the {@link INPCRole} interface whose {@link INPCRole#getType()} method returns {@link noppes.npcs.api.constants.RoleType#NONE}.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	public INPCRole getRole();
	
	/**
	 * <p>Gets the job settings of the custom NPC.</p>
	 * @return The job settings of the custom NPC.
	 * <p>If the custom NPC does not have a job, this method returns an instance of the {@link INPCJob} interface whose {@link INPCJob#getType()} method returns {@link noppes.npcs.api.constants.JobType#NONE}.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	public INPCJob getJob();
	
	/**
	 * <p>Gets the timers associated with the custom NPC.</p>
	 * @return The timers associated with the custom NPC.
	 *
	 * @since 1.8.9(29oct16)
	 */
	public ITimers getTimers();
	
	/**
	 * <p>Gets the X coordinate of the home position of the custom NPC.</p>
	 * @return The X coordinate of the home position of the custom NPC.
	 */
	public int getHomeX();
	
	/**
	 * <p>Gets the Y coordinate of the home position of the custom NPC.</p>
	 * @return The Y coordinate of the home position of the custom NPC.
	 */
	public int getHomeY();
	
	/**
	 * <p>Gets the Z coordinate of the home position of the custom NPC.</p>
	 * @return The Z coordinate of the home position of the custom NPC.
	 */
	public int getHomeZ();
	
	/**
	 * <p>Gets the owner entity of the custom NPC.</p>
	 * <p>This method uses the following logic:</p>
	 * <ol>
	 * <li>If the custom NPC is instructed by a scene command to follow an entity, the followed entity is returned.</li>
	 * <li>Otherwise, if the role of the custom NPC is Follower, the owner entity (or <code>null</code> if the custom NPC is not hired) is returned.</li>
	 * <li>Otherwise, if the role of the custom NPC is Companion, the owner entity (or <code>null</code>) is returned.</li>
	 * <li>Otherwise, if the job of the custom NPC is Follower, the followed entity (or <code>null</code> if the custom NPC is not following an entity) is returned.</li>
	 * <li>Otherwise, <code>null</code> is returned.</li>
	 * </ol>
	 * <p>If the custom NPC has a Follower or Companion role and a Follower job at the same time, the followed entity specified in the Follower job settings is never returned.</p>
	 * @return The owner entity of the custom NPC, or <code>null</code>.
	 *
	 * @since 1.12.2-05Jan19snapshot
	 */
	public IEntityLiving getOwner();
	
	/**
	 * <p>Sets the home position of the custom NPC.</p>
	 * @param x The X coordinate of the home position of the custom NPC.
	 * @param y The Y coordinate of the home position of the custom NPC.
	 * @param z The Z coordinate of the home position of the custom NPC.
	 */
	public void setHome(int x, int y, int z);

	/**
	 * <p>Resets the custom NPC.</p>
	 */
	public void reset();
	
	/**
	 * <p>Says a message to the players that can be seen by the custom NPC and whose hitboxes have intersection with the hitbox of the custom NPC extended by 40 blocks in width and height.</p>
	 * <p>If <code>message</code> is an empty string, no message will be sent.</p>
	 * <p>If <code>NpcSpeachTriggersChatEvent</code> is set to <code>true</code> in <code>CustomNpcs.cfg</code>, a {@link net.minecraftforge.event.ServerChatEvent} will be fired before the message is sent, and canceling that event prevents the message from being sent.</p>
	 * @param message A non-null message to be said.
	 */
	public void say(String message);
	
	/**
	 * <p>Says a message to a player.</p>
	 * <p>If <code>message</code> is an empty string, no message will be sent.</p>
	 * <p>If the custom NPC cannot see the player, no message will be sent.</p>
	 * @param player A player to say the message to.
	 * @param message A non-null message to be said.
	 *
	 * @since 1.8.9(29oct16)
	 */
	public void sayTo(IPlayer player, String message);

	/**
	 * <p>Shoots an item stack as a projectile towards a target.</p>
	 * <p><code>accuracy</code> is clamped between 1 (inclusive) and 100 (inclusive.)</p>
	 * @param target The target entity.
	 * @param item The item stack to be shot.
	 * @param accuracy The accuracy of the shot.
	 * @return The shot projectile entity.
	 *
	 * <p>Prior to 1.12.2-07jul18snapshot, this method returns <code>void</code>.</p>
	 *
	 * @throws noppes.npcs.api.CustomNPCsException <code>target</code> or <code>item</code> is <code>null</code>.
	 *
	 * @since 1.8.9(29oct16)
	 */
	public IProjectile shootItem(IEntityLiving target, IItemStack item, int accuracy);
	
	/**
	 * <p>Shoots an item stack as a projectile towards a position.</p>
	 * <p><code>accuracy</code> is clamped between 1 (inclusive) and 100 (inclusive.)</p>
	 * @param x The X coordinate of the target position.
	 * @param y The Y coordinate of the target position.
	 * @param z The Z coordinate of the target position.
	 * @param item The item stack to be shot.
	 * @param accuracy The accuracy of the shot.
	 * @return The shot projectile entity.
	 *
	 * <p>Prior to 1.12.2-07jul18snapshot, this method returns <code>void</code>.</p>
	 *
	 * @throws noppes.npcs.api.CustomNPCsException <code>item</code> is <code>null</code>.
	 *
	 * @since 1.11.2(29oct17)
	 * @since 1.10.2(21jul17)
	 */
	public IProjectile shootItem(double x, double y, double z, IItemStack item, int accuracy);

	/**
	 * <p>Spawns an item entity near a player.</p>
	 * @param player A non-null player entity where the item entity is to be spawned.
	 * @param item A non-null item stack that the item entity carries.
	 *
	 * @since 1.8.9(29oct16)
	 */
	public void giveItem(IPlayer player, IItemStack item);

	/**
	 * <p>Assigns a dialog to a dialog slot of the custom NPC.</p>
	 * <p>If <code>dialog</code> is <code>null</code>, the dialog slot will be cleared.</p>
	 * @param slot The index of the dialog slot.
	 * @param dialog The dialog to be assigned.
	 * @throws noppes.npcs.api.CustomNPCsException <code>slot</code> is less than <code>0</code> or greater than <code>11</code>.
	 *
	 * @since 1.11.2(29oct17)
	 * @since 1.10.2(21jul17)
	 */
	public void setDialog(int slot, IDialog dialog);
	
	/**
	 * <p>Gets the dialog in a dialog slot of the custom NPC.</p>
	 * @param slot The index of the dialog slot.
	 * @return The dialog in the specified dialog slot, or <code>null</code> if the dialog slot is not assigned.
	 * @throws noppes.npcs.api.CustomNPCsException <code>slot</code> is less than <code>0</code> or greater than <code>11</code>.
	 *
	 * @since 1.11.2(29oct17)
	 * @since 1.10.2(21jul17)
	 */
	public IDialog getDialog(int slot);
	
	/**
	 * <p>Sends the spawn data of the custom NPC to the client.</p>
	 *
	 * @since 1.12.2-07jul18snapshot
	 */
	public void updateClient();

	/**
	 * <p>Executes a command as the custom NPC.</p>
	 * <p>On a server, the <code>enable-command-block</code> option in the <code>server.properties</code> must be set to <code>true</code>.</p>
	 * <p>This method cannot execute the commands that require permission level greater than 2 if <code>NpcUseOpCommands</code> is set to <code>false</code> in <code>CustomNpcs.cfg</code>. See the <a href="https://minecraft.wiki/w/Commands#List_and_summary_of_commands">Minecraft Wiki</a> for the permission level for each command.</p>
	 * <p>The commands are run under the UUID <code>c9c843f8-4cb1-4c82-aa61-e264291b7bd6</code> and the name <code>[customnpcs]</code>.</p>
	 * @param command The command to be executed.
	 * @return The command output.
	 *
	 * <p>Prior to 1.11.2(29oct17) or 1.10.2(21jul17), this method returns <code>void</code>.</p>
	 *
	 * @since 1.8.9(29oct16)
	 */
	public String executeCommand(String command);

	/**
	 * <p>Fires a {@link noppes.npcs.api.event.WorldEvent.ScriptTriggerEvent} event.</p>
	 * @param id The ID of the event.
	 * @param arguments The arguments of the event.
	 *
	 * @since 1.16.5.20220607snapshot
	 */
	public void trigger(int id, Object... arguments);
	
}
