package noppes.npcs.api.event;

import noppes.npcs.api.entity.IProjectile;

/**
 * <p>Represents an event triggered on projectiles.</p>
 * <p>Must be listened in a script that has called {@link IProjectile#enableEvents()} on the projectile.</p>
 *
 * @since 1.12.2-15mar18snapshot
 */
public class ProjectileEvent extends CustomNPCsEvent {
	/**
	 * <p>The subject projectile of the event.</p>
	 */
	public IProjectile projectile;
	
	/**
	 * <p>Creates an instance of the {@link ProjectileEvent} class.</p>
	 * <p>Scripters should not use this constructor.</p>
	 */
	public ProjectileEvent(IProjectile projectile) {
		this.projectile = projectile;
	}
	
	/**
	 * <p>
	 * Hook function name: <code>projectileTick</code><br />
	 * Triggered every 5 game ticks (0.25 seconds expected.)
	 * </p>
	 * <p>This event is first triggered 5 game ticks after the projectile is shot.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	public static class UpdateEvent extends ProjectileEvent {
		/**
		 * <p>Creates an instance of the {@link UpdateEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public UpdateEvent(IProjectile projectile) {
			super(projectile);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>projectileImpact</code><br />
	 * Triggered when the projectile hits a block or an entity.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	public static class ImpactEvent extends ProjectileEvent {
		/**
		 * <p>The type of the object that the projectile hits.</p>
		 * <p>Possible values are:</p>
		 * <ul>
		 * <li><code>0</code>, the object is an entity;</li>
		 * <li><code>1</code>, the object is a block.</li>
		 * </ul>
		 */
		public final int type;
		/**
		 * <p>The target object that the projectile hits.</p>
		 * <p>The type of this field depends on the value of {@link type}:</p>
		 * <ul>
		 * <li>if {@link type} is <code>0</code>, this is an instance of the {@link noppes.npcs.api.entity.IEntity} interface;</li>
		 * <li>if {@link type} is <code>1</code>, this is an instance of the {@link noppes.npcs.api.block.IBlock} interface.</li>
		 * </ul>
		 */
		public final Object target;
		
		/**
		 * <p>Creates an instance of the {@link ImpactEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public ImpactEvent(IProjectile projectile, int type, Object target) {
			super(projectile);
			this.type = type;
			this.target = target;
		}
	}
}
