package com.lx.kotlin.reader.model.bean

/**
 * 知乎主题子列表
 * Created on 17-12-25 上午11:33
 */

class ThemeChildList {

    var description: String? = null
    var background: String? = null
    var color: Int = 0
    var name: String? = null
    var image: String? = null
    var image_source: String? = null
    var stories: MutableList<StoriesInfo>? = null
    var editors: List<*>? = null

}
