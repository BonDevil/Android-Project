import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.anrdoidteamproject.ui.Stats
import org.junit.Rule
import org.junit.Test

class StatsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_buttons_are_clickable() {
        composeTestRule.setContent {
            Stats()
        }
        Thread.sleep(500)
        // Test the user info button is clickable
//        composeTestRule.onNodeWithText("User Info").assertIsDisplayed()
//
//        // Test the home button is clickable
//        composeTestRule.onNodeWithText("Home").assertHasClickAction()
//
        // Test the settings button is clickable
//        composeTestRule.onNodeWithText("Settings").assertHasClickAction()
//
//         Test the bilans button is clickable
        composeTestRule.onNodeWithText("BALANCE").assertHasClickAction()
//
//        // Test the floating action button is clickable
//        composeTestRule.onNodeWithText("TODAY").assertIsDisplayed()
    }
}
