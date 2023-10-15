package noppes.npcs.api.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.eventbus.api.Cancelable;
import noppes.npcs.api.IContainer;
import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.block.IBlock;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLiving;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IFaction;
import noppes.npcs.api.item.IItemStack;

/**
 * <p>Represents an event triggered on players.</p>
 * <p>Must be listened in a player script.</p>
 *
 * <p>Prior to 1.11.2(29oct17), this class directly inherits from the Forge <code>Event</code> class.</p>
 *
 * @since 1.10.2(21jul17)
 * @since 1.11.2(29oct17)
 */
public class PlayerEvent extends CustomNPCsEvent {
	/**
	 * The subject player of the event.
	 */
	public final IPlayer player;
	
	/**
	 * <p>Creates an instance of the {@link PlayerEvent} class.</p>
	 * <p>Scripters should not use this constructor.</p>
	 */
	public PlayerEvent(IPlayer player) {
		this.player = player;
	}
	
	/**
	 * <p>
	 * Hook function name: <code>init</code><br />
	 * Triggered when the player joins the world, respawns, or travels from the End to Overworld, or <em>at most</em> 10 game ticks (0.5 seconds expected) after the player script is edited.
	 * </p>
	 * <p>After the player script is edited, the first subsequent event triggered on the player will also trigger this event. Because the {@link UpdateEvent} event is triggered every 10 game ticks, this event is triggered at most 10 game ticks after the script is edited.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.11.2(29oct17)
	 */
	public static class InitEvent extends PlayerEvent {
		/**
		 * <p>Creates an instance of the {@link InitEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public InitEvent(IPlayer player) {
			super(player);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>tick</code><br />
	 * Triggered every 10 game ticks (0.5 seconds expected.)
	 * </p>
	 * <p>This event is first triggered immediately when the player joins the world, respawns, or travels across dimensions. Note that it also keeps being triggered after the player dies, until the death animation finishes.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.11.2(29oct17)
	 */
	public static class UpdateEvent extends PlayerEvent {
		/**
		 * <p>Creates an instance of the {@link UpdateEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public UpdateEvent(IPlayer player) {
			super(player);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>interact</code><br />
	 * Triggered when the player interacts (default control: mouse right click) with a block, an entity, or an item.
	 * </p>
	 * <p>This event is not triggered if the player interacts with the air with an empty main hand.</p>
	 * <p>Note that if the player interacts with a block or an entity with an item in their main hand, the interaction with the item will not be triggered.</p>
	 * <p>Canceling this event blocks the original interaction.</p>
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.11.2(29oct17)
	 */
	@Cancelable
	public static class InteractEvent extends PlayerEvent {
		/**
		 * <p>The type of the object that the player is interacting with.</p>
		 * <p>Possible values are:</p>
		 * <ul>
		 * <li><code>0</code>, the object is an item;</li>
		 * <li><code>1</code>, the object is an entity;</li>
		 * <li><code>2</code>, the object is a block.</li>
		 * </ul>
		 */
		public final int type;
		
		/**
		 * <p>The target object that the player is interacting with.</p>
		 * <p>The type of this field depends on the value of {@link type}:</p>
		 * <ul>
		 * <li>if {@link type} is <code>0</code>, this is <code>null</code>;</li>
		 * <li>if {@link type} is <code>1</code>, this is an instance of the {@link IEntity} interface;</li>
		 * <li>if {@link type} is <code>2</code>, this is an instance of the {@link IBlock} interface.</li>
		 * </ul>
		 */
		public final Object target;
		
		/**
		 * <p>Creates an instance of the {@link InteractEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public InteractEvent(IPlayer player, int type, Object target) {
			super(player);
			this.type = type;
			this.target = target;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>attack</code><br />
	 * Triggered when the player starts destroying (default control: mouse left click) a block, attacks (default control: mouse left click) an entity or the air.
	 * </p>
	 * <p>Canceling this event prevents the block from being destroyed or the attack from being done. Canceling this event has no effect if the player is attacking the air.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	@Cancelable
	public static class AttackEvent extends PlayerEvent {
		/**
		 * <p>The type of the object that the player is attacking or destroying.</p>
		 * <p>Possible values are:</p>
		 * <ul>
		 * <li><code>0</code>, the object is the air;</li>
		 * <li><code>1</code>, the object is an entity;</li>
		 * <li><code>2</code>, the object is a block.</li>
		 * </ul>
		 */
		public final int type;
		
