package src.main.java.com.ict.edu06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu05.service.EmpService;
import com.ict.edu05.vo.EmpVO;

@Controller
public class EmpController {
	@Autowired
	private EmpService empService;
	
	@RequestMapping("/emp")
	public ModelAndView emp_form() {
		return new ModelAndView("day05/emp_form");
	}
	
	@PostMapping("/emp_getlist")
	public ModelAndView emp_list() {
		try {
			ModelAndView mv = new ModelAndView("day05/emp_list");
			List<EmpVO> list = empService.getList();
			mv.addObject("list", list);
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	@PostMapping("/emp_search")
	public ModelAndView emp_search(@RequestParam("deptno") String deptno) {
		try {
			ModelAndView mv = new ModelAndView("day05/emp_search");
			List<EmpVO> list = empService.getSearch(deptno);
			mv.addObject("list", list);
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	// �뙆�씪誘명꽣媛�? vo�뿉 議댁?���븯吏� �븡�쓣 寃쎌?�� 泥섎?�� 諛⑸�? : 
	 // 1. vo �꽔�뒗�떎.
 	 // 2. 蹂꾨룄濡�? 諛쏆븘��? �굹以묒�? Map �쑝濡� 泥섎?��   
	/*
	@PostMapping("/emp_dynamic_search")
	public ModelAndView emp_dynamic_search(EmpVO empvo) {
		try {
			ModelAndView mv = new ModelAndView("day05/emp_dynamic");
			List<EmpVO> list = empService.getSearch(empvo);
			mv.addObject("list", list);
			mv.addObject("idx", empvo.getIdx());
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	*/
	
	// �떎�쓬 jsp�뿉 諛쏆�? idx?���? 洹몃�濡�? �꽆湲곌�? �쐞�빐�꽌 => @ModelAttribute("idx") 
	@PostMapping("/emp_dynamic_search")
	public ModelAndView emp_dynamic_search(
			@ModelAttribute("idx") String idx,
			@RequestParam("keyword") String keyword) {
		try {
			ModelAndView mv = new ModelAndView("day05/emp_dynamic");
			List<EmpVO> list = empService.getSearch(idx, keyword);
			mv.addObject("list", list);
			
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}









