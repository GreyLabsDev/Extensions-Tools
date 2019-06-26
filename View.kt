fun View.isVisible(): Boolean = this.visibility == View.VISIBLE

fun View.animateFadeIn(duration: Long? = null, onAnimationEndAction: (() -> Unit)? = null) {
    this.animate()
            .alpha(1f)
            .setDuration(duration ?: 180L)
            .withEndAction { onAnimationEndAction?.invoke() }
            .start()
}

fun View.animateFadeOut(duration: Long? = null,  onAnimationEndAction: (() -> Unit)? = null) {
    this.animate()
            .alpha(0f)
            .setDuration(duration ?: 180L)
            .withEndAction { onAnimationEndAction?.invoke() }
            .start()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun TextView.measureWidth(): Int {
    val bound = Rect()
    this.paint.getTextBounds(this.text.toString(), 0, this.text.toString().length, bound)
    return bound.width()
}

fun TextView.measureHeight(): Int {
    val bound = Rect()
    this.paint.getTextBounds(this.text.toString(), 0, this.text.toString().length, bound)
    return bound.height()
}
