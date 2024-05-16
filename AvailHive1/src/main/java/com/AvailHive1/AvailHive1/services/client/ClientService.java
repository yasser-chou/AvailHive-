package com.AvailHive1.AvailHive1.services.client;

import com.AvailHive1.AvailHive1.dto.AdDTO;
import com.AvailHive1.AvailHive1.dto.AdDetailsForClientDTO;
import com.AvailHive1.AvailHive1.dto.ReservationDTO;
import com.AvailHive1.AvailHive1.dto.ReviewDTO;

import java.util.List;

public interface ClientService {

    List<AdDTO> getAllAds();
    List<AdDTO> searchAdByName(String name);
    boolean bookService(ReservationDTO reservationDTO);
    AdDetailsForClientDTO getAdDetailsByAdId(Long adId);
    List<ReservationDTO> getAllBookingsByUserId(Long userId);
    Boolean giveReview(ReviewDTO reviewDTO);
}
