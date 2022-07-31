package com.poeitemgen.items;

import com.poeitemgen.items.modifiers.Prefix;
import com.poeitemgen.items.modifiers.Suffix;
import com.poeitemgen.items.utils.LimitedModifierSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Item {

    // Declare fields ------------------------------------------------------------------------------
    String itemName = "";
    String itemRarity = "";
    int itemLevel = 1;

    LimitedModifierSet<Prefix> prefixSet = new LimitedModifierSet<>(3);
    LimitedModifierSet<Suffix> suffixSet = new LimitedModifierSet<>(3);

    LimitedModifierSet<String> influenceSet = new LimitedModifierSet<>(2);

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

    public LimitedModifierSet<String> getInfluenceSet() {
        return influenceSet;
    }

    public void setInfluenceSet(LimitedModifierSet<String> influenceSet) {
        this.influenceSet = influenceSet;
    }

    // ---------------------------------------------------------------------------------------------

    // Regular Methods -------------------------------------------------------------------------------------
    public void printItemText(String descriptionType) {
        System.out.println(getItemText(descriptionType));
    }

    public void addPrefix(Prefix prefix) {

        boolean canAddVeiled = !((getAllPrefixTypes().contains("Aisling")) && (prefix.getPrefixType().equals("Aisling")));

        if ((canAddVeiled) && (!getAllPrefixGroupID().contains(prefix.getPrefixGroupID()))) {
            if (!(prefix.getPrefixItemLevel() > getItemLevel())) {
                this.prefixSet.add(prefix);
            }
        }

    }

    public void addSuffix(Suffix suffix) {

        boolean canAddVeiled = !((getAllSuffixTypes().contains("Aisling")) && (suffix.getSuffixType().equals("Aisling")));

        if ((canAddVeiled) && (!getAllSuffixGroupID().contains(suffix.getSuffixGroupID()))) {
            this.suffixSet.add(suffix);
        }

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

    public List<String> getAllPrefixTypes() {
        List<String> prefixTypeList= new ArrayList<>();

        for (Prefix prefix : prefixSet) {
            prefixTypeList.add(prefix.getPrefixType());
        }

        return prefixTypeList;
    }

    public List<Integer> getAllPrefixGroupID() {
        List<Integer> prefixGroupIDList= new ArrayList<>();

        for (Prefix prefix : prefixSet) {
            prefixGroupIDList.add(prefix.getPrefixGroupID());
        }

        return prefixGroupIDList;
    }

    public List<String> getAllSuffixTypes() {
        List<String> suffixTypeList= new ArrayList<>();

        for (Suffix suffix : suffixSet) {
            suffixTypeList.add(suffix.getSuffixType());
        }

        return suffixTypeList;
    }

    public List<Integer> getAllSuffixGroupID() {
        List<Integer> suffixGroupIDList= new ArrayList<>();

        for (Suffix suffix : suffixSet) {
            suffixGroupIDList.add(suffix.getSuffixGroupID());
        }

        return suffixGroupIDList;
    }

    // Abstract Methods ----------------------------------------------------------------------------
    public abstract String getItemText(String descriptionType);

    // ---------------------------------------------------------------------------------------------
}
