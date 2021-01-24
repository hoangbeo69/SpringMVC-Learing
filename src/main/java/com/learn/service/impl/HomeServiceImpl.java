package com.learn.service.impl;

import com.learn.service.HomeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Override
    public List<String> loadMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Option 1");
        menu.add("Option 2");
        menu.add("Option 3");
        return menu;
    }
}
