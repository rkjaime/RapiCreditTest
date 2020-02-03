package com.rapiCredit.test.utils;

import java.util.ArrayList;
import java.util.List;

import com.rapiCredit.test.dto.DTO;
import com.rapiCredit.test.entity.BasicEntity;

public abstract class Utilities extends DTO {

    public static <T extends DTO> List<T> convertEntityListToDTO(List<? extends BasicEntity<? extends T>> entities) {
        List<T> dtos = new ArrayList<>();
        for (BasicEntity<? extends T> entity : entities) {
            dtos.add(entity.getDTO());
        }
        return dtos;
    }
}
