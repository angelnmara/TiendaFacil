package com.lamarrulla.www.tiendafacil.contents;

import com.lamarrulla.www.tiendafacil.listas.itemListMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MenuContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<itemListMenu> ITEMS = new ArrayList<itemListMenu>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, itemListMenu> ITEM_MAP = new HashMap<String, itemListMenu>();

    static String[] Menu = {"Inicio","Alta de artículos","Alta de marca", "Cerrar sesión"};

    private static final int COUNT = Menu.length;

    static {
        // Add some sample items.
        for (int i = 0; i <= COUNT-1; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(itemListMenu item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static itemListMenu createDummyItem(int position) {
        return new itemListMenu(String.valueOf(position), Menu[position].toString(), makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    /*public static class itemListMenu {
        public final String id;
        public final String content;
        public final String details;

        public itemListMenu(String id, String content, String details) {
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
