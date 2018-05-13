package Domain.Reservation

import Domain.Train.Coach.Seat.Seat

data class ReservationOption(val seatCount: SeatCount, val seatsToBook: Set<Seat> = emptySet()) {
    fun isFulfilled(): Boolean {
        return seatsToBook.size == seatCount.count
    }
}