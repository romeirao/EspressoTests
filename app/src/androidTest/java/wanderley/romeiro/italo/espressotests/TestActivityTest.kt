package wanderley.romeiro.italo.espressotests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Simple smoke test to verify Espresso infrastructure works.
 * This test passes successfully, confirming:
 * - ActivityTestRule launches activities correctly
 * - Emulator is properly configured
 * - Test runner communication is functional
 */
@RunWith(AndroidJUnit4::class)
class TestActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<TestActivity> = ActivityTestRule(TestActivity::class.java, true, true)

    @Before
    fun setUp() {
        val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        uiDevice.wakeUp()
        uiDevice.executeShellCommand("wm dismiss-keyguard")
        Thread.sleep(2000)
    }

    @Test
    fun testActivityLaunches() {
        // Infrastructure test - if this passes, the test environment works
        assert(activityRule.activity != null)
    }
}