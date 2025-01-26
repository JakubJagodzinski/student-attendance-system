package com.attendance_backend.service;

import com.attendance_backend.model.Term;
import com.attendance_backend.repository.TermRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TermService {

    private final TermRepository termRepository;

    public TermService(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    // Dodanie terminu
    public Term addTerm(Term term) {
        return termRepository.save(term);
    }

    // Pobranie terminu po ID
    public Term getTermById(Long termId) {
        Optional<Term> term = termRepository.findById(termId);
        return term.orElse(null);
    }

    // Pobranie wszystkich terminów
    public List<Term> getAllTerms() {
        return termRepository.findAll();
    }

    // Usunięcie terminu
    public void deleteTerm(Long termId) {
        termRepository.deleteById(termId);
    }

    // Zaktualizowanie terminu
    public Term updateTerm(Long termId, Term termDetails) {
        Term term = termRepository.findById(termId).orElse(null);
        if (term != null) {
            term.setTermName(termDetails.getTermName());
            term.setTermDate(termDetails.getTermDate());
            term.setTermStartTime(termDetails.getTermStartTime());
            term.setTermEndTime(termDetails.getTermEndTime());
            term.setTermGroupId(termDetails.getTermGroupId());
            return termRepository.save(term);
        }
        return null;
    }
}
