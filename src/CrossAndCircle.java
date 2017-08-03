import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrossAndCircle {

    private String[] moves;
    private int[][] winsCombination;
    private HashSet<Integer> possibleSetMoves = Stream.of(1,2,3,4,5,6,7,8,9).collect(Collectors.toCollection(HashSet::new));
    private int lastHumMove = 0, lastComMove = 0;
    private Scanner sc;
    private int round;
    private String level = "Hard";
    private boolean showNumberFields = true;
    private String humanFigure = "X";
    private String compFigure = "O";
    private Set<Integer> humanSetMoves;


    //---------------MENU-------------------------------------
    private CrossAndCircle() {
        selectMainMenu();
    }

    private void mainMenu() {
        System.out.flush();
        System.out.println("-------------------------");
        System.out.printf("|%3s|", " Cross and circle GAME ");
        System.out.println("\n-------------------------");
        System.out.printf("%4s", "\n[1] Play \n");
        System.out.printf("%4s", "[2] Settings\n");
        System.out.printf("%4s", "[0] Exit \n");
        System.out.printf("%2s", "\nWrite a selected number 0,1 or 2: ");
    }

    private void settingsMenu() {
        System.out.flush();
        System.out.println("------------");
        System.out.printf("|%3s|", " SETTINGS ");
        System.out.println("\n------------");
        System.out.printf("%4s", "\n[1] Level: ");
        System.out.printf("%19s", level);
        System.out.printf("%4s", "\n[2] Show field number: ");
        System.out.printf("%7s", showNumberFields);
        System.out.printf("%4s", "\n[3] Your figure: ");
        System.out.printf("%13s", humanFigure);
        System.out.printf("%4s", "\n[0] Back \n");
        System.out.printf("%2s", "\nWrite a selected number 0,1,2 or 3: ");
    }


    private void selectLevelMenu() {
        System.out.flush();
        System.out.println("------------");
        System.out.printf("|%3s|", " SETTINGS ");
        System.out.println("\n------------");
        System.out.printf("%2s", "Choice level: \n");
        System.out.printf("%4s", "[1] Various \n");
        System.out.printf("%4s", "[2] Hard \n");
        System.out.printf("%4s", "\n[0] Back \n");
        System.out.printf("%2s", "\nWrite a selected number 0,1 or 2: ");
    }

    private void selectFieldNumberMenu() {
        System.out.flush();
        System.out.println("------------");
        System.out.printf("|%3s|", " SETTINGS ");
        System.out.println("\n------------");
        System.out.printf("%2s", "Show field number: \n");
        System.out.printf("%4s", "[1] True \n");
        System.out.printf("%4s", "[2] False \n");
        System.out.printf("%4s", "\n[0] Back \n");
        System.out.printf("%2s", "\nWrite a selected number 0,1 or 2: ");
    }

    private void selectFigureMenu() {
        System.out.flush();
        System.out.println("------------");
        System.out.printf("|%3s|", " SETTINGS ");
        System.out.println("\n------------");
        System.out.printf("%2s", "Choice figure: \n");
        System.out.printf("%4s", "[1] Cross X \n");
        System.out.printf("%4s", "[2] Circle O \n");
        System.out.printf("%4s", "\n[0] Back \n");
        System.out.printf("%2s", "\nWrite a selected number 0,1 or 2: ");
    }


    private void selectMainMenu() {
        mainMenu();
        try {
            sc = new Scanner(System.in);
            switch (sc.next()) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    startGame();
                    break;
                case "2":
                    selectSetting();
                    break;
                default:
                    selectMainMenu();
            }
        } catch (Exception e) {
            selectMainMenu();
        }
    }

    private void selectSetting() {
        settingsMenu();
        try {
            Scanner sc = new Scanner(System.in);
            switch (sc.next()) {
                case "0":
                    selectMainMenu();
                    break;
                case "1":
                    selectLevel();
                    break;
                case "2":
                    selectFieldNumber();
                    break;
                case "3":
                    selectFigure();
                    break;
                default:
                    settingsMenu();
                    break;
            }
        } catch (Exception e) {
            settingsMenu();
        }
    }

    private void selectLevel() {
        selectLevelMenu();
        try {
            sc = new Scanner(System.in);
            String s = sc.next();
            switch (s) {
                case "0":
                    selectSetting();
                    break;
                case "1":
                    level = "Various";
                    break;
                case "2":
                    level = "Hard";
                    break;
                default:
                    selectLevelMenu();
                    break;
            }
            selectSetting();
        } catch (Exception e) {
            selectLevelMenu();
        }
    }

    private void selectFieldNumber() {
        selectFieldNumberMenu();
        try {
            sc = new Scanner(System.in);
            String s = sc.next();
            switch (s) {
                case "0":
                    selectSetting();
                    break;
                case "1":
                    showNumberFields = true;
                    break;
                case "2":
                    showNumberFields = false;
                    break;
                default:
                    selectFieldNumberMenu();
                    break;
            }
            selectSetting();
        } catch (Exception e) {
            selectFieldNumberMenu();
        }
    }


    private void selectFigure() {
        selectFigureMenu();
        try {
            sc = new Scanner(System.in);
            switch (sc.next()) {
                case "0":
                    selectSetting();
                    break;
                case "1":
                    humanFigure = "X";
                    compFigure = "O";
                    break;
                case "2":
                    humanFigure = "O";
                    compFigure = "X";
                    break;
                default:
                    selectFigureMenu();
            }
            selectSetting();
        } catch (Exception e) {
            selectFigureMenu();
        }
    }
