package org.codetome.kurator.data.user.builder

import org.codetome.kurator.data.domain.Configuration
import org.codetome.kurator.data.domain.TemplateContext
import org.codetome.kurator.defaults.KuratorDefaultLayout
import org.codetome.kurator.template.Layout

class
ConfigurationBuilder(var title: String = "Title",
                     var email: String = "email@example.com",
                     var description: String = "Description",
                     var url: String = "127.0.0.1",
                     var sourceDir: String = "src/main/resources",
                     var destinationDir: String = "site",
                     var assetsDir: String = "assets",
                     var collectionsDir: String = "collections",
                     var layoutsDir: String = "layouts",
                     var dataDir: String = "data",
                     var debug: Boolean = false,
                     var markdownExtensions: List<String> = listOf("markdown", "md"),
                     var host: String = "127.0.0.1",
                     var port: Int = 4000,
                     var baseurl: String = "",
                     var defaultLayout: Layout<TemplateContext> = KuratorDefaultLayout) {

    private var collectionsBuilder = CollectionsBuilder()


    fun collections(builder: CollectionsBuilder.() -> Unit) {
        builder.invoke(collectionsBuilder)
    }

    fun build() = Configuration(
            title = title,
            email = email,
            description = description,
            url = url,
            sourceDir = sourceDir,
            destinationDir = destinationDir,
            assetsDir = buildResourceDir(sourceDir, assetsDir),
            collectionsDir = buildResourceDir(sourceDir, collectionsDir),
            layoutsDir = buildResourceDir(sourceDir, layoutsDir),
            dataDir = buildResourceDir(sourceDir, dataDir),
            debug = debug,
            markdownExtensions = markdownExtensions,
            host = host,
            port = port,
            baseurl = baseurl,
            collectionsConfig = collectionsBuilder.build(),
            defaultLayout = defaultLayout)

    private fun buildResourceDir(vararg pathElements: String): String {
        // we can't use File.separator because Reflections doesn't support windows-style separators...
        return pathElements.filter { it.isNotBlank() }.joinToString("/")
    }

}
