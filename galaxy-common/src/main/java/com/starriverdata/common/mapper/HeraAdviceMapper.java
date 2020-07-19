package com.starriverdata.common.mapper;

import com.starriverdata.common.entity.HeraAdvice;
import com.starriverdata.common.mybatis.HeraInsertLangDriver;
import com.starriverdata.common.mybatis.HeraSelectLangDriver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HeraAdviceMapper {

    @Insert("insert into hera_advice (#{heraAdvice})")
    @Lang(HeraInsertLangDriver.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insert(HeraAdvice heraAdvice);

    @Select("select * from hera_advice")
    @Lang(HeraSelectLangDriver.class)
    List<HeraAdvice> getAll();
}
