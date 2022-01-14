package org.springframework.samples.petclinic.feeding;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {

	private static final String VIEWS_FEEDING_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateFeedingForm";

	private final FeedingService feedingService;
	private final PetService petService;

    
	@Autowired
	public FeedingController(FeedingService feedingService,PetService petService) {
		this.feedingService = feedingService;
        this.petService = petService;
	}
    

	@GetMapping(value = "/create")
	public String initCreationForm(ModelMap modelmap) {
		String view = VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
        modelmap.addAttribute("feeding",new Feeding());
		modelmap.addAttribute("feedingTypes", feedingService.getAllFeedingTypes());
		modelmap.addAttribute("pets", petService.getAllPets());
		return view;
	}

	@PostMapping(value = "/create")
	public String processCreationForm(@Valid Feeding feeding, BindingResult result, ModelMap modelmap) {
		if (result.hasErrors()) {
            modelmap.addAttribute("feeding",new Feeding());
            modelmap.addAttribute("feedingTypes", feedingService.getAllFeedingTypes());
			return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
		}
        else{
            try{
                this.feedingService.save(feeding);
            }catch(UnfeasibleFeedingException ex){
                result.rejectValue("pet", "“La mascota seleccionada no se le puede asignar el plan de alimentación especificado.");
                return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
            }
            return "redirect:/welcome";
        }
	}

}
