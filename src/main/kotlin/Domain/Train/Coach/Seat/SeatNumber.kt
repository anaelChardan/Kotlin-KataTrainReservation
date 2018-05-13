package Domain.Train.Coach.Seat

import Domain.Exception.PositiveSeatNumberRequiredException

data class SeatNumber(val seatNumber: Int) {
    init {
        if (seatNumber <= 0) {
            throw PositiveSeatNumberRequiredException()
        }
    }

    override fun toString(): String {
        return seatNumber.toString()
    }
}