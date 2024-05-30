package noppes.npcs.api.entity;

import net.minecraft.world.entity.LivingEntity;
import noppes.npcs.api.entity.data.IMark;
import noppes.npcs.api.item.IItemStack;

/**
 * <p>Represents a <a href="https://minecraft.wiki/w/Entity#Types_of_entities">living entity</a>.</p>
 *
 * <p>Prior to 1.16.5-14Nov21snapshot, this interface is named <code>IEntityLivingBase</code>. It is renamed as <code>net.minecraft.entity.EntityLiving</code> is refactored to <code>net.minecraft.entity.LivingEntity</code> (now refactored to {@link LivingEntity}.)</p>
 * <p>Prior to 1.8.9(29oct16), the interface is not generic.</p>
 */
public interface IEntityLiving<T extends LivingEntity> extends IEntity<T>{

	/**
	 * <p>Gets the health of the living entity.</p>
	 * @return The health of the living entity.
	 */
	public float getHealth();
	
	/**
	 * <p>Sets the health of the living entity.</p>
	 * @param health The health.
	 * <p>The set health is clamped between 0 (inclusive) and the max health of the living entity (inclusive.)</p>
	 */
	public void setHealth(float health);
	
	/**
	 * <p>Gets the computed (actual) max health of the living entity.</p>
	 * @return The computed (actual) max health of the living entity.
	 */
	public float getMaxHealth();
	
	/**
	 * <p>Sets the base max health of the living entity.</p>
	 * <p>This method sets the <em>base</em> max health. The actual max health is computed based on the base max health, modified by the attribute modifiers.</p>
	 * @param health The base max health.
	 * <p>If <code>health</code> is less than 0, this method does nothing.</p>
	 */
	public void setMaxHealth(float health);
	
	/**
	 * <p>Gets whether the living entity has a last hurt-by entity or an attack target.</p>
	 * <p>The "last hurt-by entity" of a living entity E is the last living entity that hurt E and is still being tracked by E.</p>
	 * @return Whether the living entity has a last hurt-by entity or an attack target.
	 */
	public boolean isAttacking();
	
	/**
	 * <p>Sets the attack target (if the living entity is a mob) and the last hurt-by entity of the living entity.</p>
	 * <p>The "last hurt-by entity" of a living entity E is the last living entity that hurt E and is still being tracked by E.</p>
	 * @param living The target and last hurt-by entity.
	 * <p>If <code>living</code> is <code>null</code>, the attack target (if the living entity is a mob) and the last hurt-by entity will be cleared.</p>
	 */
	public void setAttackTarget(IEntityLiving living);
	
	/**
	 * <p>Gets the attack target (if the living entity is a mob) or the last hurt-by entity of the living entity.</p>
	 * <p>The "last hurt-by entity" of a living entity E is the last living entity that hurt E and is still being tracked by E.</p>
	 * @return The attack target or the last hurt-by entity of the living entity.
	 * <p>If the living entity have neither an attack target nor a last hurt-by entity, this method returns <code>null</code>.</p>
	 */
	public IEntityLiving getAttackTarget();
	
	/**
	 * <p>Gets the living entity that the current living entity last attacked.</p>
	 * @return The living entity that the current living entity last attacked.
	 */
	public IEntityLiving getLastAttacked();

	/**
	 * <p>Gets the timestamp when the living entity last attacked.</p>
	 * @return The timestamp when the living entity last attacked.
	 * <p>The returned timestamp can only be used in the context of the current entity and SHOULD NOT be compared with any timestamps obtained from other entities.</p>
	 *
	 * @since 1.12.2_04Mar19snapshot
	 */
	public int getLastAttackedTime();
	
	/**
	 * <p>Determines whether the living entity can see another entity.</p>
	 * @param entity A non-null target entity.
	 * @return Whether the living entity can see the target entity.
	 */
	public boolean canSeeEntity(IEntity entity);
	
	/**
	 * <p>Swings the main hand of the living entity.</p>
	 *
	 * @since 1.9.4(29oct16)
	 */
	public void swingMainhand();
	
	/**
	 * <p>Swings the off-hand of the living entity.</p>
	 *
	 * @since 1.9.4(29oct16)
	 */
	public void swingOffhand();
	
	/**
	 * <p>Gets the item stack in the main hand of the living entity.</p>
	 * @return The item stack in the main hand of the living entity.
	 * <p>If the living entity is not holding an item in its main hand, this method returns an instance of {@link IItemStack} with its {@link IItemStack#isEmpty()} method returns <code>true</code>.</p>
	 *
	 * @since 1.9.4(29oct16)
	 */
	public IItemStack getMainhandItem();
	
