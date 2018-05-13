package Acceptance

import Domain.Reservation.BookingReference
import Application.ReservationRequest
import Domain.Reservation.SeatCount
import Application.TicketOffice
import Domain.Train.Coach.CoachLetter
import Domain.Train.Coach.Seat.Seat
import Domain.Train.Coach.Seat.SeatNumber
import Domain.Train.TrainID
import Infrastructure.Adapter.InMemory.InMemoryBookingReferenceProvider
import Infrastructure.Adapter.InMemory.InMemoryTrainDataService
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object TicketOfficeTest: Spek({
    describe("TicketOffice") {
        on("Reservation") {
            it("should reserve seats when there are some available") {
                val expectedTrainID = TrainID("local_1000")
                val expectedBookingReference = "nanou_reservation"

                val ticketOffice = TicketOffice(InMemoryBookingReferenceProvider(), InMemoryTrainDataService())
                val reservation = ticketOffice.makeReservation(ReservationRequest(expectedTrainID, SeatCount(2)))

                reservation.bookingID shouldEqual BookingReference.create(expectedBookingReference)
                reservation.trainID shouldEqual expectedTrainID
                reservation.seats shouldEqual setOf(
                        Seat(CoachLetter("A"), SeatNumber(2)),
                        Seat(CoachLetter("A"), SeatNumber(5))
                )
            }
        }
    }
})