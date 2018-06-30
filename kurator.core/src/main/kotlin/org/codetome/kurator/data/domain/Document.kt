package org.codetome.kurator.data.domain

import java.util.*

data class Document(val title: String,
                    val excerpt: String,
                    val date: String,
                    val categories: List<String>,
                    val tags: List<String>,
                    val id: String,
                    val filename: String,
                    val fileExtension: String,
                    val content: String,
                    val url: String,
                    val path: String,
                    val next: Optional<Document>,
                    val previous: Optional<Document>)
