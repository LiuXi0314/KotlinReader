package com.lx.kotlin.reader.model.bean

/**
 * Created on 17-12-25 上午11:05
 */

class Theme {

    var others: List<OthersInfo>? = null

    class OthersInfo {
        var color: Int = 0
        var thumbnail: String? = null
        var description: String? = null
        var id: Int = 0
        var name: String? = null
    }
}
