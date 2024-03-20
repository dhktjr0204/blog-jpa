package com.estsoft.blogjpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitTest {

    @Test
    public void test(){
        int a=1;
        int b=2;
        int sum=3;

        assertEquals(sum,a+b);
        assertThat(a+b).isEqualTo(sum);
    }
}
