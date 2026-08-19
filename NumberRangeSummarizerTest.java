package numberrangesummarizer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberRangeSummarizerTest {

    @Test
    void givenEmpty_returnEmpty() {
        NumberRangeSummarizer numberRangeSummarizer = new GeneralRangeSummarizer();

        assertEquals(List.of(), numberRangeSummarizer.collect(""));
        assertEquals("", numberRangeSummarizer.summarizeCollection(List.of()));
    }

    @Test
    void givenInvalid_throwException() {
        NumberRangeSummarizer numberRangeSummarizer = new GeneralRangeSummarizer();

        assertAll(
                () -> assertThrows(Exception.class, () -> numberRangeSummarizer.collect(",")),
                () -> assertThrows(Exception.class, () -> numberRangeSummarizer.collect("-")),
                () -> assertThrows(Exception.class, () -> numberRangeSummarizer.collect("--1")),
                () -> assertThrows(Exception.class, () -> numberRangeSummarizer.collect("12-34")),
                () -> assertThrows(Exception.class, () -> numberRangeSummarizer.collect("1,2-,3"))
        );
    }

    @Test
    void givenValid_returnValid() {
        NumberRangeSummarizer numberRangeSummarizer = new GeneralRangeSummarizer();

        assertAll(
                () -> assertEquals("1", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1"))),
                () -> assertEquals("1, 1", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1,1"))),
                () -> assertEquals("1, 1, 1", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1,1,1"))),
                () -> assertEquals("1-2", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1,2"))),
                () -> assertEquals("1-2, 2-1", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1,2,2,1"))),
                () -> assertEquals("1, 3", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1,3"))),
                () -> assertEquals("1, 3, 5", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1, 3, 5"))),
                () -> assertEquals("-1, -3, -5", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("-1, -3, -5"))),
                () -> assertEquals("-1, -3--5", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("-1, -3, -4, -5"))),
                () -> assertEquals("1, 3-5, 7", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1, 3, 4, 5, 7"))),
                () -> assertEquals("1, 3--3, 7", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1, 3, 2, 1, 0, -1, -2, -3, 7"))),
                () -> assertEquals("1, 3-5, 7", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1, 3, 4, 5, 7"))),
                () -> assertEquals("1-2, 4-5, 7", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1, 2, 4, 5, 7"))),
                () -> assertEquals("1-0, 1", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1, 0, 1"))),
                () -> assertEquals("1, 3, 6-8, 12-15, 21-24, 31", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31"))),
                () -> assertEquals("31, 24-21, 15-12, 8-6, 3, 1", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("31,24,23,22,21,15,14,13,12,8,7,6,3,1")))
        );
    }

    @Test
    void givenSloppyButValid_returnValid() {
        NumberRangeSummarizer numberRangeSummarizer = new GeneralRangeSummarizer();

        assertAll(
                () -> assertEquals("1", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect(",1"))),
                () -> assertEquals("1, 3, 5, 7", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1,3, 5 7"))),
                () -> assertEquals("1, 3-5, 7", numberRangeSummarizer.summarizeCollection(numberRangeSummarizer.collect("1,3,,4,5 , 7")))
        );
    }

}