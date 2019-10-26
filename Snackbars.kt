fun Snackbar.defaultConfig(context: Context, addFabMargin: Boolean = false, addButtonMargin: Boolean = false) {
    val params = this.view.layoutParams as ViewGroup.MarginLayoutParams
    var bottomMargin = params.bottomMargin
    if (addFabMargin) {
        bottomMargin += 288
    }
    if (addButtonMargin) {
        bottomMargin += 224
    }
    params.setMargins(params.leftMargin + 8,
            params.topMargin,
            params.rightMargin + 8,
            bottomMargin)
    this.view.layoutParams = params
    this.view.background = context.getDrawable(R.drawable.snackbar_drawable)
    this.view.findViewById<TextView>(android.support.design.R.id.snackbar_text).setTextColor(Color.WHITE)
    ViewCompat.setElevation(this.view, 6f)
}

fun Snackbar.addButton(context: Context,
                       buttonText: String,
                       buttonOnClickAction: () -> Unit,
                       optionalColor: Int = ContextCompat.getColor(context, R.color.colorAccent)) {
    this.setAction(buttonText) { buttonOnClickAction() }
    this.setActionTextColor(optionalColor)
}

fun Snackbar.setMargins(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0): Snackbar {
    val params = this.view.layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(left, top, right, bottom)
    this.view.layoutParams = params
    return this
}
