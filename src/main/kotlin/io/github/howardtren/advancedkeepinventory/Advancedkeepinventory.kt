/*
 * Copyright (C) 2025 Howard Tren
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

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
