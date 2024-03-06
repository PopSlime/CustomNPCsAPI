package noppes.npcs.api.gui;

import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;

public interface IEntityDisplay extends ICustomGuiComponent {

    IEntity getEntity();
	/**
	 * <i class="method-chaining"></i>
	 */
    IEntityDisplay setEntity(IEntity entity);

    int getRotation();
	/**
	 * <i class="method-chaining"></i>
	 */
    IEntityDisplay setRotation(int rot);

    float getScale();
	/**
	 * <i class="method-chaining"></i>
	 */
    IEntityDisplay setScale(float scale);

    boolean getBackground();
	/**
	 * <i class="method-chaining"></i>
	 */
    IEntityDisplay setBackground(boolean bo);

}
