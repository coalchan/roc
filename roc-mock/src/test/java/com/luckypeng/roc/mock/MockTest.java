package com.luckypeng.roc.mock;

import org.junit.Test;

public class MockTest {

    @Test
    public void mock() {
        // TODO 改为assert方式
        System.out.println("today|yesterday:");
        System.out.println(Mock.mock("@today()"));
        System.out.println(Mock.mock("@yesterday()"));

        System.out.println("integer:");
        System.out.println(Mock.mock("@integer()"));
        System.out.println(Mock.mock("@integer(10)"));
        System.out.println(Mock.mock("@integer(10, 20)"));

        System.out.println("float:");
        System.out.println(Mock.mock("@float()"));
        System.out.println(Mock.mock("@float(0)"));
        System.out.println(Mock.mock("@float(60, 100)"));
        System.out.println(Mock.mock("@float(60, 100, 3)"));
        System.out.println(Mock.mock("@float(60, 100, 3, 5)"));

        System.out.println("character:");
        System.out.println(Mock.mock("@character('lower')"));
        System.out.println(Mock.mock("@character('upper')"));
        System.out.println(Mock.mock("@character('number')"));
        System.out.println(Mock.mock("@character('symbol')"));
        System.out.println(Mock.mock("@character()"));

        System.out.println("string:");
        System.out.println(Mock.mock("@string()"));
        System.out.println(Mock.mock("@string(5)"));
        System.out.println(Mock.mock("@string('lower', 5)"));
        System.out.println(Mock.mock("@string(7, 10)"));
        System.out.println(Mock.mock("@string('aeiou', 1, 3)"));
        System.out.println(Mock.mock("@string('壹贰叁肆伍陆柒捌玖拾', 3, 5)"));
    }
}