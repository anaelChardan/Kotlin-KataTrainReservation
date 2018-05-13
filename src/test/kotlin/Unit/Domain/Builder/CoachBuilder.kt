package Unit.Domain.Builder

import Domain.Reservation.BookingReference
import Domain.Reservation.SeatWithBookingReference
import Domain.Train.Coach.Coach
import Domain.Train.Coach.CoachLetter
import Domain.Train.Coach.Seat.Seat
import Domain.Train.Coach.Seat.SeatNumber

class CoachBuilder: Builder<Coach> {
    private var coachLetter: CoachLetter = CoachLetter("A")
    private var seats: Set<SeatWithBookingReference> = emptySet()

    fun withLetter(letter: String): CoachBuilder {
        coachLetter = CoachLetter(letter)

        return this
    }

    fun withNSeats(numberOfSeats: Int): CoachBuilder {
        seats = (1 .. numberOfSeats)
                .toSet()
                .map { SeatWithBookingReference(Seat(coachLetter, SeatNumber(it))) }
                .toSet()


        return this
    }

    fun withSeatBookedFromTo(from: Int, to: Int = from, bookingReference: String): CoachBuilder {
        seats = seats.map {
            if (it.seat.seatNumber.seatNumber in from..to) {
                SeatWithBookingReference(it.seat, BookingReference.create(bookingReference))
            } else {
                it
            }
        }.toSet()

        return this
    }

    override fun build(): Coach {
        return Coach(coachLetter, seats)
    }
}