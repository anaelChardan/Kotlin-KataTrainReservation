package Domain.Train.Coach.Seat

import Domain.Train.Coach.CoachLetter

data class Seat(val coachLetter: CoachLetter, val seatNumber: SeatNumber): Comparable<Seat> {
    override fun compareTo(other: Seat): Int {
        if (coachLetter.letter < other.coachLetter.letter) {
            return -1;
        }
        if (coachLetter.letter > other.coachLetter.letter) {
            return 1;
        }

        return seatNumber.seatNumber - other.seatNumber.seatNumber
    }

    override fun toString(): String {
        return coachLetter.toString() + seatNumber.toString()
    }
}