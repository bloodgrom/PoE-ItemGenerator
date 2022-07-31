package com.poeitemgen.items.modifiers;

import java.util.ArrayList;
import java.util.List;

public class Suffix {

    // Declare fields ------------------------------------------------------------------------------
    public String suffixName = "";
    public int suffixItemLevel = 1;
    public int suffixTier = 1;
    public String suffixType = "";
    public int suffixGroupID = 1;
    public String suffixText = "";
    public String suffixInfluenceType = "None";
    public List<Integer> suffixValues = new ArrayList<Integer>();

    // ---------------------------------------------------------------------------------------------

    // Constructors --------------------------------------------------------------------------------
    public Suffix(String suffixName,int suffixItemLevel ,int suffixTier, String suffixType, int suffixGroupID, String suffixText, String suffixInfluenceType, List<Integer> suffixValues) {
        this.suffixName = suffixName;
        this.suffixItemLevel = suffixItemLevel;
        this.suffixTier = suffixTier;
        this.suffixType = suffixType;
        this.suffixGroupID = suffixGroupID;
        this.suffixText = suffixText;
        this.suffixInfluenceType = suffixInfluenceType;
        this.suffixValues = suffixValues;
    }

    // ---------------------------------------------------------------------------------------------

    // Getters & Setters ---------------------------------------------------------------------------
    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public int getSuffixTier() {
        return suffixTier;
    }

    public void setSuffixTier(int suffixTier) {
        this.suffixTier = suffixTier;
    }

    public String getSuffixType() {
        return suffixType;
    }

    public void setSuffixType(String suffixType) {
        this.suffixType = suffixType;
    }

    public int getSuffixGroupID() {
        return suffixGroupID;
    }

    public void setSuffixGroupID(int suffixGroupID) {
        this.suffixGroupID = suffixGroupID;
    }

    public int getSuffixItemLevel() {
        return suffixItemLevel;
    }

    public void setSuffixItemLevel(int suffixItemLevel) {
        this.suffixItemLevel = suffixItemLevel;
    }

    public String getSuffixText() {
        return suffixText;
    }

    public void setSuffixText(String suffixText) {
        this.suffixText = suffixText;
    }

    public String getSuffixInfluenceType() {
        return suffixInfluenceType;
    }

    public void setSuffixInfluenceType(String suffixInfluenceType) {
        this.suffixInfluenceType = suffixInfluenceType;
    }

    public List<Integer> getSuffixValues() {
        return suffixValues;
    }

    public void setSuffixValues(List<Integer> suffixValues) {
        this.suffixValues = suffixValues;
    }

    // ---------------------------------------------------------------------------------------------

    //Methods --------------------------------------------------------------------------------------
    public String toString(String descriptionType){

        String suffixValuesString1 = "";
        String suffixValuesString2 = "";
        String suffixValuesStringComplete = getSuffixText();
        List<Integer> suffixValues = getSuffixValues();

        if (suffixValues.size() == 2) {
            suffixValuesString1 = "(" + suffixValues.get(0) + " - " + suffixValues.get(1) + ")";
            suffixValuesStringComplete =String.format(suffixValuesStringComplete, suffixValuesString1);
        }
        if (suffixValues.size() == 4) {
            suffixValuesString1 = "(" + suffixValues.get(0) + " - " + suffixValues.get(1) + ")";
            suffixValuesString2 = "(" + suffixValues.get(2) + " - " + suffixValues.get(3) + ")";
            suffixValuesStringComplete =String.format(suffixValuesStringComplete, suffixValuesString1, suffixValuesString2);
        }

        if (descriptionType.equals("Advanced")) {
            return "\"" + getSuffixName() + "\"(Tier " + getSuffixTier() + ") " + suffixValuesStringComplete;
        }
        else {
            return suffixValuesStringComplete;
        }
    }
}
