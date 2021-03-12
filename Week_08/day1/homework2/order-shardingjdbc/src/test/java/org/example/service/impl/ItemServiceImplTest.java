package org.example.service.impl;

import org.example.entity.Item;
import org.example.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void saveItem() {
        Item item = new Item(null, "itemHSCode", "itemName", 1,
                12.0d,12.0, 1,System.currentTimeMillis(), System.currentTimeMillis());
        boolean success = itemService.save(item);
        System.out.println(success);
    }

}
