package net.dg.service;

import net.dg.domain.movie.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShowingServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ShowingRepository showingRepository;

    @InjectMocks
    private ShowingService sut;

    Long showingId = 1l;
    Long customerId = 1l;
    int audienceCount = 1;

    @Test
    public void test_reserveShowing() throws Exception {

        Showing mockShowing = mock(Showing.class);
        Customer customer = new Customer();

        given(showingRepository.findOne(showingId)).willReturn(mockShowing);
        given(customerRepository.findOne(customerId)).willReturn(customer);

        sut.reserveShowing(showingId, customerId, audienceCount);

        verify(mockShowing).reserve(customer, audienceCount);
        verify(reservationRepository).save(any(Reservation.class));
    }


    @Test
    public void test_should_throwException() throws Exception {

        assertShouldThrowExceptionWhenReserveShowing(null, customerId, audienceCount);
        assertShouldThrowExceptionWhenReserveShowing(showingId, null, audienceCount);
        assertShouldThrowExceptionWhenReserveShowing(showingId, customerId, 0);

    }

    private void assertShouldThrowExceptionWhenReserveShowing(Long showingId, Long customerId, int audienceCount) {
        try{
            sut.reserveShowing(showingId, customerId, audienceCount);
            fail("정상적으로 동작하면 안됨");
        }catch (IllegalArgumentException e) {

        }
    }
}
