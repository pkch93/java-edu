package edu.pkch.approvals;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApprovalTests {

    @Test
    @DisplayName("확인테스트 샘플")
    void sample() {
        // when
        int actual = fibonacci(5);

        // then
        Approvals.verify(actual);
    }

    private static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci( n - 2);
        }
    }
}
