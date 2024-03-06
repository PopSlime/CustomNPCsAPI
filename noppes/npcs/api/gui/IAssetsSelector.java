package noppes.npcs.api.gui;

import noppes.npcs.api.function.gui.GuiComponentAction;
import noppes.npcs.api.function.gui.GuiComponentAction;

public interface IAssetsSelector extends ICustomGuiComponent {

    String getSelected();
	/**
	 * <i class="method-chaining"></i>
	 */
    IAssetsSelector setSelected(String selected);

    /**
     * Default: textures
     * @return returns root
     */
    String getRoot();
	/**
	 * <i class="method-chaining"></i>
	 */
    IAssetsSelector setRoot(String root);

    /**
     * Default: png
     * @return png, ogg, json, or whatever you want to filter
     */
    String getFileType();
    /**
     *
     * @param type png, ogg, json, or whatever you want to filter
     *
	 * <i class="method-chaining"></i>
     */
    IAssetsSelector setFileType(String type);
    
	/**
	 * <i class="method-chaining"></i>
	 */
    IAssetsSelector setOnChange(GuiComponentAction<IAssetsSelector> onChange);

    /**
     * Called when an asset is double-clicked
     *
	 * <i class="method-chaining"></i>
     */
    IAssetsSelector setOnPress(GuiComponentAction<IAssetsSelector> onChange);

}
