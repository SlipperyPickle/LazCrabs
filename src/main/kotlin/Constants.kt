import org.powbot.api.rt4.Game
import kotlin.random.Random

object Constants {
    const val ResetTime = 20_000
    const val SpecialAttackWidget = 160
    const val SpecialAttackComponent = 35
    const val SpecialAttackPercentageComponent = 36
    const val AutoRetaliateVarp = 172
    const val AutoRetaliateWidget = 593
    const val AutoRetaliateComponent = 30


    val tabs = mutableListOf(
        Game.Tab.SETTINGS,
        Game.Tab.STATS,
        Game.Tab.INVENTORY,
        Game.Tab.ATTACK,
        Game.Tab.FRIENDS_LIST
    )

    var ScreenClickTime = Random.nextInt(200_000, 250_000)

    val Potions = intArrayOf(12701, 12699, 12697, 12695, 9745, 9743, 9741, 9739, 161, 159, 157, 2440,
        119, 117, 115, 113)
}