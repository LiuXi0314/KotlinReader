package com.lx.kotlin.reader.model

/**
 *  数据类是一种非常强大的类，它可以让你避免创建Java中的用于保存状态但又操作非常简单的POJO的模版代码。
 * 它们通常只提供了用于访问它们属性的简单的getter 和setter。
 * Created on 17-12-4 下午3:10
 */
data class ZhihuModel(var name: String,
                      var content: String,
                      var imageUrl: String,
                      var author: String,
                      var avatar: String)

