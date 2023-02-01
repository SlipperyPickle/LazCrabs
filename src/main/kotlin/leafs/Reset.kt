package leafs

import Script
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Movement
import org.powbot.api.script.tree.Leaf
import org.powbot.mobile.script.Logger
import kotlin.random.Random

class Reset(script: Script) : Leaf<Script>(script, "Resetting") {
    override fun execute() {
        Constants.ScreenClickTime = Random.nextInt(250_000, 300_000)
        Movement.builder(script.settings.resetLocation).move()
    }
}