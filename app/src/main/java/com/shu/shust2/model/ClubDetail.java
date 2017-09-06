package com.shu.shust2.model;

/**
 * Created by Leo on 2017/9/7.
 */

public class ClubDetail {

    /**
     * errorCode : 0
     * errorStr : ok
     * resultCount : 20
     * results : {"id":1034,"name":"德鲁克管理协会","type":"理论学习","star":4,"department":"无挂靠单位","instructor":"","chairperson":"","introduction":"上海大学德鲁克管理协会作为德鲁克青年社区在全国高校覆盖的网点之一，成立于2009年。\n社团拥有良好的学习以及衔接体系，我们会以TEAM LEARNING的方式学习德鲁克管理思想，辅助以实践活动以及和企业家面对面交流，并同步建立个人学习档案，从而对大学生在大一大二阶段培养较好的个人素质。其后若通过德鲁克青年社区的DURCKER INSIDER学员以及催化师测评，正式成为德鲁克青年社区学员，则德鲁克青年社区会负责安排后续组织接手并对其进行深造，至将优秀人才输送至优秀企业。\n社团宗旨：在德鲁克管理学院以及德鲁克青年社区的理念下，侧重于个人管理，学习德鲁克管理思想以提升个人竞争力。","nick_name":"暂无简称","logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","word_introduction":"德鲁克管理协会","mail":"","wechat_id":"","wechat_name":"","wechat_code":"http://association-1252289837.image.myqcloud.com/images/1034.jpg","weibo":"","qq_number":"","qq_qun":"","qq_url":"","due":"无社费"}
     * endMark : 0
     * queries : [{"query":"select `id`, `name`, `type`, `star`, `department`, `instructor`, `chairperson`, `status`, `sort`, `introduction`, `nick_name`, `logo`, `word_introduction`, `mail`, `wechat_id`, `wechat_name`, `wechat_code`, `weibo`, `qq_number`, `qq_qun`, `qq_url`, `apply_status`, `due`, `unit`, `editor`, `gmt_create`, `gmt_modified` from `association` where `id` = ? and `status` = ? limit 1","bindings":["1034",2],"time":2.18},{"query":"select * from `config_association_type` where `config_association_type`.`id` in (?)","bindings":[2],"time":0.32},{"query":"select * from `config_department` where `config_department`.`id` in (?)","bindings":[0],"time":0.33}]
     * timeCost : 0.01012
     * timeNow : 2017-09-07 01:21:05
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
         * id : 1034
         * name : 德鲁克管理协会
         * type : 理论学习
         * star : 4
         * department : 无挂靠单位
         * instructor :
         * chairperson :
         * introduction : 上海大学德鲁克管理协会作为德鲁克青年社区在全国高校覆盖的网点之一，成立于2009年。
         社团拥有良好的学习以及衔接体系，我们会以TEAM LEARNING的方式学习德鲁克管理思想，辅助以实践活动以及和企业家面对面交流，并同步建立个人学习档案，从而对大学生在大一大二阶段培养较好的个人素质。其后若通过德鲁克青年社区的DURCKER INSIDER学员以及催化师测评，正式成为德鲁克青年社区学员，则德鲁克青年社区会负责安排后续组织接手并对其进行深造，至将优秀人才输送至优秀企业。
         社团宗旨：在德鲁克管理学院以及德鲁克青年社区的理念下，侧重于个人管理，学习德鲁克管理思想以提升个人竞争力。
         * nick_name : 暂无简称
         * logo : http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa
         * word_introduction : 德鲁克管理协会
         * mail :
         * wechat_id :
         * wechat_name :
         * wechat_code : http://association-1252289837.image.myqcloud.com/images/1034.jpg
         * weibo :
         * qq_number :
         * qq_qun :
         * qq_url :
         * due : 无社费
         */

        private int id;
        private String name;
        private String type;
        private int star;
        private String instructor;
        private String chairperson;
        private String introduction;

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

        public String getInstructor() {
            return instructor;
        }

        public void setInstructor(String instructor) {
            this.instructor = instructor;
        }

        public String getChairperson() {
            return chairperson;
        }

        public void setChairperson(String chairperson) {
            this.chairperson = chairperson;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }
}
