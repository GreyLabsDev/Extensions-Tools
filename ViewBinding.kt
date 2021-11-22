// ViewBinding delegate for Fragments
// Use example: someFragmentBindig by bindFragment<SomeFragmentBinding>(SomeFragmentBinding::bind)

class bindFragment<T : ViewBinding>(
    private val viewBinder: (View) -> T
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null
    private val bindingLifecycleObserver = BindingLifecycleObserver()

    override operator fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        this.binding?.let { return it }
        val fragmentView = thisRef.requireView()
        thisRef.viewLifecycleOwner.lifecycle.addObserver(bindingLifecycleObserver)
        return viewBinder.invoke(fragmentView).also {
            this.binding = it
        }
    }

    private inner class BindingLifecycleObserver : DefaultLifecycleObserver {
        private val mainHandler = Handler(Looper.getMainLooper())

        @MainThread
        override fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            mainHandler.post { binding = null }
        }
    }
}
