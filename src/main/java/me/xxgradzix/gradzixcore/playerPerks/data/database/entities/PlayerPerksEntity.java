package me.xxgradzix.gradzixcore.playerPerks.data.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import me.xxgradzix.gradzixcore.playerPerks.PerkType;

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

    /** Represents percent chance of afflicting sickness effect on player*/
    @DatabaseField
    private int sicknessLevel;

    /** Represents percent additional hearts per level (every book have a chance to give from zero to two hearts) */
    @DatabaseField
    private int additionalHeartsLevel;

    /** Represents percent chance of afflicting slowness effect on player */
    @DatabaseField
    private int slownessLevel;

    public PlayerPerksEntity() {
    }

    public PlayerPerksEntity(UUID uuid) {
        this.uuid = uuid;
    }
    public void increasePerkLevelRandomly(PerkType perkType) throws RuntimeException {
        switch (perkType) {
                case STRENGTH: {
                    if(strengthLevel >= 100) throw new RuntimeException("Strength level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 3);
                    strengthLevel += additionalPercent;
                    if(strengthLevel > 100) strengthLevel = 100;
                }
                break;
                case POISON: {
                    if(poisonLevel >= 50) throw new RuntimeException("Poison level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 3);
                    poisonLevel += additionalPercent;
                    if(poisonLevel > 50) poisonLevel = 50;
                }
                break;
                case RESISTANCE: {
                    if (resistanceLevel >= 50) throw new RuntimeException("Resistance level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 3);
                    resistanceLevel += additionalPercent;
                    if(resistanceLevel > 50) resistanceLevel = 50;
                }
                break;
                case LIFE_STEAL: {
                    if (lifeStealLevel >= 30) throw new RuntimeException("Life steal level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 3);
                    lifeStealLevel += additionalPercent;
                    if(lifeStealLevel > 30) lifeStealLevel = 30;
                }
                break;
                case SICKNESS: {
                    if (sicknessLevel >= 50) throw new RuntimeException("Sickness level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 3);
                    sicknessLevel += additionalPercent;
                    if(sicknessLevel > 50) sicknessLevel = 50;
                }
                break;
//                case ADDITIONAL_HEARTS: {
//                    if (additionalHeartsLevel >= 8) throw new RuntimeException("Additional hearts level already have maximum value");
//                    int additionalPercent = generateRandomNumber(0, 2);
//                    additionalHeartsLevel += additionalPercent;
//                    if(additionalHeartsLevel > 8) additionalHeartsLevel = 8;
//                }
//                break;
                case SLOWNESS: {
                    if (slownessLevel >= 50) throw new RuntimeException("Slowness level already have maximum value");
                    int additionalPercent = generateRandomNumber(1, 3);
                    slownessLevel += additionalPercent;
                    if(slownessLevel > 50) slownessLevel = 50;
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
            case SICKNESS: {
                sicknessLevel = level;
            }
            break;
            case SLOWNESS: {
                slownessLevel = level;

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
            case SICKNESS: {
                return sicknessLevel;
            }
//            case ADDITIONAL_HEARTS: {
//                return additionalHeartsLevel;
//            }
            case SLOWNESS: {
                return slownessLevel;
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
