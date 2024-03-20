package com.estsoft.blogjpa;

import org.junit.jupiter.api.*;

import java.util.HashMap;

public class JUnitTotalTest {

    HashMap<String, String> map=new HashMap<>();

    @BeforeAll                    // 전체 테스트를 시작하기 전 1회 실행
    public static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach                    // 각각의 테스트 케이스를 실행하기 전마다 실행
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1() {
        map.put("test1","value1");
        System.out.println("test1"+map);
    }

    @Test
    public void test2() {
        map.put("test2","value2");
        System.out.println("test2"+map);
    }

    @Test
    public void test3() {
        map.put("test3","value3");
        System.out.println("test3"+map);
    }

    @AfterAll                        // 전체 테스트를 마치고 종료하기 전에 1회 실행
    public static void afterAll() {
        System.out.println("@AfterAll");
    }

    @AfterEach                        // 각각의 테스트 케이스를 종료하기 전마다 실행
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}