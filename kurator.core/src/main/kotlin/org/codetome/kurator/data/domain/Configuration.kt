package org.codetome.kurator.data.domain

import org.codetome.kurator.template.Layout

data class Configuration(val title: String,
                         val email: String,
                         val description: String,
                         val url: String,
                         val host: String,
                         val port: Int,
                         val baseurl: String,
                         val sourceDir: String,
                         val destinationDir: String,
                         val assetsDir: String,
                         val collectionsDir: String,
                         val layoutsDir: String,
                         val dataDir: String,
                         val debug: Boolean,
                         val markdownExtensions: List<String>,
                         val collectionsConfig: List<CollectionConfig>,
                         val defaultLayout: Layout<TemplateContext>)

