package org.example.mapper;


import org.example.entity.ItemEntity;

import java.util.List;

public interface ItemMapper {
    List<ItemEntity> findAll();
    int insertOne(ItemEntity item);
    int update(ItemEntity item);

    int delete(long id);

    int insertBatch(List<ItemEntity> list);

    ItemEntity findById(Long itemId);
}
