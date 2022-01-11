package com.gildedrose;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class GildedRoseTest {

    @Test
    public void simpleItem() {
        Item foo = new Item("foo", 20, 10);
        Item[] items = new Item[] { foo  };
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertEquals(19,foo.sellIn);
        assertEquals(9, foo.quality);
    }
    
    @Test
    public void backstagePassValueIncreasesButCantBeAbove50() {
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 0),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50)
        };

        Item[] updatedItems = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert", 14, 1),
            new Item("Backstage passes to a TAFKAL80ETC concert", 9, 2),
            new Item("Backstage passes to a TAFKAL80ETC concert", 4, 3),
            new Item("Backstage passes to a TAFKAL80ETC concert", 4, 50)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        for (int i = 0; i < 3; i++) {
            assertEquals(updatedItems[0].toString(), app.items[0].toString());
        }
    }

    @Test
    public void cheeseValueIncreasesButCantBeAbove50() {
        Item[] items = new Item[]{
            new Item("Aged Brie", 10, 10),
            new Item("Aged Brie", 0, 10),
            new Item("Aged Brie", 10, 50),
        };

        Item[] updatedItems = new Item[]{
            new Item("Aged Brie", 9, 11),
            new Item("Aged Brie", -1, 12),
            new Item("Aged Brie", 9, 50),
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        for (int i = 0; i < 3; i++) {
            assertEquals(updatedItems[0].toString(), app.items[0].toString());
        }
    }

    @Test
    public void legendaryItemsDontChange() {
        Item[] items = new Item[]{
            new Item("Sulfuras, Hand of Ragnaros", 1, 80)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(1, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    public void defaultItemsValueDecreasesButCantBeUnder0() {
        Item[] items = new Item[]{
            new Item("An item", 10, 10),
            new Item("Another item", 0, 10),
            new Item("Yet another item", 10, 0),
        };

        Item[] updatedItems = new Item[]{
            new Item("An item", 9, 9),
            new Item("Another item", -1, 8),
            new Item("Yet another item", 9, 0),
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        for (int i = 0; i < 3; i++) {
            assertEquals(updatedItems[0].toString(), app.items[0].toString());
        }
    }

}
