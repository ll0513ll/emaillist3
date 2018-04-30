package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.EmailVo;

@Repository
public class EmaillistDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(EmailVo vo) {
		
			return sqlSession.insert("emaillist.insert",vo);
			 
	}
	
	public List<EmailVo> getList(){
		
		return sqlSession.selectList("emaillist.list");
		
	}

}
