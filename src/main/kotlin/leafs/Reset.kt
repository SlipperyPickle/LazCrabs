package leafs

import Script
import org.powbot.api.rt4.Movement
import org.powbot.api.script.tree.Leaf

class Reset(script: Script) : Leaf<Script>(script, "Resetting") {
    override fun execute() {
        script.lastScreenClick = System.currentTimeMillis()
        Movement.builder(script.settings.resetLocation).move()
    }
}