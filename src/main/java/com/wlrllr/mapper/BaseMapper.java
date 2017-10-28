package com.wlrllr.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public interface BaseMapper<T,Example,PK> {
    int countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(PK id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(Example example);

    T selectByPrimaryKey(PK id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") Example example);

    int updateByExample(@Param("record") T record, @Param("example") Example example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
