import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.anrdoidteamproject.AppScreens
import com.example.anrdoidteamproject.navigateSingleTopTo
import com.example.anrdoidteamproject.ui.LogIn
import com.example.anrdoidteamproject.ui.Register
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogInTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLogInCorrect() {
        val email = "root@root.com"
        val password = "rootroot"

        composeTestRule.setContent {
            LogIn()
        }
        Thread.sleep(300)

        composeTestRule.onNodeWithText("E-mail")
            .performTextInput(email)
        composeTestRule.onNodeWithText("Password")
            .performTextInput(password)
//        composeTestRule.onNodeWithText("Log in")
//            .performClick()
    }

    @Test
    fun testLogInFailed() {
        val email = "abba@abba.com"
        val password = "rootroot"

        composeTestRule.setContent {
            LogIn()
        }
        composeTestRule.onNodeWithText("E-mail")
            .performTextInput(email)
        composeTestRule.onNodeWithText("Password")
            .performTextInput(password)
        composeTestRule.onNodeWithText("Log in")
            .performClick()
        Thread.sleep(1000);
        composeTestRule.onNodeWithText("Wrong credentials")
            .assertIsDisplayed()

    }

}


