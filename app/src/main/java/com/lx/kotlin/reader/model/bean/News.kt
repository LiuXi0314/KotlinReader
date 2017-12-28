package com.lx.kotlin.reader.model.bean

/**
 * 故事/消息详情
 * Created by liuxi on 2017/12/27.
 */

class News {
    var body: String? = null
    var image_source: String? = null
    var title: String? = null
    var image: String? = null
    var share_url: String? = null
    var id: Int = 0
    var ga_prefix: String? = null
    var type: Int = 0
    var section: SectionBean? = null
    var js: List<*>? = null
    var images: List<String>? = null
    var css: List<String>? = null

    class SectionBean {
        /**
         * thumbnail : https://pic3.zhimg.com/v2-c2f71cecd760f6b1534f6cc87b6a9c92.jpg
         * name : 小事
         * id : 35
         */

        var thumbnail: String? = null
        var name: String? = null
        var id: Int = 0
    }
}
