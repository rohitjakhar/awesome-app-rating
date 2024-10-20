package com.rohitjakhar.ratingdialog.preferences

import java.io.Serializable

enum class RatingThreshold : Serializable {
    NONE, HALF, ONE, ONE_AND_A_HALF, TWO, TWO_AND_A_HALF, THREE, THREE_AND_A_HALF, FOUR, FOUR_AND_A_HALF, FIVE;
}

fun RatingThreshold.toFloat() = this.ordinal / 2f

fun Float.toRatingThreshold(): RatingThreshold {
    return when  {
        this < 0.25f -> RatingThreshold.NONE
        this < 0.75f -> RatingThreshold.HALF
        this < 1.25f -> RatingThreshold.ONE
        this < 1.75f -> RatingThreshold.ONE_AND_A_HALF
        this < 2.25f -> RatingThreshold.TWO
        this < 2.75f -> RatingThreshold.TWO_AND_A_HALF
        this < 3.25f -> RatingThreshold.THREE
        this < 3.75f -> RatingThreshold.THREE_AND_A_HALF
        this < 4.25f -> RatingThreshold.FOUR
        this < 4.75f -> RatingThreshold.FOUR_AND_A_HALF
        this <= 5f -> RatingThreshold.FIVE
        else -> RatingThreshold.NONE
    }
}
