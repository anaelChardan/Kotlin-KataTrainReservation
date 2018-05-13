package Domain.Train

import Domain.Reservation.ReservationOption
import Domain.Reservation.SeatCount
import Domain.Reservation.SeatWithBookingReference
import Domain.Train.Coach.Coach

class Train(val TrainId: TrainID, val coaches: Set<Coach>) {
    val percent = 0.7
    val capacity = coaches.fold(0, { total, next -> total + next.capacity })
    val bookableSeatsCapacity = Math.round(capacity * percent)

    private fun bookedSeats(): Set<SeatWithBookingReference> = coaches.flatMap { it.bookedSeats() }.toSet()
    private fun canAccept(count: Int): Boolean {
        return (bookedSeats().size + count) <= bookableSeatsCapacity
    }

    fun fillReservationOption(seatCount: SeatCount): ReservationOption {
        if (!canAccept(seatCount.count)) {
            return ReservationOption(seatCount)
        }

        val reservation: ReservationOption? = coaches.sorted()
                .asSequence()
                .map { it.fillReservationOption(seatCount) }
                .firstOrNull { it.isFulfilled() }

        return reservation ?: ReservationOption(seatCount)
    }
}
