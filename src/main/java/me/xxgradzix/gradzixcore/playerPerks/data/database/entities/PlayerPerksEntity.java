package me.xxgradzix.gradzixcore.playerPerks.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import me.xxgradzix.gradzixcore.playerPerks.PerkType;
import me.xxgradzix.gradzixcore.playerPerks.PlayerPerks;

import java.util.Random;
import java.util.UUID;

@DatabaseTable(tableName = "gradzixcore_player_perks")
public class PlayerPerksEntity {


    @DatabaseField(unique = true, id = true)
    private UUID uuid;

    /** Show percent of additional base damage percent */
    @DatabaseField
    private int strengthLevel;

    /** Represents percent of chance of afflicting poison effect on player*/
    @DatabaseField
    private int poisonLevel;
    /** represent chance for applying resistance effect on yourself while dealing damage */
    @DatabaseField
    private int resistanceLevel;

    /** Represents percent of chance of absorbing half of damage you deal*/
    @DatabaseField
    private int lifeStealLevel;

//    /** Represents percent chance of afflicting sickness effect on player*/
//    @DatabaseField
//    private int sicknessLevel;

    /** Represents percent additional hearts per level (every book have a chance to give from zero to two hearts) */
    @DatabaseField
    private int weaknessLevel;
    @DatabaseField
    private int additionalHeartsLevel;

    /** Represents percent chance of afflicting slowness effect on player */
    @DatabaseField
    private int slownessLevel;

    /** Represents percent chance of getting perk fragment after kill */
    @DatabaseField
    private int fragmentDropLevel;

    public PlayerPerksEntity() {
    }

    public PlayerPerksEntity(UUID uuid) {
        this.uuid = uuid;
    }
    public void increasePerkLevelRandomly(PerkType perkType) throws RuntimeException {
        switch (perkType) {
                case STRENGTH: {
                    if(strengthLevel >= PlayerPerks.STRENGTH_MAX_LEVEL) throw new RuntimeException("Strength level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 3);
                    strengthLevel += additionalPercent;
                    if(strengthLevel > PlayerPerks.STRENGTH_MAX_LEVEL) strengthLevel = PlayerPerks.STRENGTH_MAX_LEVEL;
                }
                break;
                case POISON: {
                    if(poisonLevel >= PlayerPerks.POISON_MAX_LEVEL) throw new RuntimeException("Poison level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 2);
                    poisonLevel += additionalPercent;
                    if(poisonLevel > PlayerPerks.POISON_MAX_LEVEL) poisonLevel = PlayerPerks.POISON_MAX_LEVEL;
                }
                break;
                case RESISTANCE: {
                    if (resistanceLevel >= PlayerPerks.RESISTANCE_MAX_LEVEL) throw new RuntimeException("Resistance level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 3);
                    resistanceLevel += additionalPercent;
                    if(resistanceLevel > PlayerPerks.RESISTANCE_MAX_LEVEL) resistanceLevel = PlayerPerks.RESISTANCE_MAX_LEVEL;
                }
                break;
                case LIFE_STEAL: {
                    if (lifeStealLevel >= PlayerPerks.LIFE_STEAL_MAX_LEVEL) throw new RuntimeException("Life steal level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 3);
                    lifeStealLevel += additionalPercent;
                    if(lifeStealLevel > PlayerPerks.LIFE_STEAL_MAX_LEVEL) lifeStealLevel = PlayerPerks.LIFE_STEAL_MAX_LEVEL;
                }
                break;
//                case SICKNESS: {
//                    if (sicknessLevel >= 50) throw new RuntimeException("Sickness level already have maximum value");
//                    int additionalPercent = generateRandomNumber(1, 3);
//                    sicknessLevel += additionalPercent;
//                    if(sicknessLevel > 50) sicknessLevel = 50;
//                }
//                break;
                case ADDITIONAL_HEARTS: {
                    if (additionalHeartsLevel >= PlayerPerks.ADDITIONAL_HEARTS_MAX_LEVEL) throw new RuntimeException("Additional hearts level already have maximum value");
                    int additionalHeart = 1;
                    additionalHeartsLevel += additionalHeart;
                    if(additionalHeartsLevel > PlayerPerks.ADDITIONAL_HEARTS_MAX_LEVEL) additionalHeartsLevel = PlayerPerks.ADDITIONAL_HEARTS_MAX_LEVEL;
                }
                break;
                case SLOWNESS: {
                    if (slownessLevel >= PlayerPerks.SLOWNESS_MAX_LEVEL) throw new RuntimeException("Slowness level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 3);
                    slownessLevel += additionalPercent;
                    if(slownessLevel > PlayerPerks.SLOWNESS_MAX_LEVEL) slownessLevel = PlayerPerks.SLOWNESS_MAX_LEVEL;
                }
                break;
                case WEAKNESS: {
                    if (weaknessLevel >= PlayerPerks.WEAKNESS_MAX_LEVEL) throw new RuntimeException("Weakness level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 2);
                    weaknessLevel += additionalPercent;
                    if(weaknessLevel > PlayerPerks.WEAKNESS_MAX_LEVEL) slownessLevel = PlayerPerks.WEAKNESS_MAX_LEVEL;
                }
                case PERK_FRAGMENT_DROP: {
                    if (fragmentDropLevel >= PlayerPerks.PERK_FRAGMENT_DROP_MAX_LEVEL) throw new RuntimeException("Fragment drop level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 3);
                    fragmentDropLevel += additionalPercent;
                    if(fragmentDropLevel > PlayerPerks.PERK_FRAGMENT_DROP_MAX_LEVEL) fragmentDropLevel = PlayerPerks.PERK_FRAGMENT_DROP_MAX_LEVEL;
                }
                break;
        }
    }

    public void setPerkLevel(PerkType perkType, int level) {
        switch (perkType) {
            case STRENGTH: {
                strengthLevel = level;
            }
            break;
            case POISON: {
                poisonLevel = level;
            }
            break;
            case RESISTANCE: {
                resistanceLevel = level;
            }
            break;
            case LIFE_STEAL: {
                lifeStealLevel = level;
            }
            break;
//            case SICKNESS: {
//                sicknessLevel = level;
//            }
//            break;
            case SLOWNESS: {
                slownessLevel = level;
            }
            break;
            case ADDITIONAL_HEARTS: {
                additionalHeartsLevel = level;
            }
            break;
            case WEAKNESS: {
                weaknessLevel = level;
            }
            break;
            case PERK_FRAGMENT_DROP: {
                fragmentDropLevel = level;
            }
            break;
        }
    }
    public int getPerkTypeLevel(PerkType perkType) {
        switch (perkType) {
            case STRENGTH: {
                return strengthLevel;
            }
            case POISON: {
                return poisonLevel;
            }
            case RESISTANCE: {
                return resistanceLevel;
            }
            case LIFE_STEAL: {
                return lifeStealLevel;
            }
//            case SICKNESS: {
//                return sicknessLevel;
//            }
            case ADDITIONAL_HEARTS: {
                return additionalHeartsLevel;
            }
            case SLOWNESS: {
                return slownessLevel;
            }
            case WEAKNESS: {
                return weaknessLevel;
            }
            case PERK_FRAGMENT_DROP: {
                return fragmentDropLevel;
            }
            default: {
                return 0;
            }
        }
    }
    public static int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min value must be less than or equal to max value");
        }

        Random random = new Random();
        // The formula below generates a random number within the specified range
        return random.nextInt((max - min) + 1) + min;
    }

}
