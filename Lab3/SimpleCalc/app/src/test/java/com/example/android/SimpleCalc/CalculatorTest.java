package com.example.android.SimpleCalc;

//import android.test.suitebuilder.annotation.SmallTest;
//import androidx.test.filters.SmallTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)
//@SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    @Test
    public void addTwoNumbersNegative() {
        double resultAdd = mCalculator.add(-1d, 2d);
        assertThat(resultAdd, is(equalTo(1d)));
    }

    @Test
    public void addTwoNumbersFloats() {
        double resultAdd = mCalculator.add(1.111f, 1.111d);
        assertThat(resultAdd, is(closeTo(2.222, 0.01)));
    }

    @Test
    public void subTwoNumbers() {
        double resultSub = mCalculator.sub(2d, 2d);
        assertThat(resultSub, is(equalTo(0d)));
    }

    @Test
    public void subWorksWithNegativeResult() {
        double resultSub = mCalculator.sub(1d, 15d);
        assertThat(resultSub, is(equalTo(-14d)));
    }

    @Test
    public void mulTwoNumbers() {
        double resultMul = mCalculator.mul(32d, 2d);
        assertThat(resultMul, is(equalTo(64d)));
    }

    @Test
    public void divTwoNumbers() {
        double resultDiv = mCalculator.div(32d,2d);
        assertThat(resultDiv, is(equalTo(16d)));
    }

    @Test
    public void divTwoNumbersZero() {
        double resultDiv = mCalculator.div(32d,0);
        assertThat(resultDiv, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    @Test (expected=IllegalArgumentException.class)
    public void divByZeroThrows(){
        mCalculator.div(32d, 0);
//        assertThat(mCalculator.div(32d, 0), is(equalTo(IllegalArgumentException.class)));
    }

    @Test
    public void powPositiveIntegers() {
        double resultPow = mCalculator.pow(2, 3);
        assertThat(resultPow, is(equalTo(8d)));
    }

    @Test
    public void powNegativeIntegerFirstOperand() {
        double resultPow = mCalculator.pow(-2, 3);
        assertThat(resultPow, is(equalTo(-8d)));
    }

    @Test
    public void powNegativeIntegerSecondOperand() {
        double resultPow = mCalculator.pow(2, -3);
        assertThat(resultPow, is(equalTo(0.125d)));
    }

    @Test
    public void powZeroPositiveInteger() {
        double resultPow = mCalculator.pow(0, 3);
        assertThat(resultPow, is(equalTo(0d)));
    }

    @Test
    public void powZeroSecondOperand() {
        double resultPow = mCalculator.pow(2, 0);
        assertThat(resultPow, is(equalTo(1d)));
    }

    @Test
    public void powZeroNegativeOneSecondOperand() {
        double resultPow = mCalculator.pow(0, -1);
        assertThat(resultPow, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    @Test
    public void powNegativeZeroNegativeOperand() {
        double resultPow = mCalculator.pow(-0, -3);
        assertThat(resultPow, is(equalTo(Double.NEGATIVE_INFINITY)));
    }
}
