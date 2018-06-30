package org.codetome.kurator.data.user.builder

import org.codetome.kurator.data.domain.CollectionConfig

/**
 * Builds the configuration for all [org.codetome.kurator.data.domain.Collection]s.
 */
class CollectionsBuilder {

    var collections: MutableList<CollectionConfig> = mutableListOf()

    inline fun collection(builder: CollectionConfigBuilder.() -> Unit) {
        val ccb = CollectionConfigBuilder()
        builder.invoke(ccb)
        this.collections.add(ccb.build())
    }

    fun build(): List<CollectionConfig> = collections.toList()
}
