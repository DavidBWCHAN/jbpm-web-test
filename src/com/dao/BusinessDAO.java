package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;

public class BusinessDAO {
	public JbpmContext createJbpmContect(){
		return JbpmConfiguration.getInstance().createJbpmContext();
	}
	
	public void saveOrUpdateOrDelete(String sql, Object values[]) throws Exception{
		JbpmContext jbpmContect = this.createJbpmContect();
		Connection conn = jbpmContect.getConnection();
		try {
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			if(values!=null){
				for(int i=0; i<values.length;i++){
					stmt.setObject(i+1, values[i]);
				}
			}
			stmt.execute();
			stmt.close();
		}finally{
			conn.close();
			jbpmContect.close();
		}
	}
	
	public List query(String sql, Object values[]) throws Exception{
		List result = new ArrayList();
		JbpmContext jbpmContect = this.createJbpmContect();
		Connection conn = jbpmContect.getConnection();
		try {
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			if(values!=null){
				for(int i=0; i<values.length;i++){
					stmt.setObject(i+1, values[i]);
				}
			}
			ResultSetMetaData rmd=(ResultSetMetaData) stmt.getMetaData();
			int columnCount = rmd.getColumnCount();
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				Map map = new HashMap();
				for(int i=0;i<columnCount;i++){
					String columnName=rmd.getColumnName(i+1);
					map.put(columnName, rs.getObject(columnName));
				}
				result.add(map);
			}
			rs.close();
			stmt.close();
		}finally{
			conn.close();
			jbpmContect.close();
		}
		return result;
	}

}
