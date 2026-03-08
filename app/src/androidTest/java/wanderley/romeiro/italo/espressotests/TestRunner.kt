package wanderley.romeiro.italo.espressotests

import android.app.Instrumentation
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnitRunner
import wanderley.romeiro.italo.espressotests.core.EspressoTestsApplication

class TestRunner : AndroidJUnitRunner() {
    override fun onStart() {
        super.onStart()
        // Ensure application is properly initialized for testing
        val context = InstrumentationRegistry.getInstrumentation().targetContext as EspressoTestsApplication
        context.component  // Force component initialization
    }
}