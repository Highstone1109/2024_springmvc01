package src.main.java.com.ict.edu02.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu02.service.CalcService;
import com.ict.edu02.service.MemberService;
import com.ict.edu02.vo.CalcVO;
import com.ict.edu02.vo.MembersVO;

@Controller
public class MyController3 {
	
	// �꽌鍮꾩뒪�� 媛숈씠 �떎瑜� 怨녹뿉�꽌 留뚮뱾�뼱吏� 媛앹껜瑜� 媛��졇���꽌 �궗�슜�븯湲� �쐞�븳 �뼱�끂�뀗�씠�뀡
	// @Inject    : java
	// @Autowired : Spring
	
	@Autowired
	private CalcService calcService ;
	
	@Autowired
	private MemberService memberService;
	/*
	@PostMapping("/calc2")
	public ModelAndView calc2Exec(CalcVO cvo) {  
		// CalcVO�뿉 �뙆�씪誘명꽣�뱾�씠 �븣�븘�꽌 �꽔�뼱吏꾨떎.(蹂��닔�� �뙆�씪誘명꽣�씠由꾧컳�븘�빞 �븳�떎.)
		ModelAndView mv = new ModelAndView("day02/result");
		
		// �뙆�씪誘명꽣媛� ���옣�릺�뼱 �엳�뒗吏� �솗�씤 
		// System.out.println(cvo.getS1());
		
		// �씪泥섎━�뒗 service �뿉�꽌 �꽆湲곗옄 
		int result = calcService.getCalc(cvo);
		
		 mv.addObject("cvo", cvo);
		 mv.addObject("result", result);
		return mv;
	}
	*/
	
	@PostMapping("/calc2")
	public ModelAndView calc2Exec(@ModelAttribute("cvo")CalcVO cvo) {  
		// CalcVO�뿉 �뙆�씪誘명꽣�뱾�씠 �븣�븘�꽌 �꽔�뼱吏꾨떎.(蹂��닔�� �뙆�씪誘명꽣�씠由꾧컳�븘�빞 �븳�떎.)
		// Model �겢�옒�뒪�쓽 �냽�꽦�쓣 �씠�슜�빐�꽌 ("cvo", cvo) ���옣�릺�뒗 �뼱�끂�뀒�씠�뀡�씠�떎.
		
		ModelAndView mv = new ModelAndView("day02/result");
		
		// �뙆�씪誘명꽣媛� ���옣�릺�뼱 �엳�뒗吏� �솗�씤 
		// System.out.println(cvo.getS1());
		
		//CalcService cService = new CalcService();
		//int result = cService.getCalc(cvo);
		
		// �씪泥섎━�뒗 service �뿉�꽌 �꽆湲곗옄 
		 int result = calcService.getCalc(cvo);
		
		// mv.addObject("cvo", cvo);
		// ���떊 @ModelAttribute("cvo")CalcVO cvo
		
		 mv.addObject("result", result);
		return mv;
	}
	
	@GetMapping("/member_list")
	public ModelAndView memberList() {
		ModelAndView mv = new ModelAndView("day02/memberResult");
		
		List<MembersVO> list = memberService.getMemberList();
		
		mv.addObject("list", list);
		return mv;
	}
}









