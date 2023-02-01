import org.powbot.api.Tile

data class Settings (
    val resetLocation: Tile,
    val crabLocation: Tile,
    val eatFood: Boolean,
    val foodName: String,
    val foodAction: String,
    val healthLevel: Int,
    val drinkPotion: Boolean,
    val useSpecWeapon: Boolean,
    val specPercentage: Int
) {
    constructor(
        resetLocation: Tile,
        crabLocation: Tile,
        eatFood: Boolean,
        healthLevel: Int,
        drinkPotion: Boolean,
        useSpecWeapon: Boolean,
        specPercentage: Int
    ) : this(resetLocation, crabLocation, eatFood, "", "", healthLevel, drinkPotion, useSpecWeapon, specPercentage)
}
