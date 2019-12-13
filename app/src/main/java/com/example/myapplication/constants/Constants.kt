package com.example.myapplication.constants

class Constants {
    interface ACTION {
        companion object {
            const val NEXT_TRACK = "next"
            const val PREV_TRACK = "previous"
            const val PAUSE = "pause"
            const val START = "start"
            const val STOP = "stop"
        }
    }

    interface FILTER {
        companion object {
            const val MUSIC_LIST = "music_list"
            const val AUTHOR_LIST = "author_list"
            const val ALL_AUTHORS = "all"
            const val TRACK_INFO = "track_info"
        }
    }
}
