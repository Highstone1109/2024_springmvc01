package src.main.java.com.ict.edu03.vo;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	// DB 而щ읆怨� 媛숈븘�빞 �븳�떎.(fname)
	private String name, fname;
	// �뙆�씪誘명꽣�� 媛숈� �씠由꾩쑝濡� 留뚮뱾�뼱�빞 �븳�떎.
	private MultipartFile f_name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public MultipartFile getF_name() {
		return f_name;
	}
	public void setF_name(MultipartFile f_name) {
		this.f_name = f_name;
	}
	
	
}
