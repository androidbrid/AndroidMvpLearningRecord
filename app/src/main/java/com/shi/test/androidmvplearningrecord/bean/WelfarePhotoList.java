package com.shi.test.androidmvplearningrecord.bean;

import java.util.List;

/**
 * Created by long on 2016/10/10.
 */

public class WelfarePhotoList {


    /**
     * error : false
     * results : [{"_id":"5ccdbc219d212239df927a93","createdAt":"2019-05-04T16:21:53.523Z","desc":"2019-05-05","publishedAt":"2019-05-04T16:21:59.733Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg","used":true,"who":"lijinshanmx"},{"_id":"5cc43919fc3326376038d233","createdAt":"2019-04-27T19:12:25.536Z","desc":"2019-04-27","publishedAt":"2019-04-27T19:12:51.865Z","source":"web","type":"福利","url":"https://ww1.sinaimg.cn/large/0065oQSqly1g2hekfwnd7j30sg0x4djy.jpg","used":true,"who":"lijinshanmx"},{"_id":"5c6a4ae99d212226776d3256","createdAt":"2019-02-18T06:04:25.571Z","desc":"2019-02-18","publishedAt":"2019-04-10T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1g0ajj4h6ndj30sg11xdmj.jpg","used":true,"who":"lijinshanmx"},{"_id":"5c2dabdb9d21226e068debf9","createdAt":"2019-01-03T06:29:47.895Z","desc":"2019-01-03","publishedAt":"2019-01-03T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1fytdr77urlj30sg10najf.jpg","used":true,"who":"lijinshanmx"},{"_id":"5c25db189d21221e8ada8664","createdAt":"2018-12-28T08:13:12.688Z","desc":"2018-12-28","publishedAt":"2018-12-28T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1fymj13tnjmj30r60zf79k.jpg","used":true,"who":"lijinshanmx"},{"_id":"5c12216d9d21223f5a2baea2","createdAt":"2018-12-13T09:07:57.2Z","desc":"2018-12-13","publishedAt":"2018-12-13T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fy58bi1wlgj30sg10hguu.jpg","used":true,"who":"lijinshanmx"},{"_id":"5bfe1a5b9d2122309624cbb7","createdAt":"2018-11-28T04:32:27.338Z","desc":"2018-11-28","publishedAt":"2018-11-28T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fxno2dvxusj30sf10nqcm.jpg","used":true,"who":"lijinshanmx"},{"_id":"5bf22fd69d21223ddba8ca25","createdAt":"2018-11-19T03:36:54.950Z","desc":"2018-11-19","publishedAt":"2018-11-19T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg","used":true,"who":"lijinshanmx"},{"_id":"5be14edb9d21223dd50660f8","createdAt":"2018-11-06T08:20:43.656Z","desc":"2018-11-06","publishedAt":"2018-11-06T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fwyf0wr8hhj30ie0nhq6p.jpg","used":true,"who":"lijinshanmx"},{"_id":"5bcd71979d21220315c663fc","createdAt":"2018-10-22T06:43:35.440Z","desc":"2018-10-22","publishedAt":"2018-10-22T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fwgzx8n1syj30sg15h7ew.jpg","used":true,"who":"lijinshanmx"}]
     */

    private boolean error;
    private List<WelfarePhotoInfo> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<WelfarePhotoInfo> getResults() {
        return results;
    }

    public void setResults(List<WelfarePhotoInfo> results) {
        this.results = results;
    }
}
