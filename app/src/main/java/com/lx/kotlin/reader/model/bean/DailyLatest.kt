package com.lx.kotlin.reader.model.bean

/**
 *
 * 最新日报消息数据
 * Created on 18-1-5 上午9:59
 */

class DailyLatest {

    /**
     * date : 20180105
     * stories : [{"images":["https://pic2.zhimg.com/v2-7f262322ddc2c92d19a933d146d9ae81.jpg"],"type":0,"id":9663904,"ga_prefix":"010509","title":"期末考复习来不及了？教你个办法，说不定还能挽救一下"},{"images":["https://pic4.zhimg.com/v2-54b453a3771181c44fc212b6ae53b0d3.jpg"],"type":0,"id":9664108,"ga_prefix":"010508","title":"从盛名到骂名：算法的  2017 和走在十字路口的科技公司们"},{"title":"我家狗真能听懂我说的话，还是我自作多情？","ga_prefix":"010507","images":["https://pic2.zhimg.com/v2-f2bad5254bab49e9ca247bd88e2e44fd.jpg"],"multipic":true,"type":0,"id":9664169},{"images":["https://pic4.zhimg.com/v2-e5c60afc61c01eadae7aba2acd34f45f.jpg"],"type":0,"id":9664147,"ga_prefix":"010507","title":"「转行人工智能」是否前景一片光明？"},{"title":"2017 年度盘点 · 中国出现了哪些有意思的消费升级现象？","ga_prefix":"010507","images":[],"multipic":true,"type":0,"id":9664178},{"images":["https://pic3.zhimg.com/v2-4b09246727ecedbc218eeee4e980531a.jpg"],"type":0,"id":9664110,"ga_prefix":"010506","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-3bad44157039503bd4b57b3e9753ad44.jpg","type":0,"id":9664108,"ga_prefix":"010508","title":"从盛名到骂名：算法的  2017 和走在十字路口的科技公司们"},{"image":"https://pic1.zhimg.com/v2-ddaac5452577a923dcaa6c5ca8ec54d4.jpg","type":0,"id":9663904,"ga_prefix":"010509","title":"期末考复习来不及了？教你个办法，说不定还能挽救一下"},{"image":"https://pic4.zhimg.com/v2-742c83f91983fb44e4e6bf1464194b53.jpg","type":0,"id":9664178,"ga_prefix":"010507","title":"2017 年度盘点 · 中国出现了哪些有意思的消费升级现象？"},{"image":"https://pic2.zhimg.com/v2-9c12f5dc7bbd8968669e27eff2fa4f15.jpg","type":0,"id":9664147,"ga_prefix":"010507","title":"「转行人工智能」是否前景一片光明？"},{"image":"https://pic4.zhimg.com/v2-cb8c052031d887708ff2c00689eda673.jpg","type":0,"id":9663868,"ga_prefix":"010416","title":"「健身五分钟，自拍两小时」，健身房镜子真不是给你臭美的"}]
     */

    var date: String? = null
    var stories: MutableList<StoriesInfo>? = null
    var top_stories: MutableList<StoriesInfo>? = null

}
