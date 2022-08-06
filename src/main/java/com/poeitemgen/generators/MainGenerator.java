package com.poeitemgen.generators;

import com.poeitemgen.items.BodyArmour;
import com.poeitemgen.items.Item;
import com.poeitemgen.items.modifiers.Modifier;
import com.poeitemgen.items.modifiers.Prefix;
import com.poeitemgen.items.modifiers.Suffix;
import com.poeitemgen.items.utils.UtilMethods;

import java.io.IOException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class MainGenerator {

    public MainGenerator() {
    }

    //Methods --------------------------------------------------------------------------------------
    public static Item generateItem(String itemType, int itemLevel, String itemRarity) throws Exception {

        Item generatedItem;

        //Check Item level and Item rarity
        if (!(itemLevel > 0 && itemLevel <= 100)) {
            itemLevel = 100;
        }

        if (!(itemRarity.equals("Normal") || itemRarity.equals("Magic") || itemRarity.equals("Rare"))) {
            itemRarity = "Rare";
        }

        //Pick item generator
        if (itemType.equals("Armour")) {
            generatedItem = generateRandomArmour(itemLevel, itemRarity);
        }
        else if(itemType.equals("Gloves")) {
            generatedItem = new BodyArmour("Generated Gloves");
        }
        else {
            generatedItem = new BodyArmour("No item selected");
        }

        return generatedItem;
    }

    public static BodyArmour generateRandomArmour(int itemLevel, String itemRarity) throws Exception {

        BodyArmour generatedBodyArmour = new BodyArmour("Generated Armour");

        if (itemLevel > 0 && itemLevel <= 100) {
            generatedBodyArmour.setItemLevel(itemLevel);
        }
        else {
            generatedBodyArmour.setItemLevel(100);
        }

        if (itemRarity.equals("Normal") || itemRarity.equals("Magic") || itemRarity.equals("Rare")) {
            generatedBodyArmour.setItemRarity(itemRarity);
        }
        else {
            generatedBodyArmour.setItemRarity("Rare");
        }

        if (generatedBodyArmour.getArmourType() == BodyArmour.ArmourType.ARMOUR) {

            //Filepath to relevant .json file
            String jsonFilePathArmour = "src/main/java/com/poeitemgen/items/itemData/bodyArmours/Armour.json";
            String jsonFilePathPrefix = "src/main/java/com/poeitemgen/items/modifiers/modifiersData/Prefixes.json";
            String jsonFilePathSuffix = "src/main/java/com/poeitemgen/items/modifiers/modifiersData/Suffixes.json";

            JSONObject selectedBase = parsedRandomBase(jsonFilePathArmour);

            generatedBodyArmour.setBaseType((String) selectedBase.get("baseType"));

            //Set base value
            generatedBodyArmour.setBaseArmourValue(UtilMethods.parseRandomValueInRange((String) selectedBase.get("armourValue")));
            generatedBodyArmour.setBaseEnergyShieldValue(UtilMethods.parseRandomValueInRange((String) selectedBase.get("energyShieldValue")));
            generatedBodyArmour.setBaseEvasionValue((UtilMethods.parseRandomValueInRange((String) selectedBase.get("evasionValue"))));
            generatedBodyArmour.setBaseWardValue(UtilMethods.parseRandomValueInRange((String) selectedBase.get("wardValue")));

            //Set total values after modifiers
            generatedBodyArmour.setArmourValue(UtilMethods.parseRandomValueInRange((String) selectedBase.get("armourValue")));
            generatedBodyArmour.setEnergyShieldValue(UtilMethods.parseRandomValueInRange((String) selectedBase.get("energyShieldValue")));
            generatedBodyArmour.setEvasionValue((UtilMethods.parseRandomValueInRange((String) selectedBase.get("evasionValue"))));
            generatedBodyArmour.setWardValue(UtilMethods.parseRandomValueInRange((String) selectedBase.get("wardValue")));

            for(int i = 0; i < UtilMethods.getRandomNumberInRange(1,3); i++) {
                Prefix randomPrefix = (Prefix) parsedRandomModifier(jsonFilePathPrefix, generatedBodyArmour, "Prefix");

                if (randomPrefix != null) {
                    generatedBodyArmour.addPrefix(randomPrefix);
                }
            }

            for(int i = 0; i < UtilMethods.getRandomNumberInRange(1,3); i++) {
                Suffix randomSuffix = (Suffix) parsedRandomModifier(jsonFilePathSuffix, generatedBodyArmour, "Suffix");

                if (randomSuffix != null) {
                    generatedBodyArmour.addSuffix(randomSuffix);
                }
            }

            calculateItemDefenceValues(generatedBodyArmour);
        }


        return generatedBodyArmour;

    }

    public static JSONObject parsedRandomBase(String filePath) throws IOException, ParseException {

        Object jsonFile = new JSONParser().parse(new FileReader(filePath));
        // typecasting obj to JSONArray
        JSONArray armourArray = (JSONArray) jsonFile;

        return (JSONObject) armourArray.get(UtilMethods.getRandomNumberInRange(0,armourArray.size() - 1));
    }

    public static Modifier parsedRandomModifier(String filePath, Item generatedItem, String modifierType) throws IOException, ParseException {

        Object jsonFile = new JSONParser().parse(new FileReader(filePath));
        JSONArray modifierArray = (JSONArray) jsonFile;

        if (modifierType.equals("Prefix")) {
            return generateRandomPrefix(modifierArray, generatedItem);
        }
        else if(modifierType.equals("Suffix")) {
            return generateRandomSuffix(modifierArray, generatedItem);
        }
        else {
            return null;
        }

    }

    public static Prefix generateRandomPrefix(JSONArray modifierArray, Item generatedItem) {


        Prefix randomPrefix = null;
        boolean isPrefixValid = false;
        int allPrefixWeight = 0;

        for(int i = 0; i < 100; i++) {

            //Weight calculations
            if (allPrefixWeight == 0) {
                for (Object prefix : modifierArray) {
                    if (generatedItem.getItemLevel() >= (((Long) ((JSONObject) prefix).get("prefixItemLevel")).intValue())) {
                        allPrefixWeight += (((Long) ((JSONObject) prefix).get("prefixWeight")).intValue());
                    }
                }
            }

            int randomWeight = UtilMethods.getRandomNumberInRange(1, allPrefixWeight);

            int currentAccumulatedWeight = 0;
            int currentAccumulatedWeightTemp = 0;
            JSONObject randomModifierObject = null;

            for (Object prefix : modifierArray) {

                if (generatedItem.getItemLevel() >= (((Long) ((JSONObject) prefix).get("prefixItemLevel")).intValue())) {
                    currentAccumulatedWeight += (((Long) ((JSONObject) prefix).get("prefixWeight")).intValue());

                    if ((randomWeight > currentAccumulatedWeightTemp) && (randomWeight <= currentAccumulatedWeight)) {
                        randomModifierObject = (JSONObject) prefix;
                        break;
                    }

                    currentAccumulatedWeightTemp += (((Long) ((JSONObject) prefix).get("prefixWeight")).intValue());
                }
            }

//            System.out.println("============================");
//            System.out.println("Lower bound: " + currentAccumulatedWeightTemp);
//            System.out.println("Selected weight: " + randomWeight);
//            System.out.println("Upper bound: " + currentAccumulatedWeight);
//            System.out.println("Selected modifier: " + (String) randomModifierObject.get("prefixName"));
//            System.out.println("============================");

            if (randomModifierObject == null) {
                break;
            }

            randomPrefix = new Prefix(
                    (String) randomModifierObject.get("prefixName"),
                    ((Long) randomModifierObject.get("prefixItemLevel")).intValue(),
                    ((Long) randomModifierObject.get("prefixTier")).intValue(),
                    (String) randomModifierObject.get("prefixType"),
                    ((Long) randomModifierObject.get("prefixGroupID")).intValue(),
                    (String) randomModifierObject.get("prefixText"),
                    (String) randomModifierObject.get("prefixInfluenceType"),
                    (List<String>) randomModifierObject.get("prefixTags"),
                    (List<Integer>) randomModifierObject.get("prefixValues"),
                    (List<Integer>) randomModifierObject.get("prefixValuesRange"));

            //Check if prefix can be added
            boolean canAddVeiled = !(((generatedItem.getAllPrefixTypes().contains("Aisling")) || (generatedItem.getAllSuffixTypes().contains("Aisling"))) && (randomPrefix.getPrefixType().equals("Aisling")));
            boolean doesAlreadyExist = generatedItem.getAllPrefixGroupID().contains(randomPrefix.getPrefixGroupID());
            boolean isItemLevelEnough = !(randomPrefix.getPrefixItemLevel() > generatedItem.getItemLevel());
            boolean isSpaceAvailable = !(generatedItem.getItemRarity().equals("Magic") && generatedItem.getPrefixSet().size() >= 1);

            if ((canAddVeiled) && (!doesAlreadyExist) && (isItemLevelEnough) && (isSpaceAvailable)) {
                isPrefixValid = true;
            }
            if (isPrefixValid) {
                break;
            }
        }

        if (randomPrefix.getPrefixValuesRange().size() == 2) {
            List<Integer> prefixValues = new ArrayList<Integer>();
            Integer valueRange1 = ((Number) randomPrefix.getPrefixValuesRange().get(0)).intValue();
            Integer valueRange2 = ((Number) randomPrefix.getPrefixValuesRange().get(1)).intValue();

            //Get random value for first range
            if (valueRange1 < valueRange2) {
                prefixValues.add(UtilMethods.getRandomNumberInRange(valueRange1,valueRange2));
            }
            else if (valueRange1.equals(valueRange2)) {
                prefixValues.add(valueRange1);
            }
            else {
                prefixValues.add(UtilMethods.getRandomNumberInRange(valueRange2,valueRange1));
            }

            randomPrefix.setPrefixValues(prefixValues);

        }
        if (randomPrefix.getPrefixValuesRange().size() == 4) {
            List<Integer> prefixValues = new ArrayList<Integer>();
            Integer valueRange1 = ((Number) randomPrefix.getPrefixValuesRange().get(0)).intValue();
            Integer valueRange2 = ((Number) randomPrefix.getPrefixValuesRange().get(1)).intValue();
            Integer valueRange3 = ((Number) randomPrefix.getPrefixValuesRange().get(2)).intValue();
            Integer valueRange4 = ((Number) randomPrefix.getPrefixValuesRange().get(3)).intValue();

            //Get random value for first range
            if (valueRange1 < valueRange2) {
                prefixValues.add(UtilMethods.getRandomNumberInRange(valueRange1,valueRange2));
            }
            else if (valueRange1.equals(valueRange2)) {
                prefixValues.add(valueRange1);
            }
            else {
                prefixValues.add(UtilMethods.getRandomNumberInRange(valueRange2,valueRange1));
            }

            //Get random value for second range
            if (valueRange3 < valueRange4) {
                prefixValues.add(UtilMethods.getRandomNumberInRange(valueRange3,valueRange4));
            }
            else if (valueRange3.equals(valueRange4)) {
                prefixValues.add(valueRange3);
            }
            else {
                prefixValues.add(UtilMethods.getRandomNumberInRange(valueRange4,valueRange3));
            }

            randomPrefix.setPrefixValues(prefixValues);
        }

        return randomPrefix;

    }

    public static Suffix generateRandomSuffix(JSONArray modifierArray, Item generatedItem) {


        Suffix randomSuffix = null;
        boolean isSuffixValid = false;
        int allSuffixWeight = 0;

        for(int i = 0; i < 100; i++) {

            //Weight calculations
            if (allSuffixWeight == 0) {
                for (Object suffix : modifierArray) {
                    if (generatedItem.getItemLevel() >= (((Long) ((JSONObject) suffix).get("suffixItemLevel")).intValue())) {
                        allSuffixWeight += (((Long) ((JSONObject) suffix).get("suffixWeight")).intValue());
                    }
                }
            }

            int randomWeight = UtilMethods.getRandomNumberInRange(1, allSuffixWeight);

            int currentAccumulatedWeight = 0;
            int currentAccumulatedWeightTemp = 0;
            JSONObject randomModifierObject = null;

            for (Object suffix : modifierArray) {

                if (generatedItem.getItemLevel() >= (((Long) ((JSONObject) suffix).get("suffixItemLevel")).intValue())) {
                    currentAccumulatedWeight += (((Long) ((JSONObject) suffix).get("suffixWeight")).intValue());

                    if ((randomWeight > currentAccumulatedWeightTemp) && (randomWeight <= currentAccumulatedWeight)) {
                        randomModifierObject = (JSONObject) suffix;
                        break;
                    }

                    currentAccumulatedWeightTemp += (((Long) ((JSONObject) suffix).get("suffixWeight")).intValue());
                }
            }

            if (randomModifierObject == null) {
                break;
            }

            randomSuffix = new Suffix(
                    (String) randomModifierObject.get("suffixName"),
                    ((Long) randomModifierObject.get("suffixItemLevel")).intValue(),
                    ((Long) randomModifierObject.get("suffixTier")).intValue(),
                    (String) randomModifierObject.get("suffixType"),
                    ((Long) randomModifierObject.get("suffixGroupID")).intValue(),
                    (String) randomModifierObject.get("suffixText"),
                    (String) randomModifierObject.get("suffixInfluenceType"),
                    (List<String>) randomModifierObject.get("suffixTags"),
                    (List<Integer>) randomModifierObject.get("suffixValues"),
                    (List<Integer>) randomModifierObject.get("suffixValuesRange"));

            //Check if suffix can be added
            boolean canAddVeiled = !(((generatedItem.getAllPrefixTypes().contains("Aisling")) || (generatedItem.getAllSuffixTypes().contains("Aisling"))) && (randomSuffix.getSuffixType().equals("Aisling")));
            boolean doesAlreadyExist = generatedItem.getAllSuffixGroupID().contains(randomSuffix.getSuffixGroupID());
            boolean isItemLevelEnough = !(randomSuffix.getSuffixItemLevel() > generatedItem.getItemLevel());
            boolean isSpaceAvailable = !(generatedItem.getItemRarity().equals("Magic") && generatedItem.getSuffixSet().size() >= 1);

            if ((canAddVeiled) && (!doesAlreadyExist) && (isItemLevelEnough) && (isSpaceAvailable)) {
                isSuffixValid = true;
            }
            if (isSuffixValid) {
                break;
            }
        }

        if (randomSuffix.getSuffixValuesRange().size() == 2) {
            List<Integer> suffixValues = new ArrayList<Integer>();
            Integer valueRange1 = ((Number) randomSuffix.getSuffixValuesRange().get(0)).intValue();
            Integer valueRange2 = ((Number) randomSuffix.getSuffixValuesRange().get(1)).intValue();

            //Get random value for first range
            if (valueRange1 < valueRange2) {
                suffixValues.add(UtilMethods.getRandomNumberInRange(valueRange1,valueRange2));
            }
            else if (valueRange1.equals(valueRange2)) {
                suffixValues.add(valueRange1);
            }
            else {
                suffixValues.add(UtilMethods.getRandomNumberInRange(valueRange2,valueRange1));
            }

            randomSuffix.setSuffixValues(suffixValues);

        }
        if (randomSuffix.getSuffixValuesRange().size() == 4) {
            List<Integer> suffixValues = new ArrayList<Integer>();
            Integer valueRange1 = ((Number) randomSuffix.getSuffixValuesRange().get(0)).intValue();
            Integer valueRange2 = ((Number) randomSuffix.getSuffixValuesRange().get(1)).intValue();
            Integer valueRange3 = ((Number) randomSuffix.getSuffixValuesRange().get(2)).intValue();
            Integer valueRange4 = ((Number) randomSuffix.getSuffixValuesRange().get(3)).intValue();

            //Get random value for first range
            if (valueRange1 < valueRange2) {
                suffixValues.add(UtilMethods.getRandomNumberInRange(valueRange1,valueRange2));
            }
            else if (valueRange1.equals(valueRange2)) {
                suffixValues.add(valueRange1);
            }
            else {
                suffixValues.add(UtilMethods.getRandomNumberInRange(valueRange2,valueRange1));
            }

            //Get random value for second range
            if (valueRange3 < valueRange4) {
                suffixValues.add(UtilMethods.getRandomNumberInRange(valueRange3,valueRange4));
            }
            else if (valueRange3.equals(valueRange4)) {
                suffixValues.add(valueRange3);
            }
            else {
                suffixValues.add(UtilMethods.getRandomNumberInRange(valueRange4,valueRange3));
            }

            randomSuffix.setSuffixValues(suffixValues);
        }

        return randomSuffix;

    }

    public static void calculateItemDefenceValues(Item generatedItem) {

        int armourValueIncreasePercentLocal = 0;
        int evasionValueIncreasePercentLocal = 0;
        int energyShieldValueIncreasePercentLocal = 0;
        int wardValueIncreasePercentLocal = 0;

        int armourValueIncreaseFlatLocal = 0;
        int evasionValueIncreaseFlatLocal = 0;
        int energyShieldValueIncreaseFlatLocal = 0;
        int wardValueIncreaseFlatLocal = 0;


        for (Prefix prefix : generatedItem.getPrefixSet()) {
            //Calculate armour change
            if (prefix.getPrefixTags().contains("ArmourPercentIncrease")) {
                armourValueIncreasePercentLocal += prefix.getPrefixValues().get(0);
            }
            if (prefix.getPrefixTags().contains("ArmourFlatIncrease")) {
                armourValueIncreaseFlatLocal += prefix.getPrefixValues().get(0);
            }

            //Calculate evasion change
            if (prefix.getPrefixTags().contains("EvasionPercentIncrease")) {
                evasionValueIncreasePercentLocal += prefix.getPrefixValues().get(0);
            }
            if (prefix.getPrefixTags().contains("EvasionFlatIncrease")) {
                evasionValueIncreaseFlatLocal += prefix.getPrefixValues().get(0);
            }

            //Calculate energy shield change
            if (prefix.getPrefixTags().contains("EnergyShieldPercentIncrease")) {
                energyShieldValueIncreasePercentLocal += prefix.getPrefixValues().get(0);
            }
            if (prefix.getPrefixTags().contains("EnergyShieldFlatIncrease")) {
                energyShieldValueIncreaseFlatLocal += prefix.getPrefixValues().get(0);
            }

            //Calculate ward change
            if (prefix.getPrefixTags().contains("WardPercentIncrease")) {
                wardValueIncreasePercentLocal += prefix.getPrefixValues().get(0);
            }
            if (prefix.getPrefixTags().contains("WardFlatIncrease")) {
                wardValueIncreaseFlatLocal += prefix.getPrefixValues().get(0);
            }
        }

        for (Suffix suffix : generatedItem.getSuffixSet()) {
            //Calculate armour change
            if (suffix.getSuffixTags().contains("ArmourPercentIncrease")) {
                armourValueIncreasePercentLocal += suffix.getSuffixValues().get(0);
            }
            if (suffix.getSuffixTags().contains("ArmourFlatIncrease")) {
                armourValueIncreaseFlatLocal += suffix.getSuffixValues().get(0);
            }

            //Calculate evasion change
            if (suffix.getSuffixTags().contains("EvasionPercentIncrease")) {
                evasionValueIncreasePercentLocal += suffix.getSuffixValues().get(0);
            }
            if (suffix.getSuffixTags().contains("EvasionFlatIncrease")) {
                evasionValueIncreaseFlatLocal += suffix.getSuffixValues().get(0);
            }

            //Calculate energy shield change
            if (suffix.getSuffixTags().contains("EnergyShieldPercentIncrease")) {
                energyShieldValueIncreasePercentLocal += suffix.getSuffixValues().get(0);
            }
            if (suffix.getSuffixTags().contains("EnergyShieldFlatIncrease")) {
                energyShieldValueIncreaseFlatLocal += suffix.getSuffixValues().get(0);
            }

            //Calculate ward change
            if (suffix.getSuffixTags().contains("WardPercentIncrease")) {
                wardValueIncreasePercentLocal += suffix.getSuffixValues().get(0);
            }
            if (suffix.getSuffixTags().contains("WardFlatIncrease")) {
                wardValueIncreaseFlatLocal += suffix.getSuffixValues().get(0);
            }
        }

        ((BodyArmour) generatedItem).setArmourValue((((BodyArmour) generatedItem).getBaseArmourValue() + armourValueIncreaseFlatLocal) * (100 + armourValueIncreasePercentLocal) / 100);
        ((BodyArmour) generatedItem).setEvasionValue((((BodyArmour) generatedItem).getBaseEvasionValue() + evasionValueIncreaseFlatLocal) * (100 + evasionValueIncreasePercentLocal) / 100);
        ((BodyArmour) generatedItem).setEnergyShieldValue((((BodyArmour) generatedItem).getBaseEnergyShieldValue() + energyShieldValueIncreaseFlatLocal) * (100 + energyShieldValueIncreasePercentLocal) / 100);
        ((BodyArmour) generatedItem).setWardValue((((BodyArmour) generatedItem).getBaseWardValue() + wardValueIncreaseFlatLocal) * (100 + wardValueIncreasePercentLocal) / 100);

    }
}
