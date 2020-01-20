package com.example.myapplication.notes.constants

class Constants {

    interface ACTION {
        companion object {
            const val ADD_ACTION = "add"
            const val END_ADD_ACTION = "end_add"
            const val UPDATE_ACTION = "update"
        }
    }

    interface DATA {
        companion object {
            const val ARG_ACTION = "action"
            const val ARG_ID = "id"
            const val ARG_TITLE = "title"
            const val ARG_DESCRIPTION = "description"
            const val ARG_LATITUDE = "latitude"
            const val ARG_LONGITUDE = "longitude"
        }
    }
}