		/**
		 * <p>The target object that the player is attacking or destroying.</p>
		 * <p>The type of this field depends on the value of {@link type}:</p>
		 * <ul>
		 * <li>if {@link type} is <code>0</code>, this is <code>null</code>;</li>
		 * <li>if {@link type} is <code>1</code>, this is an instance of the {@link IEntity} interface;</li>
		 * <li>if {@link type} is <code>2</code>, this is an instance of the {@link IBlock} interface.</li>
		 * </ul>
		 */
		public final Object target;
		
		
		/**
		 * <p>The damage source to be applied to the {@link target} entity.</p>
		 * <p>The value of this field is <code>null</code> if {@link type} is <code>0</code> or <code>1</code>.</p>
		 *
		 * @since 1.16.5-14Nov21snapshot
		 * @since 1.12.2-19Jan22snapshot
		 */
		public final IDamageSource damageSource;
		
		/**
		 * <p>Creates an instance of the {@link AttackEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public AttackEvent(IPlayer player, int type, Object target) {
			super(player);
			this.type = type;
			this.target = target;
			this.damageSource = null;
		}
		
		/**
		 * <p>Creates an instance of the {@link AttackEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public AttackEvent(IPlayer player, IEntity target, IDamageSource damageSource) {
			super(player);
			this.type = 1;
			this.target = target;
			this.damageSource = damageSource;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>broken</code><br />
	 * Triggered when the player is done destroying (default control: mouse left click) a block.
	 * </p>
	 * <p>Canceling this event prevents the block from being destroyed.</p>
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.11.2(29oct17)
	 */
	@Cancelable
	public static class BreakEvent extends PlayerEvent {
		/**
		 * <p>The block to be destroyed.</p>
		 */
		public final IBlock block;
		/**
		 * <p>The amount of experiences to be dropped from the block.</p>
		 * <p>Setting this field will change the dropped amount of experiences.</p>
		 */
		public int exp;
		
		/**
		 * <p>Creates an instance of the {@link BreakEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public BreakEvent(IPlayer player, IBlock block, int exp) {
			super(player);
			this.block = block;
			this.exp = exp;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>toss</code><br />
	 * Triggered when the player throws an item stack.
	 * </p>
	 * <p>Canceling this event prevents the item entity from being spawned, but the item will not be returned to the player.</p>
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.11.2(29oct17)
	 */
	@Cancelable
	public static class TossEvent extends PlayerEvent {
		/**
		 * <p>The item stack to be thrown.</p>
		 */
		public final IItemStack item;
		
		/**
		 * <p>Creates an instance of the {@link TossEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TossEvent(IPlayer player, IItemStack item) {
			super(player);
			this.item = item;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>pickUp</code><br />
	 * Triggered when the player picks up an item stack.
	 * </p>
	 * <p>Canceling this event prevents the item from being picked up.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	@Cancelable
	public static class PickUpEvent extends PlayerEvent {
		/**
		 * <p>The item stack to be picked up.</p>
		 */
		public final IItemStack item;
		
		/**
		 * <p>Creates an instance of the {@link PickUpEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public PickUpEvent(IPlayer player, IItemStack item) {
			super(player);
			this.item = item;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>containerOpen</code><br />
	 * Triggered when the player opens a container.
	 * </p>
	 * <p>Unlike the {@link ContainerClosed} event, this event is not triggered when the player opens their inventory.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.11.2(29oct17)
	 */
	public static class ContainerOpen extends PlayerEvent {
		/**
		 * <p>The container opened.</p>
		 */
		public final IContainer container;
		
		/**
		 * <p>Creates an instance of the {@link ContainerOpen} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public ContainerOpen(IPlayer player, IContainer container) {
			super(player);
			this.container = container;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>containerClosed</code><br />
	 * Triggered when the player closes a container.
	 * </p>
	 * <p>Unlike the {@link ContainerOpen} event, this event is triggered when the player closes their inventory.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.11.2(29oct17)
	 */
	public static class ContainerClosed extends PlayerEvent {
		/**
		 * <p>The container closed.</p>
		 */
		public final IContainer container;
		
