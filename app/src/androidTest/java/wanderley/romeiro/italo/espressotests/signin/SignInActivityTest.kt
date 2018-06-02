package wanderley.romeiro.italo.espressotests.signin

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import wanderley.romeiro.italo.espressotests.signin.robot.SignInFormFillerRobot
import wanderley.romeiro.italo.espressotests.signin.robot.SignInViewAnalyserRobot

@RunWith(AndroidJUnit4::class)
class SignInActivityTest {

  @get:Rule
  var activityRule: ActivityTestRule<SignInActivity> = ActivityTestRule(SignInActivity::class.java)

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

    for (i in 0 until userData.size) {
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