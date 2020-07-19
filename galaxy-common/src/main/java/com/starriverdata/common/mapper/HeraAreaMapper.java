package com.starriverdata.common.mapper;

import com.starriverdata.common.entity.HeraArea;
import com.starriverdata.common.mybatis.HeraInsertLangDriver;
import com.starriverdata.common.mybatis.HeraListInLangDriver;
import com.starriverdata.common.mybatis.HeraSelectLangDriver;
import com.starriverdata.common.mybatis.HeraUpdateLangDriver;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HeraAreaMapper {

    @Insert("insert into hera_area (#{heraArea})")
    @Lang(HeraInsertLangDriver.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insert(HeraArea heraArea);

    @Select("select * from hera_area")
    @Lang(HeraSelectLangDriver.class)
    List<HeraArea> selectAll();

    @Update("update hera_area (#{heraArea}) where id = #{id}")
    @Lang(HeraUpdateLangDriver.class)
    int updateById(HeraArea heraArea);

    @Select("select * from hera_area where id = #{id}")
    @Lang(HeraSelectLangDriver.class)
    HeraArea selectById(Integer id);

    @Select("select * from hera_area where id in (#{list})")
    @Lang(HeraListInLangDriver.class)
    List<HeraArea> selectByIdList(@Param("list") List<Integer> list);

    @Select("delete from hera_area where id = #{id}")
    Integer deleteById(Integer id);
}
