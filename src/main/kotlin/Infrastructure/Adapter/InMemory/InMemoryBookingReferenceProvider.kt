package Infrastructure.Adapter.InMemory

import Domain.Reservation.BookingReference
import Domain.Service.BookingReferenceProvider

class InMemoryBookingReferenceProvider: BookingReferenceProvider {
    override fun getBookingReference(): BookingReference {
        return BookingReference.create("nanou_reservation")
    }
}