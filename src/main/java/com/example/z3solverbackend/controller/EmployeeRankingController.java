package com.example.z3solverbackend.controller;

import com.example.z3solverbackend.service.Z3SolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRankingController {

    @Autowired
    private Z3SolverService z3SolverService;

    @PostMapping("/optimize")
    public Map<String, Object> optimize(@RequestBody Map<String, Double> scores) {
        return z3SolverService.optimizeScores(scores);
    }
}


