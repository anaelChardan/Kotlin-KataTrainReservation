package Domain.Service

import Domain.Reservation.BookingReference

interface BookingReferenceProvider {
    fun getBookingReference(): BookingReference
}