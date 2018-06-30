package org.codetome.kurator.data.user

/**
 * Contains all the data which can be supplied by the user for a document.
 */
class UserDocumentData {
    var title: String = ""
    var excerpt: String = ""
    var date: String = ""
    var categories: List<String> = listOf()
    var tags: List<String> = listOf()
    var permalink: String = ""
}
