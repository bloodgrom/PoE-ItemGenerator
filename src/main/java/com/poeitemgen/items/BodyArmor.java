package com.poeitemgen.items;

import com.poeitemgen.items.modifiers.Prefix;
import com.poeitemgen.items.modifiers.Suffix;
import com.poeitemgen.items.utils.LimitedModifierSet;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BodyArmor extends Item{

    // Declare fields ------------------------------------------------------------------------------
    private final ArmorType armorType;

    public enum ArmorType {
        ARMOR("Armor"),
        SHIELD("Energy Shield"),
        EVASION("Evasion"),
        WARD("Ward"),
        ARMORSHIELD("Armor/Energy Shield"),
        ARMOREVASION("Armor/Evasion"),
        SHIELDEVASION("Energy Shield/Evasion"),
        ARMORSHIELDEVASION("Armor/Energy Shield/Evasion");

        private final String armorTypeText;

        ArmorType(String armorTypeText) {
            this.armorTypeText = armorTypeText;
        }

        public String getString() {
            return armorTypeText;
        }
    }

    int armorValue = 652;
    int energyShieldValue = 119;
    int evasionValue = 0;
    int wardValue = 0;

    // ---------------------------------------------------------------------------------------------


    // Constructors --------------------------------------------------------------------------------
    public BodyArmor(String itemName, ArmorType armorType) {
        super(itemName);
        this.armorType = armorType;
    }

    public BodyArmor(String itemName) {
        super(itemName);
        this.armorType = randomArmorType();
    }

    // ---------------------------------------------------------------------------------------------

    // Getters & Setters ---------------------------------------------------------------------------
    public ArmorType getArmorType() {
        return armorType;
    }

    public String getArmorTypeName() {
        return armorType.getString();
    }

    public int getArmorValue() {
        return armorValue;
    }

    public void setArmorValue(int armorValue) {
        this.armorValue = armorValue;
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



    // ---------------------------------------------------------------------------------------------

    // Methods -------------------------------------------------------------------------------------
    private static final List<ArmorType> VALUES = List.of(ArmorType.values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static ArmorType randomArmorType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public String getItemText(String descriptionType) {
        String armorValues = "";

        //Armor values
        if (this.getArmorValue() > 0) {
            armorValues += "Armor: " + this.getArmorValue() + "\n";
        }
        if (this.getEnergyShieldValue() > 0) {
            armorValues += "Energy Shield: " + this.getEnergyShieldValue() + "\n";
        }
        if (this.getEvasionValue() > 0) {
            armorValues += "Evasion: " + this.getEvasionValue() + "\n";
        }
        if (this.getWardValue() > 0) {
            armorValues += "Ward: " + this.getWardValue() + "\n";
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
                //Item rarity
                this.getItemRarity() + "\n" +
                "--------------------------" + "\n" +
                //Item level
                this.getItemLevel() + "\n" +
                "--------------------------" + "\n" +
                //Armor type
                this.getArmorTypeName() + "\n" +
                "--------------------------" + "\n" +
                //Armor values
                armorValues +
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
