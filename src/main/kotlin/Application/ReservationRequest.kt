package Application

import Domain.Reservation.BookingReference
import Domain.Reservation.Reservation
import Domain.Reservation.SeatCount
import Domain.Train.Coach.Seat.Seat
import Domain.Train.TrainID

data class ReservationRequest(
        val trainId: TrainID,
        val seatCount: SeatCount
) {
    fun toEmptyReservation(): Reservation {
        return Reservation(trainId)
    }

    fun toReservation(seats: Set<Seat>, bookingReference: BookingReference): Reservation {
        return Reservation(trainId, seats, bookingReference)
    }
}
