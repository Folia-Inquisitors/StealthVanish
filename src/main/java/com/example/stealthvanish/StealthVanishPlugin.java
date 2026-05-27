package com.example.stealthvanish;

import com.example.stealthvanish.api.StealthVanishApi;
import com.example.stealthvanish.command.StealthCommand;
import com.example.stealthvanish.diagnostic.CompatibilityProbeReport;
import com.example.stealthvanish.diagnostic.CompatibilityProbeService;
import com.example.stealthvanish.diagnostic.DiagnosticRecorder;
import com.example.stealthvanish.listener.PlayerVisibilityListener;
import com.example.stealthvanish.listener.ServerPingPresenceListener;
import com.example.stealthvanish.listener.TabCompletePresenceListener;
import com.example.stealthvanish.presence.PresenceAdapterRegistry;
import com.example.stealthvanish.presence.adapter.BukkitVisibilityAdapter;
import com.example.stealthvanish.presence.adapter.PaperPlayerListAdapter;
import com.example.stealthvanish.respect.PlayerCommandRespectGuard;
import com.example.stealthvanish.storage.VanishStorage;
import com.example.stealthvanish.visibility.VanishService;
import java.util.Objects;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class StealthVanishPlugin extends JavaPlugin {
    private VanishService vanishService;
    private DiagnosticRecorder diagnostics;

    @Override
    public void onEnable() {
        this.diagnostics = new DiagnosticRecorder(this);
        this.diagnostics.startNewRun();
        saveDefaultConfig();

        VanishStorage storage = new VanishStorage(this, this.diagnostics);
        PresenceAdapterRegistry presenceAdapters = new PresenceAdapterRegistry(this, this.diagnostics);
        presenceAdapters.register(new BukkitVisibilityAdapter(this));
        presenceAdapters.register(new PaperPlayerListAdapter());

        this.vanishService = new VanishService(this, storage, presenceAdapters, this.diagnostics);
        this.vanishService.load();

        runStartupCompatibilityAutomation();

        StealthCommand stealthCommand = new StealthCommand(this.vanishService);
        PluginCommand command = Objects.requireNonNull(getCommand("stealth"), "Command /stealth missing from plugin.yml");
        command.setExecutor(stealthCommand);
        command.setTabCompleter(stealthCommand);

        getServer().getPluginManager().registerEvents(new PlayerVisibilityListener(this.vanishService), this);
        getServer().getPluginManager().registerEvents(new ServerPingPresenceListener(this.vanishService), this);
        getServer().getPluginManager().registerEvents(new TabCompletePresenceListener(this.vanishService), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandRespectGuard(this, this.vanishService, this.diagnostics), this);
        getServer().getServicesManager().register(StealthVanishApi.class, this.vanishService, this, ServicePriority.Normal);
        getLogger().info("StealthVanish enabled with " + this.vanishService.vanishedCount() + " persisted vanished player(s).");
        getLogger().info("StealthVanish diagnostics: " + this.diagnostics.debugFile());
    }

    private void runStartupCompatibilityAutomation() {
        CompatibilityProbeService probeService = new CompatibilityProbeService(this, this.vanishService);
        CompatibilityProbeReport report = probeService.runStartupProbe();
        this.diagnostics.recordStartupReport(report);
        getLogger().info("Adaptive presence startup probe: " + report.result() + " (score=" + report.score() + ")");
        for (String line : report.lines()) {
            getLogger().fine("[presence-probe] " + line);
        }
    }

    @Override
    public void onDisable() {
        getServer().getServicesManager().unregisterAll(this);
        if (this.vanishService != null) {
            this.vanishService.saveNow();
        }
    }

    public VanishService vanishService() {
        return this.vanishService;
    }
}
