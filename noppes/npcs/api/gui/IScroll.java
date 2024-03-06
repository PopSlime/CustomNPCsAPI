package noppes.npcs.api.gui;

import noppes.npcs.api.function.gui.GuiComponentAction;

public interface IScroll extends ICustomGuiComponent {

    String[] getList();
	/**
	 * <i class="method-chaining"></i>
	 */
    IScroll setList(String[] list);
    
	/**
	 * <i class="method-chaining"></i>
	 */
    IScroll setSorted();

    @Deprecated
    int getDefaultSelection();
	/**
	 * <i class="method-chaining"></i>
	 */
    @Deprecated
    IScroll setDefaultSelection(int defaultSelection);

    int[] getSelection();
	/**
	 * <i class="method-chaining"></i>
	 */
    IScroll setSelection(int... selection);
    String[] getSelectionList();
	/**
	 * <i class="method-chaining"></i>
	 */
    IScroll setSelectionList(String... list);
    boolean hasSelection();

    boolean isMultiSelect();
	/**
	 * <i class="method-chaining"></i>
	 */
    IScroll setMultiSelect(boolean multiSelect);
    
	/**
	 * <i class="method-chaining"></i>
	 */
    IScroll setOnClick(GuiComponentAction<IScroll> onClick);
	/**
	 * <i class="method-chaining"></i>
	 */
    IScroll setOnDoubleClick(GuiComponentAction<IScroll> onDoubleClick);

    boolean getHasSearch();
	/**
	 * <i class="method-chaining"></i>
	 */
    IScroll setHasSearch(boolean bo);
}
