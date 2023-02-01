package leafs

import Constants.SpecialAttackComponent
import Constants.SpecialAttackWidget
import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.VarpbitConstants
import org.powbot.api.rt4.Varpbits
import org.powbot.api.rt4.Widgets
import org.powbot.api.script.tree.Leaf

class UseSpecialAttack(script: Script) : Leaf<Script>(script, "Special attack") {
    override fun execute() {
        val specWidget = Widgets.widget(SpecialAttackWidget).component(SpecialAttackComponent)
        val specPercentage = Varpbits.varpbit(VarpbitConstants.VARP_SPECIAL_ATTACK)
        specWidget.click()
        Condition.wait { Varpbits.varpbit(VarpbitConstants.VARP_SPECIAL_ATTACK) < specPercentage }
    }
}