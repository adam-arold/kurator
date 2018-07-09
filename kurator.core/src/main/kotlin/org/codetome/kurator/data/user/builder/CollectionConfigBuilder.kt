package org.codetome.kurator.data.user.builder

import org.codetome.kurator.data.domain.CollectionConfig
import org.codetome.kurator.data.domain.TemplateContext
import org.codetome.kurator.defaults.KuratorDefaultLayout
import org.codetome.kurator.template.Layout

/**
 * Builds configuration for a single [org.codetome.kurator.data.domain.Collection].
 */
@Suppress("UNCHECKED_CAST")
data class CollectionConfigBuilder<T>(var name: String = "",
                                      var permalink: String = "",
                                      var layout: Layout<TemplateContext<T>> = KuratorDefaultLayout as Layout<TemplateContext<T>>,
                                      var defaultValues: T? = null) {

    fun build(): CollectionConfig<T> {
        require(defaultValues != null) {
            "No default values were supplied for collection '$name'."
        }
        return CollectionConfig(
                name = name,
                permalink = permalink,
                layout = layout,
                defaultValues = defaultValues!!)
    }
}
