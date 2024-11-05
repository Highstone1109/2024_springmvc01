package src.main.java.com.ict.edu04.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.edu04.service.AjaxMembersService;
import com.ict.edu04.vo.MembersVO;

@RestController
public class AjaxDBController {
	
	@Autowired
	private AjaxMembersService ajaxMembersService;
	
	@RequestMapping(value = "/ajax_db_list", produces = "application/xml; charset=utf-8")
	@ResponseBody
	public String getAjaxList() {
		List<MembersVO> list =  ajaxMembersService.getMemberList();
		
		if (list != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<members>");
			for (MembersVO k : list) {
				sb.append("<member>");
				sb.append("<m_idx>" + k.getM_idx()  +"</m_idx>");
				sb.append("<m_id>" + k.getM_id()  +"</m_id>");
				sb.append("<m_pw>" + k.getM_pw()  +"</m_pw>");
				sb.append("<m_name>" + k.getM_name()  +"</m_name>");
				sb.append("<m_age>" + k.getM_age()  +"</m_age>");
				sb.append("<m_reg>" + k.getM_reg()  +"</m_reg>");
				sb.append("</member>");
			}
			
			sb.append("</members>");
			
			return sb.toString();
		}
		
		return "fail";
		
	}
	@RequestMapping(value = "/ajax_db_list2", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAjaxList2() {
		// Spring�뿉�꽌 json�쓣 留뚮뱾嫄곕굹 �뙆�떛�븷 �븣�뒗 �뿬�윭媛�吏� �씪�씠釉뚮윭由щ�� �궗�슜�븷�닔�엳�떎.
		// 洹몄쨷 gson�씠�씪�뒗 �씪�씠釉뚮윭由щ�� �궗�슜�븯�옄 (pom.xml�뿉 �벑濡�)
		List<MembersVO> list =  ajaxMembersService.getMemberList();
		
		if (list != null) {
			Gson gson = new Gson();
			String jsonString = gson.toJson(list);
			return jsonString;
		}
		
		return "fail";
		
	}
	
	@RequestMapping(value = "/ajax_db_list3", produces = "application/csv; charset=utf-8")
	@ResponseBody
	public String getAjaxList3() {
		List<MembersVO> list =  ajaxMembersService.getMemberList();
		
		if (list != null) {
			// CSV 留뚮뱾湲� ( 
			StringBuffer sb = new StringBuffer();
			
			// �뿤�뜑 異붽� (而щ읆紐�) �샃�뀡(�깮�왂媛��뒫)
			sb.append("m_idx,m_id,m_pw,m_name,m_age,m_reg\n");
			
			for (MembersVO k : list) {
				sb.append(k.getM_idx()).append(",")
				  .append(k.getM_id()).append(",")
				  .append(k.getM_pw()).append(",")
				  .append(k.getM_name()).append(",")
				  .append(k.getM_age()).append(",")
				  .append(k.getM_reg()).append("\n");
			}
			
			return sb.toString();
		}
		return "fail";
	}
	
	@RequestMapping(value = "/ajax_id_chk", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getAjaxIdChk(HttpServletRequest request) {
		String m_id = request.getParameter("m_id");
		String result = ajaxMembersService.getMemberIdChk(m_id);
		return result;
		
	}
	
	@RequestMapping(value = "/ajax_member_join", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getAjaxMemberJoin(MembersVO mvo) {
		String result =  ajaxMembersService.getMemberInsert(mvo);
		return result;
		
	}
	
	@RequestMapping(value = "/ajax_member_delete", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getAjaxMemberDelete(@RequestParam("m_idx") String m_idx) {
		// String m_idx = request.getParameter("m_idx") => @RequestParam("m_idx") String m_idx 
		String result =  ajaxMembersService.getMemberDelete(m_idx);
		return result;
		
	}
}















