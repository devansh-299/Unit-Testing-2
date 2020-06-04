package com.codingwithmitch.unittesting2.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static com.codingwithmitch.unittesting2.util.DateUtil.GET_MONTH_ERROR;
import static com.codingwithmitch.unittesting2.util.DateUtil.getMonthFromNumber;
import static com.codingwithmitch.unittesting2.util.DateUtil.monthNumbers;
import static com.codingwithmitch.unittesting2.util.DateUtil.months;
import static org.junit.jupiter.api.Assertions.*;

public class DateUtilTest {

    private static final String today = "05-2019";

    // preferred method of testing methods that throw exception
    @Test
    public void testGetCurrentTimestamp_returnedTimestamp(){
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                assertEquals(today, DateUtil.getCurrentTimeStamp());
                System.out.println("Timestamp is generated correctly");
            }
        });
    }

    // parameterized tests are used to test for multiple values that are passed inside @ValueSource
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9,10,11})
    /**
     * @param monthNumber - it is fetched from the ints list one at a time and used inside the test
     * @param testInfo - passed each time a month is passed, TestInfo [part of JUnit5] is used to
     *                 inject information about the current test. Get more info from here :
     *                 https://junit.org/junit5/docs/5.0.0/api/org/junit/jupiter/api/TestInfo.html
     * @param testReporter - passed each time a month is passed, TestInfo [part of JUnit5] is to
     *                     give publish information of each in correspondence with testInfo. This
     *                     was adopted to void System.out.print... approach which is not advised.
     *                     More details here:
     *                     https://stackoverflow.com/questions/52514720/junit5-testreporter
     *
     */
    public void getMonthFromNumber_returnSuccess(int monthNumber, TestInfo testInfo,
                                                 TestReporter testReporter){
        assertEquals(months[monthNumber], DateUtil.getMonthFromNumber(monthNumbers[monthNumber]));
        System.out.println(monthNumbers[monthNumber] + " : " + months[monthNumber]);
    }


    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11})
    public void testGetMonthFromNumber_returnError(int monthNumber, TestInfo testInfo, TestReporter testReporter){
        int randomInt = new Random().nextInt(90) + 13;
        assertEquals(getMonthFromNumber(String.valueOf(monthNumber * randomInt)), GET_MONTH_ERROR);
        System.out.println(monthNumbers[monthNumber] + " : " + GET_MONTH_ERROR);
    }

}













