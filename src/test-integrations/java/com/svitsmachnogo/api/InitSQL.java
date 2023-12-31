package com.svitsmachnogo.api;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SqlGroup({
        @Sql(value = "classpath:drop_test_data.sql", executionPhase = AFTER_TEST_METHOD),
        @Sql(value = "classpath:create_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
})
@Retention(RetentionPolicy.RUNTIME)
public @interface InitSQL {
}
