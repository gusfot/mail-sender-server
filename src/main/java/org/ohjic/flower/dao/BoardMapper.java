package org.ohjic.flower.dao;

import org.ohjic.flower.model.Board;

public interface BoardMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table board
	 * @mbggenerated  Tue Jul 26 11:22:01 KST 2016
	 */
	int deleteByPrimaryKey(Integer boardSeq);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table board
	 * @mbggenerated  Tue Jul 26 11:22:01 KST 2016
	 */
	int insert(Board record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table board
	 * @mbggenerated  Tue Jul 26 11:22:01 KST 2016
	 */
	int insertSelective(Board record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table board
	 * @mbggenerated  Tue Jul 26 11:22:01 KST 2016
	 */
	Board selectByPrimaryKey(Integer boardSeq);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table board
	 * @mbggenerated  Tue Jul 26 11:22:01 KST 2016
	 */
	int updateByPrimaryKeySelective(Board record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table board
	 * @mbggenerated  Tue Jul 26 11:22:01 KST 2016
	 */
	int updateByPrimaryKeyWithBLOBs(Board record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table board
	 * @mbggenerated  Tue Jul 26 11:22:01 KST 2016
	 */
	int updateByPrimaryKey(Board record);


}
