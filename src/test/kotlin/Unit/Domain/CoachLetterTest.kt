package Unit.Domain

import Domain.Train.Coach.CoachLetter
import Domain.Exception.CoachLetterCannotBeBlankException
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.amshove.kluent.*
import org.jetbrains.spek.api.dsl.on

class CoachLetterTest: Spek({
    describe("CoachLetter") {
        on("construction") {
            val func = { CoachLetter("") }

            it("should throw an exception on empty string") {
                func shouldThrow CoachLetterCannotBeBlankException::class
            }
        }
    }
})