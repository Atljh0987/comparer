import experimental.ExOne;
import experimental.ExTwo;
import org.fs.ComparerUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnitTests {

    @Test
    @DisplayName("Compare equals")
    void test1() {
        ExOne exOne = new ExOne();

        exOne.setIntOne(1);
        exOne.setStringOne("fdsafas");
        exOne.setIntTwo(5);

        ExTwo exTwo = new ExTwo();
        exTwo.setIntOne(1);
        exTwo.setStringOne("fdsafas");
        exTwo.setIntTwo(5);
        exTwo.setStringTwo("asdf");

        assertTrue(ComparerUtils.compareByEqualFields(exOne, exTwo));
    }

    @Test
    @DisplayName("Compare equals vise versa")
    void test2() {
        ExOne exOne = new ExOne();

        exOne.setIntOne(1);
        exOne.setStringOne("fdsafas");
        exOne.setIntTwo(5);

        ExTwo exTwo = new ExTwo();
        exTwo.setIntOne(1);
        exTwo.setStringOne("fdsafas");
        exTwo.setIntTwo(5);
        exTwo.setStringTwo("asdf");

        assertTrue(ComparerUtils.compareByEqualFields(exTwo, exOne));
    }

    @Test
    @DisplayName("Compare not equals")
    void test3() {
        ExOne exOne = new ExOne();

        exOne.setIntOne(1);
        exOne.setStringOne("fd2safas");
        exOne.setIntTwo(5);

        ExTwo exTwo = new ExTwo();
        exTwo.setIntOne(1);
        exTwo.setStringOne("fdsafas");
        exTwo.setIntTwo(5);
        exTwo.setStringTwo("asdf");

        assertFalse(ComparerUtils.compareByEqualFields(exOne, exTwo));
    }
}
