package com.starriverdata.common.mapper;

import com.starriverdata.common.entity.HeraHostGroup;
import com.starriverdata.common.mybatis.HeraInsertLangDriver;
import com.starriverdata.common.mybatis.HeraSelectLangDriver;
import com.starriverdata.common.mybatis.HeraUpdateLangDriver;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HeraHostGroupMapper {

    @Insert("insert into hera_host_group (#{heraHostGroup})")
    @Lang(HeraInsertLangDriver.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(HeraHostGroup heraHostGroup);

    @Update("delete from  hera_host_group where id = #{id}")
    int delete(@Param("id") int id);

    @Update("update hera_host_group (#{heraHostGroup}) where id = #{id}")
    @Lang(HeraUpdateLangDriver.class)
    int update(HeraHostGroup heraHostGroup);

    @Select("select * from hera_host_group")
    @Lang(HeraSelectLangDriver.class)
    List<HeraHostGroup> getAll();

    @Select("select * from hera_host_group where id = #{id} and effective = 1")
    @Lang(HeraSelectLangDriver.class)
    HeraHostGroup findById(HeraHostGroup heraHostGroup);
}