	/**
	 * <p>Sets the item stack in the main hand of the living entity.</p>
	 * @param item The item stack to be set to the main hand of the living entity.
	 * <p>If <code>item</code> is <code>null</code>, an empty item stack will be set.</p>
	 *
	 * @since 1.9.4(29oct16)
	 */
	public void setMainhandItem(IItemStack item);
	
	/**
	 * <p>Gets the item stack in the off-hand of the living entity.</p>
	 * @return The item stack in the off-hand of the living entity.
	 * <p>If the living entity is not holding an item in its off-hand, this method returns an instance of {@link IItemStack} with its {@link IItemStack#isEmpty()} method returns <code>true</code>.</p>
	 *
	 * @since 1.9.4(29oct16)
	 */
	public IItemStack getOffhandItem();
	
	/**
	 * <p>Sets the item stack in the off-hand of the living entity.</p>
	 * @param item The item stack to be set to the off-hand of the living entity.
	 * <p>If <code>item</code> is <code>null</code>, an empty item stack will be set.</p>
	 *
	 * @since 1.9.4(29oct16)
	 */
	public void setOffhandItem(IItemStack item);
	
	/**
	 * @deprecated Pulled up to {@link IEntity#getName()} since 1.12.2_04Mar19snapshot
	 * @since 1.11.2(29oct17)
	 */
	public String getName();
	
	/**
	 * @deprecated Pulled up to {@link IEntity#setName(String)} since 1.12.2_04Mar19snapshot
	 * @since 1.11.2(29oct17)
	 */
	public void setName(String name);
	
	/**
	 * <p>Gets the item stack in an armor slot of the living entity.</p>
	 * @param slot An armor slot of the living entity.
	 * <p>Possible values for <code>slot</code> are:</p>
	 * <ul>
	 * <li><code>0</code>, the feet slot;</li>
	 * <li><code>1</code>, the legs slot;</li>
	 * <li><code>2</code>, the chest slot;</li>
	 * <li><code>3</code>, the head slot.</li>
	 * </ul>
	 * @return The item stack in the specified armor slot of the living entity.
	 * <p>If the specified slot is empty, or the living entity does not have armor slots, this method returns an instance of {@link IItemStack} with its {@link IItemStack#isEmpty()} method returns <code>true</code>.</p>
	 * @throws noppes.npcs.api.CustomNPCsException <code>slot</code> is less than <code>0</code> or greater than <code>3</code>.
	 */
	public IItemStack getArmor(int slot);
	
	/**
	 * <p>Sets the item stack in an armor slot of the living entity.</p>
	 * @param slot An armor slot of the living entity.
	 * <p>Possible values for <code>slot</code> are:</p>
	 * <ul>
	 * <li><code>0</code>, the feet slot;</li>
	 * <li><code>1</code>, the legs slot;</li>
	 * <li><code>2</code>, the chest slot;</li>
	 * <li><code>3</code>, the head slot.</li>
	 * </ul>
	 * @param item The item stack to be set to the specified armor slot of the living entity.
	 * <p>If <code>item</code> is <code>null</code>, an empty item stack will be set.</p>
	 * @throws noppes.npcs.api.CustomNPCsException <code>slot</code> is less than <code>0</code> or greater than <code>3</code>.
	 */
	public void setArmor(int slot, IItemStack item);
	
	/**
	 * <p>Gives the living entity a potion effect.</p>
	 * @param effect The ID of the potion effect.
	 * <p>If the given effect ID is invalid, this method does nothing.</p>
	 * <p>See the <a href="https://minecraft.wiki/w/Effect#Effect_list">Minecraft Wiki</a> for the ID of each effect.</p>
	 * @param duration The duration of the potion effect, in seconds for normal potion effects, or in game ticks for instant potion effects.
	 * <p><code>duration</code> is clamped between 0 (inclusive) and 1000000 (inclusive.)</p>
	 * <p>If <code>duration</code> is <code>0</code>, the specified effect will be cleared.</p>
	 * @param strength The amplifier of the potion effect.
	 * <p><code>strength</code> is clamped between 0 (inclusive) and 255 (inclusive.)</p>
	 * @param hideParticles Whether the particles of the potion effect is visible.
	 * <p>Note that setting <code>hideParticles</code> to <code>true</code> makes the particles visible, and setting it to <code>false</code> hides the particles, as opposed to the name of this parameter.</p>
	 */
	public void addPotionEffect(int effect, int duration, int strength, boolean hideParticles);
	
	/**
	 * <p>Clears all the potion effects on the living entity.</p>
	 */
	public void clearPotionEffects();
	
	/**
	 * <p>Gets the current amplifier of a potion effect on the living entity.</p>
	 * @param effect The ID of the potion effect.
	 * @return The current amplifier of the specified potion effect on the living entity.
	 * <p>If the given effect ID is invalid, or the living entity does not have the potion effect, this method returns <code>-1</code>.</p>
	 */
	public int getPotionEffect(int effect);
	
