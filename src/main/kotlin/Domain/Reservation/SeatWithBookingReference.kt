package Domain.Reservation

import Domain.Train.Coach.Seat.Seat

data class SeatWithBookingReference(
        val seat: Seat,
        private val bookingReference: BookingReference? = null
) {
    fun isBooked(): Boolean = null != bookingReference

    override fun toString(): String {
        return "{ seat: " + seat.toString() + ", bookingReference: " + bookingReference.toString() + "}"
    }
}