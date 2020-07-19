package com.starriverdata.common.mapper;

import com.starriverdata.common.entity.HeraJobMonitor;
import com.starriverdata.common.entity.vo.HeraJobMonitorVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface HeraJobMonitorMapper {

    @Insert("insert into hera_job_monitor (job_id,user_ids) values(#{jobId}, #{userIds})")
    Integer insert(HeraJobMonitor monitor);

    @Update("update hera_job_monitor set user_ids = concat(user_ids,#{userIds}) where job_id = #{jobId}")
    Integer insertUser(HeraJobMonitor monitor);

    @Select("select * from hera_job_monitor where job_id = #{jobId} limit 1")
    HeraJobMonitor findByJobId(Integer jobId);

    @Select("select * from hera_job_monitor")
    List<HeraJobMonitor> selectAll();

    @Select("select m.job_id id,m.user_ids,job.name job_name,job.description from hera_job_monitor m left join hera_job job on m.job_id = job.id")
    List<HeraJobMonitorVo> selectAllVo();

    @Update("update hera_job_monitor set user_ids =#{userIds} where job_id = #{jobId} ")
    Integer update(@Param("jobId") Integer jobId,
                   @Param("userIds") String userIds);
}
