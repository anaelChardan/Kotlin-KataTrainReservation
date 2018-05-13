package Unit.Domain

import Domain.Reservation.SeatCount
import Domain.Train.Coach.CoachLetter
import Domain.Train.Coach.Seat.Seat
import Domain.Train.Coach.Seat.SeatNumber
import Unit.Domain.Builder.*
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class TrainTest : Spek ({
    it("should not contain any free seats if it exceed the capacity") {
        val coachBuilder = CoachBuilder()
        val trainBuilder = TrainBuilder()

        val coachA = coachBuilder
                .withLetter("A")
                .withNSeats(10)
                .withSeatBookedFromTo(1, 10, "abcdef")
                .build()

        val coachB = coachBuilder
                .withLetter("B")
                .withSeatBookedFromTo(1, 10, "ghijkl")
                .build()

        val train = trainBuilder
                        .withId("train_one")
                        .withCoaches(setOf(coachA, coachB))
                        .build()

        train.fillReservationOption(SeatCount(1)).isFulfilled() shouldBe false
    }

    it ("should not contain any free seats if it exceed the percentage allowed even if one coach is ok") {
        val coachBuilder = CoachBuilder()
        val trainBuilder = TrainBuilder()

        val coachA = coachBuilder
                .withLetter("A")
                .withNSeats(10)
                .withSeatBookedFromTo(1, 6, "abcdef")
                .build()

        val coachB = coachBuilder
                .withLetter("B")
                .withSeatBookedFromTo(1, 8, "ghijkl")
                .build()

        val train = trainBuilder
                .withId("train_train")
                .withCoaches(setOf(coachA, coachB))
                .build()

        train.fillReservationOption(SeatCount(1)).isFulfilled() shouldBe false
    }

    it("should not contain any free seats if nobody can accept") {
        val coachBuilder = CoachBuilder()
        val trainBuilder = TrainBuilder()

        val coachA = coachBuilder
                .withLetter("A")
                .withNSeats(10)
                .withSeatBookedFromTo(1, 2, "abcdef")
                .build()

        val coachB = coachBuilder
                .withLetter("B")
                .withSeatBookedFromTo(1, 2, "ghijkl")
                .build()

        val train = trainBuilder
                .withId("train_train")
                .withCoaches(setOf(coachA, coachB))
                .build()

        train.fillReservationOption(SeatCount(10)).isFulfilled() shouldBe false
    }

    it("should contain some free seats in the first available coach") {
        val coachBuilder = CoachBuilder()
        val trainBuilder = TrainBuilder()

        val coachA = coachBuilder
                .withLetter("A")
                .withNSeats(10)
                .withSeatBookedFromTo(1, 2, "abcdef")
                .build()

        val coachB = coachBuilder
                .withLetter("B")
                .withSeatBookedFromTo(1, 2, "ghijkl")
                .build()

        val train = trainBuilder
                .withId("train_train")
                .withCoaches(setOf(coachA, coachB))
                .build()

        train.fillReservationOption(SeatCount(2)).seatsToBook shouldEqual setOf(
                Seat(CoachLetter("A"), SeatNumber(3)),
                Seat(CoachLetter("A"), SeatNumber(4))
        )
    }
})