		/**
		 * <p>Creates an instance of the {@link ContainerClosed} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public ContainerClosed(IPlayer player, IContainer container) {
			super(player);
			this.container = container;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>damagedEntity</code><br />
	 * Triggered when the player deals damage to an entity.
	 * </p>
	 * <p>Canceling this event prevents the damage from being applied.</p>
	 *
	 * @since 1.11.2(29oct17)
	 */
	@Cancelable
	public static class DamagedEntityEvent extends PlayerEvent {
		/**
		 * <p>The damage source.</p>
		 */
		public final IDamageSource damageSource;
		
		/**
		 * <p>The entity to be damaged.</p>
		 */
		public final IEntity target;
		/**
		 * <p>The amount of damage to be applied.</p>
		 * <p>Setting this field changes the amount of damage.</p>
		 */
		public float damage;
		
		/**
		 * <p>Creates an instance of the {@link DamagedEntityEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public DamagedEntityEvent(IPlayer player, Entity target, float damage, DamageSource damagesource) {
			super(player);
			this.target = NpcAPI.Instance().getIEntity(target);
			this.damage = damage;
			this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>rangedLaunched</code><br />
	 * Triggered when the player stops charging a bow or shoots with a crossbow.
	 * </p>
	 * <p>This event is triggered even if the bow is not charged enough for an arrow to be shot.</p>
	 * <p>This event is not triggered if the player stops charging a bow by switching to another hotbar slot.</p>
	 * <p>Canceling this event prevents the arrow from being shot, if one is to be shot.</p>
	 *
	 * @since 1.11.2(29oct17)
	 */
	@Cancelable
	public static class RangedLaunchedEvent extends PlayerEvent {
		
		/**
		 * <p>Creates an instance of the {@link RangedLaunchedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public RangedLaunchedEvent(IPlayer player) {
			super(player);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>died</code><br />
	 * Triggered when the player dies.
	 * </p>
	 * <p>This event is cancelable, but canceling this event has no effect.</p>
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.11.2(29oct17)
	 */
	@Cancelable
	public static class DiedEvent extends PlayerEvent {
		/**
		 * <p>The damage source that causes the death.</p>
		 *
		 * <p>Prior to 1.11.2(29oct17), this field is named <code>mcDamageSource</code>, and the type of this field is <code>net.minecraft.util.DamageSource</code> (refactored to {@link DamageSource}.)</p>
		 */
		public final IDamageSource damageSource;
		
		/**
		 * <p>The type of the damage source that causes the death.</p>
		 * <p>Identical to the value returned by calling {@link IDamageSource#getType()} on {@link #damageSource}.</p>
		 */
		public final String type;
		/**
		 * <p>The ultimate source entity of the damage that causes the death.</p>
		 * <p>For an indirect damage, this is the indirect source of the damage.</p>
		 */
		public final IEntity source;
		
		/**
		 * <p>Creates an instance of the {@link DiedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public DiedEvent(IPlayer player, DamageSource damagesource, Entity entity) {
			super(player);
			this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
			type = damagesource.msgId;
			this.source = NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>kill</code><br />
	 * Triggered when the player kills an entity.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.11.2(29oct17)
	 */
	public static class KilledEntityEvent extends PlayerEvent {
		/**
		 * <p>The entity killed.</p>
		 */
		public final IEntityLiving entity;
		
		/**
		 * <p>Creates an instance of the {@link KilledEntityEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public KilledEntityEvent(IPlayer player, LivingEntity entity) {
			super(player);
			this.entity = (IEntityLiving) NpcAPI.Instance().getIEntity(entity);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>damaged</code><br />
	 * Triggered when the player is damaged.
	 * </p>
	 * <p>Canceling this event prevents the damage from being applied.</p>
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.11.2(29oct17)
	 */
	@Cancelable
	public static class DamagedEvent extends PlayerEvent {
		/**
		 * <p>The damage source.</p>
		 *
		 * <p>Prior to 1.11.2(29oct17), this field is named <code>mcDamageSource</code>, and the type of this field is <code>net.minecraft.util.DamageSource</code> (refactored to {@link DamageSource}.)</p>
		 */
		public final IDamageSource damageSource;
		/**
		 * <p>The ultimate source entity of the damage that causes the death.</p>
		 * <p>For an indirect damage, this is the indirect source of the damage.</p>
		 */
		public final IEntity source;
		/**
		 * <p>The amount of damage to be applied.</p>
		 * <p>Setting this field changes the amount of damage.</p>
		 */
		public float damage;
		/**
		 * <p>This is a useless field.</p>
		 */
		public boolean clearTarget = false;
		