	/**
	 * <p>Adds a mark to the living entity.</p>
	 * @param type The type of the mark.
	 * <p>Possible values for <code>type</code> are:</p>
	 * <ul>
	 * <li><code>0</code>, none;</li>
	 * <li><code>1</code>, the question mark;</li>
	 * <li><code>2</code>, the exclamation mark;</li>
	 * <li><code>3</code>, the pointer mark;</li>
	 * <li><code>4</code>, the skull mark;</li>
	 * <li><code>5</code>, the cross mark;</li>
	 * <li><code>6</code>, the star mark.</li>
	 * </ul>
	 * @return The added mark.
	 *
	 * <i class="method-mutating"></i>
	 *
	 * @since 1.11.2(29oct17)
	 */
	public IMark addMark(int type);
	
	/**
	 * <p>Removes a mark from the living entity.</p>
	 * @param mark The mark to be removed.
	 *
	 * @since 1.11.2(29oct17)
	 */
	public void removeMark(IMark mark);
	
	/**
	 * <p>Gets all the marks on the living entity.</p>
	 * @return An array of all the marks on the living entity.
	 *
	 * @since 1.11.2(29oct17)
	 */
	public IMark[] getMarks();
	
	/**
	 * <p>Determines whether the living entity is a baby.</p>
	 * @return Whether the living entity is a baby.
	 */
	public boolean isChild();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getMCEntity();
	
	/**
	 * <p>Gets the Z component of the travel vector of the living entity.</p>
	 * @return The Z component of the travel vector of the living entity.
	 *
	 * <p>A positive value indicates a forward movement, and a negative value indicates backward movement, relative to the current rotation of the living entity.</p>
	 * <p>If the magnitude of the travel vector is greater than 1, it will be normalized before used to calculate motion.</p>
	 * <p>The final velocity of the living entity is affected by other factors such as friction.</p>
	 *
	 * @since 1.12.2_01sep18snapshot
	 */
	public float getMoveForward();
	
	/**
	 * <p>Sets the Z component of the travel vector of the living entity.</p>
	 * @param move The Z component of the travel vector of the living entity.
	 * <p>A positive value indicates a forward movement, and a negative value indicates backward movement, relative to the current rotation of the living entity.</p>
	 * <p>If the magnitude of the travel vector is greater than 1, it will be normalized before used to calculate motion.</p>
	 * <p>The final velocity of the living entity is affected by other factors such as friction.</p>
	 *
	 * @since 1.12.2_01sep18snapshot
	 */
	public void setMoveForward(float move);
	
	/**
	 * <p>Gets the X component of the travel vector of the living entity.</p>
	 * @return The X component of the travel vector of the living entity.
	 *
	 * <p>A positive value indicates a rightward movement, and a negative value indicates leftward movement, relative to the current rotation of the living entity.</p>
	 * <p>If the magnitude of the travel vector is greater than 1, it will be normalized before used to calculate motion.</p>
	 * <p>The final velocity of the living entity is affected by other factors such as friction.</p>
	 *
	 * @since 1.12.2_01sep18snapshot
	 */
	public float getMoveStrafing();
	
	/**
	 * <p>Sets the X component of the travel vector of the living entity.</p>
	 * @param move The X component of the travel vector of the living entity.
	 * <p>A positive value indicates a rightward movement, and a negative value indicates leftward movement, relative to the current rotation of the living entity.</p>
	 * <p>If the magnitude of the travel vector is greater than 1, it will be normalized before used to calculate motion.</p>
	 * <p>The final velocity of the living entity is affected by other factors such as friction.</p>
	 *
	 * @since 1.12.2_01sep18snapshot
	 */
	public void setMoveStrafing(float move);
	
	/**
	 * <p>Gets the Y component of the travel vector of the living entity.</p>
	 * @return The Y component of the travel vector of the living entity.
	 *
	 * <p>A positive value indicates an upward movement, and a negative value indicates downward movement.</p>
	 * <p>If the magnitude of the travel vector is greater than 1, it will be normalized before used to calculate motion.</p>
	 * <p>The final velocity of the living entity is affected by other factors such as friction.</p>
	 *
	 * @since 1.12.2_01sep18snapshot
	 */
	public float getMoveVertical();
	
	/**
	 * <p>Sets the Y component of the travel vector of the living entity.</p>
	 * @param move The Y component of the travel vector of the living entity.
	 * <p>A positive value indicates an upward movement, and a negative value indicates downward movement.</p>
	 * <p>If the magnitude of the travel vector is greater than 1, it will be normalized before used to calculate motion.</p>
	 * <p>The final velocity of the living entity is affected by other factors such as friction.</p>
	 *
	 * @since 1.12.2_01sep18snapshot
	 */
	public void setMoveVertical(float move);
}
