public class MatrixWord {
    private StringBuilder stringBuilder;
    private int indexForWord;

    public MatrixWord() {
        stringBuilder = new StringBuilder();
        indexForWord = 1;
    }

    public String work(String word, String input,
                       int startPositionX, int startPositionY) {
        checkForNullWord(word);
        char[][] grid = createMatrix(input);
        search2D(grid, word, startPositionX, startPositionY);
        return generateResultCells(startPositionX, startPositionY);
    }

    private char[][] generateMatrix(String input) {
        checkForNullWord(input);

        int length = (int) Math.sqrt(input.length());
        double sourceLength = Math.sqrt(input.length());

        if (length != sourceLength) {
            throw new RuntimeException("Not square matrix");
        }
        return new char[length][length];
    }

    private char[][] createMatrix(String input) {
        char[][] newArr = generateMatrix(input);
        int index = 0;

        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr.length; j++) {
                newArr[i][j] = input.charAt(index++);
            }
        }
        return newArr;
    }

    private boolean search2D(char[][] grid, String word, int positionX, int positionY) {
        char oldChar = grid[positionY][positionX];

        if (indexForWord >= word.length()) {
            return true;
        }
        final int top = positionY - 1 < 0 ? positionY : positionY - 1;
        final int bottom = positionY + 1 >= grid.length ? positionY : positionY + 1;
        final int right = positionX + 1 >= grid.length ? positionX : positionX + 1;
        final int left = positionX - 1 < 0 ? positionX : positionX - 1;

        if (grid[top][positionX] == word.charAt(indexForWord)) {
            indexForWord++;
            grid[positionY][positionX] = ' ';
            boolean check = search2D(grid, word, positionX, top);
            if (check) {
                stringBuilder
                        .append("[").append(top).append(",")
                        .append(positionX).append("]").append("->");
                return true;
            } else {
                grid[positionY][positionX] = oldChar;
                indexForWord--;
            }
        }
        if (grid[bottom][positionX] == word.charAt(indexForWord)) {
            indexForWord++;
            grid[positionY][positionX] = ' ';
            boolean check = search2D(grid, word, positionX, bottom);
            if (check) {
                stringBuilder
                        .append("[").append(bottom).append(",")
                        .append(positionX).append("]").append("->");
                return true;
            } else {
                grid[positionY][positionX] = oldChar;
                indexForWord--;
            }
        }
        if (grid[positionY][left] == word.charAt(indexForWord)) {
            indexForWord++;
            grid[positionY][positionX] = ' ';
            boolean check = search2D(grid, word, left, positionY);
            if (check) {
                stringBuilder
                        .append("[").append(positionY).append(",")
                        .append(left).append("]").append("->");
                return true;
            } else {
                grid[positionY][positionX] = oldChar;
                indexForWord--;
            }
        }
        if (grid[positionY][right] == word.charAt(indexForWord)) {
            indexForWord++;
            grid[positionY][positionX] = ' ';
            boolean check = search2D(grid, word, right, positionY);
            if (check) {
                stringBuilder
                        .append("[").append(positionY).append(",")
                        .append(right).append("]").append("->");
                return true;
            } else {
                grid[positionY][positionX] = oldChar;
                indexForWord--;
            }
        }
        return false;
    }

    private void checkForNullWord(String word) {
        if (word == null || word.equals("")) {
            throw new NullPointerException("Value is empty or null");
        }
    }

    private String generateResultCells(int startPositionX, int startPositionY) {
        String[] splitOldString = stringBuilder.toString().split("->");
        StringBuilder result = new StringBuilder();

        result.append("[").append(startPositionY).append(",")
                .append(startPositionX).append("]").append("->");

        for (int i = splitOldString.length - 1; i >= 0; i--) {
            if (i == 0) {
                result.append(splitOldString[i]);
            } else {
                result.append(splitOldString[i]).append("->");
            }
        }
        return result.toString();
    }
}
