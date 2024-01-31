package com.example.unscramble.ui

data class GameUiState (
  val currentScrambledWord: String = "",
  val isGuessedWrong: Boolean = false,
  val score: Int = 0,
  val currentWordIndex: Int = 0,
  val isGameOver: Boolean = false
  )