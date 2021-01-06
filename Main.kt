package tictactoe
import java.util.*
import kotlin.math.abs

fun printCells(str: String){
    println("---------")
    println("| ${str[0]} ${str[1]} ${str[2]} |")
    println("| ${str[3]} ${str[4]} ${str[5]} |")
    println("| ${str[6]} ${str[7]} ${str[8]} |")
    println("---------")
}
fun stateOfGame(str: String): String {
    val winArray = arrayOf("123","456","789","147","258","369","159","357")
    var threeX = false
    var countThreeX = 0
    var countX = 0
    var threeO = false
    var countThreeO = 0
    var countO = 0
    var count_ = 0
    for (pos in winArray){
        //println("${pos[0].toString().toInt()-1} ${pos[1].toString().toInt()-1} ${pos[2].toString().toInt()-1}")
        if (str[pos[0].toString().toInt()-1]=='X'&&
                str[pos[1].toString().toInt()-1]=='X'&&
                str[pos[2].toString().toInt()-1]=='X'){
            countThreeX += 1
            threeX = true
        }
        if (str[pos[0].toString().toInt()-1]=='O'&&
                str[pos[1].toString().toInt()-1]=='O'&&
                str[pos[2].toString().toInt()-1]=='O'){
            countThreeO += 1
            threeO = true
        }
    }
    for (item in str){
        if (item == 'X') countX += 1
        if (item == 'O') countO += 1
        if (item == '_') count_ += 1
    }
    //println("$threeX $countThreeX")
    //println("$threeO $countThreeO")
    //println("$countO $countX")

    if ((count_==0)&&(!threeO)&&(!threeX)) {
        println("Draw")
        return "Draw"
    }

    else if (abs(countO-countX) >=2) println("Impossible")
    else if (countThreeX>1) println("Impossible")
    else if (countThreeO>1) println("Impossible")
    else if ((countThreeX>=1)&&(countThreeO>=1)) println("Impossible")

    else if (countThreeX==1) {
        println("X wins")
        return "X wins"
    }
    else if (countThreeO==1) {
        println("O wins")
        return "O wins"
    }

    else if (count_>=1) println("Game not finished")
    return ""
}

fun checkCoords(str: String, coord: String): Boolean{
    val coords = coord.split(" ")
    //println("coords=$coords")
    for (item in coords) {
        if (item.toIntOrNull()==null){
            println("You should enter numbers!")
            return false
        } else if (!(item.toInt() in 1..3)){
            println("Coordinates should be from 1 to 3!")
            return false
        }
    }
    val coordInString: Int = (coords[0].toInt() - 1) * 3 + coords[1].toInt() - 1
    //println("coordInString=$coordInString")
    if (str[coordInString]!='_'){
        println("This cell is occupied! Choose another one!")
        return false
    }
    return true
}

fun main() {
    val scanner = Scanner(System.`in`)
    //var str = scanner.nextLine()
    var str = "_________"
    printCells(str)

    var enterGoodCoord = false
    var coord = ""
    var turnX = true
    var turnY = false
    var state = ""
    while (!enterGoodCoord) {
        print("Enter the coordinates:")
        coord = scanner.nextLine()
        enterGoodCoord = checkCoords(str, coord)
        when {
            enterGoodCoord && turnX->{
                val coords = coord.split(" ")
                val coordInString: Int = (coords[0].toInt() - 1) * 3 + coords[1].toInt() - 1
                //println("old string=$str")
                str = str.substring(0, coordInString)+
                        "X"+
                        str.substring(coordInString+1,str.length)
                //println("new string=$str")
                printCells(str)
                state = stateOfGame(str)
                when (state){
                    "X wins", "O wins", "Draw"->break
                }
                turnX = false
                turnY = true
                enterGoodCoord = false
            }
            enterGoodCoord && turnY->{
                val coords = coord.split(" ")
                val coordInString: Int = (coords[0].toInt() - 1) * 3 + coords[1].toInt() - 1
                //println("old string=$str")
                str = str.substring(0, coordInString)+
                        "O"+
                        str.substring(coordInString+1,str.length)
                //println("new string=$str")
                printCells(str)
                state = stateOfGame(str)
                when (state){
                    "X wins", "O wins", "Draw"->break
                }
                turnY = false
                turnX = true
                enterGoodCoord = false
            }
        }
    }

}