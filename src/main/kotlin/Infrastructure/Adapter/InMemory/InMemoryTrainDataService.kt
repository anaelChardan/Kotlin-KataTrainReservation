package Infrastructure.Adapter.InMemory

import Domain.Reservation.BookingReference
import Domain.Reservation.SeatWithBookingReference
import Domain.Service.TrainDataService
import Domain.Train.*
import Domain.Train.Coach.Coach
import Domain.Train.Coach.CoachLetter
import Domain.Train.Coach.Seat.Seat
import Domain.Train.Coach.Seat.SeatNumber

class InMemoryTrainDataService: TrainDataService {
    override fun getTrain(trainID: TrainID): Train {
        val coachLetter = CoachLetter("A")
        val seats = (1 .. 10).toList().map { Seat(coachLetter, SeatNumber(it)) }
        val references = listOf(
                BookingReference.create("abcdef"),
                null,
                BookingReference.create("abcdef"),
                BookingReference.create("abcdef"),
                null,
                null,
                null,
                null,
                BookingReference.create("ghijkl"),
                BookingReference.create("ghijkl")
        )

        return Train(trainID, setOf(Coach(coachLetter, seats.withIndex().map { SeatWithBookingReference(it.value, references[it.index]) }.toSet())))
    }
}