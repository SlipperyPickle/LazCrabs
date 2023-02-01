package leafs

import Constants
import Script
import org.powbot.api.rt4.Movement
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class Reset(script: Script) : Leaf<Script>(script, "Resetting") {
    override fun execute() {
        Constants.ScreenClickTime = Random.nextInt(250_000, 300_000)
        Movement.builder(script.settings.resetLocation).move()
    }
}