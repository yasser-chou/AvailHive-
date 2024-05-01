package com.AvailHive1.AvailHive1.services.company;

import com.AvailHive1.AvailHive1.dto.AdDTO;

import java.io.IOException;

public interface CompanyService  {

    boolean postAd(Long userId, AdDTO adDTO) throws IOException;

}
