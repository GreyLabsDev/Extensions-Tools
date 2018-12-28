
fun Context.getPixelsFromDp(dpValue: Float): Float {
    return dpValue * this.resources.displayMetrics.density
}