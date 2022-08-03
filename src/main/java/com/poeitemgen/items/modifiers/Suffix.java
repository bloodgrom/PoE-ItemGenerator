package com.poeitemgen.items.modifiers;

import java.util.ArrayList;
import java.util.List;

public class Suffix extends Modifier{

    // Declare fields ------------------------------------------------------------------------------
    public String suffixName = "";
    public int suffixItemLevel = 1;
    public int suffixTier = 1;
    public String suffixType = "";
    public int suffixGroupID = 1;
    public String suffixText = "";
    public String suffixInfluenceType = "None";
    public List<String> suffixTags = new ArrayList<>();
    public List<Integer> suffixValues = new ArrayList<Integer>();
    public List<Integer> suffixValuesRange = new ArrayList<Integer>();

    // ---------------------------------------------------------------------------------------------

    // Constructors --------------------------------------------------------------------------------
    public Suffix(String suffixName,int suffixItemLevel ,int suffixTier, String suffixType, int suffixGroupID, String suffixText, String suffixInfluenceType, List<String> suffixTags, List<Integer> suffixValues, List<Integer> suffixValuesRange) {
        this.suffixName = suffixName;
        this.suffixItemLevel = suffixItemLevel;
        this.suffixTier = suffixTier;
        this.suffixType = suffixType;
        this.suffixGroupID = suffixGroupID;
        this.suffixText = suffixText;
        this.suffixInfluenceType = suffixInfluenceType;
        this.suffixTags = suffixTags;
        this.suffixValues = suffixValues;
        this.suffixValuesRange = suffixValuesRange;
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

    public List<Integer> getsuffixValuesRange() {
        return suffixValuesRange;
    }

    public void setsuffixValuesRange(List<Integer> suffixValuesRange) {
        this.suffixValuesRange = suffixValuesRange;
    }

    public String getSuffixText() {
        return suffixText;
    }

    public void setSuffixText(String suffixText) {
        this.suffixText = suffixText;
    }

    public int getSuffixItemLevel() {
        return suffixItemLevel;
    }

    public void setSuffixItemLevel(int suffixItemLevel) {
        this.suffixItemLevel = suffixItemLevel;
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

    public List<Integer> getSuffixValuesRange() {
        return suffixValuesRange;
    }

    public void setSuffixValuesRange(List<Integer> suffixValuesRange) {
        this.suffixValuesRange = suffixValuesRange;
    }

    public List<String> getSuffixTags() {
        return suffixTags;
    }

    public void setSuffixTags(List<String> suffixTags) {
        this.suffixTags = suffixTags;
    }

    // ---------------------------------------------------------------------------------------------

    //Methods --------------------------------------------------------------------------------------
    public String toString(String descriptionType){

        String suffixValuesRangeString1 = "";
        String suffixValuesRangeString2 = "";
        Integer suffixValue1 = 1;
        Integer suffixValue2 = 1;
        String suffixValuesRangeStringCompleteNormal = getSuffixText();
        String suffixValuesRangeStringCompleteAdvanced = getSuffixText();
        List<Integer> suffixValuesRange = getsuffixValuesRange();
        List<Integer> suffixValues = getSuffixValues();

        if (suffixValuesRange.size() == 2) {
            suffixValue1 = ((Number) suffixValues.get(0)).intValue();
            suffixValuesRangeString1 = suffixValue1 + "(" + suffixValuesRange.get(0) + " - " + suffixValuesRange.get(1) + ")";
            suffixValuesRangeStringCompleteNormal =String.format(suffixValuesRangeStringCompleteNormal, suffixValue1);
            suffixValuesRangeStringCompleteAdvanced =String.format(suffixValuesRangeStringCompleteAdvanced, suffixValuesRangeString1);
        }
        if (suffixValuesRange.size() == 4) {
            suffixValue1 = ((Number) suffixValues.get(0)).intValue();
            suffixValue2 = ((Number) suffixValues.get(1)).intValue();
            suffixValuesRangeString1 = suffixValue1 + "(" + suffixValuesRange.get(0) + " - " + suffixValuesRange.get(1) + ")";
            suffixValuesRangeString2 = suffixValue2 + "(" + suffixValuesRange.get(2) + " - " + suffixValuesRange.get(3) + ")";
            suffixValuesRangeStringCompleteNormal =String.format(suffixValuesRangeStringCompleteNormal, suffixValue1, suffixValue2);
            suffixValuesRangeStringCompleteAdvanced =String.format(suffixValuesRangeStringCompleteAdvanced, suffixValuesRangeString1, suffixValuesRangeString2);
        }

        if (descriptionType.equals("Advanced")) {
            return "\"" + getSuffixName() + "\"(Tier " + getSuffixTier() + ") " + suffixValuesRangeStringCompleteAdvanced;
        }
        else {
            return suffixValuesRangeStringCompleteNormal;
        }
    }
}
