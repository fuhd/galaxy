package com.starriverdata.common.service.impl;

import com.starriverdata.common.entity.HeraJob;
import com.starriverdata.common.entity.Judge;
import com.starriverdata.logs.GalaxyLog;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 内存级别缓存job信息
 */
@Service("heraJobMemoryService")
public class HeraJobMemoryServiceImpl extends HeraJobServiceImpl {

    private volatile Judge judge;

    private Map<Integer, HeraJob> memoryJob;

    private Map<Integer, HeraJob> getMemoryJob() {
        Judge newJudge = heraJobMapper.selectTableInfo();
        if (newJudge.getMaxId() == null || newJudge.getLastModified() == null || newJudge.getCount() == null) {
            return new HashMap<>(0);
        }
        if (judge == null || !newJudge.getCount().equals(judge.getCount()) || !newJudge.getLastModified().equals(judge.getLastModified()) || !newJudge.getMaxId().equals(judge.getMaxId())) {
            synchronized (this) {
                if (judge == null || !newJudge.getCount().equals(judge.getCount()) || !newJudge.getLastModified().equals(judge.getLastModified()) || !newJudge.getMaxId().equals(judge.getMaxId())) {
                    GalaxyLog.info("刷新hera_job库");
                    judge = newJudge;
                    List<HeraJob> all = heraJobMapper.getAll();
                    Map<Integer, HeraJob> jobMap = new HashMap<>(all.size());
                    all.forEach(job -> jobMap.put(job.getId(), job));
                    memoryJob = jobMap;
                }
            }
        }
        judge.setStamp(new Date());
        return memoryJob;
    }


    @Override
    public List<HeraJob> getAll() {
        return new ArrayList<>(getMemoryJob().values());
    }


    @Override
    public HeraJob findMemById(int id) {
        if (memoryJob == null) {
            memoryJob = getMemoryJob();
        }
        return memoryJob.get(id);
    }

    @Override
    public List<HeraJob> findByIds(List<Integer> list) {
        List<HeraJob> res = new ArrayList<>();
        Map<Integer, HeraJob> memoryJob = getMemoryJob();
        list.forEach(id -> res.add(memoryJob.get(id)));
        return res;
    }

    @Override
    public List<HeraJob> getAllJobDependencies() {
        return this.getAll();
    }
}
