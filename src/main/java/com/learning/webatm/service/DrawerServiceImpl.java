package com.learning.webatm.service;

import com.learning.webatm.model.Drawer;
import com.learning.webatm.repository.DrawerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrawerServiceImpl implements DrawerService{

    @Autowired
    private DrawerRepo drawerRepo;

    public Drawer getDrawer(Long id){
        return drawerRepo.getDrawerById(id);
    }
    public Drawer save(Drawer drawer){ return drawerRepo.save(drawer); };


}
