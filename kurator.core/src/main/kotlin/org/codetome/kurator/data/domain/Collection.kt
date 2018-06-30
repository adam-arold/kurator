package org.codetome.kurator.data.domain

data class Collection(val config: CollectionConfig,
                      val pages: List<Document>) {

    fun layout() = config.layout
}
