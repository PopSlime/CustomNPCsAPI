package noppes.npcs.api.entity;

import net.minecraft.world.entity.Mob;
import noppes.npcs.api.IPos;

/**
 * <p>Represents a <a href="https://minecraft.wiki/w/Mob">mob</a>.</p>
 *
 * <p>Prior to 1.16.5-14Nov21snapshot, this interface is named <code>IEntityLiving</code>. It is renamed as <code>net.minecraft.entity.EntityLiving</code> is refactored to <code>net.minecraft.entity.MobEntity</code> (now refactored to {@link Mob}.)</p>
 * <p>Prior to 1.8.9(29oct16), the interface is not generic.</p>
 */
public interface IMob<T extends Mob> extends IEntityLiving<T> {

	/**
	 * <p>Gets whether the mob is navigating.</p>
	 * @return Whether the mob is navigating.
	 */
	public boolean isNavigating();
	
	/**
	 * <p>Stops the current navigation on the mob.</p>
	 * <p>Does nothing if the mob is not navigating.</p>
	 */
	public void clearNavigation();
	
	/**
	 * <p>Starts the navigation towards a target position.</p>
	 * <p>The current navigation is stopped if present.</p>
	 * @param x The X coordinate of the target position.
	 * @param y The Y coordinate of the target position.
	 * @param z The Z coordinate of the target position.
	 * @param speed The speed multiplier.
	 * <p>The movement speed during the navigation is modified to the <code>generic.movement_speed</code> attribute value of the mob multiplied by the value of <code>speed</code> parameter.</p>
	 */
	public void navigateTo(double x, double y, double z, double speed);

	/**
	 * <p>Makes the mob jump.</p>
	 *
	 * @since 1.10.2(21jul17)
	 */
	public void jump();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getMCEntity();
	
	/**
	 * <p>Gets the destination position of the current navigation.</p>
	 * <p>The destination position may be a position different from, yet nearby, the target position specified in a {@link #navigateTo(double,double,double,double)} call.</p>
	 * @return The destination position of the current navigation, or <code>null</code> if the mob is not navigating or there is no destination position.
	 *
	 * @since 1.12.2_05Jan19snapshot
	 */
	public IPos getNavigationPath();
	
}
