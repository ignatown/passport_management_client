package ru.job4j.passportclient.controller;

import org.springframework.web.bind.annotation.*;
import ru.job4j.passportclient.domain.Passport;
import ru.job4j.passportclient.service.PassportService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping("/find")
    public List<Passport> findAll(@RequestParam(required = false) Integer seria) {
        List<Passport> rsl;
        if (seria == null) {
            rsl = passportService.findAll();
        } else {
            rsl = passportService.findBySeria(seria);
        }
        return rsl;
    }

    @GetMapping("/unavailable")
    public List<Passport> unavailable() {
        return passportService.findUnavailable();
    }

    @GetMapping("/replaceable")
    public List<Passport> replaceable() {
        return passportService.findReplaceable();
    }

    @PostMapping("/save")
    public Passport save(@RequestBody Passport passport) {
        return passportService.save(passport);
    }

    @PutMapping("/update")
    public Passport update(@RequestBody Passport passport) {
        return passportService.update(passport);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) {
        passportService.delete(id);
    }

}