//--------------------GAME------------------------

    private void drawVerticalLine(String one, String two, String three) {
        System.out.printf("%3s", one + " ");
        System.out.printf("|%3s|", two + " ");
        System.out.printf("%3s", three + " ");
    }

    private void drawHorizontalLine() {
        System.out.println("\n---+---+---");
    }

    private void buildMoves() {
        moves = new String[10];
        if (showNumberFields) {
            for (int i = 1; i < moves.length; i++) {
                moves[i] = Integer.toString(i);
            }
        } else {
            for (int i = 1; i < moves.length; i++) {
                moves[i] = " ";
            }
        }
    }

    private void drawBoard() {
        System.out.println("-----------");
        System.out.printf("|%3s|", " ROUND " + round +" ");
        System.out.println("\n-----------\n");
        System.out.flush();
        drawVerticalLine(moves[1], moves[2], moves[3]);
        drawHorizontalLine();
        drawVerticalLine(moves[4], moves[5], moves[6]);
        drawHorizontalLine();
        drawVerticalLine(moves[7], moves[8], moves[9]);
        System.out.println();
    }

    private void drawMoveMenu() {
        System.out.printf("%3s", "\nWrite number of field, where you place " + humanFigure + " : ");
    }

    private void startGame() {
        winsCombination = new int[][]{{1, 2, 3},{1, 4, 7}, {1, 5, 9}, {2, 5, 8},  {3, 6, 9}, {3, 5, 7}, {4, 5, 6}, {7, 8, 9}};
        round = 1;
        humanSetMoves = new HashSet<>();
        buildMoves();
        while (!isWin(lastHumMove,humanFigure)) {
            drawBoard();
            play();
            if(possibleSetMoves.isEmpty()){
                drawBoard();
                System.out.println("\n-------------");
                System.out.printf("|%3s|", " IT'S DRAW ");
                System.out.println("\n-------------");
                break;
            }
        }
    }

    private boolean isMove(int field) {
        return !possibleSetMoves.contains(field) ;
    }



    private void play() {
        drawMoveMenu();
        try {
            sc = new Scanner(System.in);
            lastHumMove = sc.nextInt();
            if (isMove(lastHumMove)) {
                System.out.printf("%3s", "That field is occupied! Select different field.\n");
                play();
            } else {
                // in there give switch (humanfirst, comFirst)
                humMove();
                compMove();
                //after switch
                round++;
            }
        } catch (Exception e) {
            play();
        }
    }

    private void humMove(){
        moves[lastHumMove] = humanFigure;
        humanSetMoves.add(lastHumMove);
        possibleSetMoves.remove(lastHumMove);
    }

    private void compMove() {
//        if(round == 1 && Stream.of(1, 3, 7, 9).collect(Collectors.toSet()).contains(lastHumMove)) {
//            moves[5] = compFigure;
//        }else if(round == 1)


    }

    private boolean checkCombination(int row, String figure) {
        int[] group = winsCombination[row];
        if (moves[group[0]].equals(figure) && moves[group[1]].equals(figure) && moves[group[2]].equals(figure) && figure.equals(humanFigure)) {
            drawBoard();
            System.out.println("\n------------");
            System.out.printf("|%3s|", " YOU WIN! ");
            System.out.println("\n------------");
            return true;
        } else if (moves[group[0]].equals(figure) && moves[group[1]].equals(figure) && moves[group[2]].equals(figure) && figure.equals(compFigure)) {
            drawBoard();
            System.out.println("\n-------------");
            System.out.printf("|%3s|", " YOU LOST! ");
            System.out.println("\n-------------");
            return true;
        } else if (!moves[group[0]].equals(figure)||!moves[group[1]].equals(figure) || !moves[group[2]].equals(figure)
                && ((figure.equals(humanFigure) || (figure.equals(compFigure))))) {
            winsCombination[row][0] = 0;
        }
        return false;
    }

    private boolean isWin(int lastMove, String figure) {
        if (round >= 4) {
            switch (lastMove) {
                case 1:
                    if(winsCombination[0][0] != 0){
                        return checkCombination(0, figure);
                    }else if(winsCombination[1][0] != 0){
                        return checkCombination(1, figure);
                    }else if(winsCombination[2][0] != 0){
                        return checkCombination(2, figure);
                    }
                case 2:
                    if(winsCombination[0][0] != 0){
                        return checkCombination(0, figure);
                    }else if(winsCombination[3][0] != 0){
                        return checkCombination(3, figure);
                    }
                case 3:
                    if(winsCombination[0][0] != 0){
                        return checkCombination(0, figure);
                    }else if(winsCombination[4][0] != 0){
                        return checkCombination(4, figure);
                    }else if(winsCombination[5][0] != 0){
                        return checkCombination(5, figure);
                    }
                case 4:
                    if(winsCombination[1][0] != 0){
                        return checkCombination(1, figure);
                    }else if(winsCombination[6][0] != 0){
                        return checkCombination(6, figure);
                    }
                case 5:
                    if(winsCombination[2][0] != 0){
                        return checkCombination(2, figure);
                    }else if(winsCombination[3][0] != 0){
                        return checkCombination(3, figure);
                    }else if(winsCombination[5][0] != 0){
                        return checkCombination(5, figure);
                    }else if(winsCombination[6][0] != 0){
                        return checkCombination(6, figure);
                    }
                case 6:
                    if(winsCombination[4][0] != 0){
                        return checkCombination(4, figure);
                    }else if(winsCombination[6][0] != 0){
                        return checkCombination(6, figure);
                    }
                case 7:
                    if(winsCombination[1][0] != 0){
                        return checkCombination(1, figure);
                    }else if(winsCombination[5][0] != 0){
                        return checkCombination(5, figure);
                    }else if(winsCombination[7][0] != 0){
                        return checkCombination(7, figure);
                    }
                case 8:
                    if(winsCombination[3][0] != 0){
                        return checkCombination(3, figure);
                    }else if(winsCombination[7][0] != 0){
                        return checkCombination(7, figure);
                    }
                case 9:
                    if(winsCombination[2][0] != 0){
                        return checkCombination(2, figure);
                    }else if(winsCombination[4][0] != 0){
                        return checkCombination(4, figure);
                    }else if(winsCombination[6][0] != 0){
                        return checkCombination(6, figure);
                    }
                    default:
                        return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new CrossAndCircle();
    }
}
