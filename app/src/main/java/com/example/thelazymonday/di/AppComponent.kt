package com.example.thelazymonday.di

import com.example.thelazymonday.ui.gamenews.GameNewsFragment
import dagger.Component

@Component
interface AppComponent {
    fun inject(fragment: GameNewsFragment)
}