package hello.controller;

import hello.EventNotFoundException;
import hello.model.Event;
import hello.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class EventsController {

    @Autowired
    EventsRepository eventsRepository;

    @GetMapping("/events")
    public List<Event> fetchEvents() {
        return eventsRepository.findAll();
    }

    @GetMapping("/events/{id}")
    public Optional<Event> Event(@PathVariable long id) { 
    	Optional<Event> idEvent = eventsRepository.findById(id);
    	if (!idEvent.isPresent()) {
    		throw new EventNotFoundException();
    	}
    	else return idEvent;
    } 
    
    @PostMapping("/events/add")
    public Event addEvent(@RequestBody Event event) {
    	eventsRepository.save(event);
    	return event;
    }	
    
     	
     @PutMapping("/events/update/{id}")
    public ResponseEntity<Object> updateEvent(@RequestBody Event event, @PathVariable long id) {
    	Optional<Event> idEvent = eventsRepository.findById(id);
    	if (!idEvent.isPresent()) {
    		return ResponseEntity.notFound().build();
    	}
    	else event.setId(id);
    	
    	eventsRepository.save(event);
    	return ResponseEntity.noContent().build();
    } 
     
    @DeleteMapping("/events/delete/{id}") 
    public ResponseEntity<Object> deleteEvent(@PathVariable long id) {
    	Optional<Event>	idEvent = eventsRepository.findById(id);
    	if (!idEvent.isPresent()) {
    		return ResponseEntity.notFound().build();
    	}
    	else eventsRepository.deleteById(id);
    
    	return ResponseEntity.noContent().build();
    }
   
    
    
    
    
    
    
}

