package by.itacademy.jd2.votetask.web.controller;

import by.itacademy.jd2.votetask.domain.Performer;
import by.itacademy.jd2.votetask.dto.PerformerDto;
import by.itacademy.jd2.votetask.service.api.IPerformerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/performers")
public class PerformerController {
    private final IPerformerService service;

    public PerformerController(IPerformerService performerService) {
        this.service = performerService;
    }

    @GetMapping
    public List<Performer> getList() {
        return service.getContent();
    }

    @GetMapping(path = "/{id}")
    public Performer getById(@PathVariable("id") Long performerId){
        return null;
    }

    @PostMapping
    public void doPost(@RequestBody PerformerDto performer) {
        service.create(performer);
    }

    @PutMapping(path = "/{id}/version/{version}")
    public void update(@PathVariable("id") Long performerId,
                       @PathVariable("version") Integer version,
                       @RequestBody PerformerDto performer) {
        service.update(performerId,version,performer);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long performerId) {
        service.delete(performerId);
    }
}
