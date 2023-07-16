package hello.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TrafficController {

    @Autowired
    private final DataSource dataSource;
    private final List<String> list = new ArrayList<>();

    @GetMapping("/cpu")
    public String cpu() {
        log.info("cpu");

        long value = 0;
        for (long i = 0; i < 1000000000000L; i++) {
            value++;
        }
        return "ok value=" + value;
    }

    @GetMapping("/jvm")
    public String jvm() {
        log.info("jvm");
        for (int i = 0; i < 1000000; i++) {
            list.add("hello jvm!" + i);
        }

        return "ok";
    }

    @GetMapping("/jdbc")
    public String jdbc() throws SQLException {
        log.info("jdbc");
        Connection conn = dataSource.getConnection();
        log.info("connection info={}", conn);
        //conn.close(); // 커넥션을 닫지 않음
        return "ok";
    }

    @GetMapping("/error-log")
    public String errorLog() {
        log.error("error log");
        return "error";
    }
}
