package com.shu.shust2.model;

import java.util.List;

/**
 * Created by Leo on 2017/9/6.
 */

public class ClubBean {

    /**
     * errorCode : 0
     * errorStr : ok
     * resultCount : 1
     * results : {"association":[{"id":1001,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"模拟联合国协会","type":"学术科技","star":5,"word_introduction":"模拟联合国协会"},{"id":1005,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"慈善蓝纽带爱心志愿者服务社","type":"公益实践","star":5,"word_introduction":"慈善蓝纽带爱心志愿者服务社"},{"id":1097,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"MCs 主持人社","type":"文化艺术","star":5,"word_introduction":" MCs 主持人社"},{"id":1099,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"重华汉服社","type":"文化艺术","star":5,"word_introduction":"重华汉服社"},{"id":1100,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"Soul Kool 街舞社","type":"文化艺术","star":5,"word_introduction":" Soul Kool 街舞社"},{"id":1102,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"吉他协会","type":"文化艺术","star":5,"word_introduction":"吉他协会"},{"id":1123,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"辩论协会","type":"学术科技","star":5,"word_introduction":"辩论协会"},{"id":1124,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"大学生创业者协会","type":"学术科技","star":5,"word_introduction":"大学生创业者协会"},{"id":1125,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"数学建模社","type":"学术科技","star":5,"word_introduction":"数学建模社"},{"id":1006,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"白玉兰社区服务社","type":"公益实践","star":4,"word_introduction":"白玉兰社区服务社"},{"id":1003,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"原创音乐社","type":"文化艺术","star":3,"word_introduction":"原创音乐社"},{"id":1004,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"MOMO 编织社","type":"文化艺术","star":3,"word_introduction":" MOMO 编织社"},{"id":1002,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"SHULAX\u2014\u2014上大棍网球社","type":"体育健身","star":1,"word_introduction":"SHULAX\u2014\u2014上大棍网球社"}]}
     * endMark : 1
     * queries : [{"query":"select count(*) as aggregate from `association` where `status` = ?","bindings":[2],"time":2.06},{"query":"select `id`, `logo`, `name`, `nick_name`, `type`, `star`, `word_introduction` from `association` where `status` = ? order by `sort` desc, `star` desc limit 15 offset 0","bindings":[2],"time":0.66},{"query":"select * from `config_association_type` where `config_association_type`.`id` in (?, ?, ?, ?)","bindings":[1,4,5,6],"time":0.41}]
     * timeCost : 0.01280
     * timeNow : 2017-09-06 20:59:48
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

        public List<AssociationBean> getAssociation() {
            return association;
        }

        public void setAssociation(List<AssociationBean> association) {
            this.association = association;
        }

        public static class AssociationBean {
            /**
             * id : 1001
             * logo : http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa
             * nick_name : 模拟联合国协会
             * type : 学术科技
             * star : 5
             * word_introduction : 模拟联合国协会
             */

            private int id;
            private String logo;
            private String nick_name;
            private String type;
            private int star;
            private String word_introduction;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
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

            public String getWord_introduction() {
                return word_introduction;
            }

            public void setWord_introduction(String word_introduction) {
                this.word_introduction = word_introduction;
            }
        }
    }
}
