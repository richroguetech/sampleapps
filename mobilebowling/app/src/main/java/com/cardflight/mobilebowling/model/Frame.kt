package com.cardflight.mobilebowling.model

class Frame(frameNumber: Int, roll: Roll) {
    var frameScore = 0 // number of pins knocked down in frame roll1 + roll2
    var firstRoll: Roll? = null // first roll
    var secondRoll: Roll? = null // second roll
    var thirdRoll: Roll? = null // 3rd roll - only applies to 10th frame...
    private var bonus = 0 // bonus for 1st ball (i.e. spare).
    private var bonus2 = 0 //bonus for 2nd ball (i.e. strike).
    var isBonusProcessed = false
        get() {
            return field
        }
        // first bonus processed
        private set
    var isBonus2Processed = false  // second bonus processed
        private set
    private var frameNumber = -1

    // process all 3 balls....
    // frameScore + 1 or 2 next balls.
    var totalFrameScore = -1 // frameScore + 1 or 2 next balls.
    var isSpare = false
    var isStrike = false

    // means scoring isn't finished...
    // means total frame score calculated...
    var isFinished = false // isFinished is false if strike or spare

    // and bonus not calculated....
    var isLastFrame = false
        private set

    fun processBonus(bonus: Int) {
        this.bonus = bonus
        if (isSpare) {
            isFinished = true
        }
        totalFrameScore = frameScore + bonus
        isBonusProcessed = true
    }

    fun processBonus2(bonus2: Int) {
        if (isStrike) {
            isFinished = true
            totalFrameScore = frameScore + bonus + bonus2
            isBonus2Processed = true
        }
    }

    fun setFrameNumber(frameNumber: Int) {
        this.frameNumber = frameNumber
    }

    fun getFrameNumber(frameNumber: Int) {
        if (frameNumber == 9) {
            setLastFrame()
        }
        this.frameNumber = frameNumber
    }

    private fun setLastFrame() {
        isLastFrame = true
    }

    fun insertFirstRoll(roll: Roll) {
        if (roll.isValid) {
            if (roll.type == Roll.RollType.STRIKE) {
                isStrike = true
                frameScore = 10
            } else {
                frameScore = roll.numPins
            }
            this.firstRoll = roll
            totalFrameScore = frameScore
        }
    }

    fun insertSecondRoll(roll2: Roll) {
        if (roll2.isValid && firstRoll?.type != Roll.RollType.STRIKE) {
            if (roll2.type == Roll.RollType.SPARE) {
                isSpare = true
                frameScore = 10
            } else {
                frameScore += roll2.numPins
                isFinished = true
            }
            totalFrameScore = frameScore
            secondRoll = roll2
        }
    }

    fun insertThirdRoll(roll3: Roll) {
        if (roll3.isValid && frameNumber == LAST_FRAME_NUMBER) {
            thirdRoll = roll3
            totalFrameScore = frameScore + thirdRoll!!.numPins
        }
    }

    companion object {
        var LAST_FRAME_NUMBER = 9 // 0 base index.
    }

    init {
        // process the frameNumber and 1st roll value....
        insertFirstRoll(roll)
        setFrameNumber(frameNumber)
    }
}