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
    public List<Integer> prefixValues = new ArrayList<Integer>();

    // ---------------------------------------------------------------------------------------------

    // Constructors --------------------------------------------------------------------------------
    public Prefix(String prefixName,int prefixItemLevel ,int prefixTier, String prefixType, int prefixGroupID, String prefixText, String prefixInfluenceType, List<Integer> prefixValues) {
        this.prefixName = prefixName;
        this.prefixItemLevel = prefixItemLevel;
        this.prefixTier = prefixTier;
        this.prefixType = prefixType;
        this.prefixGroupID = prefixGroupID;
        this.prefixText = prefixText;
        this.prefixInfluenceType = prefixInfluenceType;
        this.prefixValues = prefixValues;
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

    public List<Integer> getPrefixValues() {
        return prefixValues;
    }

    public void setPrefixValues(List<Integer> prefixValues) {
        this.prefixValues = prefixValues;
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

    // ---------------------------------------------------------------------------------------------

    //Methods --------------------------------------------------------------------------------------
    public String toString(String descriptionType){

        String prefixValuesString1 = "";
        String prefixValuesString2 = "";
        String prefixValuesStringComplete = getPrefixText();
        List<Integer> prefixValues = getPrefixValues();

        if (prefixValues.size() == 2) {
            prefixValuesString1 = "(" + prefixValues.get(0) + " - " + prefixValues.get(1) + ")";
            prefixValuesStringComplete =String.format(prefixValuesStringComplete, prefixValuesString1);
        }
        if (prefixValues.size() == 4) {
            prefixValuesString1 = "(" + prefixValues.get(0) + " - " + prefixValues.get(1) + ")";
            prefixValuesString2 = "(" + prefixValues.get(2) + " - " + prefixValues.get(3) + ")";
            prefixValuesStringComplete =String.format(prefixValuesStringComplete, prefixValuesString1, prefixValuesString2);
        }

        if (descriptionType.equals("Advanced")) {
            return "\"" + getPrefixName() + "\"(Tier " + getPrefixTier() + ") " + prefixValuesStringComplete;
        }
        else {
            return prefixValuesStringComplete;
        }
    }
}