		/**
		 * <p>Creates an instance of the {@link DamagedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public DamagedEvent(IPlayer player, Entity source, float damage, DamageSource damagesource) {
			super(player);
			this.source = (IEntity) NpcAPI.Instance().getIEntity(source);
			this.damage = damage;
			this.damageSource = NpcAPI.Instance().getIDamageSource(damagesource);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>timer</code><br />
	 * Triggered when a timer on the player is done.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.10.2(21jul17)
	 * @since 1.11.2(29oct17)
	 *
	 * @see IPlayer#getTimers()
	 */
	public static class TimerEvent extends PlayerEvent {
		/**
		 * <p>The ID of the timer.</p>
		 */
		public final int id;
		
		/**
		 * <p>Creates an instance of the {@link TimerEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public TimerEvent(IPlayer player, int id) {
			super(player);
			this.id = id;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>login</code><br />
	 * Triggered when the player logs in.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.11.2(29oct17)
	 */
	public static class LoginEvent extends PlayerEvent {
		/**
		 * <p>Creates an instance of the {@link LoginEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public LoginEvent(IPlayer player) {
			super(player);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>logout</code><br />
	 * Triggered when the player logs out.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.11.2(29oct17)
	 */
	public static class LogoutEvent extends PlayerEvent {
		/**
		 * <p>Creates an instance of the {@link LogoutEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public LogoutEvent(IPlayer player) {
			super(player);
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>levelUp</code><br />
	 * Triggered when the level of the player changes.
	 * </p>
	 * <p>This event is not triggered when the player loses their experiences because of death.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.12.2-25apr18snapshot
	 */
	public static class LevelUpEvent extends PlayerEvent {
		/**
		 * <p>The amount of level decreasing. A negative value indicates that the level increases.</p>
		 *
		 * <p>Prior to 1.12.2-07sep18snapshot, this field is private.</p>
		 */
		public final int change;
		
