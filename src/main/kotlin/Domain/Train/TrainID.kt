package Domain.Train

import Domain.Exception.EmptyTrainIDException

data class TrainID(val trainID: String) {
    init {
        if (trainID.isEmpty()) {
            throw EmptyTrainIDException()
        }
    }
}