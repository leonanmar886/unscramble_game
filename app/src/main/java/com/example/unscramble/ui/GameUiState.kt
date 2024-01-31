package com.example.unscramble.ui

data class GameUiState (
  val currentScrambledWord: String = "",
  val isGuessedWrong: Boolean = false
  )