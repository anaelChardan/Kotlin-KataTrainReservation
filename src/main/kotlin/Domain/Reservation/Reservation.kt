package Domain.Reservation

import Domain.Train.Coach.Seat.Seat
import Domain.Train.TrainID

data class Reservation(
        val trainID: TrainID,
        val seats: Set<Seat> = emptySet(),
        val bookingID: BookingReference = BookingReference.emptyBookingReference()
)
