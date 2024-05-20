package com.AvailHive1.AvailHive1.services.company;

import com.AvailHive1.AvailHive1.repository.AdRepository;
import com.AvailHive1.AvailHive1.repository.ReservationRepository;
import com.AvailHive1.AvailHive1.repository.UserRepository;
import com.AvailHive1.AvailHive1.dto.AdDTO;
import com.AvailHive1.AvailHive1.dto.ReservationDTO;
import com.AvailHive1.AvailHive1.entity.Ad;
import com.AvailHive1.AvailHive1.entity.Reservation;
import com.AvailHive1.AvailHive1.entity.User;
import com.AvailHive1.AvailHive1.enums.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean postAd(Long userId, AdDTO adDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Ad ad = new Ad();
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

    public List<AdDTO> getAllAds(Long userId){
        return adRepository.findAllByUserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public AdDTO getAdById(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if(optionalAd.isPresent()){
            return optionalAd.get().getAdDto();
        }
        return null;
    }

    public boolean updateAd(Long adId,AdDTO adDTO) throws IOException {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if(optionalAd.isPresent()){
            Ad ad = optionalAd.get();

            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            ad.setPrice(adDTO.getPrice());

            if(adDTO.getImg() != null){
                ad.setImg(adDTO.getImg().getBytes());
            }

            adRepository.save(ad);
            return true;

        }else{
            return false;
        }
    }

    public boolean deleteAd(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if(optionalAd.isPresent()){
            adRepository.delete(optionalAd.get());
            return true;
        }
        return false;
    }

    public List<ReservationDTO> getAllAdBookings(Long companyId){
        return reservationRepository.findAllByCompanyId(companyId).stream()
                .map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    public boolean changeBookingStatus(Long bookingId, String status){
        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
        if(optionalReservation.isPresent()){
                Reservation existingReservation=optionalReservation.get();
                if(Objects.equals(status,"Approve")){
                    existingReservation.setReservationStatus(ReservationStatus.APPROVED);
                }else{
                    existingReservation.setReservationStatus(ReservationStatus.REJECTED);
                }

                reservationRepository.save(existingReservation);
                return true;
        }
            return false;

    }
}
