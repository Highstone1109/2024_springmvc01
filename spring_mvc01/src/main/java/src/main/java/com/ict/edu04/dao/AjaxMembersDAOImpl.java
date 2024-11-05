package src.main.java.com.ict.edu04.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.edu04.vo.MembersVO;

@Repository
public class AjaxMembersDAOImpl implements AjaxMembersDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<MembersVO> getMemberList() {
		return sqlSessionTemplate.selectList("ajaxmembers.getMembersList");
	}

	@Override
	public MembersVO getMemberDetail(String m_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMemberInsert(MembersVO mvo) {
		// �옄�룞 而ㅻ컠�씠�떎.
		int result = sqlSessionTemplate.insert("ajaxmembers.getMemberInsert", mvo);
		return String.valueOf(result);
	}

	@Override
	public String getMemberDelete(String m_idx) {
		// �옄�룞 而ㅻ컠�씠�떎.
		int result = sqlSessionTemplate.delete("ajaxmembers.getMemberDelete", m_idx);
		return String.valueOf(result);
	}

	@Override
	public String getMemberUpdate(MembersVO mvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMemberIdChk(String m_id) {
		// �엳�쑝硫� 1, �뾾�쑝硫� 0
		int result = sqlSessionTemplate.selectOne("ajaxmembers.getMemberIdChk", m_id);
		return String.valueOf(result);
		
	}

}
