package src.main.java.com.ict.edu01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

// �뼱�끂�뀒�씠�뀡�쑝濡� @Controller瑜� �븯硫� �옄�룞�쑝濡� Spring�뿉�꽌 �옄�룞�쑝濡� Controller濡� �씤�떇�븳�떎.

@Controller
public class Start3Controller {
	// URL 留ㅽ븨�씠�� 
	// @RequestMapping(value = "URL", method = RequestMethod.GET)
	// @RequestMapping(value = "URL", method = RequestMethod.POST)
	
	// @RequestMapping("URL")
	// @GetMapping("URL")
	// @PostMapping("URL")
	
	// �빐�떦硫붿꽌�뱶�뒗 URL 留ㅽ븨�씠 ���빞 �떎�뻾�맂�떎.
	// exec(�씤�옄) : �씤�옄�뒗 �븘�슂�븷�븣留� �궗�슜�븯硫� �맂�떎.
	  
	   @GetMapping("/start3")
	// @RequestMapping("/start3")
	// @PostMapping("/start3")   // a 留곹겕�뒗 get 諛⑹떇�씠誘�濡� �삤瑜� 諛쒖깮
	public ModelAndView exec(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("day01/result3");
			mv.addObject("city", "�꽌�슱");
		return mv ;
	}
}