		/**
		 * <p>Creates an instance of the {@link LevelUpEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public LevelUpEvent(IPlayer player, int change) {
			super(player);
			this.change = change;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>keyPressed</code><br />
	 * Triggered when the player presses down a key on the keyboard.
	 * </p>
	 * <p>Prior to 1.16.5.20220506snapshot, this event is triggered when the player releases a key, only if the key has been held for less than 0.5 second, and no other keys (except the ignored keys) were pressed down while it is being held, and no GUI is opened by pressing the key. The ignored keys are the two Control keys, the two Shift keys, the two Menu (Alt) keys, and the two Meta (Windows) keys. They also does not trigger this event.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.12.2-07sep18snapshot
	 */
	public static class KeyPressedEvent extends PlayerEvent {
		/**
		 * <p>The key pressed down.</p>
		 * <p>For Minecraft version 1.13 and above, possible values are:</p>
		 * <table class="striped">
		 * <caption>Key codes (1.13+)</caption>
		 * <thead>
		 * <tr><th>ID</th><th>Key name</th></tr>
		 * </thead>
		 * <tbody>
		 * <tr><td>-1</td><td>Not bound</td></tr>
		 * <tr><td>48</td><td>0</td></tr>
		 * <tr><td>49</td><td>1</td></tr>
		 * <tr><td>50</td><td>2</td></tr>
		 * <tr><td>51</td><td>3</td></tr>
		 * <tr><td>52</td><td>4</td></tr>
		 * <tr><td>53</td><td>5</td></tr>
		 * <tr><td>54</td><td>6</td></tr>
		 * <tr><td>55</td><td>7</td></tr>
		 * <tr><td>56</td><td>8</td></tr>
		 * <tr><td>57</td><td>9</td></tr>
		 * <tr><td>65</td><td>A</td></tr>
		 * <tr><td>66</td><td>B</td></tr>
		 * <tr><td>67</td><td>C</td></tr>
		 * <tr><td>68</td><td>D</td></tr>
		 * <tr><td>69</td><td>E</td></tr>
		 * <tr><td>70</td><td>F</td></tr>
		 * <tr><td>71</td><td>G</td></tr>
		 * <tr><td>72</td><td>H</td></tr>
		 * <tr><td>73</td><td>I</td></tr>
		 * <tr><td>74</td><td>J</td></tr>
		 * <tr><td>75</td><td>K</td></tr>
		 * <tr><td>76</td><td>L</td></tr>
		 * <tr><td>77</td><td>M</td></tr>
		 * <tr><td>78</td><td>N</td></tr>
		 * <tr><td>79</td><td>O</td></tr>
		 * <tr><td>80</td><td>P</td></tr>
		 * <tr><td>81</td><td>Q</td></tr>
		 * <tr><td>82</td><td>R</td></tr>
		 * <tr><td>83</td><td>S</td></tr>
		 * <tr><td>84</td><td>T</td></tr>
		 * <tr><td>85</td><td>U</td></tr>
		 * <tr><td>86</td><td>V</td></tr>
		 * <tr><td>87</td><td>W</td></tr>
		 * <tr><td>88</td><td>X</td></tr>
		 * <tr><td>89</td><td>Y</td></tr>
		 * <tr><td>90</td><td>Z</td></tr>
		 * <tr><td>290</td><td>F1</td></tr>
		 * <tr><td>291</td><td>F2</td></tr>
		 * <tr><td>292</td><td>F3</td></tr>
		 * <tr><td>293</td><td>F4</td></tr>
		 * <tr><td>294</td><td>F5</td></tr>
		 * <tr><td>295</td><td>F6</td></tr>
		 * <tr><td>296</td><td>F7</td></tr>
		 * <tr><td>297</td><td>F8</td></tr>
		 * <tr><td>298</td><td>F9</td></tr>
		 * <tr><td>299</td><td>F10</td></tr>
		 * <tr><td>300</td><td>F11</td></tr>
		 * <tr><td>301</td><td>F12</td></tr>
		 * <tr><td>302</td><td>F13</td></tr>
		 * <tr><td>303</td><td>F14</td></tr>
		 * <tr><td>304</td><td>F15</td></tr>
		 * <tr><td>305</td><td>F16</td></tr>
		 * <tr><td>306</td><td>F17</td></tr>
		 * <tr><td>307</td><td>F18</td></tr>
		 * <tr><td>308</td><td>F19</td></tr>
		 * <tr><td>309</td><td>F20</td></tr>
		 * <tr><td>310</td><td>F21</td></tr>
		 * <tr><td>311</td><td>F22</td></tr>
		 * <tr><td>312</td><td>F23</td></tr>
		 * <tr><td>313</td><td>F24</td></tr>
		 * <tr><td>314</td><td>F25</td></tr>
		 * <tr><td>282</td><td>Num Lock</td></tr>
		 * <tr><td>320</td><td>Keypad 0</td></tr>
		 * <tr><td>321</td><td>Keypad 1</td></tr>
		 * <tr><td>322</td><td>Keypad 2</td></tr>
		 * <tr><td>323</td><td>Keypad 3</td></tr>
		 * <tr><td>324</td><td>Keypad 4</td></tr>
		 * <tr><td>325</td><td>Keypad 5</td></tr>
		 * <tr><td>326</td><td>Keypad 6</td></tr>
		 * <tr><td>327</td><td>Keypad 7</td></tr>
		 * <tr><td>328</td><td>Keypad 8</td></tr>
		 * <tr><td>329</td><td>Keypad 9</td></tr>
		 * <tr><td>334</td><td>Keypad +</td></tr>
		 * <tr><td>330</td><td>Keypad Decimal</td></tr>
		 * <tr><td>335</td><td>Keypad Enter</td></tr>
		 * <tr><td>336</td><td>Keypad =</td></tr>
		 * <tr><td>332</td><td>Keypad *</td></tr>
		 * <tr><td>331</td><td>Keypad /</td></tr>
		 * <tr><td>333</td><td>Keypad -</td></tr>
		 * <tr><td>264</td><td>Down Arrow</td></tr>
		 * <tr><td>263</td><td>Left Arrow</td></tr>
		 * <tr><td>262</td><td>Right Arrow</td></tr>
		 * <tr><td>265</td><td>Up Arrow</td></tr>
		 * <tr><td>39</td><td>'</td></tr>
		 * <tr><td>92</td><td>\</td></tr>
		 * <tr><td>44</td><td>,</td></tr>
		 * <tr><td>61</td><td>=</td></tr>
		 * <tr><td>96</td><td>`</td></tr>
		 * <tr><td>91</td><td>[</td></tr>
		 * <tr><td>45</td><td>-</td></tr>
		 * <tr><td>46</td><td>.</td></tr>
		 * <tr><td>93</td><td>]</td></tr>
		 * <tr><td>59</td><td>;</td></tr>
		 * <tr><td>47</td><td>/</td></tr>
		 * <tr><td>32</td><td>Space</td></tr>
		 * <tr><td>258</td><td>Tab</td></tr>
		 * <tr><td>342</td><td>Left Alt</td></tr>
		 * <tr><td>341</td><td>Left Control</td></tr>
		 * <tr><td>340</td><td>Left Shift</td></tr>
		 * <tr><td>343</td><td>Left Win</td></tr>
		 * <tr><td>346</td><td>Right Alt</td></tr>
		 * <tr><td>345</td><td>Right Control</td></tr>
		 * <tr><td>344</td><td>Right Shift</td></tr>
		 * <tr><td>347</td><td>Right Win</td></tr>
		 * <tr><td>257</td><td>Enter</td></tr>
		 * <tr><td>256</td><td>Escape</td></tr>
		 * <tr><td>259</td><td>Backspace</td></tr>
		 * <tr><td>261</td><td>Delete</td></tr>
		 * <tr><td>269</td><td>End</td></tr>
		 * <tr><td>268</td><td>Home</td></tr>
		 * <tr><td>260</td><td>Insert</td></tr>
		 * <tr><td>267</td><td>Page Down</td></tr>
		 * <tr><td>266</td><td>Page Up</td></tr>
		 * <tr><td>280</td><td>Caps Lock</td></tr>
		 * <tr><td>284</td><td>Pause</td></tr>
		 * <tr><td>281</td><td>Scroll Lock</td></tr>
		 * <tr><td>348</td><td>Menu</td></tr>
		 * <tr><td>283</td><td>Print Screen</td></tr>
		 * <tr><td>161</td><td>World 1</td></tr>
		 * <tr><td>162</td><td>World 2</td></tr>
		 * </tbody>
		 * </table>
		 * <p>For Minecraft version 1.12.2 and below, check <a href="https://minecraft.wiki/w/Key_codes#Before_1.13">Minecraft Wiki</a> for possible values.</p>
		 */
		public final int key;
		/**
		 * <p>Whether the Control key is held down.</p>
		 */
		public final boolean isCtrlPressed;
		/**
		 * <p>Whether the Menu (Alt) key is held down.</p>
		 */
		public final boolean isAltPressed;
		/**
		 * <p>Whether the Shift key is held down.</p>
		 */
		public final boolean isShiftPressed;
		/**
		 * <p>Whether the Meta (Windows or Apple) key is held down.</p>
		 */
		public final boolean isMetaPressed;
		/**
		 * <p>The class name of the GUI currently opened, or an empty string if none is opened.</p>
		 *
		 * @since 1.16.5.20220506snapshot
		 */
		public final String openGui;
		
