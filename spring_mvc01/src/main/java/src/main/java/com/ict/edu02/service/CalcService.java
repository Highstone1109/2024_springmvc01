package src.main.java.com.ict.edu02.service;

import org.springframework.stereotype.Service;

import com.ict.edu02.vo.CalcVO;

// �꽌鍮꾩뒪 媛앹껜 �깮�꽦�븯�뒗 �뼱�끂�뀒�씠�뀡
@Service
public class CalcService {
	
	public CalcService() {
		System.out.println("CalcService �깮�꽦�옄");
	}
	
	// �젙蹂대�� 諛쏆븘�꽌 怨꾩궛�븯�뒗 硫붿꽌�뱶瑜� 留뚮뱺�떎.
	public int getCalc(CalcVO cvo) {
		int result = 0 ;
		int su1 = Integer.parseInt(cvo.getS1());
		int su2 = Integer.parseInt(cvo.getS2());
		
		switch (cvo.getOp()) {
			case "+": result = su1 + su2 ; break;
			case "-": result = su1 + su2 ; break;
			case "*": result = su1 + su2 ; break;
			case "/": result = su1 + su2 ; break;
		}
		return result;
	}
}
