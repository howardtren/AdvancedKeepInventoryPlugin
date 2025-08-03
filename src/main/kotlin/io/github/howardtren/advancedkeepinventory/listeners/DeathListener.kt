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

package io.github.howardtren.advancedkeepinventory.listeners

import io.github.howardtren.advancedkeepinventory.commands.AKICommand

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack
import java.util.Iterator


class DeathListener : Listener {

    @EventHandler fun onDeath(deathEvent : PlayerDeathEvent) {

        val iterator : Iterator <ItemStack> = deathEvent.drops.iterator() as Iterator<ItemStack>
        if(AKICommand.getToggle()) {
            while (iterator.hasNext()) {
                val drop: ItemStack = iterator.next()
                if(!AKICommand.matList.contains(drop.type.name)) {
                    deathEvent.itemsToKeep.add(drop)
                    iterator.remove()
                }
            }
        }
    }
}