		/**
		 * <p>Creates an instance of the {@link KeyPressedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public KeyPressedEvent(IPlayer player, int key, boolean isCtrlPressed, boolean isAltPressed, boolean isShiftPressed, boolean isMetaPressed, String openGui) {
			super(player);
			this.key = key;
			this.isCtrlPressed = isCtrlPressed;
			this.isAltPressed = isAltPressed;
			this.isShiftPressed = isShiftPressed;
			this.isMetaPressed = isMetaPressed;
			this.openGui = openGui;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>keyReleased</code><br />
	 * Triggered when the player releases a key on the keyboard.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.16.5.20220506snapshot
	 */
	public static class KeyReleasedEvent extends PlayerEvent {
		/**
		 * <p>The key released.</p>
		 * <p>For Minecraft version 1.13 and above, check {@link KeyPressedEvent#key} for possible values.</p>
		 * <p>For Minecraft version 1.12.2 and below, check <a href="https://minecraft.wiki/w/Key_codes#Before_1.13">Minecraft Wiki</a> for possible values.</p>
		 */
		public final int key;
		/**
		 * <p>Whether the Control key is held down.</p>
		 */
		public final boolean isCtrlPressed;
		/**
		 * <p>Whether the Menu (Alt) key is held down.</p>
		 */
		public final boolean isAltPressed;
		/**
		 * <p>Whether the Shift key is held down.</p>
		 */
		public final boolean isShiftPressed;
		/**
		 * <p>Whether the Meta (Windows or Apple) key is held down.</p>
		 */
		public final boolean isMetaPressed;
		/**
		 * <p>The class name of the GUI currently opened, or an empty string if none is opened.</p>
		 *
		 * @since 1.16.5.20220506snapshot
		 */
		public final String openGui;
		
