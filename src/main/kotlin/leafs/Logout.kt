package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf
import org.powbot.mobile.script.ScriptManager

class Logout(script: Script) : Leaf<Script>(script, "Logout") {
    override fun execute() {
        Movement.builder(script.settings.resetLocation).move()
        Condition.wait { Players.local().tile().distanceTo(script.settings.crabLocation) > 10 }
        ScriptManager.stop()
    }
}