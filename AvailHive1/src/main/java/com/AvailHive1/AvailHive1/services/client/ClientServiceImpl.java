package com.AvailHive1.AvailHive1.services.client;

import com.AvailHive1.AvailHive1.Repository.ReviewRepository;
import com.AvailHive1.AvailHive1.dto.AdDetailsForClientDTO;
import com.AvailHive1.AvailHive1.Repository.AdRepository;
import com.AvailHive1.AvailHive1.Repository.ReservationRepository;
import com.AvailHive1.AvailHive1.Repository.UserRepository;
import com.AvailHive1.AvailHive1.dto.AdDTO;
import com.AvailHive1.AvailHive1.dto.ReservationDTO;
import com.AvailHive1.AvailHive1.dto.ReviewDTO;
import com.AvailHive1.AvailHive1.entity.Ad;
import com.AvailHive1.AvailHive1.entity.Reservation;
import com.AvailHive1.AvailHive1.entity.Review;
import com.AvailHive1.AvailHive1.entity.User;
import com.AvailHive1.AvailHive1.enums.ReservationStatus;
import com.AvailHive1.AvailHive1.enums.ReviewStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<AdDTO> getAllAds(){
        return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public List<AdDTO> searchAdByName(String name){
        return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public boolean bookService(ReservationDTO reservationDTO){
        Optional<Ad> optionalAd = adRepository.findById(reservationDTO.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());

        if(optionalAd.isPresent() && optionalUser.isPresent()){
            Reservation reservation=new Reservation();

            reservation.setBookDate(reservationDTO.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());

            reservation.setAd(optionalAd.get());
            reservation.setCompany(optionalAd.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FALSE);

            reservationRepository.save(reservation);

            return true;


        }
        return false;
    }

    public AdDetailsForClientDTO getAdDetailsByAdId(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        AdDetailsForClientDTO adDetailsForClientDTO = new AdDetailsForClientDTO();
        if(optionalAd.isPresent()){
            adDetailsForClientDTO.setAdDTO(optionalAd.get().getAdDto());

            List<Review> reviewList = reviewRepository.findAllByAdId(adId);
            adDetailsForClientDTO.setReviewDTOList(reviewList.stream().map(Review::getDTO).collect(Collectors.toList()));

        }
        return adDetailsForClientDTO;

    }

    public List<ReservationDTO> getAllBookingsByUserId(Long userId){
        return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    public Boolean giveReview(ReviewDTO reviewDTO) {
        if (reviewDTO.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (reviewDTO.getBookId() == null) {
            throw new IllegalArgumentException("Booking ID must not be null");
        }

        Optional<User> optionalUser = userRepository.findById(reviewDTO.getUserId());
        Optional<Reservation> optionalBooking = reservationRepository.findById(reviewDTO.getBookId());

        if (optionalUser.isPresent() && optionalBooking.isPresent()) {
            Review review = new Review();
            review.setReviewDate(new Date());
            review.setReview(reviewDTO.getReview());
            review.setRating(reviewDTO.getRating());
            review.setUser(optionalUser.get());
            review.setAd(optionalBooking.get().getAd());

            reviewRepository.save(review);

            Reservation booking = optionalBooking.get();
            booking.setReviewStatus(ReviewStatus.TRUE);

            reservationRepository.save(booking);

            return true;
        }
        return false;
    }


}
