package org.codetome.kurator.service

import kotlinx.html.stream.appendHTML
import org.codetome.kurator.data.domain.*
import org.codetome.kurator.data.domain.Collection
import org.codetome.kurator.template.Layout
import org.reflections.Reflections
import java.io.File


@Suppress("UNCHECKED_CAST")
class SiteGenerator(private val documentLoader: DocumentLoader) {

    fun generate(config: Configuration) {

        val layouts = loadLayouts(config)

        // collections
        val collections = loadCollections(config)

        // site data
        val siteData = loadSiteData(config, collections)

        val destinationDir = prepareDestinationDir(config)

        generateCollections(collections, config, siteData, destinationDir)

        copyAssets(config)
    }

    private fun loadSiteData(config: Configuration, collections: List<Collection>): Site {
        return Site(
                title = config.title,
                collections = collections,
                pages = collections.flatMap { it.pages },
                configuration = config)
    }

    private fun loadLayouts(config: Configuration): Map<String, Layout<out Any>> {
        val reflections = Reflections(config.layoutsDir)
        return reflections.getSubTypesOf(Layout::class.java).map { klass ->
            val instances = klass.declaredFields.filter { it.name == OBJECT_INSTANCE_NAME }
            require(instances.size == 1) {
                "A layout must be declared as an `object`."
            }
            val layout = instances.first().get(null) as? Layout<out Any>
                    ?: throw IllegalArgumentException("A Layout must extend `Layout`.")
            layout::class.simpleName!! to layout
        }.toMap()
    }

    private fun generateCollections(collections: List<Collection>, config: Configuration, siteData: Site, destinationDir: File) {
        collections.forEach { coll ->
            File(config.destinationDir + File.separator + coll.config.name).mkdirs()
            coll.pages.forEach { page ->
                val contentBuilder = coll.layout().templateBuilder
                val sb = StringBuilder()
                val tagConsumer = sb.appendHTML()
                contentBuilder.build(tagConsumer, TemplateContext(
                        page = page,
                        site = siteData))

                val documentFile = File(destinationDir.absolutePath + File.separator +
                        coll.config.name + File.separator +
                        page.filename + ".html")
                documentFile.writeText(sb.toString())
            }
        }
    }

    private fun prepareDestinationDir(config: Configuration): File {
        val destinationDir = File(config.destinationDir)
        destinationDir.listFiles().forEach {
            it.deleteRecursively()
        }
        destinationDir.mkdirs()
        return destinationDir
    }

    private fun copyAssets(config: Configuration) {
        val assetsDir = File(config.assetsDir)
        assetsDir.copyRecursively(File(config.destinationDir + File.separator + config.assetsDir))
    }

    private fun loadCollections(config: Configuration): List<Collection> {
        val collectionsConfig = config.collectionsConfig.map { it.name to it }.toMap()
        val collectionsDir = File(config.collectionsDir)
        return collectionsDir.listFiles { it ->
            it.isDirectory
        }.filter {
            collectionsConfig.containsKey(it.name)
        }.map { collectionDir ->
            val collName = collectionDir.name
            Collection(
                    config = collectionsConfig[collName]
                            ?: throw NoSuchElementException("No config found for collection: '$collName'."),
                    pages = collectionDir.listFiles()
                            .filter { it.isFile }
                            .map { documentLoader.load(it, config) })
        }
    }

    companion object {
        const val OBJECT_INSTANCE_NAME = "INSTANCE"
    }
}
