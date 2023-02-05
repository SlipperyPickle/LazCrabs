package leafs

import Constants
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
        val potions = Inventory.stream().id(*Constants.Potions)
        val found = potions.isNotEmpty()


        if (found) {
            if (Game.tab(Game.Tab.INVENTORY)) {
                potions.first().interact("Drink")
            }
        }

        Constants.ScreenClickTime = Random.nextInt(250_000, 300_000)
        Condition.wait { Skills.level(Skill.Strength) != Skills.realLevel(Skill.Strength) }
    }
}