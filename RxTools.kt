fun <T: Any>Subject<T>.subscribeObserveDefault(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.subscribeOnSchedulers(): Completable {
    return this.subscribeOn(Schedulers.io())
}

fun Completable.observeOnMain(): Completable {
    return this.observeOn(AndroidSchedulers.mainThread())
}

fun <T: Any> Subject<T>.subscribeOnSchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T: Any> Observable<T>.subscribeOnSchedulers(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T: Any>Observable<T>.observeOnMain(): Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}

fun <T: Any> Single<T>.subscribeOnSchedulers(): Single<T> {
    return this.subscribeOn(Schedulers.io())
}

fun <T: Any> Single<T>.observeOnMain(): Single<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
}
