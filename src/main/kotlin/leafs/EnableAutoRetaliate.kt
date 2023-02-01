package leafs

import Constants.AutoRetaliateComponent
import Constants.AutoRetaliateVarp
import Constants.AutoRetaliateWidget
import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Varpbits
import org.powbot.api.rt4.Widgets
import org.powbot.api.script.tree.Leaf

class EnableAutoRetaliate(script: Script) : Leaf<Script>(script, "EnableAutoRetaliate") {
    override fun execute() {
        if (Game.tab(Game.Tab.ATTACK)) {
            val autoRetaliateWidget = Widgets.widget(AutoRetaliateWidget).component(AutoRetaliateComponent)
            if (autoRetaliateWidget.valid()) {
                autoRetaliateWidget.click()
                Condition.wait { Varpbits.varpbit(AutoRetaliateVarp) == 0 }
            }
        }
    }
}