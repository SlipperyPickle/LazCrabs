package branch

import Constants
import Constants.AutoRetaliateVarp
import Constants.ResetTime
import Constants.ScreenClickTime
import Constants.SpecialAttackPercentageComponent
import Constants.SpecialAttackWidget
import Script
import leafs.*
import org.powbot.api.rt4.*
import org.powbot.api.rt4.walking.model.Skill
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.SimpleLeaf
import org.powbot.api.script.tree.TreeComponent

class ShouldReset(script: Script) : Branch<Script>(script, "ShouldReset?") {
    override val successComponent: TreeComponent<Script> = Reset(script)
    override val failedComponent: TreeComponent<Script> = ShouldEnableAutoRetaliate(script)

    override fun validate(): Boolean {
        if (script.settings.eatFood) {
            return Inventory.stream().name(script.settings.foodName).isEmpty()
        }
        return (System.currentTimeMillis() - script.lastCombatTime) > ResetTime &&
                Players.local().tile() == script.settings.crabLocation
    }
}

class ShouldEnableAutoRetaliate(script: Script) : Branch<Script>(script, "ShouldEnableAutoRetaliate?") {
    override val successComponent: TreeComponent<Script> = EnableAutoRetaliate(script)
    override val failedComponent: TreeComponent<Script> = ShouldWalkToCrabs(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(AutoRetaliateVarp) == 1
    }
}

class ShouldWalkToCrabs(script: Script) : Branch<Script>(script, "ShouldWalkToCrabs?") {
    override val successComponent: TreeComponent<Script> = WalkToCrabs(script)
    override val failedComponent: TreeComponent<Script> = ShouldEat(script)

    override fun validate(): Boolean {
        return Players.local().tile() != script.settings.crabLocation
    }
}

class ShouldEat(script: Script) : Branch<Script>(script, "ShouldEat?") {
    override val successComponent: TreeComponent<Script> = Eat(script)
    override val failedComponent: TreeComponent<Script> = ShouldDrinkPotion(script)

    override fun validate(): Boolean {
        return (Combat.health() < script.settings.healthLevel) &&
                Inventory.stream().name(script.settings.foodName).isNotEmpty()
    }
}

class ShouldDrinkPotion(script: Script) : Branch<Script>(script, "ShouldDrinkPotion?") {
    override val successComponent: TreeComponent<Script> = DrinkPotion(script)
    override val failedComponent: TreeComponent<Script> = ShouldClickScreen(script)

    override fun validate(): Boolean {
        val potions = Inventory.stream().id(*Constants.Potions)
        val found = potions.isNotEmpty()

        if (script.settings.drinkPotion && found) {
            if (Skills.level(Skill.Strength) == Skills.realLevel(Skill.Strength)) {
                return true
            }
        }
        return false
    }
}

class ShouldClickScreen(script: Script) : Branch<Script>(script, "ShouldClickScreen?") {
    override val successComponent: TreeComponent<Script> = ClickScreen(script)
    override val failedComponent: TreeComponent<Script> = ShouldLogout(script)

    override fun validate(): Boolean {
        return (System.currentTimeMillis() - script.lastScreenClick) > ScreenClickTime
    }
}

class ShouldLogout(script: Script) : Branch<Script>(script, "ShouldLogout?") {
    override val successComponent: TreeComponent<Script> = Logout(script)
    override val failedComponent: TreeComponent<Script> = ShouldUseSpecialAttack(script)

    override fun validate(): Boolean {
        return Inventory.stream().name(script.settings.foodName).isEmpty() && script.settings.eatFood
    }
}

class ShouldUseSpecialAttack(script: Script) : Branch<Script>(script, "ShouldUseSpecialAttack?") {
    override val successComponent: TreeComponent<Script> = UseSpecialAttack(script)
    override val failedComponent: TreeComponent<Script> = SimpleLeaf(script, "Chilling") { }

    override fun validate(): Boolean {
        return script.settings.useSpecWeapon && Widgets.widget(SpecialAttackWidget)
            .component(SpecialAttackPercentageComponent).text().toInt() >= script.settings.specPercentage
    }
}




