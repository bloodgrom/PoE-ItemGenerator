package com.poeitemgen.items;

import com.poeitemgen.items.modifiers.Prefix;
import com.poeitemgen.items.modifiers.Suffix;
import com.poeitemgen.items.utils.LimitedModifierSet;

import java.util.Random;

abstract class Item {

    // Declare fields ------------------------------------------------------------------------------
    String itemName = "";
    String itemRarity = "";
    int itemLevel = 1;

    LimitedModifierSet<Prefix> prefixSet = new LimitedModifierSet<>(3);
    LimitedModifierSet<Suffix> suffixSet = new LimitedModifierSet<>(3);

    // Constructors --------------------------------------------------------------------------------
    Item(String itemName) {
        this.itemName = itemName;
        this.itemRarity = "Normal";
        this.itemLevel = 100;
    }

    Item(String itemName, String ItemRarity) {
        this.itemName = itemName;
        this.itemRarity = ItemRarity;
        this.itemLevel = 100;
    }

    Item() {
        this.itemName = "Custom Item Name";
        this.itemRarity = "Normal";
        this.itemLevel = 100;
    }

    // ---------------------------------------------------------------------------------------------

    // Getters & Setters ---------------------------------------------------------------------------
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemRarity() {
        return itemRarity;
    }

    public void setItemRarity(String itemRarity) {
        this.itemRarity = itemRarity;
    }

    public int getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    public LimitedModifierSet<Prefix> getPrefixSet() {
        return prefixSet;
    }

    public void setPrefixSet(LimitedModifierSet<Prefix> prefixSet) {
        this.prefixSet = prefixSet;
    }

    public LimitedModifierSet<Suffix> getSuffixSet() {
        return suffixSet;
    }

    public void setSuffixSet(LimitedModifierSet<Suffix> suffixSet) {
        this.suffixSet = suffixSet;
    }

    public void addPrefix(Prefix prefix) {
        this.prefixSet.add(prefix);
    }

    public void addSuffix(Suffix suffix) {
        this.suffixSet.add(suffix);
    }

    public void removePrefix() {
        int size = this.prefixSet.size();
        int removeIndex = new Random().nextInt(size);
        int currentIndex = 0;

        for (Prefix prefix : this.prefixSet) {
            if (currentIndex == removeIndex)
            {
                this.prefixSet.remove(prefix);
                break;
            }
            currentIndex++;
        }
    }

    public void removeSuffix() {
        int size = this.suffixSet.size();
        int removeIndex = new Random().nextInt(size);
        int currentIndex = 0;

        for (Suffix suffix : this.suffixSet) {
            if (currentIndex == removeIndex)
            {
                this.suffixSet.remove(suffix);
                break;
            }
            currentIndex++;
        }
    }

    // ---------------------------------------------------------------------------------------------

    // Regular Methods -------------------------------------------------------------------------------------
    public void printItemText() {
        System.out.println(getItemText());
    };

    // Abstract Methods
    public abstract String getItemText();

    // ---------------------------------------------------------------------------------------------
}
