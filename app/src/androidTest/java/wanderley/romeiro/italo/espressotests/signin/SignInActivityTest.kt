package wanderley.romeiro.italo.espressotests.signin

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import wanderley.romeiro.italo.espressotests.signin.robot.SignInFormFillerRobot
import wanderley.romeiro.italo.espressotests.signin.robot.SignInViewAnalyserRobot

@RunWith(AndroidJUnit4::class)
class SignInActivityTest {

    private lateinit var scenario: ActivityScenario<SignInActivity>

    @Before
    fun setUp() {
        val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        uiDevice.wakeUp()
        uiDevice.executeShellCommand("wm dismiss-keyguard")
        Thread.sleep(2000)
        scenario = ActivityScenario.launch(SignInActivity::class.java)
        Thread.sleep(1000) // Wait for activity to fully load
    }

    @Test
    @Throws(Exception::class)
    fun signIn_checkFormIntegrity() {
        SignInViewAnalyserRobot()
            .analyseTexts()
            .analyseFields()
            .analyseButtons()
            .analyseImages()
    }

    @Test
    @Throws(Exception::class)
    fun signIn_emptyFields() {
        val robot = SignInFormFillerRobot()

        val userData = arrayOf("abc", "abc")

        for (i in userData.indices) {
            val result = robot
                .fillUsernameInForm(if (i == 0) "" else userData[0], isReplacement = true)
                .fillPasswordInForm(if (i == 1) "" else userData[1], isReplacement = true)
                .clickToSignIn()

            result.hasEmptyFieldErrorMsg()
        }
    }

    @Test
    @Throws(Exception::class)
    fun signIn_filledFields() {
        val robot = SignInFormFillerRobot()
        robot.fillUsernameInForm("abc").fillPasswordInForm("abc").hasNoErrorMsg()

        val result = robot.clickToSignIn()
        result.reachDashboardView()
    }
}