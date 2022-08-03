package com.poeitemgen.items;

import com.poeitemgen.items.modifiers.Prefix;
import com.poeitemgen.items.modifiers.Suffix;

import java.util.List;
import java.util.Random;

public class BodyArmour extends Item{

    // Declare fields ------------------------------------------------------------------------------
    private final ArmourType armourType;

    public enum ArmourType {
        ARMOUR("Armour");
//        SHIELD("Energy Shield"),
//        EVASION("Evasion"),
//        WARD("Ward"),
//        ARMOURSHIELD("Armour/Energy Shield"),
//        ARMOUREVASION("Armour/Evasion"),
//        SHIELDEVASION("Energy Shield/Evasion"),
//        ARMOURSHIELDEVASION("Armour/Energy Shield/Evasion");

        private final String armourTypeText;

        ArmourType(String armourTypeText) {
            this.armourTypeText = armourTypeText;
        }

        public String getString() {
            return armourTypeText;
        }
    }

    int armourValue = 652;
    int energyShieldValue = 119;
    int evasionValue = 0;
    int wardValue = 0;
    String baseType = "";

    // ---------------------------------------------------------------------------------------------


    // Constructors --------------------------------------------------------------------------------
    public BodyArmour(String itemName, ArmourType armourType) {
        super(itemName);
        this.armourType = armourType;
        this.baseType = armourType.getString();
    }

    public BodyArmour(String itemName) {
        super(itemName);
        this.armourType = randomArmourType();
        this.baseType = this.armourType.getString();
    }

    // ---------------------------------------------------------------------------------------------

    // Getters & Setters ---------------------------------------------------------------------------
    public ArmourType getArmourType() {
        return armourType;
    }

    public String getArmourTypeName() {
        return armourType.getString();
    }

    public int getArmourValue() {
        return armourValue;
    }

    public void setArmourValue(int armourValue) {
        this.armourValue = armourValue;
    }

    public int getEnergyShieldValue() {
        return energyShieldValue;
    }

    public void setEnergyShieldValue(int energyShieldValue) {
        this.energyShieldValue = energyShieldValue;
    }

    public int getEvasionValue() {
        return evasionValue;
    }

    public void setEvasionValue(int evasionValue) {
        this.evasionValue = evasionValue;
    }

    public int getWardValue() {
        return wardValue;
    }

    public void setWardValue(int wardValue) {
        this.wardValue = wardValue;
    }

    public String getBaseType() {
        return baseType;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

// ---------------------------------------------------------------------------------------------

    // Methods -------------------------------------------------------------------------------------
    private static final List<ArmourType> VALUES = List.of(ArmourType.values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static ArmourType randomArmourType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public String getItemText(String descriptionType) {
        String armourValues = "";

        //Armour values
        if (this.getArmourValue() > 0) {
            armourValues += "Armour: " + this.getArmourValue() + "\n";
        }
        if (this.getEnergyShieldValue() > 0) {
            armourValues += "Energy Shield: " + this.getEnergyShieldValue() + "\n";
        }
        if (this.getEvasionValue() > 0) {
            armourValues += "Evasion: " + this.getEvasionValue() + "\n";
        }
        if (this.getWardValue() > 0) {
            armourValues += "Ward: " + this.getWardValue() + "\n";
        }

        StringBuilder prefixString = new StringBuilder();
            for (Prefix prefix: this.prefixSet) {
                prefixString.append(prefix.toString(descriptionType)).append("\n");
            }

        StringBuilder suffixString = new StringBuilder();
        for (Suffix suffix: this.suffixSet) {
            suffixString.append(suffix.toString(descriptionType)).append("\n");
        }

        String itemFullString =
                //Item name
                this.getItemName() + "\n" +
                "--------------------------" + "\n" +
                //Armour type
                this.getBaseType() + "\n" +
                "--------------------------" + "\n" +
                //Item level
                this.getItemLevel() + "\n" +
                "--------------------------" + "\n" +
                //Item rarity
                this.getItemRarity() + "\n" +
                "--------------------------" + "\n" +
                //Armour values
                armourValues +
                "--------------------------" + "\n" +
                //Prefixes
                prefixString +
                "--------------------------"  + "\n" +
                //Suffixes
                suffixString +
                "--------------------------"  + "\n";

        return itemFullString;
    }


    // ---------------------------------------------------------------------------------------------




}
