package com.lx.kotlin.reader.model.bean

/**
 * Created on 17-12-25 上午11:33
 */

class DailyList {

    var description: String? = null
    var background: String? = null
    var color: Int = 0
    var name: String? = null
    var image: String? = null
    var image_source: String? = null
    var stories: List<StoriesInfo>? = null
    var editors: List<*>? = null

    class StoriesInfo {
        var type: Int = 0
        var id: Int = 0
        var title: String? = null
        var images: List<String>? = null
    }
}
