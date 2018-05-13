package Domain.Reservation

import Domain.Exception.PositiveSeatCountRequiredException

data class SeatCount(val count: Int) {
    init {
        if (count <= 0) {
            throw PositiveSeatCountRequiredException()
        }
    }
}