package vcmsa.ci.mealsuggestionapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputTime = findViewById<EditText>(R.id.inputTime)
        val btnSuggest = findViewById<Button>(R.id.btnSuggest)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val suggestionText = findViewById<TextView>(R.id.suggestionText)

        btnSuggest.setOnClickListener {
            val timeOfDay = inputTime.text.toString().trim().lowercase()
            val mealSuggestion = getMealSuggestion(timeOfDay)

            if (mealSuggestion != null) {
                suggestionText.text = "Suggested Meal: $mealSuggestion"
            } else {
                Toast.makeText(this, "Invalid time of day. Please enter a valid time.", Toast.LENGTH_SHORT).show()
            }
        }

        btnReset.setOnClickListener {
            inputTime.text.clear()
            suggestionText.text = ""
        }
    }

    private fun getMealSuggestion(timeOfDay: String): String? {
        return when (timeOfDay) {
            "morning" -> "Eggs"
            "mid-morning" -> "Fruit"
            "afternoon" -> "Sandwich"
            "mid-afternoon" -> "Cake"
            "dinner" -> "Pasta"
            "after dinner" -> "Ice cream"
            else -> null
        }
    }
}
