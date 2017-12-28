package com.lx.kotlin.reader.utils

/**
 * Created on 17-12-28 下午5:06
 */
object HtmlUtils {
    fun structHtml(oriStr: String, cssList: List<String>): String {
        val htmlString = StringBuilder("<html><head>")
        for (css in cssList) {
            htmlString.append(structCssLink(css))
        }
        htmlString.append("</head><body>")
        htmlString.append("<style>img{max-width:340px !important;}</style>")
        htmlString.append(oriStr)
        htmlString.append("</body></html>")
        return htmlString.toString()
    }

    fun structCssLink(css: String): String {
        return "<link type=\\\"text/css\\\" rel=\\\"stylesheet\\\" href=\\\"$css\">"
    }
}