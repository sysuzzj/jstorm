/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.jstorm.task;

import com.alibaba.jstorm.task.heartbeat.TaskHeartbeat;
import com.alibaba.jstorm.utils.TimeUtils;

/**
 * TkHbCacheTime is describle taskheartcache (Map<topologyId, Map<taskid,
 * Map<tkHbCacheTime, time>>>)
 */

public class TkHbCacheTime {
    private int nimbusTime;
    private int taskReportedTime;
    private int taskAssignedTime;

    public int getNimbusTime() {
        return nimbusTime;
    }

    public void setNimbusTime(int nimbusTime) {
        this.nimbusTime = nimbusTime;
    }

    public int getTaskReportedTime() {
        return taskReportedTime;
    }

    public void setTaskReportedTime(int taskReportedTime) {
        this.taskReportedTime = taskReportedTime;
    }

    public int getTaskAssignedTime() {
        return taskAssignedTime;
    }

    public void setTaskAssignedTime(int taskAssignedTime) {
        this.taskAssignedTime = taskAssignedTime;
    }

    public void update(TaskHeartbeat zkTaskHeartbeat) {
        int nowSecs = TimeUtils.current_time_secs();
        this.nimbusTime = nowSecs;
        this.taskReportedTime = zkTaskHeartbeat.getTimeSecs();
        this.taskAssignedTime =
                zkTaskHeartbeat.getTimeSecs() - zkTaskHeartbeat.getUptimeSecs();
    }

}
