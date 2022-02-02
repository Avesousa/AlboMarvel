package com.albo.marvel.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.albo.marvel.services.ScheduledServices;
import com.albo.marvel.services.CharacterServices;

@Component
public class ScheduledServiceImp implements ScheduledServices{
    
    @Autowired
    private CharacterServices characterService;
    
    @Override
    @Scheduled(cron = "0 30 0 ? * *")
    public void syncData() {
        characterService.updateData();
    }
    
}
