package Unit.Domain

import Domain.Reservation.SeatCount
import Domain.Train.Coach.Coach
import Domain.Train.Coach.CoachLetter
import Domain.Train.Coach.Seat.Seat
import Domain.Train.Coach.Seat.SeatNumber
import Unit.Domain.Builder.*
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class CoachTest : Spek ({
    it("should refuse a reservation if it exceed the capacity") {
        val coachBuilder = CoachBuilder()

        val coach: Coach = coachBuilder
                .withLetter("A")
                .withNSeats(10)
                .withSeatBookedFromTo(1, 7, "abcdef")
                .withSeatBookedFromTo(8, 10, "ghijkl")
                .build()


        coach.fillReservationOption(SeatCount(1)).isFulfilled() shouldBe false
    }

    it("should refuse a reservation if it exceed the percentage allowed") {
        val coachBuilder = CoachBuilder()

        val coach: Coach = coachBuilder
                .withLetter("A")
                .withNSeats(10)
                .withSeatBookedFromTo(1, 7, "abcdef")
                .build()

        coach.fillReservationOption(SeatCount(1)).isFulfilled() shouldBe false
    }

    it("should accept a reservation event if it exceed the percentage in the same reservation") {
        val coachBuilder = CoachBuilder()
        val coachLetter = CoachLetter("A")

        val coach: Coach = coachBuilder
                .withLetter("A")
                .withNSeats(10)
                .withSeatBookedFromTo(1, 6, "abcdef")
                .build()

        coach.fillReservationOption(SeatCount(2)).seatsToBook shouldEqual setOf(
                Seat(coachLetter, SeatNumber(7)),
                Seat(coachLetter, SeatNumber(8))
        )
    }
})