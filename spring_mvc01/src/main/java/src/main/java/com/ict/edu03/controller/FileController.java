package src.main.java.com.ict.edu03.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu03.vo.FileVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class FileController {

	@GetMapping("/f_up1")
	public ModelAndView fileGetUp() {
		return new ModelAndView("day03/error");
	}
	
	// cos �씪�씠釉뚮윭由� �궗�슜
	@PostMapping("/f_up1")
	public ModelAndView fileUp(HttpServletRequest request, HttpServletResponse response) {
		try {
			ModelAndView mv = new ModelAndView("day03/result");
			// �떎�쟾�뿉�꽌�뒗 �봽濡쒖젥�듃媛� �븘�땶 �떎瑜� 怨녹뿉 ���옣 �븷 �닔�룄 �엳�떎. (AWS�쓽 S3�굹 �꽌踰꾩쓽 �듅�젙�쐞移�)
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			
			
			// cos �씪�씠釉뚮윭由щ�� �궗�슜�븯硫� request ���떊�뿉 MultipartRequest瑜� �궗�슜�븳�떎.
			MultipartRequest mr =
					new MultipartRequest(
							request,         // request ���떊�븯湲� �쐞�빐�꽌 request瑜� 諛쏅뒗�떎.
							path,            // ���옣�쐞移�     
							// 500MB (1024(KB)*1024 (MB) *1024 (GB)*1024 (TB) )
							500*1024*1024,   // �븳踰덉뿉 �뾽濡쒕뱶�븷 �닔 �엳�뒗 理쒕��겕湲�    
							"utf-8",         // �븳湲�泥섎━
							new DefaultFileRenamePolicy()); // 媛숈��씠由꾩쓽 �뙆�씪�씠 �엳�쓣�븣  �씠由꾨뮘�뿉 �닽�옄瑜� 遺숈뿬 �뾽濡쒕뱶�븳�떎.
		
			// String name = request.getParameter("name");
			// System.out.println(name); // null �굹�샂
			
			 String name = mr.getParameter("name");
			 String f_name = mr.getFilesystemName("f_name"); // ���옣 �떦�떆 �씠由�
			 String file_type = mr.getContentType("f_name");
			 
			 // �빐�떦 �뙆�씪 �젙蹂댁텛異�
			 File file = mr.getFile("f_name");
			 
			 // long size = file.length();  // �뙆�씪 �겕湲곕�� byte濡� �굹���깂
			 long size = file.length( ) / 1024;  // �뙆�씪 �겕湲곕�� KB濡� �굹���깂
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss E");
			 String lastday = sdf.format(file.lastModified());
			 
			 mv.addObject("name", name);
			 mv.addObject("f_name", f_name);
			 mv.addObject("file_type", file_type);
			 mv.addObject("size", size);
			 mv.addObject("lastday", lastday);
			return mv;
			
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("day03/error");
		}
	}
	
	@GetMapping("/down")
	public void FileDown(HttpServletRequest request, HttpServletResponse response) {
		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			String f_name = request.getParameter("f_name");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload/"+f_name);
		
			String r_path = URLEncoder.encode(f_name, "utf-8");
		
			// 釉뚮씪�슦�� �꽕�젙
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + r_path);
			
			// �떎�젣 �떎�슫濡쒕뱶�븯湲� 
			File file = new File(new String(path.getBytes(),"utf-8"));
			int b;
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(response.getOutputStream());
			
			while((b=bis.read()) != -1) {
				bos.write(b);
				bos.flush();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				bos.close();
				bis.close();
				fis.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}
	
	@GetMapping("/f_up2")
	public ModelAndView file2GetUp() {
		return new ModelAndView("day03/error");
	}
	
	@PostMapping("/f_up2")
	public ModelAndView fileUp2(
			@RequestParam("f_name") MultipartFile f_name,
			HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("day03/result");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			
			// �뙆�씪�뾽濡쒕뱶�븷�븣�쓽 �씠由꾨쭔 議댁옱�븳�떎.
			// 利� 媛숈� �씠由꾩쓽 �뙆�씪�쓣 �뾽濡쒕뱶�븯硫� �뾽濡쒕뱶媛� �릺吏� �븡�뒗�떎.(�떒�젏)
			// 蹂댄넻 �빐寃� 梨낆� �뙆�씪�씠由� �뮘�뿉 id�� �뾽濡쒕뱶 �궇吏쒕�� 遺숈씤�떎.
			// �븘�땲硫� UUID瑜� �깮�꽦�빐�꽌 遺숈씤�떎.
			// (踰붿슜 怨좎쑀 �떇蹂꾩옄瑜� �쓽誘명븯硫� 以묐났�씠 �릺吏� �븡�뒗 �쑀�씪�븳 媛믪쓣 援ъ꽦�븯怨좎옄 �븷�븣 二쇰줈 �궗�슜)
			UUID uuid = UUID.randomUUID();
			String fname = uuid.toString()+"_"+f_name.getOriginalFilename();
			
			String age = request.getParameter("age");
		    String file_type = f_name.getContentType();
		    long size = f_name.getSize()/1024 ;
		    
		    // �떎�젣 �삱由щ뒗 �옉�뾽�쓣 �븯�옄 
		    // FileCopyUtils.copy(byte[] in, File out)) : byte���엯諛곗뿴�쓣 吏��젙�븳 File�뿉 蹂듭궗�븳�떎.  
		    // �삱由� �뙆�씪�쓣 byte[]濡� 留뚮벉
		    byte[] in = f_name.getBytes();
		    
		    // �뾽濡쒕뱶�븷 �옣�냼�� ���옣�씠由�
		    File out = new File(path, fname);
		    
		    // �뙆�씪蹂듭궗 (upload = down)
		    FileCopyUtils.copy(in, out);
		
		    String name = request.getParameter("name");
		    
		    long lastModified = out.lastModified();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss E");
			String lastday = sdf.format(lastModified);
			 
			 mv.addObject("name", name);
			 mv.addObject("f_name", fname);
			 mv.addObject("file_type", file_type);
			 mv.addObject("size", size);
			 mv.addObject("lastday", lastday);
		    
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("day03/error");
		}
		
	}
	
	@GetMapping("/down2")
	public void fileDown2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String f_name = request.getParameter("f_name");
			String path = 
				request.getSession().getServletContext().getRealPath("/resources/upload/"+f_name);
			
			String r_path = URLEncoder.encode(f_name, "utf-8");
			// 釉뚮씪�슦�� �꽕�젙
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="+ r_path);
			
			// byte[] in 
			File file = new File(new String(path.getBytes(),"utf-8"));
			FileInputStream in = new FileInputStream(file);
			
			// File out
			OutputStream out = response.getOutputStream();
			
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@GetMapping("/f_up3")
	public ModelAndView file3GetUp() {
		return new ModelAndView("day03/error");
	}
	
	@PostMapping("/f_up3")
	public ModelAndView fileUp3(
			FileVO fvo,
			HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("day03/result");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			
			UUID uuid = UUID.randomUUID();
			
			MultipartFile f_name = fvo.getF_name();
			String fname = uuid.toString()+"_"+f_name.getOriginalFilename();
			
			String age = request.getParameter("age");
		    String file_type = f_name.getContentType();
		    long size = f_name.getSize()/1024 ;
		    
		    byte[] in = f_name.getBytes();
		    
		    // �뾽濡쒕뱶�븷 �옣�냼�� ���옣�씠由�
		    File out = new File(path, fname);
		    
		    // �뙆�씪蹂듭궗 (upload = down)
		    FileCopyUtils.copy(in, out);
		
		    String name = fvo.getName();
		     
		    long lastModified = out.lastModified();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss E");
			String lastday = sdf.format(lastModified);
			 
			 mv.addObject("name", name);
			 mv.addObject("f_name", fname);
			 mv.addObject("file_type", file_type);
			 mv.addObject("size", size);
			 mv.addObject("lastday", lastday);
		    
			return mv;
		} catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("day03/error");
		}
		
	}
	
}















