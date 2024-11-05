package src.main.java.com.ict.edu01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Start4Controller {
	
	@PostMapping("/start4")
	// @RequestMapping("/start4")
	// @GetMapping("/start4")
	public ModelAndView test01() {
		ModelAndView mv = new ModelAndView("day01/result4");
		  String[] carName =  {"�젣�꽕�떆�뒪!", "k9", "洹몃옖��", "k7"};
		  mv.addObject("carName", carName);
		return mv;
	}
}
