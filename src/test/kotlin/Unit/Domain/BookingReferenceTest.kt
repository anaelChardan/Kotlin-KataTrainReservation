package Unit.Domain

import Domain.Reservation.BookingReference
import Domain.Exception.EmptyBookingReferenceException
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.amshove.kluent.*
import org.jetbrains.spek.api.dsl.on

class BookingReferenceTest: Spek({
    describe("BookingReference") {
        on("construction") {
            val func = { BookingReference.create("") }

            it("should throw an exception on empty string") {
                func shouldThrow EmptyBookingReferenceException::class
            }
        }
    }
})