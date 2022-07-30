package com.poeitemgen.items.modifiers;

public class Suffix {

    // Declare fields ------------------------------------------------------------------------------
    public String suffixName = "";
    public int suffixTier = 1;
    public String suffixType = "";
    public int suffixGroupID = 1;

    // ---------------------------------------------------------------------------------------------

    // Constructors --------------------------------------------------------------------------------
    public Suffix(String suffixName, int suffixTier, String suffixType, int suffixGroupID) {
        this.suffixName = suffixName;
        this.suffixTier = suffixTier;
        this.suffixType = suffixType;
        this.suffixGroupID = suffixGroupID;
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

    // ---------------------------------------------------------------------------------------------
}
