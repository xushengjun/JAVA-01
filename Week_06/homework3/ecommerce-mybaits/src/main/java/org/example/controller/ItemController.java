package org.example.controller;

import org.example.entity.ItemEntity;
import org.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/item",produces = "application/json; charset=utf-8")//解决中文乱码
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/add")
    public void addItem(){
        ItemEntity item = new ItemEntity();
        item.setItemHSCode("HS_SDNVF_SDNC");
        item.setItemName("肥皂");
        item.setItemPrice(123.43);
        item.setItemType(1);
        item.setItemWeight(12.0d);
        item.setStatus(1);
        itemService.add(item);

    }
    @GetMapping("/addbatch")
    public void addBatch(){
        long startTime = System.currentTimeMillis();
        List<ItemEntity> itemList = new ArrayList<>(1000000);
        for (int i = 0; i < 1000000; i++) {
            ItemEntity item = new ItemEntity();
            item.setItemHSCode("HS_SDNVF_SDNC_"+i);
            item.setItemName("肥皂"+i);
            item.setItemPrice(10d+i);
            item.setItemType(1);
            item.setItemWeight(12.0d);
            item.setStatus(1);
            itemList.add(item);
            if (i%500==0){
                itemService.addBatch(itemList);
                itemList.clear();
            }
        }
        itemService.addBatch(itemList);
        System.out.println("插入一百万行数据，耗时："+(System.currentTimeMillis()-startTime)/1000);
    }

    @GetMapping("/update")
    public void update(){
        ItemEntity item = new ItemEntity();
        item.setId(1L);
        item.setItemName("肥皂");
        itemService.update(item);
    }

    @GetMapping("/list")
    public List<ItemEntity> getAll(){
        return itemService.getAll();
    }

    @GetMapping("/del")
    public void delete(){
        itemService.delete(1L);
    }
}
