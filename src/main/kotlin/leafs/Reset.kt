package leafs

import Constants.ResetTime
import Script
import org.powbot.api.rt4.Movement
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class Reset(script: Script) : Leaf<Script>(script, "Resetting") {
    override fun execute() {
        Movement.builder(script.settings.resetLocation).move()
        ResetTime = Random.nextInt(4_000, 10_000)
        script.lastScreenClick = System.currentTimeMillis()
    }
}