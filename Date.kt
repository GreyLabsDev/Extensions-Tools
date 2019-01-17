fun Date.getCurrentTime() : String {
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(this.time)
}

fun Date.getCurrentWeekday() : String {
    val weekdayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return weekdayFormat.format(this.time)
}

fun Date.getCurrentDate() : String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(this.time)
}