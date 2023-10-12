/**
 * <p>This package contains event classes whose instances are dispatched to the corresponding hook functions (if present) in CNPC scripts.</p>
 * <p>These classes represent different events that a script may listen to, e.g. when a player is initialized, an NPC is damaged, etc. Most scripts are written in a way that they listen to specific events, and then do specific actions.</p>
 * <p>To listen to an event in a script, a function with the hook function name of the corresponding event class and a parameter (usually named <code>event</code> or <code>e</code>) is written in the script. When the event is triggered, this function will be called with its only parameter set to an instance of the event class. For example, to listen to the {@link NpcEvent.InitEvent} event in an NPC script:</p>
 *<pre>
 *function init(e) {
 *
 *}
 *</pre>
 * <p>In this example, <code>init</code> is the hook function name of the {@link NpcEvent.InitEvent} event. When the scripted NPC is initialized, this function will be called, and its parameter <code>e</code> will be set to an instance of the {@link NpcEvent.InitEvent} event class, which contains information about this event.</p>
 * <p>For the hook function name of each events, check its description.</p>
 * <p>Event classes with different "prefixes" are triggered in different scriptable objects. See their descriptions for details.</p>
 */
package noppes.npcs.api.event;
