import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.anrdoidteamproject.ui.Register
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTextFieldsAreDisplayed() {
        composeTestRule.setContent {
            Register()
        }
        composeTestRule.onNodeWithText("Name")
            .assertExists()
        composeTestRule.onNodeWithText("Last Name")
            .assertExists()
        composeTestRule.onNodeWithText("Password")
            .assertExists()
        composeTestRule.onNodeWithText("Repeat password")
            .assertExists()
        composeTestRule.onNodeWithText("Phone number")
            .assertExists()
        composeTestRule.onNodeWithText("E-mail")
            .assertExists()
    }


    @Test
    fun testInputValuesAreStored() {
        val firstName = "John"
        val lastName = "Doe"
        val email = "john.doe@example.com"
        val password = "password123"
        val repeatPassword = "password123"
        val phoneNumber = "555-555-5555"

        composeTestRule.setContent {
            Register()
        }
        composeTestRule.onNodeWithText("Name")
            .performTextInput(firstName)
        composeTestRule.onNodeWithText("Last Name")
            .performTextInput(lastName)
        composeTestRule.onNodeWithText("E-mail")
            .performTextInput(email)
        composeTestRule.onNodeWithText("Password")
            .performTextInput(password)
        composeTestRule.onNodeWithText("Repeat password")
            .performTextInput(repeatPassword)
        composeTestRule.onNodeWithText("Phone number")
            .performTextInput(phoneNumber)

        composeTestRule.onNodeWithText(firstName)
            .assertExists()
        composeTestRule.onNodeWithText(lastName)
            .assertExists()
        composeTestRule.onNodeWithText(email)
            .assertExists()
        composeTestRule.onNodeWithText(phoneNumber)
            .assertExists()


    }
}


