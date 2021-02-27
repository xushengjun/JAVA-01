package org.example.service;

import org.example.entity.ItemEntity;
import org.example.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemMapper itemMapper;

    public int add(ItemEntity item){
        return itemMapper.insertOne(item);
    }

    public int addBatch(List<ItemEntity> itemList){
        return itemMapper.insertBatch(itemList);
    }

    public int update(ItemEntity item){
        return itemMapper.update(item);
    }

    public int delete(long id){
        return itemMapper.delete(id);
    }

    public List<ItemEntity> getAll(){
        return itemMapper.findAll();
    }
}
