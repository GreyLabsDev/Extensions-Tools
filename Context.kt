
/**
 * Checking current network connection
 */
fun Context.hasInternetConnection(): Boolean {
    return (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnected == true
}

/**
 * Getting clean pixels from Density-independent Pixels
 * 
 * @property dpValue - size in Density-independent Pixels
 */
fun Context.getPixelsFromDp(dpValue: Float): Float {
    return dpValue * this.resources.displayMetrics.density
}

/**
 * Getting device screen width in pixels
 */
fun Context.getScreenWidthInPixels(): Int {
    val metrics = DisplayMetrics()
    this.windowManager().defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

/**
 * Getting device screen height in pixels
 */
fun Context.getScreenHeightInPixels(): Int {
    val metrics = DisplayMetrics()
    this.windowManager().defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}

/**
 * Getting navigation bar height (if you dont want to use WindowInsets)
 * */
fun Context.getNavigationBarHeight(): Int {
    val resId = this.resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resId > 0) this.resources.getDimensionPixelSize(resId)
           else 0
}

/**
 * Getting difference between two dates in format like : "Type of difference in Days/Hours e.t.c - Value of difference"
 * 
 * @property startDate
 * @property endDate
 * 
 * Differences types symbols:
 * "D" - days
 * "H" - hours
 * "M" - minutes
 * "S" - seconds
 */
fun Context.getDateDifferense(startDate: Date, endDate: Date): Pair<String,Long>? {
    
    var different = endDate.time - startDate.time

    val secondsInMilli: Long = 1000
    val minutesInMilli = secondsInMilli * 60
    val hoursInMilli = minutesInMilli * 60
    val daysInMilli = hoursInMilli * 24
    val monthsInMilli = daysInMilli * 30

    val elapsedDays = different / daysInMilli
    different %= daysInMilli

    val elapsedHours = different / hoursInMilli
    different %= hoursInMilli

    val elapsedMinutes = different / minutesInMilli
    different %= minutesInMilli

    val elapsedSeconds = different / secondsInMilli

    return when {
        elapsedDays > 0 -> {
            Pair("D", elapsedDays)
        }
        elapsedHours > 0 -> {
            Pair("H", elapsedHours)
        }
        elapsedMinutes > 0 -> {
            Pair("M", elapsedMinutes)
        }
        elapsedSeconds > 0 -> {
            Pair("S", elapsedSeconds)
        }
        else -> {
            null
        }
    }
}
