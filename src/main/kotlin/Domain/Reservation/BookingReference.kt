package Domain.Reservation

import Domain.Exception.EmptyBookingReferenceException

data class BookingReference internal constructor(private val bookingReference: String) {
    override fun toString(): String {
        return bookingReference
    }
    companion object {
        fun emptyBookingReference(): BookingReference {
            return BookingReference("")
        }

        fun create(bookingReference: String): BookingReference {
            if (bookingReference.isEmpty()) {
                throw EmptyBookingReferenceException()
            }

            return BookingReference(bookingReference)
        }
    }
}