package com.shu.shust2.model;

import java.util.List;

/**
 * Created by Leo on 2017/9/5.
 */

public class HotBean {

    /**
     * errorCode : 0
     * errorStr : ok
     * resultCount : 2
     * results : {"banner":[],"association":[{"id":1005,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"慈善蓝纽带爱心志愿者服务社","type":"公益实践","star":5,"word_introduction":"慈善蓝纽带爱心志愿者服务社"},{"id":1001,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"模拟联合国协会","type":"学术科技","star":5,"word_introduction":"模拟联合国协会"},{"id":1006,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"白玉兰社区服务社","type":"公益实践","star":4,"word_introduction":"白玉兰社区服务社"},{"id":1123,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"辩论协会","type":"学术科技","star":5,"word_introduction":"辩论协会"},{"id":1099,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"重华汉服社","type":"文化艺术","star":5,"word_introduction":"重华汉服社"},{"id":1100,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"Soul Kool 街舞社","type":"文化艺术","star":5,"word_introduction":" Soul Kool 街舞社"},{"id":1102,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"吉他协会","type":"文化艺术","star":5,"word_introduction":"吉他协会"},{"id":1125,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"数学建模社","type":"学术科技","star":5,"word_introduction":"数学建模社"},{"id":1002,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"SHULAX\u2014\u2014上大棍网球社","type":"体育健身","star":1,"word_introduction":"SHULAX\u2014\u2014上大棍网球社"},{"id":1003,"logo":"http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa","nick_name":"原创音乐社","type":"文化艺术","star":3,"word_introduction":"原创音乐社"}]}
     * endMark : 0
     * queries : [{"query":"select `name`, `img` from `config_banner` order by `sort` asc","bindings":[],"time":1.44},{"query":"select `id`, `logo`, `name`, `nick_name`, `type`, `star`, `word_introduction` from `association` where `status` = ? order by RAND() limit 10","bindings":[2],"time":0.55},{"query":"select * from `config_association_type` where `config_association_type`.`id` in (?, ?, ?, ?)","bindings":[1,4,5,6],"time":0.3}]
     * timeCost : 0.00788
     * timeNow : 2017-09-05 13:41:44
     */

    private int errorCode;
    private String errorStr;
    private int resultCount;
    private ResultsBean results;
    private int endMark;
    private String timeCost;
    private String timeNow;
    private List<QueriesBean> queries;

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

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public int getEndMark() {
        return endMark;
    }

    public void setEndMark(int endMark) {
        this.endMark = endMark;
    }

    public String getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(String timeCost) {
        this.timeCost = timeCost;
    }

    public String getTimeNow() {
        return timeNow;
    }

    public void setTimeNow(String timeNow) {
        this.timeNow = timeNow;
    }

    public List<QueriesBean> getQueries() {
        return queries;
    }

    public void setQueries(List<QueriesBean> queries) {
        this.queries = queries;
    }

    public static class ResultsBean {
        private List<?> banner;
        private List<AssociationBean> association;

        public List<?> getBanner() {
            return banner;
        }

        public void setBanner(List<?> banner) {
            this.banner = banner;
        }

        public List<AssociationBean> getAssociation() {
            return association;
        }

        public void setAssociation(List<AssociationBean> association) {
            this.association = association;
        }

        public static class AssociationBean {
            /**
             * id : 1005
             * logo : http://association-1252289837.image.myqcloud.com/images/d23aecf5158933c251be02ee3335f3fa
             * nick_name : 慈善蓝纽带爱心志愿者服务社
             * type : 公益实践
             * star : 5
             * word_introduction : 慈善蓝纽带爱心志愿者服务社
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

    public static class QueriesBean {
        /**
         * query : select `name`, `img` from `config_banner` order by `sort` asc
         * bindings : []
         * time : 1.44
         */

        private String query;
        private double time;
        private List<?> bindings;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public double getTime() {
            return time;
        }

        public void setTime(double time) {
            this.time = time;
        }

        public List<?> getBindings() {
            return bindings;
        }

        public void setBindings(List<?> bindings) {
            this.bindings = bindings;
        }
    }
}
