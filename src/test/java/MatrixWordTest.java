import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatrixWordTest {
    private MatrixWord matrixWord;

    @BeforeEach
    void createObject() {
        matrixWord = new MatrixWord();
    }

    @Test
    void usualCases_test1() {
        String word = "DISABILITATING";
        String input = "FBDHBAAGNITISTDASABIDDBITILBNILALASGTATIGIYGNTGND";
        assertEquals("[0,2]->[1,2]->[2,2]->[2,3]->[2,4]->[3,4]->[4,4]->"
                        + "[5,4]->[5,3]->[5,2]->[5,1]->[4,1]->[4,0]->[5,0]",
                matrixWord.work(word, input, 2, 0));
    }

    @Test
    void usualCases_test2() {
        String word = "APPLE";
        String input = "UKJVXNAPBXELPLHVNLDKBVVNM";
        assertEquals("[1,1]->[1,2]->[2,2]->[2,1]->[2,0]",
                matrixWord.work(word, input, 1, 1));
    }

    @Test
    void usualCases_test3() {
        String word = "KING";
        String input = "QLGNAEKIRLRNGEAE";
        assertEquals("[1,2]->[1,3]->[0,3]->[0,2]",
                matrixWord.work(word, input, 2, 1));
    }

    @Test
    void usualCases_test4() {
        String word = "BOBA";
        String input = "QWBOABOBGSBSERTY";
        assertEquals("[0,2]->[1,2]->[1,1]->[1,0]",
                matrixWord.work(word, input, 2, 0));
    }

    @Test
    void case_emptyWord() {
        String word = "";
        String input = "QWBOABOBGSBSERTY";
        assertThrows(NullPointerException.class,
                () -> matrixWord.work(word, input, 2, 0));
    }

    @Test
    void case_nullWord() {
        String word = null;
        String input = "QWBOABOBGSBSERTY";
        assertThrows(NullPointerException.class,
                () -> matrixWord.work(word, input, 2, 0));
    }

    @Test
    void case_emptyInput() {
        String word = "APPLE";
        String input = "";
        assertThrows(NullPointerException.class,
                () -> matrixWord.work(word, input, 1, 1));
    }

    @Test
    void case_nullInput() {
        String word = "APPLE";
        String input = null;
        assertThrows(NullPointerException.class,
                () -> matrixWord.work(word, input, 1, 1));
    }

    @Test
    void case_notSquareMatrix() {
        String word = "BOBA";
        String input = "QWBOABOBGSBSERTYS";
        assertThrows(RuntimeException.class,
                () -> matrixWord.work(word, input, 2, 0));
    }
}
