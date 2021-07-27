package com.learning.webatm.service;


import com.learning.webatm.model.Drawer;
import org.springframework.stereotype.Service;


public interface DrawerService {

    Drawer getDrawer(Long id);
    Drawer save(Drawer drawer);
}
