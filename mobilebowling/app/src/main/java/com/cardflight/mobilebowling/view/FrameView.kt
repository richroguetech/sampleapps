package com.cardflight.mobilebowling.view

import java.util.ArrayList
import java.util.HashMap

/**
 * View for Frame Details.
 */
object FrameView {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<FrameViewItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, FrameViewItem> = HashMap()

    private val COUNT = 10

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(
                createFrameItem(i)
            )
        }
    }

    private fun addItem(item: FrameViewItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createFrameItem(position: Int): FrameViewItem {
        return FrameViewItem(
            position.toString(),
            "Frame " + position,
            makeDetails(position)
        )
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Frame Score: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nHere's the Scoring Results of this Frame.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class FrameViewItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}