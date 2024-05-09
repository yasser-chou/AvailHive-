package com.AvailHive1.AvailHive1.services.company;

import com.AvailHive1.AvailHive1.dto.AdDTO;

import java.io.IOException;
import java.util.List;

public interface CompanyService  {

    boolean postAd(Long userId, AdDTO adDTO) throws IOException;
    List<AdDTO> getAllAds(Long userId);
    AdDTO getAdById(Long adId);
    boolean updateAd(Long adId,AdDTO adDTO) throws IOException;
    boolean deleteAd(Long adId);

}
