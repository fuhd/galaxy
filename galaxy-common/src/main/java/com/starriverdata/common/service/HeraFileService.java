package com.starriverdata.common.service;

import com.starriverdata.common.entity.HeraFile;
import com.starriverdata.common.entity.vo.HeraFileTreeNodeVo;

import java.util.List;

/**
 * 开发中心文件文件管理
 */
public interface HeraFileService {

    Integer insert(HeraFile heraFile);

    int delete(Integer id);

    int update(HeraFile heraFile);

    List<HeraFile> getAll();

    HeraFile findById(Integer id);

    List<HeraFile> findByIds(List<Integer> list);

    List<HeraFile> findByParent(Integer parent);

    List<HeraFile> findByOwner(String heraFile);

    List<HeraFileTreeNodeVo> buildFileTree(String user);

    int updateContent(HeraFile heraFile);

    int updateFileName(HeraFile heraFile);

    HeraFile findDocByOwner(String owner);

    boolean updateParentById(Integer id, Integer parent);
}
