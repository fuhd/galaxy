package com.starriverdata.common.mapper;

import com.starriverdata.common.entity.HeraHostRelation;
import com.starriverdata.common.mybatis.HeraInsertLangDriver;
import com.starriverdata.common.mybatis.HeraSelectLangDriver;
import com.starriverdata.common.mybatis.HeraUpdateLangDriver;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HeraHostRelationMapper {

    @Insert("insert into hera_host_relation (#{heraHostRelation})")
    @Lang(HeraInsertLangDriver.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(HeraHostRelation heraHostRelation);

    @Update("delete from  hera_host_relation where id = #{id}")
    int delete(@Param("id") int id);

    @Update("update hera_host_relation (#{heraHostRelation}) where id = #{id}")
    @Lang(HeraUpdateLangDriver.class)
    int update(HeraHostRelation heraHostRelation);

    @Select("select * from hera_host_relation")
    @Lang(HeraSelectLangDriver.class)
    List<HeraHostRelation> getAll();

    @Select("select * from hera_host_relation where id = #{id}")
    @Lang(HeraSelectLangDriver.class)
    HeraHostRelation findById(HeraHostRelation heraHostRelation);

    @Select("select host from hera_host_relation where host_group_id = #{groupId}")
    @Lang(HeraSelectLangDriver.class)
    List<String> findPreemptionGroup(@Param("groupId") int groupId);

}
