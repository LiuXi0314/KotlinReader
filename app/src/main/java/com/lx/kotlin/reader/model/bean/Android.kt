package com.lx.kotlin.reader.model.bean

/**
 * 干货Android 列表
 * Created on 18-1-15 上午10:21
 */

class Android {

    var results: MutableList<ResultsInfo>? = null

    class ResultsInfo {
        /**
         * _id : 5a4af266421aa90fe50c02b6
         * createdAt : 2018-01-02T10:45:58.490Z
         * desc : 一个简洁、实用、方便的Android异步处理库，已应用到百万日活的线上项目中
         * publishedAt : 2018-01-10T07:57:19.486Z
         * source : web
         * type : Android
         * url : https://github.com/SilenceDut/TaskScheduler
         * used : true
         * who : null
         * images : ["http://img.gank.io/c141c075-afdf-4f8f-9d17-ec41faa10e75"]
         */

        var _id: String? = null
        var createdAt: String? = null
        var desc: String? = null
        var publishedAt: String? = null
        var source: String? = null
        var type: String? = null
        var url: String? = null
        var isUsed: Boolean = false
        var who: Any? = null
        var images: List<String>? = null
    }
}
