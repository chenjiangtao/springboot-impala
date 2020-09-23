package com.example.jason.log.dao;

import com.example.jason.common.core.Mapper;
import com.example.jason.log.domain.CrashLog;

public interface CrashLogMapper extends Mapper<CrashLog> {

    void scheduleRefreshMetadata();

    void scheduleRefreshPartitions();
}
