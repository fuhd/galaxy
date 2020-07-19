package com.starriverdata.common.mapper;

import com.starriverdata.common.entity.HeraFile;
import com.starriverdata.common.entity.Judge;
import com.starriverdata.common.mybatis.HeraInsertLangDriver;
import com.starriverdata.common.mybatis.HeraListInLangDriver;
import com.starriverdata.common.mybatis.HeraSelectLangDriver;
import com.starriverdata.common.mybatis.HeraUpdateLangDriver;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 开发中心文件管理
 */
public interface HeraFileMapper {

    @Insert("insert into hera_file (#{heraFile})")
    @Lang(HeraInsertLangDriver.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(HeraFile heraFile);

    @Delete("delete from hera_file where id = #{id}")
    int delete(@Param("id") Integer id);

    @Update("update hera_file (#{heraFile}) where id = #{id}")
    @Lang(HeraUpdateLangDriver.class)
    int update(HeraFile heraFile);

    @Select("select * from hera_file")
    @Lang(HeraSelectLangDriver.class)
    List<HeraFile> getAll();

    @Select("select * from hera_file where id = #{id}")
    @Lang(HeraSelectLangDriver.class)
    HeraFile findById(Integer id);

    @Select("select * from hera_file where id in (#{list})")
    @Lang(HeraListInLangDriver.class)
    List<HeraFile> findByIds(@Param("list") List<Integer> list);

    @Select("select * from hera_file where parent = #{parent}")
    @Lang(HeraSelectLangDriver.class)
    List<HeraFile> findByParent(Integer parent);

    @Select("select * from hera_file where owner = #{owner}")
    @Lang(HeraSelectLangDriver.class)
    List<HeraFile> findByOwner(String owner);

    @Update("update hera_file set content = #{content} where id = #{id}")
    int updateContent(HeraFile heraFile);

    @Update("update hera_file set name = #{name} where id = #{id}")
    int updateFileName(HeraFile heraFile);

    @Select("select count(*) count, max(id) maxId, max(gmt_modified) lastModified from hera_file")
    Judge selectTableInfo();

    @Select("select * from hera_file where owner = #{owner} and name='个人文档'")
    HeraFile findDocByOwner(String owner);

    @Update("update hera_file set parent = #{parent} where id = #{id} ")
    Integer updateParentById(@Param("id") Integer id, @Param("parent") Integer parent);
}
