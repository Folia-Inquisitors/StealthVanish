/**
 * Public integration API for StealthVanish.
 *
 * <p>Other plugins should use {@link com.example.stealthvanish.api.VanishAPI}
 * for simple static calls, or load {@link com.example.stealthvanish.api.StealthVanishApi}
 * from Bukkit's ServicesManager when they want a service object. Both paths are
 * designed as respect hooks: they expose invisibility state and safe state
 * changes without pretending the player actually disconnected from the server.
 */
package com.example.stealthvanish.api;
