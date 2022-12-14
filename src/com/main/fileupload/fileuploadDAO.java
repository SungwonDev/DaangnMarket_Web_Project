package com.main.fileupload;
import com.main.fileidx.*;
import com.main.filecount.*; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.main.db.DBconn;
import com.main.fileupload.fileuploadDTO;

public class fileuploadDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql="";
	
	
	public fileuploadDTO insertFile(fileuploadDTO fileuploadDTO){
		conn = null;
		pstmt =null;
		
		try {
			sql="INSERT INTO tb_fileupload (fu_name, fu_category, fu_price, fu_comment, fu_filename1, fu_filename2, fu_filename3, fu_filename4, fu_filename5, fu_filepath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = DBconn.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, fileuploadDTO.getName());
			pstmt.setString(2, fileuploadDTO.getCategory());
			pstmt.setInt(3, fileuploadDTO.getPrice());
			pstmt.setString(4, fileuploadDTO.getComment());
			pstmt.setString(5, fileuploadDTO.getFilename1());
			pstmt.setString(6, fileuploadDTO.getFilename2());
			pstmt.setString(7, fileuploadDTO.getFilename3());
			pstmt.setString(8, fileuploadDTO.getFilename4());
			pstmt.setString(9, fileuploadDTO.getFilename5());
			pstmt.setString(10, fileuploadDTO.getFilepath());
			int result = pstmt.executeUpdate();

			if(result>=1) {
				return fileuploadDTO;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) {pstmt.close();}
				if(conn !=null) {conn.close();}
			}catch(SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}
	
	public List<fileuploadDTO> selectList(int start){
		List<fileuploadDTO> fileList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT * FROM tb_fileupload WHERE fu_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				fileuploadDTO fileuploadDTO = new fileuploadDTO();
				
				fileuploadDTO.setIdx(rs.getInt("fu_idx"));
				fileuploadDTO.setName(rs.getString("fu_name"));
				fileuploadDTO.setCategory(rs.getString("fu_category"));
				fileuploadDTO.setPrice(rs.getInt("fu_price"));
				fileuploadDTO.setComment(rs.getString("fu_comment"));
				fileuploadDTO.setFilename1(rs.getString("fu_filename1"));
				fileuploadDTO.setFilename2(rs.getString("fu_filename2"));
				fileuploadDTO.setFilename3(rs.getString("fu_filename3"));
				fileuploadDTO.setFilename4(rs.getString("fu_filename4"));
				fileuploadDTO.setFilename5(rs.getString("fu_filename5"));
				fileuploadDTO.setFilepath(rs.getString("fu_filepath"));
				fileList.add(fileuploadDTO);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fileList;
	}
	

	//????????? ???????????? ???????????? ?????? ???????????????????? ??????????????? ?????? ?????????
	public List<fileuploadDTO> selectListAll(){
		List<fileuploadDTO> fileList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT * FROM tb_fileupload WHERE fu_idx=?";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, start);
//			pstmt.setInt(2, pagePerCount);	
			rs = pstmt.executeQuery();
			while(rs.next()) {
				fileuploadDTO fileuploadDTO = new fileuploadDTO();
				
				fileuploadDTO.setIdx(rs.getInt("fu_idx"));
				fileuploadDTO.setName(rs.getString("fu_name"));
				fileuploadDTO.setCategory(rs.getString("fu_category"));
				fileuploadDTO.setPrice(rs.getInt("fu_price"));
				fileuploadDTO.setComment(rs.getString("fu_comment"));
				fileuploadDTO.setFilename1(rs.getString("fu_filename1"));
				fileuploadDTO.setFilename2(rs.getString("fu_filename2"));
				fileuploadDTO.setFilename3(rs.getString("fu_filename3"));
				fileuploadDTO.setFilename4(rs.getString("fu_filename4"));
				fileuploadDTO.setFilename5(rs.getString("fu_filename5"));
				fileuploadDTO.setFilepath(rs.getString("fu_filepath"));
				fileList.add(fileuploadDTO);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fileList;
	}
	
	//???????????????????????? ?????????????????? ????????????????????? ??????????????
	public List<fileuploadDTO> selectListAll(String keyWord){
		List<fileuploadDTO> fileList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			StringBuffer query = new StringBuffer();
		
			//???????????????????????? ??????????????? ????????? ?????????????????? ??????????????? ????????????????????????
			//?????? ????????????????????? ???????????????????????? ???????????? ??????????????? sql????????????????????? fu_comment???????????? ???????????????????????? ???????????? ????????? ???????????????
			if(keyWord!=null && !keyWord.equals("")) {
				query.append("SELECT * FROM tb_fileupload WHERE fu_name LIKE ? ORDER BY fu_idx DESC");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, "%"+keyWord+"%");
				rs = pstmt.executeQuery();

			}else{
				//?????????????????? ???????????? ????????? ??????????????????????????????
				query.append("SELECT * FROM tb_fileupload ORDER BY fu_idx DESC");
				pstmt = conn.prepareStatement(query.toString());

				rs = pstmt.executeQuery();
			}
		
			while(rs.next()) {
				fileuploadDTO fileuploadDTO = new fileuploadDTO();
				
				fileuploadDTO.setIdx(rs.getInt("fu_idx"));
				fileuploadDTO.setName(rs.getString("fu_name"));
				fileuploadDTO.setCategory(rs.getString("fu_category"));
				fileuploadDTO.setPrice(rs.getInt("fu_price"));
				fileuploadDTO.setComment(rs.getString("fu_comment"));
				fileuploadDTO.setFilename1(rs.getString("fu_filename1"));
				fileuploadDTO.setFilename2(rs.getString("fu_filename2"));
				fileuploadDTO.setFilename3(rs.getString("fu_filename3"));
				fileuploadDTO.setFilename4(rs.getString("fu_filename4"));
				fileuploadDTO.setFilename5(rs.getString("fu_filename5"));
				fileuploadDTO.setFilepath(rs.getString("fu_filepath"));
				fileList.add(fileuploadDTO);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fileList;
	}
	
	//???????????? + ???????????????????????? ??????????????????
	public List<fileuploadDTO> selectListAll(String S_location, String keyWord){
		List<fileuploadDTO> fileList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			StringBuffer query = new StringBuffer();
		
			if(keyWord!=null && !keyWord.equals("")) {
				query.append("SELECT * FROM tb_fileupload WHERE fu_name LIKE ? ORDER BY fu_idx DESC");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, "%"+keyWord+"%");
				rs = pstmt.executeQuery();

			}else{
				query.append("SELECT * FROM tb_fileupload ORDER BY fu_idx DESC");
				pstmt = conn.prepareStatement(query.toString());
				rs = pstmt.executeQuery();
			}
			
			
			while(rs.next()) {
				fileuploadDTO fileuploadDTO = new fileuploadDTO();
				
				fileuploadDTO.setIdx(rs.getInt("fu_idx"));
				fileuploadDTO.setName(rs.getString("fu_name"));
				fileuploadDTO.setCategory(rs.getString("fu_category"));
				fileuploadDTO.setPrice(rs.getInt("fu_price"));
				fileuploadDTO.setComment(rs.getString("fu_comment"));
				fileuploadDTO.setFilename1(rs.getString("fu_filename1"));
				fileuploadDTO.setFilename2(rs.getString("fu_filename2"));
				fileuploadDTO.setFilename3(rs.getString("fu_filename3"));
				fileuploadDTO.setFilename4(rs.getString("fu_filename4"));
				fileuploadDTO.setFilename5(rs.getString("fu_filename5"));
				fileuploadDTO.setFilepath(rs.getString("fu_filepath"));
				fileList.add(fileuploadDTO);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fileList;
	}
	
	
	//idx ??????????????
	//???????????? ???????????? ???????????? ???????????? ?????????
	public List<fileuploadidxDTO> selectListidx(){
		List<fileuploadidxDTO> fileidxList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT fu_idx FROM tb_fileupload";
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, pagePerCount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				fileuploadidxDTO fileuploadidxDTO = new fileuploadidxDTO();
				
				fileuploadidxDTO.setIdx(rs.getInt("fu_idx"));
				fileidxList.add(fileuploadidxDTO);
				
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fileidxList;
	}

	//???????????????
	//???????????????????????? ???????????? ???????????? ?????????
	public int countColumn(){
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT COUNT(*) FROM tb_fileupload";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if(rs.next()){
                count = rs.getInt(1);
            }
        }catch(Exception ex){
            System.out.println("getListCount ???????????? : " + ex);
        }finally{
            if(rs!=null)try{rs.close();}catch(SQLException ex){}
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
        }
       
        return count;
    }
	
	
	//????????????
	//comment?????? ???????????? ???????????????????????? ???????????? ?????????????????? sql ???????????????????????? ???????????????????? ???????????????????????? ??????.(????????? ????????????)
	public List<fileuploadDTO> searchtAllList(String keyWord){
		List<fileuploadDTO> fileList = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBconn.getConnection();
			String sql = "SELECT * FROM tb_fileupload";
			
			if(keyWord!=null && !keyWord.equals("")) {
				sql +="WHERE fu_name LIKE '%" +keyWord.trim()+ "%' OR fu_comment LIKE '%" +keyWord.trim()+ "%' ORDER BY fu_idx";

			}else {
				sql +="order by fu_idx";
			}
			
			pstmt = conn.prepareStatement(sql);	
			rs = pstmt.executeQuery();
			while(rs.next()) {
				fileuploadDTO fileuploadDTO = new fileuploadDTO();
				
				fileuploadDTO.setIdx(rs.getInt("fu_idx"));
				fileuploadDTO.setName(rs.getString("fu_name"));
				fileuploadDTO.setCategory(rs.getString("fu_category"));
				fileuploadDTO.setPrice(rs.getInt("fu_price"));
				fileuploadDTO.setComment(rs.getString("fu_comment"));
				fileuploadDTO.setFilename1(rs.getString("fu_File1"));
				fileuploadDTO.setFilename2(rs.getString("fu_File2"));
				fileuploadDTO.setFilename3(rs.getString("fu_File3"));
				fileuploadDTO.setFilename4(rs.getString("fu_File4"));
				fileuploadDTO.setFilename5(rs.getString("fu_File5"));
				fileuploadDTO.setFilepath(rs.getString("fu_filepath"));
				fileList.add(fileuploadDTO);
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return fileList;
	}
	
	

	//???????????? //id ???????????? ???????????????????????? ?????????
	//
	public int deleteList(String id){
		DBconn db = new DBconn();
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			conn = DBconn.getConnection();
			String sql = "delete from tb_fileupload where id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id.trim());//	
			rs = pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
		return rs;
	}
}