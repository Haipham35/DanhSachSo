package com.example.bai2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val textViewError = findViewById<TextView>(R.id.textViewError)
        val listViewNumbers = findViewById<ListView>(R.id.listViewNumbers)

        buttonShow.setOnClickListener {
            val inputText = editTextNumber.text.toString()
            textViewError.visibility = TextView.GONE

            // Kiểm tra dữ liệu đầu vào
            val n = inputText.toIntOrNull()
            if (n == null || n <= 0) {
                textViewError.text = "Vui lòng nhập số nguyên dương"
                textViewError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            // Lấy RadioButton đã chọn
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val numbers = when (selectedRadioButtonId) {
                R.id.radioEven -> generateEvenNumbers(n)
                R.id.radioOdd -> generateOddNumbers(n)
                R.id.radioSquare -> generateSquareNumbers(n)
                else -> emptyList()
            }

            // Hiển thị danh sách trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listViewNumbers.adapter = adapter
        }
    }

    // Hàm tạo danh sách các số chẵn từ 0 đến n
    private fun generateEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    // Hàm tạo danh sách các số lẻ từ 1 đến n
    private fun generateOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    // Hàm tạo danh sách các số chính phương từ 0 đến n
    private fun generateSquareNumbers(n: Int): List<Int> {
        val maxSquareRoot = sqrt(n.toDouble()).toInt()
        return (0..maxSquareRoot).map { it * it }.filter { it <= n }
    }
}
