package com.gildedrose;

import java.util.Arrays;
import java.util.List;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    /*
     /!\ Do not change code above this line /!\
     */

    final List<String> CHEESE = Arrays.asList("Aged Brie");
    final List<String> BACKSTAGE_PASS = Arrays.asList("Backstage passes to a TAFKAL80ETC concert");
    final List<String> LEGENDARY_ITEM = Arrays.asList("Sulfuras, Hand of Ragnaros");

    public void updateQuality() {
        for (Item item : items) {
            if (CHEESE.contains(item.name)) {
                item.sellIn -= 1;
                updateQualityForCheese(item);
            } else if (BACKSTAGE_PASS.contains(item.name)) {
                item.sellIn -= 1;
                updateQualityForBackstagePass(item);
            } else if (LEGENDARY_ITEM.contains((item.name))) {
                // Do nothing.
            } else if (item.name.startsWith("Conjured ")) {
                item.sellIn -= 1;
                updateQualityForConjuredItem(item);
            } else {
                item.sellIn -= 1;
                updateQualityForDefaultItem(item);
            }
        }
    }

    private void updateQualityForCheese(Item item) {
        if (item.sellIn < 0) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }

        maximumItemQuality(item, 50);
    }

    private void updateQualityForBackstagePass(Item item) {
        if (item.sellIn < 0) {
            item.quality = 0;
        } else if (item.sellIn < 5) {
            item.quality += 3;
        } else if (item.sellIn < 10) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }

        maximumItemQuality(item, 50);
    }

    private void updateQualityForDefaultItem(Item item) {
        if (item.sellIn >= 0) {
            item.quality -= 1;
        } else {
            item.quality -= 2;
        }

        minimumItemQuality(item, 0);
    }

    private void updateQualityForConjuredItem(Item item) {
        if (item.sellIn >= 0) {
            item.quality -= 2;
        } else {
            item.quality -= 4;
        }

        minimumItemQuality(item, 0);
    }

    private void maximumItemQuality(Item item, int max) {
        if (item.quality > max) {
            item.quality = max;
        }
    }

    private void minimumItemQuality(Item item, int min) {
        if (item.quality < min) {
            item.quality = min;
        }
    }

}
