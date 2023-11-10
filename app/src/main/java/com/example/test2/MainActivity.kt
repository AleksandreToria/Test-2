package com.example.test2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var anagrams: MutableList<MutableList<String>> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            dataSave()
        }

        binding.outputBtn.setOnClickListener {
            showGroups()
        }
    }

    private fun dataSave() {
        val input = binding.Input.text.toString().trim()
        if (input.isNotEmpty()) {
            var added = false

            for (group in anagrams) {
                if (isAnagram(group[0], input)) {
                    group.add(input)
                    added = true
                    break
                }
            }

            if (!added) {
                val newGroupOfAnagrams = mutableListOf(input)
                anagrams.add(newGroupOfAnagrams)
            }

            binding.Input.text?.clear()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showGroups() {
        val result = StringBuilder()

        for (group in anagrams) {
            result.append("Group: $group\n")
        }

        binding.count.text = "Groups: ${anagrams.size}"
    }

    private fun isAnagram(text1: String, text2: String): Boolean {
        val textToChar1 = text1.toCharArray()
        val textToChar2 = text2.toCharArray()

        textToChar1.sort()
        textToChar2.sort()

        return textToChar1.contentEquals(textToChar2)
    }
}