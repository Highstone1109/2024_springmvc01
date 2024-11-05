package src.main.java.com.ict.edu04.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// Controller �뒗 servlet-context.xml濡� 由ы꽩�븳�떎.
// RestController �뒗 servlet-context.xml濡� 由ы꽩�븯吏� �븡怨� 釉뚮씪�슦���뿉 異쒕젰

@RestController
public class AjaxTestController {

	@RequestMapping(value = "/test01", produces="text/html; charset=utf-8")
	@ResponseBody
	// 諛섑솚�삎�씠 String �씤 寃쎌슦 臾몄꽌 ���엯�씠 contentType="text/html" 濡� 蹂�寃쎌떆�궎�옄�맂�떎.
	public String Text_Exam01() {
	   String msg = "<h2>Hello World Spring Ajax !! <br>�솚�쁺�빀�땲�떎.</h2>";
	   return msg ;
	}
	
	@RequestMapping(value = "/test02", produces="text/xml; charset=utf-8")
	@ResponseBody
	public String Xml_Exam01() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<products>");
        sb.append("<product count=\"5\" name=\"�젣�꼫�떆�뒪\">�쁽���옄�룞李�</product>");
        sb.append("<product count=\"7\" name=\"移대젋�뒪\">湲곗븘�옄�룞李�</product>");
        sb.append("<product count=\"9\" name=\"移대땲諛�\">湲곗븘�옄�룞李�</product>");
        sb.append("<product count=\"5\" name=\"移댁뒪��\">湲곗븘�옄�룞李�</product>");
        sb.append("<product count=\"2\" name=\"�듃�쐞移�\">瑜대끂�옄�룞李�</product> ");
        sb.append("</products>");
		
        return sb.toString();
	}
	
	@RequestMapping(value = "/test03", produces="text/xml; charset=utf-8")
	@ResponseBody
	public String Xml_Exam02() {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			// �쎒 �긽�쓽 議댁옱�븯�뒗 xml瑜� 媛��졇�삤湲� 
			URL url = new URL("https://www.kma.go.kr/XML/weather/sfc_web_map.xml");
			URLConnection conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String msg = null;
			while((msg=br.readLine()) != null) {
				sb.append(msg);
			}
			
			return sb.toString();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	@RequestMapping(value = "/test04", produces="text/xml; charset=utf-8")
	@ResponseBody
	public String Xml_Exam03() {
		
		 BufferedReader rd = null;
		 HttpURLConnection conn = null;
		try {
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=7Jn37duPnqJP2hXtNvhcywuZlcu2XWgEJYHRSJIIwWps7J94qVJ8gOWdJOJSqoQ9rH2YQCMaCFMtlFsxFPAv8A==");   /*�옄湲곗쓽 �꽌鍮꾩뒪�궎 = Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*xml �삉�뒗 json*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));   /*�븳 �럹�씠吏� 寃곌낵 �닔*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));       /*�럹�씠吏�踰덊샇*/
	        urlBuilder.append("&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode("�꽌�슱", "UTF-8"));   /*�떆�룄 �씠由�(�쟾援�, �꽌�슱, 遺��궛, ��援�, �씤泥�, 愿묒＜, ���쟾, �슱�궛, 寃쎄린, 媛뺤썝, 異⑸턿, 異⑸궓, �쟾遺�, �쟾�궓, 寃쎈턿, 寃쎈궓, �젣二�, �꽭醫�)*/
	        urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" + URLEncoder.encode("1.0", "UTF-8"));        /*踰꾩쟾蹂� �긽�꽭 寃곌낵 李멸퀬*/
	        
	        URL url = new URL(urlBuilder.toString());
	        conn = (HttpURLConnection) url.openConnection();
	        
	        conn.setRequestMethod("GET");
	      
	        System.out.println("Response code: " + conn.getResponseCode()); // 200 OK
	        
	       
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	       
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	       
	        return sb.toString();
	        
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			try {
				rd.close();
			    conn.disconnect();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}
	
	
	@RequestMapping(value = "/test05", produces="application/json; charset=utf-8")
	@ResponseBody
	public String Json_Exam01() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(" {\"name\" : \"HTML\", \"scope\" : \"15\"},");  	
		sb.append(" {\"name\" : \"CSS\", \"scope\" : \"13\"},");  
		sb.append(" {\"name\" : \"JavaScript\", \"scope\" : \"17\"},");  
		sb.append(" {\"name\" : \"JSP\", \"scope\" : \"12\"}	");  
		sb.append("]");
		return sb.toString();
	}
	
	@RequestMapping(value = "/test06", produces="application/json; charset=utf-8")
	@ResponseBody
	public String Json_Exam06() {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			// �쎒 �긽�쓽 議댁옱�븯�뒗 xml瑜� 媛��졇�삤湲� 
			URL url = new URL("http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline");
			URLConnection conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String msg = null;
			while((msg=br.readLine()) != null) {
				sb.append(msg);
			}
			
			return sb.toString();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	@RequestMapping(value = "/test07", produces="application/json; charset=utf-8")
	@ResponseBody
	public String Json_Exam07() {
		// 湲곗긽泥�_以묎린�삁蹂� 議고쉶�꽌鍮꾩뒪 / 以묎린湲곗삩議고쉶
		 BufferedReader rd = null;
		 HttpURLConnection conn = null;
		try {
			 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa"); /*URL*/
		     urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=7Jn37duPnqJP2hXtNvhcywuZlcu2XWgEJYHRSJIIwWps7J94qVJ8gOWdJOJSqoQ9rH2YQCMaCFMtlFsxFPAv8A=="); /*Service Key*/
		     urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*�럹�씠吏�踰덊샇*/
		     urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*�븳 �럹�씠吏� 寃곌낵 �닔*/
		     urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*�슂泥��옄猷뚰삎�떇(XML/JSON)Default: XML*/
		     urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode("11B10101", "UTF-8")); /*11B10101 �꽌�슱, 11B20201 �씤泥� �벑 ( 蹂꾩꺼�뿊���옄猷� 李멸퀬)*/
		     urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode("202410170600", "UTF-8")); /*-�씪 2�쉶(06:00,18:00)�쉶 �깮�꽦 �릺硫� 諛쒗몴�떆媛곸쓣 �엯�젰- YYYYMMDD0600(1800) 理쒓렐 24�떆媛� �옄猷뚮쭔 �젣怨�*/
			
			
	        URL url = new URL(urlBuilder.toString());
	       
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");


	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        
	        return sb.toString();
		} catch (Exception e) {
			System.out.println(e);
			return null;			
		} finally {
			try {
				rd.close();
		        conn.disconnect();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}
}







