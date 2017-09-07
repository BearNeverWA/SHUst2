package com.shu.shust2.model;

import java.util.List;

/**
 * Created by Leo on 2017/9/8.
 */

public class activitysearch {

    /**
     * errorCode : 0
     * errorStr : ok
     * resultCount : 1
     * results : {"activity":[{"id":10001,"name":"活动1","status":"已修改","head_picture":"http://association-1252289837.image.myqcloud.com/images/16223e369cf67cc19d60604ff9ec05c1","type":"讲座","word_introduction":"活动介绍详细1","location":"伟长楼","start_time":"2017-09-06 12:00:00","end_time":"2017-09-09 12:30:00"},{"id":10002,"name":"活动2","status":"未开始","head_picture":"http://association-1252289837.image.myqcloud.com/images/fd4e5aee826c749475c34f250bfc1f27","type":"公益","word_introduction":"活动介绍详细2","location":"体育馆","start_time":"2017-09-06 12:00:00","end_time":"2017-09-06 12:30:00"},{"id":10003,"name":"活动3","status":"未开始","head_picture":"http://association-1252289837.image.myqcloud.com/images/fd4e5aee826c749475c34f250bfc1f27","type":"日常","word_introduction":"活动详细介绍3","location":"A4002","start_time":"2017-09-06 12:00:00","end_time":"2017-09-06 12:30:00"},{"id":10004,"name":"活动4","status":"进行中","head_picture":"http://association-1252289837.image.myqcloud.com/images/fd4e5aee826c749475c34f250bfc1f27","type":"讲座","word_introduction":"活动介绍介绍4","location":"A4003","start_time":"2017-09-06 12:00:00","end_time":"2017-09-06 12:30:00"},{"id":10005,"name":"活动5","status":"进行中","head_picture":"http://association-1252289837.image.myqcloud.com/images/fd4e5aee826c749475c34f250bfc1f27","type":"培训","word_introduction":"详细详细5","location":"其他：上大校区","start_time":"2017-09-06 12:00:00","end_time":"2017-09-06 12:30:00"},{"id":10006,"name":"测试admin","status":"审核中","head_picture":"http://association-1252289837.image.myqcloud.com/images/c1a02070f15f148abfbaf24bbcf3d635","type":"无类别","word_introduction":"这是一句话介绍","location":"A4001","start_time":"2015-10-10 10:00:00","end_time":"2019-10-10 10:00:00"}]}
     * endMark : 1
     * queries : [{"query":"select count(*) as aggregate from `activity`","bindings":[],"time":1.17},{"query":"select `id`, `name`, `status`, `head_picture`, `type`, `word_introduction`, `location`, `location_value`, `start_time`, `end_time` from `activity` limit 15 offset 0","bindings":[],"time":0.42},{"query":"select * from `config_activity_type` where `config_activity_type`.`id` in (?, ?, ?, ?, ?)","bindings":[0,1,2,3,4],"time":0.38},{"query":"select * from `config_location` where `config_location`.`id` in (?, ?, ?, ?, ?, ?)","bindings":[0,1,2,4,5,6],"time":0.32}]
     * timeCost : 0.00854
     * timeNow : 2017-09-08 03:13:27
     */

    private int errorCode;
    private String errorStr;
    private ResultsBean results;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorStr() {
        return errorStr;
    }

    public void setErrorStr(String errorStr) {
        this.errorStr = errorStr;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public static class ResultsBean {
        private java.util.List<ActivityBean> activity;

        public List<ActivityBean> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityBean> activity) {
            this.activity = activity;
        }

        public static class ActivityBean {
            /**
             * id : 10001
             * name : 活动1
             * status : 已修改
             * head_picture : http://association-1252289837.image.myqcloud.com/images/16223e369cf67cc19d60604ff9ec05c1
             * type : 讲座
             * word_introduction : 活动介绍详细1
             * location : 伟长楼
             * start_time : 2017-09-06 12:00:00
             * end_time : 2017-09-09 12:30:00
             */

            private int id;
            private String name;
            private String status;
            private String type;
            private String location;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }
        }
    }
}
