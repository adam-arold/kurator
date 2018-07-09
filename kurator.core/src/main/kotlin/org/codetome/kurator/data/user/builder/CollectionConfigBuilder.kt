package org.codetome.kurator.data.user.builder

import org.codetome.kurator.data.domain.CollectionConfig
import org.codetome.kurator.data.domain.TemplateContext
import org.codetome.kurator.defaults.KuratorDefaultLayout
import org.codetome.kurator.template.Layout

/**
 * Builds configuration for a single [org.codetome.kurator.data.domain.Collection].
 */
data class CollectionConfigBuilder(var name: String = "",
                                   var permalink: String = "",
                                   var layout: Layout<TemplateContext> = KuratorDefaultLayout,
                                   var defaultValues: Any = "") {

    fun build() = CollectionConfig(
            name = name,
            permalink = permalink,
            layout = layout,
            defaultValues = defaultValues)
}
