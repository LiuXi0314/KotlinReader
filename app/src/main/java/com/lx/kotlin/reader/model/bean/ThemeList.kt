package com.lx.kotlin.reader.model.bean

/**
 * 知乎主题列表
 * Created on 17-12-25 上午11:05
 */

class ThemeList {

    var others: MutableList<OthersInfo>? = null

   open class OthersInfo {
        var color: Int = 0
        var thumbnail: String? = null
        var description: String? = null
        var id: Int = 0
        var name: String? = null
    }
}
