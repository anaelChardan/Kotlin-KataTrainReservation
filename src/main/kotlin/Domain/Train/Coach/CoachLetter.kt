package Domain.Train.Coach

import Domain.Exception.CoachLetterCannotBeBlankException

data class CoachLetter(val letter: String) {
    init {
        if (letter.isBlank()) {
            throw CoachLetterCannotBeBlankException()
        }
    }

    override fun toString(): String {
        return letter
    }
}