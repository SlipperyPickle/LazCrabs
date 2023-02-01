package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Skills
import org.powbot.api.rt4.walking.model.Skill
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class DrinkPotion(script: Script) : Leaf<Script>(script, "Drinking Potion") {
    override fun execute() {
        val potions = Inventory.stream().map { it.id() }.toSet()
        val found = Constants.Potions.intersect(potions).isNotEmpty()
        val whatFound = Constants.Potions.intersect(potions).toList()


        if (found) {
            if (Game.tab(Game.Tab.INVENTORY)) {
                Inventory.stream().id(whatFound[0]).first().interact("Drink")
            }
        }

        Constants.ScreenClickTime = Random.nextInt(250_000, 300_000)
        Condition.wait { Skills.level(Skill.Strength) != Skills.realLevel(Skill.Strength) }
    }
}