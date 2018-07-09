package org.codetome.kurator.data.domain

import org.codetome.kurator.template.Layout

data class CollectionConfig<T>(val name: String,
                            val permalink: String,
                            val layout: Layout<TemplateContext<T>>,
                            val defaultValues: T)
