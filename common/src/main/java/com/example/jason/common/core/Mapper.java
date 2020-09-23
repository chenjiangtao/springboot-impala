package com.example.jason.common.core;

import tk.mybatis.mapper.additional.aggregation.AggregationMapper;
import tk.mybatis.mapper.common.*;

/**
 * @author Jason
 * @Description mybatis通用Mapper
 * @date 2020-9-21 17:35:22
 */
public interface Mapper<T> extends
        BaseMapper<T>,
        MySqlMapper<T>,
        AggregationMapper<T>,
        ConditionMapper<T>,
        ExampleMapper<T>{
}
