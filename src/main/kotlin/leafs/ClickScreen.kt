package leafs

import Constants
import Script
import org.powbot.api.rt4.Game
import org.powbot.api.script.tree.Leaf
import java.util.*
import kotlin.random.Random

class ClickScreen(script: Script) : Leaf<Script>(script, "Anti logout") {
    override fun execute() {
        val i = Random.nextInt(0, Constants.tabs.size)
        Game.tab(Constants.tabs[i])
        script.lastScreenClick = Calendar.getInstance().timeInMillis
        Constants.ScreenClickTime = Random.nextInt(250_000, 300_000)
    }
}