package com.AvailHive1.AvailHive1.services.client;

import com.AvailHive1.AvailHive1.dto.AdDTO;

import java.util.List;

public interface ClientService {

    List<AdDTO> getAllAds();
    List<AdDTO> searchAdByName(String name);
}
