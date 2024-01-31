package com.example.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(GameUiState())
  val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

  var userGuess by mutableStateOf("")
    private set

  private lateinit var currentWord: String
  private var usedWords: MutableSet<String> = mutableSetOf()

  private fun shuffleCurrentWord(word: String): String {
    val tempWord = word.toCharArray()
    tempWord.shuffle()
    while (String(tempWord).equals(word)) {
      tempWord.shuffle()
    }
    return String(tempWord)
  }

  fun validateUserGuess() {
    if(userGuess.equals(currentWord, ignoreCase = true)){

    } else {
      _uiState.update { currentState -> currentState.copy(isGuessedWrong = true) }
    }
    updateUserGuess("")
  }

  private fun pickRandomWordAndShuffle(): String {
    currentWord = allWords.random()
    if (usedWords.contains(currentWord)) {
      return pickRandomWordAndShuffle()
    } else {
      usedWords.add(currentWord)
      return shuffleCurrentWord(currentWord)
    }
  }

  fun updateUserGuess(guessedWord: String){
    userGuess = guessedWord
  }


  private fun resetGame() {
    usedWords.clear()
    _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
  }

  init {
    resetGame()
  }


}