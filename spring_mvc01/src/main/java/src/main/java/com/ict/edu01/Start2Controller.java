package src.main.java.com.ict.edu01;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Start2Controller implements Controller{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ModelAndView mv = new ModelAndView();
		 // 諛⑸쾿1)
		 // ModelAndView mv = new ModelAndView();
		 // mv.setViewName(酉곕꽕�엫 =�릺�룎�븘媛� jsp�씠由� = "result1");
		   mv.setViewName("result2");
			
		 // 諛⑸쾿2)
		 // ModelAndView mv = new ModelAndView("result1");
		   
		   String[] dogName = {"�븙肄⑹씠", "吏꾨룎�씠", "留먮났�씠", "�븯�삤"};
		   mv.addObject("dogName", dogName);
		   
		   ArrayList<String> catName = new ArrayList<String>();
		   catName.add("戮�誘�");
		   catName.add("�굹鍮�");
		   catName.add("�떖肄⑹씠");
		   catName.add("源뚮쭩�씠");
		   mv.addObject("catName", catName);
		   
		return mv;
	}
}
