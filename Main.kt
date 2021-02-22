package tictactoe

fun main() {
    val cells = initializeField()
    printField(cells)

    var turn = 'X'
    while(gameCheck(cells)) {
        if (setCoordinates(cells, turn)) {
            when (turn) {
                'X' -> turn = 'O'
                'O' -> turn = 'X'
            }
        }
    }
}

fun gameOver(array: Array<CharArray>): Boolean {
    for (i in 0..2) {
        for (j in 0..2) {
            if (array[i][j] == ' ') return false
        }
    }
    return true
}

fun initializeField(): Array<CharArray> {
    val cell = Array(3, {CharArray(3)})
    for (i in 0..2) {
        for (j in 0..2) {
            cell[i][j] = ' '
        }
    }
    return cell
}

fun printField(array: Array<CharArray>) {
    println("---------")
    for (i in 0..2) {
        print("| ")
        for (j in 0..2) {
            print(array[i][j])
            print(' ')
        }
        println('|')
    }
    println("---------")
}

fun setCoordinates(array: Array<CharArray>, turn: Char): Boolean {
    print("Enter the coordinates: ")
    val coords = readLine()!!
            .split(" ")
            .toTypedArray()
    val intY = coords[0].toInt() - 1
    val intX = coords[1].toInt() - 1
    if (coords[0].toIntOrNull() != null && coords[1].toIntOrNull() != null) {
        if (intY in 0..2 && intX in 0..2) {
            if (array[intY][intX] == ' ') {
                array[intY][intX] = turn
                printField(array)
                return true
            } else println("This cell is occupied! Choose another one!")
        } else println("Coordinates should be from 1 to 3!")
    } else println("You should enter numbers!")
    return false
}

fun gameCheck(array: Array<CharArray>): Boolean {
    when {
        isWin('X', array) -> {
            print("X wins")
            return false
        }
        isWin('O', array) -> {
            print("O wins")
            return false
        }
        !isWin('X', array) && !isWin('O', array) && gameOver(array) -> {
            print("Draw")
            return false
        }
        else -> return true
    }
}

fun isWin(char: Char, array: Array<CharArray>): Boolean =
        (array[0][0] == char && array[0][1] == char && array[0][2] == char) ||
        (array[1][0] == char && array[1][1] == char && array[1][2] == char) ||
        (array[2][0] == char && array[2][1] == char && array[2][2] == char) ||
        (array[0][0] == char && array[1][0] == char && array[2][0] == char) ||
        (array[0][1] == char && array[1][1] == char && array[2][1] == char) ||
        (array[0][2] == char && array[1][2] == char && array[2][2] == char) ||
        (array[0][0] == char && array[1][1] == char && array[2][2] == char) ||
        (array[0][2] == char && array[1][1] == char && array[2][0] == char)