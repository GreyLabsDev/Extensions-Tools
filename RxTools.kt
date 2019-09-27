fun <T: Any>Subject<T>.subscribeObserveDefault(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T: Any> Subject<T>.subscribeOnSchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T: Any>Observable<T>.observeOnMainThread(): Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}
