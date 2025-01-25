package com.attendance_backend.controller;

import com.attendance_backend.model.Term;
import com.attendance_backend.service.TermService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/terms")
public class TermController {

    private final TermService termService;

    public TermController(TermService termService) {
        this.termService = termService;
    }

    // Dodanie terminu
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Term addTerm(@RequestBody Term term) {
        return termService.addTerm(term);
    }

    // Pobranie terminu po ID
    @GetMapping("/{termId}")
    public Term getTermById(@PathVariable Long termId) {
        return termService.getTermById(termId);
    }

    // Pobranie wszystkich terminów
    @GetMapping
    public List<Term> getAllTerms() {
        return termService.getAllTerms();
    }

    // Usunięcie terminu
    @DeleteMapping("/{termId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTerm(@PathVariable Long termId) {
        termService.deleteTerm(termId);
    }

    // Zaktualizowanie terminu
    @PutMapping("/{termId}")
    public Term updateTerm(@PathVariable Long termId, @RequestBody Term termDetails) {
        return termService.updateTerm(termId, termDetails);
    }
}
