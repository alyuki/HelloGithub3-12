package com.example;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/simple")
public class SimpleController {
	final static Logger logger = LoggerFactory.getLogger(SimpleController.class);
	
	final static Map<String, String> CHECK_ITEMS =
		    Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
		    {
		      put("checkbox_A", "A");
		      put("checkbox_B", "B");
		      put("checkbox_C", "C");
		      put("checkbox_D", "D");
		      put("checkbox_E", "E");
		    }
		  });
	
	 @RequestMapping(value = "/", method = RequestMethod.GET)
	  public String index(SimpleForm form,Model model) {
	   
	    model.addAttribute("checkItems", CHECK_ITEMS);
	   
	    return "form1";
	  }
	 
	 @RequestMapping(value = "/confirm", method = RequestMethod.POST)
	  public String confirm(@Validated @ModelAttribute SimpleForm form, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	      model.addAttribute("validationError", "不正な値が入力されました。");
	      return index(form, model);
	    }
	    return "confirm";
	  }
	 
}
