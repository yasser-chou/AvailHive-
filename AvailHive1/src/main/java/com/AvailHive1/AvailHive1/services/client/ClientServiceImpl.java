package com.AvailHive1.AvailHive1.services.client;

import com.AvailHive1.AvailHive1.Repository.AdRepository;
import com.AvailHive1.AvailHive1.dto.AdDTO;
import com.AvailHive1.AvailHive1.entity.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private AdRepository adRepository;

    public List<AdDTO> getAllAds(){
        return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public List<AdDTO> searchAdByName(String name){
        return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }
}
