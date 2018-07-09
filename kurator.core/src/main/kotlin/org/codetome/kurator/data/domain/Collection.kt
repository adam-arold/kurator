package org.codetome.kurator.data.domain

data class Collection<T>(val config: CollectionConfig<T>,
                         val pages: List<Document>) {

    fun layout() = config.layout
}
