package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class InCombat(script: Script) : Leaf<Script>(script, "In Combat") {
    override fun execute() {
        Condition.sleep(Random.nextInt(600, 1000))
    }
}