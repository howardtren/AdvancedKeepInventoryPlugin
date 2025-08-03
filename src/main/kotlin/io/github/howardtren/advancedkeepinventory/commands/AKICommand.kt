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

package io.github.howardtren.advancedkeepinventory.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.util.StringUtil
import java.util.Objects.toString
import javax.annotation.Nullable



class AKICommand : CommandExecutor, TabCompleter {
    companion object{
        private var toggle : Boolean = true

        fun setToggle(setToggle : Boolean) {
            toggle = setToggle
        }
        fun getToggle() : Boolean {
            return toggle
        }
        var matList : ArrayList<String> = ArrayList<String>()

        fun getMatList() : String {
            return matList.toString()
        }

    }
    
    override fun onCommand(sender: CommandSender, command: Command,
                           label: String, args: Array<out String>): Boolean {
        //base command string for plugin
        if (label.equals("aki")) {
            if (args.size == 0) {
                sender.sendMessage {
                    Component.text(
                        ("<AKI> Usage: /aki <| toggle | include | remove | reset | list |>"),
                        NamedTextColor.GREEN
                    )
                }
                return true
            }
            //toggles the plugin to be on or off
            if (args[0].equals("toggle")) {
                if (getToggle()) {
                    setToggle(false)
                } else {
                    setToggle(true)
                }
                sender.sendMessage {
                    Component.text(
                        "<AKI> Toggle has been set to ${getToggle()}",
                        NamedTextColor.GREEN
                    )
                }
                return true
            }
            //includes Materials into a list that is called from the Death Event
            if (args[0].equals("include")) {
                if(args.size == 1) {
                    sender.sendMessage("§a<AKI> §4You must add something to the list!")
                } else {
                    if (matList.contains(args[1])) {
                        sender.sendMessage("§a<AKI> §4This is already in the list!")
                        return true
                    } else {
                        var loopTest = true
                        for (material in Material.entries) {
                            if (args[1].equals(toString(material))) {
                                matList.add(toString(material))
                                sender.sendMessage {
                                    Component.text(
                                        "<AKI> You have successfully added $material " +
                                                "to the list", NamedTextColor.GREEN
                                    )
                                }
                                loopTest = false
                            }
                        }
                        if (loopTest) {
                            sender.sendMessage("§a<AKI> §4There is no such item!")
                            return true
                        }
                    }
                    return true
                }
            }
            //removes Materials from the list
            if (args[0].equals("remove")) {
                if(args.size == 1) {
                    sender.sendMessage("§a<AKI> §4You must add something to the list!")
                    return true
                }
                if (matList.size == 0) {
                    sender.sendMessage {
                        Component.text(
                            "<AKI> There is nothing in the list to remove!",
                            NamedTextColor.GREEN
                        )
                    }
                    return true
                } else {
                    if (matList.contains(args[1])) {
                        matList.remove(args[1])
                        sender.sendMessage {
                            Component.text(
                                "<AKI> You have successfully removed ${args.get(1)} " +
                                        "from the list!", NamedTextColor.GREEN
                            )
                        }
                        return true
                    } else {
                        sender.sendMessage {
                            Component.text(
                                "<AKI> There is no such item!",
                                NamedTextColor.GREEN
                            )
                        }
                        return true
                    }
                }
                /*
            This adds Items to a list that ADDS
            items to be DROPPED from a player's inventory.
             */
            }
            //removes ALL Materials from the list
            if (args[0].equals("reset")) {
                matList.clear()
                sender.sendMessage("§a<AKI> Items from list have been: §4RESET.")
                return true
                /*
                This resets the list completely, meaning players
                keep ALL items that drop from their inventory.
                 */
            }
            //displays what items are contained in the list
            if (args[0].equals("list")) {
                if (matList.size == 0) {
                    sender.sendMessage("§a<AKI> §4There is nothing in the list!")
                    return true
                } else {
                    sender.sendMessage { Component.text(matList.toString(), NamedTextColor.GREEN) }
                    return true
                }
            }
        }
        return true
    }
    @Nullable
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): List<String> {
        val completions : ArrayList<String> = ArrayList<String>()

        if(label == "aki") {
            if(args.size == 1) {
                StringUtil.copyPartialMatches(
                    args[0],
                    listOf("toggle", "reset", "include", "remove", "list"),
                    completions
                )
                return completions
            }
            if(args.size == 2) {
                if(args[0] == "include") {
                    StringUtil.copyPartialMatches(args[1], Material.entries.map { it.name }, completions)
                    return completions
                }
                if(args[0] == "remove") {
                    StringUtil.copyPartialMatches(args[1], matList.map { it }, completions)
                    return completions
                }
            }
        }
        return listOf()
    }
}

