package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf
import org.powbot.mobile.script.Logger
import java.util.Calendar
import kotlin.random.Random

class WalkToCrabs(script: Script) : Leaf<Script>(script, "Walking to crabs") {
    override fun execute() {
        Constants.ScreenClickTime = Random.nextInt(250_000, 300_000)

        val distance = Players.local().tile().distanceTo(script.settings.crabLocation).toInt()
        if (distance in 1..5) {
            Movement.step(script.settings.crabLocation)
            Condition.wait ({ Players.local().tile().equals(script.settings.crabLocation) }, 150, 10)
        } else {
            Movement.builder(script.settings.crabLocation).move()
        }

        Condition.wait { Players.local().tile() == script.settings.crabLocation }

        script.lastCombatTime = Calendar.getInstance().timeInMillis
    }
}