package net.proselyte.springsecuritydemo.rest;

import net.proselyte.springsecuritydemo.model.Developer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestController {

    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1l, "Iven", "Ivanov"),
            new Developer(2l, "Sergey", "Sergeev"),
            new Developer(3l, "Petr", "Petrov"))
            .collect(Collectors.toList());

    @GetMapping
    public List<Developer> getAll () {
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id) {
        return DEVELOPERS
                .stream()
                .filter(developer -> developer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable
                                Long id) {
        DEVELOPERS.removeIf(developer -> developer.getId().equals(id));
    }

    @PostMapping
    public Developer addDeveloper(Developer developer) {
        this.DEVELOPERS.add(developer);
        return developer;
    }
}
