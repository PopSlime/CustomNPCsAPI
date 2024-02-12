package noppes.npcs.api.handler;

import java.util.List;

import noppes.npcs.api.handler.data.IFaction;

public interface IFactionHandler {
	
	public List<IFaction> list();
	
	/**
	 * <i class="method-mutating"></i>
	 */
	public IFaction delete(int id);
	
	/**
	 * Example: create("Bandits", 0xFF0000)
	 *
	 * <i class="method-mutating"></i>
	 */
	public IFaction create(String name, int color);
	
	public IFaction get(int id);
}
