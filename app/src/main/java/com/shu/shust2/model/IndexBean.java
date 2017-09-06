package com.shu.shust2.model;

import java.util.List;

/**
 * Created by Leo on 2017/9/6.
 */

public class IndexBean {

    /**
     * errorCode : 0
     * errorStr : ok
     * resultCount : 3
     * results : {"banner":[],"association":[{"id":1125,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"数学建模社","type":"学术科技","star":5,"word_introduction":"数学建模社"},{"id":1006,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"白玉兰社区服务社","type":"公益实践","star":4,"word_introduction":"白玉兰社区服务社"},{"id":1097,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"MCs 主持人社","type":"文化艺术","star":5,"word_introduction":" MCs 主持人社"},{"id":1102,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"吉他协会","type":"文化艺术","star":5,"word_introduction":"吉他协会"}],"activity":[{"id":10002,"name":"活动2","status":"未开始","head_picture":"http://association-1252289837.image.myqcloud.com/images/fd4e5aee826c749475c34f250bfc1f27","type":"公益","word_introduction":"活动介绍详细2","location":"体育馆","start_time":"2017-09-06 12:00:00","end_time":"2017-09-06 12:30:00"},{"id":10004,"name":"活动4","status":"进行中","head_picture":"http://association-1252289837.image.myqcloud.com/images/fd4e5aee826c749475c34f250bfc1f27","type":"讲座","word_introduction":"活动介绍介绍4","location":"A4003","start_time":"2017-09-06 12:00:00","end_time":"2017-09-06 12:30:00"},{"id":10001,"name":"活动1","status":"未开始","head_picture":"http://association-1252289837.image.myqcloud.com/images/fd4e5aee826c749475c34f250bfc1f27","type":"讲座","word_introduction":"活动介绍详细1","location":"伟长楼","start_time":"2017-09-06 12:00:00","end_time":"2017-09-06 12:30:00"},{"id":10005,"name":"活动5","status":"进行中","head_picture":"http://association-1252289837.image.myqcloud.com/images/fd4e5aee826c749475c34f250bfc1f27","type":"培训","word_introduction":"详细详细5","location":"其他：上大校区","start_time":"2017-09-06 12:00:00","end_time":"2017-09-06 12:30:00"}]}
     * endMark : 0
     * queries : [{"query":"select `name`, `img` from `config_banner` order by `sort` asc","bindings":[],"time":1.27},{"query":"select `id`, `logo`, `name`, `nick_name`, `type`, `star`, `word_introduction` from `association` where `status` = ? order by RAND() limit 4","bindings":[2],"time":0.64},{"query":"select * from `config_association_type` where `config_association_type`.`id` in (?, ?, ?)","bindings":[1,5,6],"time":0.35},{"query":"select `id`, `name`, `status`, `head_picture`, `type`, `word_introduction`, `location`, `location_value`, `start_time`, `end_time` from `activity` where `status` in (?, ?) order by RAND() limit 4","bindings":[4,5],"time":0.37},{"query":"select * from `config_activity_type` where `config_activity_type`.`id` in (?, ?, ?)","bindings":[1,2,4],"time":0.4},{"query":"select * from `config_location` where `config_location`.`id` in (?, ?, ?, ?)","bindings":[0,1,2,6],"time":0.33}]
     * timeCost : 0.01148
     * timeNow : 2017-09-06 16:20:08
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
        private List<AssociationBean> association;
        private List<ActivityBean> activity;

        public List<AssociationBean> getAssociation() {
            return association;
        }

        public void setAssociation(List<AssociationBean> association) {
            this.association = association;
        }

        public List<ActivityBean> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityBean> activity) {
            this.activity = activity;
        }

        public static class AssociationBean {
            /**
             * id : 1125
             * logo : http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa
             * nick_name : 数学建模社
             * type : 学术科技
             * star : 5
             * word_introduction : 数学建模社
             */

            private int id;
            private String nick_name;
            private String type;
            private int star;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }
        }

        public static class ActivityBean {
            /**
             * id : 10002
             * name : 活动2
             * status : 未开始
             * head_picture : http://association-1252289837.image.myqcloud.com/images/fd4e5aee826c749475c34f250bfc1f27
             * type : 公益
             * word_introduction : 活动介绍详细2
             * location : 体育馆
             * start_time : 2017-09-06 12:00:00
             * end_time : 2017-09-06 12:30:00
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