		/**
		 * <p>Creates an instance of the {@link KeyReleasedEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public KeyReleasedEvent(IPlayer player, int key, boolean isCtrlPressed, boolean isAltPressed, boolean isShiftPressed, boolean isMetaPressed, String openGui) {
			super(player);
			this.key = key;
			this.isCtrlPressed = isCtrlPressed;
			this.isAltPressed = isAltPressed;
			this.isShiftPressed = isShiftPressed;
			this.isMetaPressed = isMetaPressed;
			this.openGui = openGui;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>chat</code><br />
	 * Triggered when the player sends a message in the chat.
	 * </p>
	 * <p>This event is not triggered by messages sent with the <code>/say</code> command.</p>
	 * <p>Canceling this event prevents the message from being sent.</p>
	 *
	 * @since 1.11.2(29oct17)
	 */
	@Cancelable
	public static class ChatEvent extends PlayerEvent {
		/**
		 * <p>The message to be sent.</p>
		 */
		public String message;
		
		/**
		 * <p>Creates an instance of the {@link ChatEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public ChatEvent(IPlayer player, String message) {
			super(player);
			this.message = message;
		}
	}
	
	/**
	 * <p>
	 * Hook function name: <code>factionUpdate</code><br />
	 * Triggered when the faction point of the player changes or is initialized.
	 * </p>
	 * <p>If the faction point is changed before it is initialized, this event will be triggered twice, first for initialization, and later for the change.</p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.12.2-15mar18snapshot
	 */
	public static class FactionUpdateEvent extends PlayerEvent {
		/**
		 * <p>The faction.</p>
		 */
		public final IFaction faction;
		/**
		 * <p>The amount of faction point increased.</p>
		 * <p>If the faction point is being initialized, this is the default faction point to the faction, and setting this field changes the faction point applied.</p>
		 * <p>If the faction point is being changed, setting this field has no effect.</p>
		 */
		public int points;
		
		/**
		 * <p>Whether the faction point is being initialized.</p>
		 */
		public boolean init;
		
		/**
		 * <p>Creates an instance of the {@link FactionUpdateEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public FactionUpdateEvent(IPlayer player, IFaction faction, int points, boolean init) {
			super(player);
			this.faction = faction;
			this.points = points;
			this.init = init;
		}
		
	}

	/**
	 * <p>
	 * Hook function name: <code>playSound</code><br />
	 * Triggered when a sound is played to the player.
	 * </p>
	 * <p>This event is not cancelable.</p>
	 *
	 * @since 1.16.5.20220514snapshot
	 */
	public static class PlaySoundEvent extends PlayerEvent {
		/**
		 * <p>The resource location of the sound.</p>
		 */
		public final String sound;
		/**
		 * <p>The category of the sound.</p>
		 */
		public final String category;
		/**
		 * <p>Whether the sound is looping.</p>
		 */
		public final boolean looping;
		
		/**
		 * <p>Creates an instance of the {@link PlaySoundEvent} class.</p>
		 * <p>Scripters should not use this constructor.</p>
		 */
		public PlaySoundEvent(IPlayer player, String sound, String category, boolean looping) {
			super(player);
			this.sound = sound;
			this.category = category;
			this.looping = looping;
		}
	}
}
