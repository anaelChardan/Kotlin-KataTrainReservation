package Application

import Domain.Reservation.Reservation
import Domain.Service.BookingReferenceProvider
import Domain.Service.TrainDataService

class TicketOffice(
        private val bookingReferenceProvider: BookingReferenceProvider,
        private val trainDataService: TrainDataService
) {
    fun makeReservation(reservationRequest: ReservationRequest): Reservation {
        val train = trainDataService.getTrain(reservationRequest.trainId)

        val reservationOption = train.fillReservationOption(reservationRequest.seatCount)

        if (!reservationOption.isFulfilled()) {
            return reservationRequest.toEmptyReservation()
        }

        return reservationRequest.toReservation(
                reservationOption.seatsToBook,
                bookingReferenceProvider.getBookingReference()
        )
    }
}