package ua.rd.deliveryservice.web.rest;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ua.rd.deliveryservice.domain.Pizza;
import ua.rd.deliveryservice.service.PizzaService;


/**
 *
 * @author andrii
 */
@RestController
public class RestPizzaController {

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
    
    @RequestMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }
    
    @RequestMapping(value = "/pizza", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public List<Pizza> view() {
        return pizzaService.findAll();
    }

    @RequestMapping(value = "/pizza/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> find(@PathVariable Integer id) {
        Pizza pizza = pizzaService.find(id);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Link link = linkTo(methodOn(RestPizzaController.class).find(id)).withSelfRel();
        pizza.add(link);
        
        return new ResponseEntity<>(pizza, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/pizza",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<Void> add(@RequestBody Pizza pizza,
            UriComponentsBuilder builder) {
        Pizza savedPizza = pizzaService.save(pizza);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/pizza/{id}").
                buildAndExpand(savedPizza.getPizzaId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
