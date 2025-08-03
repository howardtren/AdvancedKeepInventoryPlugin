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