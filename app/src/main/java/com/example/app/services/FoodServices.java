package com.example.app.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.app.models.RestaurantItem;

public class FoodServices {
    private static final Logger log = LoggerFactory.getLogger(FoodServices.class);
    private String path = System.getProperty("user.dir") + "/app/src/main/resources/menus/items/items.txt";
    private String mapPath = System.getProperty("user.dir") + "/app/src/main/resources/restaurantMapping.csv";
    private String url = "https://www.skipthedishes.com";
    private Set<RestaurantItem> items = null;
    private Map<String, String> rMap = null;
    public Set<RestaurantItem> getRestaurantItems(List<String> searchItems) throws Exception {
        Set<RestaurantItem> ans = new HashSet<>();

		Set<String> searchWords = searchItems.stream().map(String::toLowerCase).collect(Collectors.toSet());
		if(rMap == null) {
            rMap = new HashMap<>();
            try (BufferedReader br = new BufferedReader(new FileReader(mapPath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split(", ");
                    rMap.put(words[0], url + words[1]);
                }
            }
            
        }
		if(items == null) {
            items = new HashSet<>();
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] words = line.split("###");
                    String link = rMap.containsKey(words[2]) ? rMap.get(words[2]) : "";
                    RestaurantItem r = new RestaurantItem(Float.parseFloat(words[1]), words[0].toLowerCase(), words[2], link, (words.length >= 4 ? words[3] : ""));
                    items.add(r);
                }
            }
        }
		
		int n = searchWords.size();
		while(ans.isEmpty() && n > 0) {
			for(RestaurantItem i : items) {
				int matchedWords = 0;
				for(String ss : searchWords) {
					if(i.itemName.contains(ss)) {
						matchedWords++;
					}
				}
				if(matchedWords >= n) {
					ans.add(i);
				}
			}
			n--;
		}
		// System.out.println(ans.size());
		// for(RestaurantItem i : ans) {
		// 	System.out.println(i.itemName + " : " + i.price + " : " + i.restaurant + " : " + i.imageLink);
		// }
        return ans;
    }
}
