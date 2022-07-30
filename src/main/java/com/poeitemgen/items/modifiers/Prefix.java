package com.poeitemgen.items.modifiers;

public class Prefix {

    // Declare fields ------------------------------------------------------------------------------
    public String prefixName = "";
    public int prefixTier = 1;
    public String prefixType = "";
    public int prefixGroupID = 1;

    // ---------------------------------------------------------------------------------------------

    // Constructors --------------------------------------------------------------------------------
    public Prefix(String prefixName, int prefixTier, String prefixType, int prefixGroupID) {
        this.prefixName = prefixName;
        this.prefixTier = prefixTier;
        this.prefixType = prefixType;
        this.prefixGroupID = prefixGroupID;
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

    // ---------------------------------------------------------------------------------------------

    //Methods --------------------------------------------------------------------------------------
    public String toString(){
        return "\"" + getPrefixName() + "\"(Tier " + String.valueOf(getPrefixTier() + ")");
    }
}
