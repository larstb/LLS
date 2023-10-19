package at.ltb.apprenticedeliverysystem.core._common;

import at.ltb.apprenticedeliverysystem.core._common.auth.AuthUserHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/heroes")
public class TestRestController {
    private final List<String> someHeroes = List.of(
            "Ken",
            "Yannick",
            "Pieter");
    private final AuthUserHelper helper;

    public TestRestController(AuthUserHelper helper) {
        this.helper = helper;
    }

    @GetMapping
    public List<String> heroes() {
        helper.getCurrentUser();
        return someHeroes;
    }

    @GetMapping("/{id}")
    public String hero(@PathVariable("id") String name) {
        return someHeroes.stream()
                .filter(h -> h.equals(name))
                .findFirst()
                .orElse(null);
    }
}
