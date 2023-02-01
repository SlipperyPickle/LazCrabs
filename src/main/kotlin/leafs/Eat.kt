package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Combat
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Item
import org.powbot.api.script.tree.Leaf

class Eat(script: Script) : Leaf<Script>(script, "Eating") {
    override fun execute() {
        val food: Item = Inventory.stream().name(script.settings.foodName).first()
        val health = Combat.health()
        if (food.valid() && Inventory.opened()) {
            food.interact(script.settings.foodAction)
            Condition.wait { Combat.health() > health }
        }
    }
}