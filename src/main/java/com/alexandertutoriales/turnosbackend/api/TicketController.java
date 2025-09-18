package com.alexandertutoriales.turnosbackend.api;

import java.util.List;

import com.alexandertutoriales.turnosbackend.api.dto.BoardItemDTO;
import com.alexandertutoriales.turnosbackend.api.dto.PersonResponseDTO;
import com.alexandertutoriales.turnosbackend.api.dto.TicketResponseDTO;
import com.alexandertutoriales.turnosbackend.person.Person;
import com.alexandertutoriales.turnosbackend.person.PersonRepository;
import com.alexandertutoriales.turnosbackend.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

  private final TicketService ticketService;

  private final PersonRepository personRepository;

  @PostMapping
  public TicketResponseDTO create(@RequestBody final PersonResponseDTO dto) {
    Person p = personRepository.findByDni(dto.dni()).orElseGet(() -> {
      Person person = new Person();
      person.setDni(dto.dni());
      person.setName(dto.firstName());
      person.setLastName(dto.lastName());
      return personRepository.save(person);
    });
    return ticketService.create(p);
  }

  @GetMapping("/next")
  public TicketResponseDTO callNext(@RequestParam(name = "module") final int module) {
    return ticketService.callNext(module);
  }

  @PostMapping("/{id}/serve")
  public TicketResponseDTO serve(@PathVariable(name = "id") final Long id) {
    return ticketService.serve(id);
  }

  @GetMapping("/board/last")
  public List<BoardItemDTO> lastCall(@RequestParam(name = "limit", defaultValue = "3") final int limit) {
    return ticketService.lastCalled(limit);
  }
}
