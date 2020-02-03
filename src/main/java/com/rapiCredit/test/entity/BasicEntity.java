package com.rapiCredit.test.entity;

import com.rapiCredit.test.dto.DTO;
import org.springframework.beans.BeanUtils;

public abstract class BasicEntity<T extends DTO> {
    public abstract T getDTO();

    public BasicEntity() {

    }

    public BasicEntity(T dto) {
        BeanUtils.copyProperties(dto, this);
    }
}
