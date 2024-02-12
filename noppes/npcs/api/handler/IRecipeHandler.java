package noppes.npcs.api.handler;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import noppes.npcs.api.handler.data.IRecipe;

public interface IRecipeHandler {
	
	public List<IRecipe> getGlobalList();
	
	public List<IRecipe> getCarpentryList();

	/**
	 * <i class="method-mutating"></i>
	 */
	public IRecipe addRecipe(String name, boolean global, ItemStack result, Object... objects);

	/**
	 * <i class="method-mutating"></i>
	 */
	public IRecipe addRecipe(String name, boolean global, ItemStack result, int width, int height, ItemStack... recipe);

	/**
	 * <i class="method-mutating"></i>
	 */
	public IRecipe delete(int id);
}
