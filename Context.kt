
fun Context.hasInternetConnection(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    networkInfo?.let {info ->
        if (info.isConnected) {
            return true
        }
    }
    return false
}

fun Context.getPixelsFromDp(dpValue: Float): Float {
    return dpValue * this.resources.displayMetrics.density
}

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
