package com.example.testsrxjava2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val someObservable = Observable.just(
            "Java",
            "Kotlin",
            "C++",
            "Delphi",
            "Python",
            "Python"
        )


        someObservable
            .doOnNext { Log.d("TAG1", "doOnNext: " + it) }
            .skip(2)
            .filter { it.length > 3 }
            .map { it + "Language" }
            .distinct()
            .subscribe(
                { Log.d("TAG2", "onNext: $it") }, {}, {}
            )

        val someFlowable = Flowable.just(
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10
        )

        someFlowable
            .onBackpressureBuffer(10)
            .take(8)
            .filter { it % 2 == 0 }
            .map { it * 10 }
            .forEach { Log.d("TAG3", "ForEach: $it") }

        val publishSubject = PublishSubject.create<String>()
        publishSubject.onNext("Delphi")
        publishSubject.onNext("Java")
        publishSubject.subscribe {
            Log.d("TAG4", "publishSubject: $it")
        }
        publishSubject.onNext("Kotlin")
        publishSubject.onNext("C++")

        val replaySubject = ReplaySubject.create<String>()
        replaySubject.onNext("Delphi")
        replaySubject.onNext("Java")
        replaySubject.subscribe {
            Log.d("TAG4", "replaySubject: $it")
        }
        replaySubject.onNext("Kotlin")
        replaySubject.onNext("C++")

        val behaviorSubject = BehaviorSubject.create<String>()
        behaviorSubject.onNext("Delphi")
        behaviorSubject.onNext("Java")
        behaviorSubject.subscribe {
            Log.d("TAG4", "behaviorSubject: $it")
        }
        behaviorSubject.onNext("Kotlin")
        behaviorSubject.onNext("C++")

        val asyncSubject = AsyncSubject.create<String>()
        asyncSubject.subscribe {
            Log.d("TAG4", "asyncSubject1: $it") }
        asyncSubject.onNext("Delphi")
        asyncSubject.onNext("Java")
        asyncSubject.onNext("Kotlin")
        asyncSubject.subscribe {
            Log.d("TAG4", "asyncSubject2: $it")
        }
        asyncSubject.onNext("C++")
        asyncSubject.onComplete()

    }
}