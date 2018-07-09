package org.codetome.kurator.data.domain

data class Site(val title: String,
                val collections: List<Collection<out Any>>,
                val pages: List<Document>,
                val configuration: Configuration)
