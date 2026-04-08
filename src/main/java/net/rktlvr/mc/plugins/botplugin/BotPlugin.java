package net.rktlvr.mc.plugins.botplugin;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;

public final class BotPlugin extends JavaPlugin {
    private BotHandler botHandler;

    @Override
    public void onEnable() {
        getLogger().info("BotPlugin v1.0-PRERELEASE starting...");

        this.botHandler = new BotHandler(this);

        try {
            botHandler.startServer(1211);
            getLogger().info("Python bridge open on http://127.0.0.1:1211");
        } catch (IOException e) {
            getLogger().severe("CRITICAL: Failed to start Python bridge! Error: " + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        if (botHandler != null) {
            botHandler.stopServer();
        }
        getLogger().info("BotPlugin v0.1-PRERELEASE stopping...");
    }
}
