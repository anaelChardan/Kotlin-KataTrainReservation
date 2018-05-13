package Domain.Service

import Domain.Train.Train
import Domain.Train.TrainID

interface TrainDataService {
    fun getTrain(trainID: TrainID): Train
}