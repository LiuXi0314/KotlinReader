package com.lx.kotlin.reader.model.bean

/**
 * Created on 18-1-13 下午2:54
 */

class Girls {

    var results: MutableList<Girl>? = null

    class Girl {
        /**
         * _id : 5a5411fb421aa90fef2035f3
         * createdAt : 2018-01-09T08:51:07.91Z
         * desc : 1-9
         * publishedAt : 2018-01-10T07:57:19.486Z
         * source : chrome
         * type : 福利
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/20180109085038_4A7atU_rakukoo_9_1_2018_8_50_25_276.jpeg
         * used : true
         * who : daimajia
         */

        var _id: String? = null
        var createdAt: String? = null
        var desc: String? = null
        var publishedAt: String? = null
        var source: String? = null
        var type: String? = null
        var url: String? = null
        var isUsed: Boolean = false
        var who: String? = null
    }
}
