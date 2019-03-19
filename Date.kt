fun Date.getCurrentTimeString() : String {
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(this.time)
}

fun Date.getCurrentTimeHour() : Int {
    return this.getCurrentTimeString().split(":")[0].toInt()
}

fun Date.getCurrentTimeMinutes() : Int {
    return this.getCurrentTimeString().split(":")[1].toInt()
}

fun Date.getMonthNumber(): Int {
    val dateFormat = SimpleDateFormat("MM", Locale.getDefault())
    val mStr = dateFormat.format(this.time)
    return Integer.parseInt(mStr)
}

fun Date.getCurrentDayString(): String {
    val dateFormat = SimpleDateFormat("dd", Locale.getDefault())
    return dateFormat.format(this.time)
}

fun Date.getCurrentWeekdayString() : String {
    val weekdayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return weekdayFormat.format(this.time)
}

fun Date.getCurrentDateString() : String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(this.time)
}
