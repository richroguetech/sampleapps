package com.cardflight.mobilebowling.model

class Frame(frameNumber: Int, roll: Roll) {
    var frameScore = 0 // number of pins knocked down in frame roll1 + roll2
    val totalScore = 0 // total game score up to this point.
    var firstRoll: Roll? = null // first roll
    var secondRoll: Roll? = null // second roll
    var thirdRoll: Roll? = null // 3rd roll - only applies to 10th frame...
        private set
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
        get() {
            field = if (isLastFrame) {
                // process all 3 balls....
                frameScore + thirdRoll!!.numPins
            } else {
                if (isStrike) {
                    frameScore + bonus + bonus2
                } else if (isSpare) {
                    frameScore + bonus
                } else {
                    frameScore
                }
            }

            return field // frameScore + 1 or 2 next balls.
        }
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
        isBonusProcessed = true
    }

    fun processBonus2(bonus: Int) {
        bonus2 = bonus
        if (isStrike) {
            isFinished = true
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
            }
            this.firstRoll = roll
        }
    }

    fun insertSecondRoll(roll2: Roll) {
        if (roll2.isValid) {
            if (roll2.type == Roll.RollType.SPARE) {
                isSpare = true
            } else {
                isFinished = true
            }
            secondRoll = roll2
        }
    }

    fun insertThirdRoll(roll3: Roll) {
        if (roll3.isValid && frameNumber == LAST_FRAME_NUMBER) {
            thirdRoll = roll3
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