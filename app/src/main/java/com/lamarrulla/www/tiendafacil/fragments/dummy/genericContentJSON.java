package com.lamarrulla.www.tiendafacil.fragments.dummy;

import android.content.Context;
import android.widget.Toast;

import com.lamarrulla.www.tiendafacil.listas.itemListArticle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.security.AccessController.getContext;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class genericContentJSON {

    public static ArrayList Item;
    private static int COUNT; // = 25;

    public static void getData(JSONArray jsa, String lista) throws JSONException {
        Item = new ArrayList();
        COUNT = jsa.length()-1;
        for(int i = 0; i < COUNT; i++){
            JSONObject jso = new JSONObject(jsa.get(i).toString());

            switch (lista){
                case "itemListArticle":
                    Item.add(new itemListArticle(jso.getInt("article_id"), jso.getString("article_name"), jso.getString("article_desc"), jso.getDouble("article_precio"), jso.getDouble("article_costo"), jso.getInt("article_foto"), jso.getInt("article_stock")));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * An array of sample (dummy) items.
     */
    /*public static final List<itemListArticle> ITEMS = new ArrayList<itemListArticle>();

    *//**
     * A map of sample (dummy) items, by ID.
     *//*
    public static final Map<String, itemListArticle> ITEM_MAP = new HashMap<String, itemListArticle>();

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(itemListArticle item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getArticle_id().toString(), item);
    }

    private static itemListArticle createDummyItem(int position) {
        return new itemListArticle(position, "Item " + position, makeDetails(position), 1.0, 1.0, 0, 0);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        *//*for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }*//*
        builder.append("\nMore details information here.");
        return builder.toString();
    }

    *//**
     * A dummy item representing a piece of content.
     *//*
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }*/
}
