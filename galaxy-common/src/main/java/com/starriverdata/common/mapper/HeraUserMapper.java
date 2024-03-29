package com.starriverdata.common.mapper;

import com.starriverdata.common.entity.HeraUser;
import com.starriverdata.common.mybatis.HeraInsertLangDriver;
import com.starriverdata.common.mybatis.HeraListInLangDriver;
import com.starriverdata.common.mybatis.HeraSelectLangDriver;
import com.starriverdata.common.mybatis.HeraUpdateLangDriver;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HeraUserMapper {

    @Insert("insert into hera_user (#{heraUser})")
    @Lang(HeraInsertLangDriver.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(HeraUser heraUser);

    @Delete("delete from hera_user where id = #{id}")
    int delete(@Param("id") Integer id);

    @Update("update hera_user (#{heraUser}) where id = #{id}")
    @Lang(HeraUpdateLangDriver.class)
    int update(HeraUser heraUser);

    @Select("select id,email,name,phone,is_effective,description,gmt_create,gmt_modified from hera_user")
    @Lang(HeraSelectLangDriver.class)
    List<HeraUser> getAll();

    @Select("select name from hera_user")
    @Lang(HeraSelectLangDriver.class)
    List<HeraUser> getAllName();

    @Select("select * from hera_user where id = #{id}")
    @Lang(HeraSelectLangDriver.class)
    HeraUser findById(Integer id);

    @Select("SELECT * FROM hera_user WHERE NAME = #{name} limit 1")
    @Lang(HeraUpdateLangDriver.class)
    HeraUser getByName(HeraUser heraUser);

    @Select("select * from hera_user where id in (#{list})")
    @Lang(HeraListInLangDriver.class)
    List<HeraUser> findByIds(@Param("list") List<Integer> list);

    @Update("update hera_user set is_effective = #{isEffective} where id = #{id}")
    int updateEffective(@Param("id") Integer id, @Param("isEffective") String effective);

    @Select("select id,name from hera_user where is_effective = 1")
    List<HeraUser> selectGroups();

}
