package org.codetome.kurator.data.user.builder

import org.codetome.kurator.data.domain.CollectionConfig

@Suppress("UNCHECKED_CAST")
/**
 * Builds the configuration for all [org.codetome.kurator.data.domain.Collection]s.
 */
class CollectionsBuilder {

    var collections: MutableList<CollectionConfig<Any>> = mutableListOf()

    inline fun <T> collection(builder: CollectionConfigBuilder<T>.() -> Unit) {
        val ccb = CollectionConfigBuilder<T>()
        builder.invoke(ccb)
        this.collections.add(ccb.build() as CollectionConfig<Any>)
    }

    fun build(): List<CollectionConfig<out Any>> = collections.toList()
}
