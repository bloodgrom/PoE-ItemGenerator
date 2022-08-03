package com.poeitemgen.items.modifiers;

import java.util.ArrayList;
import java.util.List;

public class Prefix {

    // Declare fields ------------------------------------------------------------------------------
    public String prefixName = "";
    public int prefixItemLevel = 1;
    public int prefixTier = 1;
    public String prefixType = "";
    public int prefixGroupID = 1;
    public String prefixText = "";
    public String prefixInfluenceType = "None";
    public List<String> prefixTags = new ArrayList<>();
    public List<Integer> prefixValues = new ArrayList<Integer>();
    public List<Integer> prefixValuesRange = new ArrayList<Integer>();

    // ---------------------------------------------------------------------------------------------

    // Constructors --------------------------------------------------------------------------------
    public Prefix(String prefixName,int prefixItemLevel ,int prefixTier, String prefixType, int prefixGroupID, String prefixText, String prefixInfluenceType, List<String> prefixTags, List<Integer> prefixValues, List<Integer> prefixValuesRange) {
        this.prefixName = prefixName;
        this.prefixItemLevel = prefixItemLevel;
        this.prefixTier = prefixTier;
        this.prefixType = prefixType;
        this.prefixGroupID = prefixGroupID;
        this.prefixText = prefixText;
        this.prefixInfluenceType = prefixInfluenceType;
        this.prefixTags = prefixTags;
        this.prefixValues = prefixValues;
        this.prefixValuesRange = prefixValuesRange;
    }

    // ---------------------------------------------------------------------------------------------

    // Getters & Setters ---------------------------------------------------------------------------
    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public int getPrefixTier() {
        return prefixTier;
    }

    public void setPrefixTier(int prefixTier) {
        this.prefixTier = prefixTier;
    }

    public String getPrefixType() {
        return prefixType;
    }

    public void setPrefixType(String prefixType) {
        this.prefixType = prefixType;
    }

    public int getPrefixGroupID() {
        return prefixGroupID;
    }

    public void setPrefixGroupID(int prefixGroupID) {
        this.prefixGroupID = prefixGroupID;
    }

    public List<Integer> getprefixValuesRange() {
        return prefixValuesRange;
    }

    public void setprefixValuesRange(List<Integer> prefixValuesRange) {
        this.prefixValuesRange = prefixValuesRange;
    }

    public String getPrefixText() {
        return prefixText;
    }

    public void setPrefixText(String prefixText) {
        this.prefixText = prefixText;
    }

    public int getPrefixItemLevel() {
        return prefixItemLevel;
    }

    public void setPrefixItemLevel(int prefixItemLevel) {
        this.prefixItemLevel = prefixItemLevel;
    }

    public String getPrefixInfluenceType() {
        return prefixInfluenceType;
    }

    public void setPrefixInfluenceType(String prefixInfluenceType) {
        this.prefixInfluenceType = prefixInfluenceType;
    }

    public List<Integer> getPrefixValues() {
        return prefixValues;
    }

    public void setPrefixValues(List<Integer> prefixValues) {
        this.prefixValues = prefixValues;
    }

    public List<Integer> getPrefixValuesRange() {
        return prefixValuesRange;
    }

    public void setPrefixValuesRange(List<Integer> prefixValuesRange) {
        this.prefixValuesRange = prefixValuesRange;
    }

    public List<String> getPrefixTags() {
        return prefixTags;
    }

    public void setPrefixTags(List<String> prefixTags) {
        this.prefixTags = prefixTags;
    }

    // ---------------------------------------------------------------------------------------------

    //Methods --------------------------------------------------------------------------------------
    public String toString(String descriptionType){

        String prefixValuesRangeString1 = "";
        String prefixValuesRangeString2 = "";
        Integer prefixValue1 = 1;
        Integer prefixValue2 = 1;
        String prefixValuesRangeStringCompleteNormal = getPrefixText();
        String prefixValuesRangeStringCompleteAdvanced = getPrefixText();
        List<Integer> prefixValuesRange = getprefixValuesRange();
        List<Integer> prefixValues = getPrefixValues();

        if (prefixValuesRange.size() == 2) {
            prefixValue1 = ((Number) prefixValues.get(0)).intValue();
            prefixValuesRangeString1 = prefixValue1 + "(" + prefixValuesRange.get(0) + " - " + prefixValuesRange.get(1) + ")";
            prefixValuesRangeStringCompleteNormal =String.format(prefixValuesRangeStringCompleteNormal, prefixValue1);
            prefixValuesRangeStringCompleteAdvanced =String.format(prefixValuesRangeStringCompleteAdvanced, prefixValuesRangeString1);
        }
        if (prefixValuesRange.size() == 4) {
            prefixValue1 = ((Number) prefixValues.get(0)).intValue();
            prefixValue2 = ((Number) prefixValues.get(1)).intValue();
            prefixValuesRangeString1 = prefixValue1 + "(" + prefixValuesRange.get(0) + " - " + prefixValuesRange.get(1) + ")";
            prefixValuesRangeString2 = prefixValue2 + "(" + prefixValuesRange.get(2) + " - " + prefixValuesRange.get(3) + ")";
            prefixValuesRangeStringCompleteNormal =String.format(prefixValuesRangeStringCompleteNormal, prefixValue1, prefixValue2);
            prefixValuesRangeStringCompleteAdvanced =String.format(prefixValuesRangeStringCompleteAdvanced, prefixValuesRangeString1, prefixValuesRangeString2);
        }

        if (descriptionType.equals("Advanced")) {
            return "\"" + getPrefixName() + "\"(Tier " + getPrefixTier() + ") " + prefixValuesRangeStringCompleteAdvanced;
        }
        else {
            return prefixValuesRangeStringCompleteNormal;
        }
    }
}
