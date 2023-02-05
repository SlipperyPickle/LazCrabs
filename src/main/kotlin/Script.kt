import branch.ShouldReset
import com.google.common.eventbus.Subscribe
import org.powbot.api.Tile
import org.powbot.api.event.PlayerAnimationChangedEvent
import org.powbot.api.rt4.Item
import org.powbot.api.rt4.Players
import org.powbot.api.rt4.walking.model.Skill
import org.powbot.api.script.*
import org.powbot.api.script.paint.Paint
import org.powbot.api.script.paint.PaintBuilder
import org.powbot.api.script.tree.TreeComponent
import org.powbot.api.script.tree.TreeScript
import java.util.*
import java.util.logging.Logger

@ScriptManifest(
    name = "LazCrabs",
    description = "LazCrabs",
    version = "1.0.0",
    category = ScriptCategory.Combat,
    author = "Lazarus",
    markdownFileName = "LazCrabs.md"
)

@ScriptConfiguration.List(
    [
        ScriptConfiguration(
            name = "CrabLocation",
            description = "Select the crabs tile",
            optionType = OptionType.TILE
        ),
        ScriptConfiguration(
            name = "ResetLocation",
            description = "Select the reset tile",
            optionType = OptionType.TILE,
        ),
        ScriptConfiguration(
            name = "FoodToEat",
            description = "Select food to eat",
            optionType = OptionType.INVENTORY_ACTIONS,
            visible = false
        ),
        ScriptConfiguration(
            name = "EatFood",
            description = "Do you want to eat food?",
            optionType = OptionType.BOOLEAN
        ),
        ScriptConfiguration(
            name = "HealthLevel",
            description = "At what hp level do you want to eat?",
            optionType = OptionType.INTEGER
        ),
        ScriptConfiguration(
            name = "DrinkPotion",
            description = "Do you want to drink potion?",
            optionType = OptionType.BOOLEAN
        ),
        ScriptConfiguration(
            name = "UseSpecWeapon",
            description = "Do you want to use a spec weapon?",
            optionType = OptionType.BOOLEAN
        ),
        ScriptConfiguration(
            name = "SpecPercentage",
            description = "Percentage the spec weapon uses",
            optionType = OptionType.INTEGER
        ),
    ]
)

class Script : TreeScript() {

    val logger: Logger = Logger.getLogger(this.javaClass.simpleName)
    var lastCombatTime = System.currentTimeMillis()
    var lastScreenClick = System.currentTimeMillis()
    lateinit var settings: Settings

    override val rootComponent: TreeComponent<*> by lazy {
        ShouldReset(this)
    }

    override fun onStart() {
        lastCombatTime = System.currentTimeMillis()
        val resetLocation = getOption<Tile>("ResetLocation")
        val crabLocation = getOption<Tile>("CrabLocation")

        //val resetLocation = Tile(3669, 3829, 0)
        //val crabLocation = Tile(3657, 3874, 0)

        val eatFood = getOption<Boolean>("EatFood")
        val healthLevel = getOption<Int>("HealthLevel")
        val drinkPotion = getOption<Boolean>("DrinkPotion")
        val specWeapon = getOption<Boolean>("UseSpecWeapon")
        val specWeaponPercentage = getOption<Int>("SpecPercentage")
        settings = if (eatFood) {
            val foodToEat = getOption<Item>("FoodToEat")
            Settings(resetLocation, crabLocation, eatFood, foodToEat.name(), foodToEat.actions()[0],
                healthLevel, drinkPotion, specWeapon, specWeaponPercentage)
        } else {
            Settings(resetLocation, crabLocation, eatFood, healthLevel, drinkPotion, specWeapon, specWeaponPercentage)
        }
        addPaint()
    }

    @ValueChanged(keyName = "EatFood")
    fun foodToEatConfig(valueChanged: Boolean) {
        updateVisibility("FoodToEat", valueChanged)
    }

    @ValueChanged(keyName = "UseSpecWeapon")
    fun specWeaponConfig(valueChanged: Boolean) {
        updateVisibility("SpecPercentage", valueChanged)
    }

    @Subscribe
    fun playerAnimationChangedEvent(change: PlayerAnimationChangedEvent) {
        if (change.player == Players.local()) {
            lastCombatTime = System.currentTimeMillis()
        }
    }

    private fun addPaint() {
        val p: Paint = PaintBuilder.newBuilder()
            .addString("Last leaf:") {lastLeaf.name}
            .trackSkill(Skill.Attack)
            .trackSkill(Skill.Defence)
            .trackSkill(Skill.Strength)
            .trackSkill(Skill.Hitpoints)
            .trackSkill(Skill.Ranged)
            .trackSkill(Skill.Magic)
            .y(45)
            .x(40)
            .build()
        addPaint(p)
    }
}

fun main() {
    Script().startScript()
}