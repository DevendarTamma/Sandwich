package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    static String NAME="name";
    static String MAIN_NAME="mainName";
    static String ALSO_KNOWN_AS="alsoKnownAs";
    static String PLACE_OF_ORIGIN="placeOfOrigin";
    static String DESCRIPTION="description";
    static  String IMAGE="image";
    static  String INGREDIENTS="ingredients";

    public static Sandwich parseSandwichJson(String sandwichJson) {

        if (sandwichJson==null) {
            return null;
        }
        Sandwich sandwichObject = null;
        try {
            JSONObject baseJson = new JSONObject(sandwichJson);
            JSONObject name = baseJson.getJSONObject(NAME);
            String mainName = name.getString(MAIN_NAME);
            List<String> alsoKnownAsList = new ArrayList<>();
            JSONArray alsoKnownAsArray = name.getJSONArray(ALSO_KNOWN_AS);
            int countAlsoKnownAsArray = alsoKnownAsArray.length();
            for (int i = 0; i < countAlsoKnownAsArray; i++) {
                String otherName = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(otherName);
            }
            String placeOfOrigin = baseJson.getString(PLACE_OF_ORIGIN);
            String description = baseJson.getString(DESCRIPTION);
            String image = baseJson.getString(IMAGE);
            List<String> ingredientsList = new ArrayList<>();
            JSONArray ingredientsArray = baseJson.getJSONArray(INGREDIENTS);
            int countIngredientsArray = ingredientsArray.length();
            for (int j = 0; j < countIngredientsArray; j++) {
                String ingredient = ingredientsArray.getString(j);
                ingredientsList.add(ingredient);
            }
            sandwichObject = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);


        } catch (JSONException e) {
            Log.e("JsonUtils", "Problem parsing the Sandwich JSON Object", e);
        }
        return sandwichObject;
    }
}
