package com.wlrllr.service.impl;

import com.wlrllr.mapper.BaseMapper;
import com.wlrllr.service.BaseService;

import java.util.List;

/**
 * Created by w_zhanglong on 2017/10/24.
 */
public abstract class BaseServiceImpl<T,Example,PK> implements BaseService<T,Example,PK>{

    public abstract BaseMapper<T,Example,PK> getMapper();

    @Override
    public int countByExample(Example example) {
        return getMapper().countByExample(example);
    }

    @Override
    public int deleteByExample(Example example) {
        return getMapper().deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(PK id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        return getMapper().insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return getMapper().insertSelective(record);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return getMapper().selectByExample(example);
    }

    @Override
    public T selectByPrimaryKey(PK id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(T record, Example example) {
        return getMapper().updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(T record, Example example) {
        return getMapper().updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return getMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return getMapper().updateByPrimaryKey(record);
    }
}
