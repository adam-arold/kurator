package org.codetome.kurator.data.domain

import org.codetome.kurator.template.Layout

data class CollectionConfig(val name: String,
                            val permalink: String,
                            val layout: Layout<TemplateContext>,
                            val defaultValues: Any)
