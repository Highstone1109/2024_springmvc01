package src.main.java.com.ict.edu01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 而⑦듃濡ㅻ윭瑜� �뼱�끂�뀒�씠�뀡 �븯吏� �븡�쑝硫� 諛섎뱶�떆 Controller瑜� 援ы쁽(implements)�빐�빞 �븳�떎.
public class Start1Controller implements Controller{
	
	
	// �떎�뻾�븯�뒗 硫붿꽌�뱶 
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// servlet-context.xml �릺�룎�븘 媛붾떎媛� ViewResolver �뿉 �뱾�뼱媛��뒗 �씠由� 
		// (�떒�닚�븯寃� 留먰븯硫� 寃곌낵瑜� 蹂댁뿬以� jsp �씠由�)
		
		// 諛⑸쾿1)
		// ModelAndView mv = new ModelAndView();
		// mv.setViewName(酉곕꽕�엫 =�릺�룎�븘媛� jsp�씠由� = "result1");
		
		// 諛⑸쾿2)
		   ModelAndView mv = new ModelAndView("result1");
		
		   
		// Spring�뿉�꽌 �씪泥섎━ �븯�뒗 �뙣�꽩  
		// (�씪泥섎━) => �꽌鍮꾩뒪(interface) => �꽌鍮꾩뒪�엫�뵆(�겢�옒�뒪) => DAO(interface) => DAO�엫�뵆(�겢�옒�뒪)  => DB    
		//           |======== Business �젅�씠�뼱 =========|    |= Repository(Mapper) �젅�씠�뼱 =| 
		  
		 // JSP�뿉�꽌 �몴�쁽�븯湲� �쐞�빐�꽌 媛� ���옣�븯�뒗 諛⑸쾿
		 // 1. request �씠�슜(JSP MVC�� 媛숈쓬)  
		  request.setAttribute("name", "�솉湲몃룞");
		 
		  // 2. ModelAndView �씠�슜 (default) 
		  mv.addObject("age", 27);
		 
		  // 3. session ( 濡쒓렇�씤 泥섎━�븷�븣留� �궗�슜)
		  request.getSession().setAttribute("phone", "010-7777-9999");
		   
		return mv;
	}
}




