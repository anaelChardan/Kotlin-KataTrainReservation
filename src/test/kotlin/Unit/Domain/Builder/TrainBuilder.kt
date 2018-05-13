package Unit.Domain.Builder

import Domain.Train.Coach.Coach
import Domain.Train.Train
import Domain.Train.TrainID

class TrainBuilder: Builder<Train> {
    private var id: TrainID = TrainID("train_id")
    private var coachesList: Set<Coach> = emptySet()

    fun withId(letter: String): TrainBuilder {
        id = TrainID(letter)

        return this
    }

    fun withCoaches(coaches: Set<Coach>): TrainBuilder {
        coachesList = coaches

        return this
    }

    override fun build(): Train {
        return Train(id, coachesList)
    }
}