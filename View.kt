/**
 * Cheching view visibility
 */
fun View.isVisible(): Boolean = this.visibility == View.VISIBLE

/**
 * Animating fade in for view, can execure callback after animation is ended
 * 
 * @property duration - animation duration
 * @property onAnimationEndAction - callback for executiong after animation end
 */
fun View.animateFadeIn(duration: Long? = null, onAnimationEndAction: (() -> Unit)? = null) {
    this.animate()
            .alpha(1f)
            .setDuration(duration ?: 180L)
            .withEndAction { onAnimationEndAction?.invoke() }
            .start()
}

/**
 * Animating fade out for view, can execure callback after animation is ended
 * 
 * @property duration - animation duration
 * @property onAnimationEndAction - callback for executiong after animation end
 */
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

/**
 * Measuring TextView width. Use this extension after view rendered
 */
fun TextView.measureWidth(): Int {
    val bound = Rect()
    this.paint.getTextBounds(this.text.toString(), 0, this.text.toString().length, bound)
    return bound.width()
}

/**
 * Measuring TextView height. Use this extension after view rendered
 */
fun TextView.measureHeight(): Int {
    val bound = Rect()
    this.paint.getTextBounds(this.text.toString(), 0, this.text.toString().length, bound)
    return bound.height()
}

/**
 * Reducing repeting code for inflating views 
 * 
 * @property resId - inflating layout resource id
 * @property attach
 */
fun ViewGroup.inflate(resId: Int, attach: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resId, this, attach)
}
