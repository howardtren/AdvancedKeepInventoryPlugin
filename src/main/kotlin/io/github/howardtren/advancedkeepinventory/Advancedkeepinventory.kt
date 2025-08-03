package io.github.howardtren.advancedkeepinventory

import io.github.howardtren.advancedkeepinventory.commands.AKICommand
import io.github.howardtren.advancedkeepinventory.listeners.DeathListener
import org.bukkit.plugin.java.JavaPlugin

class Advancedkeepinventory : JavaPlugin() {

    override fun onLoad() {
    }

    override fun onEnable() {
        logger.info("Your test Plugin has been activated")
        getCommand("aki")?.setExecutor(AKICommand())
        server.pluginManager.registerEvents(DeathListener(), this)

    }

    override fun onDisable() {
    }
}
