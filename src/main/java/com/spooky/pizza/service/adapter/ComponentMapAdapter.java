package com.spooky.pizza.service.adapter;

import com.spooky.pizza.model.Amount;
import com.spooky.pizza.model.Component;
import com.spooky.pizza.model.Dto.ComponentDto;
import com.spooky.pizza.model.Dto.ComponentMapDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Convert from Map<Component,Amount> ---> to ComponentMapDto and vice versa
 */
public class ComponentMapAdapter extends XmlAdapter<ComponentMapDto, Map<Component, Amount>> {
    @Override
    public Map<Component, Amount> unmarshal(ComponentMapDto componentMapDto) throws Exception {
        Map<Component, Amount> componentMap = new HashMap<>();
        componentMapDto.getComponents().forEach(componentDto ->
                componentMap.put(
                        new Component(componentDto.getName(),
                                componentDto.getPrice()), componentDto.getAmount()));
//        for (ComponentDto itemDto : componentMapDto.getComponents()) {
//            componentMap.put(new Component(itemDto.getName(), itemDto.getPrice()), itemDto.getAmount());
//        }
        return componentMap;
    }

    @Override
    public ComponentMapDto marshal(Map<Component, Amount> componentAmountMap) throws Exception {
        ComponentMapDto componentMapDto = new ComponentMapDto();
        return componentMapDto;
    }
}
