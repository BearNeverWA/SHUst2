package com.shu.shust2.model;

import java.util.List;

/**
 * Created by Leo on 2017/9/7.
 */

public class ClubSearch {

    /**
     * errorCode : 0
     * errorStr : ok
     * resultCount : 1
     * results : {"association":[{"id":1001,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"模拟联合国协会","type":"学术科技","star":5,"word_introduction":"模拟联合国协会"}]}
     * endMark : 1
     * queries : [{"query":"select count(*) as aggregate from `association` where `name` like ? and `status` = ?","bindings":["%模拟%",2],"time":1.3},{"query":"select `id`, `logo`, `name`, `nick_name`, `type`, `star`, `word_introduction` from `association` where `name` like ? and `status` = ? order by `sort` desc, `star` desc limit 15 offset 0","bindings":["%模拟%",2],"time":0.62},{"query":"select * from `config_association_type` where `config_association_type`.`id` in (?)","bindings":[6],"time":0.28}]
     * timeCost : 0.00806
     * timeNow : 2017-09-07 23:31:12
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
        private java.util.List<AssociationBean> association;

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
