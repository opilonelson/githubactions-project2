package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JdbcTemplate jdbc;

    // Initialize a simple table (for demo)
    @PostConstruct
    public void init() {
        jdbc.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY, username VARCHAR(100), password VARCHAR(100));");
        jdbc.update("INSERT INTO users (id, username, password) VALUES (1, 'admin', 'admin') ON DUPLICATE KEY UPDATE username=username;");
    }

    // Vulnerable to SQL Injection: building SQL via string concatenation
    @GetMapping("/search")
    public List<Map<String, Object>> search(@RequestParam("q") String q) {
        String sql = "SELECT id, username, password FROM users WHERE username LIKE '%" + q + "%';";
        return jdbc.queryForList(sql);
    }

    @GetMapping("/{id}")
    public Map<String, Object> get(@PathVariable("id") Long id) {
        String sql = "SELECT id, username, password FROM users WHERE id = " + id + ";";
        return jdbc.queryForMap(sql);
    }

    @PostMapping
    public int create(@RequestBody Map<String, Object> body) {
        Long id = ((Number) body.get("id")).longValue();
        String username = (String) body.get("username");
        String password = (String) body.get("password");
        String sql = "INSERT INTO users (id, username, password) VALUES (" + id + ", '" + username + "', '" + password + "')";
        return jdbc.update(sql);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Long id) {
        String sql = "DELETE FROM users WHERE id = " + id;
        return jdbc.update(sql);
    }
}
