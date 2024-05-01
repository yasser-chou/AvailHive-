package com.AvailHive1.AvailHive1.services.company;

import com.AvailHive1.AvailHive1.Repository.AdRepository;
import com.AvailHive1.AvailHive1.Repository.UserRepository;
import com.AvailHive1.AvailHive1.dto.AdDTO;
import com.AvailHive1.AvailHive1.entity.Ad;
import com.AvailHive1.AvailHive1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    public boolean postAd(Long userId, AdDTO adDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            Ad ad=new Ad();
            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            ad.setImg(adDTO.getImg().getBytes());
            ad.setPrice(adDTO.getPrice());
            ad.setUser(optionalUser.get());

            adRepository.save(ad);
            return true;
        }
        return false;
    }
}
