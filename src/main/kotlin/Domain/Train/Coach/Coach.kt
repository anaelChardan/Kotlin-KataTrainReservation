package Domain.Train.Coach

import Domain.Reservation.ReservationOption
import Domain.Reservation.SeatCount
import Domain.Reservation.SeatWithBookingReference

class Coach(val coachLetter: CoachLetter, val seatsWithBookingReference: Set<SeatWithBookingReference>) : Comparable<Coach> {
    override fun compareTo(other: Coach): Int {
        return coachLetter.letter.compareTo(other.coachLetter.letter)
    }

    private val seatsPercent = 0.7
    val capacity = seatsWithBookingReference.size
    val bookableSeatsCapacity = Math.round(capacity * seatsPercent)

    fun bookedSeats(): Set<SeatWithBookingReference> = seatsWithBookingReference.filter { it.isBooked() }.toSet()
    fun freeSeats(): Set<SeatWithBookingReference> = seatsWithBookingReference.minus(bookedSeats())

    private fun canAccept(seatCount: Int): Boolean {
        return !((bookedSeats().size + seatCount) > capacity || bookedSeats().size >= bookableSeatsCapacity)
    }

    fun fillReservationOption(seatCount: SeatCount): ReservationOption {
        if (!canAccept(seatCount.count)) {
            return ReservationOption(seatCount)
        }

        return ReservationOption(
                seatCount,
                freeSeats().map { it.seat }.sorted().take(seatCount.count).toSet()
        )
    }

    override fun toString(): String {
        return "{ coachLetter:" +
                coachLetter.letter +
                ", [ " + seatsWithBookingReference +  "]" +
                "}"
    }
}