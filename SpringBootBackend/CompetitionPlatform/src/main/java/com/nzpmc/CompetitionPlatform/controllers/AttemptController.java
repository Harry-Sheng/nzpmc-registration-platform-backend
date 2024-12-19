package com.nzpmc.CompetitionPlatform.controllers;

import com.nzpmc.CompetitionPlatform.Service.AttemptService;
import com.nzpmc.CompetitionPlatform.dto.ResultResponse;
import com.nzpmc.CompetitionPlatform.dto.SubmitAttemptRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attempts")
public class AttemptController {
    private final AttemptService attemptService;

    @Autowired
    public AttemptController(AttemptService attemptService){
        this.attemptService = attemptService;
    }

    @PostMapping("/{competitionId}")
    public ResponseEntity<String> submitAttempt(
            @PathVariable String competitionId,
            @RequestBody SubmitAttemptRequest submitAttemptRequest) {
        attemptService.saveAttempt(competitionId, submitAttemptRequest);
        return ResponseEntity.ok("Attempt submitted successfully.");
    }

    @GetMapping("/{competitionId}/results")
    public ResponseEntity<List<ResultResponse>> generateResults(@PathVariable String competitionId) {
        List<ResultResponse> results = attemptService.generateResults(competitionId);
        return ResponseEntity.ok(results);
    }
}