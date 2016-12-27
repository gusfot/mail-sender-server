package org.ohjic.mem.dao;

import java.util.List;

import org.ohjic.mem.model.NextYearSettingStatus;
import org.ohjic.mem.vo.NextYearSettingStatusVo;
import org.ohjic.mem.vo.YearVo;

public interface NextYearSettingStatusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table next_year_setting_status
     *
     * @mbg.generated Mon Dec 26 14:30:37 KST 2016
     */
    int deleteByPrimaryKey(Integer nextYearSettingIdx);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table next_year_setting_status
     *
     * @mbg.generated Mon Dec 26 14:30:37 KST 2016
     */
    int insert(NextYearSettingStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table next_year_setting_status
     *
     * @mbg.generated Mon Dec 26 14:30:37 KST 2016
     */
    int insertSelective(NextYearSettingStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table next_year_setting_status
     *
     * @mbg.generated Mon Dec 26 14:30:37 KST 2016
     */
    NextYearSettingStatus selectByPrimaryKey(Integer nextYearSettingIdx);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table next_year_setting_status
     *
     * @mbg.generated Mon Dec 26 14:30:37 KST 2016
     */
    int updateByPrimaryKeySelective(NextYearSettingStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table next_year_setting_status
     *
     * @mbg.generated Mon Dec 26 14:30:37 KST 2016
     */
    int updateByPrimaryKey(NextYearSettingStatus record);

	int insertNextYearSettingStatus(NextYearSettingStatus record);

	List<NextYearSettingStatusVo> selectNextYearSettingStatusByChurchCode(YearVo yearVo);
}