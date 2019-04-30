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
