package com.orinab.shoporinab.utils.build_config


class BuildConfig {
    companion object {
        var BASE_URL = "https://back.orinab.com/api/"
        var BASE_URL_IMAGE = "https://back.orinab.com/storage/"
        var BASE_URL_SITE = "https://orinab.com/kitchen-cabinet/"

        //api
        const val DASHBOARD = "dashboard"
        const val CABINET_KITCHEN = "cabinetkitchen"
        const val CABINET_KITCHEN_SINGLE = "cabinetkitchensingle"

        //filed
        const val API_TOKEN = "api_token"

        //filed
        const val MOBILE = "mobile"

        //token
        var TOKEN_APP= ""

        //header
        const val X_API_KEY = "x-api-key"

        //query
        const val URL = "url"

        //query_defualt
        const val JSON_V2 = "jsonv2"


        // mapbox
        const val STYLE_MAP_BOX = "https://tile.snappmaps.ir/styles/snapp-style/style.json"
        const val ZOOM_MAP = 15.0
        const val DURATION_ANIMATION = 7000

        //path
        const val ID = "id"

        // validation
        const val START_PHONE_NUMBER_VALIDATION = "09"

        // table
        const val USER_ENTITY = "user_entity"

        // state
        const val STATE_ACCOUNT = "display_user_information"
        const val STATE_CHANGE_PASSWORD = "change_password"
        const val STATE_WALLET = "wallet"
        const val STATE_MY_PAYMENT = "my_payments"
        const val STATE_MY_ADDRESSES = "my_addresses"
        const val STATE_MY_TRAVELS = "my_travels"
        const val STATE_EXIT = "exit"

        //state
        const val FAIL = "fail"


        // database
        const val USER_DATABASE = "user_database"


        //request_code
        const val REQUEST_USER_CONSENT = 100
        const val REQUEST_LOCATION_PERMISSION = 200
        const val REQUEST_GOOGLE_SEARCH_PERMISSION = 300
        const val GPS_SETTINGS = 0x7

        //timer
        const val TIMER = 90000
        const val COUNT_DOWN_INTERNAL = 1000

        //date
        const val MIN_YEAR_DATE = 1400
        const val PERSIAN_DATE = 1400
        const val PERSIAN_MONT = 2
        const val PERSIAN_DAY = 17


        //state_marker
        const val STATE_ORIGIN = 1
        const val STATE_DISTANCE = 2
        const val STATE_DISTANCE_TWO = 3
        const val STATE_REQUEST_PAY = 4

        // request_location
        const val INTERVAL = (1000 * 300).toLong()
        const val FASTEST_INTERVAL = (1000 * 200).toLong()

        //type_adapter
        const val TYPE_INT_SLIDER = 100
        const val TYPE_INT_SUB_LIST = 101
        const val TYPE_INT_HEADER_LIST = 102
        const val TYPE_INT_CATEGORY_LIST = 103
        const val TYPE_INT_VALUE_LIST = 104

        const val TYPE_SLIDER = "banner"
        const val TYPE_SUB_LIST = "subList"
        const val TYPE_HEADER_LIST = "header"
        const val TYPE_VALUE_LIST = "value"

        // bundle
        const val BUNDLE_ID = "id"
        const val BUNDLE_TITLE = "title"
        const val BUNDLE_SUB = "Sub"
        const val BUNDLE_CATEGORY = "Category"
        const val BUNDLE_PRO = "Pro"
        const val BUNDLE_DETAIL_SINGLE = "Detail_Single"

        //cost_id_product
        const val CONST_ALL_PRODUCT = 5



    }
}