<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <h1>Tic-Tac-Toe Console Game</h1>
    <p>Welcome to the Tic-Tac-Toe Console Game! This project is a modern and interactive implementation of the classic Tic-Tac-Toe game, designed for console play. It is developed as part of the advanced programming course at Yazd University. For more information about Yazd University, visit <a href="https://yazd.ac.ir/en" target="_blank">Yazd University</a>.</p>
    <h2>Overview</h2>
    <ul>
        <li><strong>Board Layout:</strong> The game is played on a 4x4 grid, which provides 16 cells for strategic play.</li>
        <li><strong>Locked Cells:</strong> At the start of each game, three cells are randomly locked and cannot be used by players. This adds an extra layer of strategy and unpredictability.</li>
        <li><strong>Winning Objective:</strong> To win, a player must align three of their symbols in a row—either horizontally, vertically, or diagonally.</li>
        <li><strong>Game Modes:</strong> You can choose between single-player or two-player modes. In single-player mode, you will play against a computer opponent that makes random moves.</li>
    </ul>
    <h2>Gameplay</h2>
    <h3>Modes</h3>
    <ul>
        <li><strong>Single Player:</strong>
            <ul>
                <li>Player 1: 'X'</li>
                <li>Computer (Player 2): 'O'</li>
                <li>The computer opponent makes random moves.</li>
            </ul>
        </li>
        <li><strong>Two Player:</strong>
            <ul>
                <li>Player 1: 'X'</li>
                <li>Player 2: 'O'</li>
                <li>Both players take turns making their moves.</li>
            </ul>
        </li>
    </ul>
    <h3>Objective</h3>
    <p>The goal of the game is to get three of your symbols in a row—horizontally, vertically, or diagonally. The game ends when one player achieves this, the opponent wins, or all possible moves are exhausted, resulting in a tie.</p>
    <h3>Game Flow</h3>
    <ul>
        <li><strong>Setup:</strong>
            <ul>
                <li>The game starts with an empty board.</li>
                <li>Three cells are randomly locked and cannot be used.</li>
            </ul>
        </li>
        <li><strong>Turns:</strong>
            <ul>
                <li>Players alternate turns to place their symbols.</li>
                <li>In single-player mode, the computer will make its move after you.</li>
            </ul>
        </li>
        <li><strong>Winning Check:</strong>
            <ul>
                <li>After each move, the game checks if a player has won or if the game is a tie.</li>
            </ul>
        </li>
        <li><strong>Endgame:</strong>
            <ul>
                <li>The game ends when a player wins or when all cells are filled with no winner, resulting in a tie.</li>
            </ul>
        </li>
    </ul>
    <p>Enjoy playing Tic-Tac-Toe and test your strategic thinking!</p>
</body>
</html>
