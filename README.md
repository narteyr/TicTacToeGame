# Tic-Tac-Toe

A two-player Tic-Tac-Toe game for Android, built with Kotlin and Jetpack Compose.

## Features

- 3×3 board drawn on a Compose `Canvas`; tap a cell to place your mark
- Running scoreboard showing X and O wins
- A match is played over 2 rounds; a round ends on a win or a full board (draw)
- Game Over screen announcing the match winner (or a tie), with a **Play Again** button
- **Reset** button to clear the current board
- Material 3 theming with light and dark mode support

## Project structure

```
app/src/main/java/hu/ait/tictactoe/
├── MainActivity.kt              # Entry point; switches between game and game-over screens
└── ui/
    ├── screen/
    │   ├── TicTacToeViewModel.kt   # Game state, move handling, win detection, scoring
    │   ├── TicTacToeScreen.kt      # Main game screen and score card
    │   ├── TicTacToeBoard.kt       # Canvas-drawn board and tap handling
    │   └── GameOverScreen.kt       # Match result and replay
    └── theme/                      # Colors, typography, Material theme
```

## Requirements

- Android Studio (recent version with Compose support)
- JDK 11+
- Android device or emulator running API 24 (Android 7.0) or higher

## Build and run

Open the project in Android Studio and run the `app` configuration, or from the command line:

```bash
./gradlew assembleDebug        # build the debug APK
./gradlew installDebug         # install on a connected device/emulator
```

## Tests

```bash
./gradlew test                 # unit tests
./gradlew connectedAndroidTest # instrumented tests (requires a device/emulator)
```
