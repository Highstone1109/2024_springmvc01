package src.main.java.com.ict.edu05.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu05.vo.EmpVO;

@Repository
public class EmpDAOImpl implements EmpDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<EmpVO> getList() throws Exception {
		return sqlSessionTemplate.selectList("emp.list");
	}

	@Override
	public List<EmpVO> getSearch(String deptno) throws Exception {
		return sqlSessionTemplate.selectList("emp.search", deptno);
	}


	@Override
	public List<EmpVO> getSearch(EmpVO empvo) throws Exception {
		return sqlSessionTemplate.selectList("emp.dynamic", empvo);
	}

	@Override
	public List<EmpVO> getSearch(String idx, String keyword) throws Exception {
		// MyBatis�뿉�꽌 �뙆�씪誘명꽣 �옄由щ뒗 �뾾嫄곕굹 �븯�굹留� 議댁옱�븳�떎. 
		// 洹몃옒�꽌 �몢 媛� �씠�긽�씪 �븣�뒗 �쐞�뿉 泥섎읆 vo瑜� �궗�슜�븯�뒗 諛⑸쾿怨� Map瑜� �궗�슜�븯�뒗 諛⑸쾿 
		Map<String, String> map = new HashMap<String, String>();
		map.put("idx", idx);
		map.put("keyword", keyword);
		return sqlSessionTemplate.selectList("emp.dynamic2", map);
	}
}










