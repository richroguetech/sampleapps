package com.cardflight.mobilebowling.model

import java.util.*

class Game {
    private lateinit var userFrames: List<Frame>
    var totalScore = 0
        get() {
            for (frame in userFrames) {
                field += frame.totalFrameScore
            }
            return field
        }
        private set
    var frames: List<Frame>
        get() = userFrames
        set(userFrames) {
            this.userFrames = userFrames
        }

    companion object {
        const val NUMBER_OF_FRAMES = 10
    }
}