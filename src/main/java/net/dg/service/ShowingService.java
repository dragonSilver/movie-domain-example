package net.dg.service;

import net.dg.domain.movie.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class ShowingService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShowingRepository showingRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Reservation reserveShowing(Long showingId, Long customerId, int audienceCount) {
        Assert.notNull(showingId);
        Assert.notNull(customerId);
        if(audienceCount < 1) {
            throw new IllegalArgumentException("audienceCount는 0보다 커야 함");
        }

        Showing showing = showingRepository.findOne(showingId);
        Customer customer = customerRepository.findOne(customerId);
        Reservation reservation = showing.reserve(customer, audienceCount);

        reservationRepository.save(reservation);

        return reservation;
    }
}
