package com.starriverdata.common.mapper;

import com.starriverdata.common.entity.HeraAction;
import com.starriverdata.common.entity.vo.HeraActionVo;
import com.starriverdata.common.mybatis.HeraInsertLangDriver;
import com.starriverdata.common.mybatis.HeraListInLangDriver;
import com.starriverdata.common.mybatis.HeraSelectLangDriver;
import com.starriverdata.common.mybatis.HeraUpdateLangDriver;
import com.starriverdata.common.mybatis.action.HeraActionBatchInsertDriver;
import com.starriverdata.common.mybatis.action.HeraActionBatchUpdateDriver;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 版本运行历史查询
 */
public interface HeraJobActionMapper {

    @Insert("insert into hera_action (#{heraAction})")
    @Lang(HeraInsertLangDriver.class)
    int insert(HeraAction heraAction1);

    @Insert("insert into hera_action (#{list})")
    @Lang(HeraActionBatchInsertDriver.class)
    int batchInsert(@Param("list") List<HeraAction> list);

    @Insert("update hera_action (#{list})")
    @Lang(HeraActionBatchUpdateDriver.class)
    int batchUpdate(@Param("list") List<HeraAction> list);

    @Delete("delete from hera_action where id = #{id}")
    int delete(@Param("id") Long id);

    @Update("update hera_action (#{heraJobHistory}) where id = #{id}")
    @Lang(HeraUpdateLangDriver.class)
    int update(HeraAction heraJobHistory);

    @Select("select * from hera_action")
    List<HeraAction> getAll();

    @Select("select * from hera_action where id = #{id}")
    @Lang(HeraSelectLangDriver.class)
    HeraAction findById(Long id);

    @Select("select * from hera_action where job_id = #{jobId} order by id desc limit 1")
    HeraAction findLatestByJobId(Long jobId);

    @Select("select * from hera_action where job_id = #{jobId} order by id")
    List<HeraAction> findByJobId(Long jobId);

    @Update("update hera_action set status = #{status} where id = #{id}")
    Integer updateStatus(@Param("id") Long id,
                         @Param("status") String status);

    @Update("update hera_action set status = #{status},ready_dependency=#{readyDependency} where id = #{id}")
    Integer updateStatusAndReadDependency(HeraAction heraAction);

    @Select("select * from hera_action where id >= #{action}")
    List<HeraAction> selectAfterAction(long action);

    /**
     * 根据JobId 获取版本
     */
    @Select("select id from hera_action where job_id = #{jobId} order by id desc limit 24")
    List<Long> getActionVersionByJobId(Long jobId);

    @Select("select id,job_id,owner,auto from hera_action where id <= CURRENT_TIMESTAMP()* 10000 and id >= CURRENT_DATE () * 10000000000 and schedule_type = 0 and auto = 1 and status != 'success' group by job_id")
    List<HeraActionVo> getNotRunScheduleJob();

    @Select("select id,job_id,owner,auto from hera_action where id <= CURRENT_TIMESTAMP()* 10000 and id >= CURRENT_DATE () * 10000000000  and status = 'failed' and auto = 1 group by job_id")
    List<HeraActionVo> getFailedJob();

    /**
     * selectList 只能传递一个参数  需要封装为map或者对象
     */
    @Select("select id,job_id,status,ready_dependency,dependencies,schedule_type,last_result,name from hera_action where job_id in (#{list}) and id &gt;= #{startDate} * 10000000000 and id &lt;= #{endDate} * 10000000000 " +
            "<if test=\"status != null\" > and status=#{status} </if> " +
            " limit #{page},#{limit}")
    @Lang(HeraListInLangDriver.class)
    List<HeraAction> findByJobIdsAndPage(Map<String, Object> params);

    @Select("select count(1) from hera_action where job_id in (#{list}) and id &gt;= #{startDate} * 10000000000 and id &lt;= #{endDate} * 10000000000 " +
            "<if test=\"status != null\" > and status=#{status} </if> ")
    @Lang(HeraListInLangDriver.class)
    Integer findByJobIdsCount(Map<String, Object> params);

    @Delete("delete from hera_action where id < DATE_SUB(CURRENT_DATE(),INTERVAL #{beforeDay} DAY) * 10000000000;")
    Integer deleteHistoryRecord(Integer beforeDay);

    @Select("select * from hera_action where job_id = #{jobId} and id > #{startAction} and id <= #{endAction} limit #{limit}")
    List<HeraAction> selectByStartAndEnd(@Param("startAction") Long startAction,
                                         @Param("endAction") Long endAction,
                                         @Param("jobId") Integer jobId,
                                         @Param("limit") Integer limit);


    @Delete("delete from hera_action where job_id = #{jobId} and id >= #{startAction} and id <=#{endAction} ")
    Integer deleteAction(@Param("startAction") long startAction,
                         @Param("endAction") long endAction,
                         @Param("jobId") Integer jobId);

    @Select("select * from hera_action where job_id = #{jobId} and status = 'success' and id >= CURRENT_DATE () * 10000000000 limit 1")
    HeraAction selectTodaySuccessByJobId(int jobId);
}
