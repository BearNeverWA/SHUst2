package com.shu.shust2.model;

/**
 * Created by Leo on 2017/9/8.
 */

public class ActivityDetail {

    /**
     * errorCode : 0
     * errorStr : ok
     * resultCount : 14
     * results : {"id":10002,"name":"活动2","status":"未开始","head_picture":"http://association-1252289837.image.myqcloud.com/images/fd4e5aee826c749475c34f250bfc1f27","introduction":"介绍2","type":"公益","introduction":"活动介绍详细2","set_people":100,"is_top":0,"location":"体育馆","location_value":"","start_time":"2017-09-06 12:00:00","end_time":"2017-09-06 12:30:00","association":"SHULAX\u2014\u2014上大棍网球社"}
     * endMark : 0
     * queries : [{"query":"select `id`, `name`, `association_id`, `status`, `head_picture`, `introduction`, `type`, `introduction`, `apply_status`, `set_people`, `is_top`, `location`, `location_value`, `start_time`, `end_time`, `editor`, `gmt_create`, `gmt_modified` from `activity` where `id` = ? and `status` in (?, ?) limit 1","bindings":["10002",4,5],"time":1.5},{"query":"select * from `association` where `association`.`id` in (?)","bindings":[1002],"time":0.36},{"query":"select * from `config_activity_type` where `config_activity_type`.`id` in (?)","bindings":[1],"time":0.25},{"query":"select * from `config_location` where `config_location`.`id` in (?)","bindings":[1],"time":0.31}]
     * timeCost : 0.00816
     * timeNow : 2017-09-08 02:32:15
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
        /**
         * id : 10002
         * name : 活动2
         * status : 未开始
         * head_picture : http://association-1252289837.image.myqcloud.com/images/fd4e5aee826c749475c34f250bfc1f27
         * introduction : 介绍2
         * type : 公益
         * introduction : 活动介绍详细2
         * set_people : 100
         * is_top : 0
         * location : 体育馆
         * location_value :
         * start_time : 2017-09-06 12:00:00
         * end_time : 2017-09-06 12:30:00
         * association : SHULAX——上大棍网球社
         */

        private int id;
        private String name;
        private String status;
        private String type;
        private String introduction;
        private int set_people;
        private String location;
        private String start_time;
        private String end_time;
        private String association;

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

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public int getSet_people() {
            return set_people;
        }

        public void setSet_people(int set_people) {
            this.set_people = set_people;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getAssociation() {
            return association;
        }

        public void setAssociation(String association) {
            this.association = association;
        }
    }
}
